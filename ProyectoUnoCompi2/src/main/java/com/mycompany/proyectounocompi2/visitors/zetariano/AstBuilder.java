package com.mycompany.proyectounocompi2.visitors.zetariano;

import com.mycompany.proyectounocompi2.ast.zetariano.*;

import com.mycompany.ZetarianoBaseVisitor;
import com.mycompany.ZetarianoParser;
import com.mycompany.proyectounocompi2.ast.comun.Literales;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import java.util.ArrayList;
import java.util.List;

// Recorre el arbol de ANTLR de un archivo .z y construye su AST.
public class AstBuilder extends ZetarianoBaseVisitor<AstNode> {

    @Override
    public Clase visitCompilacion(ZetarianoParser.CompilacionContext ctx) {
        return visitDeclaracionClase(ctx.declaracionClase());
    }

    // ---------- Clase y miembros ----------

    @Override
    public Clase visitDeclaracionClase(ZetarianoParser.DeclaracionClaseContext ctx) {
        List<Atributo> atributos = new ArrayList<>();
        List<Constructor> constructores = new ArrayList<>();
        List<Metodo> metodos = new ArrayList<>();
        for (ZetarianoParser.MiembroContext miembro : ctx.miembro()) {
            if (miembro.declaracionAtributo() != null) {
                atributos.add(visitDeclaracionAtributo(miembro.declaracionAtributo()));
            } else if (miembro.declaracionConstructor() != null) {
                constructores.add(visitDeclaracionConstructor(miembro.declaracionConstructor()));
            } else {
                metodos.add(visitDeclaracionMetodo(miembro.declaracionMetodo()));
            }
        }
        String padre = ctx.EXTENDS() != null ? ctx.IDENTIFICADOR(1).getText() : null;
        return new Clase(construirModificadores(ctx.modificador()), ctx.IDENTIFICADOR(0).getText(), padre,
                atributos, constructores, metodos, Posicion.de(ctx));
    }

    @Override
    public Atributo visitDeclaracionAtributo(ZetarianoParser.DeclaracionAtributoContext ctx) {
        return new Atributo(construirModificadores(ctx.modificador()), construirTipo(ctx.tipo()),
                construirDeclaradores(ctx.declaradorVariable()), Posicion.de(ctx));
    }

    @Override
    public Constructor visitDeclaracionConstructor(ZetarianoParser.DeclaracionConstructorContext ctx) {
        return new Constructor(construirModificadores(ctx.modificador()), ctx.IDENTIFICADOR().getText(),
                construirParametros(ctx.parametros()), visitBloque(ctx.bloque()), Posicion.de(ctx));
    }

    @Override
    public Metodo visitDeclaracionMetodo(ZetarianoParser.DeclaracionMetodoContext ctx) {
        Tipo tipoRetorno = ctx.tipoRetorno().VOID() != null
                ? Tipo.primitivo(TipoPrimitivo.VOID)
                : construirTipo(ctx.tipoRetorno().tipo());
        return new Metodo(construirModificadores(ctx.modificador()), tipoRetorno, ctx.IDENTIFICADOR().getText(),
                construirParametros(ctx.parametros()), visitBloque(ctx.bloque()), Posicion.de(ctx));
    }

    private List<String> construirModificadores(List<ZetarianoParser.ModificadorContext> contextos) {
        List<String> modificadores = new ArrayList<>();
        for (ZetarianoParser.ModificadorContext modificador : contextos) {
            modificadores.add(modificador.getText());
        }
        return modificadores;
    }

    private List<Parametro> construirParametros(ZetarianoParser.ParametrosContext ctx) {
        List<Parametro> parametros = new ArrayList<>();
        if (ctx != null) {
            for (ZetarianoParser.ParametroContext parametro : ctx.parametro()) {
                parametros.add(new Parametro(construirTipo(parametro.tipo()), parametro.IDENTIFICADOR().getText(),
                        Posicion.de(parametro)));
            }
        }
        return parametros;
    }

    // ---------- Tipos y declaraciones ----------

    private Tipo construirTipo(ZetarianoParser.TipoContext ctx) {
        return construirTipoBase(ctx.tipoBase()).conDimensiones(ctx.CORCHETE_IZQUIERDO().size());
    }

