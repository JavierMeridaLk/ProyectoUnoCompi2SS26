package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// mientras(x < 5) hacer
public record CicloMientras(
        Expression condicion,
        List<Statement> cuerpo,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarCicloMientras(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
        contexto.dentroDe(this, () -> contexto.enCiclo(() -> cuerpo.forEach(sentencia -> sentencia.analizar(contexto))));
        return null;
    }
}
