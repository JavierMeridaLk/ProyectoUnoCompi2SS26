package com.mycompany.proyectounocompi2.formato;

import com.mycompany.proyectounocompi2.analisis.Lenguaje;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Re-tabula el codigo fuente (4 espacios por nivel).
 *
 * - Pig Latin y Zetariano: el nivel lo dan las llaves { }.
 * - Y?: la indentacion ES la estructura, asi que no se inventan niveles:
 *   se respetan los bloques que ya existen (igual que el lexer) y solo se
 *   normalizan a 4 espacios por nivel (tabs incluidos).
 *
 * Nunca se modifica el contenido de cadenas, caracteres ni comentarios de bloque.
 */
public final class FormateadorCodigo {

    private static final String SANGRIA = "    ";
    private static final int TAB = 4;

    private FormateadorCodigo() {
    }

    /** Error al formatear (p. ej. indentacion inconsistente en Y). */
    public static class ErrorFormato extends Exception {
        public ErrorFormato(String mensaje) {
            super(mensaje);
        }
    }

    public static String formatear(Lenguaje lenguaje, String codigo) throws ErrorFormato {
        String salto = codigo.contains("\r\n") ? "\r\n" : "\n";
        String[] lineas = codigo.split("\r?\n", -1);
        List<String> resultado = lenguaje == Lenguaje.Y
                ? formatearY(lineas)
                : formatearConLlaves(lineas, lenguaje == Lenguaje.PIG_LATIN ? "##" : "/*",
                        lenguaje == Lenguaje.PIG_LATIN ? "##" : "*/");
        return String.join(salto, resultado);
    }

    // =====================================================================
    //  Pig Latin y Zetariano
    // =====================================================================

    private static List<String> formatearConLlaves(String[] lineas, String abreComentario, String cierraComentario) {
        List<String> resultado = new ArrayList<>();
        Escaner escaner = new Escaner(abreComentario, cierraComentario);
        int nivel = 0;
        for (String linea : lineas) {
            if (escaner.enComentario) {
                resultado.add(quitarEspaciosFinales(linea)); // dentro de un comentario de bloque: tal cual
                nivel = Math.max(0, nivel + escaner.recorrer(linea));
                continue;
            }
            String texto = linea.strip();
            if (texto.isEmpty()) {
                resultado.add("");
                continue;
            }
            // "} aliter {" o "} else {" se escriben un nivel antes
            int nivelLinea = texto.startsWith("}") ? Math.max(0, nivel - 1) : nivel;
            resultado.add(SANGRIA.repeat(nivelLinea) + texto);
            nivel = Math.max(0, nivel + escaner.recorrer(texto));
        }
        return resultado;
    }

    // =====================================================================
    //  Y?
    // =====================================================================

    private record Pendiente(String texto, boolean ajustar) {
    }

    private static List<String> formatearY(String[] lineas) throws ErrorFormato {
        List<String> resultado = new ArrayList<>();
        List<Pendiente> comentarios = new ArrayList<>(); // toman el nivel de la siguiente linea de codigo
        Deque<Integer> indentaciones = new ArrayDeque<>();
        indentaciones.push(0);
        Escaner escaner = new Escaner("/*", "*/");
        int agrupaciones = 0; // [ { abiertos: las lineas de continuacion no cuentan como bloques

        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i];
            String texto = linea.strip();
            if (escaner.enComentario) {
                comentarios.add(new Pendiente(quitarEspaciosFinales(linea), false));
                escaner.recorrer(linea);
                continue;
            }
            if (texto.isEmpty()) {
                comentarios.add(new Pendiente("", false));
                continue;
            }
            if (agrupaciones > 0) {
                boolean cierra = texto.startsWith("}") || texto.startsWith("]");
                int nivel = indentaciones.size() - 1 + (cierra ? 0 : 1);
                vaciar(comentarios, nivel, resultado);
                resultado.add(SANGRIA.repeat(nivel) + texto);
                agrupaciones = Math.max(0, agrupaciones + escaner.recorrerAgrupaciones(texto));
                continue;
            }
            if (esSoloComentario(texto)) {
                comentarios.add(new Pendiente(texto, true));
                escaner.recorrer(texto);
                continue;
            }