    private Tipo construirTipoBase(ZetarianoParser.TipoBaseContext ctx) {
        if (ctx.INT() != null) {
            return Tipo.primitivo(TipoPrimitivo.ENTERO);
        }
        if (ctx.DOUBLE() != null) {
            return Tipo.primitivo(TipoPrimitivo.DECIMAL);
        }
        if (ctx.STRING() != null) {
            return Tipo.primitivo(TipoPrimitivo.CADENA);
        }
        if (ctx.CHAR() != null) {
            return Tipo.primitivo(TipoPrimitivo.CARACTER);
        }
        if (ctx.BOOLEAN() != null) {
            return Tipo.primitivo(TipoPrimitivo.BOOLEANO);
        }
        return Tipo.clase(ctx.IDENTIFICADOR().getText());
    }

    private List<Declarador> construirDeclaradores(List<ZetarianoParser.DeclaradorVariableContext> contextos) {
        List<Declarador> declaradores = new ArrayList<>();
        for (ZetarianoParser.DeclaradorVariableContext declarador : contextos) {
            Expression valor = declarador.inicializadorVariable() != null
                    ? construirInicializador(declarador.inicializadorVariable())
                    : null;
            declaradores.add(new Declarador(declarador.IDENTIFICADOR().getText(), valor, Posicion.de(declarador)));
        }
        return declaradores;
    }

    private Expression construirInicializador(ZetarianoParser.InicializadorVariableContext ctx) {
        if (ctx.expresion() != null) {
            return construirExpresion(ctx.expresion());
        }
        return visitInicializadorArreglo(ctx.inicializadorArreglo());
    }

    @Override
    public ListaInicializacion visitInicializadorArreglo(ZetarianoParser.InicializadorArregloContext ctx) {
        List<Expression> elementos = new ArrayList<>();
        for (ZetarianoParser.InicializadorVariableContext inicializador : ctx.inicializadorVariable()) {
            elementos.add(construirInicializador(inicializador));
        }
        return new ListaInicializacion(elementos, Posicion.de(ctx));
    }

    @Override
    public DeclaracionLocal visitDeclaracionLocal(ZetarianoParser.DeclaracionLocalContext ctx) {
        return new DeclaracionLocal(construirTipo(ctx.tipo()), construirDeclaradores(ctx.declaradorVariable()),
                Posicion.de(ctx));
    }

    // ---------- Sentencias ----------

    @Override
    public Bloque visitBloque(ZetarianoParser.BloqueContext ctx) {
        return new Bloque(construirSentencias(ctx.sentencia()), Posicion.de(ctx));
    }

    private List<Statement> construirSentencias(List<ZetarianoParser.SentenciaContext> contextos) {
        List<Statement> sentencias = new ArrayList<>();
        for (ZetarianoParser.SentenciaContext sentencia : contextos) {
            sentencias.add(construirSentencia(sentencia));
        }
        return sentencias;
    }

    private Statement construirSentencia(ZetarianoParser.SentenciaContext ctx) {
        return (Statement) visit(ctx);
    }

    @Override
    public AstNode visitSentenciaBloque(ZetarianoParser.SentenciaBloqueContext ctx) {
        return visitBloque(ctx.bloque());
    }

    @Override
    public AstNode visitSentenciaDeclaracion(ZetarianoParser.SentenciaDeclaracionContext ctx) {
        return visitDeclaracionLocal(ctx.declaracionLocal());
    }

