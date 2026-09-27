package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// >> "a" >> x;
public record Escritura(List<Expression> valores, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarEscritura(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        for (Expression valor : valores) {
            contexto.verificarImprimible(valor.analizar(contexto), valor.posicion());
        }
        return null;
    }
}
