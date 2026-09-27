package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// -x   +x   !x   ++x   --x
public record OperacionUnaria(
        OperadorUnario operador,
        Expression operando,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarOperacionUnaria(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return contexto.operacionUnaria(operador, operando.analizar(contexto), posicion);
    }
}
