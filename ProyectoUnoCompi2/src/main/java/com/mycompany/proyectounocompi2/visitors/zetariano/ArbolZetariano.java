package com.mycompany.proyectounocompi2.visitors.zetariano;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.zetariano.*;
import com.mycompany.proyectounocompi2.visitors.NodoArbol;
import java.util.ArrayList;
import java.util.List;

// Arbol simplificado de un .z para dibujarlo
public class ArbolZetariano implements Visitor<NodoArbol> {

    private static final Lenguaje LENGUAJE = Lenguaje.ZETARIANO;

    private List<NodoArbol> todos(List<? extends AstNode> nodos) {
        List<NodoArbol> hijos = new ArrayList<>();
        nodos.forEach(nodo -> hijos.add(nodo.accept(this)));
        return hijos;
    }

    private NodoArbol opcional(AstNode nodo) {
        return nodo != null ? nodo.accept(this) : null;
    }

    private NodoArbol parametros(List<Parametro> parametros) {
        return parametros.isEmpty() ? null : NodoArbol.de("parámetros", todos(parametros));
    }

    private NodoArbol cuerpo(Bloque bloque) {
        return NodoArbol.de("cuerpo", todos(bloque.sentencias()));
    }

    private static String modificadores(List<String> modificadores) {
        return modificadores.isEmpty() ? "" : String.join(" ", modificadores) + " ";
    }

    // ---------- Clase ----------

    @Override
    public NodoArbol visitarClase(Clase nodo) {
        List<NodoArbol> miembros = todos(nodo.atributos());
        miembros.addAll(todos(nodo.constructores()));
        miembros.addAll(todos(nodo.metodos()));
        return NodoArbol.de(modificadores(nodo.modificadores()) + "class " + nodo.nombre(), miembros);
    }

    @Override
    public NodoArbol visitarAtributo(Atributo nodo) {
        return NodoArbol.de(modificadores(nodo.modificadores()) + LENGUAJE.nombreDe(nodo.tipo()),
                todos(nodo.declaradores()));
    }

    // x  o  x = valor
    @Override
    public NodoArbol visitarDeclarador(Declarador nodo) {
        NodoArbol nombre = NodoArbol.de(nodo.nombre());
        return nodo.valor() != null ? NodoArbol.de("=", nombre, nodo.valor().accept(this)) : nombre;
    }