            int ancho = anchoIndentacion(linea);
            if (ancho > indentaciones.peek()) {
                indentaciones.push(ancho);
            } else {
                while (indentaciones.peek() > ancho) {
                    indentaciones.pop();
                }
                if (indentaciones.peek() != ancho) {
                    throw new ErrorFormato("Indentación inconsistente en la línea " + (i + 1)
                            + ": no coincide con ningún bloque abierto.");
                }
            }
            int nivel = indentaciones.size() - 1;
            vaciar(comentarios, nivel, resultado);
            resultado.add(SANGRIA.repeat(nivel) + texto);
            agrupaciones = Math.max(0, agrupaciones + escaner.recorrerAgrupaciones(texto));
        }
        vaciar(comentarios, 0, resultado);
        return resultado;
    }

    private static void vaciar(List<Pendiente> pendientes, int nivel, List<String> resultado) {
        for (Pendiente p : pendientes) {
            resultado.add(p.ajustar() ? SANGRIA.repeat(nivel) + p.texto() : p.texto());
        }
        pendientes.clear();
    }

    /** "// ..." o "/* ... *\/" sin codigo despues (como lo decide el lexer de Y). */
    private static boolean esSoloComentario(String texto) {
        if (texto.startsWith("//")) {
            return true;
        }
        if (texto.startsWith("/*")) {
            int fin = texto.indexOf("*/", 2);
            return fin < 0 || texto.substring(fin + 2).isBlank() || texto.substring(fin + 2).strip().startsWith("//");
        }
        return false;
    }

    /** Ancho de la indentacion: un tab avanza al siguiente multiplo de 4 (igual que el lexer). */
    private static int anchoIndentacion(String linea) {
        int columnas = 0;
        for (char c : linea.toCharArray()) {
            if (c == ' ') {
                columnas++;
            } else if (c == '\t') {
                columnas += TAB - (columnas % TAB);
            } else {
                break;
            }
        }
        return columnas;
    }

    private static String quitarEspaciosFinales(String linea) {
        return linea.stripTrailing();
    }

    // =====================================================================

    /**
     * Recorre el codigo contando llaves/corchetes fuera de cadenas, caracteres
     * y comentarios. Recuerda si una linea termina dentro de un comentario de bloque.
     */
    private static final class Escaner {
        private final String abre;
        private final String cierra;
        boolean enComentario;

        Escaner(String abre, String cierra) {
            this.abre = abre;
            this.cierra = cierra;
        }

        /** Balance de { } en la linea. */
        int recorrer(String linea) {
            return contar(linea, false);
        }

        /** Balance de { } y [ ] en la linea. */
        int recorrerAgrupaciones(String linea) {
            return contar(linea, true);
        }

        private int contar(String linea, boolean corchetes) {
            int balance = 0;
            int i = 0;
            while (i < linea.length()) {
                if (enComentario) {
                    int fin = linea.indexOf(cierra, i);
                    if (fin < 0) {
                        return balance;
                    }
                    enComentario = false;
                    i = fin + cierra.length();
                    continue;
                }
                char c = linea.charAt(i);
                if (linea.startsWith("//", i)) {
                    return balance;
                }
                if (linea.startsWith(abre, i)) {
                    enComentario = true;
                    i += abre.length();
                    continue;
                }
                if (c == '"' || c == '\'') {
                    i = saltarLiteral(linea, i, c);
                    continue;
                }
                if (c == '{' || (corchetes && c == '[')) {
                    balance++;
                } else if (c == '}' || (corchetes && c == ']')) {
                    balance--;
                }
                i++;
            }
            return balance;
        }

        private static int saltarLiteral(String linea, int inicio, char comilla) {
            int i = inicio + 1;
            while (i < linea.length()) {
                char c = linea.charAt(i);
                if (c == '\\') {
                    i += 2;
                    continue;
                }
                if (c == comilla) {
                    return i + 1;
                }
                i++;
            }
            return linea.length(); // literal sin cerrar: hasta el final de la linea
        }
    }
}
