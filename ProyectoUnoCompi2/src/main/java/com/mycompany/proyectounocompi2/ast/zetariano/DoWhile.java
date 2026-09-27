package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// do .. while 
public record DoWhile(Statement cuerpo, Expression condicion, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarDoWhile(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.enCiclo(() -> cuerpo.analizar(contexto));
        contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
        return null;
    }
}
