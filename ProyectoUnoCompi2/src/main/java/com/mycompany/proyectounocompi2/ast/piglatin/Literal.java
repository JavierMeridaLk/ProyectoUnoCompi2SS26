package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

public record Literal(TipoPrimitivo tipo, Object valor, Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarLiteral(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return Tipo.primitivo(tipo);
    }
}
