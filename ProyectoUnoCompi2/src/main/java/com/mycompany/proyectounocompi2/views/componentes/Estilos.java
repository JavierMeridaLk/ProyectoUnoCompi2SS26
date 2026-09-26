package com.mycompany.proyectounocompi2.views.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Colores, fuentes y piezas visuales 
public final class Estilos {

    public static final Font FUENTE_CODIGO = new Font(Font.MONOSPACED, Font.PLAIN, 14);
    public static final Font FUENTE_TITULO = new Font(Font.SANS_SERIF, Font.BOLD, 13);

    public static final Color ENCABEZADO_EDITOR = new Color(224, 229, 240);
    public static final Color ENCABEZADO_ARBOL = new Color(226, 236, 226);
    public static final Color ENCABEZADO_ERRORES = new Color(240, 224, 224);
    public static final Color ENCABEZADO_CODIGO = new Color(230, 226, 240);
    public static final Color BORDE = new Color(200, 200, 210);

    private Estilos() {
    }

    // Barra de titulo de un panel, con un componente opcional a la derecha.
    public static JPanel encabezado(String titulo, Color fondo, JComponent derecha) {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(fondo);
        barra.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDE),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setFont(FUENTE_TITULO);
        barra.add(etiqueta, BorderLayout.WEST);
        if (derecha != null) {
            derecha.setOpaque(false);
            barra.add(derecha, BorderLayout.EAST);
        }
        return barra;
    }

    public static JPanel encabezado(String titulo, Color fondo) {
        return encabezado(titulo, fondo, null);
    }
}
