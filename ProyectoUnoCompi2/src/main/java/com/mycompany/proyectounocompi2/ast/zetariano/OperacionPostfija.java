package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// x++   x--
public record OperacionPostfija(
        Expression operando,
        boolean incremento,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarOperacionPostfija(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Tipo tipo = operando.analizar(contexto);
        contexto.verificarNumerico(tipo, posicion, incremento ? "++" : "--");
        return tipo;
    }
}
