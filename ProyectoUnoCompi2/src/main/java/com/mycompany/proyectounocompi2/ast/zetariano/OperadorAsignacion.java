package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;

public enum OperadorAsignacion {
    ASIGNACION("="),
    MAS_IGUAL("+="),
    MENOS_IGUAL("-="),
    POR_IGUAL("*="),
    ENTRE_IGUAL("/="),
    MODULO_IGUAL("%=");

    private final String simbolo;

    OperadorAsignacion(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public static OperadorAsignacion desdeTexto(String texto) {
        for (OperadorAsignacion operador : values()) {
            if (operador.simbolo.equals(texto)) {
                return operador;
            }
        }
        throw new IllegalArgumentException("Operador de asignacion desconocido: " + texto);
    }

    public OperadorBinario operacion() {
        return switch (this) {
            case MAS_IGUAL -> OperadorBinario.SUMA;
            case MENOS_IGUAL -> OperadorBinario.RESTA;
            case POR_IGUAL -> OperadorBinario.MULTIPLICACION;
            case ENTRE_IGUAL -> OperadorBinario.DIVISION;
            case MODULO_IGUAL -> OperadorBinario.MODULO;
            case ASIGNACION -> null;
        };
    }
}
