package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// readln()
public record Readln(Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarReadln(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        // readln() se adapta al tipo de donde se guarde
        return null;
    }
}
