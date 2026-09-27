package com.mycompany.proyectounocompi2.tablas;

import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import java.util.ArrayList;
import java.util.List;

// Tabla de tipos: los primitivos, las estructuras de los .y y las clases de los .z.
public class TablaTipos {

    private final TablaHash<String, TipoDefinido> tipos = new TablaHash<>();
    private final List<TipoDefinido> enOrden = new ArrayList<>();

    public TablaTipos() {
        for (TipoPrimitivo primitivo : TipoPrimitivo.values()) {
            if (primitivo != TipoPrimitivo.NULO) {
                agregar(new TipoDefinido(Tipo.primitivo(primitivo).toString(), TipoDefinido.Categoria.PRIMITIVO,
                        null));
            }
        }
    }

    // retorna false si ya existia un tipo con ese nombre
    public boolean agregar(TipoDefinido tipo) {
        if (tipos.contiene(tipo.getNombre())) {
            return false;
        }
        tipos.insertar(tipo.getNombre(), tipo);
        enOrden.add(tipo);
        return true;
    }

    public TipoDefinido buscar(String nombre) {
        return tipos.obtener(nombre);
    }

    // Un tipo existe si es primitivo o si su estructura/clase esta definida
    public boolean existe(Tipo tipo) {
        return tipo.esPrimitivo() || tipos.contiene(tipo.nombreClase());
    }

    public List<TipoDefinido> todos() {
        return enOrden;
    }
}
