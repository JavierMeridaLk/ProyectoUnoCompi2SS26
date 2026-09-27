package com.mycompany.proyectounocompi2.visitors.zetariano;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import com.mycompany.proyectounocompi2.ast.zetariano.*;
import com.mycompany.proyectounocompi2.cuartetas.Direccion;
import com.mycompany.proyectounocompi2.cuartetas.GeneradorBase;
import com.mycompany.proyectounocompi2.cuartetas.ListaCuartetas;
import com.mycompany.proyectounocompi2.cuartetas.Valor;
import com.mycompany.proyectounocompi2.tablas.Simbolo;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import java.util.ArrayList;
import java.util.List;

// Cuartetas de una clase .z
public class GeneradorZetariano extends GeneradorBase implements Visitor<Valor> {

    private String clase;

    public GeneradorZetariano(Tablas tablas, ListaCuartetas codigo, String archivo) {
        super(tablas, codigo, Lenguaje.ZETARIANO, archivo);
    }

    private List<Valor> generarTodas(List<Expression> expresiones) {
        List<Valor> valores = new ArrayList<>();
        expresiones.forEach(expresion -> valores.add(expresion.accept(this)));
        return valores;
    }

    private Valor inicializar(Tipo destino, Expression valor, List<Valor> tamanos) {
        return inicializar(destino, valor, tamanos,
                e -> e instanceof ListaInicializacion lista ? lista.elementos() : null,
                e -> e.accept(this));
    }

    // El simbolo que la tabla registro para este constructor/metodo 
    private static Simbolo simboloDe(List<Simbolo> sobrecargas, Posicion posicion) {
        return sobrecargas.stream().filter(simbolo -> simbolo.posicion().equals(posicion)).findFirst().orElseThrow();
    }

    private void generarMarco(AstNode nodo, Simbolo funcion, Bloque cuerpo, Tipo retorno) {
        inicioFuncion(nombreC(funcion), funcion.tamano(), retorno);
        dentroDe(nodo, () -> cuerpo.sentencias().forEach(sentencia -> sentencia.accept(this)));
        finFuncion();
    }

    private Direccion direccion(Expression expresion) {
        if (expresion instanceof Identificador identificador) {
            if (tablas.simbolos().buscarVariable(identificador.nombre(), identificador.posicion()) != null) {
                return direccionVariable(identificador.nombre(), identificador.posicion());
            }
            return direccionCampo(este(clase), identificador.nombre());
        }
        if (expresion instanceof AccesoAtributo acceso) {
            return direccionCampo(acceso.objeto().accept(this), acceso.nombre());
        }
        // a[i][j] llega como AccesoIndice(AccesoIndice(a, i), j)
        List<Expression> indices = new ArrayList<>();
        while (expresion instanceof AccesoIndice acceso) {
            indices.add(0, acceso.indice());
            expresion = acceso.arreglo();
        }
        Valor arreglo = expresion.accept(this);
        return direccionElemento(arreglo, generarTodas(indices), expresion.posicion());
    }

    // Las comparaciones van directo al if (if a > b goto L1); && y || en corto circuito.
    private void condicion(Expression expresion, String verdadero, String falso) {
        if (expresion instanceof OperacionBinaria binaria && esLogico(binaria.operador())) {
            String siguiente = etiqueta();
            if (binaria.operador() == OperadorBinario.Y_LOGICO) {
                condicion(binaria.izquierda(), siguiente, falso);
            } else {
                condicion(binaria.izquierda(), verdadero, siguiente);
            }
            colocar(siguiente);
            condicion(binaria.derecha(), verdadero, falso);
        } else if (expresion instanceof OperacionBinaria binaria && esRelacional(binaria.operador())) {
            comparar(binaria.operador(), binaria.izquierda().accept(this), binaria.derecha().accept(this),
                    verdadero, falso);
        } else if (expresion instanceof OperacionUnaria unaria && unaria.operador() == OperadorUnario.NEGACION) {
            condicion(unaria.operando(), falso, verdadero);
        } else {
            saltarSegun(expresion.accept(this), verdadero, falso);
        }
    }

    // Ciclo con la condicion al inicio o al final
    private void ciclo(Expression expresion, Statement cuerpo, boolean condicionAlFinal) {
        String inicio = etiqueta();
        String instrucciones = etiqueta();
        String fin = etiqueta();
        String evaluar = condicionAlFinal ? etiqueta() : inicio;
        colocar(inicio);
        if (!condicionAlFinal) {
            condicion(expresion, instrucciones, fin);
            colocar(instrucciones);
        }
        enCiclo(fin, evaluar, () -> cuerpo.accept(this));
        if (condicionAlFinal) {
            colocar(evaluar);
            condicion(expresion, inicio, fin);
        } else {
            saltar(inicio);
        }
        colocar(fin);
    }

    // ---------- Clase ----------

    @Override
    public Valor visitarClase(Clase nodo) {
        clase = nodo.nombre();
        inicioFuncion(nombreInicializador(clase), 1, VOID);
        dentroDe(nodo, () -> nodo.atributos().forEach(atributo -> atributo.accept(this)));
        finFuncion();
        nodo.constructores().forEach(constructor -> constructor.accept(this));
        nodo.metodos().forEach(metodo -> metodo.accept(this));
        return null;
    }

