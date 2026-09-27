package com.mycompany.proyectounocompi2.visitors.y;

import com.mycompany.proyectounocompi2.ast.y.*;

import com.mycompany.proyectounocompi2.ast.comun.Literales;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import com.mycompany.yBaseVisitor;
import com.mycompany.yParser;
import java.util.ArrayList;
import java.util.List;

// Recorre el arbol de ANTLR de un archivo .y y construye su AST.
public class AstBuilder extends yBaseVisitor<AstNode> {

    @Override
    public Programa visitPrograma(yParser.ProgramaContext ctx) {
        List<DefinicionEstructura> estructuras = new ArrayList<>();
        if (ctx.seccionEstructuras() != null) {
            for (yParser.DefinicionEstructuraContext estructura : ctx.seccionEstructuras().definicionEstructura()) {
                estructuras.add(visitDefinicionEstructura(estructura));
            }
        }
        List<DefinicionFuncion> funciones = new ArrayList<>();
        for (yParser.DefinicionFuncionContext funcion : ctx.seccionFunciones().definicionFuncion()) {
            funciones.add(visitDefinicionFuncion(funcion));
        }
        return new Programa(estructuras, funciones, Posicion.de(ctx));
    }

    // ---------- Estructuras ----------

    @Override
    public DefinicionEstructura visitDefinicionEstructura(yParser.DefinicionEstructuraContext ctx) {
        List<AtributoEstructura> atributos = new ArrayList<>();
        for (yParser.AtributoEstructuraContext atributo : ctx.atributoEstructura()) {
            atributos.add(visitAtributoEstructura(atributo));
        }
        return new DefinicionEstructura(ctx.IDENTIFICADOR().getText(), atributos, Posicion.de(ctx));
    }

    @Override
    public AtributoEstructura visitAtributoEstructura(yParser.AtributoEstructuraContext ctx) {
        List<Expression> dimensiones = construirDimensiones(ctx.dimension());
        Tipo tipo = construirTipo(ctx.tipo()).conDimensiones(dimensiones.size());
        return new AtributoEstructura(tipo, ctx.IDENTIFICADOR().getText(), dimensiones, Posicion.de(ctx));
    }

    // ---------- Funciones ----------

    @Override
    public DefinicionFuncion visitDefinicionFuncion(yParser.DefinicionFuncionContext ctx) {
        List<Parametro> parametros = new ArrayList<>();
        if (ctx.parametros() != null) {
            for (yParser.ParametroContext parametro : ctx.parametros().parametro()) {
                parametros.add((Parametro) visit(parametro));
            }
        }
        Tipo tipoRetorno = null;
        if (ctx.tipoRetorno() != null) {
            tipoRetorno = construirTipo(ctx.tipoRetorno().tipo())
                    .conDimensiones(ctx.tipoRetorno().CORCHETE_IZQUIERDO().size());
        }
        return new DefinicionFuncion(ctx.IDENTIFICADOR().getText(), parametros, tipoRetorno,
                construirBloque(ctx.bloque()), Posicion.de(ctx));
    }

    // entero a  (por valor)
    @Override
    public Parametro visitParametroValor(yParser.ParametroValorContext ctx) {
        return new Parametro(construirTipo(ctx.tipo()), ctx.IDENTIFICADOR().getText(), false, Posicion.de(ctx));
    }

    // [] entero arr  (por referencia)
    @Override
    public Parametro visitParametroArreglo(yParser.ParametroArregloContext ctx) {
        Tipo tipo = construirTipo(ctx.tipo()).conDimensiones(ctx.CORCHETE_IZQUIERDO().size());
        return new Parametro(tipo, ctx.IDENTIFICADOR().getText(), true, Posicion.de(ctx));
    }

    // {} Persona p  (por referencia)
    @Override
    public Parametro visitParametroEstructura(yParser.ParametroEstructuraContext ctx) {
        return new Parametro(Tipo.clase(ctx.IDENTIFICADOR(0).getText()), ctx.IDENTIFICADOR(1).getText(), true,
                Posicion.de(ctx));
    }

