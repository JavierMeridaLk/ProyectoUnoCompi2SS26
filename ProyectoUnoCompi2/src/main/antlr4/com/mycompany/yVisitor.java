// Generated from y.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link yParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface yVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link yParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(yParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#seccionEstructuras}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionEstructuras(yParser.SeccionEstructurasContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#seccionFunciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionFunciones(yParser.SeccionFuncionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#definicionEstructura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicionEstructura(yParser.DefinicionEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#atributoEstructura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributoEstructura(yParser.AtributoEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#definicionFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicionFuncion(yParser.DefinicionFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#tipoRetorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoRetorno(yParser.TipoRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(yParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parametroValor}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametroValor(yParser.ParametroValorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parametroArreglo}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametroArreglo(yParser.ParametroArregloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parametroEstructura}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametroEstructura(yParser.ParametroEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(yParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(yParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(yParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#instruccionSimple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionSimple(yParser.InstruccionSimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#instruccionCompuesta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionCompuesta(yParser.InstruccionCompuestaContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(yParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimension(yParser.DimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#inicializador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializador(yParser.InicializadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#listaInicializacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaInicializacion(yParser.ListaInicializacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(yParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#incremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncremento(yParser.IncrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#retornar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetornar(yParser.RetornarContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#imprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImprimir(yParser.ImprimirContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#leer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeer(yParser.LeerContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaFuncion(yParser.LlamadaFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(yParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#condicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicional(yParser.CondicionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#sinoSi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinoSi(yParser.SinoSiContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#contrario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContrario(yParser.ContrarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#elegir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElegir(yParser.ElegirContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#caso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso(yParser.CasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#casoSiempre}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasoSiempre(yParser.CasoSiempreContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#cicloPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloPara(yParser.CicloParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#inicioPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicioPara(yParser.InicioParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#actualizacionPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualizacionPara(yParser.ActualizacionParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#cicloMientras}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloMientras(yParser.CicloMientrasContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#cicloHacer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloHacer(yParser.CicloHacerContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#acceso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcceso(yParser.AccesoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sufijoIndice}
	 * labeled alternative in {@link yParser#sufijoAcceso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSufijoIndice(yParser.SufijoIndiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sufijoAtributo}
	 * labeled alternative in {@link yParser#sufijoAcceso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSufijoAtributo(yParser.SufijoAtributoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionLeer}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionLeer(yParser.ExpresionLeerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionUnaria(yParser.ExpresionUnariaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionIgualdad(yParser.ExpresionIgualdadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionMultiplicativa(yParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionY(yParser.ExpresionYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionRelacional(yParser.ExpresionRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionLiteral}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionLiteral(yParser.ExpresionLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionParentesis}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionParentesis(yParser.ExpresionParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionLlamada}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionLlamada(yParser.ExpresionLlamadaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionAcceso}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAcceso(yParser.ExpresionAccesoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionO(yParser.ExpresionOContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionAditiva(yParser.ExpresionAditivaContext ctx);
	/**
	 * Visit a parse tree produced by {@link yParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(yParser.LiteralContext ctx);
}