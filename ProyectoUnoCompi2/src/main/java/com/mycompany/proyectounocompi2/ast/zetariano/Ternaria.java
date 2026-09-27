package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.tablas.TablaCompatibilidad;

// c ? a : b
public record Ternaria(
        Expression condicion,
        Expression siVerdadero,
        Expression siFalso,
        Posicion posicion) implements Expression {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarTernaria(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.verificarCondicion(condicion.analizar(contexto), condicion.posicion());
        Tipo tipoVerdadero = siVerdadero.analizar(contexto);
        Tipo tipoFalso = siFalso.analizar(contexto);
        if (tipoVerdadero == null || tipoFalso == null) {
            return null;
        }
        if (TablaCompatibilidad.esAsignable(tipoVerdadero, tipoFalso)) {
            return tipoVerdadero;
        }
        if (TablaCompatibilidad.esAsignable(tipoFalso, tipoVerdadero)) {
            return tipoFalso;
        }
        contexto.error(posicion, "?", "Las dos opciones del operador ternario deben ser de tipos compatibles ('"
                + tipoVerdadero + "' y '" + tipoFalso + "')");
        return null;
    }
}
