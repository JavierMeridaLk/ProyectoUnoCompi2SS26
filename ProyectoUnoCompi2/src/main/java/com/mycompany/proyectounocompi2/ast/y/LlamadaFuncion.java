package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// f(a, b)
public record LlamadaFuncion(
        String nombre,
        List<Expression> argumentos,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarLlamadaFuncion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return contexto.llamarFuncion(nombre, Expression.analizarTodas(argumentos, contexto), posicion);
    }
}
