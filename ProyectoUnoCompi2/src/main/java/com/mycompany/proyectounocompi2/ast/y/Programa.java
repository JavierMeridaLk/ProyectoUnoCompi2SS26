package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.ast.comun.AstRaiz;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Archivo .y seccion %estructuras (opcional) y seccion %funciones
public record Programa(
        List<DefinicionEstructura> estructuras,
        List<DefinicionFuncion> funciones,
        Posicion posicion) implements AstNode, AstRaiz {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarPrograma(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        estructuras.forEach(estructura -> estructura.analizar(contexto));
        funciones.forEach(funcion -> funcion.analizar(contexto));
        return null;
    }
}
