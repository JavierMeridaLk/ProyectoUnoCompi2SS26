package com.mycompany.proyectounocompi2.tablas;

import java.util.ArrayList;
import java.util.List;

// Una entrada de la tabla de tipos: un primitivo, una estructura de Y o una clase de Zetariano

public class TipoDefinido {

    public enum Categoria {
        PRIMITIVO,
        ESTRUCTURA,
        CLASE
    }

    private final String nombre;
    private final Categoria categoria;
    private final String archivo;       
    private final List<Campo> campos = new ArrayList<>();

    public TipoDefinido(String nombre, Categoria categoria, String archivo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.archivo = archivo;
    }

    // retorna false si ya habia un atributo con ese nombre
    public boolean agregarCampo(Campo campo) {
        if (buscarCampo(campo.nombre()) != null) {
            return false;
        }
        campos.add(campo);
        return true;
    }

    public Campo buscarCampo(String nombre) {
        for (Campo campo : campos) {
            if (campo.nombre().equals(nombre)) {
                return campo;
            }
        }
        return null;
    }

    // Celdas que ocupa en el heap
    public int getTamano() {
        return categoria == Categoria.PRIMITIVO ? 1 : campos.size();
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getArchivo() {
        return archivo;
    }

    public List<Campo> getCampos() {
        return campos;
    }
}
