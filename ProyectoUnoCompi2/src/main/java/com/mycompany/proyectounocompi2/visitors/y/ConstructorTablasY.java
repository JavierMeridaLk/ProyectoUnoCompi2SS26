package com.mycompany.proyectounocompi2.visitors.y;

import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import com.mycompany.proyectounocompi2.ast.y.*;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.tablas.Campo;
import com.mycompany.proyectounocompi2.tablas.CategoriaSimbolo;
import com.mycompany.proyectounocompi2.tablas.ConstructorTablasBase;
import com.mycompany.proyectounocompi2.tablas.Simbolo;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import com.mycompany.proyectounocompi2.tablas.TipoDefinido;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

// Llena las tablas con un archivo .y
public class ConstructorTablasY extends ConstructorTablasBase implements Visitor<Void> {

    // Estructuras de la seccion
    private final Map<DefinicionEstructura, TipoDefinido> registradas = new IdentityHashMap<>();

    public ConstructorTablasY(Tablas tablas, ManejadorErrores errores) {
        super(tablas, errores);
    }

    // registra los nombres de las estructuras para que cualquier archivo pueda usarlas
    public void registrarTipos(Programa programa) {
        for (DefinicionEstructura estructura : programa.estructuras()) {
            registradas.put(estructura, registrar(estructura));
        }
    }

    private TipoDefinido registrar(DefinicionEstructura nodo) {
        TipoDefinido tipo = new TipoDefinido(nodo.nombre(), TipoDefinido.Categoria.ESTRUCTURA,
                errores.getArchivo());
        if (!tablas.tipos().agregar(tipo)) {
            error(nodo.posicion(), nodo.nombre(), "Ya existe una estructura o clase llamada '" + nodo.nombre() + "'");
            return null;
        }
        return tipo;
    }

    private void visitarBloque(String nombre, List<Statement> sentencias, AstNode nodo) {
        tablas.simbolos().entrarAmbito(tablas.simbolos().getActual().getNombre() + "/" + nombre, false, nodo);
        sentencias.forEach(sentencia -> sentencia.accept(this));
        tablas.simbolos().salirAmbito();
    }

    // ---------- Programa ----------

    @Override
    public Void visitarPrograma(Programa nodo) {
        nodo.estructuras().forEach(estructura -> estructura.accept(this));
        nodo.funciones().forEach(funcion -> funcion.accept(this));
        return null;
    }

    @Override
    public Void visitarDefinicionEstructura(DefinicionEstructura nodo) {
        TipoDefinido tipo = registradas.containsKey(nodo) ? registradas.get(nodo) : registrar(nodo);
        if (tipo == null) {
            return null;
        }
        for (AtributoEstructura atributo : nodo.atributos()) {
            verificarTipo(atributo.tipo(), atributo.posicion());
            Campo campo = new Campo(atributo.nombre(), atributo.tipo(), tipo.getCampos().size(),
                    tamanos(atributo.dimensiones()), atributo.posicion());
            if (!tipo.agregarCampo(campo)) {
                error(atributo.posicion(), atributo.nombre(),
                        "La estructura '" + nodo.nombre() + "' ya tiene un atributo '" + atributo.nombre() + "'");
            }
        }
        return null;
    }

    // Tamanos constantes de un arreglo de estructura 
    private static List<Integer> tamanos(List<Expression> dimensiones) {
        List<Integer> tamanos = new ArrayList<>();
        for (Expression dimension : dimensiones) {
            if (dimension instanceof Literal literal && literal.valor() instanceof Long tamano) {
                tamanos.add(tamano.intValue());
            }
        }
        return tamanos;
    }

    @Override
    public Void visitarAtributoEstructura(AtributoEstructura nodo) {
        return null;
    }

