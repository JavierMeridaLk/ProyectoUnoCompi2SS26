package com.mycompany.proyectounocompi2.visitors.piglatin;

import com.mycompany.proyectounocompi2.ast.piglatin.*;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.tablas.CategoriaSimbolo;
import com.mycompany.proyectounocompi2.tablas.ConstructorTablasBase;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import java.util.List;

// Llena la tabla de simbolos con las variables del .pig
public class ConstructorTablasPigLatin extends ConstructorTablasBase implements Visitor<Void> {

    public ConstructorTablasPigLatin(Tablas tablas, ManejadorErrores errores) {
        super(tablas, errores);
    }

    private void visitarBloque(String nombre, List<Statement> sentencias, AstNode nodo) {
        tablas.simbolos().entrarAmbito(tablas.simbolos().getActual().getNombre() + "/" + nombre, false, nodo);
        sentencias.forEach(sentencia -> sentencia.accept(this));
        tablas.simbolos().salirAmbito();
    }

    // ---------- Programa ----------

    @Override
    public Void visitarPrograma(Programa nodo) {
        nodo.variables().forEach(sentencia -> sentencia.accept(this));
        nodo.principal().forEach(sentencia -> sentencia.accept(this));
        return null;
    }

    @Override
    public Void visitarImportacion(Importacion nodo) {
        return null;
    }

    // ---------- Sentencias ----------

    @Override
    public Void visitarDeclaracion(Declaracion nodo) {
        declararVariable(nodo.nombre(), CategoriaSimbolo.VARIABLE, nodo.tipo(), nodo.posicion());
        return null;
    }

    @Override
    public Void visitarDeclaracionArreglo(DeclaracionArreglo nodo) {
        declararVariable(nodo.nombre(), CategoriaSimbolo.VARIABLE, nodo.tipo(), nodo.posicion());
        return null;
    }

    @Override
    public Void visitarAsignacion(Asignacion nodo) {
        return null;
    }

    @Override
    public Void visitarIncrementoDecremento(IncrementoDecremento nodo) {
        return null;
    }

    @Override
    public Void visitarSentenciaExpresion(SentenciaExpresion nodo) {
        return null;
    }

    @Override
    public Void visitarCondicional(Condicional nodo) {
        nodo.ramas().forEach(rama -> rama.accept(this));
        if (nodo.sino() != null) {
            visitarBloque("aliter", nodo.sino(), nodo);
        }
        return null;
    }

    @Override
    public Void visitarRamaCondicional(RamaCondicional nodo) {
        visitarBloque("si", nodo.cuerpo(), nodo);
        return null;
    }

    @Override
    public Void visitarCicloDum(CicloDum nodo) {
        visitarBloque("dum", nodo.cuerpo(), nodo);
        return null;
    }

    @Override
    public Void visitarCicloFacere(CicloFacere nodo) {
        visitarBloque("facere", nodo.cuerpo(), nodo);
        return null;
    }

    @Override
    public Void visitarCicloPer(CicloPer nodo) {
        tablas.simbolos().entrarAmbito(tablas.simbolos().getActual().getNombre() + "/per", false, nodo);
        if (nodo.inicio() != null) {
            nodo.inicio().accept(this);
        }
        nodo.cuerpo().forEach(sentencia -> sentencia.accept(this));
        tablas.simbolos().salirAmbito();
        return null;
    }

    @Override
    public Void visitarPerge(Perge nodo) {
        return null;
    }

    @Override
    public Void visitarInterrumpe(Interrumpe nodo) {
        return null;
    }

    @Override
    public Void visitarLectura(Lectura nodo) {
        return null;
    }

    @Override
    public Void visitarEscritura(Escritura nodo) {
        return null;
    }

    // ---------- Expresiones ----------

    @Override
    public Void visitarLiteral(Literal nodo) {
        return null;
    }

    @Override
    public Void visitarListaInicializacion(ListaInicializacion nodo) {
        return null;
    }

    @Override
    public Void visitarAcceso(Acceso nodo) {
        return null;
    }

    @Override
    public Void visitarNuevoObjeto(NuevoObjeto nodo) {
        return null;
    }

    @Override
    public Void visitarOperacionUnaria(OperacionUnaria nodo) {
        return null;
    }

    @Override
    public Void visitarOperacionBinaria(OperacionBinaria nodo) {
        return null;
    }

    @Override
    public Void visitarSufijoIndice(SufijoIndice nodo) {
        return null;
    }

    @Override
    public Void visitarSufijoAtributo(SufijoAtributo nodo) {
        return null;
    }

    @Override
    public Void visitarSufijoLlamada(SufijoLlamada nodo) {
        return null;
    }
}
