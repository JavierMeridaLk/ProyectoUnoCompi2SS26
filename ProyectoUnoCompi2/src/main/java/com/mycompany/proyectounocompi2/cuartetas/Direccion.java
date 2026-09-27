package com.mycompany.proyectounocompi2.cuartetas;

import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Una celda del stack o del heap donde se puede leer o escribir
public record Direccion(String base, int desplazamiento, boolean enHeap, Tipo tipo) {
}
