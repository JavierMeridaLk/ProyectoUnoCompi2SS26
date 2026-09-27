package com.mycompany.proyectounocompi2.tablas;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;

// Una entrada de la tabla de simbolos.

public record Simbolo(
        String nombre,
        CategoriaSimbolo categoria,
        Tipo tipo,
        List<Tipo> parametros,
        String ambito,
        String archivo,
        Posicion posicion,
        int desplazamiento,
        int tamano) {
}
