package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// for (int i = 0; i < 5; i++) 
public record For(
        DeclaracionLocal declaracion,
        List<Expression> inicio,
        Expression condicion,
        List<Expression> actualizacion,
        Statement cuerpo,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarFor(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.dentroDe(this, () -> {
            if (declaracion != null) {
                declaracion.analizar(contexto);
            }
            inicio.forEach(expresion -> expresion.analizar(contexto));
            if (condicion != null) {
                contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
            }
            actualizacion.forEach(expresion -> expresion.analizar(contexto));
            contexto.enCiclo(() -> cuerpo.analizar(contexto));
        });
        return null;
    }
}
