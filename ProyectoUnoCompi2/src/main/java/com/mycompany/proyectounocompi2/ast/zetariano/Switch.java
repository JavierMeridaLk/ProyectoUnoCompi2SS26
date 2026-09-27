package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;

// switch (x) 
public record Switch(
        Expression valor,
        List<SeccionSwitch> secciones,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarSwitch(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Tipo tipoValor = valor.analizar(contexto);
        contexto.enSeleccion(() -> {
            for (SeccionSwitch seccion : secciones) {
                // cada case se compara con == contra el valor
                for (Expression caso : seccion.casos()) {
                    contexto.operacionBinaria(OperadorBinario.IGUAL_QUE, tipoValor, caso.analizar(contexto), caso.posicion());
                }
                seccion.analizar(contexto);
            }
        });
        return null;
    }
}
