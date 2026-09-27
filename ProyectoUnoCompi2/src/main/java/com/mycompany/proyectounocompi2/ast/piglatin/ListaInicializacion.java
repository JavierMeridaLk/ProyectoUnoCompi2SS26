package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// arreglos y estructuras
public record ListaInicializacion(List<Expression> elementos, Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarListaInicializacion(this);
    }

    // Valida el valor inicial de una declaracion o asignacion
    public static void verificarValor(Tipo destino, Expression valor, ContextoSemantico contexto) {
        contexto.verificarInicializacion(destino, valor, valor.posicion(),
                e -> e instanceof ListaInicializacion lista ? lista.elementos() : null,
                e -> e.analizar(contexto));
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.error(posicion, "{", "Una lista { } solo se puede usar para inicializar un arreglo o estructura");
        return null;
    }
}
