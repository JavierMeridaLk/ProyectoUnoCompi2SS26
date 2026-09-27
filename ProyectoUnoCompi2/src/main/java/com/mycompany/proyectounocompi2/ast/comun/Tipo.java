package com.mycompany.proyectounocompi2.ast.comun;

// Tipo de una variable, parametro o funcion
public record Tipo(TipoPrimitivo primitivo, String nombreClase, int dimensiones) {

    public static Tipo primitivo(TipoPrimitivo primitivo) {
        return new Tipo(primitivo, null, 0);
    }

    public static Tipo clase(String nombre) {
        return new Tipo(null, nombre, 0);
    }

    public Tipo conDimensiones(int dimensiones) {
        return new Tipo(primitivo, nombreClase, dimensiones);
    }

    public boolean esPrimitivo() {
        return primitivo != null;
    }

    @Override
    public String toString() {
        return (esPrimitivo() ? primitivo.name().toLowerCase() : nombreClase) + "[]".repeat(dimensiones);
    }
}
