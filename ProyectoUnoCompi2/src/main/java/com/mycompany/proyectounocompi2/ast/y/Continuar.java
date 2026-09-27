package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// continuar
public record Continuar(Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarContinuar(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarCiclo(posicion, "continuar");
        return null;
    }
}
