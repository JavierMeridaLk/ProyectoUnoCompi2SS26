package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// interrumpe
public record Interrumpe(Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarInterrumpe(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarCiclo(posicion, "interrumpe");
        return null;
    }
}
