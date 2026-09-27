package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// [i]
public record SufijoIndice(Expression indice, Posicion posicion) implements Sufijo {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarSufijoIndice(this);
    }

    @Override
    public Tipo aplicar(Tipo base, ContextoSemantico contexto) {
        return contexto.tipoIndice(base, indice.analizar(contexto), posicion);
    }
}