    private Tipo construirTipo(yParser.TipoContext ctx) {
        if (ctx.ENTERO_T() != null) {
            return Tipo.primitivo(TipoPrimitivo.ENTERO);
        }
        if (ctx.FLOTANTE_T() != null) {
            return Tipo.primitivo(TipoPrimitivo.DECIMAL);
        }
        if (ctx.CADENA_T() != null) {
            return Tipo.primitivo(TipoPrimitivo.CADENA);
        }
        if (ctx.CARACTER_T() != null) {
            return Tipo.primitivo(TipoPrimitivo.CARACTER);
        }
        if (ctx.BOOL_T() != null) {
            return Tipo.primitivo(TipoPrimitivo.BOOLEANO);
        }
        return Tipo.clase(ctx.IDENTIFICADOR().getText());
    }

    // ---------- Instrucciones ----------

    private List<Statement> construirBloque(yParser.BloqueContext ctx) {
        List<Statement> sentencias = new ArrayList<>();
        for (yParser.InstruccionContext instruccion : ctx.instruccion()) {
            sentencias.add(instruccion.instruccionSimple() != null
                    ? construirSimple(instruccion.instruccionSimple())
                    : construirCompuesta(instruccion.instruccionCompuesta()));
        }
        return sentencias;
    }

    private Statement construirSimple(yParser.InstruccionSimpleContext ctx) {
        Posicion posicion = Posicion.de(ctx);
        if (ctx.declaracion() != null) {
            return visitDeclaracion(ctx.declaracion());
        }
        if (ctx.asignacion() != null) {
            return visitAsignacion(ctx.asignacion());
        }
        if (ctx.incremento() != null) {
            return visitIncremento(ctx.incremento());
        }
        if (ctx.llamadaFuncion() != null) {
            return new SentenciaExpresion(visitLlamadaFuncion(ctx.llamadaFuncion()), posicion);
        }
        if (ctx.imprimir() != null) {
            return new Imprimir(construirArgumentos(ctx.imprimir().argumentos()), posicion);
        }
        if (ctx.leer() != null) {
            return new SentenciaExpresion(new Leer(posicion), posicion);
        }
        if (ctx.ROMPER() != null) {
            return new Romper(posicion);
        }
        if (ctx.CONTINUAR() != null) {
            return new Continuar(posicion);
        }
        yParser.RetornarContext retornar = ctx.retornar();
        return new Retornar(retornar.expresion() != null ? construirExpresion(retornar.expresion()) : null, posicion);
    }

    private Statement construirCompuesta(yParser.InstruccionCompuestaContext ctx) {
        if (ctx.definicionEstructura() != null) {
            return visitDefinicionEstructura(ctx.definicionEstructura());
        }
        if (ctx.condicional() != null) {
            return visitCondicional(ctx.condicional());
        }
        if (ctx.elegir() != null) {
            return visitElegir(ctx.elegir());
        }
        if (ctx.cicloPara() != null) {
            return visitCicloPara(ctx.cicloPara());
        }
        if (ctx.cicloMientras() != null) {
            return visitCicloMientras(ctx.cicloMientras());
        }
        return visitCicloHacer(ctx.cicloHacer());
    }

    @Override
    public Declaracion visitDeclaracion(yParser.DeclaracionContext ctx) {
        List<Expression> dimensiones = construirDimensiones(ctx.dimension());
        Tipo tipo = construirTipo(ctx.tipo()).conDimensiones(dimensiones.size());
        Expression valor = ctx.inicializador() != null ? construirInicializador(ctx.inicializador()) : null;
        return new Declaracion(tipo, ctx.IDENTIFICADOR().getText(), dimensiones, valor, Posicion.de(ctx));
    }

    private List<Expression> construirDimensiones(List<yParser.DimensionContext> contextos) {
        List<Expression> dimensiones = new ArrayList<>();
        for (yParser.DimensionContext dimension : contextos) {
            dimensiones.add(construirExpresion(dimension.expresion()));
        }
        return dimensiones;
    }

