package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.NumerosDeLinea;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// Muestra el codigo de tres direcciones del ultimo programa compilado (del que sale el codigo C)
public class DialogoC3D extends JDialog {

    public DialogoC3D(Frame propietario, List<String> c3d) {
        super(propietario, "Código de tres direcciones", false);
        JTextArea codigo = new JTextArea(String.join("\n", c3d));
        codigo.setEditable(false);
        codigo.setFont(Estilos.FUENTE_CODIGO);
        codigo.setCaretPosition(0);
        JScrollPane desplazamiento = new JScrollPane(codigo);
        desplazamiento.setRowHeaderView(new NumerosDeLinea(codigo));
        setLayout(new BorderLayout());
        add(Estilos.encabezado("Código de tres direcciones", Estilos.ENCABEZADO_CODIGO,
                new JLabel(c3d.size() + " instrucción(es)")), BorderLayout.NORTH);
        add(desplazamiento, BorderLayout.CENTER);
        setSize(700, 650);
        setLocationRelativeTo(propietario);
    }
}
