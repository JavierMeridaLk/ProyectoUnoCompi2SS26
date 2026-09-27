package com.mycompany.proyectounocompi2.tablas;

import java.util.ArrayList;
import java.util.List;

// Tabla hash con encadenamiento 
public class TablaHash<K, V> {

    private static final int CAPACIDAD_INICIAL = 16;
    private static final double FACTOR_CARGA_MAXIMO = 0.75;

    private static class Entrada<K, V> {
        final K clave;
        V valor;
        Entrada<K, V> siguiente;

        Entrada(K clave, V valor, Entrada<K, V> siguiente) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = siguiente;
        }
    }

    private Entrada<K, V>[] cubetas;
    private int cantidad;

    @SuppressWarnings("unchecked")
    public TablaHash() {
        cubetas = new Entrada[CAPACIDAD_INICIAL];
    }

    private int indice(K clave, int capacidad) {
        int hash = clave.hashCode();
        hash ^= (hash >>> 16);
        return (hash & 0x7fffffff) % capacidad;
    }

    public void insertar(K clave, V valor) {
        if ((double) (cantidad + 1) / cubetas.length > FACTOR_CARGA_MAXIMO) {
            redimensionar();
        }
        int i = indice(clave, cubetas.length);
        for (Entrada<K, V> actual = cubetas[i]; actual != null; actual = actual.siguiente) {
            if (actual.clave.equals(clave)) {
                actual.valor = valor;
                return;
            }
        }
        cubetas[i] = new Entrada<>(clave, valor, cubetas[i]);
        cantidad++;
    }

    public V obtener(K clave) {
        for (Entrada<K, V> actual = cubetas[indice(clave, cubetas.length)]; actual != null; actual = actual.siguiente) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
        }
        return null;
    }

    public boolean contiene(K clave) {
        return obtener(clave) != null;
    }

    public List<V> valores() {
        List<V> lista = new ArrayList<>(cantidad);
        for (Entrada<K, V> cubeta : cubetas) {
            for (Entrada<K, V> actual = cubeta; actual != null; actual = actual.siguiente) {
                lista.add(actual.valor);
            }
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        Entrada<K, V>[] anteriores = cubetas;
        cubetas = new Entrada[anteriores.length * 2];
        cantidad = 0;
        for (Entrada<K, V> cubeta : anteriores) {
            for (Entrada<K, V> actual = cubeta; actual != null; actual = actual.siguiente) {
                insertar(actual.clave, actual.valor);
            }
        }
    }
}
