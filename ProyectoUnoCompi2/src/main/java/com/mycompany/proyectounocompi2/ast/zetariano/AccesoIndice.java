package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// arr[i]
public record AccesoIndice(Expression arreglo, Expression indice, Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAccesoIndice(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return contexto.tipoIndice(arreglo.analizar(contexto), indice.analizar(contexto), posicion);
    }
}
