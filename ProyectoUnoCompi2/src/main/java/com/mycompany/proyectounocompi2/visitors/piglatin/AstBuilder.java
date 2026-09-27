package com.mycompany.proyectounocompi2.visitors.piglatin;

import com.mycompany.proyectounocompi2.ast.piglatin.*;

import com.mycompany.pigLatinBaseVisitor;
import com.mycompany.pigLatinParser;
import com.mycompany.proyectounocompi2.ast.comun.Literales;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;

// Recorre el arbol de ANTLR de un archivo .pig y construye su AST.
public class AstBuilder extends pigLatinBaseVisitor<AstNode> {

    @Override
    public Programa visitPrograma(pigLatinParser.ProgramaContext ctx) {
        List<Importacion> importaciones = new ArrayList<>();
        for (pigLatinParser.ImportacionContext importacion : ctx.importacion()) {
            importaciones.add(visitImportacion(importacion));
        }
        List<Statement> variables = new ArrayList<>();
        if (ctx.seccionVariables() != null) {
            for (pigLatinParser.DeclaracionContext declaracion : ctx.seccionVariables().declaracion()) {
                variables.add(construirDeclaracion(declaracion));
            }
        }
        List<Statement> principal = construirInstrucciones(ctx.seccionPrincipal().instruccion());
        return new Programa(importaciones, variables, principal, Posicion.de(ctx));
    }

    @Override
    public Importacion visitImportacion(pigLatinParser.ImportacionContext ctx) {
        List<String> ruta = new ArrayList<>();
        for (TerminalNode parte : ctx.rutaImportacion().IDENTIFICADOR()) {
            ruta.add(parte.getText());
        }
        return new Importacion(ruta, Posicion.de(ctx));
    }

    // ---------- Declaraciones ----------

    private Statement construirDeclaracion(pigLatinParser.DeclaracionContext ctx) {
        if (ctx.declaracionVariable() != null) {
            return visitDeclaracionVariable(ctx.declaracionVariable());
        }
        return visitDeclaracionArreglo(ctx.declaracionArreglo());
    }

