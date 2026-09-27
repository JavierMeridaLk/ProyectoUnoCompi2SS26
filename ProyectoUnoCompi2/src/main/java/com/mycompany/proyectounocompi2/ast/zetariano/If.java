package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// if else 
public record If(
        Expression condicion,
        Statement entonces,
        Statement sino,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarIf(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
        entonces.analizar(contexto);
        if (sino != null) {
            sino.analizar(contexto);
        }
        return null;
    }
}
