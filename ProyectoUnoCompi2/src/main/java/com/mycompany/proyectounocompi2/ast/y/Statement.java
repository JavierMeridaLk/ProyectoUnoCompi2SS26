package com.mycompany.proyectounocompi2.ast.y;

import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Instruccion de Y
public interface Statement extends AstNode {

    @Override
    Tipo analizar(ContextoSemantico contexto);
}
