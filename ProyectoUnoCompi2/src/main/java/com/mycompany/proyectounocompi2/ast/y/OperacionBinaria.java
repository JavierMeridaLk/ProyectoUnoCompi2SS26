package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// a + b   a % b   a && b
public record OperacionBinaria(
        Expression izquierda,
        OperadorBinario operador,
        Expression derecha,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarOperacionBinaria(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return contexto.operacionBinaria(operador, izquierda.analizar(contexto), derecha.analizar(contexto), posicion);
    }
}
