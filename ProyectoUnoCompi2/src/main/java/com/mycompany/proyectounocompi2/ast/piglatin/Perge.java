package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// perge;  
public record Perge(Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarPerge(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarCiclo(posicion, "perge");
        return null;
    }
}
