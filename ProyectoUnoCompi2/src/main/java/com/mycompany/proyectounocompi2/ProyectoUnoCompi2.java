/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectounocompi2;

import java.io.IOException;
import java.nio.file.Path;

import com.mycompany.proyectounocompi2.analisis.Analizador;
import com.mycompany.proyectounocompi2.analisis.ResultadoAnalisis;
import com.mycompany.proyectounocompi2.views.FramePrincipal;

/**
 *
 * @author xavi
 */
public class ProyectoUnoCompi2 {


    public static void main(String[] args) throws IOException {
        // Sin argumentos se abre el IDE; con archivos se analizan por consola
        if (args.length == 0) {
            FramePrincipal.main(args);
            return;
        }
        for (String archivo : args) {
            ResultadoAnalisis resultado = Analizador.analizarArchivo(Path.of(archivo));
            System.out.println("=== " + archivo + " (" + resultado.lenguaje().getNombre() + ")");
            System.out.println(resultado.reporte());
        }
    }
}
