package com.mycompany.proyectounocompi2.errores;

public enum TipoError {
    LEXICO("Léxico"),
    SINTACTICO("Sintáctico");

    private final String nombre;

    TipoError(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
