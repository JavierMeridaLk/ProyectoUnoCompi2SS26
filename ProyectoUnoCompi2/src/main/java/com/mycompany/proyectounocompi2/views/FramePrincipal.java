/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.analisis.Analizador;
import com.mycompany.proyectounocompi2.analisis.Lenguaje;
import com.mycompany.proyectounocompi2.analisis.ResultadoAnalisis;
import com.mycompany.proyectounocompi2.views.componentes.PestanaEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Ventana principal del IDE.
 *
 * @author xavi
 */
public class FramePrincipal extends javax.swing.JFrame {

    private final ArbolDeProyectoPanel arbolProyecto = new ArbolDeProyectoPanel();
    private final EditorDeTexctoPanel editor = new EditorDeTexctoPanel();
    private final ErroresPanel errores = new ErroresPanel();
    private final CodigoCPanel codigoC = new CodigoCPanel();

    public FramePrincipal() {
        initComponents();
        this.setTitle("IDE XS1");

        colocar(jPanel1, arbolProyecto, new Dimension(280, 615));
        colocar(jPanel2, editor, new Dimension(1071, 615));
        colocar(jPanel3, errores, new Dimension(1370, 260));
        colocar(jPanel5, codigoC, new Dimension(420, 595));
        configurarMenus();
        configurarBotones();
        conectarPaneles();

        // Al cerrar la ventana se pregunta por los archivos sin guardar
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });

        this.setSize(1850, 1000);
        setLocationRelativeTo(null);
    }

    // Reemplaza el contenido vacio de un panel 
    private static void colocar(JPanel contenedor, JComponent contenido, Dimension tamano) {
        contenedor.removeAll();
        contenedor.setLayout(new BorderLayout());
        contenedor.add(contenido, BorderLayout.CENTER);
        contenedor.setPreferredSize(tamano);
    }

    //Todas las operaciones de archivos 
    private void configurarMenus() {
        jMenuItem5.addActionListener(e -> crearArchivo());

        JMenuItem crearCarpeta = new JMenuItem("Crear carpeta");
        crearCarpeta.addActionListener(e -> crearCarpeta());
        JMenuItem abrirArchivo = new JMenuItem("Abrir archivo");
        abrirArchivo.addActionListener(e -> abrirArchivo());
        JMenuItem descargar = new JMenuItem("Descargar seleccionado");
        descargar.addActionListener(e -> arbolProyecto.descargarSeleccionado());
        JMenuItem eliminar = new JMenuItem("Eliminar seleccionado");
        eliminar.addActionListener(e -> arbolProyecto.eliminarSeleccionado());
        JMenuItem cerrarPestana = new JMenuItem("Cerrar pestaña");
        cerrarPestana.addActionListener(e -> editor.cerrarActual());
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(e -> salir());

        jMenu1.insert(crearCarpeta, 2);   
        jMenu1.insert(abrirArchivo, 4);   
        jMenu1.addSeparator();
        jMenu1.add(descargar);
        jMenu1.add(eliminar);
        jMenu1.addSeparator();
        jMenu1.add(cerrarPestana);
        jMenu1.add(salir);

        JMenuItem analizar = new JMenuItem("Analizar archivo actual");
        analizar.addActionListener(e -> analizarActual());
        jMenu2.add(analizar);
    }

    //Botones del panel derecho se habilitaran conforme se implemente cada parte
    private void configurarBotones() {
        jToggleButton2.addActionListener(e -> pendiente(jToggleButton2, "La tabla de símbolos"));
        jToggleButton3.addActionListener(e -> pendiente(jToggleButton3, "La tabla de tipos"));
        jButton1.addActionListener(e -> pendiente(null, "La vista de cuartetas"));
        jButton2.addActionListener(e -> pendiente(null, "La vista de árboles AST"));
    }

    private void pendiente(javax.swing.JToggleButton boton, String que) {
        if (boton != null) {
            boton.setSelected(false);
        }
        JOptionPane.showMessageDialog(this, que + " estará disponible cuando se implemente esa fase.",
                "Pendiente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void conectarPaneles() {
        arbolProyecto.alAbrirArchivo(this::abrirEnEditor);
        arbolProyecto.alEliminar(editor::cerrarEliminados);
        editor.alGuardar(ruta -> {
            if (arbolProyecto.getRaiz().isPresent() && ruta.startsWith(arbolProyecto.getRaiz().get())) {
                arbolProyecto.refrescar();
            }
        });
    }

    // Acciones de archivos 

    private void abrirEnEditor(Path archivo) {
        try {
            editor.abrir(archivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir " + archivo.getFileName() + ":\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearProyecto() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("¿Dónde crear el proyecto?");
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (selector.showDialog(this, "Seleccionar") != JFileChooser.APPROVE_OPTION) {
            return;
        }
        String nombre = JOptionPane.showInputDialog(this, "Nombre del proyecto:", "Crear proyecto",
                JOptionPane.PLAIN_MESSAGE);
        if (nombre == null || nombre.isBlank()) {
            return;
        }
        Path carpeta = selector.getSelectedFile().toPath().resolve(nombre.trim());
        if (Files.exists(carpeta)) {
            JOptionPane.showMessageDialog(this, "Ya existe una carpeta con ese nombre.", "Crear proyecto",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Files.createDirectories(carpeta);
            arbolProyecto.abrirProyecto(carpeta);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo crear el proyecto:\n" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirProyecto() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Abrir proyecto (carpeta)");
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            arbolProyecto.abrirProyecto(selector.getSelectedFile().toPath());
        }
    }

    private void abrirArchivo() {
        JFileChooser selector = new JFileChooser();
        arbolProyecto.getRaiz().ifPresent(raiz -> selector.setCurrentDirectory(raiz.toFile()));
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Pig Latin, Y?, Zetariano (*.pig, *.y, *.z)", "pig", "y", "z"));
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            abrirEnEditor(selector.getSelectedFile().toPath());
        }
    }


    private void crearArchivo() {
        if (arbolProyecto.getRaiz().isPresent()) {
            arbolProyecto.crearArchivo();
        } else {
            editor.nuevaPestana();
        }
    }

    private void crearCarpeta() {
        if (arbolProyecto.getRaiz().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero abre o crea un proyecto.", "Crear carpeta",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        arbolProyecto.crearCarpeta();
    }

    private void salir() {
        if (editor.cerrarTodo()) {
            dispose();
            System.exit(0);
        }
    }

    // Analisis 


    private void analizarActual() {
        Optional<PestanaEditor> actual = editor.actual();
        if (actual.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ningún archivo abierto.", "Analizar",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        PestanaEditor pestana = actual.get();
        Optional<Lenguaje> lenguaje = pestana.getLenguaje();
        if (lenguaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Guarda el archivo con extensión .pig, .y o .z para analizarlo.",
                    "Analizar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        ResultadoAnalisis resultado = Analizador.analizar(pestana.getContenido(), lenguaje.get(), pestana.getNombre());
        errores.setErrores(resultado.errores());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1071, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 615, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jToggleButton2.setText("Ver tabla de simbolos");

        jToggleButton3.setText("Ver tabla de Tipos");

        jButton1.setText("Ver Cuartetas");

        jButton2.setText("Ver arboles AST");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Crear proyecto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem5.setText("Crear archivo");
        jMenu1.add(jMenuItem5);

        jMenuItem2.setText("Abrir proyecto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setText("Guardar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Guardar todo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Compilar");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        crearProyecto();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        abrirProyecto();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        editor.guardarTodo();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        editor.guardarActual();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Set the Nimbus look and feel
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration//GEN-END:variables
}
