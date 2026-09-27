package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// x <<   o solo <<  
public record Lectura(Acceso destino, Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarLectura(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        if (destino != null) {
            Tipo tipo = destino.analizar(contexto);
            if (tipo != null && !ContextoSemantico.esSimple(tipo)) {
                contexto.error(posicion, destino.nombre(),
                        "Solo se puede leer en un valor de tipo primitivo (se encontró '" + tipo + "')");
            }
        }
        return null;
    }
}
