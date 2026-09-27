package com.mycompany.proyectounocompi2.cuartetas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

// compilcion el codigo C con gcc. 
//   programa.c     el codigo generado

public final class EjecutorC {

    public record Resultado(boolean compilo, String mensajes) {
    }

    private EjecutorC() {
    }

    public static Resultado compilar(Path carpeta, String codigo) throws IOException, InterruptedException {
        Files.createDirectories(carpeta);
        Files.writeString(carpeta.resolve("programa.c"), codigo);
        Path script = carpeta.resolve("ejecutar.sh");
        Files.writeString(script, """
                #!/bin/bash
                # Compila el programa generado y lo ejecuta
                cd "$(dirname "$0")"
                gcc programa.c -o programa && ./programa
                """);
        script.toFile().setExecutable(true);

        Process gcc = new ProcessBuilder("gcc", "programa.c", "-o", "programa")
                .directory(carpeta.toFile()).redirectErrorStream(true).start();
        String mensajes = new String(gcc.getInputStream().readAllBytes());
        if (!gcc.waitFor(60, TimeUnit.SECONDS)) {
            gcc.destroy();
            return new Resultado(false, "gcc tardó demasiado.");
        }
        return new Resultado(gcc.exitValue() == 0, mensajes);
    }
}
