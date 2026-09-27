package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

public record LlamadaMetodo(
        Expression objeto,
        String nombre,
        List<Expression> argumentos,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarLlamadaMetodo(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        List<Tipo> tipos = Expression.analizarTodas(argumentos, contexto);
        if (objeto != null) {
            return contexto.llamarMetodo(objeto.analizar(contexto), nombre, tipos, posicion);
        }
        // metodo de la misma clase, si no tiene uno con ese nombre, una funcion de un .y
        String clase = contexto.getClase().getNombre();
        if (contexto.getTablas().simbolos().buscarMetodos(clase, nombre).isEmpty()
                && contexto.getTablas().simbolos().buscarFuncion(nombre) != null) {
            return contexto.llamarFuncion(nombre, tipos, posicion);
        }
        return contexto.llamarMetodo(Tipo.clase(clase), nombre, tipos, posicion);
    }
}
