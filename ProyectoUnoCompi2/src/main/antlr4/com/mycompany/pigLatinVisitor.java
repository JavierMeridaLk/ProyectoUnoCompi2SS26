// Generated from pigLatin.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link pigLatinParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface pigLatinVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(pigLatinParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#importacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportacion(pigLatinParser.ImportacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#rutaImportacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRutaImportacion(pigLatinParser.RutaImportacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#seccionVariables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionVariables(pigLatinParser.SeccionVariablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#seccionPrincipal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionPrincipal(pigLatinParser.SeccionPrincipalContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(pigLatinParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracionVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracionConTipo}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionConTipo(pigLatinParser.DeclaracionConTipoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracionBooleana}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionBooleana(pigLatinParser.DeclaracionBooleanaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declaracionObjeto}
	 * labeled alternative in {@link pigLatinParser#cuerpoDeclaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionObjeto(pigLatinParser.DeclaracionObjetoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracionArreglo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimension(pigLatinParser.DimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#inicializador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializador(pigLatinParser.InicializadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#listaInicializacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaInicializacion(pigLatinParser.ListaInicializacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(pigLatinParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(pigLatinParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(pigLatinParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(pigLatinParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#incremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncremento(pigLatinParser.IncrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#llamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamada(pigLatinParser.LlamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#continuar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuar(pigLatinParser.ContinuarContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#interrumpir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterrumpir(pigLatinParser.InterrumpirContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#condicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicional(pigLatinParser.CondicionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#sinoSi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinoSi(pigLatinParser.SinoSiContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#sino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSino(pigLatinParser.SinoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#cicloDum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloDum(pigLatinParser.CicloDumContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#cicloFacere}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloFacere(pigLatinParser.CicloFacereContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#cicloPer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloPer(pigLatinParser.CicloPerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inicioPerDeclaracion}
	 * labeled alternative in {@link pigLatinParser#inicioPer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicioPerDeclaracion(pigLatinParser.InicioPerDeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inicioPerAsignacion}
	 * labeled alternative in {@link pigLatinParser#inicioPer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicioPerAsignacion(pigLatinParser.InicioPerAsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#actualizacionPer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualizacionPer(pigLatinParser.ActualizacionPerContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#lectura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLectura(pigLatinParser.LecturaContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#escritura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEscritura(pigLatinParser.EscrituraContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#acceso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcceso(pigLatinParser.AccesoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sufijoIndice}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSufijoIndice(pigLatinParser.SufijoIndiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sufijoAtributo}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSufijoAtributo(pigLatinParser.SufijoAtributoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sufijoLlamada}
	 * labeled alternative in {@link pigLatinParser#sufijo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSufijoLlamada(pigLatinParser.SufijoLlamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(pigLatinParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#creacionObjeto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreacionObjeto(pigLatinParser.CreacionObjetoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionNuevoObjeto}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionNuevoObjeto(pigLatinParser.ExpresionNuevoObjetoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionUnaria(pigLatinParser.ExpresionUnariaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionIgualdad(pigLatinParser.ExpresionIgualdadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionMultiplicativa(pigLatinParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionY(pigLatinParser.ExpresionYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionRelacional(pigLatinParser.ExpresionRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionLiteral}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionLiteral(pigLatinParser.ExpresionLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionParentesis}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionParentesis(pigLatinParser.ExpresionParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionAcceso}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAcceso(pigLatinParser.ExpresionAccesoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionO(pigLatinParser.ExpresionOContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAditiva(pigLatinParser.ExpresionAditivaContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(pigLatinParser.LiteralContext ctx);
}