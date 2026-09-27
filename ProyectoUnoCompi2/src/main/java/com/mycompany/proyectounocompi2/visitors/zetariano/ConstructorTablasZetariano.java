package com.mycompany.proyectounocompi2.visitors.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.zetariano.*;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.tablas.Campo;
import com.mycompany.proyectounocompi2.tablas.CategoriaSimbolo;
import com.mycompany.proyectounocompi2.tablas.ConstructorTablasBase;
import com.mycompany.proyectounocompi2.tablas.Simbolo;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import com.mycompany.proyectounocompi2.tablas.TipoDefinido;
import java.util.ArrayList;
import java.util.List;

// Llena las tablas con un archivo .z
public class ConstructorTablasZetariano extends ConstructorTablasBase implements Visitor<Void> {

    private TipoDefinido clase;

    public ConstructorTablasZetariano(Tablas tablas, ManejadorErrores errores) {
        super(tablas, errores);
    }

    // registra el nombre de la clase para que cualquier archivo pueda usarla
    public void registrarTipos(Clase nodo) {
        clase = new TipoDefinido(nodo.nombre(), TipoDefinido.Categoria.CLASE, errores.getArchivo());
        if (!tablas.tipos().agregar(clase)) {
            error(nodo.posicion(), nodo.nombre(), "Ya existe una estructura o clase llamada '" + nodo.nombre() + "'");
            clase = null;
        }
    }

    // Abre el marco de un constructor/metodo
    private int visitarMarco(String nombre, List<Parametro> parametros, Bloque cuerpo, AstNode nodo) {
        tablas.simbolos().entrarAmbito(clase.getNombre() + "." + nombre, true, nodo);
        declararVariable("this", CategoriaSimbolo.PARAMETRO, Tipo.clase(clase.getNombre()), nodo.posicion());
        parametros.forEach(parametro -> parametro.accept(this));
        cuerpo.sentencias().forEach(sentencia -> sentencia.accept(this));
        int tamanoMarco = tablas.simbolos().getActual().getTamanoMarco();
        tablas.simbolos().salirAmbito();
        return tamanoMarco;
    }

    private static List<Tipo> tipos(List<Parametro> parametros) {
        List<Tipo> tipos = new ArrayList<>();
        parametros.forEach(parametro -> tipos.add(parametro.tipo()));
        return tipos;
    }

    // ---------- Clase ----------

    @Override
    public Void visitarClase(Clase nodo) {
        if (clase == null) {
            return null;
        }
        //  ahi se evaluan los valores iniciales de los atributos
        tablas.simbolos().entrarAmbito(clase.getNombre(), true, nodo);
        nodo.atributos().forEach(atributo -> atributo.accept(this));
        nodo.constructores().forEach(constructor -> constructor.accept(this));
        nodo.metodos().forEach(metodo -> metodo.accept(this));
        tablas.simbolos().salirAmbito();
        return null;
    }

    @Override
    public Void visitarAtributo(Atributo nodo) {
        verificarTipo(nodo.tipo(), nodo.posicion());
        for (Declarador declarador : nodo.declaradores()) {
            Campo campo = new Campo(declarador.nombre(), nodo.tipo(), clase.getCampos().size(), List.of(),
                    declarador.posicion());
            if (!clase.agregarCampo(campo)) {
                error(declarador.posicion(), declarador.nombre(),
                        "La clase '" + clase.getNombre() + "' ya tiene un atributo '" + declarador.nombre() + "'");
            }
        }
        return null;
    }

    @Override
    public Void visitarDeclarador(Declarador nodo) {
        return null;
    }

    @Override
    public Void visitarConstructor(Constructor nodo) {
        if (!nodo.nombre().equals(clase.getNombre())) {
            error(nodo.posicion(), nodo.nombre(),
                    "El constructor '" + nodo.nombre() + "' debe llamarse igual que la clase '" + clase.getNombre() + "'");
        }
        int tamanoMarco = visitarMarco(clase.getNombre(), nodo.parametros(), nodo.cuerpo(), nodo);
        Simbolo constructor = new Simbolo(clase.getNombre(), CategoriaSimbolo.CONSTRUCTOR,
                Tipo.clase(clase.getNombre()), tipos(nodo.parametros()), clase.getNombre(), errores.getArchivo(),
                nodo.posicion(), -1, tamanoMarco);
        if (!tablas.simbolos().declararConstructor(clase.getNombre(), constructor)) {
            error(nodo.posicion(), nodo.nombre(), "Ya existe un constructor con los mismos tipos de parámetros");
        }
        return null;
    }

