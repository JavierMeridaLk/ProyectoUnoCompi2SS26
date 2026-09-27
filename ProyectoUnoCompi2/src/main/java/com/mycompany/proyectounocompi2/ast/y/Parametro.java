package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// entero a  (valor)   [] entero arr  y  {} Persona p  (referencia)
public record Parametro(
        Tipo tipo,
        String nombre,
        boolean porReferencia,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarParametro(this);
    }
}
