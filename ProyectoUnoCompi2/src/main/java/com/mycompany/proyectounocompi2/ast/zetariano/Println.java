package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// println(x)
public record Println(Expression valor, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarPrintln(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        if (valor != null) {
            contexto.verificarImprimible(valor.analizar(contexto), valor.posicion());
        }
        return null;
    }
}
