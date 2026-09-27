package com.mycompany.proyectounocompi2.tablas;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.errores.TipoError;
import java.util.List;

public abstract class ConstructorTablasBase {

    protected final Tablas tablas;
    protected final ManejadorErrores errores;

    protected ConstructorTablasBase(Tablas tablas, ManejadorErrores errores) {
        this.tablas = tablas;
        this.errores = errores;
    }

    // Declara una variable o parametro en el ambito actual, reservandole una celda del marco
    protected void declararVariable(String nombre, CategoriaSimbolo categoria, Tipo tipo, Posicion posicion) {
        verificarTipo(tipo, posicion);
        Ambito ambito = tablas.simbolos().getActual();
        Simbolo simbolo = new Simbolo(nombre, categoria, tipo, List.of(), ambito.getNombre(), errores.getArchivo(),
                posicion, ambito.reservar(), 1);
        if (!ambito.declarar(simbolo)) {
            error(posicion, nombre, "La variable '" + nombre + "' ya fue declarada en este ámbito");
        }
    }

    // Reporta si el tipo es una estructura/clase que no esta definida
    protected void verificarTipo(Tipo tipo, Posicion posicion) {
        if (!tablas.tipos().existe(tipo)) {
            error(posicion, tipo.nombreClase(), "El tipo '" + tipo.nombreClase() + "' no está definido");
        }
    }

    protected void error(Posicion posicion, String lexema, String descripcion) {
        errores.agregar(TipoError.SEMANTICO, posicion.linea(), posicion.columna(), lexema, descripcion);
    }
}
