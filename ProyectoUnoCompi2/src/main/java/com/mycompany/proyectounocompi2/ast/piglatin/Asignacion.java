package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;

public record Asignacion(Acceso destino, Expression valor, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAsignacion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        List<Sufijo> sufijos = destino.sufijos();
        if (!sufijos.isEmpty() && sufijos.get(sufijos.size() - 1) instanceof SufijoLlamada) {
            contexto.error(posicion, destino.nombre(), "No se puede asignar un valor a una llamada");
            return null;
        }
        ListaInicializacion.verificarValor(destino.analizar(contexto), valor, contexto);
        return null;
    }
}
