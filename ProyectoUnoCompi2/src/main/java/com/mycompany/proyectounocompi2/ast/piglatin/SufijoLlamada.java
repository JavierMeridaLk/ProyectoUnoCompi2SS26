package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// (a, b)
public record SufijoLlamada(List<Expression> argumentos, Posicion posicion) implements Sufijo {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarSufijoLlamada(this);
    }

    @Override
    public Tipo aplicar(Tipo base, ContextoSemantico contexto) {
        // las llamadas con nombre se revisan en Acceso.aplicarSufijos
        contexto.error(posicion, null, "Solo se pueden llamar funciones o métodos por su nombre");
        return null;
    }
}
