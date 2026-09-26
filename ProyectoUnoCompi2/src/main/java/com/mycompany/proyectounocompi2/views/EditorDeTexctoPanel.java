/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.analisis.Lenguaje;
import com.mycompany.proyectounocompi2.archivos.GestorArchivos;
import com.mycompany.proyectounocompi2.formato.FormateadorCodigo;
import com.mycompany.proyectounocompi2.views.componentes.EncabezadoPestana;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.PestanaEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

/**
 * Editor de codigo con pestañas
 *
 * @author xavi
 */
public class EditorDeTexctoPanel extends javax.swing.JPanel {

    private final JTabbedPane pestanas = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    private final JLabel posicion = new JLabel("Línea 1, Columna 1");
    private final JLabel lenguaje = new JLabel(" ");
    private final JLabel vacio = new JLabel("Abre un archivo del proyecto o crea uno nuevo", SwingConstants.CENTER);
    private final JPanel contenido = new JPanel(new BorderLayout());
    private final List<Consumer<Path>> alGuardar = new ArrayList<>();


    public EditorDeTexctoPanel() {
        initComponents();
        construir();
    }

    private void construir() {
        removeAll();
        setLayout(new BorderLayout());
        JButton tabular = new JButton("Tabular código");
        tabular.setFocusable(false);
        tabular.addActionListener(e -> tabularActual());
        add(Estilos.encabezado("Editor de código fuente", Estilos.ENCABEZADO_EDITOR, tabular), BorderLayout.NORTH);

        vacio.setForeground(Color.GRAY);
        contenido.setBackground(Color.WHITE);
        contenido.add(vacio, BorderLayout.CENTER);
        add(contenido, BorderLayout.CENTER);

        JPanel barraEstado = new JPanel(new BorderLayout());
        barraEstado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Estilos.BORDE),
                BorderFactory.createEmptyBorder(3, 10, 3, 10)));
        lenguaje.setForeground(Color.DARK_GRAY);
        posicion.setForeground(Color.DARK_GRAY);
        barraEstado.add(lenguaje, BorderLayout.WEST);
        barraEstado.add(posicion, BorderLayout.EAST);
        add(barraEstado, BorderLayout.SOUTH);

        pestanas.addChangeListener(e -> actualizarBarraEstado());
        actualizarBarraEstado();
    }

    // Abrir y crear 

    // Abre un archivo en una pestaña nueva, o selecciona la pestaña si ya estaba abiert
    public PestanaEditor abrir(Path archivo) throws IOException {
        Optional<PestanaEditor> abierta = buscar(archivo);
        if (abierta.isPresent()) {
            pestanas.setSelectedComponent(abierta.get());
            return abierta.get();
        }
        PestanaEditor pestana = new PestanaEditor(archivo.toAbsolutePath().normalize(), GestorArchivos.leer(archivo));
        agregar(pestana);
        return pestana;
    }

    // Pestaña vacia que todavia no tiene archivo
    public PestanaEditor nuevaPestana() {
        PestanaEditor pestana = new PestanaEditor(null, "");
        agregar(pestana);
        return pestana;
    }

    private void agregar(PestanaEditor pestana) {
        if (pestanas.getParent() == null) {
            contenido.remove(vacio);
            contenido.add(pestanas, BorderLayout.CENTER);
            contenido.revalidate();
        }
        pestanas.addTab(pestana.getNombre(), pestana);
        int indice = pestanas.indexOfComponent(pestana);
        pestanas.setTabComponentAt(indice, new EncabezadoPestana(pestana, () -> cerrar(pestana)));
        pestana.getTexto().addCaretListener(e -> actualizarBarraEstado());
        pestana.alCambiarEstado(this::actualizarBarraEstado);
        pestanas.setSelectedComponent(pestana);
        pestana.getTexto().requestFocusInWindow();
    }

    public Optional<PestanaEditor> buscar(Path archivo) {
        Path buscado = archivo.toAbsolutePath().normalize();
        return todas().stream().filter(p -> buscado.equals(p.getArchivo())).findFirst();
    }

    // Consultas 

    public Optional<PestanaEditor> actual() {
        return Optional.ofNullable((PestanaEditor) pestanas.getSelectedComponent());
    }

    public List<PestanaEditor> todas() {
        List<PestanaEditor> lista = new ArrayList<>();
        for (int i = 0; i < pestanas.getTabCount(); i++) {
            lista.add((PestanaEditor) pestanas.getComponentAt(i));
        }
        return lista;
    }

    public void alGuardar(Consumer<Path> accion) {
        alGuardar.add(accion);
    }

    // Guardar 

    public boolean guardarActual() {
        return actual().map(this::guardar).orElse(false);
    }

    public boolean guardarTodo() {
        boolean todo = true;
        for (PestanaEditor pestana : todas()) {
            if (pestana.isModificado() || pestana.getArchivo() == null) {
                todo &= guardar(pestana);
            }
        }
        return todo;
    }

    //Guarda la pestaña si no tiene archivo pide donde guardarla
    public boolean guardar(PestanaEditor pestana) {
        if (pestana.getArchivo() == null) {
            JFileChooser selector = new JFileChooser();
            selector.setDialogTitle("Guardar archivo");
            if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                return false;
            }
            Path destino = selector.getSelectedFile().toPath().toAbsolutePath().normalize();
            if (Files.exists(destino) && JOptionPane.showConfirmDialog(this,
                    "El archivo " + destino.getFileName() + " ya existe. ¿Reemplazarlo?", "Guardar",
                    JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return false;
            }
            pestana.setArchivo(destino);
        }
        try {
            GestorArchivos.escribir(pestana.getArchivo(), pestana.getContenido());
            pestana.marcarGuardado();
            alGuardar.forEach(accion -> accion.accept(pestana.getArchivo()));
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar " + pestana.getNombre() + ":\n" + e.getMessage(),
                    "Error al guardar", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Cerrar 

    //Cierra la pestaña preguntando si hay cambios sin guardar
    public boolean cerrar(PestanaEditor pestana) {
        if (pestana.isModificado()) {
            pestanas.setSelectedComponent(pestana);
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Guardar los cambios de " + pestana.getNombre() + " antes de cerrar?", "Cambios sin guardar",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (opcion == JOptionPane.CANCEL_OPTION || opcion == JOptionPane.CLOSED_OPTION) {
                return false;
            }
            if (opcion == JOptionPane.YES_OPTION && !guardar(pestana)) {
                return false;
            }
        }
        quitar(pestana);
        return true;
    }

    public boolean cerrarActual() {
        return actual().map(this::cerrar).orElse(true);
    }

    //Cierra todas las pestañas 
    public boolean cerrarTodo() {
        for (PestanaEditor pestana : todas()) {
            if (!cerrar(pestana)) {
                return false;
            }
        }
        return true;
    }

    
    public void cerrarEliminados(Path ruta) {
        Path eliminada = ruta.toAbsolutePath().normalize();
        for (PestanaEditor pestana : todas()) {
            if (pestana.getArchivo() != null && pestana.getArchivo().startsWith(eliminada)) {
                quitar(pestana);
            }
        }
    }

    private void quitar(PestanaEditor pestana) {
        pestanas.remove(pestana);
        if (pestanas.getTabCount() == 0) {
            contenido.remove(pestanas);
            contenido.add(vacio, BorderLayout.CENTER);
            contenido.revalidate();
            contenido.repaint();
        }
        actualizarBarraEstado();
    }

    // Tabular 

    // Re-tabula el archivo actual segun su lenguaje
    public void tabularActual() {
        Optional<PestanaEditor> actual = actual();
        if (actual.isEmpty()) {
            return;
        }
        PestanaEditor pestana = actual.get();
        Optional<Lenguaje> lenguajeArchivo = pestana.getLenguaje();
        if (lenguajeArchivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Guarda el archivo con extensión .pig, .y o .z para tabularlo.",
                    "Tabular código", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            pestana.reemplazarContenido(FormateadorCodigo.formatear(lenguajeArchivo.get(), pestana.getContenido()));
        } catch (FormateadorCodigo.ErrorFormato e) {
            JOptionPane.showMessageDialog(this, "No se puede tabular:\n" + e.getMessage(), "Tabular código",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Barra de estado 

    private void actualizarBarraEstado() {
        Optional<PestanaEditor> pestana = actual();
        if (pestana.isEmpty()) {
            posicion.setText(" ");
            lenguaje.setText(" ");
            return;
        }
        int[] lineaColumna = pestana.get().getLineaColumna();
        posicion.setText("Línea " + lineaColumna[0] + ", Columna " + lineaColumna[1]);
        lenguaje.setText(pestana.get().getLenguaje().map(l -> l.getNombre()).orElse("Texto"));
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
