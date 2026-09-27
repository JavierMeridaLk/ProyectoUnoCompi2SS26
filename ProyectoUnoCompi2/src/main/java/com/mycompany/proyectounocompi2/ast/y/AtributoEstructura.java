package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;

// entero notas[3]
public record AtributoEstructura(
        Tipo tipo,
        String nombre,
        List<Expression> dimensiones,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAtributoEstructura(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        for (Expression dimension : dimensiones) {
            if (!(dimension instanceof Literal literal) || literal.tipo() != TipoPrimitivo.ENTERO) {
                contexto.error(dimension.posicion(), nombre,
                        "El tamaño de un arreglo dentro de una estructura debe ser una constante entera");
            }
        }
        return null;
    }
}
