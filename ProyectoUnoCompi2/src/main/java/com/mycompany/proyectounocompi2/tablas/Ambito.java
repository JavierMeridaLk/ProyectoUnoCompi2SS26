package com.mycompany.proyectounocompi2.tablas;

import java.util.List;


public class Ambito {

    private final String nombre;
    private final Ambito padre;
    private final boolean esMarco;
    private final TablaHash<String, Simbolo> simbolos = new TablaHash<>();
    private int tamanoMarco;

    public Ambito(String nombre, Ambito padre, boolean esMarco) {
        this.nombre = nombre;
        this.padre = padre;
        this.esMarco = esMarco;
    }

    public String getNombre() {
        return nombre;
    }

    public Ambito getPadre() {
        return padre;
    }

    public boolean esMarco() {
        return esMarco;
    }

    public Simbolo buscar(String nombre) {
        return simbolos.obtener(nombre);
    }

    public boolean declarar(Simbolo simbolo) {
        if (simbolos.contiene(simbolo.nombre())) {
            return false;
        }
        simbolos.insertar(simbolo.nombre(), simbolo);
        return true;
    }

    public int reservar() {
        return marco().tamanoMarco++;
    }

    public int getTamanoMarco() {
        return marco().tamanoMarco;
    }

    private Ambito marco() {
        return esMarco || padre == null ? this : padre.marco();
    }

    public List<Simbolo> getSimbolos() {
        return simbolos.valores();
    }
}
