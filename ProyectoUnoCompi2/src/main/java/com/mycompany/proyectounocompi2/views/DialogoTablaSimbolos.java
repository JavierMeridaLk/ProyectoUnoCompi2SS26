package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.tablas.CategoriaSimbolo;
import com.mycompany.proyectounocompi2.tablas.Simbolo;
import com.mycompany.proyectounocompi2.tablas.TablaSimbolos;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.TablaSoloLectura;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

// Muestra la tabla de simbolos del ultimo programa compilado: funciones,
// constructores, metodos, parametros y variables con su ambito y su lugar
// en memoria.
public class DialogoTablaSimbolos extends JDialog {

    private static final String[] COLUMNAS = {"Nombre", "Categoría", "Tipo", "Parámetros", "Ámbito", "Archivo",
        "Línea", "Desplazamiento", "Tamaño"};

    public DialogoTablaSimbolos(Frame propietario, TablaSimbolos tabla) {
        super(propietario, "Tabla de símbolos", false);
        List<Object[]> filas = new ArrayList<>();
        for (Simbolo simbolo : tabla.todos()) {
            boolean esVariable = simbolo.categoria() == CategoriaSimbolo.VARIABLE
                    || simbolo.categoria() == CategoriaSimbolo.PARAMETRO;
            filas.add(new Object[]{simbolo.nombre(), simbolo.categoria(), simbolo.tipo(),
                esVariable ? "-" : parametros(simbolo.parametros()), simbolo.ambito(), simbolo.archivo(),
                simbolo.posicion().linea(), esVariable ? simbolo.desplazamiento() : "-", simbolo.tamano()});
        }
        setLayout(new BorderLayout());
        add(Estilos.encabezado("Tabla de símbolos", Estilos.ENCABEZADO_EDITOR,
                new JLabel(filas.size() + " símbolo(s)")), BorderLayout.NORTH);
        add(new JScrollPane(new TablaSoloLectura(COLUMNAS, filas)), BorderLayout.CENTER);
        setSize(1100, 520);
        setLocationRelativeTo(propietario);
    }

    private static String parametros(List<Tipo> tipos) {
        List<String> nombres = new ArrayList<>();
        tipos.forEach(tipo -> nombres.add(tipo.toString()));
        return "(" + String.join(", ", nombres) + ")";
    }
}
