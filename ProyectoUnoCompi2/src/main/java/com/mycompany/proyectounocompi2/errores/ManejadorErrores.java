package com.mycompany.proyectounocompi2.errores;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.antlr.v4.runtime.Token;

/**
 * Acumula los errores lexicos y sintacticos de un archivo.
 */
public class ManejadorErrores {

    private final String archivo;
    private final List<ErrorCompilacion> errores = new ArrayList<>();

    public ManejadorErrores(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void agregar(TipoError tipo, int linea, int columna, String lexema, String descripcion) {
        errores.add(new ErrorCompilacion(tipo, archivo, linea, columna, lexema, descripcion));
    }

    // Agrega un error ubicado en un token (la columna de ANTLR empieza en 0).
    public void agregar(TipoError tipo, Token token, String descripcion) {
        agregar(tipo, token.getLine(), token.getCharPositionInLine() + 1, recortar(token.getText()), descripcion);
    }

    //  Lexemas largos se recortan para el reporte
    private static String recortar(String lexema) {
        if (lexema == null) {
            return null;
        }
        String primeraLinea = lexema.lines().findFirst().orElse("");
        return primeraLinea.length() > 30 ? primeraLinea.substring(0, 30) + "..." : primeraLinea;
    }

    public void lexico(Token token, String descripcion) {
        agregar(TipoError.LEXICO, token, descripcion);
    }

    //  Errores ordenados por posicion en el archivo. 
    public List<ErrorCompilacion> getErrores() {
        List<ErrorCompilacion> ordenados = new ArrayList<>(errores);
        ordenados.sort(Comparator.comparingInt(ErrorCompilacion::linea)
                .thenComparingInt(ErrorCompilacion::columna));
        return ordenados;
    }

    public List<ErrorCompilacion> getErrores(TipoError tipo) {
        return getErrores().stream().filter(e -> e.tipo() == tipo).toList();
    }

    public boolean hayErrores() {
        return !errores.isEmpty();
    }

    public boolean hayErrores(TipoError tipo) {
        return errores.stream().anyMatch(e -> e.tipo() == tipo);
    }

    public String generarReporte() {
        if (errores.isEmpty()) {
            return archivo + ": sin errores";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-11s %-6s %-7s %-15s %s%n", "Tipo", "Linea", "Columna", "Lexema", "Descripcion"));
        for (ErrorCompilacion e : getErrores()) {
            sb.append(String.format("%-11s %-6d %-7d %-15s %s%n", e.tipo().getNombre(), e.linea(), e.columna(),
                    e.lexema() == null ? "" : e.lexema(), e.descripcion()));
        }
        return sb.toString();
    }
}
