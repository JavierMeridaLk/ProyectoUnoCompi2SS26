package com.mycompany.proyectounocompi2.analizador;

import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import java.util.Optional;

public enum Lenguaje {
    // tipos en el orden de TipoPrimitivo
    PIG_LATIN("pig", "Pig Latin", "numerus", "decimalis", "textum", "littera", "bool", "void", "nulo"),
    Y("y", "Y?", "entero", "flotante", "cadena", "caracter", "bool", "void", "nulo"),
    ZETARIANO("z", "Zetariano", "int", "double", "String", "char", "boolean", "void", "null");

    private final String extension;
    private final String nombre;
    private final String[] tipos;

    Lenguaje(String extension, String nombre, String... tipos) {
        this.extension = extension;
        this.nombre = nombre;
        this.tipos = tipos;
    }

    public String getNombre() {
        return nombre;
    }

    public String nombreDe(TipoPrimitivo tipo) {
        return tipos[tipo.ordinal()];
    }

    public String booleano(boolean valor) {
        return switch (this) {
            case PIG_LATIN -> valor ? "verum" : "falsus";
            case Y -> valor ? "verdadero" : "falso";
            case ZETARIANO -> valor ? "true" : "false";
        };
    }

    public String nombreDe(Tipo tipo) {
        return (tipo.esPrimitivo() ? nombreDe(tipo.primitivo()) : tipo.nombreClase()) + "[]".repeat(tipo.dimensiones());
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
