package com.mycompany.proyectounocompi2.ast.zetariano;

import com.mycompany.proyectounocompi2.ast.comun.AstRaiz;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import java.util.List;
import com.mycompany.proyectounocompi2.analizador.ContextoSemantico;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.tablas.TipoDefinido;

// public class Persona 
public record Clase(
        List<String> modificadores,
        String nombre,
        String padre,
        List<Atributo> atributos,
        List<Constructor> constructores,
        List<Metodo> metodos,
        Posicion posicion) implements AstNode, AstRaiz {

    @Override
    public <T> T accept(Visitor<T> visitante) {
        return visitante.visitarClase(this);
    }

    @Override
    public Tipo analizar(ContextoSemantico contexto) {
        TipoDefinido definida = contexto.getTablas().tipos().buscar(nombre);
        if (definida == null || !contexto.getArchivo().equals(definida.getArchivo())) {
            return null;
        }
        contexto.setClase(definida);
        contexto.dentroDe(this, () -> {
            atributos.forEach(atributo -> atributo.analizar(contexto));
            constructores.forEach(constructor -> constructor.analizar(contexto));
            metodos.forEach(metodo -> metodo.analizar(contexto));
        });
        return null;
    }
}
