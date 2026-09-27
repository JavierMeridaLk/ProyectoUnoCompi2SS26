package com.mycompany.proyectounocompi2.cuartetas;

// Una instruccion de codigo de tres direcciones: (operador, operando1, operando2, resultado).

public record Cuarteta(String operador, String argumento1, String argumento2, String resultado) {

    @Override
    public String toString() {
        return "(" + operador + ", " + argumento1 + ", " + argumento2 + ", " + resultado + ")";
    }
}