    @Override
    public Void visitarMetodo(Metodo nodo) {
        verificarTipo(nodo.tipoRetorno(), nodo.posicion());
        int tamanoMarco = visitarMarco(nodo.nombre(), nodo.parametros(), nodo.cuerpo(), nodo);
        Simbolo metodo = new Simbolo(nodo.nombre(), CategoriaSimbolo.METODO, nodo.tipoRetorno(),
                tipos(nodo.parametros()), clase.getNombre(), errores.getArchivo(), nodo.posicion(), -1, tamanoMarco);
        if (!tablas.simbolos().declararMetodo(clase.getNombre(), metodo)) {
            error(nodo.posicion(), nodo.nombre(),
                    "Ya existe un método '" + nodo.nombre() + "' con los mismos tipos de parámetros");
        }
        return null;
    }

    @Override
    public Void visitarParametro(Parametro nodo) {
        declararVariable(nodo.nombre(), CategoriaSimbolo.PARAMETRO, nodo.tipo(), nodo.posicion());
        return null;
    }

    // ---------- Sentencias ----------

    @Override
    public Void visitarBloque(Bloque nodo) {
        tablas.simbolos().entrarAmbito(tablas.simbolos().getActual().getNombre() + "/bloque", false, nodo);
        nodo.sentencias().forEach(sentencia -> sentencia.accept(this));
        tablas.simbolos().salirAmbito();
        return null;
    }

    @Override
    public Void visitarDeclaracionLocal(DeclaracionLocal nodo) {
        for (Declarador declarador : nodo.declaradores()) {
            declararVariable(declarador.nombre(), CategoriaSimbolo.VARIABLE, nodo.tipo(), declarador.posicion());
        }
        return null;
    }

    @Override
    public Void visitarIf(If nodo) {
        nodo.entonces().accept(this);
        if (nodo.sino() != null) {
            nodo.sino().accept(this);
        }
        return null;
    }

    @Override
    public Void visitarSwitch(Switch nodo) {
        nodo.secciones().forEach(seccion -> seccion.accept(this));
        return null;
    }

    @Override
    public Void visitarSeccionSwitch(SeccionSwitch nodo) {
        tablas.simbolos().entrarAmbito(tablas.simbolos().getActual().getNombre() + "/case", false, nodo);
        nodo.sentencias().forEach(sentencia -> sentencia.accept(this));
        tablas.simbolos().salirAmbito();
        return null;
    }

    @Override
    public Void visitarFor(For nodo) {
        // la variable declarada en el for solo existe dentro del ciclo
        tablas.simbolos().entrarAmbito(tablas.simbolos().getActual().getNombre() + "/for", false, nodo);
        if (nodo.declaracion() != null) {
            nodo.declaracion().accept(this);
        }
        nodo.cuerpo().accept(this);
        tablas.simbolos().salirAmbito();
        return null;
    }

    @Override
    public Void visitarWhile(While nodo) {
        nodo.cuerpo().accept(this);
        return null;
    }

    @Override
    public Void visitarDoWhile(DoWhile nodo) {
        nodo.cuerpo().accept(this);
        return null;
    }

    @Override
    public Void visitarBreak(Break nodo) {
        return null;
    }

    @Override
    public Void visitarContinue(Continue nodo) {
        return null;
    }

    @Override
    public Void visitarReturn(Return nodo) {
        return null;
    }

    @Override
    public Void visitarPrintln(Println nodo) {
        return null;
    }

    @Override
    public Void visitarPrint(Print nodo) {
        return null;
    }

    @Override
    public Void visitarSentenciaExpresion(SentenciaExpresion nodo) {
        return null;
    }

    // ---------- Expresiones ----------

    @Override
    public Void visitarLiteral(Literal nodo) {
        return null;
    }

    @Override
    public Void visitarListaInicializacion(ListaInicializacion nodo) {
        return null;
    }

    @Override
    public Void visitarThis(This nodo) {
        return null;
    }

    @Override
    public Void visitarIdentificador(Identificador nodo) {
        return null;
    }

    @Override
    public Void visitarLlamadaMetodo(LlamadaMetodo nodo) {
        return null;
    }

    @Override
    public Void visitarAccesoAtributo(AccesoAtributo nodo) {
        return null;
    }

    @Override
    public Void visitarAccesoIndice(AccesoIndice nodo) {
        return null;
    }

    @Override
    public Void visitarOperacionPostfija(OperacionPostfija nodo) {
        return null;
    }

    @Override
    public Void visitarOperacionUnaria(OperacionUnaria nodo) {
        return null;
    }

    @Override
    public Void visitarOperacionBinaria(OperacionBinaria nodo) {
        return null;
    }

    @Override
    public Void visitarTernaria(Ternaria nodo) {
        return null;
    }

    @Override
    public Void visitarAsignacionExpresion(AsignacionExpresion nodo) {
        return null;
    }

    @Override
    public Void visitarNuevoObjeto(NuevoObjeto nodo) {
        return null;
    }

    @Override
    public Void visitarNuevoArreglo(NuevoArreglo nodo) {
        return null;
    }

    @Override
    public Void visitarReadln(Readln nodo) {
        return null;
    }
}
