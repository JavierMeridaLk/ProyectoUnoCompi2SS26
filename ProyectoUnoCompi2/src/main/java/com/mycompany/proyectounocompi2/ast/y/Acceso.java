package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// x   arr[i][j] 
public record Acceso(String nombre, List<Sufijo> sufijos, Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAcceso(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Tipo tipo = contexto.tipoVariable(nombre, posicion);
        for (Sufijo sufijo : sufijos) {
            tipo = sufijo.aplicar(tipo, contexto);
        }
        return tipo;
    }
}
