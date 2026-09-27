package com.mycompany.proyectounocompi2.ast.piglatin;

import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.ArrayList;
import java.util.List;

// Expresion de Pig Latin
public interface Expression extends AstNode {

    @Override
    Tipo analizar(ContextoSemantico contexto);

    // Analiza cada expresion y devuelve sus tipos
    static List<Tipo> analizarTodas(List<Expression> expresiones, ContextoSemantico contexto) {
        List<Tipo> tipos = new ArrayList<>();
        expresiones.forEach(expresion -> tipos.add(expresion.analizar(contexto)));
        return tipos;
    }
}
