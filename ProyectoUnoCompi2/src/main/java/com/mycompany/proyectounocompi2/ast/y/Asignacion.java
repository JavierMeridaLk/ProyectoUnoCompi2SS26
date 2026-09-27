package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// x = 1  
public record Asignacion(Acceso destino, Expression valor, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAsignacion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        ListaInicializacion.verificarValor(destino.analizar(contexto), valor, contexto);
        return null;
    }
}
