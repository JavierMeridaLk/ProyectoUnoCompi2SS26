// Generated from y.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link yParser}.
 */
public interface yListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link yParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(yParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(yParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#seccionEstructuras}.
	 * @param ctx the parse tree
	 */
	void enterSeccionEstructuras(yParser.SeccionEstructurasContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#seccionEstructuras}.
	 * @param ctx the parse tree
	 */
	void exitSeccionEstructuras(yParser.SeccionEstructurasContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#seccionFunciones}.
	 * @param ctx the parse tree
	 */
	void enterSeccionFunciones(yParser.SeccionFuncionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#seccionFunciones}.
	 * @param ctx the parse tree
	 */
	void exitSeccionFunciones(yParser.SeccionFuncionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#definicionEstructura}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionEstructura(yParser.DefinicionEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#definicionEstructura}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionEstructura(yParser.DefinicionEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#atributoEstructura}.
	 * @param ctx the parse tree
	 */
	void enterAtributoEstructura(yParser.AtributoEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#atributoEstructura}.
	 * @param ctx the parse tree
	 */
	void exitAtributoEstructura(yParser.AtributoEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionFuncion(yParser.DefinicionFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionFuncion(yParser.DefinicionFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void enterTipoRetorno(yParser.TipoRetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void exitTipoRetorno(yParser.TipoRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(yParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(yParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parametroValor}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametroValor(yParser.ParametroValorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parametroValor}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametroValor(yParser.ParametroValorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parametroArreglo}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametroArreglo(yParser.ParametroArregloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parametroArreglo}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametroArreglo(yParser.ParametroArregloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parametroEstructura}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametroEstructura(yParser.ParametroEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parametroEstructura}
	 * labeled alternative in {@link yParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametroEstructura(yParser.ParametroEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(yParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(yParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(yParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(yParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(yParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(yParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#instruccionSimple}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionSimple(yParser.InstruccionSimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#instruccionSimple}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionSimple(yParser.InstruccionSimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#instruccionCompuesta}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionCompuesta(yParser.InstruccionCompuestaContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#instruccionCompuesta}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionCompuesta(yParser.InstruccionCompuestaContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(yParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(yParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#dimension}.
	 * @param ctx the parse tree
	 */
	void enterDimension(yParser.DimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#dimension}.
	 * @param ctx the parse tree
	 */
	void exitDimension(yParser.DimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#inicializador}.
	 * @param ctx the parse tree
	 */
	void enterInicializador(yParser.InicializadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#inicializador}.
	 * @param ctx the parse tree
	 */
	void exitInicializador(yParser.InicializadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#listaInicializacion}.
	 * @param ctx the parse tree
	 */
	void enterListaInicializacion(yParser.ListaInicializacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#listaInicializacion}.
	 * @param ctx the parse tree
	 */
	void exitListaInicializacion(yParser.ListaInicializacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(yParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(yParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#incremento}.
	 * @param ctx the parse tree
	 */
	void enterIncremento(yParser.IncrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#incremento}.
	 * @param ctx the parse tree
	 */
	void exitIncremento(yParser.IncrementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#retornar}.
	 * @param ctx the parse tree
	 */
	void enterRetornar(yParser.RetornarContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#retornar}.
	 * @param ctx the parse tree
	 */
	void exitRetornar(yParser.RetornarContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#imprimir}.
	 * @param ctx the parse tree
	 */
	void enterImprimir(yParser.ImprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#imprimir}.
	 * @param ctx the parse tree
	 */
	void exitImprimir(yParser.ImprimirContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#leer}.
	 * @param ctx the parse tree
	 */
	void enterLeer(yParser.LeerContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#leer}.
	 * @param ctx the parse tree
	 */
	void exitLeer(yParser.LeerContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaFuncion(yParser.LlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaFuncion(yParser.LlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(yParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(yParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#condicional}.
	 * @param ctx the parse tree
	 */
	void enterCondicional(yParser.CondicionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#condicional}.
	 * @param ctx the parse tree
	 */
	void exitCondicional(yParser.CondicionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#sinoSi}.
	 * @param ctx the parse tree
	 */
	void enterSinoSi(yParser.SinoSiContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#sinoSi}.
	 * @param ctx the parse tree
	 */
	void exitSinoSi(yParser.SinoSiContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#contrario}.
	 * @param ctx the parse tree
	 */
	void enterContrario(yParser.ContrarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#contrario}.
	 * @param ctx the parse tree
	 */
	void exitContrario(yParser.ContrarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#elegir}.
	 * @param ctx the parse tree
	 */
	void enterElegir(yParser.ElegirContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#elegir}.
	 * @param ctx the parse tree
	 */
	void exitElegir(yParser.ElegirContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#caso}.
	 * @param ctx the parse tree
	 */
	void enterCaso(yParser.CasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#caso}.
	 * @param ctx the parse tree
	 */
	void exitCaso(yParser.CasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#casoSiempre}.
	 * @param ctx the parse tree
	 */
	void enterCasoSiempre(yParser.CasoSiempreContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#casoSiempre}.
	 * @param ctx the parse tree
	 */
	void exitCasoSiempre(yParser.CasoSiempreContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#cicloPara}.
	 * @param ctx the parse tree
	 */
	void enterCicloPara(yParser.CicloParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#cicloPara}.
	 * @param ctx the parse tree
	 */
	void exitCicloPara(yParser.CicloParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#inicioPara}.
	 * @param ctx the parse tree
	 */
	void enterInicioPara(yParser.InicioParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#inicioPara}.
	 * @param ctx the parse tree
	 */
	void exitInicioPara(yParser.InicioParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#actualizacionPara}.
	 * @param ctx the parse tree
	 */
	void enterActualizacionPara(yParser.ActualizacionParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#actualizacionPara}.
	 * @param ctx the parse tree
	 */
	void exitActualizacionPara(yParser.ActualizacionParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#cicloMientras}.
	 * @param ctx the parse tree
	 */
	void enterCicloMientras(yParser.CicloMientrasContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#cicloMientras}.
	 * @param ctx the parse tree
	 */
	void exitCicloMientras(yParser.CicloMientrasContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#cicloHacer}.
	 * @param ctx the parse tree
	 */
	void enterCicloHacer(yParser.CicloHacerContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#cicloHacer}.
	 * @param ctx the parse tree
	 */
	void exitCicloHacer(yParser.CicloHacerContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#acceso}.
	 * @param ctx the parse tree
	 */
	void enterAcceso(yParser.AccesoContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#acceso}.
	 * @param ctx the parse tree
	 */
	void exitAcceso(yParser.AccesoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufijoIndice}
	 * labeled alternative in {@link yParser#sufijoAcceso}.
	 * @param ctx the parse tree
	 */
	void enterSufijoIndice(yParser.SufijoIndiceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufijoIndice}
	 * labeled alternative in {@link yParser#sufijoAcceso}.
	 * @param ctx the parse tree
	 */
	void exitSufijoIndice(yParser.SufijoIndiceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufijoAtributo}
	 * labeled alternative in {@link yParser#sufijoAcceso}.
	 * @param ctx the parse tree
	 */
	void enterSufijoAtributo(yParser.SufijoAtributoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufijoAtributo}
	 * labeled alternative in {@link yParser#sufijoAcceso}.
	 * @param ctx the parse tree
	 */
	void exitSufijoAtributo(yParser.SufijoAtributoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionLeer}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionLeer(yParser.ExpresionLeerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionLeer}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionLeer(yParser.ExpresionLeerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionUnaria(yParser.ExpresionUnariaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionUnaria}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionUnaria(yParser.ExpresionUnariaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionIgualdad(yParser.ExpresionIgualdadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionIgualdad}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionIgualdad(yParser.ExpresionIgualdadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionMultiplicativa(yParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionMultiplicativa}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionMultiplicativa(yParser.ExpresionMultiplicativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionY(yParser.ExpresionYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionY}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionY(yParser.ExpresionYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionRelacional(yParser.ExpresionRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionRelacional}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionRelacional(yParser.ExpresionRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionLiteral}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionLiteral(yParser.ExpresionLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionLiteral}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionLiteral(yParser.ExpresionLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionParentesis}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionParentesis(yParser.ExpresionParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionParentesis}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionParentesis(yParser.ExpresionParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionLlamada}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionLlamada(yParser.ExpresionLlamadaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionLlamada}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionLlamada(yParser.ExpresionLlamadaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionAcceso}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionAcceso(yParser.ExpresionAccesoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionAcceso}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionAcceso(yParser.ExpresionAccesoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionO(yParser.ExpresionOContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionO}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionO(yParser.ExpresionOContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresionAditiva(yParser.ExpresionAditivaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expresionAditiva}
	 * labeled alternative in {@link yParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresionAditiva(yParser.ExpresionAditivaContext ctx);
	/**
	 * Enter a parse tree produced by {@link yParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(yParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link yParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(yParser.LiteralContext ctx);
}