    @Override
    public AstNode visitSentenciaIf(ZetarianoParser.SentenciaIfContext ctx) {
        Statement sino = ctx.ELSE() != null ? construirSentencia(ctx.sentencia(1)) : null;
        return new If(construirExpresion(ctx.expresion()), construirSentencia(ctx.sentencia(0)), sino, Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaSwitch(ZetarianoParser.SentenciaSwitchContext ctx) {
        List<SeccionSwitch> secciones = new ArrayList<>();
        for (ZetarianoParser.SeccionSwitchContext seccion : ctx.seccionSwitch()) {
            List<Expression> casos = new ArrayList<>();
            boolean esDefault = false;
            for (ZetarianoParser.EtiquetaSwitchContext etiqueta : seccion.etiquetaSwitch()) {
                if (etiqueta.DEFAULT() != null) {
                    esDefault = true;
                } else {
                    casos.add(construirExpresion(etiqueta.expresion()));
                }
            }
            secciones.add(new SeccionSwitch(casos, esDefault, construirSentencias(seccion.sentencia()),
                    Posicion.de(seccion)));
        }
        return new Switch(construirExpresion(ctx.expresion()), secciones, Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaFor(ZetarianoParser.SentenciaForContext ctx) {
        DeclaracionLocal declaracion = null;
        List<Expression> inicio = new ArrayList<>();
        if (ctx.inicioFor() != null) {
            if (ctx.inicioFor().declaracionLocal() != null) {
                declaracion = visitDeclaracionLocal(ctx.inicioFor().declaracionLocal());
            } else {
                inicio = construirLista(ctx.inicioFor().listaExpresiones());
            }
        }
        Expression condicion = ctx.expresion() != null ? construirExpresion(ctx.expresion()) : null;
        return new For(declaracion, inicio, condicion, construirLista(ctx.listaExpresiones()),
                construirSentencia(ctx.sentencia()), Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaWhile(ZetarianoParser.SentenciaWhileContext ctx) {
        return new While(construirExpresion(ctx.expresion()), construirSentencia(ctx.sentencia()), Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaDoWhile(ZetarianoParser.SentenciaDoWhileContext ctx) {
        return new DoWhile(construirSentencia(ctx.sentencia()), construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaBreak(ZetarianoParser.SentenciaBreakContext ctx) {
        return new Break(Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaContinue(ZetarianoParser.SentenciaContinueContext ctx) {
        return new Continue(Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaReturn(ZetarianoParser.SentenciaReturnContext ctx) {
        return new Return(ctx.expresion() != null ? construirExpresion(ctx.expresion()) : null, Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaPrintln(ZetarianoParser.SentenciaPrintlnContext ctx) {
        return new Println(ctx.expresion() != null ? construirExpresion(ctx.expresion()) : null, Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaPrint(ZetarianoParser.SentenciaPrintContext ctx) {
        return new Print(construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaExpresion(ZetarianoParser.SentenciaExpresionContext ctx) {
        return new SentenciaExpresion(construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    @Override
    public AstNode visitSentenciaVacia(ZetarianoParser.SentenciaVaciaContext ctx) {
        return new Bloque(List.of(), Posicion.de(ctx));
    }

    // ---------- Expresiones ----------

    private Expression construirExpresion(ZetarianoParser.ExpresionContext ctx) {
        return (Expression) visit(ctx);
    }

    private List<Expression> construirLista(ZetarianoParser.ListaExpresionesContext ctx) {
        List<Expression> expresiones = new ArrayList<>();
        if (ctx != null) {
            for (ZetarianoParser.ExpresionContext expresion : ctx.expresion()) {
                expresiones.add(construirExpresion(expresion));
            }
        }
        return expresiones;
    }

    private List<Expression> construirArgumentos(ZetarianoParser.ArgumentosContext ctx) {
        List<Expression> argumentos = new ArrayList<>();
        if (ctx != null) {
            for (ZetarianoParser.ExpresionContext expresion : ctx.expresion()) {
                argumentos.add(construirExpresion(expresion));
            }
        }
        return argumentos;
    }

    @Override
    public AstNode visitExpresionPrimaria(ZetarianoParser.ExpresionPrimariaContext ctx) {
        return visit(ctx.primario());
    }

    @Override
    public AstNode visitExpresionLlamadaMetodo(ZetarianoParser.ExpresionLlamadaMetodoContext ctx) {
        return new LlamadaMetodo(construirExpresion(ctx.expresion()), ctx.IDENTIFICADOR().getText(),
                construirArgumentos(ctx.argumentos()), Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionAtributo(ZetarianoParser.ExpresionAtributoContext ctx) {
        return new AccesoAtributo(construirExpresion(ctx.expresion()), ctx.IDENTIFICADOR().getText(), Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionIndice(ZetarianoParser.ExpresionIndiceContext ctx) {
        return new AccesoIndice(construirExpresion(ctx.expresion(0)), construirExpresion(ctx.expresion(1)),
                Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionPostfija(ZetarianoParser.ExpresionPostfijaContext ctx) {
        return new OperacionPostfija(construirExpresion(ctx.expresion()), ctx.INCREMENTO() != null, Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionUnaria(ZetarianoParser.ExpresionUnariaContext ctx) {
        return new OperacionUnaria(OperadorUnario.desdeTexto(ctx.operador.getText()), construirExpresion(ctx.expresion()),
                Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionMultiplicativa(ZetarianoParser.ExpresionMultiplicativaContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionAditiva(ZetarianoParser.ExpresionAditivaContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionRelacional(ZetarianoParser.ExpresionRelacionalContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionIgualdad(ZetarianoParser.ExpresionIgualdadContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionY(ZetarianoParser.ExpresionYContext ctx) {
        return binaria(ctx.expresion(0), "&&", ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionO(ZetarianoParser.ExpresionOContext ctx) {
        return binaria(ctx.expresion(0), "||", ctx.expresion(1), ctx);
    }

    private OperacionBinaria binaria(ZetarianoParser.ExpresionContext izquierda, String operador,
            ZetarianoParser.ExpresionContext derecha, ZetarianoParser.ExpresionContext ctx) {
        return new OperacionBinaria(construirExpresion(izquierda), OperadorBinario.desdeTexto(operador),
                construirExpresion(derecha), Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionTernaria(ZetarianoParser.ExpresionTernariaContext ctx) {
        return new Ternaria(construirExpresion(ctx.expresion(0)), construirExpresion(ctx.expresion(1)),
                construirExpresion(ctx.expresion(2)), Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionAsignacion(ZetarianoParser.ExpresionAsignacionContext ctx) {
        return new AsignacionExpresion(construirExpresion(ctx.expresion(0)),
                OperadorAsignacion.desdeTexto(ctx.operador.getText()), construirExpresion(ctx.expresion(1)),
                Posicion.de(ctx));
    }

    // ---------- Primarios ----------

    @Override
    public AstNode visitPrimarioParentesis(ZetarianoParser.PrimarioParentesisContext ctx) {
        return visit(ctx.expresion());
    }

    @Override
    public AstNode visitPrimarioLiteral(ZetarianoParser.PrimarioLiteralContext ctx) {
        return visitLiteral(ctx.literal());
    }

    @Override
    public AstNode visitPrimarioThis(ZetarianoParser.PrimarioThisContext ctx) {
        return new This(Posicion.de(ctx));
    }

    // metodo de la misma clase
    @Override
    public AstNode visitPrimarioLlamada(ZetarianoParser.PrimarioLlamadaContext ctx) {
        return new LlamadaMetodo(null, ctx.IDENTIFICADOR().getText(), construirArgumentos(ctx.argumentos()),
                Posicion.de(ctx));
    }

    @Override
    public AstNode visitPrimarioIdentificador(ZetarianoParser.PrimarioIdentificadorContext ctx) {
        return new Identificador(ctx.IDENTIFICADOR().getText(), Posicion.de(ctx));
    }

    @Override
    public AstNode visitPrimarioNuevoObjeto(ZetarianoParser.PrimarioNuevoObjetoContext ctx) {
        return new NuevoObjeto(ctx.IDENTIFICADOR().getText(), construirArgumentos(ctx.argumentos()), Posicion.de(ctx));
    }

    // new int[3][3
    @Override
    public AstNode visitPrimarioNuevoArreglo(ZetarianoParser.PrimarioNuevoArregloContext ctx) {
        List<Expression> tamanos = new ArrayList<>();
        for (ZetarianoParser.ExpresionContext tamano : ctx.expresion()) {
            tamanos.add(construirExpresion(tamano));
        }
        Tipo tipo = construirTipoBase(ctx.tipoBase()).conDimensiones(ctx.CORCHETE_IZQUIERDO().size());
        return new NuevoArreglo(tipo, tamanos, null, Posicion.de(ctx));
    }

    // new int[]{1, 2}
    @Override
    public AstNode visitPrimarioNuevoArregloInicializado(ZetarianoParser.PrimarioNuevoArregloInicializadoContext ctx) {
        Tipo tipo = construirTipoBase(ctx.tipoBase()).conDimensiones(ctx.CORCHETE_IZQUIERDO().size());
        return new NuevoArreglo(tipo, List.of(), visitInicializadorArreglo(ctx.inicializadorArreglo()),
                Posicion.de(ctx));
    }

    @Override
    public AstNode visitPrimarioReadln(ZetarianoParser.PrimarioReadlnContext ctx) {
        return new Readln(Posicion.de(ctx));
    }

    @Override
    public Literal visitLiteral(ZetarianoParser.LiteralContext ctx) {
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
        if (ctx.NULL() != null) {
            return new Literal(TipoPrimitivo.NULO, null, posicion);
        }
        return new Literal(TipoPrimitivo.BOOLEANO, ctx.TRUE() != null, posicion);
    }
}
