package com.mycompany.proyectounocompi2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.mycompany.proyectounocompi2.analizador.Analizador;
import com.mycompany.proyectounocompi2.analizador.ResultadoAnalisis;
import com.mycompany.proyectounocompi2.archivos.GestorArchivos;
import com.mycompany.proyectounocompi2.analizador.CargadorPrograma;
import com.mycompany.proyectounocompi2.analizador.ProgramaCargado;
import com.mycompany.proyectounocompi2.analizador.AnalizadorSemantico;
import com.mycompany.proyectounocompi2.views.FramePrincipal;

// @author xavi
public class ProyectoUnoCompi2 {


    public static void main(String[] args) throws IOException {
        
        if (args.length == 0) {
            FramePrincipal.main(args);
            return;
        }
        for (String archivo : args) {
            Path ruta = Path.of(archivo).toAbsolutePath();
            if (archivo.endsWith(".pig")) {
                ProgramaCargado programa = new CargadorPrograma(ruta.getParent(), GestorArchivos::leer).cargar(ruta);
                imprimir(programa.principal());
                programa.importados().values().forEach(ProyectoUnoCompi2::imprimir);
            } else {
                ResultadoAnalisis resultado = Analizador.analizarArchivo(ruta);
                AnalizadorSemantico.analizar(List.of(resultado));
                imprimir(resultado);
            }
        }
    }

    private static void imprimir(ResultadoAnalisis resultado) {
        System.out.println("=== " + resultado.manejador().getArchivo() + " (" + resultado.lenguaje().getNombre() + ")");
        System.out.println(resultado.reporte());
    }
}
