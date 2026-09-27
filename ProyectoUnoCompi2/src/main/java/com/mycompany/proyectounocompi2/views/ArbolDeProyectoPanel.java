package com.mycompany.proyectounocompi2.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import com.mycompany.proyectounocompi2.archivos.GestorArchivos;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.Iconos;
import javax.swing.tree.TreePath;

// @author xavi
public class ArbolDeProyectoPanel extends javax.swing.JPanel {

    // Extensiones que se ofrecen al crear un archivo.
    private static final String[] EXTENSIONES = {".pig", ".y", ".z"};

    private final DefaultTreeModel modelo = new DefaultTreeModel(new DefaultMutableTreeNode());
    private final JTree arbol = new JTree(modelo);
    private final JPanel tarjetas = new JPanel(new CardLayout());
    private final List<Consumer<Path>> alAbrirArchivo = new ArrayList<>();
    private final List<Consumer<Path>> alEliminar = new ArrayList<>();
    private Path raiz;

    //Nodo del arbol guarda la ruta y muestra solo el nombre
    private record Nodo(Path ruta) {
        @Override
        public String toString() {
            return ruta.getFileName() != null ? ruta.getFileName().toString() : ruta.toString();
        }
    }

    public ArbolDeProyectoPanel() {
        initComponents();
        construir();
    }

