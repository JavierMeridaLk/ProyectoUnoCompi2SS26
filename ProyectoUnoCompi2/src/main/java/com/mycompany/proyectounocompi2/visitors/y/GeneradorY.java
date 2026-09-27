package com.mycompany.proyectounocompi2.visitors.y;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.y.*;
import com.mycompany.proyectounocompi2.cuartetas.Direccion;
import com.mycompany.proyectounocompi2.cuartetas.GeneradorBase;
import com.mycompany.proyectounocompi2.cuartetas.ListaCuartetas;
import com.mycompany.proyectounocompi2.cuartetas.Valor;
import com.mycompany.proyectounocompi2.tablas.Simbolo;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import java.util.ArrayList;
import java.util.List;

// Cuartetas de un .y
public class GeneradorY extends GeneradorBase implements Visitor<Valor> {

    public GeneradorY(Tablas tablas, ListaCuartetas codigo, String archivo) {
        super(tablas, codigo, Lenguaje.Y, archivo);
    }

    private void generar(List<Statement> sentencias) {
        sentencias.forEach(sentencia -> sentencia.accept(this));
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

    private Direccion direccion(Acceso acceso) {
        Direccion direccion = direccionVariable(acceso.nombre(), acceso.posicion());
        List<Sufijo> sufijos = acceso.sufijos();
        int i = 0;
        while (i < sufijos.size()) {
            Valor actual = cargar(direccion);
            if (sufijos.get(i) instanceof SufijoAtributo atributo) {
                direccion = direccionCampo(actual, atributo.nombre());
                i++;
                continue;
            }
            // un indice por dimension: a[i][j]
            List<Valor> indices = new ArrayList<>();
            Sufijo primero = sufijos.get(i);
            while (i < sufijos.size() && sufijos.get(i) instanceof SufijoIndice indice
                    && indices.size() < actual.tipo().dimensiones()) {
                indices.add(indice.indice().accept(this));
                i++;
            }
            direccion = direccionElemento(actual, indices, primero.posicion());
        }
        return direccion;
    }

    // Salta a 'verdadero' si la condicion se cumple y si no a 'falso'.
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
    private void ciclo(AstNode nodo, Expression expresion, List<Statement> cuerpo, boolean condicionAlFinal) {
        String inicio = etiqueta();
        String instrucciones = etiqueta();
        String fin = etiqueta();
        String evaluar = condicionAlFinal ? etiqueta() : inicio;
        colocar(inicio);
        if (!condicionAlFinal) {
            condicion(expresion, instrucciones, fin);
            colocar(instrucciones);
        }
        dentroDe(nodo, () -> enCiclo(fin, evaluar, () -> generar(cuerpo)));
        if (condicionAlFinal) {
            colocar(evaluar);
            condicion(expresion, inicio, fin);
        } else {
            saltar(inicio);
        }
        colocar(fin);
    }

    // ---------- Programa ----------

    @Override
    public Valor visitarPrograma(Programa nodo) {
        nodo.funciones().forEach(funcion -> funcion.accept(this));
        return null;
    }

    @Override
    public Valor visitarDefinicionEstructura(DefinicionEstructura nodo) {
        return null;
    }

    @Override
    public Valor visitarAtributoEstructura(AtributoEstructura nodo) {
        return null;
    }

    @Override
    public Valor visitarDefinicionFuncion(DefinicionFuncion nodo) {
        Simbolo funcion = tablas.simbolos().buscarFuncion(nodo.nombre());
        inicioFuncion(nombreC(funcion), funcion.tamano(), funcion.tipo());
        dentroDe(nodo, () -> generar(nodo.cuerpo()));
        finFuncion();
        return null;
    }

    @Override
    public Valor visitarParametro(Parametro nodo) {
        return null;
    }

    // ---------- Sentencias ----------

    @Override
    public Valor visitarDeclaracion(Declaracion nodo) {
        List<Valor> tamanos = generarTodas(nodo.dimensiones());
        Valor valor = nodo.valor() != null ? inicializar(nodo.tipo(), nodo.valor(), tamanos)
                : valorPorDefecto(nodo.tipo(), tamanos);
        guardar(direccionVariable(nodo.nombre(), nodo.posicion()), valor);
        return null;
    }

    @Override
    public Valor visitarAsignacion(Asignacion nodo) {
        Direccion destino = direccion(nodo.destino());
        guardar(destino, inicializar(destino.tipo(), nodo.valor(), List.of()));
        return null;
    }

    @Override
    public Valor visitarIncrementoDecremento(IncrementoDecremento nodo) {
        incrementar(direccion(nodo.destino()), nodo.incremento(), false);
        return null;
    }

    @Override
    public Valor visitarSentenciaExpresion(SentenciaExpresion nodo) {
        nodo.expresion().accept(this);
        return null;
    }

    // imprimir(a, b): los valores seguidos y un salto de linea
    @Override
    public Valor visitarImprimir(Imprimir nodo) {
        nodo.valores().forEach(valor -> imprimir(valor.accept(this)));
        saltoDeLinea();
        return null;
    }

    @Override
    public Valor visitarRomper(Romper nodo) {
        romper();
        return null;
    }

    @Override
    public Valor visitarContinuar(Continuar nodo) {
        continuar();
        return null;
    }

    @Override
    public Valor visitarRetornar(Retornar nodo) {
        retornar(nodo.valor() != null ? nodo.valor().accept(this) : null);
        return null;
    }

    @Override
    public Valor visitarCondicional(Condicional nodo) {
        String fin = etiqueta();
        for (RamaCondicional rama : nodo.ramas()) {
            String verdadero = etiqueta();
            String siguiente = etiqueta();
            condicion(rama.condicion(), verdadero, siguiente);
            colocar(verdadero);
            dentroDe(rama, () -> generar(rama.cuerpo()));
            saltar(fin);
            colocar(siguiente);
        }
        if (nodo.contrario() != null) {
            dentroDe(nodo, () -> generar(nodo.contrario()));
        }
        colocar(fin);
        return null;
    }

    @Override
    public Valor visitarRamaCondicional(RamaCondicional nodo) {
        return null;
    }

    // Se compara el valor con cada caso y se salta al primero que coincide sin 'romper' sigue con el siguiente
    @Override
    public Valor visitarElegir(Elegir nodo) {
        Valor valor = nodo.valor().accept(this);
        String fin = etiqueta();
        String siempre = etiqueta();
        List<String> etiquetas = new ArrayList<>();
        for (Caso caso : nodo.casos()) {
            String etiqueta = etiqueta();
            etiquetas.add(etiqueta);
            comparar(OperadorBinario.IGUAL_QUE, valor, caso.valor().accept(this), etiqueta, null);
        }
        saltar(nodo.siempre() != null ? siempre : fin);
        enSeleccion(fin, () -> {
            for (int i = 0; i < nodo.casos().size(); i++) {
                Caso caso = nodo.casos().get(i);
                colocar(etiquetas.get(i));
                dentroDe(caso, () -> generar(caso.cuerpo()));
            }
            if (nodo.siempre() != null) {
                colocar(siempre);
                dentroDe(nodo, () -> generar(nodo.siempre()));
            }
        });
        colocar(fin);
        return null;
    }

    @Override
    public Valor visitarCaso(Caso nodo) {
        return null;
    }

    @Override
    public Valor visitarCicloPara(CicloPara nodo) {
        dentroDe(nodo, () -> {
            if (nodo.inicio() != null) {
                nodo.inicio().accept(this);
            }
            String evaluar = etiqueta();
            String cuerpo = etiqueta();
            String actualizacion = etiqueta();
            String fin = etiqueta();
            colocar(evaluar);
            if (nodo.condicion() != null) {
                condicion(nodo.condicion(), cuerpo, fin);
            }
            colocar(cuerpo);
            enCiclo(fin, actualizacion, () -> generar(nodo.cuerpo()));
            colocar(actualizacion);
            if (nodo.actualizacion() != null) {
                nodo.actualizacion().accept(this);
            }
            saltar(evaluar);
            colocar(fin);
        });
        return null;
    }

    @Override
    public Valor visitarCicloMientras(CicloMientras nodo) {
        ciclo(nodo, nodo.condicion(), nodo.cuerpo(), false);
        return null;
    }

    @Override
    public Valor visitarCicloHacer(CicloHacer nodo) {
        ciclo(nodo, nodo.condicion(), nodo.cuerpo(), true);
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
    public Valor visitarAcceso(Acceso nodo) {
        return cargar(direccion(nodo));
    }

    @Override
    public Valor visitarLlamadaFuncion(LlamadaFuncion nodo) {
        return llamarFuncion(nodo.nombre(), generarTodas(nodo.argumentos()));
    }

    @Override
    public Valor visitarLeer(Leer nodo) {
        return leer();
    }

    @Override
    public Valor visitarOperacionUnaria(OperacionUnaria nodo) {
        return operacionUnaria(nodo.operador(), nodo.operando().accept(this));
    }

    @Override
    public Valor visitarOperacionBinaria(OperacionBinaria nodo) {
        if (esLogico(nodo.operador())) {
            return valorDeCondicion((verdadero, falso) -> condicion(nodo, verdadero, falso));
        }
        return operacionBinaria(nodo.operador(), nodo.izquierda().accept(this), nodo.derecha().accept(this));
    }

    // Los sufijos se generan en direccion()
    @Override
    public Valor visitarSufijoIndice(SufijoIndice nodo) {
        return null;
    }

    @Override
    public Valor visitarSufijoAtributo(SufijoAtributo nodo) {
        return null;
    }
}
