package com.mycompany.proyectounocompi2.tablas;

import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import static com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo.*;

// Compatibilidad de tipos, igual para los tres lenguajes:

public final class TablaCompatibilidad {

    private TablaCompatibilidad() {
    }

    public static TipoPrimitivo resultado(OperadorBinario operador, TipoPrimitivo izquierda, TipoPrimitivo derecha) {
        switch (operador) {
            case SUMA:
                if ((izquierda == CADENA && esValor(derecha)) || (derecha == CADENA && esValor(izquierda))) {
                    return CADENA;
                }
                return aritmetico(izquierda, derecha);
            case RESTA:
            case MULTIPLICACION:
            case DIVISION:
                return aritmetico(izquierda, derecha);
            case MODULO:
                return esEntero(izquierda) && esEntero(derecha) ? ENTERO : null;
            case MENOR_QUE:
            case MENOR_O_IGUAL_QUE:
            case MAYOR_QUE:
            case MAYOR_O_IGUAL_QUE:
                return esNumerico(izquierda) && esNumerico(derecha) ? BOOLEANO : null;
            case IGUAL_QUE:
            case DIFERENTE_DE:
                return (esNumerico(izquierda) && esNumerico(derecha)) || (izquierda == derecha && izquierda != VOID)
                        ? BOOLEANO : null;
            case Y_LOGICO:
            case O_LOGICO:
                return izquierda == BOOLEANO && derecha == BOOLEANO ? BOOLEANO : null;
            default:
                return null;
        }
    }

    // el tipo del resultado, o null si la operacion no es valida
    public static TipoPrimitivo resultado(OperadorUnario operador, TipoPrimitivo operando) {
        switch (operador) {
            case NEGATIVO:
            case POSITIVO:
                return esNumerico(operando) ? (operando == DECIMAL ? DECIMAL : ENTERO) : null;
            case PRE_INCREMENTO:
            case PRE_DECREMENTO:
                return esNumerico(operando) ? operando : null;
            case NEGACION:
                return operando == BOOLEANO ? BOOLEANO : null;
            default:
                return null;
        }
    }

    public static boolean esAsignable(Tipo destino, Tipo origen) {
        if (destino.equals(origen)) {
            return true;
        }
        if (origen.primitivo() == NULO) {
            return !destino.esPrimitivo() || destino.dimensiones() > 0;
        }
        if (destino.dimensiones() > 0 || origen.dimensiones() > 0 || !destino.esPrimitivo() || !origen.esPrimitivo()) {
            return false;
        }
        return switch (destino.primitivo()) {
            case DECIMAL -> origen.primitivo() == ENTERO || origen.primitivo() == CARACTER;
            case ENTERO -> origen.primitivo() == CARACTER;
            default -> false;
        };
    }

    private static TipoPrimitivo aritmetico(TipoPrimitivo izquierda, TipoPrimitivo derecha) {
        if (!esNumerico(izquierda) || !esNumerico(derecha)) {
            return null;
        }
        return izquierda == DECIMAL || derecha == DECIMAL ? DECIMAL : ENTERO;
    }

    private static boolean esEntero(TipoPrimitivo tipo) {
        return tipo == ENTERO || tipo == CARACTER;
    }

    private static boolean esNumerico(TipoPrimitivo tipo) {
        return esEntero(tipo) || tipo == DECIMAL;
    }

    // Tipos que tienen un valor (no void ni nulo)
    private static boolean esValor(TipoPrimitivo tipo) {
        return tipo != null && tipo != VOID && tipo != NULO;
    }
}
