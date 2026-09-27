package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// series m[2][3] : numerus {{..},{..}}; 
public record DeclaracionArreglo(
        String nombre,
        List<Expression> dimensiones,
        Tipo tipo,
        Expression valor,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarDeclaracionArreglo(this);
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
