package com.mycompany.proyectounocompi2.cuartetas;

import com.mycompany.proyectounocompi2.analizador.ProgramaCargado;
import com.mycompany.proyectounocompi2.analizador.ResultadoAnalisis;
import com.mycompany.proyectounocompi2.ast.piglatin.Programa;
import com.mycompany.proyectounocompi2.ast.zetariano.Clase;
import com.mycompany.proyectounocompi2.visitors.piglatin.GeneradorPigLatin;
import com.mycompany.proyectounocompi2.visitors.y.GeneradorY;
import com.mycompany.proyectounocompi2.visitors.zetariano.GeneradorZetariano;
import java.util.List;

// Genera una sola lista de cuartetas para todo el programa
public final class GeneradorCuartetas {

    private GeneradorCuartetas() {
    }

    // El programa debe estar libre de errores y su archivo principal debe ser un .pig
    public static List<Cuarteta> generar(ProgramaCargado programa) {
        ListaCuartetas codigo = new ListaCuartetas();
        for (ResultadoAnalisis archivo : programa.archivos()) {
            String nombre = archivo.manejador().getArchivo();
            if (archivo.ast() instanceof Programa pig) {
                pig.accept(new GeneradorPigLatin(programa.tablas(), codigo, nombre));
            } else if (archivo.ast() instanceof Clase clase) {
                clase.accept(new GeneradorZetariano(programa.tablas(), codigo, nombre));
            } else if (archivo.ast() instanceof com.mycompany.proyectounocompi2.ast.y.Programa y) {
                y.accept(new GeneradorY(programa.tablas(), codigo, nombre));
            }
        }
        return codigo.getCuartetas();
    }
}
