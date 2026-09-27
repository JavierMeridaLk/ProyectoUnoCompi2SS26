package com.mycompany.proyectounocompi2.visitors.y;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.y.*;
import com.mycompany.proyectounocompi2.visitors.NodoArbol;
import java.util.ArrayList;
import java.util.List;

// Arbol abstracto de un .y para dibujarlo
public class ArbolY implements Visitor<NodoArbol> {

    private static final Lenguaje LENGUAJE = Lenguaje.Y;

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

    private NodoArbol tamano(List<Expression> dimensiones) {
        return dimensiones.isEmpty() ? null : NodoArbol.de("tamaño", todos(dimensiones));
    }

    // ---------- Programa ----------

    @Override
    public NodoArbol visitarPrograma(Programa nodo) {
        return NodoArbol.de("programa",
                nodo.estructuras().isEmpty() ? null : NodoArbol.de("%estructuras", todos(nodo.estructuras())),
                NodoArbol.de("%funciones", todos(nodo.funciones())));
    }

    @Override
    public NodoArbol visitarDefinicionEstructura(DefinicionEstructura nodo) {
        return NodoArbol.de("estructura " + nodo.nombre(), todos(nodo.atributos()));
    }

    @Override
    public NodoArbol visitarAtributoEstructura(AtributoEstructura nodo) {
        return NodoArbol.de(LENGUAJE.nombreDe(nodo.tipo()) + " " + nodo.nombre(), tamano(nodo.dimensiones()));
    }

    @Override
    public NodoArbol visitarDefinicionFuncion(DefinicionFuncion nodo) {
        return NodoArbol.de("definir " + nodo.nombre(),
                nodo.parametros().isEmpty() ? null : NodoArbol.de("parámetros", todos(nodo.parametros())),
                nodo.tipoRetorno() != null ? NodoArbol.de("-> " + LENGUAJE.nombreDe(nodo.tipoRetorno())) : null,
                cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarParametro(Parametro nodo) {
        String prefijo = !nodo.porReferencia() ? "" : nodo.tipo().esPrimitivo() ? "[] " : "{} ";
        return NodoArbol.de(prefijo + LENGUAJE.nombreDe(nodo.tipo()) + " " + nodo.nombre());
    }

    // ---------- Sentencias ----------

    @Override
    public NodoArbol visitarDeclaracion(Declaracion nodo) {
        return NodoArbol.de("declaración", NodoArbol.de(nodo.nombre()), tamano(nodo.dimensiones()),
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
    public NodoArbol visitarImprimir(Imprimir nodo) {
        return NodoArbol.de("imprimir", todos(nodo.valores()));
    }

    @Override
    public NodoArbol visitarRomper(Romper nodo) {
        return NodoArbol.de("romper");
    }

    @Override
    public NodoArbol visitarContinuar(Continuar nodo) {
        return NodoArbol.de("continuar");
    }

    @Override
    public NodoArbol visitarRetornar(Retornar nodo) {
        return NodoArbol.de("retornar", opcional(nodo.valor()));
    }

    @Override
    public NodoArbol visitarCondicional(Condicional nodo) {
        List<NodoArbol> ramas = new ArrayList<>();
        for (RamaCondicional rama : nodo.ramas()) {
            NodoArbol si = rama.accept(this);
            ramas.add(ramas.isEmpty() ? si : new NodoArbol("sino", si.hijos()));
        }
        return NodoArbol.de("condicional", ramas,
                nodo.contrario() != null ? NodoArbol.de("contrario", todos(nodo.contrario())) : null);
    }

    @Override
    public NodoArbol visitarRamaCondicional(RamaCondicional nodo) {
        return NodoArbol.de("si", nodo.condicion().accept(this), cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarElegir(Elegir nodo) {
        List<NodoArbol> hijos = new ArrayList<>(List.of(nodo.valor().accept(this)));
        hijos.addAll(todos(nodo.casos()));
        return NodoArbol.de("elegir", hijos,
                nodo.siempre() != null ? NodoArbol.de("siempre", todos(nodo.siempre())) : null);
    }

    @Override
    public NodoArbol visitarCaso(Caso nodo) {
        return NodoArbol.de("caso", nodo.valor().accept(this), cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarCicloPara(CicloPara nodo) {
        return NodoArbol.de("para", opcional(nodo.inicio()), opcional(nodo.condicion()), opcional(nodo.actualizacion()),
                cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarCicloMientras(CicloMientras nodo) {
        return NodoArbol.de("mientras", nodo.condicion().accept(this), cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarCicloHacer(CicloHacer nodo) {
        return NodoArbol.de("hacer", cuerpo(nodo.cuerpo()), nodo.condicion().accept(this));
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
        NodoArbol base = NodoArbol.de(nodo.nombre());
        for (Sufijo sufijo : nodo.sufijos()) {
            base = sufijo instanceof SufijoIndice indice
                    ? NodoArbol.de("[ ]", base, indice.indice().accept(this))
                    : NodoArbol.de(".", base, sufijo.accept(this));
        }
        return base;
    }

    @Override
    public NodoArbol visitarLlamadaFuncion(LlamadaFuncion nodo) {
        return NodoArbol.llamada(NodoArbol.de(nodo.nombre()), todos(nodo.argumentos()));
    }

    @Override
    public NodoArbol visitarLeer(Leer nodo) {
        return NodoArbol.de("leer()");
    }

    @Override
    public NodoArbol visitarOperacionUnaria(OperacionUnaria nodo) {
        return NodoArbol.de(nodo.operador().getSimbolo(), nodo.operando().accept(this));
    }

    @Override
    public NodoArbol visitarOperacionBinaria(OperacionBinaria nodo) {
        return NodoArbol.de(nodo.operador().getSimbolo(), nodo.izquierda().accept(this), nodo.derecha().accept(this));
    }

    @Override
    public NodoArbol visitarSufijoIndice(SufijoIndice nodo) {
        return nodo.indice().accept(this);
    }

    @Override
    public NodoArbol visitarSufijoAtributo(SufijoAtributo nodo) {
        return NodoArbol.de(nodo.nombre());
    }
}
