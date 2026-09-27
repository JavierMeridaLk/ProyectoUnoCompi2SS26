package com.mycompany.proyectounocompi2.ast.comun;

public enum OperadorBinario {
    SUMA("+"),
    RESTA("-"),
    MULTIPLICACION("*"),
    DIVISION("/"),
    MODULO("%"),
    IGUAL_QUE("=="),
    DIFERENTE_DE("!="),
    MENOR_QUE("<"),
    MENOR_O_IGUAL_QUE("<="),
    MAYOR_QUE(">"),
    MAYOR_O_IGUAL_QUE(">="),
    Y_LOGICO("&&"),
    O_LOGICO("||");

    private final String simbolo;

    OperadorBinario(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public static OperadorBinario desdeTexto(String texto) {
        for (OperadorBinario operador : values()) {
            if (operador.simbolo.equals(texto)) {
                return operador;
            }
        }
        throw new IllegalArgumentException("Operador binario desconocido: " + texto);
    }
}
