// Generated from pigLatin.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link pigLatinParser}.
 */
public interface pigLatinListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(pigLatinParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(pigLatinParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#importacion}.
	 * @param ctx the parse tree
	 */
	void enterImportacion(pigLatinParser.ImportacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#importacion}.
	 * @param ctx the parse tree
	 */
	void exitImportacion(pigLatinParser.ImportacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#rutaImportacion}.
	 * @param ctx the parse tree
	 */
	void enterRutaImportacion(pigLatinParser.RutaImportacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#rutaImportacion}.
	 * @param ctx the parse tree
	 */
	void exitRutaImportacion(pigLatinParser.RutaImportacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#seccionVariables}.
	 * @param ctx the parse tree
	 */
	void enterSeccionVariables(pigLatinParser.SeccionVariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#seccionVariables}.
	 * @param ctx the parse tree
	 */
	void exitSeccionVariables(pigLatinParser.SeccionVariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#seccionPrincipal}.
	 * @param ctx the parse tree
	 */
	void enterSeccionPrincipal(pigLatinParser.SeccionPrincipalContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#seccionPrincipal}.
	 * @param ctx the parse tree
	 */
	void exitSeccionPrincipal(pigLatinParser.SeccionPrincipalContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(pigLatinParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(pigLatinParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracionVariable}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracionVariable}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaracionConTipo}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionConTipo(pigLatinParser.DeclaracionConTipoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaracionConTipo}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionConTipo(pigLatinParser.DeclaracionConTipoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaracionBooleana}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionBooleana(pigLatinParser.DeclaracionBooleanaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaracionBooleana}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionBooleana(pigLatinParser.DeclaracionBooleanaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaracionObjeto}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionObjeto(pigLatinParser.DeclaracionObjetoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaracionObjeto}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionObjeto(pigLatinParser.DeclaracionObjetoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracionArreglo}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracionArreglo}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#dimension}.
	 * @param ctx the parse tree
	 */
	void enterDimension(pigLatinParser.DimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#dimension}.
	 * @param ctx the parse tree
	 */
	void exitDimension(pigLatinParser.DimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#inicializador}.
	 * @param ctx the parse tree
	 */
	void enterInicializador(pigLatinParser.InicializadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#inicializador}.
	 * @param ctx the parse tree
	 */
	void exitInicializador(pigLatinParser.InicializadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#listaInicializacion}.
	 * @param ctx the parse tree
	 */
	void enterListaInicializacion(pigLatinParser.ListaInicializacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#listaInicializacion}.
	 * @param ctx the parse tree
	 */
	void exitListaInicializacion(pigLatinParser.ListaInicializacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(pigLatinParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(pigLatinParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(pigLatinParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(pigLatinParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(pigLatinParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(pigLatinParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(pigLatinParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(pigLatinParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#incremento}.
	 * @param ctx the parse tree
	 */
	void enterIncremento(pigLatinParser.IncrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#incremento}.
	 * @param ctx the parse tree
	 */
	void exitIncremento(pigLatinParser.IncrementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#llamada}.
	 * @param ctx the parse tree
	 */
	void enterLlamada(pigLatinParser.LlamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#llamada}.
	 * @param ctx the parse tree
	 */
	void exitLlamada(pigLatinParser.LlamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#continuar}.
	 * @param ctx the parse tree
	 */
	void enterContinuar(pigLatinParser.ContinuarContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#continuar}.
	 * @param ctx the parse tree
	 */
	void exitContinuar(pigLatinParser.ContinuarContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#interrumpir}.
	 * @param ctx the parse tree
	 */
	void enterInterrumpir(pigLatinParser.InterrumpirContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#interrumpir}.
	 * @param ctx the parse tree
	 */
	void exitInterrumpir(pigLatinParser.InterrumpirContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#condicional}.
	 * @param ctx the parse tree
	 */
	void enterCondicional(pigLatinParser.CondicionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#condicional}.
	 * @param ctx the parse tree
	 */
	void exitCondicional(pigLatinParser.CondicionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#sinoSi}.
	 * @param ctx the parse tree
	 */
	void enterSinoSi(pigLatinParser.SinoSiContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#sinoSi}.
	 * @param ctx the parse tree
	 */
	void exitSinoSi(pigLatinParser.SinoSiContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#sino}.
	 * @param ctx the parse tree
	 */
	void enterSino(pigLatinParser.SinoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#sino}.
	 * @param ctx the parse tree
	 */
	void exitSino(pigLatinParser.SinoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#cicloDum}.
	 * @param ctx the parse tree
	 */
	void enterCicloDum(pigLatinParser.CicloDumContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#cicloDum}.
	 * @param ctx the parse tree
	 */
	void exitCicloDum(pigLatinParser.CicloDumContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#cicloFacere}.
	 * @param ctx the parse tree
	 */
	void enterCicloFacere(pigLatinParser.CicloFacereContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#cicloFacere}.
	 * @param ctx the parse tree
	 */
	void exitCicloFacere(pigLatinParser.CicloFacereContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#cicloPer}.
	 * @param ctx the parse tree
	 */
	void enterCicloPer(pigLatinParser.CicloPerContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#cicloPer}.
	 * @param ctx the parse tree
	 */
	void exitCicloPer(pigLatinParser.CicloPerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inicioPerDeclaracion}
	 * labeled alternative in {@link pigLatinParser#inicioPer}.
	 * @param ctx the parse tree
	 */
	void enterInicioPerDeclaracion(pigLatinParser.InicioPerDeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inicioPerDeclaracion}
	 * labeled alternative in {@link pigLatinParser#inicioPer}.
	 * @param ctx the parse tree
	 */
	void exitInicioPerDeclaracion(pigLatinParser.InicioPerDeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inicioPerAsignacion}
	 * labeled alternative in {@link pigLatinParser#inicioPer}.
	 * @param ctx the parse tree
	 */
	void enterInicioPerAsignacion(pigLatinParser.InicioPerAsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inicioPerAsignacion}
	 * labeled alternative in {@link pigLatinParser#inicioPer}.
	 * @param ctx the parse tree
	 */
	void exitInicioPerAsignacion(pigLatinParser.InicioPerAsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#actualizacionPer}.
	 * @param ctx the parse tree
	 */
	void enterActualizacionPer(pigLatinParser.ActualizacionPerContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#actualizacionPer}.
	 * @param ctx the parse tree
	 */
	void exitActualizacionPer(pigLatinParser.ActualizacionPerContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#lectura}.
	 * @param ctx the parse tree
	 */
	void enterLectura(pigLatinParser.LecturaContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#lectura}.
	 * @param ctx the parse tree
	 */
	void exitLectura(pigLatinParser.LecturaContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#escritura}.
	 * @param ctx the parse tree
	 */
	void enterEscritura(pigLatinParser.EscrituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#escritura}.
	 * @param ctx the parse tree
	 */
	void exitEscritura(pigLatinParser.EscrituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#acceso}.
	 * @param ctx the parse tree
	 */
	void enterAcceso(pigLatinParser.AccesoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#acceso}.
	 * @param ctx the parse tree
	 */
	void exitAcceso(pigLatinParser.AccesoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufijoIndice}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 */
	void enterSufijoIndice(pigLatinParser.SufijoIndiceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufijoIndice}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 */
	void exitSufijoIndice(pigLatinParser.SufijoIndiceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufijoAtributo}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 */
	void enterSufijoAtributo(pigLatinParser.SufijoAtributoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufijoAtributo}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 */
	void exitSufijoAtributo(pigLatinParser.SufijoAtributoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufijoLlamada}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 */
	void enterSufijoLlamada(pigLatinParser.SufijoLlamadaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufijoLlamada}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 */
	void exitSufijoLlamada(pigLatinParser.SufijoLlamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(pigLatinParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(pigLatinParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#creacionObjeto}.
	 * @param ctx the parse tree
	 */
	void enterCreacionObjeto(pigLatinParser.CreacionObjetoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#creacionObjeto}.
	 * @param ctx the parse tree
	 */
	void exitCreacionObjeto(pigLatinParser.CreacionObjetoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionNuevoObjeto}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionNuevoObjeto(pigLatinParser.ExpresionNuevoObjetoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionNuevoObjeto}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionNuevoObjeto(pigLatinParser.ExpresionNuevoObjetoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionUnaria(pigLatinParser.ExpresionUnariaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionUnaria(pigLatinParser.ExpresionUnariaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionIgualdad(pigLatinParser.ExpresionIgualdadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionIgualdad(pigLatinParser.ExpresionIgualdadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionMultiplicativa(pigLatinParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionMultiplicativa(pigLatinParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionY(pigLatinParser.ExpresionYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionY(pigLatinParser.ExpresionYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionRelacional(pigLatinParser.ExpresionRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionRelacional(pigLatinParser.ExpresionRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionLiteral}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionLiteral(pigLatinParser.ExpresionLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionLiteral}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionLiteral(pigLatinParser.ExpresionLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionParentesis}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionParentesis(pigLatinParser.ExpresionParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionParentesis}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionParentesis(pigLatinParser.ExpresionParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionAcceso}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionAcceso(pigLatinParser.ExpresionAccesoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionAcceso}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionAcceso(pigLatinParser.ExpresionAccesoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionO(pigLatinParser.ExpresionOContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionO(pigLatinParser.ExpresionOContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionAditiva(pigLatinParser.ExpresionAditivaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionAditiva(pigLatinParser.ExpresionAditivaContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(pigLatinParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(pigLatinParser.LiteralContext ctx);
}