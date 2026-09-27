package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.tablas.Campo;
import com.mycompany.proyectounocompi2.tablas.TablaTipos;
import com.mycompany.proyectounocompi2.tablas.TipoDefinido;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.TablaSoloLectura;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

// Muestra la tabla de tipos del ultimo programa compilado: los primitivos y
// cada estructura/clase con una fila por atributo.
public class DialogoTablaTipos extends JDialog {

    private static final String[] COLUMNAS = {"Tipo", "Categoría", "Tamaño", "Archivo", "Atributo",
        "Tipo del atributo", "Desplazamiento"};

    public DialogoTablaTipos(Frame propietario, TablaTipos tabla) {
        super(propietario, "Tabla de tipos", false);
        List<Object[]> filas = new ArrayList<>();
        for (TipoDefinido tipo : tabla.todos()) {
            String archivo = tipo.getArchivo() != null ? tipo.getArchivo() : "-";
            if (tipo.getCampos().isEmpty()) {
                filas.add(new Object[]{tipo.getNombre(), tipo.getCategoria(), tipo.getTamano(), archivo, "-", "-", "-"});
            }
            for (Campo campo : tipo.getCampos()) {
                filas.add(new Object[]{tipo.getNombre(), tipo.getCategoria(), tipo.getTamano(), archivo,
                    campo.nombre(), campo.tipo(), campo.desplazamiento()});
            }
        }
        setLayout(new BorderLayout());
        add(Estilos.encabezado("Tabla de tipos", Estilos.ENCABEZADO_EDITOR,
                new JLabel(tabla.todos().size() + " tipo(s)")), BorderLayout.NORTH);
        add(new JScrollPane(new TablaSoloLectura(COLUMNAS, filas)), BorderLayout.CENTER);
        setSize(900, 480);
        setLocationRelativeTo(propietario);
    }
}
