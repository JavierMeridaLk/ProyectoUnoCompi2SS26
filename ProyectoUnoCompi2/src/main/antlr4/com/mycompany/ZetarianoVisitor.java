// Generated from Zetariano.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ZetarianoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ZetarianoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#compilacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilacion(ZetarianoParser.CompilacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaracionClase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionClase(ZetarianoParser.DeclaracionClaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#modificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificador(ZetarianoParser.ModificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#miembro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembro(ZetarianoParser.MiembroContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaracionAtributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionAtributo(ZetarianoParser.DeclaracionAtributoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaracionConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionConstructor(ZetarianoParser.DeclaracionConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaracionMetodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionMetodo(ZetarianoParser.DeclaracionMetodoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#tipoRetorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoRetorno(ZetarianoParser.TipoRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(ZetarianoParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(ZetarianoParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(ZetarianoParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#tipoBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoBase(ZetarianoParser.TipoBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaradorVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaradorVariable(ZetarianoParser.DeclaradorVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#inicializadorVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializadorVariable(ZetarianoParser.InicializadorVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#inicializadorArreglo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializadorArreglo(ZetarianoParser.InicializadorArregloContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaBloque}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaBloque(ZetarianoParser.SentenciaBloqueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaDeclaracion}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaDeclaracion(ZetarianoParser.SentenciaDeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaIf}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaIf(ZetarianoParser.SentenciaIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaSwitch}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaSwitch(ZetarianoParser.SentenciaSwitchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaFor}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaFor(ZetarianoParser.SentenciaForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaWhile}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaWhile(ZetarianoParser.SentenciaWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaDoWhile}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaDoWhile(ZetarianoParser.SentenciaDoWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaBreak}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaBreak(ZetarianoParser.SentenciaBreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaContinue}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaContinue(ZetarianoParser.SentenciaContinueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaReturn}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaReturn(ZetarianoParser.SentenciaReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaPrintln}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaPrintln(ZetarianoParser.SentenciaPrintlnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaPrint}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaPrint(ZetarianoParser.SentenciaPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaExpresion}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaExpresion(ZetarianoParser.SentenciaExpresionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sentenciaVacia}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentenciaVacia(ZetarianoParser.SentenciaVaciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaracionLocal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionLocal(ZetarianoParser.DeclaracionLocalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#seccionSwitch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionSwitch(ZetarianoParser.SeccionSwitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#etiquetaSwitch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEtiquetaSwitch(ZetarianoParser.EtiquetaSwitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#inicioFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicioFor(ZetarianoParser.InicioForContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#listaExpresiones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaExpresiones(ZetarianoParser.ListaExpresionesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionMultiplicativa(ZetarianoParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionY(ZetarianoParser.ExpresionYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionPrimaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionPrimaria(ZetarianoParser.ExpresionPrimariaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionPostfija}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionPostfija(ZetarianoParser.ExpresionPostfijaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionAtributo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAtributo(ZetarianoParser.ExpresionAtributoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionIndice}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionIndice(ZetarianoParser.ExpresionIndiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionUnaria(ZetarianoParser.ExpresionUnariaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionIgualdad(ZetarianoParser.ExpresionIgualdadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionRelacional(ZetarianoParser.ExpresionRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionTernaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionTernaria(ZetarianoParser.ExpresionTernariaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionLlamadaMetodo(ZetarianoParser.ExpresionLlamadaMetodoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionAsignacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAsignacion(ZetarianoParser.ExpresionAsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionO(ZetarianoParser.ExpresionOContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAditiva(ZetarianoParser.ExpresionAditivaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioParentesis}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioParentesis(ZetarianoParser.PrimarioParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioLiteral}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioLiteral(ZetarianoParser.PrimarioLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioThis}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioThis(ZetarianoParser.PrimarioThisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioLlamada}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioLlamada(ZetarianoParser.PrimarioLlamadaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioIdentificador}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioIdentificador(ZetarianoParser.PrimarioIdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioNuevoObjeto}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioNuevoObjeto(ZetarianoParser.PrimarioNuevoObjetoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioNuevoArreglo}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioNuevoArreglo(ZetarianoParser.PrimarioNuevoArregloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioNuevoArregloInicializado}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioNuevoArregloInicializado(ZetarianoParser.PrimarioNuevoArregloInicializadoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primarioReadln}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimarioReadln(ZetarianoParser.PrimarioReadlnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(ZetarianoParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ZetarianoParser.LiteralContext ctx);
}