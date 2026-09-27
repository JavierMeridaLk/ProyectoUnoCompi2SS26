package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import com.mycompany.proyectounocompi2.tablas.TablaCompatibilidad;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import com.mycompany.proyectounocompi2.views.componentes.TablaSoloLectura;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import static com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo.*;

// Muestra la tabla de compatibilidad de tipos (la misma que usa el analisis
// semantico) en una sola tabla: una fila por cada par de tipos y una columna
// por operador, con los nombres de los tipos del lenguaje elegido.
public class DialogoTablaCompatibilidad extends JDialog {

    private static final TipoPrimitivo[] TIPOS = {ENTERO, DECIMAL, CADENA, CARACTER, BOOLEANO};

    // Operadores con las mismas reglas van en la misma columna
    private static final OperadorBinario[] OPERADORES = {OperadorBinario.SUMA, OperadorBinario.RESTA,
        OperadorBinario.MODULO, OperadorBinario.MENOR_QUE, OperadorBinario.IGUAL_QUE, OperadorBinario.Y_LOGICO};
    private static final String[] COLUMNAS = {"Izquierdo", "Derecho", "+", "- * /", "%", "< > <= >=", "== !=",
        "&& ||", "Asignar"};

    private final JComboBox<String> lenguajes = new JComboBox<>();
    private final JScrollPane contenedor = new JScrollPane();
    private final JLabel unarios = new JLabel();

    public DialogoTablaCompatibilidad(Frame propietario) {
        super(propietario, "Tabla de compatibilidad de tipos", false);
        for (Lenguaje lenguaje : Lenguaje.values()) {
            lenguajes.addItem(lenguaje.getNombre());
        }
        lenguajes.addActionListener(e -> llenar());

        JPanel opciones = new JPanel(new BorderLayout(8, 0));
        opciones.add(new JLabel("Lenguaje:"), BorderLayout.WEST);
        opciones.add(lenguajes, BorderLayout.CENTER);

        JLabel nota = new JLabel("<html>Cada celda es el tipo del resultado de <i>izquierdo operador derecho</i> "
                + "(<b>error</b> si no se permite). <b>Asignar</b>: si una variable del tipo izquierdo puede recibir "
                + "un valor del tipo derecho. Las reglas son las mismas en los tres lenguajes; solo cambian los "
                + "nombres de los tipos.</html>");
        nota.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        unarios.setBorder(BorderFactory.createEmptyBorder(6, 10, 8, 10));

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(Estilos.encabezado("Tabla de compatibilidad de tipos", Estilos.ENCABEZADO_EDITOR, opciones),
                BorderLayout.NORTH);
        norte.add(nota, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(norte, BorderLayout.NORTH);
        add(contenedor, BorderLayout.CENTER);
        add(unarios, BorderLayout.SOUTH);
        llenar();
        setSize(980, 780);
        setLocationRelativeTo(propietario);
    }

    private void llenar() {
        List<Object[]> filas = new ArrayList<>();
        for (TipoPrimitivo izquierda : TIPOS) {
            for (TipoPrimitivo derecha : TIPOS) {
                Object[] fila = new Object[COLUMNAS.length];
                fila[0] = nombre(izquierda);
                fila[1] = nombre(derecha);
                for (int i = 0; i < OPERADORES.length; i++) {
                    fila[i + 2] = resultado(TablaCompatibilidad.resultado(OPERADORES[i], izquierda, derecha));
                }
                fila[COLUMNAS.length - 1] = asignacion(izquierda, derecha);
                filas.add(fila);
            }
        }
        contenedor.setViewportView(new TablaSoloLectura(COLUMNAS, filas));
        unarios.setText("<html><b>Un solo operando:</b>&nbsp; " + unario(OperadorUnario.NEGATIVO, "-x") + "&nbsp;&nbsp;|&nbsp;&nbsp;"
                + unario(OperadorUnario.NEGACION, "!x / non x") + "&nbsp;&nbsp;|&nbsp;&nbsp;"
                + unario(OperadorUnario.PRE_INCREMENTO, "++ --") + "</html>");
    }

    // Una variable del tipo izquierdo puede recibir un valor del tipo derecho
    private static String asignacion(TipoPrimitivo variable, TipoPrimitivo valor) {
        if (variable == valor) {
            return "sí";
        }
        return TablaCompatibilidad.esAsignable(Tipo.primitivo(variable), Tipo.primitivo(valor)) ? "sí (implícita)"
                : "error";
    }

    // -x: numerus -> numerus, decimalis -> decimalis...
    private String unario(OperadorUnario operador, String como) {
        List<String> validos = new ArrayList<>();
        for (TipoPrimitivo operando : TIPOS) {
            TipoPrimitivo resultado = TablaCompatibilidad.resultado(operador, operando);
            if (resultado != null) {
                validos.add(nombre(operando) + " → " + nombre(resultado));
            }
        }
        return "<b>" + como + "</b>: " + String.join(", ", validos);
    }

    private String resultado(TipoPrimitivo tipo) {
        return tipo != null ? nombre(tipo) : "error";
    }

    private String nombre(TipoPrimitivo tipo) {
        return Lenguaje.values()[lenguajes.getSelectedIndex()].nombreDe(tipo);
    }
}
