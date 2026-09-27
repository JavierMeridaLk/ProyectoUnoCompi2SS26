package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// x = 1   x += 2   a = b = 3
public record AsignacionExpresion(
        Expression destino,
        OperadorAsignacion operador,
        Expression valor,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAsignacionExpresion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        if (!(destino instanceof Identificador || destino instanceof AccesoAtributo || destino instanceof AccesoIndice)) {
            contexto.error(posicion, operador.getSimbolo(),
                    "Solo se puede asignar a una variable, un atributo o una posición de un arreglo");
            return null;
        }
        Tipo tipo = destino.analizar(contexto);
        if (operador == OperadorAsignacion.ASIGNACION) {
            ListaInicializacion.verificarValor(tipo, valor, contexto);
        } else {
            // a += b  ->  a = a + b
            Tipo resultado = contexto.operacionBinaria(operador.operacion(), tipo, valor.analizar(contexto), posicion);
            contexto.verificarAsignacion(tipo, resultado, posicion);
        }
        return tipo;
    }
}
