package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// definir f(entero a)  entero
public record DefinicionFuncion(
        String nombre,
        List<Parametro> parametros,
        Tipo tipoRetorno,
        List<Statement> cuerpo,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarDefinicionFuncion(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.setRetorno(contexto.conocido(tipoRetorno != null ? tipoRetorno : ContextoSemantico.VOID));
        contexto.dentroDe(this, () -> cuerpo.forEach(sentencia -> sentencia.analizar(contexto)));
        return null;
    }
}
