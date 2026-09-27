package com.mycompany.proyectounocompi2.views;

import com.mycompany.proyectounocompi2.analizador.ResultadoAnalisis;
import com.mycompany.proyectounocompi2.visitors.piglatin.ArbolPigLatin;
import com.mycompany.proyectounocompi2.visitors.y.ArbolY;
import com.mycompany.proyectounocompi2.visitors.zetariano.ArbolZetariano;
import com.mycompany.proyectounocompi2.visitors.NodoArbol;
import com.mycompany.proyectounocompi2.ast.comun.AstRaiz;
import com.mycompany.proyectounocompi2.ast.piglatin.Programa;
import com.mycompany.proyectounocompi2.ast.zetariano.Clase;
import com.mycompany.proyectounocompi2.views.componentes.GraficaGraphviz;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

// Muestra el AST de cada archivo del ultimo programa compilado, dibujado con
// Graphviz, una pestana por archivo.
public class DialogoArbolesAst extends JDialog {

    public DialogoArbolesAst(Frame propietario, List<ResultadoAnalisis> archivos) {
        super(propietario, "Árboles AST", false);
        JTabbedPane pestanas = new JTabbedPane();
        for (ResultadoAnalisis archivo : archivos) {
            if (archivo.ast() == null) {
                pestanas.addTab(archivo.manejador().getArchivo(), new JLabel(
                        "No hay AST: el archivo tiene errores léxicos o sintácticos.", SwingConstants.CENTER));
            } else {
                pestanas.addTab(archivo.manejador().getArchivo(), new GraficaGraphviz(dot(arbol(archivo.ast()))));
            }
        }
        setLayout(new BorderLayout());
        add(pestanas, BorderLayout.CENTER);
        setSize(1100, 750);
        setLocationRelativeTo(propietario);
    }

    // Cada lenguaje tiene su visitor que arma el arbol
    private static NodoArbol arbol(AstRaiz ast) {
        if (ast instanceof Programa programa) {
            return programa.accept(new ArbolPigLatin());
        }
        if (ast instanceof Clase clase) {
            return clase.accept(new ArbolZetariano());
        }
        return ((com.mycompany.proyectounocompi2.ast.y.Programa) ast).accept(new ArbolY());
    }

    private static String dot(NodoArbol raiz) {
        StringBuilder dot = new StringBuilder("digraph AST {\n")
                .append("  node [shape=box, style=\"rounded,filled\", fontname=\"Helvetica\", fontsize=12];\n")
                .append("  edge [color=\"#969696\"];\n");
        emitir(raiz, dot, new int[1]);
        return dot.append("}\n").toString();
    }

    // Escribe el nodo y sus hijos en DOT y devuelve su id (azul: con hijos, verde: hoja)
    private static String emitir(NodoArbol nodo, StringBuilder dot, int[] siguiente) {
        String id = "n" + siguiente[0]++;
        String colores = nodo.hijos().isEmpty() ? "fillcolor=\"#E0F5E4\", color=\"#5AA064\""
                : "fillcolor=\"#DEEAFA\", color=\"#5A78B4\"";
        dot.append("  ").append(id).append(" [label=\"").append(escapar(nodo.etiqueta())).append("\", ")
                .append(colores).append("];\n");
        for (NodoArbol hijo : nodo.hijos()) {
            String idHijo = emitir(hijo, dot, siguiente);
            dot.append("  ").append(id).append(" -> ").append(idHijo).append(";\n");
        }
        return id;
    }

    private static String escapar(String texto) {
        return texto.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
