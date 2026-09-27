package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// break;
public record Break(Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarBreak(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarRomper(posicion, "break");
        return null;
    }
}
