package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// x++;  x--;
public record IncrementoDecremento(
        Acceso destino,
        boolean incremento,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarIncrementoDecremento(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarNumerico(destino.analizar(contexto), posicion, incremento ? "++" : "--");
        return null;
    }
}
