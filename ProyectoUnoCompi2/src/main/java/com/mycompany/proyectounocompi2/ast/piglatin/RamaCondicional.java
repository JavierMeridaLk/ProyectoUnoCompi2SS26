package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// condicion con su bloque
public record RamaCondicional(
        Expression condicion,
        List<Statement> cuerpo,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarRamaCondicional(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
        contexto.dentroDe(this, () -> cuerpo.forEach(sentencia -> sentencia.analizar(contexto)));
        return null;
    }
}
