package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.tablas.Campo;
import com.mycompany.proyectounocompi2.tablas.Simbolo;

// x
public record Identificador(String nombre, Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarIdentificador(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Simbolo variable = contexto.getTablas().simbolos().buscarVariable(nombre, posicion);
        if (variable != null) {
            return contexto.conocido(variable.tipo());
        }
        Campo atributo = contexto.getClase().buscarCampo(nombre);
        if (atributo != null) {
            return contexto.conocido(atributo.tipo());
        }
        return contexto.tipoVariable(nombre, posicion);
    }
}
