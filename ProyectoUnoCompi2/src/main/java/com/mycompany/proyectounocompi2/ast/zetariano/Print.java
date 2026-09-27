package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// print(x);
public record Print(Expression valor, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarPrint(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        if (valor != null) {
            contexto.verificarImprimible(valor.analizar(contexto), valor.posicion());
        }
        return null;
    }
}
