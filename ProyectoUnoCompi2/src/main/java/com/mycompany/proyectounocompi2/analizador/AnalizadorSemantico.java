package com.mycompany.proyectounocompi2.analizador;

import com.mycompany.proyectounocompi2.tablas.ConstructorTablas;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import java.util.List;

//primero se construyen las tablas de tipos y de simbolos y despues cada archivo se revisa desde la raiz de suAST
//cada nodo valida su parte con analizar
//Los errores quedan en el manejador de cada archivo.
public final class AnalizadorSemantico {

    private AnalizadorSemantico() {
    }

    // si no tiene error entonces se revisa 
    public static Tablas analizar(List<ResultadoAnalisis> archivos) {
        Tablas tablas = ConstructorTablas.construir(archivos);
        for (ResultadoAnalisis archivo : archivos) {
            if (archivo.ast() != null) {
                archivo.ast().analizar(new ContextoSemantico(tablas, archivo.manejador()));
            }
        }
        return tablas;
    }
}
