/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectounocompi2;

import java.io.IOException;
import java.nio.file.Path;

import com.mycompany.proyectounocompi2.analisis.Analizador;
import com.mycompany.proyectounocompi2.analisis.ResultadoAnalisis;

/**
 *
 * @author xavi
 */
public class ProyectoUnoCompi2 {


    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Uso: ProyectoUnoCompi2 <archivo.pig|.y|.z> ...");
            return;
        }
        for (String archivo : args) {
            ResultadoAnalisis resultado = Analizador.analizarArchivo(Path.of(archivo));
            System.out.println("=== " + archivo + " (" + resultado.lenguaje().getNombre() + ")");
            System.out.println(resultado.reporte());
        }
    }
}
