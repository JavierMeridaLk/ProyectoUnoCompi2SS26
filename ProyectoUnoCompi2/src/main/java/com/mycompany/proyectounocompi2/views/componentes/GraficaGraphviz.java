package com.mycompany.proyectounocompi2.views.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GraficaGraphviz extends JPanel {

    private final Lienzo lienzo = new Lienzo();

    public GraficaGraphviz(String dot) {
        setLayout(new BorderLayout());
        add(lienzo, BorderLayout.CENTER);

        JButton alejar = new JButton("-");
        alejar.addActionListener(e -> lienzo.zoom(new Point(lienzo.getWidth() / 2, lienzo.getHeight() / 2), 1 / 1.2));
        JButton ajustar = new JButton("Ajustar vista");
        ajustar.addActionListener(e -> lienzo.ajustar());
        JButton acercar = new JButton("+");
        acercar.addActionListener(e -> lienzo.zoom(new Point(lienzo.getWidth() / 2, lienzo.getHeight() / 2), 1.2));
        JButton exportar = new JButton("Exportar PNG");
        exportar.addActionListener(e -> exportar());
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 4));
        barra.add(alejar);
        barra.add(ajustar);
        barra.add(acercar);
        barra.add(exportar);
        add(barra, BorderLayout.SOUTH);

        new SwingWorker<BufferedImage, Void>() {
            @Override
            protected BufferedImage doInBackground() throws Exception {
                return dibujar(dot);
            }

            @Override
            protected void done() {
                try {
                    lienzo.imagen = get();
                } catch (Exception e) {
                    Throwable causa = e.getCause() != null ? e.getCause() : e;
                    lienzo.mensaje = "No se pudo generar la gráfica con Graphviz: " + causa.getMessage();
                }
                lienzo.ajustar();
            }
        }.execute();
    }

    private static BufferedImage dibujar(String dot) throws IOException, InterruptedException {
        Path archivoDot = Files.createTempFile("ast_", ".dot");
        Path archivoPng = Files.createTempFile("ast_", ".png");
        try {
            Files.writeString(archivoDot, dot);
            Process proceso = new ProcessBuilder("dot", "-Tpng", archivoDot.toString(), "-o", archivoPng.toString())
                    .redirectErrorStream(true).start();
            String salida = new String(proceso.getInputStream().readAllBytes());
            if (proceso.waitFor() != 0) {
                throw new IOException(salida);
            }
            BufferedImage imagen = ImageIO.read(archivoPng.toFile());
            if (imagen == null) {
                throw new IOException("Graphviz no produjo una imagen");
            }
            return imagen;
        } finally {
            Files.deleteIfExists(archivoDot);
            Files.deleteIfExists(archivoPng);
        }
    }

    private void exportar() {
        if (lienzo.imagen == null) {
            return;
        }
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Exportar como PNG");
        selector.setFileFilter(new FileNameExtensionFilter("Imagen PNG (*.png)", "png"));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File archivo = selector.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".png")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".png");
        }
        try {
            ImageIO.write(lienzo.imagen, "png", archivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo exportar la imagen:\n" + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Muestra la imagen con escala y desplazamiento
    private static class Lienzo extends JPanel {

        private BufferedImage imagen;
        private String mensaje = "Generando gráfica...";
        private double escala = 1;
        private double x;
        private double y;
        private Point inicioArrastre;

        Lienzo() {
            setBackground(Color.WHITE);
            MouseAdapter raton = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    inicioArrastre = e.getPoint();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    x += e.getX() - inicioArrastre.x;
                    y += e.getY() - inicioArrastre.y;
                    inicioArrastre = e.getPoint();
                    repaint();
                }

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    zoom(e.getPoint(), e.getWheelRotation() < 0 ? 1.12 : 1 / 1.12);
                }
            };
            addMouseListener(raton);
            addMouseMotionListener(raton);
            addMouseWheelListener(raton);
        }

        void ajustar() {
            if (imagen != null && getWidth() > 0) {
                escala = Math.min(1, Math.min((getWidth() - 20.0) / imagen.getWidth(),
                        (getHeight() - 20.0) / imagen.getHeight()));
                x = (getWidth() - imagen.getWidth() * escala) / 2;
                y = (getHeight() - imagen.getHeight() * escala) / 2;
            }
            repaint();
        }

        // Acerca o aleja dejando fijo el punto bajo el cursor
        void zoom(Point punto, double factor) {
            double nueva = Math.max(0.05, Math.min(5, escala * factor));
            x = punto.x - (punto.x - x) * nueva / escala;
            y = punto.y - (punto.y - y) * nueva / escala;
            escala = nueva;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen == null) {
                g.setColor(Color.GRAY);
                g.drawString(mensaje, 20, 30);
                return;
            }
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.translate(x, y);
            g2.scale(escala, escala);
            g2.drawImage(imagen, 0, 0, null);
            g2.dispose();
        }
    }
}
