package com.mycompany.proyectounocompi2.analisis;

import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import com.mycompany.proyectounocompi2.errores.ErrorCompilacion;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;

public record ResultadoAnalisis(
        Lenguaje lenguaje,
        ParseTree arbol,
        Parser parser,
        CommonTokenStream tokens,
        ManejadorErrores manejador) {

    public List<ErrorCompilacion> errores() {
        return manejador.getErrores();
    }

    public String reporte() {
        return manejador.generarReporte();
    }
}