    private void construir() {
        removeAll();
        setLayout(new BorderLayout());

        JButton actualizar = new JButton("Actualizar");
        actualizar.setFocusable(false);
        actualizar.addActionListener(e -> refrescar());
        add(Estilos.encabezado("Proyecto", Estilos.ENCABEZADO_ARBOL, actualizar), BorderLayout.NORTH);

        arbol.setRootVisible(true);
        arbol.setShowsRootHandles(true);
        arbol.setRowHeight(Iconos.TAMANO + 4);
        arbol.setCellRenderer(new RenderizadorArchivos());
        arbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionado().filter(Files::isRegularFile).ifPresent(ruta -> alAbrirArchivo.forEach(a -> a.accept(ruta)));
                }
            }
        });

        JLabel sinProyecto = new JLabel("<html><center>No hay un proyecto abierto.<br>"
                + "Usa Archivo → Abrir proyecto<br>o Crear proyecto.</center></html>", SwingConstants.CENTER);
        sinProyecto.setForeground(Color.GRAY);
        JPanel panelVacio = new JPanel(new BorderLayout());
        panelVacio.setBackground(Color.WHITE);
        panelVacio.add(sinProyecto, BorderLayout.CENTER);

        tarjetas.add(panelVacio, "vacio");
        tarjetas.add(new JScrollPane(arbol), "arbol");
        add(tarjetas, BorderLayout.CENTER);
    }

    // Proyecto 

    public void abrirProyecto(Path carpeta) {
        raiz = carpeta.toAbsolutePath().normalize();
        refrescar();
        ((CardLayout) tarjetas.getLayout()).show(tarjetas, "arbol");
    }

    public Optional<Path> getRaiz() {
        return Optional.ofNullable(raiz);
    }

    public void alAbrirArchivo(Consumer<Path> accion) {
        alAbrirArchivo.add(accion);
    }

    
    public void alEliminar(Consumer<Path> accion) {
        alEliminar.add(accion);
    }

    
    public void refrescar() {
        if (raiz == null) {
            return;
        }
        Set<Path> expandidas = new HashSet<>();
        Enumeration<TreePath> abiertas = arbol.getExpandedDescendants(new TreePath(modelo.getRoot()));
        while (abiertas != null && abiertas.hasMoreElements()) {
            expandidas.add(rutaDe(abiertas.nextElement()));
        }
        Optional<Path> seleccion = seleccionado();

        DefaultMutableTreeNode nodoRaiz = new DefaultMutableTreeNode(new Nodo(raiz));
        cargar(nodoRaiz, raiz);
        modelo.setRoot(nodoRaiz);

        arbol.expandPath(new TreePath(nodoRaiz.getPath()));
        for (int fila = 0; fila < arbol.getRowCount(); fila++) {
            TreePath camino = arbol.getPathForRow(fila);
            if (expandidas.contains(rutaDe(camino))) {
                arbol.expandPath(camino);
            }
            if (seleccion.isPresent() && seleccion.get().equals(rutaDe(camino))) {
                arbol.setSelectionPath(camino);
            }
        }
    }

    // Carga el contenido de una carpeta
    private void cargar(DefaultMutableTreeNode nodo, Path carpeta) {
        try (Stream<Path> hijos = Files.list(carpeta)) {
            List<Path> ordenados = hijos
                    .filter(p -> !p.getFileName().toString().startsWith("."))
                    .sorted(Comparator.comparing((Path p) -> !Files.isDirectory(p))
                            .thenComparing(p -> p.getFileName().toString().toLowerCase()))
                    .toList();
            for (Path hijo : ordenados) {
                DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(new Nodo(hijo));
                nodo.add(nodoHijo);
                if (Files.isDirectory(hijo)) {
                    cargar(nodoHijo, hijo);
                }
            }
        } catch (IOException e) {
            // una carpeta que no se puede leer se muestra vacia
        }
    }

    private static Path rutaDe(TreePath camino) {
        Object nodo = ((DefaultMutableTreeNode) camino.getLastPathComponent()).getUserObject();
        return nodo instanceof Nodo n ? n.ruta() : null;
    }

    public Optional<Path> seleccionado() {
        TreePath camino = arbol.getSelectionPath();
        return camino == null ? Optional.empty() : Optional.ofNullable(rutaDe(camino));
    }

    // Carpeta donde crear cosas
    private Path carpetaDestino() {
        return seleccionado().map(p -> Files.isDirectory(p) ? p : p.getParent()).orElse(raiz);
    }

    // Operaciones 

    // Pide nombre y extension, crea el archivo en la carpeta 
    public void crearArchivo() {
        if (raiz == null) {
            return;
        }
        JTextField nombre = new JTextField(18);
        JComboBox<String> extension = new JComboBox<>(EXTENSIONES);
        JPanel formulario = new JPanel(new GridLayout(0, 1, 4, 4));
        formulario.add(new JLabel("Carpeta: " + raiz.relativize(carpetaDestino())));
        formulario.add(new JLabel("Nombre del archivo:"));
        formulario.add(nombre);
        formulario.add(new JLabel("Lenguaje:"));
        formulario.add(extension);
        // el cursor queda en el nombre al abrirse el dialogo
        nombre.addHierarchyListener(e -> {
            if (nombre.isShowing()) {
                nombre.requestFocusInWindow();
            }
        });
        if (JOptionPane.showConfirmDialog(this, formulario, "Nuevo archivo", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE) != JOptionPane.OK_OPTION) {
            return;
        }
        String texto = nombre.getText().trim();
        if (!nombreValido(texto)) {
            return;
        }
        String elegida = (String) extension.getSelectedItem();
        Path archivo = carpetaDestino().resolve(texto.endsWith(elegida) ? texto : texto + elegida);
        if (Files.exists(archivo)) {
            JOptionPane.showMessageDialog(this, "Ya existe " + archivo.getFileName(), "Nuevo archivo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            GestorArchivos.escribir(archivo, "");
            refrescar();
            alAbrirArchivo.forEach(a -> a.accept(archivo));
        } catch (IOException ex) {
            error("No se pudo crear el archivo", ex);
        }
    }

    public void crearCarpeta() {
        if (raiz == null) {
            return;
        }
        String nombre = JOptionPane.showInputDialog(this, "Nombre de la carpeta:", "Nueva carpeta",
                JOptionPane.PLAIN_MESSAGE);
        if (nombre == null || !nombreValido(nombre.trim())) {
            return;
        }
        try {
            Files.createDirectories(carpetaDestino().resolve(nombre.trim()));
            refrescar();
        } catch (IOException ex) {
            error("No se pudo crear la carpeta", ex);
        }
    }

    // Guarda una copia del archivo seleccionado
    public void descargarSeleccionado() {
        Optional<Path> seleccion = seleccionado();
        if (seleccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un archivo o carpeta del proyecto.", "Descargar",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Path origen = seleccion.get();
        boolean esCarpeta = Files.isDirectory(origen);
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle(esCarpeta ? "Descargar carpeta (.zip)" : "Descargar archivo");
        selector.setSelectedFile(new java.io.File(origen.getFileName() + (esCarpeta ? ".zip" : "")));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        Path destino = selector.getSelectedFile().toPath();
        if (Files.exists(destino) && JOptionPane.showConfirmDialog(this, destino.getFileName()
                + " ya existe. ¿Reemplazarlo?", "Descargar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            if (esCarpeta) {
                GestorArchivos.comprimir(origen, destino);
            } else {
                GestorArchivos.copiar(origen, destino);
            }
            JOptionPane.showMessageDialog(this, "Guardado en:\n" + destino, "Descargar",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            error("No se pudo descargar", ex);
        }
    }

    public void eliminarSeleccionado() {
        Optional<Path> seleccion = seleccionado();
        if (seleccion.isEmpty() || seleccion.get().equals(raiz)) {
            JOptionPane.showMessageDialog(this, "Selecciona en el árbol el archivo o carpeta a eliminar.\n"
                    + "(La carpeta raíz del proyecto no se puede eliminar.)", "Eliminar",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Path ruta = seleccion.get();
        String tipo = Files.isDirectory(ruta) ? "la carpeta" : "el archivo";
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar " + tipo + " " + ruta.getFileName()
                + "?\nEsta acción no se puede deshacer.", "Eliminar", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            GestorArchivos.eliminar(ruta);
            alEliminar.forEach(a -> a.accept(ruta));
            refrescar();
        } catch (IOException ex) {
            error("No se pudo eliminar", ex);
        }
    }

    private boolean nombreValido(String nombre) {
        if (nombre.isEmpty() || nombre.contains("/") || nombre.contains("\\")) {
            JOptionPane.showMessageDialog(this, "Nombre inválido.", "Nombre", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void error(String mensaje, IOException ex) {
        JOptionPane.showMessageDialog(this, mensaje + ":\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    //Icono de carpeta tambien para carpetas vacias
    private static class RenderizadorArchivos extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree arbol, Object valor, boolean seleccionado,
                boolean expandido, boolean hoja, int fila, boolean foco) {
            super.getTreeCellRendererComponent(arbol, valor, seleccionado, expandido, hoja, fila, foco);
            Object nodo = ((DefaultMutableTreeNode) valor).getUserObject();
            if (nodo instanceof Nodo n) {
                if (Files.isDirectory(n.ruta())) {
                    setIcon(expandido ? getOpenIcon() : getClosedIcon());
                } else {
                    Iconos.paraArchivo(n.toString()).ifPresent(this::setIcon); // .pig, .y, .z
                }
            }
            return this;
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