    @Override
    public Valor visitarAtributo(Atributo nodo) {
        for (Declarador declarador : nodo.declaradores()) {
            if (declarador.valor() != null) {
                Valor valor = inicializar(nodo.tipo(), declarador.valor(), List.of());
                guardar(direccionCampo(este(clase), declarador.nombre()), valor);
            }
        }
        return null;
    }

    @Override
    public Valor visitarDeclarador(Declarador nodo) {
        return null;
    }

    @Override
    public Valor visitarConstructor(Constructor nodo) {
        Simbolo constructor = simboloDe(tablas.simbolos().buscarConstructores(clase), nodo.posicion());
        generarMarco(nodo, constructor, nodo.cuerpo(), VOID);
        return null;
    }

    @Override
    public Valor visitarMetodo(Metodo nodo) {
        Simbolo metodo = simboloDe(tablas.simbolos().buscarMetodos(clase, nodo.nombre()), nodo.posicion());
        generarMarco(nodo, metodo, nodo.cuerpo(), nodo.tipoRetorno());
        return null;
    }

    @Override
    public Valor visitarParametro(Parametro nodo) {
        return null;
    }

    // ---------- Sentencias ----------

    @Override
    public Valor visitarBloque(Bloque nodo) {
        dentroDe(nodo, () -> nodo.sentencias().forEach(sentencia -> sentencia.accept(this)));
        return null;
    }

    @Override
    public Valor visitarDeclaracionLocal(DeclaracionLocal nodo) {
        for (Declarador declarador : nodo.declaradores()) {
            Valor valor = declarador.valor() != null ? inicializar(nodo.tipo(), declarador.valor(), List.of())
                    : valorPorDefecto(nodo.tipo(), List.of());
            guardar(direccionVariable(declarador.nombre(), declarador.posicion()), valor);
        }
        return null;
    }

    @Override
    public Valor visitarIf(If nodo) {
        String entonces = etiqueta();
        String sino = etiqueta();
        String fin = etiqueta();
        condicion(nodo.condicion(), entonces, sino);
        colocar(entonces);
        nodo.entonces().accept(this);
        saltar(fin);
        colocar(sino);
        if (nodo.sino() != null) {
            nodo.sino().accept(this);
        }
        colocar(fin);
        return null;
    }

    // Se compara el valor con cada case y se salta al primero que coincide
    @Override
    public Valor visitarSwitch(Switch nodo) {
        Valor valor = nodo.valor().accept(this);
        String fin = etiqueta();
        String porDefecto = fin;
        List<String> etiquetas = new ArrayList<>();
        for (SeccionSwitch seccion : nodo.secciones()) {
            String etiqueta = etiqueta();
            etiquetas.add(etiqueta);
            for (Expression caso : seccion.casos()) {
                comparar(OperadorBinario.IGUAL_QUE, valor, caso.accept(this), etiqueta, null);
            }
            if (seccion.esDefault()) {
                porDefecto = etiqueta;
            }
        }
        saltar(porDefecto);
        enSeleccion(fin, () -> {
            for (int i = 0; i < nodo.secciones().size(); i++) {
                SeccionSwitch seccion = nodo.secciones().get(i);
                colocar(etiquetas.get(i));
                dentroDe(seccion, () -> seccion.sentencias().forEach(sentencia -> sentencia.accept(this)));
            }
        });
        colocar(fin);
        return null;
    }

    @Override
    public Valor visitarSeccionSwitch(SeccionSwitch nodo) {
        return null;
    }

    @Override
    public Valor visitarFor(For nodo) {
        dentroDe(nodo, () -> {
            if (nodo.declaracion() != null) {
                nodo.declaracion().accept(this);
            }
            generarTodas(nodo.inicio());
            String evaluar = etiqueta();
            String cuerpo = etiqueta();
            String actualizacion = etiqueta();
            String fin = etiqueta();
            colocar(evaluar);
            if (nodo.condicion() != null) {
                condicion(nodo.condicion(), cuerpo, fin);
            }
            colocar(cuerpo);
            enCiclo(fin, actualizacion, () -> nodo.cuerpo().accept(this));
            colocar(actualizacion);
            generarTodas(nodo.actualizacion());
            saltar(evaluar);
            colocar(fin);
        });
        return null;
    }

    @Override
    public Valor visitarWhile(While nodo) {
        ciclo(nodo.condicion(), nodo.cuerpo(), false);
        return null;
    }

    @Override
    public Valor visitarDoWhile(DoWhile nodo) {
        ciclo(nodo.condicion(), nodo.cuerpo(), true);
        return null;
    }

    @Override
    public Valor visitarBreak(Break nodo) {
        romper();
        return null;
    }

    @Override
    public Valor visitarContinue(Continue nodo) {
        continuar();
        return null;
    }

    @Override
    public Valor visitarReturn(Return nodo) {
        retornar(nodo.valor() != null ? nodo.valor().accept(this) : null);
        return null;
    }

