package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// si aliter aliter finis
public record Condicional(
        List<RamaCondicional> ramas,
        List<Statement> sino,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarCondicional(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        ramas.forEach(rama -> rama.analizar(contexto));
        if (sino != null) {
            contexto.dentroDe(this, () -> sino.forEach(sentencia -> sentencia.analizar(contexto)));
        }
        return null;
    }
}
