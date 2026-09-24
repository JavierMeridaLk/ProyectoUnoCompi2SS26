package com.mycompany.proyectounocompi2.analisis;

import java.util.Optional;

public enum Lenguaje {
    PIG_LATIN("pig", "Pig Latin"),
    Y("y", "Y?"),
    ZETARIANO("z", "Zetariano");

    private final String extension;
    private final String nombre;

    Lenguaje(String extension, String nombre) {
        this.extension = extension;
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public String getNombre() {
        return nombre;
    }

    //Detecta el lenguaje por la extension del archivo
    public static Optional<Lenguaje> desdeArchivo(String nombreArchivo) {
        int punto = nombreArchivo.lastIndexOf('.');
        if (punto < 0) {
            return Optional.empty();
        }
        String extension = nombreArchivo.substring(punto + 1);
        for (Lenguaje lenguaje : values()) {
            if (lenguaje.extension.equals(extension)) {
                return Optional.of(lenguaje);
            }
        }
        return Optional.empty();
    }
}
