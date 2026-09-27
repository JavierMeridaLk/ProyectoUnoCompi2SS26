package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// retornar x  
public record Retornar(Expression valor, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarRetornar(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Tipo tipo = valor != null ? valor.analizar(contexto) : null;
        contexto.verificarRetorno(tipo, valor != null, posicion);
        return null;
    }
}
