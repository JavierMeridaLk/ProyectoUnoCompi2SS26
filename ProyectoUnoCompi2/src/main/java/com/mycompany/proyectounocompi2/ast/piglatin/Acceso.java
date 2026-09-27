package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

public record Acceso(String nombre, List<Sufijo> sufijos, Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAcceso(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        // nombre
        if (!sufijos.isEmpty() && sufijos.get(0) instanceof SufijoLlamada llamada) {
            Tipo tipo = contexto.llamarFuncion(nombre, Expression.analizarTodas(llamada.argumentos(), contexto), posicion);
            return aplicarSufijos(tipo, sufijos, 1, contexto);
        }
        return aplicarSufijos(contexto.tipoVariable(nombre, posicion), sufijos, 0, contexto);
    }

    // Aplica [indice], .atributo y .metodo(args) de izquierda a derecha
    static Tipo aplicarSufijos(Tipo tipo, List<Sufijo> sufijos, int desde, ContextoSemantico contexto) {
        for (int i = desde; i < sufijos.size(); i++) {
            if (sufijos.get(i) instanceof SufijoAtributo metodo && i + 1 < sufijos.size()
                    && sufijos.get(i + 1) instanceof SufijoLlamada llamada) {
                tipo = contexto.llamarMetodo(tipo, metodo.nombre(),
                        Expression.analizarTodas(llamada.argumentos(), contexto), metodo.posicion());
                i++;
            } else {
                tipo = sufijos.get(i).aplicar(tipo, contexto);
            }
        }
        return tipo;
    }
}
