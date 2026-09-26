/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyectounocompi2.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mycompany.proyectounocompi2.archivos.GestorArchivos;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.NumerosDeLinea;

/**
 * Muestra el codigo C generado solo lectura con opciones para copiarlo o guardarlo como archivo .c
 *
 * @author xavi
 */
public class CodigoCPanel extends javax.swing.JPanel {

    private static final String SIN_CODIGO = "// Todavía no se ha generado código C.\n";

    private final JTextArea codigo = new JTextArea();
    private final JButton copiar = new JButton("Copiar");
    private final JButton guardar = new JButton("Guardar .c");
    private boolean hayCodigo;


    public CodigoCPanel() {
        initComponents();
        construir();
    }

    private void construir() {
        removeAll();
        setLayout(new BorderLayout());
        add(Estilos.encabezado("Código C generado", Estilos.ENCABEZADO_CODIGO), BorderLayout.NORTH);

        codigo.setEditable(false);
        codigo.setFont(Estilos.FUENTE_CODIGO);
        codigo.setBackground(Color.WHITE);
        codigo.setTabSize(4);
        codigo.setMargin(new java.awt.Insets(2, 6, 2, 6));
        JScrollPane desplazamiento = new JScrollPane(codigo);
        desplazamiento.setRowHeaderView(new NumerosDeLinea(codigo));
        desplazamiento.setBorder(BorderFactory.createEmptyBorder());
        add(desplazamiento, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 4));
        botones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Estilos.BORDE));
        copiar.addActionListener(e -> copiar());
        guardar.addActionListener(e -> guardar());
        botones.add(copiar);
        botones.add(guardar);
        add(botones, BorderLayout.SOUTH);

        setCodigo(null);
    }

    //Muestra el codigo C generado
    public void setCodigo(String codigoC) {
        hayCodigo = codigoC != null && !codigoC.isBlank();
        codigo.setText(hayCodigo ? codigoC : SIN_CODIGO);
        codigo.setForeground(hayCodigo ? new Color(30, 30, 30) : Color.GRAY);
        codigo.setCaretPosition(0);
        copiar.setEnabled(hayCodigo);
        guardar.setEnabled(hayCodigo);
    }

    private void copiar() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(codigo.getText()), null);
    }

    private void guardar() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar código C");
        selector.setFileFilter(new FileNameExtensionFilter("Código C (*.c)", "c"));
        selector.setSelectedFile(new File("programa.c"));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        Path destino = selector.getSelectedFile().toPath();
        if (!destino.getFileName().toString().endsWith(".c")) {
            destino = destino.resolveSibling(destino.getFileName() + ".c");
        }
        try {
            GestorArchivos.escribir(destino, codigo.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar:\n" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
