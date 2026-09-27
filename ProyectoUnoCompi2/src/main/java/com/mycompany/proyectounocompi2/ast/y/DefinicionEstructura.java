package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// estructura Persona
public record DefinicionEstructura(
        String nombre,
        List<AtributoEstructura> atributos,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarDefinicionEstructura(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        atributos.forEach(atributo -> atributo.analizar(contexto));
        return null;
    }
}
