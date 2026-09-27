package com.mycompany.proyectounocompi2.tablas;

import com.mycompany.proyectounocompi2.analizador.ResultadoAnalisis;
import com.mycompany.proyectounocompi2.ast.piglatin.Programa;
import com.mycompany.proyectounocompi2.ast.zetariano.Clase;
import com.mycompany.proyectounocompi2.visitors.piglatin.ConstructorTablasPigLatin;
import com.mycompany.proyectounocompi2.visitors.y.ConstructorTablasY;
import com.mycompany.proyectounocompi2.visitors.zetariano.ConstructorTablasZetariano;
import java.util.ArrayList;
import java.util.List;

// Construye la tabla de tipos y la tabla de simbolos de un programa con un visitor por lenguaje.
public final class ConstructorTablas {

    private ConstructorTablas() {
    }

    // Los archivos sin AST  no se toman en cuenta
    public static Tablas construir(List<ResultadoAnalisis> archivos) {
        Tablas tablas = new Tablas(new TablaTipos(), new TablaSimbolos());
        List<Runnable> importados = new ArrayList<>();
        List<Runnable> principales = new ArrayList<>();

        for (ResultadoAnalisis archivo : archivos) {
            if (archivo.ast() instanceof com.mycompany.proyectounocompi2.ast.y.Programa programa) {
                ConstructorTablasY constructor = new ConstructorTablasY(tablas, archivo.manejador());
                constructor.registrarTipos(programa);
                importados.add(() -> programa.accept(constructor));
            } else if (archivo.ast() instanceof Clase clase) {
                ConstructorTablasZetariano constructor = new ConstructorTablasZetariano(tablas, archivo.manejador());
                constructor.registrarTipos(clase);
                importados.add(() -> clase.accept(constructor));
            } else if (archivo.ast() instanceof Programa programa) {
                ConstructorTablasPigLatin constructor = new ConstructorTablasPigLatin(tablas, archivo.manejador());
                principales.add(() -> programa.accept(constructor));
            }
        }
        importados.forEach(Runnable::run);
        principales.forEach(Runnable::run);
        return tablas;
    }
}
