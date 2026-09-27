package com.mycompany.proyectounocompi2.analizador;

import com.mycompany.pigLatinParser;
import com.mycompany.proyectounocompi2.visitors.piglatin.AstBuilder;
import com.mycompany.proyectounocompi2.ast.piglatin.Importacion;
import com.mycompany.proyectounocompi2.ast.piglatin.Programa;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.errores.TipoError;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CargadorPrograma {

    // Lee el contenido de un archivo
    public interface LectorArchivos {
        String leer(Path archivo) throws IOException;
    }

    private final Path raiz;
    private final LectorArchivos lector;

    public CargadorPrograma(Path raiz, LectorArchivos lector) {
        this.raiz = raiz.toAbsolutePath().normalize();
        this.lector = lector;
    }

    public ProgramaCargado cargar(Path archivoPig) throws IOException {
        Path ruta = archivoPig.toAbsolutePath().normalize();
        ResultadoAnalisis principal = Analizador.analizar(lector.leer(ruta), Lenguaje.PIG_LATIN, nombreVisible(ruta));
        Map<Path, ResultadoAnalisis> importados = new LinkedHashMap<>();
        for (Importacion importacion : importaciones(principal)) {
            cargarImportacion(importacion, principal.manejador(), importados);
        }
        List<ResultadoAnalisis> archivos = new ArrayList<>(importados.values());
        archivos.add(principal);
        Tablas tablas = AnalizadorSemantico.analizar(archivos);
        return new ProgramaCargado(principal, importados, tablas);
    }

    // los import bien escritos se leen del arbol de ANTLR
    private static List<Importacion> importaciones(ResultadoAnalisis principal) {
        if (principal.ast() instanceof Programa programa) {
            return programa.importaciones();
        }
        List<Importacion> importaciones = new ArrayList<>();
        if (principal.arbol() instanceof pigLatinParser.ProgramaContext programa) {
            AstBuilder constructor = new AstBuilder();
            for (pigLatinParser.ImportacionContext importacion : programa.importacion()) {
                if (importacion.exception == null && importacion.rutaImportacion().exception == null) {
                    importaciones.add(constructor.visitImportacion(importacion));
                }
            }
        }
        return importaciones;
    }

    private void cargarImportacion(Importacion importacion, ManejadorErrores errores,
            Map<Path, ResultadoAnalisis> importados) {
        List<String> partes = importacion.ruta();
        String textoImport = String.join(".", partes);
        String extension = partes.get(partes.size() - 1);

        if (!extension.equals("y") && !extension.equals("z")) {
            error(errores, importacion, textoImport,
                    "Solo se pueden importar archivos .y o .z (se encontró '." + extension + "')");
            return;
        }
        
        Path archivo = raiz;
        for (int i = 0; i < partes.size() - 2; i++) {
            archivo = archivo.resolve(partes.get(i));
        }
        archivo = archivo.resolve(partes.get(partes.size() - 2) + "." + extension).normalize();

        if (importados.containsKey(archivo)) {
            error(errores, importacion, textoImport, "El archivo '" + nombreVisible(archivo) + "' ya fue importado");
            return;
        }
        if (!Files.isRegularFile(archivo)) {
            error(errores, importacion, textoImport,
                    "No se encontró el archivo importado '" + nombreVisible(archivo) + "'");
            return;
        }
        try {
            Lenguaje lenguaje = extension.equals("y") ? Lenguaje.Y : Lenguaje.ZETARIANO;
            importados.put(archivo, Analizador.analizar(lector.leer(archivo), lenguaje, nombreVisible(archivo)));
        } catch (IOException e) {
            error(errores, importacion, textoImport,
                    "No se pudo leer el archivo importado '" + nombreVisible(archivo) + "': " + e.getMessage());
        }
    }

    private static void error(ManejadorErrores errores, Importacion importacion, String lexema, String descripcion) {
        errores.agregar(TipoError.SEMANTICO, importacion.posicion().linea(), importacion.posicion().columna(), lexema,
                descripcion);
    }

    // Nombre que se muestra en la tabla de errores
    private String nombreVisible(Path archivo) {
        Path relativa = archivo.startsWith(raiz) ? raiz.relativize(archivo) : archivo.getFileName();
        return relativa.toString().replace('\\', '/');
    }
}
