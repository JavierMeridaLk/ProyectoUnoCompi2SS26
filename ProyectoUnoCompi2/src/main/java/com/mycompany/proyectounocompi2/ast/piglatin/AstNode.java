package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Nodo del AST de Pig Latin
public interface AstNode {

    Posicion posicion();

    <T> T accept(Visitor<T> visitante);

    // Validacion semantica del nodo
    default Tipo analizar(ContextoSemantico contexto) {
        return null;
    }
}
