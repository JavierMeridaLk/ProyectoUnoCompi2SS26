package com.mycompany.proyectounocompi2.archivos;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// Operaciones sobre archivos y carpetas del proyecto.
public final class GestorArchivos {

    private GestorArchivos() {
    }

    public static String leer(Path archivo) throws IOException {
        return Files.readString(archivo, StandardCharsets.UTF_8);
    }

    public static void escribir(Path archivo, String contenido) throws IOException {
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
        Files.writeString(archivo, contenido, StandardCharsets.UTF_8);
    }

    // Elimina un archivo o una carpeta con todo su contenido.
    public static void eliminar(Path ruta) throws IOException {
        if (!Files.isDirectory(ruta)) {
            Files.deleteIfExists(ruta);
            return;
        }
        try (Stream<Path> recorrido = Files.walk(ruta)) {
            List<Path> rutas = recorrido.sorted(Comparator.reverseOrder()).toList();
            for (Path p : rutas) {
                Files.delete(p);
            }
        }
    }

    public static void copiar(Path origen, Path destino) throws IOException {
        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
    }

    // Comprime una carpeta completa en un archivo .zip.
    public static void comprimir(Path carpeta, Path zip) throws IOException {
        Path base = carpeta.getParent() != null ? carpeta.getParent() : carpeta;
        try (OutputStream salida = Files.newOutputStream(zip);
                ZipOutputStream zipSalida = new ZipOutputStream(salida);
                Stream<Path> recorrido = Files.walk(carpeta)) {
            for (Path ruta : recorrido.sorted().toList()) {
                String nombre = base.relativize(ruta).toString().replace('\\', '/');
                if (Files.isDirectory(ruta)) {
                    zipSalida.putNextEntry(new ZipEntry(nombre + "/"));
                } else {
                    zipSalida.putNextEntry(new ZipEntry(nombre));
                    Files.copy(ruta, zipSalida);
                }
                zipSalida.closeEntry();
            }
        }
    }
}
