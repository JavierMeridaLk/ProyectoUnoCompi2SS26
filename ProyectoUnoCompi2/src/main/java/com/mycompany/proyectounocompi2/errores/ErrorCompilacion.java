package com.mycompany.proyectounocompi2.errores;

/**
 * Un error encontrado durante el analisis de un archivo.
 */
public record ErrorCompilacion(
        TipoError tipo,
        String archivo,
        int linea,
        int columna,
        String lexema,
        String descripcion) {

    @Override
    public String toString() {
        String ubicacion = archivo + " (" + linea + ":" + columna + ")";
        String token = lexema == null || lexema.isEmpty() ? "" : " cerca de '" + lexema + "'";
        return "[" + tipo.getNombre() + "] " + ubicacion + token + ": " + descripcion;
    }
}
