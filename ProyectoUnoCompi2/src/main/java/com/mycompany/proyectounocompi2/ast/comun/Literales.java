package com.mycompany.proyectounocompi2.ast.comun;

// Convierte el texto de un literal en su valor
public final class Literales {

    private Literales() {
    }

    
    public static Long entero(String texto) {
        try {
            return Long.parseLong(texto);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double decimal(String texto) {
        return Double.parseDouble(texto);
    }

    
    public static String cadena(String texto) {
        return quitarEscapes(texto.substring(1, texto.length() - 1));
    }

    
    public static Character caracter(String texto) {
        return quitarEscapes(texto.substring(1, texto.length() - 1)).charAt(0);
    }

    private static String quitarEscapes(String texto) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '\\' && i + 1 < texto.length()) {
                char siguiente = texto.charAt(++i);
                sb.append(switch (siguiente) {
                    case 'n' -> '\n';
                    case 't' -> '\t';
                    case 'r' -> '\r';
                    case '0' -> '\0';
                    default -> siguiente; 
                });
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
