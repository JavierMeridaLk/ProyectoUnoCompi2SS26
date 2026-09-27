package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// this
public record This(Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarThis(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return Tipo.clase(contexto.getClase().getNombre());
    }
}
