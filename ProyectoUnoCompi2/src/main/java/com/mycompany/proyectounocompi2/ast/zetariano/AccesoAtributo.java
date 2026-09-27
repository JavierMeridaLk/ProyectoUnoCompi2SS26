package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// obj.atributo
public record AccesoAtributo(Expression objeto, String nombre, Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAccesoAtributo(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        return contexto.tipoAtributo(objeto.analizar(contexto), nombre, posicion);
    }
}
