package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// .atributo
public record SufijoAtributo(String nombre, Posicion posicion) implements Sufijo {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarSufijoAtributo(this);
    }

    @Override
    public Tipo aplicar(Tipo base, ContextoSemantico contexto) {
        return contexto.tipoAtributo(base, nombre, posicion);
    }
}
