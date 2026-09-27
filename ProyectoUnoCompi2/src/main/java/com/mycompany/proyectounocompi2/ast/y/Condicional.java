package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// si sino contrario
public record Condicional(
        List<RamaCondicional> ramas,
        List<Statement> contrario,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarCondicional(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        ramas.forEach(rama -> rama.analizar(contexto));
        if (contrario != null) {
            contexto.dentroDe(this, () -> contrario.forEach(sentencia -> sentencia.analizar(contexto)));
        }
        return null;
    }
}
