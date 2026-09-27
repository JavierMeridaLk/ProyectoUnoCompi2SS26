package com.mycompany.proyectounocompi2.ast.comun;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

// Linea y columna donde empieza un nodo en el codigo fuente
public record Posicion(int linea, int columna) {

    public static Posicion de(ParserRuleContext contexto) {
        return de(contexto.getStart());
    }

    public static Posicion de(Token token) {
        return new Posicion(token.getLine(), token.getCharPositionInLine() + 1);
    }
}
