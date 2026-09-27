package com.mycompany.proyectounocompi2.visitors.piglatin;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.piglatin.*;
import com.mycompany.proyectounocompi2.visitors.NodoArbol;
import java.util.ArrayList;
import java.util.List;

// Arbol simplificado de un .pig para dibujarlo
public class ArbolPigLatin implements Visitor<NodoArbol> {

    private static final Lenguaje LENGUAJE = Lenguaje.PIG_LATIN;

    private List<NodoArbol> todos(List<? extends AstNode> nodos) {
        List<NodoArbol> hijos = new ArrayList<>();
        nodos.forEach(nodo -> hijos.add(nodo.accept(this)));
        return hijos;
    }

    private NodoArbol cuerpo(List<Statement> sentencias) {
        return NodoArbol.de("cuerpo", todos(sentencias));
    }

    private NodoArbol opcional(AstNode nodo) {
        return nodo != null ? nodo.accept(this) : null;
    }

    // x[i].atributo.metodo(args): cada sufijo envuelve lo anterior
    private NodoArbol conSufijos(NodoArbol base, List<Sufijo> sufijos) {
        for (Sufijo sufijo : sufijos) {
            if (sufijo instanceof SufijoIndice indice) {
                base = NodoArbol.de("[ ]", base, indice.indice().accept(this));
            } else if (sufijo instanceof SufijoAtributo atributo) {
                base = NodoArbol.de(".", base, NodoArbol.de(atributo.nombre()));
            } else if (sufijo instanceof SufijoLlamada llamada) {
                base = NodoArbol.llamada(base, todos(llamada.argumentos()));
            }
        }
        return base;
    }

    // ---------- Programa ----------

    @Override
    public NodoArbol visitarPrograma(Programa nodo) {
        return NodoArbol.de("programa",
                nodo.importaciones().isEmpty() ? null : NodoArbol.de("import", todos(nodo.importaciones())),
                nodo.variables().isEmpty() ? null : NodoArbol.de("VARIABILES>", todos(nodo.variables())),
                NodoArbol.de("MAIOR>", todos(nodo.principal())));
    }

    @Override
    public NodoArbol visitarImportacion(Importacion nodo) {
        return NodoArbol.de(String.join(".", nodo.ruta()));
    }

    // ---------- Sentencias ----------

    @Override
    public NodoArbol visitarDeclaracion(Declaracion nodo) {
        return NodoArbol.de("esto", NodoArbol.de(nodo.nombre()), NodoArbol.de(LENGUAJE.nombreDe(nodo.tipo())),
                opcional(nodo.valor()));
    }

    @Override
    public NodoArbol visitarDeclaracionArreglo(DeclaracionArreglo nodo) {
        return NodoArbol.de("series", NodoArbol.de(nodo.nombre()), NodoArbol.de("tamaño", todos(nodo.dimensiones())),
                NodoArbol.de(LENGUAJE.nombreDe(nodo.tipo())), opcional(nodo.valor()));
    }

    @Override
    public NodoArbol visitarAsignacion(Asignacion nodo) {
        return NodoArbol.de("=", nodo.destino().accept(this), nodo.valor().accept(this));
    }

    @Override
    public NodoArbol visitarIncrementoDecremento(IncrementoDecremento nodo) {
        return NodoArbol.de(nodo.incremento() ? "++" : "--", nodo.destino().accept(this));
    }

    @Override
    public NodoArbol visitarSentenciaExpresion(SentenciaExpresion nodo) {
        return nodo.expresion().accept(this);
    }

    @Override
    public NodoArbol visitarCondicional(Condicional nodo) {
        List<NodoArbol> ramas = new ArrayList<>();
        for (RamaCondicional rama : nodo.ramas()) {
            NodoArbol si = rama.accept(this);
            ramas.add(ramas.isEmpty() ? si : new NodoArbol("aliter si", si.hijos()));
        }
        return NodoArbol.de("condicional", ramas, nodo.sino() != null ? NodoArbol.de("aliter", todos(nodo.sino())) : null);
    }

    @Override
    public NodoArbol visitarRamaCondicional(RamaCondicional nodo) {
        return NodoArbol.de("si", nodo.condicion().accept(this), cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarCicloDum(CicloDum nodo) {
        return NodoArbol.de("dum", nodo.condicion().accept(this), cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarCicloFacere(CicloFacere nodo) {
        return NodoArbol.de("facere", cuerpo(nodo.cuerpo()), nodo.condicion().accept(this));
    }

    @Override
    public NodoArbol visitarCicloPer(CicloPer nodo) {
        return NodoArbol.de("per", opcional(nodo.inicio()), opcional(nodo.condicion()), opcional(nodo.actualizacion()),
                cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarPerge(Perge nodo) {
        return NodoArbol.de("perge");
    }

    @Override
    public NodoArbol visitarInterrumpe(Interrumpe nodo) {
        return NodoArbol.de("interrumpe");
    }

    @Override
    public NodoArbol visitarLectura(Lectura nodo) {
        return NodoArbol.de("<<", opcional(nodo.destino()));
    }

    @Override
    public NodoArbol visitarEscritura(Escritura nodo) {
        return NodoArbol.de(">>", todos(nodo.valores()));
    }

    // ---------- Expresiones ----------

    @Override
    public NodoArbol visitarLiteral(Literal nodo) {
        return NodoArbol.literal(nodo.tipo(), nodo.valor(), LENGUAJE);
    }

    @Override
    public NodoArbol visitarListaInicializacion(ListaInicializacion nodo) {
        return NodoArbol.de("{ }", todos(nodo.elementos()));
    }

    @Override
    public NodoArbol visitarAcceso(Acceso nodo) {
        return conSufijos(NodoArbol.de(nodo.nombre()), nodo.sufijos());
    }

    @Override
    public NodoArbol visitarNuevoObjeto(NuevoObjeto nodo) {
        return conSufijos(NodoArbol.de("novus " + nodo.clase(), todos(nodo.argumentos())), nodo.sufijos());
    }

    @Override
    public NodoArbol visitarOperacionUnaria(OperacionUnaria nodo) {
        return NodoArbol.de(nodo.operador().getSimbolo(), nodo.operando().accept(this));
    }

    @Override
    public NodoArbol visitarOperacionBinaria(OperacionBinaria nodo) {
        return NodoArbol.de(nodo.operador().getSimbolo(), nodo.izquierda().accept(this), nodo.derecha().accept(this));
    }

    // Los sufijos se dibujan en conSufijos
    @Override
    public NodoArbol visitarSufijoIndice(SufijoIndice nodo) {
        return nodo.indice().accept(this);
    }

    @Override
    public NodoArbol visitarSufijoAtributo(SufijoAtributo nodo) {
        return NodoArbol.de(nodo.nombre());
    }

    @Override
    public NodoArbol visitarSufijoLlamada(SufijoLlamada nodo) {
        return NodoArbol.de("llamada", todos(nodo.argumentos()));
    }
}
