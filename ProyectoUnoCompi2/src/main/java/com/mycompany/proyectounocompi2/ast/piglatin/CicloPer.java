package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// per (esto i : numerus 0; i < 10; i++) 
public record CicloPer(
        Statement inicio,
        Expression condicion,
        Statement actualizacion,
        List<Statement> cuerpo,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarCicloPer(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.dentroDe(this, () -> {
            if (inicio != null) {
                inicio.analizar(contexto);
            }
            if (condicion != null) {
                contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
            }
            if (actualizacion != null) {
                actualizacion.analizar(contexto);
            }
            contexto.enCiclo(() -> cuerpo.forEach(sentencia -> sentencia.analizar(contexto)));
        });
        return null;
    }
}
