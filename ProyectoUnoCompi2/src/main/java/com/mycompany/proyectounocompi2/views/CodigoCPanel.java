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
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mycompany.proyectounocompi2.archivos.GestorArchivos;
import com.mycompany.proyectounocompi2.cuartetas.EjecutorC;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.NumerosDeLinea;

// Muestra el codigo C generado solo lectura con opciones para copiarlo o guardarlo como archivo .c
//
// @author xavi
public class CodigoCPanel extends javax.swing.JPanel {

    private static final String SIN_CODIGO = "// Todavía no se ha generado código C.\n";

    private final JTextArea codigo = new JTextArea();
    private final JButton copiar = new JButton("Copiar");
    private final JButton guardar = new JButton("Guardar .c");
    private final JButton compilarC = new JButton("Compilar en C");
    private boolean hayCodigo;
    private Path carpetaSalida;             // donde se escriben programa.c, programa y ejecutar.sh
    private Runnable alGenerarArchivos = () -> { };


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
        compilarC.addActionListener(e -> compilarEnC());
        botones.add(copiar);
        botones.add(guardar);
        botones.add(compilarC);
        add(botones, BorderLayout.SOUTH);

        setCodigo(null, null);
    }

    // Muestra el codigo C generado y la carpeta donde se compila (null: no hay codigo)
    public void setCodigo(String codigoC, Path carpeta) {
        hayCodigo = codigoC != null && !codigoC.isBlank();
        carpetaSalida = carpeta;
        codigo.setText(hayCodigo ? codigoC : SIN_CODIGO);
        codigo.setForeground(hayCodigo ? new Color(30, 30, 30) : Color.GRAY);
        codigo.setCaretPosition(0);
        copiar.setEnabled(hayCodigo);
        guardar.setEnabled(hayCodigo);
        compilarC.setEnabled(hayCodigo);
    }

    // Se llama despues de escribir los archivos en la carpeta de salida (para refrescar el arbol)
    public void alGenerarArchivos(Runnable accion) {
        alGenerarArchivos = accion;
    }

    // Compila el codigo con gcc (fuera del hilo de la interfaz) y avisa si
    // compilo o no. El programa se ejecuta aparte, desde una terminal.
    private void compilarEnC() {
        compilarC.setEnabled(false);
        String fuente = codigo.getText();
        Path carpeta = carpetaSalida;
        new SwingWorker<EjecutorC.Resultado, Void>() {
            @Override
            protected EjecutorC.Resultado doInBackground() throws Exception {
                return EjecutorC.compilar(carpeta, fuente);
            }

            @Override
            protected void done() {
                compilarC.setEnabled(hayCodigo);
                alGenerarArchivos.run();
                try {
                    mostrarResultado(get(), carpeta);
                } catch (Exception e) {
                    Throwable causa = e.getCause() != null ? e.getCause() : e;
                    JOptionPane.showMessageDialog(CodigoCPanel.this, "No se pudo compilar con gcc:\n"
                            + causa.getMessage() + "\n\n¿Está instalado gcc?", "Compilar en C",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void mostrarResultado(EjecutorC.Resultado resultado, Path carpeta) {
        if (!resultado.compilo()) {
            JTextArea mensajes = new JTextArea(resultado.mensajes(), 15, 70);
            mensajes.setEditable(false);
            mensajes.setFont(Estilos.FUENTE_CODIGO);
            JOptionPane.showMessageDialog(this, new JScrollPane(mensajes), "El código C no compiló",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "El código C compiló correctamente con gcc.\n\n"
                + "Archivos en: " + carpeta + "\n"
                + "Para ejecutarlo, en una terminal:  " + carpeta.resolve("programa"),
                "Compilar en C", JOptionPane.INFORMATION_MESSAGE);
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
