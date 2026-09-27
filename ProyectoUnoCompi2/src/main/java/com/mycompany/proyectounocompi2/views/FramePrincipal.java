package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.analizador.Analizador;
import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.analizador.ResultadoAnalisis;
import com.mycompany.proyectounocompi2.archivos.GestorArchivos;
import com.mycompany.proyectounocompi2.analizador.CargadorPrograma;
import com.mycompany.proyectounocompi2.analizador.ProgramaCargado;
import com.mycompany.proyectounocompi2.cuartetas.Cuarteta;
import com.mycompany.proyectounocompi2.cuartetas.GeneracionException;
import com.mycompany.proyectounocompi2.cuartetas.GeneradorCuartetas;
import com.mycompany.proyectounocompi2.cuartetas.TraductorC;
import com.mycompany.proyectounocompi2.cuartetas.TraductorC3D;
import com.mycompany.proyectounocompi2.analizador.AnalizadorSemantico;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import com.mycompany.proyectounocompi2.views.componentes.PestanaEditor;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

// Ventana principal del IDE.
//
// @author xavi
public class FramePrincipal extends javax.swing.JFrame {

    private final ArbolDeProyectoPanel arbolProyecto = new ArbolDeProyectoPanel();
    private final EditorDeTexctoPanel editor = new EditorDeTexctoPanel();
    private final ErroresPanel errores = new ErroresPanel();
    private final CodigoCPanel codigoC = new CodigoCPanel();
    private ProgramaCargado ultimoPrograma;     // null hasta compilar algo
    private List<Cuarteta> ultimasCuartetas;    // null si el ultimo programa tuvo errores o no es un .pig
    private List<String> ultimoC3D;             // codigo de tres direcciones de esas cuartetas

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

