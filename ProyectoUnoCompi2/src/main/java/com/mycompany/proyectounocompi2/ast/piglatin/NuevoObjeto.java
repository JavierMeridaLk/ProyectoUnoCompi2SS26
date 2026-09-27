package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// novus Persona(12, "a")  
public record NuevoObjeto(
        String clase,
        List<Expression> argumentos,
        List<Sufijo> sufijos,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept// "texto", 'c', verum, falsus
(Visitor<T> visitante) {
        return visitante.visitarNuevoObjeto(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Tipo tipo = contexto.crearObjeto(clase, Expression.analizarTodas(argumentos, contexto), posicion);
        return Acceso.aplicarSufijos(tipo, sufijos, 0, contexto);
    }
}
