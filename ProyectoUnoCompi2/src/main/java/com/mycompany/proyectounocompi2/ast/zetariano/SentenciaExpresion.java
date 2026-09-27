package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// x = 1;  x++;  f();  new P();  readln();
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
