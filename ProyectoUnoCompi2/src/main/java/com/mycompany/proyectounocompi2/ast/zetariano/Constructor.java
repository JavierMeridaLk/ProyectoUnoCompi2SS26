package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;

// public Persona(String n) 
public record Constructor(
        List<String> modificadores,
        String nombre,
        List<Parametro> parametros,
        Bloque cuerpo,
        Posicion posicion) implements AstNode {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarConstructor(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        contexto.setRetorno(ContextoSemantico.VOID);
        // el cuerpo se revisa en el marco que se creo para el constructor
        contexto.dentroDe(this, () -> cuerpo.sentencias().forEach(sentencia -> sentencia.analizar(contexto)));
        return null;
    }
}
