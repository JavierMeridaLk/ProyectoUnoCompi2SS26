package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;

// String nombre
public record Atributo(
        List<String> modificadores,
        Tipo tipo,
        List<Declarador> declaradores,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarAtributo(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        for (Declarador declarador : declaradores) {
            if (declarador.valor() != null) {
                ListaInicializacion.verificarValor(contexto.conocido(tipo), declarador.valor(), contexto);
            }
        }
        return null;
    }
}
