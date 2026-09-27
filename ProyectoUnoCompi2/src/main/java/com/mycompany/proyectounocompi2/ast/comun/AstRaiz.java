package com.mycompany.proyectounocompi2.ast.comun;

import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// Raiz del AST de un archivo 
public interface AstRaiz {

    Tipo analizar(ContextoSemantico contexto);
}
