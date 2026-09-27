package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// esto x : numerus 10
public record Declaracion(
        String nombre,
        Tipo tipo,
        Expression valor,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarDeclaracion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        if (valor != null) {
            ListaInicializacion.verificarValor(contexto.conocido(tipo), valor, contexto);
        }
        return null;
    }
}
