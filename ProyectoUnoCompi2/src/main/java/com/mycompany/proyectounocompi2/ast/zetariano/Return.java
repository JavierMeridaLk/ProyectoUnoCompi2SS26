package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// return x
public record Return(Expression valor, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarReturn(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Tipo tipo = valor != null ? valor.analizar(contexto) : null;
        contexto.verificarRetorno(tipo, valor != null, posicion);
        return null;
    }
}
