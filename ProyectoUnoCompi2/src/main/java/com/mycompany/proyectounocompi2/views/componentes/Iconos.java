package com.mycompany.proyectounocompi2.views.componentes;

import java.awt.Image;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.mycompany.proyectounocompi2.analisis.Lenguaje;

/**
 * Iconos de cada lenguaje 
 */
public final class Iconos {

    public static final int TAMANO = 18;

    private static final Map<Lenguaje, Icon> CACHE = new EnumMap<>(Lenguaje.class);

    private Iconos() {
    }

    // Icono del lenguaje segun la extension del archivo
    public static Optional<Icon> paraArchivo(String nombre) {
        return Lenguaje.desdeArchivo(nombre).map(Iconos::paraLenguaje);
    }

    public static synchronized Icon paraLenguaje(Lenguaje lenguaje) {
        return CACHE.computeIfAbsent(lenguaje, l -> cargar(switch (l) {
            case PIG_LATIN -> "pig.png";
            case Y -> "y.png";
            case ZETARIANO -> "z.png";
        }));
    }

    private static Icon cargar(String archivo) {
        URL recurso = Iconos.class.getResource("/iconos/" + archivo);
        if (recurso == null) {
            return null;
        }
        Image imagen = new ImageIcon(recurso).getImage().getScaledInstance(TAMANO, TAMANO, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }
}
