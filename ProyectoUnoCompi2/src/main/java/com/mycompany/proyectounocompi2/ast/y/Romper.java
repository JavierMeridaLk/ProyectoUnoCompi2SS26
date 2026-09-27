package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// romper
public record Romper(Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarRomper(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarRomper(posicion, "romper");
        return null;
    }
}
