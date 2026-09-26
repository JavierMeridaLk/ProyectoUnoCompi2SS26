package com.mycompany.proyectounocompi2.views.componentes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Titulo de una pestaña
 */
public class EncabezadoPestana extends JPanel {

    public EncabezadoPestana(PestanaEditor pestana, Runnable alCerrar) {
        super(new FlowLayout(FlowLayout.LEFT, 4, 0));
        setOpaque(false);

        JLabel titulo = new JLabel();
        Runnable actualizar = () -> {
            titulo.setText((pestana.isModificado() ? "*" : "") + pestana.getNombre());
            titulo.setIcon(Iconos.paraArchivo(pestana.getNombre()).orElse(null));
            setToolTipText(pestana.getArchivo() != null ? pestana.getArchivo().toString() : "Archivo sin guardar");
        };
        actualizar.run();
        pestana.alCambiarEstado(actualizar);

        JButton cerrar = new JButton("×");
        cerrar.setToolTipText("Cerrar");
        cerrar.setFocusable(false);
        cerrar.setContentAreaFilled(false);
        cerrar.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 2));
        cerrar.setForeground(Color.GRAY);
        cerrar.addActionListener(e -> alCerrar.run());
        cerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cerrar.setForeground(new Color(200, 40, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cerrar.setForeground(Color.GRAY);
            }
        });

        add(titulo);
        add(cerrar);
    }
}
