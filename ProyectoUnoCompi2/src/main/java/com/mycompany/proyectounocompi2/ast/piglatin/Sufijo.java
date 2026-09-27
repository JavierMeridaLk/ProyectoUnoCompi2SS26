package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// Parte de un acceso despues del nombre
public interface Sufijo extends AstNode {

    // El tipo que resulta de aplicar este sufijo a un valor de tipo 'base'
    Tipo aplicar(Tipo base, ContextoSemantico contexto);
}
