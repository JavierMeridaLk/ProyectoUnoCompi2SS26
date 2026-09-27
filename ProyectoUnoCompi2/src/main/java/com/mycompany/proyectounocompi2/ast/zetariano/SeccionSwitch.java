package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// case 1: case 2
public record SeccionSwitch(
        List<Expression> casos,
        boolean esDefault,
        List<Statement> sentencias,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarSeccionSwitch(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.dentroDe(this, () -> sentencias.forEach(sentencia -> sentencia.analizar(contexto)));
        return null;
    }
}
