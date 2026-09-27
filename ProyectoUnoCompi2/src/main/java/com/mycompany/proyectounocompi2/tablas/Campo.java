package com.mycompany.proyectounocompi2.tablas;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;

// Atributo de una estructura o clase.

public record Campo(String nombre, Tipo tipo, int desplazamiento, List<Integer> tamanos, Posicion posicion) {
}
