package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// hacer mientras(x < 10)
public record CicloHacer(
        List<Statement> cuerpo,
        Expression condicion,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarCicloHacer(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.dentroDe(this, () -> contexto.enCiclo(() -> cuerpo.forEach(sentencia -> sentencia.analizar(contexto))));
        contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
        return null;
    }
}
