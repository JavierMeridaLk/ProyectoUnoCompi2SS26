package com.mycompany.proyectounocompi2.ast.comun;

public enum OperadorUnario {
    NEGATIVO("-"),
    POSITIVO("+"),
    NEGACION("!"),          
    PRE_INCREMENTO("++"),
    PRE_DECREMENTO("--");

    private final String simbolo;

    OperadorUnario(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public static OperadorUnario desdeTexto(String texto) {
        if (texto.equals("non")) {
            return NEGACION;
        }
        for (OperadorUnario operador : values()) {
            if (operador.simbolo.equals(texto)) {
                return operador;
            }
        }
        throw new IllegalArgumentException("Operador unario desconocido: " + texto);
    }
}