    @Override
    public Declaracion visitDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx) {
        String nombre = ctx.IDENTIFICADOR().getText();
        Tipo tipo;
        Expression valor;
        if (ctx.cuerpoDeclaracion() instanceof pigLatinParser.DeclaracionConTipoContext conTipo) {
            tipo = construirTipo(conTipo.tipo());
            valor = conTipo.inicializador() != null ? construirInicializador(conTipo.inicializador()) : null;
        } else if (ctx.cuerpoDeclaracion() instanceof pigLatinParser.DeclaracionBooleanaContext booleana) {
            tipo = Tipo.primitivo(TipoPrimitivo.BOOLEANO);
            valor = new Literal(TipoPrimitivo.BOOLEANO, booleana.VERUM() != null, Posicion.de(booleana));
        } else {
            pigLatinParser.DeclaracionObjetoContext objeto = (pigLatinParser.DeclaracionObjetoContext) ctx.cuerpoDeclaracion();
            valor = construirNuevoObjeto(objeto.creacionObjeto(), List.of());
            tipo = Tipo.clase(objeto.creacionObjeto().IDENTIFICADOR().getText());
        }
        return new Declaracion(nombre, tipo, valor, Posicion.de(ctx));
    }

    @Override
    public DeclaracionArreglo visitDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx) {
        List<Expression> dimensiones = new ArrayList<>();
        for (pigLatinParser.DimensionContext dimension : ctx.dimension()) {
            dimensiones.add(construirExpresion(dimension.expresion()));
        }
        Tipo tipo = construirTipo(ctx.tipo()).conDimensiones(dimensiones.size());
        Expression valor = ctx.inicializador() != null ? construirInicializador(ctx.inicializador()) : null;
        return new DeclaracionArreglo(ctx.IDENTIFICADOR().getText(), dimensiones, tipo, valor, Posicion.de(ctx));
    }

    private Expression construirInicializador(pigLatinParser.InicializadorContext ctx) {
        if (ctx.expresion() != null) {
            return construirExpresion(ctx.expresion());
        }
        return visitListaInicializacion(ctx.listaInicializacion());
    }

    @Override
    public ListaInicializacion visitListaInicializacion(pigLatinParser.ListaInicializacionContext ctx) {
        List<Expression> elementos = new ArrayList<>();
        for (pigLatinParser.InicializadorContext inicializador : ctx.inicializador()) {
            elementos.add(construirInicializador(inicializador));
        }
        return new ListaInicializacion(elementos, Posicion.de(ctx));
    }

    private Tipo construirTipo(pigLatinParser.TipoContext ctx) {
        if (ctx.NUMERUS() != null) {
            return Tipo.primitivo(TipoPrimitivo.ENTERO);
        }
        if (ctx.DECIMALIS() != null) {
            return Tipo.primitivo(TipoPrimitivo.DECIMAL);
        }
        if (ctx.TEXTUM() != null) {
            return Tipo.primitivo(TipoPrimitivo.CADENA);
        }
        if (ctx.LITTERA() != null) {
            return Tipo.primitivo(TipoPrimitivo.CARACTER);
        }
        if (ctx.BOOL() != null) {
            return Tipo.primitivo(TipoPrimitivo.BOOLEANO);
        }
        return Tipo.clase(ctx.IDENTIFICADOR().getText());
    }

    // ---------- Instrucciones ----------

    private List<Statement> construirInstrucciones(List<pigLatinParser.InstruccionContext> instrucciones) {
        List<Statement> sentencias = new ArrayList<>();
        for (pigLatinParser.InstruccionContext instruccion : instrucciones) {
            sentencias.add(construirInstruccion(instruccion));
        }
        return sentencias;
    }

    private List<Statement> construirBloque(pigLatinParser.BloqueContext ctx) {
        return construirInstrucciones(ctx.instruccion());
    }

    private Statement construirInstruccion(pigLatinParser.InstruccionContext ctx) {
        if (ctx.declaracion() != null) {
            return construirDeclaracion(ctx.declaracion());
        }
        if (ctx.asignacion() != null) {
            return visitAsignacion(ctx.asignacion());
        }
        if (ctx.incremento() != null) {
            return visitIncremento(ctx.incremento());
        }
        if (ctx.llamada() != null) {
            return new SentenciaExpresion(visitAcceso(ctx.llamada().acceso()), Posicion.de(ctx));
        }
        if (ctx.condicional() != null) {
            return visitCondicional(ctx.condicional());
        }
        if (ctx.cicloDum() != null) {
            return visitCicloDum(ctx.cicloDum());
        }
        if (ctx.cicloFacere() != null) {
            return visitCicloFacere(ctx.cicloFacere());
        }
        if (ctx.cicloPer() != null) {
            return visitCicloPer(ctx.cicloPer());
        }
        if (ctx.continuar() != null) {
            return new Perge(Posicion.de(ctx));
        }
        if (ctx.interrumpir() != null) {
            return new Interrumpe(Posicion.de(ctx));
        }
        if (ctx.lectura() != null) {
            return visitLectura(ctx.lectura());
        }
        return visitEscritura(ctx.escritura());
    }

    @Override
    public Asignacion visitAsignacion(pigLatinParser.AsignacionContext ctx) {
        return new Asignacion(visitAcceso(ctx.acceso()), construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    @Override
    public IncrementoDecremento visitIncremento(pigLatinParser.IncrementoContext ctx) {
        return new IncrementoDecremento(visitAcceso(ctx.acceso()), ctx.INCREMENTO() != null, Posicion.de(ctx));
    }

    @Override
    public Condicional visitCondicional(pigLatinParser.CondicionalContext ctx) {
        List<RamaCondicional> ramas = new ArrayList<>();
        ramas.add(new RamaCondicional(construirExpresion(ctx.expresion()), construirBloque(ctx.bloque()),
                Posicion.de(ctx)));
        for (pigLatinParser.SinoSiContext sinoSi : ctx.sinoSi()) {
            ramas.add(new RamaCondicional(construirExpresion(sinoSi.expresion()), construirBloque(sinoSi.bloque()),
                    Posicion.de(sinoSi)));
        }
        List<Statement> sino = ctx.sino() != null ? construirBloque(ctx.sino().bloque()) : null;
        return new Condicional(ramas, sino, Posicion.de(ctx));
    }

    @Override
    public CicloDum visitCicloDum(pigLatinParser.CicloDumContext ctx) {
        return new CicloDum(construirExpresion(ctx.expresion()), construirBloque(ctx.bloque()), Posicion.de(ctx));
    }

    @Override
    public CicloFacere visitCicloFacere(pigLatinParser.CicloFacereContext ctx) {
        return new CicloFacere(construirBloque(ctx.bloque()), construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    @Override
    public CicloPer visitCicloPer(pigLatinParser.CicloPerContext ctx) {
        Statement inicio;
        if (ctx.inicioPer() instanceof pigLatinParser.InicioPerDeclaracionContext declaracion) {
            inicio = new Declaracion(declaracion.IDENTIFICADOR().getText(), construirTipo(declaracion.tipo()),
                    construirExpresion(declaracion.expresion()), Posicion.de(declaracion));
        } else {
            pigLatinParser.InicioPerAsignacionContext asignacion = (pigLatinParser.InicioPerAsignacionContext) ctx.inicioPer();
            inicio = new Asignacion(visitAcceso(asignacion.acceso()), construirExpresion(asignacion.expresion()),
                    Posicion.de(asignacion));
        }
        pigLatinParser.ActualizacionPerContext actualizacionCtx = ctx.actualizacionPer();
        Statement actualizacion = actualizacionCtx.ASIGNACION() != null
                ? new Asignacion(visitAcceso(actualizacionCtx.acceso()), construirExpresion(actualizacionCtx.expresion()),
                        Posicion.de(actualizacionCtx))
                : new IncrementoDecremento(visitAcceso(actualizacionCtx.acceso()), actualizacionCtx.INCREMENTO() != null,
                        Posicion.de(actualizacionCtx));
        return new CicloPer(inicio, construirExpresion(ctx.expresion()), actualizacion, construirBloque(ctx.bloque()),
                Posicion.de(ctx));
    }

    @Override
    public Lectura visitLectura(pigLatinParser.LecturaContext ctx) {
        Acceso destino = ctx.acceso() != null ? visitAcceso(ctx.acceso()) : null;
        return new Lectura(destino, Posicion.de(ctx));
    }

    @Override
    public Escritura visitEscritura(pigLatinParser.EscrituraContext ctx) {
        return new Escritura(construirExpresiones(ctx.expresion()), Posicion.de(ctx));
    }

    // ---------- Accesos ----------

    @Override
    public Acceso visitAcceso(pigLatinParser.AccesoContext ctx) {
        return new Acceso(ctx.IDENTIFICADOR().getText(), construirSufijos(ctx.sufijo()), Posicion.de(ctx));
    }

    private List<Sufijo> construirSufijos(List<pigLatinParser.SufijoContext> contextos) {
        List<Sufijo> sufijos = new ArrayList<>();
        for (pigLatinParser.SufijoContext sufijo : contextos) {
            sufijos.add((Sufijo) visit(sufijo));
        }
        return sufijos;
    }

    @Override
    public SufijoIndice visitSufijoIndice(pigLatinParser.SufijoIndiceContext ctx) {
        return new SufijoIndice(construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    @Override
    public SufijoAtributo visitSufijoAtributo(pigLatinParser.SufijoAtributoContext ctx) {
        return new SufijoAtributo(ctx.IDENTIFICADOR().getText(), Posicion.de(ctx));
    }

    @Override
    public SufijoLlamada visitSufijoLlamada(pigLatinParser.SufijoLlamadaContext ctx) {
        return new SufijoLlamada(construirArgumentos(ctx.argumentos()), Posicion.de(ctx));
    }

    private NuevoObjeto construirNuevoObjeto(pigLatinParser.CreacionObjetoContext ctx, List<Sufijo> sufijos) {
        return new NuevoObjeto(ctx.IDENTIFICADOR().getText(), construirArgumentos(ctx.argumentos()), sufijos,
                Posicion.de(ctx));
    }

    private List<Expression> construirArgumentos(pigLatinParser.ArgumentosContext ctx) {
        return ctx == null ? new ArrayList<>() : construirExpresiones(ctx.expresion());
    }

    // ---------- Expresiones ----------

    private Expression construirExpresion(pigLatinParser.ExpresionContext ctx) {
        return (Expression) visit(ctx);
    }

    private List<Expression> construirExpresiones(List<pigLatinParser.ExpresionContext> contextos) {
        List<Expression> expresiones = new ArrayList<>();
        for (pigLatinParser.ExpresionContext expresion : contextos) {
            expresiones.add(construirExpresion(expresion));
        }
        return expresiones;
    }

    @Override
    public AstNode visitExpresionParentesis(pigLatinParser.ExpresionParentesisContext ctx) {
        return visit(ctx.expresion());
    }

    @Override
    public AstNode visitExpresionLiteral(pigLatinParser.ExpresionLiteralContext ctx) {
        return visitLiteral(ctx.literal());
    }

    @Override
    public AstNode visitExpresionNuevoObjeto(pigLatinParser.ExpresionNuevoObjetoContext ctx) {
        return construirNuevoObjeto(ctx.creacionObjeto(), construirSufijos(ctx.sufijo()));
    }

    @Override
    public AstNode visitExpresionAcceso(pigLatinParser.ExpresionAccesoContext ctx) {
        return visitAcceso(ctx.acceso());
    }

    @Override
    public AstNode visitExpresionUnaria(pigLatinParser.ExpresionUnariaContext ctx) {
        return new OperacionUnaria(OperadorUnario.desdeTexto(ctx.operador.getText()), construirExpresion(ctx.expresion()),
                Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionMultiplicativa(pigLatinParser.ExpresionMultiplicativaContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionAditiva(pigLatinParser.ExpresionAditivaContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionRelacional(pigLatinParser.ExpresionRelacionalContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionIgualdad(pigLatinParser.ExpresionIgualdadContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionY(pigLatinParser.ExpresionYContext ctx) {
        return binaria(ctx.expresion(0), "&&", ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionO(pigLatinParser.ExpresionOContext ctx) {
        return binaria(ctx.expresion(0), "||", ctx.expresion(1), ctx);
    }

    private OperacionBinaria binaria(pigLatinParser.ExpresionContext izquierda, String operador,
            pigLatinParser.ExpresionContext derecha, pigLatinParser.ExpresionContext ctx) {
        return new OperacionBinaria(construirExpresion(izquierda), OperadorBinario.desdeTexto(operador),
                construirExpresion(derecha), Posicion.de(ctx));
    }

    @Override
    public Literal visitLiteral(pigLatinParser.LiteralContext ctx) {
        Posicion posicion = Posicion.de(ctx);
        if (ctx.ENTERO() != null) {
            return new Literal(TipoPrimitivo.ENTERO, Literales.entero(ctx.getText()), posicion);
        }
        if (ctx.DECIMAL() != null) {
            return new Literal(TipoPrimitivo.DECIMAL, Literales.decimal(ctx.getText()), posicion);
        }
        if (ctx.TEXTO() != null) {
            return new Literal(TipoPrimitivo.CADENA, Literales.cadena(ctx.getText()), posicion);
        }
        if (ctx.CARACTER() != null) {
            return new Literal(TipoPrimitivo.CARACTER, Literales.caracter(ctx.getText()), posicion);
        }
        return new Literal(TipoPrimitivo.BOOLEANO, ctx.VERUM() != null, posicion);
    }
}