        JMenuItem compilar = new JMenuItem("Compilar proyecto");
        compilar.addActionListener(e -> compilar());
        jMenu2.add(compilar);
    }

    // Botones del panel derecho, todos del mismo tamano, uno debajo de otro
    private void configurarBotones() {
        JButton compatibilidad = new JButton("Ver tabla de compatibilidad");
        compatibilidad.addActionListener(e -> new DialogoTablaCompatibilidad(this).setVisible(true));
        JButton verC3D = new JButton("Ver C3D");
        jToggleButton2.setText("Ver tabla de símbolos");
        jToggleButton3.setText("Ver tabla de tipos");
        jButton2.setText("Ver árboles AST");
        jButton1.setText("Ver cuartetas");

        jPanel4.removeAll();
        jPanel4.setLayout(new GridLayout(0, 1, 0, 8));
        jPanel4.setBorder(BorderFactory.createCompoundBorder(jPanel4.getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        for (AbstractButton boton : new AbstractButton[]{jToggleButton2, jToggleButton3, compatibilidad, jButton2,
            jButton1, verC3D}) {
            boton.setPreferredSize(new Dimension(0, 34));
            jPanel4.add(boton);
        }

        // Estos muestran lo del ultimo programa compilado
        jToggleButton2.addActionListener(e -> {
            jToggleButton2.setSelected(false);
            mostrar(programa -> new DialogoTablaSimbolos(this, programa.tablas().simbolos()));
        });
        jToggleButton3.addActionListener(e -> {
            jToggleButton3.setSelected(false);
            mostrar(programa -> new DialogoTablaTipos(this, programa.tablas().tipos()));
        });
        jButton1.addActionListener(e -> {
            if (hayCodigo("sus cuartetas")) {
                new DialogoCuartetas(this, ultimasCuartetas).setVisible(true);
            }
        });
        verC3D.addActionListener(e -> {
            if (hayCodigo("su código de tres direcciones")) {
                new DialogoC3D(this, ultimoC3D).setVisible(true);
            }
        });
        jButton2.addActionListener(e -> mostrar(programa -> new DialogoArbolesAst(this, programa.archivos())));
    }

    // Las cuartetas y el C3D solo existen si el ultimo programa .pig compilo sin errores
    private boolean hayCodigo(String que) {
        if (ultimasCuartetas == null) {
            JOptionPane.showMessageDialog(this, "Compila un programa .pig sin errores para ver " + que + ".",
                    "Código intermedio", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    private void mostrar(Function<ProgramaCargado, JDialog> crearDialogo) {
        if (ultimoPrograma == null) {
            JOptionPane.showMessageDialog(this, "Primero compila el proyecto (Compilar > Compilar proyecto).",
                    "Sin compilar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        crearDialogo.apply(ultimoPrograma).setVisible(true);
    }

    private void conectarPaneles() {
        arbolProyecto.alAbrirArchivo(this::abrirEnEditor);
        arbolProyecto.alEliminar(editor::cerrarEliminados);
        codigoC.alGenerarArchivos(() -> arbolProyecto.getRaiz().ifPresent(raiz -> arbolProyecto.refrescar()));
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


    // Compila todo el programa: el .pig del proyecto con los archivos que importa,
    // sin importar que pestana este abierta. Si el proyecto tiene varios .pig se
    // usa el de la pestana actual o se pregunta cual. Sin proyecto abierto se
    // compila el archivo actual.
    private void compilar() {
        Optional<Path> raiz = arbolProyecto.getRaiz();
        if (raiz.isEmpty()) {
            compilarArchivoActual();
            return;
        }
        List<Path> programas;
        try (Stream<Path> archivos = Files.walk(raiz.get())) {
            programas = archivos.filter(archivo -> archivo.toString().endsWith(".pig")).sorted().toList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo leer el proyecto:\n" + e.getMessage(), "Compilar",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (programas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El proyecto no tiene un archivo .pig (el programa principal).",
                    "Compilar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Path programa = programas.get(0);
        if (programas.size() > 1) {
            Optional<Path> actual = editor.actual().map(PestanaEditor::getArchivo)
                    .filter(programas::contains);
            if (actual.isPresent()) {
                programa = actual.get();
            } else {
                Object[] opciones = programas.stream().map(archivo -> raiz.get().relativize(archivo)).toArray();
                Object elegido = JOptionPane.showInputDialog(this, "El proyecto tiene varios .pig, ¿cuál compilar?",
                        "Compilar", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (elegido == null) {
                    return;
                }
                programa = raiz.get().resolve(elegido.toString());
            }
        }
        compilarPrograma(raiz.get(), programa);
    }

    private void compilarArchivoActual() {
        Optional<PestanaEditor> actual = editor.actual();
        if (actual.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Abre un proyecto o un archivo para compilar.", "Compilar",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        PestanaEditor pestana = actual.get();
        Optional<Lenguaje> lenguaje = pestana.getLenguaje();
        if (lenguaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Guarda el archivo con extensión .pig, .y o .z para compilarlo.",
                    "Compilar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (lenguaje.get() == Lenguaje.PIG_LATIN) {
            compilarPrograma(pestana.getArchivo().getParent(), pestana.getArchivo());
            return;
        }
        ResultadoAnalisis resultado = Analizador.analizar(pestana.getContenido(), lenguaje.get(), pestana.getNombre());
        Tablas tablas = AnalizadorSemantico.analizar(List.of(resultado));
        ultimoPrograma = new ProgramaCargado(resultado, Map.of(), tablas);
        errores.setErrores(ultimoPrograma.errores());
        // un .y o .z solo no es un programa completo: no se genera codigo
        ultimasCuartetas = null;
        codigoC.setCodigo(null, null);
    }

    // Un .pig se compila junto con los archivos .y / .z que importa (rutas relativas a la raiz)
    private void compilarPrograma(Path raiz, Path programa) {
        // Los archivos abiertos se leen del editor (aunque no esten guardados)
        CargadorPrograma cargador = new CargadorPrograma(raiz, archivo -> editor.buscar(archivo)
                .map(PestanaEditor::getContenido)
                .orElse(GestorArchivos.leer(archivo)));
        try {
            ultimoPrograma = cargador.cargar(programa);
            errores.setErrores(ultimoPrograma.errores());
            generarCodigo(programa.getParent().resolve("salida"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo leer el programa:\n" + e.getMessage(), "Compilar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Sin errores: cuartetas -> codigo de tres direcciones -> codigo C (se compila en 'salida' junto al .pig)
    private void generarCodigo(Path carpetaSalida) {
        ultimasCuartetas = null;
        codigoC.setCodigo(null, null);
        if (!ultimoPrograma.errores().isEmpty()) {
            return;
        }
        try {
            ultimasCuartetas = GeneradorCuartetas.generar(ultimoPrograma);
            ultimoC3D = TraductorC3D.traducir(ultimasCuartetas);
            codigoC.setCodigo(TraductorC.traducir(ultimoC3D), carpetaSalida);
        } catch (GeneracionException e) {
            JOptionPane.showMessageDialog(this, "No se pudo generar el código:\n" + e.getMessage(),
                    "Generar código", JOptionPane.ERROR_MESSAGE);
        }
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

    // @param args the command line arguments
    public static void main(String args[]) {
        // Set the Nimbus look and feel
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        // If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        // For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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
