package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.AstRaiz;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Archivo .pig, importaciones, seccion VARIABILES> y seccion MAIOR>
public record Programa(
        List<Importacion> importaciones,
        List<Statement> variables,
        List<Statement> principal,
        Posicion posicion) implements AstNode, AstRaiz {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarPrograma(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        variables.forEach(sentencia -> sentencia.analizar(contexto));
        principal.forEach(sentencia -> sentencia.analizar(contexto));
        return null;
    }
}