    @Override
    public Valor visitarPrintln(Println nodo) {
        if (nodo.valor() != null) {
            imprimir(nodo.valor().accept(this));
        }
        saltoDeLinea();
        return null;
    }

    @Override
    public Valor visitarPrint(Print nodo) {
        if (nodo.valor() != null) {
            imprimir(nodo.valor().accept(this));
        }
        return null;
    }

    @Override
    public Valor visitarSentenciaExpresion(SentenciaExpresion nodo) {
        nodo.expresion().accept(this);
        return null;
    }

    // ---------- Expresiones ----------

    @Override
    public Valor visitarLiteral(Literal nodo) {
        return literal(nodo.tipo(), nodo.valor());
    }

    @Override
    public Valor visitarListaInicializacion(ListaInicializacion nodo) {
        throw noSoportado(nodo.posicion(), "una lista { } solo se puede usar para inicializar");
    }

    @Override
    public Valor visitarThis(This nodo) {
        return este(clase);
    }

    @Override
    public Valor visitarIdentificador(Identificador nodo) {
        return cargar(direccion(nodo));
    }

    @Override
    public Valor visitarLlamadaMetodo(LlamadaMetodo nodo) {
        List<Valor> argumentos = generarTodas(nodo.argumentos());
        if (nodo.objeto() != null) {
            return llamarMetodo(nodo.objeto().accept(this), nodo.nombre(), argumentos);
        }
        if (tablas.simbolos().buscarMetodos(clase, nodo.nombre()).isEmpty()) {
            return llamarFuncion(nodo.nombre(), argumentos);
        }
        return llamarMetodo(este(clase), nodo.nombre(), argumentos);
    }

    @Override
    public Valor visitarAccesoAtributo(AccesoAtributo nodo) {
        return cargar(direccion(nodo));
    }

    @Override
    public Valor visitarAccesoIndice(AccesoIndice nodo) {
        return cargar(direccion(nodo));
    }

    @Override
    public Valor visitarOperacionPostfija(OperacionPostfija nodo) {
        return incrementar(direccion(nodo.operando()), nodo.incremento(), true);
    }

    @Override
    public Valor visitarOperacionUnaria(OperacionUnaria nodo) {
        if (nodo.operador() == OperadorUnario.PRE_INCREMENTO || nodo.operador() == OperadorUnario.PRE_DECREMENTO) {
            return incrementar(direccion(nodo.operando()), nodo.operador() == OperadorUnario.PRE_INCREMENTO, false);
        }
        return operacionUnaria(nodo.operador(), nodo.operando().accept(this));
    }

    @Override
    public Valor visitarOperacionBinaria(OperacionBinaria nodo) {
        if (esLogico(nodo.operador())) {
            return valorDeCondicion((verdadero, falso) -> condicion(nodo, verdadero, falso));
        }
        return operacionBinaria(nodo.operador(), nodo.izquierda().accept(this), nodo.derecha().accept(this));
    }

    @Override
    public Valor visitarTernaria(Ternaria nodo) {
        String resultado = temporal();
        String entonces = etiqueta();
        String sino = etiqueta();
        String fin = etiqueta();
        condicion(nodo.condicion(), entonces, sino);
        colocar(entonces);
        Valor verdadero = nodo.siVerdadero().accept(this);
        emitir("=", verdadero.direccion(), "", resultado);
        saltar(fin);
        colocar(sino);
        Valor falso = nodo.siFalso().accept(this);
        emitir("=", falso.direccion(), "", resultado);
        colocar(fin);
        boolean verdaderoEsNulo = Tipo.primitivo(TipoPrimitivo.NULO).equals(verdadero.tipo());
        return new Valor(resultado, verdaderoEsNulo ? falso.tipo() : verdadero.tipo());
    }

    @Override
    public Valor visitarAsignacionExpresion(AsignacionExpresion nodo) {
        Direccion destino = direccion(nodo.destino());
        Valor valor;
        if (nodo.operador() == OperadorAsignacion.ASIGNACION) {
            valor = inicializar(destino.tipo(), nodo.valor(), List.of());
        } else {
            // a += b  ->  a = a + b
            valor = operacionBinaria(nodo.operador().operacion(), cargar(destino), nodo.valor().accept(this));
        }
        guardar(destino, valor);
        return valor;
    }

    @Override
    public Valor visitarNuevoObjeto(NuevoObjeto nodo) {
        return crearObjeto(nodo.clase(), generarTodas(nodo.argumentos()));
    }

    @Override
    public Valor visitarNuevoArreglo(NuevoArreglo nodo) {
        List<Valor> tamanos = generarTodas(nodo.tamanos());
        if (nodo.valores() != null) {
            return inicializar(nodo.tipo(), nodo.valores(), tamanos);
        }
        if (tamanos.size() != nodo.tipo().dimensiones()) {
            throw noSoportado(nodo.posicion(), "se debe indicar el tamaño de cada dimensión del arreglo");
        }
        return nuevoArreglo(nodo.tipo(), tamanos);
    }

    @Override
    public Valor visitarReadln(Readln nodo) {
        return leer();
    }
}
