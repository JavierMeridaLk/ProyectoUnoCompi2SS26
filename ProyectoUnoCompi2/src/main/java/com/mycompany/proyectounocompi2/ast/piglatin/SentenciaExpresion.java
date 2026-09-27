package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Una llamada usada como instruccion
public record SentenciaExpresion(Expression expresion, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarSentenciaExpresion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        expresion.analizar(contexto);
        return null;
    }
}
