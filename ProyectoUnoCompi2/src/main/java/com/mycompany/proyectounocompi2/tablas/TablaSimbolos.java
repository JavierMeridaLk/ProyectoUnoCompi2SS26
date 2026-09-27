package com.mycompany.proyectounocompi2.tablas;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

// Tabla de simbolos de todo el programa variables y parametros por ambito, funciones de los .y y metodos y constructores de las clases .z.
public class TablaSimbolos {

    private final Ambito global = new Ambito("global", null, true);
    private final List<Ambito> ambitos = new ArrayList<>(List.of(global));
    private Ambito actual = global;
    private final Map<Object, Ambito> ambitosPorNodo = new IdentityHashMap<>();
    private final TablaHash<String, Simbolo> funciones = new TablaHash<>();
    private final TablaHash<String, List<Simbolo>> metodos = new TablaHash<>();
    private final TablaHash<String, List<Simbolo>> constructores = new TablaHash<>();

    public void entrarAmbito(String nombre, boolean esMarco, Object nodo) {
        actual = new Ambito(nombre, actual, esMarco);
        ambitos.add(actual);
        ambitosPorNodo.put(nodo, actual);
    }

    // Vuelve a entrar al ambito que se creo para el nodo
    public void entrar(Object nodo) {
        actual = ambitosPorNodo.get(nodo);
    }

    public void salirAmbito() {
        if (actual.getPadre() != null) {
            actual = actual.getPadre();
        }
    }

    public Ambito getActual() {
        return actual;
    }

    // Celdas del marco del programa principal (variables del .pig)
    public int tamanoGlobal() {
        return global.getTamanoMarco();
    }

    // Busca una variable desde el ambito actual hacia afuera, sin salir de la funcion/metodo 
    public Simbolo buscarVariable(String nombre, Posicion uso) {
        for (Ambito ambito = actual; ambito != null; ambito = ambito.getPadre()) {
            Simbolo simbolo = ambito.buscar(nombre);
            if (simbolo != null && declaradoAntes(simbolo.posicion(), uso)) {
                return simbolo;
            }
            if (ambito.esMarco()) {
                break;
            }
        }
        return null;
    }

    private static boolean declaradoAntes(Posicion declaracion, Posicion uso) {
        return declaracion.linea() < uso.linea()
                || (declaracion.linea() == uso.linea() && declaracion.columna() <= uso.columna());
    }

    public Simbolo buscarFuncion(String nombre) {
        return funciones.obtener(nombre);
    }

    public List<Simbolo> buscarMetodos(String clase, String nombre) {
        List<Simbolo> sobrecargas = metodos.obtener(clase + "." + nombre);
        return sobrecargas != null ? sobrecargas : List.of();
    }

    public List<Simbolo> buscarConstructores(String clase) {
        List<Simbolo> sobrecargas = constructores.obtener(clase);
        return sobrecargas != null ? sobrecargas : List.of();
    }

    // La primera sobrecarga de parametros aceptan los argumentos 
    public static Simbolo elegirSobrecarga(List<Simbolo> opciones, List<Tipo> argumentos) {
        for (Simbolo opcion : opciones) {
            if (opcion.parametros().size() != argumentos.size()) {
                continue;
            }
            boolean acepta = true;
            for (int i = 0; i < argumentos.size() && acepta; i++) {
                acepta = argumentos.get(i) == null
                        || TablaCompatibilidad.esAsignable(opcion.parametros().get(i), argumentos.get(i));
            }
            if (acepta) {
                return opcion;
            }
        }
        return null;
    }

    public boolean declararFuncion(Simbolo funcion) {
        if (funciones.contiene(funcion.nombre())) {
            return false;
        }
        funciones.insertar(funcion.nombre(), funcion);
        return true;
    }

    // retorna false si la clase ya tiene un metodo con el mismo nombre y los mismos tipos de parametros
    public boolean declararMetodo(String clase, Simbolo metodo) {
        return agregarSobrecarga(metodos, clase + "." + metodo.nombre(), metodo);
    }

    // retorna false si la clase ya tiene un constructor con los mismos tipos de parametros
    public boolean declararConstructor(String clase, Simbolo constructor) {
        return agregarSobrecarga(constructores, clase, constructor);
    }

    private static boolean agregarSobrecarga(TablaHash<String, List<Simbolo>> tabla, String clave, Simbolo simbolo) {
        List<Simbolo> sobrecargas = tabla.obtener(clave);
        if (sobrecargas == null) {
            sobrecargas = new ArrayList<>();
            tabla.insertar(clave, sobrecargas);
        }
        for (Simbolo existente : sobrecargas) {
            if (existente.parametros().equals(simbolo.parametros())) {
                return false;
            }
        }
        sobrecargas.add(simbolo);
        return true;
    }

    // Todo lo registrado: funciones, constructores y metodos, y luego las variables de cada ambito
    public List<Simbolo> todos() {
        Comparator<Simbolo> porPosicion = Comparator.comparing(Simbolo::archivo)
                .thenComparingInt(s -> s.posicion().linea())
                .thenComparingInt(s -> s.posicion().columna());
        List<Simbolo> resultado = new ArrayList<>(funciones.valores());
        constructores.valores().forEach(resultado::addAll);
        metodos.valores().forEach(resultado::addAll);
        resultado.sort(porPosicion);
        for (Ambito ambito : ambitos) {
            List<Simbolo> simbolos = new ArrayList<>(ambito.getSimbolos());
            simbolos.sort(porPosicion);
            resultado.addAll(simbolos);
        }
        return resultado;
    }
}