    @Override
    public Void visitarDefinicionFuncion(DefinicionFuncion nodo) {
        tablas.simbolos().entrarAmbito(nodo.nombre(), true, nodo);
        nodo.parametros().forEach(parametro -> parametro.accept(this));
        nodo.cuerpo().forEach(sentencia -> sentencia.accept(this));
        int tamanoMarco = tablas.simbolos().getActual().getTamanoMarco();
        tablas.simbolos().salirAmbito();

        Tipo retorno = nodo.tipoRetorno() != null ? nodo.tipoRetorno() : Tipo.primitivo(TipoPrimitivo.VOID);
        verificarTipo(retorno, nodo.posicion());
        List<Tipo> parametros = new ArrayList<>();
        nodo.parametros().forEach(parametro -> parametros.add(parametro.tipo()));
        Simbolo funcion = new Simbolo(nodo.nombre(), CategoriaSimbolo.FUNCION, retorno, parametros,
                tablas.simbolos().getActual().getNombre(), errores.getArchivo(), nodo.posicion(), -1, tamanoMarco);
        if (!tablas.simbolos().declararFuncion(funcion)) {
            error(nodo.posicion(), nodo.nombre(), "Ya existe una función llamada '" + nodo.nombre() + "'");
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
    public Void visitarDeclaracion(Declaracion nodo) {
        declararVariable(nodo.nombre(), CategoriaSimbolo.VARIABLE, nodo.tipo(), nodo.posicion());
        return null;
    }

    @Override
    public Void visitarAsignacion(Asignacion nodo) {
        return null;
    }

    @Override
    public Void visitarIncrementoDecremento(IncrementoDecremento nodo) {
        return null;
    }

    @Override
    public Void visitarSentenciaExpresion(SentenciaExpresion nodo) {
        return null;
    }

    @Override
    public Void visitarImprimir(Imprimir nodo) {
        return null;
    }

    @Override
    public Void visitarRomper(Romper nodo) {
        return null;
    }

    @Override
    public Void visitarContinuar(Continuar nodo) {
        return null;
    }

    @Override
    public Void visitarRetornar(Retornar nodo) {
        return null;
    }

    @Override
    public Void visitarCondicional(Condicional nodo) {
        nodo.ramas().forEach(rama -> rama.accept(this));
        if (nodo.contrario() != null) {
            visitarBloque("contrario", nodo.contrario(), nodo);
        }
        return null;
    }

    @Override
    public Void visitarRamaCondicional(RamaCondicional nodo) {
        visitarBloque("si", nodo.cuerpo(), nodo);
        return null;
    }

    @Override
    public Void visitarElegir(Elegir nodo) {
        nodo.casos().forEach(caso -> caso.accept(this));
        if (nodo.siempre() != null) {
            visitarBloque("siempre", nodo.siempre(), nodo);
        }
        return null;
    }

    @Override
    public Void visitarCaso(Caso nodo) {
        visitarBloque("caso", nodo.cuerpo(), nodo);
        return null;
    }

    @Override
    public Void visitarCicloPara(CicloPara nodo) {
        // la variable declarada en el inicio solo existe dentro del ciclo
        tablas.simbolos().entrarAmbito(tablas.simbolos().getActual().getNombre() + "/para", false, nodo);
        if (nodo.inicio() != null) {
            nodo.inicio().accept(this);
        }
        nodo.cuerpo().forEach(sentencia -> sentencia.accept(this));
        tablas.simbolos().salirAmbito();
        return null;
    }

    @Override
    public Void visitarCicloMientras(CicloMientras nodo) {
        visitarBloque("mientras", nodo.cuerpo(), nodo);
        return null;
    }

    @Override
    public Void visitarCicloHacer(CicloHacer nodo) {
        visitarBloque("hacer", nodo.cuerpo(), nodo);
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
    public Void visitarAcceso(Acceso nodo) {
        return null;
    }

    @Override
    public Void visitarLlamadaFuncion(LlamadaFuncion nodo) {
        return null;
    }

    @Override
    public Void visitarLeer(Leer nodo) {
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
    public Void visitarSufijoIndice(SufijoIndice nodo) {
        return null;
    }

    @Override
    public Void visitarSufijoAtributo(SufijoAtributo nodo) {
        return null;
    }
}
