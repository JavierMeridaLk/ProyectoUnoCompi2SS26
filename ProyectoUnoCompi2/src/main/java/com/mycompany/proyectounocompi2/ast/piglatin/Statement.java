package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Instruccion de Pig Latin
public interface Statement extends AstNode {

    @Override
    Tipo analizar(ContextoSemantico contexto);
}
