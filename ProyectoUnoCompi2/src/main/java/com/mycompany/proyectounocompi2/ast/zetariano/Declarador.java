package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;

// a = 1  dentro de una declaracion  
public record Declarador(String nombre, Expression valor, Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarDeclarador(this);
    }
}