    private Expression construirInicializador(yParser.InicializadorContext ctx) {
        if (ctx.expresion() != null) {
            return construirExpresion(ctx.expresion());
        }
        return visitListaInicializacion(ctx.listaInicializacion());
    }

    @Override
    public ListaInicializacion visitListaInicializacion(yParser.ListaInicializacionContext ctx) {
        List<Expression> elementos = new ArrayList<>();
        for (yParser.InicializadorContext inicializador : ctx.inicializador()) {
            elementos.add(construirInicializador(inicializador));
        }
        return new ListaInicializacion(elementos, Posicion.de(ctx));
    }

    @Override
    public Asignacion visitAsignacion(yParser.AsignacionContext ctx) {
        return new Asignacion(visitAcceso(ctx.acceso()), construirInicializador(ctx.inicializador()), Posicion.de(ctx));
    }

    @Override
    public IncrementoDecremento visitIncremento(yParser.IncrementoContext ctx) {
        return new IncrementoDecremento(visitAcceso(ctx.acceso()), ctx.INCREMENTO() != null, Posicion.de(ctx));
    }

    @Override
    public Condicional visitCondicional(yParser.CondicionalContext ctx) {
        List<RamaCondicional> ramas = new ArrayList<>();
        ramas.add(new RamaCondicional(construirExpresion(ctx.expresion()), construirBloque(ctx.bloque()),
                Posicion.de(ctx)));
        for (yParser.SinoSiContext sinoSi : ctx.sinoSi()) {
            ramas.add(new RamaCondicional(construirExpresion(sinoSi.expresion()), construirBloque(sinoSi.bloque()),
                    Posicion.de(sinoSi)));
        }
        List<Statement> contrario = ctx.contrario() != null ? construirBloque(ctx.contrario().bloque()) : null;
        return new Condicional(ramas, contrario, Posicion.de(ctx));
    }

    @Override
    public Elegir visitElegir(yParser.ElegirContext ctx) {
        List<Caso> casos = new ArrayList<>();
        for (yParser.CasoContext caso : ctx.caso()) {
            casos.add(new Caso(construirExpresion(caso.expresion()), construirBloque(caso.bloque()), Posicion.de(caso)));
        }
        List<Statement> siempre = ctx.casoSiempre() != null ? construirBloque(ctx.casoSiempre().bloque()) : null;
        return new Elegir(construirExpresion(ctx.expresion()), casos, siempre, Posicion.de(ctx));
    }

    @Override
    public CicloPara visitCicloPara(yParser.CicloParaContext ctx) {
        Statement inicio = null;
        if (ctx.inicioPara() != null) {
            inicio = ctx.inicioPara().declaracion() != null
                    ? visitDeclaracion(ctx.inicioPara().declaracion())
                    : visitAsignacion(ctx.inicioPara().asignacion());
        }
        Expression condicion = ctx.expresion() != null ? construirExpresion(ctx.expresion()) : null;
        Statement actualizacion = null;
        if (ctx.actualizacionPara() != null) {
            actualizacion = ctx.actualizacionPara().incremento() != null
                    ? visitIncremento(ctx.actualizacionPara().incremento())
                    : visitAsignacion(ctx.actualizacionPara().asignacion());
        }
        return new CicloPara(inicio, condicion, actualizacion, construirBloque(ctx.bloque()), Posicion.de(ctx));
    }

    @Override
    public CicloMientras visitCicloMientras(yParser.CicloMientrasContext ctx) {
        return new CicloMientras(construirExpresion(ctx.expresion()), construirBloque(ctx.bloque()), Posicion.de(ctx));
    }

