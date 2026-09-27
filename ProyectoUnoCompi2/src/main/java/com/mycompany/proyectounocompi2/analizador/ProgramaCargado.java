package com.mycompany.proyectounocompi2.analizador;

import com.mycompany.proyectounocompi2.errores.ErrorCompilacion;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Un programa completo, cada lenguaje con su propio AST, y las tablas de todo el programa.

public record ProgramaCargado(ResultadoAnalisis principal, Map<Path, ResultadoAnalisis> importados, Tablas tablas) {

    
    public List<ResultadoAnalisis> archivos() {
        List<ResultadoAnalisis> archivos = new ArrayList<>();
        archivos.add(principal);
        archivos.addAll(importados.values());
        return archivos;
    }

    // Errores de todos los archivos
    public List<ErrorCompilacion> errores() {
        List<ErrorCompilacion> errores = new ArrayList<>();
        archivos().forEach(archivo -> errores.addAll(archivo.errores()));
        return errores;
    }
}
