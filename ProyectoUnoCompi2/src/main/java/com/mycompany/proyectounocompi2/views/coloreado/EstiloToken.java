package com.mycompany.proyectounocompi2.views.coloreado;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.mycompany.proyectounocompi2.views.componentes.Estilos;

// Categorias del coloreado con su color
public enum EstiloToken {

    PALABRA_RESERVADA("#8E24AA"),   // violeta
    TIPO("#E65100"),                // naranja
    OPERADOR("#00838F"),            // cian
    IDENTIFICADOR("#B39B00"),       // amarillo
    NUMERO("#C62828"),              // rojo
    TEXTO("#2E7D32"),               // verde
    AGRUPACION("#E91E63"),          // rosa
    SEPARADOR("#590202"),           // vino
    COMENTARIO("#8A8A8A"),          // gris
    NORMAL("#000000");              // negro

    private final SimpleAttributeSet atributos = new SimpleAttributeSet();

    EstiloToken(String hexadecimal) {
        StyleConstants.setForeground(atributos, Color.decode(hexadecimal));
        StyleConstants.setFontFamily(atributos, Estilos.FUENTE_CODIGO.getFamily());
        StyleConstants.setFontSize(atributos, Estilos.FUENTE_CODIGO.getSize());
    }

    public SimpleAttributeSet getAtributos() {
        return atributos;
    }
}
