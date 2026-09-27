package com.mycompany.proyectounocompi2.visitors.piglatin;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.piglatin.*;
import com.mycompany.proyectounocompi2.cuartetas.Direccion;
import com.mycompany.proyectounocompi2.cuartetas.GeneradorBase;
import com.mycompany.proyectounocompi2.cuartetas.ListaCuartetas;
import com.mycompany.proyectounocompi2.cuartetas.Valor;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import java.util.ArrayList;
import java.util.List;

// Cuartetas del .pig
public class GeneradorPigLatin extends GeneradorBase implements Visitor<Valor> {

    public GeneradorPigLatin(Tablas tablas, ListaCuartetas codigo, String archivo) {
        super(tablas, codigo, Lenguaje.PIG_LATIN, archivo);
    }

    private void generar(List<Statement> sentencias) {
        sentencias.forEach(sentencia -> sentencia.accept(this));
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

    // Recorre un acceso de izquierda a derecha, devuelve la Direccion del ultimo elemento o su Valor 
    private Object recorrer(Valor valor, Direccion direccion, List<Sufijo> sufijos, int desde, boolean comoDireccion) {
        int i = desde;
        while (i < sufijos.size()) {
            Valor actual = direccion != null ? cargar(direccion) : valor;
            Sufijo sufijo = sufijos.get(i);
            if (sufijo instanceof SufijoIndice) {
                List<Valor> indices = new ArrayList<>();
                while (i < sufijos.size() && sufijos.get(i) instanceof SufijoIndice indice
                        && indices.size() < actual.tipo().dimensiones()) {
                    indices.add(indice.indice().accept(this));
                    i++;
                }
                direccion = direccionElemento(actual, indices, sufijo.posicion());
            } else if (sufijo instanceof SufijoAtributo atributo && i + 1 < sufijos.size()
                    && sufijos.get(i + 1) instanceof SufijoLlamada llamada) {
                valor = llamarMetodo(actual, atributo.nombre(), generarTodas(llamada.argumentos()));
                direccion = null;
                i += 2;
            } else {
                direccion = direccionCampo(actual, ((SufijoAtributo) sufijo).nombre());
                i++;
            }
        }
        if (comoDireccion) {
            return direccion;
        }
        return direccion != null ? cargar(direccion) : valor;
    }

    private Direccion direccion(Acceso acceso) {
        return (Direccion) recorrer(null, direccionVariable(acceso.nombre(), acceso.posicion()), acceso.sufijos(), 0,
                true);
    }

    // ---------- Programa ----------

    @Override
    public Valor visitarPrograma(Programa nodo) {
        inicioFuncion("main", tablas.simbolos().tamanoGlobal(), VOID);
        generar(nodo.variables());
        generar(nodo.principal());
        finPrograma();
        return null;
    }

    @Override
    public Valor visitarImportacion(Importacion nodo) {
        return null;
    }

    // ---------- Sentencias ----------

    @Override
    public Valor visitarDeclaracion(Declaracion nodo) {
        Valor valor = nodo.valor() != null ? inicializar(nodo.tipo(), nodo.valor(), List.of())
                : valorPorDefecto(nodo.tipo(), List.of());
        guardar(direccionVariable(nodo.nombre(), nodo.posicion()), valor);
        return null;
    }

    @Override
    public Valor visitarDeclaracionArreglo(DeclaracionArreglo nodo) {
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
        if (nodo.sino() != null) {
            dentroDe(nodo, () -> generar(nodo.sino()));
        }
        colocar(fin);
        return null;
    }

    @Override
    public Valor visitarRamaCondicional(RamaCondicional nodo) {
        return null;
    }

    @Override
    public Valor visitarCicloDum(CicloDum nodo) {
        String inicio = etiqueta();
        String cuerpo = etiqueta();
        String fin = etiqueta();
        colocar(inicio);
        condicion(nodo.condicion(), cuerpo, fin);
        colocar(cuerpo);
        dentroDe(nodo, () -> enCiclo(fin, inicio, () -> generar(nodo.cuerpo())));
        saltar(inicio);
        colocar(fin);
        return null;
    }

    @Override
    public Valor visitarCicloFacere(CicloFacere nodo) {
        String inicio = etiqueta();
        String evaluar = etiqueta();
        String fin = etiqueta();
        colocar(inicio);
        dentroDe(nodo, () -> enCiclo(fin, evaluar, () -> generar(nodo.cuerpo())));
        colocar(evaluar);
        condicion(nodo.condicion(), inicio, fin);
        colocar(fin);
        return null;
    }

    @Override
    public Valor visitarCicloPer(CicloPer nodo) {
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
    public Valor visitarPerge(Perge nodo) {
        continuar();
        return null;
    }

    @Override
    public Valor visitarInterrumpe(Interrumpe nodo) {
        romper();
        return null;
    }

    @Override
    public Valor visitarLectura(Lectura nodo) {
        Valor texto = leer();
        if (nodo.destino() != null) {
            guardar(direccion(nodo.destino()), texto);
        }
        return null;
    }

    // >> a >> b: imprime los valores seguidos, sin salto de linea 
    @Override
    public Valor visitarEscritura(Escritura nodo) {
        nodo.valores().forEach(valor -> imprimir(valor.accept(this)));
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
        List<Sufijo> sufijos = nodo.sufijos();
        if (!sufijos.isEmpty() && sufijos.get(0) instanceof SufijoLlamada llamada) {
            Valor resultado = llamarFuncion(nodo.nombre(), generarTodas(llamada.argumentos()));
            return (Valor) recorrer(resultado, null, sufijos, 1, false);
        }
        return (Valor) recorrer(null, direccionVariable(nodo.nombre(), nodo.posicion()), sufijos, 0, false);
    }

    @Override
    public Valor visitarNuevoObjeto(NuevoObjeto nodo) {
        Valor objeto = crearObjeto(nodo.clase(), generarTodas(nodo.argumentos()));
        return (Valor) recorrer(objeto, null, nodo.sufijos(), 0, false);
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

    // Los sufijos se generan en recorrer
    @Override
    public Valor visitarSufijoIndice(SufijoIndice nodo) {
        return null;
    }

    @Override
    public Valor visitarSufijoAtributo(SufijoAtributo nodo) {
        return null;
    }

    @Override
    public Valor visitarSufijoLlamada(SufijoLlamada nodo) {
        return null;
    }
}
