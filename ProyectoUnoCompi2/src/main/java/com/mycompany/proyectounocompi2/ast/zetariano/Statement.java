package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Instruccion de Zetariano
public interface Statement extends AstNode {

    @Override
    Tipo analizar(ContextoSemantico contexto);
}
