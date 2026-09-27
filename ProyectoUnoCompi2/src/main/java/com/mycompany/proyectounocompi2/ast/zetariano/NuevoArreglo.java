package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// new int[3][3]   new int[]{1, 2} 
public record NuevoArreglo(
        Tipo tipo,
        List<Expression> tamanos,
        ListaInicializacion valores,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarNuevoArreglo(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        for (Expression tamano : tamanos) {
            contexto.verificarEntero(tamano.analizar(contexto), tamano.posicion(), "El tamaño del arreglo");
        }
        Tipo conocido = contexto.conocido(tipo);
        if (conocido == null) {
            contexto.error(posicion, tipo.nombreClase(), "El tipo '" + tipo.nombreClase() + "' no está definido");
        } else if (valores != null) {
            ListaInicializacion.verificarValor(conocido, valores, contexto);
        }
        return conocido;
    }
}
