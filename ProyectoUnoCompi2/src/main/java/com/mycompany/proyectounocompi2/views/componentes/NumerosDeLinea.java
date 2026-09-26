package com.mycompany.proyectounocompi2.views.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

/**
 * Columna con los numeros de linea de un componente de texto. 
 */
public class NumerosDeLinea extends JComponent {

    private static final int MARGEN = 8;
    private static final Color FONDO = new Color(245, 245, 245);
    private static final Color BORDE = new Color(220, 220, 220);
    private static final Color NUMERO = new Color(150, 150, 150);
    private static final Color NUMERO_ACTUAL = new Color(40, 40, 40);

    private final JTextComponent texto;
    private int lineaActual = -1;

    public NumerosDeLinea(JTextComponent texto) {
        this.texto = texto;
        setFont(texto.getFont());
        setOpaque(true);

        texto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizar();
            }
        });
        texto.addCaretListener(e -> {
            int linea = lineaDelCursor();
            if (linea != lineaActual) {
                lineaActual = linea;
                repaint();
            }
        });
        texto.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }
        });
    }

    private void actualizar() {
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

    private int lineaDelCursor() {
        return texto.getDocument().getDefaultRootElement().getElementIndex(texto.getCaretPosition());
    }

    @Override
    public Dimension getPreferredSize() {
        int lineas = texto.getDocument().getDefaultRootElement().getElementCount();
        FontMetrics metricas = getFontMetrics(getFont());
        int ancho = metricas.stringWidth(String.valueOf(Math.max(lineas, 999))) + MARGEN * 2;
        return new Dimension(ancho, texto.getPreferredSize().height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Rectangle area = g.getClipBounds();
        g2.setColor(FONDO);
        g2.fillRect(area.x, area.y, area.width, area.height);
        g2.setColor(BORDE);
        g2.drawLine(getWidth() - 1, area.y, getWidth() - 1, area.y + area.height);

        Element raiz = texto.getDocument().getDefaultRootElement();
        int primera = raiz.getElementIndex(texto.viewToModel2D(new Point(0, area.y)));
        int ultima = raiz.getElementIndex(texto.viewToModel2D(new Point(0, area.y + area.height)));
        int actual = lineaDelCursor();
        Font normal = getFont();
        Font negrita = normal.deriveFont(Font.BOLD);

        for (int linea = primera; linea <= ultima; linea++) {
            try {
                Rectangle2D posicion = texto.modelToView2D(raiz.getElement(linea).getStartOffset());
                if (posicion == null) {
                    continue;
                }
                g2.setFont(linea == actual ? negrita : normal);
                g2.setColor(linea == actual ? NUMERO_ACTUAL : NUMERO);
                FontMetrics metricas = g2.getFontMetrics();
                String numero = String.valueOf(linea + 1);
                int x = getWidth() - MARGEN - metricas.stringWidth(numero);
                int y = (int) (posicion.getY() + posicion.getHeight()) - metricas.getDescent();
                g2.drawString(numero, x, y);
            } catch (BadLocationException e) {
            
            }
        }
    }
}
