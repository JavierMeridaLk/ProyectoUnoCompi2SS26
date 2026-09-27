package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Nodo del AST de Zetariano, cada nodo se recorre con un Visitor
public interface AstNode {

    Posicion posicion();

    <T> T accept(Visitor<T> visitante);

    // Validacion semantica del nodo 
    default Tipo analizar(ContextoSemantico contexto) {
        return null;
    }
}
