package com.mycompany.proyectounocompi2.cuartetas;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Convierte el codigo de tres direcciones en un programa C:
public final class TraductorC {

    private static final String ENCABEZADO = """
            #include <stdio.h>
            #include <stdlib.h>
            #include <string.h>

            #define TAM_STACK 100000
            #define TAM_HEAP 2000000

            double stack[TAM_STACK];    // un marco por llamada: variables y parametros
            double heap[TAM_HEAP];      // cadenas, arreglos, estructuras y objetos
            int P = 0;                  // inicio del marco actual
            int H = 1;                  // primera celda libre del heap (heap[0] = 0: cadena vacia / null)
            double RET = 0;             // valor de retorno de la ultima llamada

            // ================= Funciones del sistema =================

            // Guarda un texto de C en el heap (un byte por celda y un 0 al final)
            double cadena_desde(const char *texto) {
                int inicio = H;
                for (; *texto; texto++) {
                    heap[H++] = (unsigned char) *texto;
                }
                heap[H++] = 0;
                return inicio;
            }

            // Copia una cadena del heap a un texto de C
            void a_texto(double cadena, char *texto, int maximo) {
                int i = 0;
                for (int c = (int) cadena; heap[c] != 0 && i < maximo - 1; c++) {
                    texto[i++] = (char) (int) heap[c];
                }
                texto[i] = 0;
            }

            // Un caracter (codigo Unicode) en UTF-8
            void utf8(int c, char *texto) {
                if (c < 0x80) {
                    texto[0] = c; texto[1] = 0;
                } else if (c < 0x800) {
                    texto[0] = 0xC0 | (c >> 6); texto[1] = 0x80 | (c & 0x3F); texto[2] = 0;
                } else {
                    texto[0] = 0xE0 | (c >> 12); texto[1] = 0x80 | ((c >> 6) & 0x3F); texto[2] = 0x80 | (c & 0x3F);
                    texto[3] = 0;
                }
            }

            // Decimales como en Java: 8.5, 2.0
            void formato_decimal(double d, char *texto) {
                snprintf(texto, 64, "%.10g", d);
                if (!strpbrk(texto, ".eni")) {
                    strcat(texto, ".0");
                }
            }

            void imprimir_cadena(double cadena) {
                for (int c = (int) cadena; heap[c] != 0; c++) {
                    putchar((int) heap[c]);
                }
            }

            void imprimir_caracter(double c) {
                char texto[8];
                utf8((int) c, texto);
                printf("%s", texto);
            }

            void imprimir_decimal(double d) {
                char texto[64];
                formato_decimal(d, texto);
                printf("%s", texto);
            }

            double concat(double a, double b) {
                int inicio = H;
                for (int c = (int) a; heap[c] != 0; c++) heap[H++] = heap[c];
                for (int c = (int) b; heap[c] != 0; c++) heap[H++] = heap[c];
                heap[H++] = 0;
                return inicio;
            }

            double cadenas_iguales(double a, double b) {
                int i = (int) a, j = (int) b;
                while (heap[i] != 0 && heap[i] == heap[j]) {
                    i++;
                    j++;
                }
                return heap[i] == heap[j];
            }

            double entero_a_cadena(double v) {
                char texto[32];
                snprintf(texto, sizeof texto, "%d", (int) v);
                return cadena_desde(texto);
            }

            double decimal_a_cadena(double v) {
                char texto[64];
                formato_decimal(v, texto);
                return cadena_desde(texto);
            }

            double caracter_a_cadena(double v) {
                char texto[8];
                utf8((int) v, texto);
                return cadena_desde(texto);
            }

            // Lee una linea de la consola y la guarda en el heap
            double leer(void) {
                char texto[1024];
                fflush(stdout);
                if (!fgets(texto, sizeof texto, stdin)) {
                    texto[0] = 0;
                }
                texto[strcspn(texto, "\\r\\n")] = 0;
                return cadena_desde(texto);
            }

            double cadena_a_entero(double cadena) {
                char texto[1024];
                a_texto(cadena, texto, sizeof texto);
                return atoi(texto);
            }

            double cadena_a_decimal(double cadena) {
                char texto[1024];
                a_texto(cadena, texto, sizeof texto);
                return atof(texto);
            }

            double cadena_a_caracter(double cadena) {
                int c = (int) cadena;
                int b = (int) heap[c];
                if (b < 0x80) return b;
                if (b < 0xE0) return ((b & 0x1F) << 6) | ((int) heap[c + 1] & 0x3F);
                return ((b & 0x0F) << 12) | (((int) heap[c + 1] & 0x3F) << 6) | ((int) heap[c + 2] & 0x3F);
            }

            double cadena_a_booleano(double cadena) {
                char texto[1024];
                a_texto(cadena, texto, sizeof texto);
                return strcmp(texto, "verum") == 0 || strcmp(texto, "verdadero") == 0
                        || strcmp(texto, "true") == 0 || strcmp(texto, "1") == 0;
            }

            // ================= Programa =================
            """;

    private static final Pattern PIEZA = Pattern.compile(
            "\"(?:\\\\.|[^\"\\\\])*\"|\\(int\\)|-?\\d+(?:\\.\\d+)?(?:[eE][-+]?\\d+)?|\\w+|==|!=|<=|>=|\\S");
    private static final Pattern TEMPORAL = Pattern.compile("t\\d+");

