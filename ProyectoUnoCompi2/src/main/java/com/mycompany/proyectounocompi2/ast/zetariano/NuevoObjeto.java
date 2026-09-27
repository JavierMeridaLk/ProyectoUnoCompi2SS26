package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// new Persona
public record NuevoObjeto(
        String clase,
        List<Expression> argumentos,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarNuevoObjeto(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return contexto.crearObjeto(clase, Expression.analizarTodas(argumentos, contexto), posicion);
    }
}