    @Override
    public CicloHacer visitCicloHacer(yParser.CicloHacerContext ctx) {
        return new CicloHacer(construirBloque(ctx.bloque()), construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    // ---------- Accesos y llamadas ----------

    @Override
    public Acceso visitAcceso(yParser.AccesoContext ctx) {
        List<Sufijo> sufijos = new ArrayList<>();
        for (yParser.SufijoAccesoContext sufijo : ctx.sufijoAcceso()) {
            sufijos.add((Sufijo) visit(sufijo));
        }
        return new Acceso(ctx.IDENTIFICADOR().getText(), sufijos, Posicion.de(ctx));
    }

    @Override
    public SufijoIndice visitSufijoIndice(yParser.SufijoIndiceContext ctx) {
        return new SufijoIndice(construirExpresion(ctx.expresion()), Posicion.de(ctx));
    }

    @Override
    public SufijoAtributo visitSufijoAtributo(yParser.SufijoAtributoContext ctx) {
        return new SufijoAtributo(ctx.IDENTIFICADOR().getText(), Posicion.de(ctx));
    }

    @Override
    public LlamadaFuncion visitLlamadaFuncion(yParser.LlamadaFuncionContext ctx) {
        return new LlamadaFuncion(ctx.IDENTIFICADOR().getText(), construirArgumentos(ctx.argumentos()),
                Posicion.de(ctx));
    }

    private List<Expression> construirArgumentos(yParser.ArgumentosContext ctx) {
        List<Expression> argumentos = new ArrayList<>();
        if (ctx != null) {
            for (yParser.ExpresionContext expresion : ctx.expresion()) {
                argumentos.add(construirExpresion(expresion));
            }
        }
        return argumentos;
    }

    // ---------- Expresiones ----------

    private Expression construirExpresion(yParser.ExpresionContext ctx) {
        return (Expression) visit(ctx);
    }

    @Override
    public AstNode visitExpresionParentesis(yParser.ExpresionParentesisContext ctx) {
        return visit(ctx.expresion());
    }

    @Override
    public AstNode visitExpresionLiteral(yParser.ExpresionLiteralContext ctx) {
        return visitLiteral(ctx.literal());
    }

    @Override
    public AstNode visitExpresionLeer(yParser.ExpresionLeerContext ctx) {
        return new Leer(Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionLlamada(yParser.ExpresionLlamadaContext ctx) {
        return visitLlamadaFuncion(ctx.llamadaFuncion());
    }

    @Override
    public AstNode visitExpresionAcceso(yParser.ExpresionAccesoContext ctx) {
        return visitAcceso(ctx.acceso());
    }

    @Override
    public AstNode visitExpresionUnaria(yParser.ExpresionUnariaContext ctx) {
        return new OperacionUnaria(OperadorUnario.desdeTexto(ctx.operador.getText()), construirExpresion(ctx.expresion()),
                Posicion.de(ctx));
    }

    @Override
    public AstNode visitExpresionMultiplicativa(yParser.ExpresionMultiplicativaContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionAditiva(yParser.ExpresionAditivaContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionRelacional(yParser.ExpresionRelacionalContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionIgualdad(yParser.ExpresionIgualdadContext ctx) {
        return binaria(ctx.expresion(0), ctx.operador.getText(), ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionY(yParser.ExpresionYContext ctx) {
        return binaria(ctx.expresion(0), "&&", ctx.expresion(1), ctx);
    }

    @Override
    public AstNode visitExpresionO(yParser.ExpresionOContext ctx) {
        return binaria(ctx.expresion(0), "||", ctx.expresion(1), ctx);
    }

    private OperacionBinaria binaria(yParser.ExpresionContext izquierda, String operador,
            yParser.ExpresionContext derecha, yParser.ExpresionContext ctx) {
        return new OperacionBinaria(construirExpresion(izquierda), OperadorBinario.desdeTexto(operador),
                construirExpresion(derecha), Posicion.de(ctx));
    }

    @Override
    public Literal visitLiteral(yParser.LiteralContext ctx) {
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
        return new Literal(TipoPrimitivo.BOOLEANO, ctx.VERDADERO() != null, posicion);
    }
}
