package com.mycompany.proyectounocompi2.cuartetas;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;

// Algo valido para el semantico que la generacion de codigo no soporta
public class GeneracionException extends RuntimeException {

    public GeneracionException(String archivo, Posicion posicion, String mensaje) {
        super(archivo + ", línea " + posicion.linea() + ": " + mensaje);
    }
}
