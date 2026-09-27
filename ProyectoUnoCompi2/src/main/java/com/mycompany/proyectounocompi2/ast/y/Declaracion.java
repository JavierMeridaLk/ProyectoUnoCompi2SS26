package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// entero x = 5   entero m[2][2]
public record Declaracion(
        Tipo tipo,
        String nombre,
        List<Expression> dimensiones,
        Expression valor,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarDeclaracion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        for (Expression dimension : dimensiones) {
            contexto.verificarEntero(dimension.analizar(contexto), dimension.posicion(), "El tamaño del arreglo");
        }
        if (valor != null) {
            ListaInicializacion.verificarValor(contexto.conocido(tipo), valor, contexto);
        }
        return null;
    }
}
