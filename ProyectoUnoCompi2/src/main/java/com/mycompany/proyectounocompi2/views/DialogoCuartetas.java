package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.cuartetas.Cuarteta;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.TablaSoloLectura;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

// Muestra las cuartetas (operador, argumento 1, argumento 2, resultado) del ultimo programa compilado
public class DialogoCuartetas extends JDialog {

    private static final String[] COLUMNAS = {"#", "Operador", "Argumento 1", "Argumento 2", "Resultado"};

    public DialogoCuartetas(Frame propietario, List<Cuarteta> cuartetas) {
        super(propietario, "Cuartetas", false);
        List<Object[]> filas = new ArrayList<>();
        for (int i = 0; i < cuartetas.size(); i++) {
            Cuarteta cuarteta = cuartetas.get(i);
            filas.add(new Object[]{i + 1, cuarteta.operador(), cuarteta.argumento1(), cuarteta.argumento2(),
                cuarteta.resultado()});
        }
        TablaSoloLectura tabla = new TablaSoloLectura(COLUMNAS, filas);
        tabla.setFont(Estilos.FUENTE_CODIGO);
        tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        setLayout(new BorderLayout());
        add(Estilos.encabezado("Cuartetas", Estilos.ENCABEZADO_CODIGO, new JLabel(cuartetas.size() + " cuarteta(s)")),
                BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        setSize(800, 600);
        setLocationRelativeTo(propietario);
    }
}