    private TraductorC() {
    }

    public static String traducir(List<String> c3d) {
        StringBuilder c = new StringBuilder(ENCABEZADO).append('\n');
        for (String linea : c3d) {
            List<String> piezas = piezas(linea);
            if (piezas.get(0).equals("func") && !piezas.get(1).equals("main")) {
                c.append("void ").append(piezas.get(1)).append("(void);\n");
            }
        }
        for (int i = 0; i < c3d.size(); i++) {
            List<String> piezas = piezas(c3d.get(i));
            if (piezas.get(0).equals("func")) {
                String nombre = piezas.get(1);
                c.append('\n').append(nombre.equals("main") ? "int main(void) {\n" : "void " + nombre + "(void) {\n");
                Set<String> temporales = temporales(c3d, i);
                if (!temporales.isEmpty()) {
                    c.append("    double ").append(String.join(", ", temporales)).append(";\n");
                }
            } else if (piezas.get(0).equals("end")) {
                c.append("}\n");
            } else if (piezas.size() == 2 && piezas.get(1).equals(":")) {
                // el ';' permite una etiqueta justo antes de '}'
                c.append(piezas.get(0)).append(":;\n");
            } else {
                c.append("    ").append(instruccion(piezas)).append('\n');
            }
        }
        return c.toString();
    }

    private static List<String> piezas(String linea) {
        List<String> piezas = new ArrayList<>();
        Matcher pieza = PIEZA.matcher(linea);
        while (pieza.find()) {
            piezas.add(pieza.group());
        }
        return piezas;
    }

    // Temporales usados desde la linea 'inicio' hasta su end
    private static Set<String> temporales(List<String> c3d, int inicio) {
        Set<String> temporales = new TreeSet<>((a, b) -> Integer.compare(numero(a), numero(b)));
        for (int i = inicio + 1; i < c3d.size() && !c3d.get(i).trim().equals("end"); i++) {
            for (String pieza : piezas(c3d.get(i))) {
                if (TEMPORAL.matcher(pieza).matches()) {
                    temporales.add(pieza);
                }
            }
        }
        return temporales;
    }

    private static int numero(String temporal) {
        return Integer.parseInt(temporal.substring(1));
    }

    // Una instruccion de C3D en C
    private static String instruccion(List<String> p) {
        switch (p.get(0)) {
            case "halt":
                return "return 0;";
            case "goto":
                return "goto " + p.get(1) + ";";
            case "call":
                return p.get(1) + "();";
            case "if":
                // if a op b goto L
                return "if (" + operando(p.get(1)) + " " + p.get(2) + " " + operando(p.get(3)) + ") goto " + p.get(5) + ";";
            case "print":
                return imprimir(p.get(1), p.get(2));
            default:
                return asignacion(p);
        }
    }

    private static String imprimir(String tipo, String valor) {
        if (valor.startsWith("\"")) {
            
            return "printf(\"%s\", " + valor + ");";
        }
        return switch (tipo) {
            case "entero" -> "printf(\"%d\", (int) " + valor + ");";
            case "decimal" -> "imprimir_decimal(" + valor + ");";
            case "caracter" -> "imprimir_caracter(" + valor + ");";
            default -> "imprimir_cadena(" + valor + ");";
        };
    }

    // los indices de stack/heap y los operandos de % se convierten a int
    private static String asignacion(List<String> p) {
        int igual = p.indexOf("=");
        String destino = expresion(p.subList(0, igual));
        List<String> derecha = p.subList(igual + 1, p.size());
        if (derecha.size() == 3 && derecha.get(1).equals("%")) {
            return destino + " = (int) " + operando(derecha.get(0)) + " % (int) " + operando(derecha.get(2)) + ";";
        }
        return destino + " = " + expresion(derecha) + ";";
    }

    // Formas: a | a op b | -a | !a | (int) a | stack[a + n] | heap[a + n] | funcion(a, b)
    private static String expresion(List<String> p) {
        List<String> partes = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            if ((p.get(i).equals("stack") || p.get(i).equals("heap")) && i + 1 < p.size() && p.get(i + 1).equals("[")) {
                int cierre = p.indexOf("]");
                String indice = String.join(" ", p.subList(i + 2, cierre));
                partes.add(p.get(i) + "[(int) " + (cierre - i - 2 == 1 ? indice : "(" + indice + ")") + "]");
                i = cierre;
            } else {
                partes.add(operando(p.get(i)));
            }
        }
        if (partes.size() > 1 && partes.get(1).equals("(")) {
            List<String> argumentos = partes.subList(2, partes.size() - 1).stream().filter(a -> !a.equals(",")).toList();
            return partes.get(0) + "(" + String.join(", ", argumentos) + ")";
        }
        if (partes.size() == 2 && (partes.get(0).equals("-") || partes.get(0).equals("!"))) {
            return partes.get(0) + partes.get(1);
        }
        return String.join(" ", partes);
    }

    // Una cadena literal se copia al heap cuando se usa como valor
    private static String operando(String pieza) {
        return pieza.startsWith("\"") ? "cadena_desde(" + pieza + ")" : pieza;
    }
}
