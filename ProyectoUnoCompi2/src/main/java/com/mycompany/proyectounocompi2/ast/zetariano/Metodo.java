package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// public int f(int a) 
public record Metodo(
        List<String> modificadores,
        Tipo tipoRetorno,
        String nombre,
        List<Parametro> parametros,
        Bloque cuerpo,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarMetodo(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.setRetorno(contexto.conocido(tipoRetorno));
        contexto.dentroDe(this, () -> cuerpo.sentencias().forEach(sentencia -> sentencia.analizar(contexto)));
        return null;
    }
}
