package com.mycompany.proyectounocompi2.analisis;

import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import com.mycompany.proyectounocompi2.errores.ErrorCompilacion;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.errores.TipoError;

public record ResultadoAnalisis(
        Lenguaje lenguaje,
        ParseTree arbol,
        Parser parser,
        CommonTokenStream tokens,
        ManejadorErrores manejador) {

    public boolean esValido() {
        return !manejador.hayErrores();
    }

    public List<ErrorCompilacion> errores() {
        return manejador.getErrores();
    }

    public List<ErrorCompilacion> errores(TipoError tipo) {
        return manejador.getErrores(tipo);
    }

    public String arbolComoTexto() {
        return arbol.toStringTree(parser);
    }

    public String reporte() {
        return manejador.generarReporte();
    }
}
