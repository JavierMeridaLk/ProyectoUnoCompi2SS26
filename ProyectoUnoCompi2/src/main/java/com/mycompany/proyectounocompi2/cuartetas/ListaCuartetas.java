package com.mycompany.proyectounocompi2.cuartetas;

import java.util.ArrayList;
import java.util.List;

// Las cuartetas de todo el programa y los contadores de temporales y etiquetas 
public class ListaCuartetas {

    private final List<Cuarteta> cuartetas = new ArrayList<>();
    private int temporales;
    private int etiquetas;

    public void agregar(String operador, String argumento1, String argumento2, String resultado) {
        cuartetas.add(new Cuarteta(operador, argumento1, argumento2, resultado));
    }

    public String nuevoTemporal() {
        return "t" + (++temporales);
    }

    public String nuevaEtiqueta() {
        return "L" + (++etiquetas);
    }

    public List<Cuarteta> getCuartetas() {
        return cuartetas;
    }
}
