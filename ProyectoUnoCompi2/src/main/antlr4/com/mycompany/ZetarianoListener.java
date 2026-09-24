// Generated from Zetariano.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ZetarianoParser}.
 */
public interface ZetarianoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#compilacion}.
	 * @param ctx the parse tree
	 */
	void enterCompilacion(ZetarianoParser.CompilacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#compilacion}.
	 * @param ctx the parse tree
	 */
	void exitCompilacion(ZetarianoParser.CompilacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaracionClase}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionClase(ZetarianoParser.DeclaracionClaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaracionClase}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionClase(ZetarianoParser.DeclaracionClaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#modificador}.
	 * @param ctx the parse tree
	 */
	void enterModificador(ZetarianoParser.ModificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#modificador}.
	 * @param ctx the parse tree
	 */
	void exitModificador(ZetarianoParser.ModificadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#miembro}.
	 * @param ctx the parse tree
	 */
	void enterMiembro(ZetarianoParser.MiembroContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#miembro}.
	 * @param ctx the parse tree
	 */
	void exitMiembro(ZetarianoParser.MiembroContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaracionAtributo}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionAtributo(ZetarianoParser.DeclaracionAtributoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaracionAtributo}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionAtributo(ZetarianoParser.DeclaracionAtributoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaracionConstructor}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionConstructor(ZetarianoParser.DeclaracionConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaracionConstructor}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionConstructor(ZetarianoParser.DeclaracionConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaracionMetodo}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionMetodo(ZetarianoParser.DeclaracionMetodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaracionMetodo}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionMetodo(ZetarianoParser.DeclaracionMetodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void enterTipoRetorno(ZetarianoParser.TipoRetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void exitTipoRetorno(ZetarianoParser.TipoRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(ZetarianoParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(ZetarianoParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(ZetarianoParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(ZetarianoParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(ZetarianoParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(ZetarianoParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#tipoBase}.
	 * @param ctx the parse tree
	 */
	void enterTipoBase(ZetarianoParser.TipoBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#tipoBase}.
	 * @param ctx the parse tree
	 */
	void exitTipoBase(ZetarianoParser.TipoBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaradorVariable}.
	 * @param ctx the parse tree
	 */
	void enterDeclaradorVariable(ZetarianoParser.DeclaradorVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaradorVariable}.
	 * @param ctx the parse tree
	 */
	void exitDeclaradorVariable(ZetarianoParser.DeclaradorVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#inicializadorVariable}.
	 * @param ctx the parse tree
	 */
	void enterInicializadorVariable(ZetarianoParser.InicializadorVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#inicializadorVariable}.
	 * @param ctx the parse tree
	 */
	void exitInicializadorVariable(ZetarianoParser.InicializadorVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#inicializadorArreglo}.
	 * @param ctx the parse tree
	 */
	void enterInicializadorArreglo(ZetarianoParser.InicializadorArregloContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#inicializadorArreglo}.
	 * @param ctx the parse tree
	 */
	void exitInicializadorArreglo(ZetarianoParser.InicializadorArregloContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaBloque}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaBloque(ZetarianoParser.SentenciaBloqueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaBloque}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaBloque(ZetarianoParser.SentenciaBloqueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaDeclaracion}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaDeclaracion(ZetarianoParser.SentenciaDeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaDeclaracion}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaDeclaracion(ZetarianoParser.SentenciaDeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaIf}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaIf(ZetarianoParser.SentenciaIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaIf}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaIf(ZetarianoParser.SentenciaIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaSwitch}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaSwitch(ZetarianoParser.SentenciaSwitchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaSwitch}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaSwitch(ZetarianoParser.SentenciaSwitchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaFor}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaFor(ZetarianoParser.SentenciaForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaFor}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaFor(ZetarianoParser.SentenciaForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaWhile}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaWhile(ZetarianoParser.SentenciaWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaWhile}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaWhile(ZetarianoParser.SentenciaWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaDoWhile}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaDoWhile(ZetarianoParser.SentenciaDoWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaDoWhile}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaDoWhile(ZetarianoParser.SentenciaDoWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaBreak}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaBreak(ZetarianoParser.SentenciaBreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaBreak}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaBreak(ZetarianoParser.SentenciaBreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaContinue}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaContinue(ZetarianoParser.SentenciaContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaContinue}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaContinue(ZetarianoParser.SentenciaContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaReturn}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaReturn(ZetarianoParser.SentenciaReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaReturn}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaReturn(ZetarianoParser.SentenciaReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaPrintln}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaPrintln(ZetarianoParser.SentenciaPrintlnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaPrintln}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaPrintln(ZetarianoParser.SentenciaPrintlnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaPrint}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaPrint(ZetarianoParser.SentenciaPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaPrint}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaPrint(ZetarianoParser.SentenciaPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaExpresion}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaExpresion(ZetarianoParser.SentenciaExpresionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaExpresion}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaExpresion(ZetarianoParser.SentenciaExpresionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sentenciaVacia}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentenciaVacia(ZetarianoParser.SentenciaVaciaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sentenciaVacia}
	 * labeled alternative in {@link ZetarianoParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentenciaVacia(ZetarianoParser.SentenciaVaciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaracionLocal}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionLocal(ZetarianoParser.DeclaracionLocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaracionLocal}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionLocal(ZetarianoParser.DeclaracionLocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#seccionSwitch}.
	 * @param ctx the parse tree
	 */
	void enterSeccionSwitch(ZetarianoParser.SeccionSwitchContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#seccionSwitch}.
	 * @param ctx the parse tree
	 */
	void exitSeccionSwitch(ZetarianoParser.SeccionSwitchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#etiquetaSwitch}.
	 * @param ctx the parse tree
	 */
	void enterEtiquetaSwitch(ZetarianoParser.EtiquetaSwitchContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#etiquetaSwitch}.
	 * @param ctx the parse tree
	 */
	void exitEtiquetaSwitch(ZetarianoParser.EtiquetaSwitchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#inicioFor}.
	 * @param ctx the parse tree
	 */
	void enterInicioFor(ZetarianoParser.InicioForContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#inicioFor}.
	 * @param ctx the parse tree
	 */
	void exitInicioFor(ZetarianoParser.InicioForContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#listaExpresiones}.
	 * @param ctx the parse tree
	 */
	void enterListaExpresiones(ZetarianoParser.ListaExpresionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#listaExpresiones}.
	 * @param ctx the parse tree
	 */
	void exitListaExpresiones(ZetarianoParser.ListaExpresionesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionMultiplicativa(ZetarianoParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionMultiplicativa(ZetarianoParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionY(ZetarianoParser.ExpresionYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionY(ZetarianoParser.ExpresionYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionPrimaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionPrimaria(ZetarianoParser.ExpresionPrimariaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionPrimaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionPrimaria(ZetarianoParser.ExpresionPrimariaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionPostfija}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionPostfija(ZetarianoParser.ExpresionPostfijaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionPostfija}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionPostfija(ZetarianoParser.ExpresionPostfijaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionAtributo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionAtributo(ZetarianoParser.ExpresionAtributoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionAtributo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionAtributo(ZetarianoParser.ExpresionAtributoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionIndice}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionIndice(ZetarianoParser.ExpresionIndiceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionIndice}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionIndice(ZetarianoParser.ExpresionIndiceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionUnaria(ZetarianoParser.ExpresionUnariaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionUnaria(ZetarianoParser.ExpresionUnariaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionIgualdad(ZetarianoParser.ExpresionIgualdadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionIgualdad(ZetarianoParser.ExpresionIgualdadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionRelacional(ZetarianoParser.ExpresionRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionRelacional(ZetarianoParser.ExpresionRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionTernaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionTernaria(ZetarianoParser.ExpresionTernariaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionTernaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionTernaria(ZetarianoParser.ExpresionTernariaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionLlamadaMetodo(ZetarianoParser.ExpresionLlamadaMetodoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionLlamadaMetodo(ZetarianoParser.ExpresionLlamadaMetodoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionAsignacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionAsignacion(ZetarianoParser.ExpresionAsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionAsignacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionAsignacion(ZetarianoParser.ExpresionAsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionO(ZetarianoParser.ExpresionOContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionO(ZetarianoParser.ExpresionOContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionAditiva(ZetarianoParser.ExpresionAditivaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionAditiva(ZetarianoParser.ExpresionAditivaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioParentesis}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioParentesis(ZetarianoParser.PrimarioParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioParentesis}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioParentesis(ZetarianoParser.PrimarioParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioLiteral}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioLiteral(ZetarianoParser.PrimarioLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioLiteral}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioLiteral(ZetarianoParser.PrimarioLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioThis}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioThis(ZetarianoParser.PrimarioThisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioThis}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioThis(ZetarianoParser.PrimarioThisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioLlamada}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioLlamada(ZetarianoParser.PrimarioLlamadaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioLlamada}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioLlamada(ZetarianoParser.PrimarioLlamadaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioIdentificador}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioIdentificador(ZetarianoParser.PrimarioIdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioIdentificador}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioIdentificador(ZetarianoParser.PrimarioIdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioNuevoObjeto}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioNuevoObjeto(ZetarianoParser.PrimarioNuevoObjetoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioNuevoObjeto}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioNuevoObjeto(ZetarianoParser.PrimarioNuevoObjetoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioNuevoArreglo}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioNuevoArreglo(ZetarianoParser.PrimarioNuevoArregloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioNuevoArreglo}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioNuevoArreglo(ZetarianoParser.PrimarioNuevoArregloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioNuevoArregloInicializado}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioNuevoArregloInicializado(ZetarianoParser.PrimarioNuevoArregloInicializadoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioNuevoArregloInicializado}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioNuevoArregloInicializado(ZetarianoParser.PrimarioNuevoArregloInicializadoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primarioReadln}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void enterPrimarioReadln(ZetarianoParser.PrimarioReadlnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primarioReadln}
	 * labeled alternative in {@link ZetarianoParser#primario}.
	 * @param ctx the parse tree
	 */
	void exitPrimarioReadln(ZetarianoParser.PrimarioReadlnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(ZetarianoParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(ZetarianoParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ZetarianoParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ZetarianoParser.LiteralContext ctx);
}