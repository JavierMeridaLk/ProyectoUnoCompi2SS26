package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;

// elegir(x): caso siempre
public record Elegir(
        Expression valor,
        List<Caso> casos,
        List<Statement> siempre,
        Posicion posicion) implements Statement {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarElegir(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        Tipo tipoValor = valor.analizar(contexto);
        contexto.enSeleccion(() -> {
            for (Caso caso : casos) {
                contexto.operacionBinaria(OperadorBinario.IGUAL_QUE, tipoValor, caso.valor().analizar(contexto),
                        caso.posicion());
                caso.analizar(contexto);
            }
            if (siempre != null) {
                contexto.dentroDe(this, () -> siempre.forEach(sentencia -> sentencia.analizar(contexto)));
            }
        });
        return null;
    }
}
