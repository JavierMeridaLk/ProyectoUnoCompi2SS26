package com.mycompany.proyectounocompi2.visitors;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Un nodo del arbol que se dibuja y sus hijos
public record NodoArbol(String etiqueta, List<NodoArbol> hijos) {

    // Los hijos null se omiten
    public static NodoArbol de(String etiqueta, NodoArbol... hijos) {
        return new NodoArbol(etiqueta, Arrays.stream(hijos).filter(Objects::nonNull).toList());
    }

    // Nodo con una lista de hijos seguida de otros hijos sueltos
    public static NodoArbol de(String etiqueta, List<NodoArbol> primeros, NodoArbol... resto) {
        List<NodoArbol> hijos = new ArrayList<>(primeros);
        Arrays.stream(resto).filter(Objects::nonNull).forEach(hijos::add);
        return new NodoArbol(etiqueta, hijos);
    }

    // el primer hijo es lo que se llama y despues los argumentos
    public static NodoArbol llamada(NodoArbol funcion, List<NodoArbol> argumentos) {
        List<NodoArbol> hijos = new ArrayList<>(List.of(funcion));
        hijos.addAll(argumentos);
        return new NodoArbol("llamada", hijos);
    }

    // Un literal como se escribe en el lenguaje
    public static NodoArbol literal(TipoPrimitivo tipo, Object valor, Lenguaje lenguaje) {
        String texto = switch (tipo) {
            case CADENA -> "\"" + valor + "\"";
            case CARACTER -> "'" + valor + "'";
            case BOOLEANO -> lenguaje.booleano((Boolean) valor);
            case NULO -> lenguaje.nombreDe(TipoPrimitivo.NULO);
            default -> String.valueOf(valor);
        };
        return de(texto.replace("\n", "\\n").replace("\t", "\\t"));
    }
}