    @Override
    public NodoArbol visitarConstructor(Constructor nodo) {
        return NodoArbol.de(modificadores(nodo.modificadores()) + nodo.nombre() + "()", parametros(nodo.parametros()),
                cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarMetodo(Metodo nodo) {
        return NodoArbol.de(modificadores(nodo.modificadores()) + LENGUAJE.nombreDe(nodo.tipoRetorno()) + " "
                + nodo.nombre() + "()", parametros(nodo.parametros()), cuerpo(nodo.cuerpo()));
    }

    @Override
    public NodoArbol visitarParametro(Parametro nodo) {
        return NodoArbol.de(LENGUAJE.nombreDe(nodo.tipo()) + " " + nodo.nombre());
    }

    // ---------- Sentencias ----------

    @Override
    public NodoArbol visitarBloque(Bloque nodo) {
        return NodoArbol.de("{ }", todos(nodo.sentencias()));
    }

    @Override
    public NodoArbol visitarDeclaracionLocal(DeclaracionLocal nodo) {
        return NodoArbol.de(LENGUAJE.nombreDe(nodo.tipo()), todos(nodo.declaradores()));
    }

    @Override
    public NodoArbol visitarIf(If nodo) {
        return NodoArbol.de("if", nodo.condicion().accept(this), nodo.entonces().accept(this),
                nodo.sino() != null ? NodoArbol.de("else", nodo.sino().accept(this)) : null);
    }

    @Override
    public NodoArbol visitarSwitch(Switch nodo) {
        List<NodoArbol> hijos = new ArrayList<>(List.of(nodo.valor().accept(this)));
        hijos.addAll(todos(nodo.secciones()));
        return new NodoArbol("switch", hijos);
    }

    @Override
    public NodoArbol visitarSeccionSwitch(SeccionSwitch nodo) {
        List<NodoArbol> hijos = todos(nodo.casos());
        hijos.add(NodoArbol.de("cuerpo", todos(nodo.sentencias())));
        return new NodoArbol(nodo.esDefault() && nodo.casos().isEmpty() ? "default" : "case", hijos);
    }

    @Override
    public NodoArbol visitarFor(For nodo) {
        List<NodoArbol> hijos = new ArrayList<>();
        if (nodo.declaracion() != null) {
            hijos.add(nodo.declaracion().accept(this));
        }
        hijos.addAll(todos(nodo.inicio()));
        if (nodo.condicion() != null) {
            hijos.add(nodo.condicion().accept(this));
        }
        hijos.addAll(todos(nodo.actualizacion()));
        hijos.add(nodo.cuerpo().accept(this));
        return new NodoArbol("for", hijos);
    }

    @Override
    public NodoArbol visitarWhile(While nodo) {
        return NodoArbol.de("while", nodo.condicion().accept(this), nodo.cuerpo().accept(this));
    }

    @Override
    public NodoArbol visitarDoWhile(DoWhile nodo) {
        return NodoArbol.de("do while", nodo.cuerpo().accept(this), nodo.condicion().accept(this));
    }

    @Override
    public NodoArbol visitarBreak(Break nodo) {
        return NodoArbol.de("break");
    }

    @Override
    public NodoArbol visitarContinue(Continue nodo) {
        return NodoArbol.de("continue");
    }

    @Override
    public NodoArbol visitarReturn(Return nodo) {
        return NodoArbol.de("return", opcional(nodo.valor()));
    }

    @Override
    public NodoArbol visitarPrintln(Println nodo) {
        return NodoArbol.de("println", opcional(nodo.valor()));
    }

    @Override
    public NodoArbol visitarPrint(Print nodo) {
        return NodoArbol.de("print", opcional(nodo.valor()));
    }

    @Override
    public NodoArbol visitarSentenciaExpresion(SentenciaExpresion nodo) {
        return nodo.expresion().accept(this);
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
    public NodoArbol visitarThis(This nodo) {
        return NodoArbol.de("this");
    }

    @Override
    public NodoArbol visitarIdentificador(Identificador nodo) {
        return NodoArbol.de(nodo.nombre());
    }

    @Override
    public NodoArbol visitarLlamadaMetodo(LlamadaMetodo nodo) {
        NodoArbol metodo = NodoArbol.de(nodo.nombre());
        if (nodo.objeto() != null) {
            metodo = NodoArbol.de(".", nodo.objeto().accept(this), metodo);
        }
        return NodoArbol.llamada(metodo, todos(nodo.argumentos()));
    }

    @Override
    public NodoArbol visitarAccesoAtributo(AccesoAtributo nodo) {
        return NodoArbol.de(".", nodo.objeto().accept(this), NodoArbol.de(nodo.nombre()));
    }

    @Override
    public NodoArbol visitarAccesoIndice(AccesoIndice nodo) {
        return NodoArbol.de("[ ]", nodo.arreglo().accept(this), nodo.indice().accept(this));
    }

    @Override
    public NodoArbol visitarOperacionPostfija(OperacionPostfija nodo) {
        return NodoArbol.de(nodo.incremento() ? "++" : "--", nodo.operando().accept(this));
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
    public NodoArbol visitarTernaria(Ternaria nodo) {
        return NodoArbol.de("? :", nodo.condicion().accept(this), nodo.siVerdadero().accept(this),
                nodo.siFalso().accept(this));
    }

    @Override
    public NodoArbol visitarAsignacionExpresion(AsignacionExpresion nodo) {
        return NodoArbol.de(nodo.operador().getSimbolo(), nodo.destino().accept(this), nodo.valor().accept(this));
    }

    @Override
    public NodoArbol visitarNuevoObjeto(NuevoObjeto nodo) {
        return NodoArbol.de("new " + nodo.clase(), todos(nodo.argumentos()));
    }

    @Override
    public NodoArbol visitarNuevoArreglo(NuevoArreglo nodo) {
        return NodoArbol.de("new " + LENGUAJE.nombreDe(nodo.tipo()), todos(nodo.tamanos()), opcional(nodo.valores()));
    }

    @Override
    public NodoArbol visitarReadln(Readln nodo) {
        return NodoArbol.de("readln()");
    }
}
