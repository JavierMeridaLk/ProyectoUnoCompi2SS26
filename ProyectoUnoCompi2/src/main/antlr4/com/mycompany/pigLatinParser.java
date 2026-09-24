// Generated from pigLatin.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class pigLatinParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IMPORT=1, ESTO=2, NUMERUS=3, TEXTUM=4, DECIMALIS=5, LITTERA=6, VERUM=7, 
		FALSUS=8, BOOL=9, SERIES=10, NOVUS=11, SI=12, ALITER=13, DUM=14, FACERE=15, 
		PER=16, PERGE=17, INTERRUMPE=18, NEGACION=19, FINIS=20, VARIABILES=21, 
		MAIOR=22, FIN_PROGRAMA=23, IGUAL_QUE=24, DIFERENTE_DE=25, MENOR_O_IGUAL_QUE=26, 
		MAYOR_O_IGUAL_QUE=27, Y_LOGICO=28, O_LOGICO=29, NO_LOGICO=30, INCREMENTO=31, 
		DECREMENTO=32, LEER=33, IMPRIMIR=34, MENOR_QUE=35, MAYOR_QUE=36, SUMA=37, 
		RESTA=38, MULTIPLICACION=39, DIVISION=40, ASIGNACION=41, DOS_PUNTOS=42, 
		PUNTO_Y_COMA=43, COMA=44, PUNTO=45, CORCHETE_IZQUIERDO=46, CORCHETE_DERECHO=47, 
		LLAVE_IZQUIERDA=48, LLAVE_DERECHA=49, PARENTESIS_IZQUIERDO=50, PARENTESIS_DERECHO=51, 
		DECIMAL=52, ENTERO=53, TEXTO=54, CARACTER=55, IDENTIFICADOR=56, COMENTARIO_DE_BLOQUE=57, 
		COMENTARIO_DE_LINEA=58, ESPACIOS_EN_BLANCO=59, COMENTARIO_SIN_CERRAR=60, 
		TEXTO_SIN_CERRAR=61, CARACTER_INVALIDO=62, ERROR_LEXICO=63;
	public static final int
		RULE_programa = 0, RULE_importacion = 1, RULE_rutaImportacion = 2, RULE_seccionVariables = 3, 
		RULE_seccionPrincipal = 4, RULE_declaracion = 5, RULE_declaracionVariable = 6, 
		RULE_cuerpoDeclaracion = 7, RULE_declaracionArreglo = 8, RULE_dimension = 9, 
		RULE_inicializador = 10, RULE_listaInicializacion = 11, RULE_tipo = 12, 
		RULE_bloque = 13, RULE_instruccion = 14, RULE_asignacion = 15, RULE_incremento = 16, 
		RULE_llamada = 17, RULE_continuar = 18, RULE_interrumpir = 19, RULE_condicional = 20, 
		RULE_sinoSi = 21, RULE_sino = 22, RULE_cicloDum = 23, RULE_cicloFacere = 24, 
		RULE_cicloPer = 25, RULE_inicioPer = 26, RULE_actualizacionPer = 27, RULE_lectura = 28, 
		RULE_escritura = 29, RULE_acceso = 30, RULE_sufijo = 31, RULE_argumentos = 32, 
		RULE_creacionObjeto = 33, RULE_expresion = 34, RULE_literal = 35;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "importacion", "rutaImportacion", "seccionVariables", "seccionPrincipal", 
			"declaracion", "declaracionVariable", "cuerpoDeclaracion", "declaracionArreglo", 
			"dimension", "inicializador", "listaInicializacion", "tipo", "bloque", 
			"instruccion", "asignacion", "incremento", "llamada", "continuar", "interrumpir", 
			"condicional", "sinoSi", "sino", "cicloDum", "cicloFacere", "cicloPer", 
			"inicioPer", "actualizacionPer", "lectura", "escritura", "acceso", "sufijo", 
			"argumentos", "creacionObjeto", "expresion", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", "'esto'", "'numerus'", "'textum'", "'decimalis'", "'littera'", 
			"'verum'", "'falsus'", "'bool'", "'series'", "'novus'", "'si'", "'aliter'", 
			"'dum'", "'facere'", "'per'", "'perge'", "'interrumpe'", "'non'", "'finis'", 
			"'VARIABILES'", "'MAIOR'", "'FINIS'", "'=='", "'!='", "'<='", "'>='", 
			"'&&'", "'||'", "'!'", "'++'", "'--'", "'<<'", "'>>'", "'<'", "'>'", 
			"'+'", "'-'", "'*'", "'/'", "'='", "':'", "';'", "','", "'.'", "'['", 
			"']'", "'{'", "'}'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IMPORT", "ESTO", "NUMERUS", "TEXTUM", "DECIMALIS", "LITTERA", 
			"VERUM", "FALSUS", "BOOL", "SERIES", "NOVUS", "SI", "ALITER", "DUM", 
			"FACERE", "PER", "PERGE", "INTERRUMPE", "NEGACION", "FINIS", "VARIABILES", 
			"MAIOR", "FIN_PROGRAMA", "IGUAL_QUE", "DIFERENTE_DE", "MENOR_O_IGUAL_QUE", 
			"MAYOR_O_IGUAL_QUE", "Y_LOGICO", "O_LOGICO", "NO_LOGICO", "INCREMENTO", 
			"DECREMENTO", "LEER", "IMPRIMIR", "MENOR_QUE", "MAYOR_QUE", "SUMA", "RESTA", 
			"MULTIPLICACION", "DIVISION", "ASIGNACION", "DOS_PUNTOS", "PUNTO_Y_COMA", 
			"COMA", "PUNTO", "CORCHETE_IZQUIERDO", "CORCHETE_DERECHO", "LLAVE_IZQUIERDA", 
			"LLAVE_DERECHA", "PARENTESIS_IZQUIERDO", "PARENTESIS_DERECHO", "DECIMAL", 
			"ENTERO", "TEXTO", "CARACTER", "IDENTIFICADOR", "COMENTARIO_DE_BLOQUE", 
			"COMENTARIO_DE_LINEA", "ESPACIOS_EN_BLANCO", "COMENTARIO_SIN_CERRAR", 
			"TEXTO_SIN_CERRAR", "CARACTER_INVALIDO", "ERROR_LEXICO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "pigLatin.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public pigLatinParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public SeccionPrincipalContext seccionPrincipal() {
			return getRuleContext(SeccionPrincipalContext.class,0);
		}
		public TerminalNode EOF() { return getToken(pigLatinParser.EOF, 0); }
		public List<ImportacionContext> importacion() {
			return getRuleContexts(ImportacionContext.class);
		}
		public ImportacionContext importacion(int i) {
			return getRuleContext(ImportacionContext.class,i);
		}
		public SeccionVariablesContext seccionVariables() {
			return getRuleContext(SeccionVariablesContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(72);
				importacion();
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABILES) {
				{
				setState(78);
				seccionVariables();
				}
			}

			setState(81);
			seccionPrincipal();
			setState(82);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportacionContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(pigLatinParser.IMPORT, 0); }
		public RutaImportacionContext rutaImportacion() {
			return getRuleContext(RutaImportacionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public ImportacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterImportacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitImportacion(this);
		}
	}

	public final ImportacionContext importacion() throws RecognitionException {
		ImportacionContext _localctx = new ImportacionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_importacion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(IMPORT);
			setState(85);
			rutaImportacion();
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNTO_Y_COMA) {
				{
				setState(86);
				match(PUNTO_Y_COMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RutaImportacionContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFICADOR() { return getTokens(pigLatinParser.IDENTIFICADOR); }
		public TerminalNode IDENTIFICADOR(int i) {
			return getToken(pigLatinParser.IDENTIFICADOR, i);
		}
		public List<TerminalNode> PUNTO() { return getTokens(pigLatinParser.PUNTO); }
		public TerminalNode PUNTO(int i) {
			return getToken(pigLatinParser.PUNTO, i);
		}
		public RutaImportacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rutaImportacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterRutaImportacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitRutaImportacion(this);
		}
	}

	public final RutaImportacionContext rutaImportacion() throws RecognitionException {
		RutaImportacionContext _localctx = new RutaImportacionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_rutaImportacion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(IDENTIFICADOR);
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(90);
				match(PUNTO);
				setState(91);
				match(IDENTIFICADOR);
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PUNTO );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeccionVariablesContext extends ParserRuleContext {
		public TerminalNode VARIABILES() { return getToken(pigLatinParser.VARIABILES, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(pigLatinParser.MAYOR_QUE, 0); }
		public List<DeclaracionContext> declaracion() {
			return getRuleContexts(DeclaracionContext.class);
		}
		public DeclaracionContext declaracion(int i) {
			return getRuleContext(DeclaracionContext.class,i);
		}
		public SeccionVariablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccionVariables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSeccionVariables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSeccionVariables(this);
		}
	}

	public final SeccionVariablesContext seccionVariables() throws RecognitionException {
		SeccionVariablesContext _localctx = new SeccionVariablesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_seccionVariables);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(VARIABILES);
			setState(97);
			match(MAYOR_QUE);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ESTO || _la==SERIES) {
				{
				{
				setState(98);
				declaracion();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeccionPrincipalContext extends ParserRuleContext {
		public TerminalNode MAIOR() { return getToken(pigLatinParser.MAIOR, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(pigLatinParser.MAYOR_QUE, 0); }
		public TerminalNode FIN_PROGRAMA() { return getToken(pigLatinParser.FIN_PROGRAMA, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public SeccionPrincipalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccionPrincipal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSeccionPrincipal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSeccionPrincipal(this);
		}
	}

	public final SeccionPrincipalContext seccionPrincipal() throws RecognitionException {
		SeccionPrincipalContext _localctx = new SeccionPrincipalContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_seccionPrincipal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(MAIOR);
			setState(105);
			match(MAYOR_QUE);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057619808244740L) != 0)) {
				{
				{
				setState(106);
				instruccion();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
			match(FIN_PROGRAMA);
			setState(113);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionContext extends ParserRuleContext {
		public DeclaracionVariableContext declaracionVariable() {
			return getRuleContext(DeclaracionVariableContext.class,0);
		}
		public DeclaracionArregloContext declaracionArreglo() {
			return getRuleContext(DeclaracionArregloContext.class,0);
		}
		public DeclaracionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracion(this);
		}
	}

	public final DeclaracionContext declaracion() throws RecognitionException {
		DeclaracionContext _localctx = new DeclaracionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_declaracion);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESTO:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				declaracionVariable();
				}
				break;
			case SERIES:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				declaracionArreglo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionVariableContext extends ParserRuleContext {
		public TerminalNode ESTO() { return getToken(pigLatinParser.ESTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(pigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(pigLatinParser.DOS_PUNTOS, 0); }
		public CuerpoDeclaracionContext cuerpoDeclaracion() {
			return getRuleContext(CuerpoDeclaracionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public DeclaracionVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionVariable(this);
		}
	}

	public final DeclaracionVariableContext declaracionVariable() throws RecognitionException {
		DeclaracionVariableContext _localctx = new DeclaracionVariableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declaracionVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(ESTO);
			setState(120);
			match(IDENTIFICADOR);
			setState(121);
			match(DOS_PUNTOS);
			setState(122);
			cuerpoDeclaracion();
			setState(123);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CuerpoDeclaracionContext extends ParserRuleContext {
		public CuerpoDeclaracionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cuerpoDeclaracion; }
	 
		public CuerpoDeclaracionContext() { }
		public void copyFrom(CuerpoDeclaracionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionConTipoContext extends CuerpoDeclaracionContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public InicializadorContext inicializador() {
			return getRuleContext(InicializadorContext.class,0);
		}
		public DeclaracionConTipoContext(CuerpoDeclaracionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionConTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionConTipo(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionBooleanaContext extends CuerpoDeclaracionContext {
		public TerminalNode VERUM() { return getToken(pigLatinParser.VERUM, 0); }
		public TerminalNode FALSUS() { return getToken(pigLatinParser.FALSUS, 0); }
		public DeclaracionBooleanaContext(CuerpoDeclaracionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionBooleana(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionBooleana(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionObjetoContext extends CuerpoDeclaracionContext {
		public CreacionObjetoContext creacionObjeto() {
			return getRuleContext(CreacionObjetoContext.class,0);
		}
		public DeclaracionObjetoContext(CuerpoDeclaracionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionObjeto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionObjeto(this);
		}
	}

	public final CuerpoDeclaracionContext cuerpoDeclaracion() throws RecognitionException {
		CuerpoDeclaracionContext _localctx = new CuerpoDeclaracionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cuerpoDeclaracion);
		int _la;
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMERUS:
			case TEXTUM:
			case DECIMALIS:
			case LITTERA:
			case BOOL:
			case IDENTIFICADOR:
				_localctx = new DeclaracionConTipoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				tipo();
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141019239284214144L) != 0)) {
					{
					setState(126);
					inicializador();
					}
				}

				}
				break;
			case VERUM:
			case FALSUS:
				_localctx = new DeclaracionBooleanaContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				_la = _input.LA(1);
				if ( !(_la==VERUM || _la==FALSUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case NOVUS:
				_localctx = new DeclaracionObjetoContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				creacionObjeto();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionArregloContext extends ParserRuleContext {
		public TerminalNode SERIES() { return getToken(pigLatinParser.SERIES, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(pigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(pigLatinParser.DOS_PUNTOS, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public List<DimensionContext> dimension() {
			return getRuleContexts(DimensionContext.class);
		}
		public DimensionContext dimension(int i) {
			return getRuleContext(DimensionContext.class,i);
		}
		public InicializadorContext inicializador() {
			return getRuleContext(InicializadorContext.class,0);
		}
		public DeclaracionArregloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionArreglo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionArreglo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionArreglo(this);
		}
	}

	public final DeclaracionArregloContext declaracionArreglo() throws RecognitionException {
		DeclaracionArregloContext _localctx = new DeclaracionArregloContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaracionArreglo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(SERIES);
			setState(134);
			match(IDENTIFICADOR);
			setState(136); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135);
				dimension();
				}
				}
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORCHETE_IZQUIERDO );
			setState(140);
			match(DOS_PUNTOS);
			setState(141);
			tipo();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141019239284214144L) != 0)) {
				{
				setState(142);
				inicializador();
				}
			}

			setState(145);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DimensionContext extends ParserRuleContext {
		public TerminalNode CORCHETE_IZQUIERDO() { return getToken(pigLatinParser.CORCHETE_IZQUIERDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DERECHO() { return getToken(pigLatinParser.CORCHETE_DERECHO, 0); }
		public DimensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDimension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDimension(this);
		}
	}

	public final DimensionContext dimension() throws RecognitionException {
		DimensionContext _localctx = new DimensionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dimension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(CORCHETE_IZQUIERDO);
			setState(148);
			expresion(0);
			setState(149);
			match(CORCHETE_DERECHO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InicializadorContext extends ParserRuleContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ListaInicializacionContext listaInicializacion() {
			return getRuleContext(ListaInicializacionContext.class,0);
		}
		public InicializadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inicializador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInicializador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInicializador(this);
		}
	}

	public final InicializadorContext inicializador() throws RecognitionException {
		InicializadorContext _localctx = new InicializadorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_inicializador);
		try {
			setState(153);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VERUM:
			case FALSUS:
			case NOVUS:
			case NEGACION:
			case NO_LOGICO:
			case RESTA:
			case PARENTESIS_IZQUIERDO:
			case DECIMAL:
			case ENTERO:
			case TEXTO:
			case CARACTER:
			case IDENTIFICADOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				expresion(0);
				}
				break;
			case LLAVE_IZQUIERDA:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				listaInicializacion();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaInicializacionContext extends ParserRuleContext {
		public TerminalNode LLAVE_IZQUIERDA() { return getToken(pigLatinParser.LLAVE_IZQUIERDA, 0); }
		public TerminalNode LLAVE_DERECHA() { return getToken(pigLatinParser.LLAVE_DERECHA, 0); }
		public List<InicializadorContext> inicializador() {
			return getRuleContexts(InicializadorContext.class);
		}
		public InicializadorContext inicializador(int i) {
			return getRuleContext(InicializadorContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(pigLatinParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(pigLatinParser.COMA, i);
		}
		public ListaInicializacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaInicializacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterListaInicializacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitListaInicializacion(this);
		}
	}

	public final ListaInicializacionContext listaInicializacion() throws RecognitionException {
		ListaInicializacionContext _localctx = new ListaInicializacionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_listaInicializacion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(LLAVE_IZQUIERDA);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 141019239284214144L) != 0)) {
				{
				setState(156);
				inicializador();
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMA) {
					{
					{
					setState(157);
					match(COMA);
					setState(158);
					inicializador();
					}
					}
					setState(163);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(166);
			match(LLAVE_DERECHA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TerminalNode NUMERUS() { return getToken(pigLatinParser.NUMERUS, 0); }
		public TerminalNode TEXTUM() { return getToken(pigLatinParser.TEXTUM, 0); }
		public TerminalNode DECIMALIS() { return getToken(pigLatinParser.DECIMALIS, 0); }
		public TerminalNode LITTERA() { return getToken(pigLatinParser.LITTERA, 0); }
		public TerminalNode BOOL() { return getToken(pigLatinParser.BOOL, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(pigLatinParser.IDENTIFICADOR, 0); }
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_tipo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037928568L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BloqueContext extends ParserRuleContext {
		public TerminalNode LLAVE_IZQUIERDA() { return getToken(pigLatinParser.LLAVE_IZQUIERDA, 0); }
		public TerminalNode LLAVE_DERECHA() { return getToken(pigLatinParser.LLAVE_DERECHA, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public BloqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterBloque(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitBloque(this);
		}
	}

	public final BloqueContext bloque() throws RecognitionException {
		BloqueContext _localctx = new BloqueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_bloque);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(LLAVE_IZQUIERDA);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057619808244740L) != 0)) {
				{
				{
				setState(171);
				instruccion();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177);
			match(LLAVE_DERECHA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionContext extends ParserRuleContext {
		public DeclaracionContext declaracion() {
			return getRuleContext(DeclaracionContext.class,0);
		}
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public IncrementoContext incremento() {
			return getRuleContext(IncrementoContext.class,0);
		}
		public LlamadaContext llamada() {
			return getRuleContext(LlamadaContext.class,0);
		}
		public CondicionalContext condicional() {
			return getRuleContext(CondicionalContext.class,0);
		}
		public CicloDumContext cicloDum() {
			return getRuleContext(CicloDumContext.class,0);
		}
		public CicloFacereContext cicloFacere() {
			return getRuleContext(CicloFacereContext.class,0);
		}
		public CicloPerContext cicloPer() {
			return getRuleContext(CicloPerContext.class,0);
		}
		public ContinuarContext continuar() {
			return getRuleContext(ContinuarContext.class,0);
		}
		public InterrumpirContext interrumpir() {
			return getRuleContext(InterrumpirContext.class,0);
		}
		public LecturaContext lectura() {
			return getRuleContext(LecturaContext.class,0);
		}
		public EscrituraContext escritura() {
			return getRuleContext(EscrituraContext.class,0);
		}
		public InstruccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccion(this);
		}
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_instruccion);
		try {
			setState(191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				declaracion();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				asignacion();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(181);
				incremento();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(182);
				llamada();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(183);
				condicional();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(184);
				cicloDum();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(185);
				cicloFacere();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(186);
				cicloPer();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(187);
				continuar();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(188);
				interrumpir();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(189);
				lectura();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(190);
				escritura();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AsignacionContext extends ParserRuleContext {
		public AccesoContext acceso() {
			return getRuleContext(AccesoContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(pigLatinParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitAsignacion(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_asignacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			acceso();
			setState(194);
			match(ASIGNACION);
			setState(195);
			expresion(0);
			setState(196);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IncrementoContext extends ParserRuleContext {
		public AccesoContext acceso() {
			return getRuleContext(AccesoContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public TerminalNode INCREMENTO() { return getToken(pigLatinParser.INCREMENTO, 0); }
		public TerminalNode DECREMENTO() { return getToken(pigLatinParser.DECREMENTO, 0); }
		public IncrementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incremento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterIncremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitIncremento(this);
		}
	}

	public final IncrementoContext incremento() throws RecognitionException {
		IncrementoContext _localctx = new IncrementoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_incremento);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			acceso();
			setState(199);
			_la = _input.LA(1);
			if ( !(_la==INCREMENTO || _la==DECREMENTO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(200);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LlamadaContext extends ParserRuleContext {
		public AccesoContext acceso() {
			return getRuleContext(AccesoContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public LlamadaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llamada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterLlamada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitLlamada(this);
		}
	}

	public final LlamadaContext llamada() throws RecognitionException {
		LlamadaContext _localctx = new LlamadaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_llamada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			acceso();
			setState(203);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinuarContext extends ParserRuleContext {
		public TerminalNode PERGE() { return getToken(pigLatinParser.PERGE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public ContinuarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continuar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterContinuar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitContinuar(this);
		}
	}

	public final ContinuarContext continuar() throws RecognitionException {
		ContinuarContext _localctx = new ContinuarContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_continuar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(PERGE);
			setState(206);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterrumpirContext extends ParserRuleContext {
		public TerminalNode INTERRUMPE() { return getToken(pigLatinParser.INTERRUMPE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public InterrumpirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interrumpir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInterrumpir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInterrumpir(this);
		}
	}

	public final InterrumpirContext interrumpir() throws RecognitionException {
		InterrumpirContext _localctx = new InterrumpirContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_interrumpir);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(INTERRUMPE);
			setState(209);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondicionalContext extends ParserRuleContext {
		public TerminalNode SI() { return getToken(pigLatinParser.SI, 0); }
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINIS() { return getToken(pigLatinParser.FINIS, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public List<SinoSiContext> sinoSi() {
			return getRuleContexts(SinoSiContext.class);
		}
		public SinoSiContext sinoSi(int i) {
			return getRuleContext(SinoSiContext.class,i);
		}
		public SinoContext sino() {
			return getRuleContext(SinoContext.class,0);
		}
		public CondicionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterCondicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitCondicional(this);
		}
	}

	public final CondicionalContext condicional() throws RecognitionException {
		CondicionalContext _localctx = new CondicionalContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_condicional);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(SI);
			setState(212);
			match(PARENTESIS_IZQUIERDO);
			setState(213);
			expresion(0);
			setState(214);
			match(PARENTESIS_DERECHO);
			setState(215);
			bloque();
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(216);
					sinoSi();
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ALITER) {
				{
				setState(222);
				sino();
				}
			}

			setState(225);
			match(FINIS);
			setState(226);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SinoSiContext extends ParserRuleContext {
		public TerminalNode ALITER() { return getToken(pigLatinParser.ALITER, 0); }
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public SinoSiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sinoSi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSinoSi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSinoSi(this);
		}
	}

	public final SinoSiContext sinoSi() throws RecognitionException {
		SinoSiContext _localctx = new SinoSiContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_sinoSi);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(ALITER);
			setState(229);
			match(PARENTESIS_IZQUIERDO);
			setState(230);
			expresion(0);
			setState(231);
			match(PARENTESIS_DERECHO);
			setState(232);
			bloque();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SinoContext extends ParserRuleContext {
		public TerminalNode ALITER() { return getToken(pigLatinParser.ALITER, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public SinoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSino(this);
		}
	}

	public final SinoContext sino() throws RecognitionException {
		SinoContext _localctx = new SinoContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_sino);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(ALITER);
			setState(235);
			bloque();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CicloDumContext extends ParserRuleContext {
		public TerminalNode DUM() { return getToken(pigLatinParser.DUM, 0); }
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINIS() { return getToken(pigLatinParser.FINIS, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public CicloDumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cicloDum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterCicloDum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitCicloDum(this);
		}
	}

	public final CicloDumContext cicloDum() throws RecognitionException {
		CicloDumContext _localctx = new CicloDumContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_cicloDum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(DUM);
			setState(238);
			match(PARENTESIS_IZQUIERDO);
			setState(239);
			expresion(0);
			setState(240);
			match(PARENTESIS_DERECHO);
			setState(241);
			bloque();
			setState(242);
			match(FINIS);
			setState(243);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CicloFacereContext extends ParserRuleContext {
		public TerminalNode FACERE() { return getToken(pigLatinParser.FACERE, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode DUM() { return getToken(pigLatinParser.DUM, 0); }
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public CicloFacereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cicloFacere; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterCicloFacere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitCicloFacere(this);
		}
	}

	public final CicloFacereContext cicloFacere() throws RecognitionException {
		CicloFacereContext _localctx = new CicloFacereContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_cicloFacere);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(FACERE);
			setState(246);
			bloque();
			setState(247);
			match(DUM);
			setState(248);
			match(PARENTESIS_IZQUIERDO);
			setState(249);
			expresion(0);
			setState(250);
			match(PARENTESIS_DERECHO);
			setState(251);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CicloPerContext extends ParserRuleContext {
		public TerminalNode PER() { return getToken(pigLatinParser.PER, 0); }
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public InicioPerContext inicioPer() {
			return getRuleContext(InicioPerContext.class,0);
		}
		public List<TerminalNode> PUNTO_Y_COMA() { return getTokens(pigLatinParser.PUNTO_Y_COMA); }
		public TerminalNode PUNTO_Y_COMA(int i) {
			return getToken(pigLatinParser.PUNTO_Y_COMA, i);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ActualizacionPerContext actualizacionPer() {
			return getRuleContext(ActualizacionPerContext.class,0);
		}
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINIS() { return getToken(pigLatinParser.FINIS, 0); }
		public CicloPerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cicloPer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterCicloPer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitCicloPer(this);
		}
	}

	public final CicloPerContext cicloPer() throws RecognitionException {
		CicloPerContext _localctx = new CicloPerContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_cicloPer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(PER);
			setState(254);
			match(PARENTESIS_IZQUIERDO);
			setState(255);
			inicioPer();
			setState(256);
			match(PUNTO_Y_COMA);
			setState(257);
			expresion(0);
			setState(258);
			match(PUNTO_Y_COMA);
			setState(259);
			actualizacionPer();
			setState(260);
			match(PARENTESIS_DERECHO);
			setState(261);
			bloque();
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINIS) {
				{
				setState(262);
				match(FINIS);
				setState(263);
				match(PUNTO_Y_COMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InicioPerContext extends ParserRuleContext {
		public InicioPerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inicioPer; }
	 
		public InicioPerContext() { }
		public void copyFrom(InicioPerContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InicioPerDeclaracionContext extends InicioPerContext {
		public TerminalNode ESTO() { return getToken(pigLatinParser.ESTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(pigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(pigLatinParser.DOS_PUNTOS, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public InicioPerDeclaracionContext(InicioPerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInicioPerDeclaracion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInicioPerDeclaracion(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InicioPerAsignacionContext extends InicioPerContext {
		public AccesoContext acceso() {
			return getRuleContext(AccesoContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(pigLatinParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public InicioPerAsignacionContext(InicioPerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInicioPerAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInicioPerAsignacion(this);
		}
	}

	public final InicioPerContext inicioPer() throws RecognitionException {
		InicioPerContext _localctx = new InicioPerContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_inicioPer);
		try {
			setState(276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESTO:
				_localctx = new InicioPerDeclaracionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(ESTO);
				setState(267);
				match(IDENTIFICADOR);
				setState(268);
				match(DOS_PUNTOS);
				setState(269);
				tipo();
				setState(270);
				expresion(0);
				}
				break;
			case IDENTIFICADOR:
				_localctx = new InicioPerAsignacionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				acceso();
				setState(273);
				match(ASIGNACION);
				setState(274);
				expresion(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActualizacionPerContext extends ParserRuleContext {
		public AccesoContext acceso() {
			return getRuleContext(AccesoContext.class,0);
		}
		public TerminalNode INCREMENTO() { return getToken(pigLatinParser.INCREMENTO, 0); }
		public TerminalNode DECREMENTO() { return getToken(pigLatinParser.DECREMENTO, 0); }
		public TerminalNode ASIGNACION() { return getToken(pigLatinParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ActualizacionPerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualizacionPer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterActualizacionPer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitActualizacionPer(this);
		}
	}

	public final ActualizacionPerContext actualizacionPer() throws RecognitionException {
		ActualizacionPerContext _localctx = new ActualizacionPerContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_actualizacionPer);
		int _la;
		try {
			setState(285);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				acceso();
				setState(279);
				_la = _input.LA(1);
				if ( !(_la==INCREMENTO || _la==DECREMENTO) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				acceso();
				setState(282);
				match(ASIGNACION);
				setState(283);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LecturaContext extends ParserRuleContext {
		public TerminalNode LEER() { return getToken(pigLatinParser.LEER, 0); }
		public AccesoContext acceso() {
			return getRuleContext(AccesoContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public LecturaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lectura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterLectura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitLectura(this);
		}
	}

	public final LecturaContext lectura() throws RecognitionException {
		LecturaContext _localctx = new LecturaContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_lectura);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFICADOR) {
				{
				setState(287);
				acceso();
				}
			}

			setState(290);
			match(LEER);
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNTO_Y_COMA) {
				{
				setState(291);
				match(PUNTO_Y_COMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EscrituraContext extends ParserRuleContext {
		public List<TerminalNode> IMPRIMIR() { return getTokens(pigLatinParser.IMPRIMIR); }
		public TerminalNode IMPRIMIR(int i) {
			return getToken(pigLatinParser.IMPRIMIR, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(pigLatinParser.PUNTO_Y_COMA, 0); }
		public EscrituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escritura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterEscritura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitEscritura(this);
		}
	}

	public final EscrituraContext escritura() throws RecognitionException {
		EscrituraContext _localctx = new EscrituraContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_escritura);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(IMPRIMIR);
			setState(295);
			expresion(0);
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPRIMIR) {
				{
				{
				setState(296);
				match(IMPRIMIR);
				setState(297);
				expresion(0);
				}
				}
				setState(302);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(303);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccesoContext extends ParserRuleContext {
		public TerminalNode IDENTIFICADOR() { return getToken(pigLatinParser.IDENTIFICADOR, 0); }
		public List<SufijoContext> sufijo() {
			return getRuleContexts(SufijoContext.class);
		}
		public SufijoContext sufijo(int i) {
			return getRuleContext(SufijoContext.class,i);
		}
		public AccesoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acceso; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterAcceso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitAcceso(this);
		}
	}

	public final AccesoContext acceso() throws RecognitionException {
		AccesoContext _localctx = new AccesoContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_acceso);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(IDENTIFICADOR);
			setState(309);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(306);
					sufijo();
					}
					} 
				}
				setState(311);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SufijoContext extends ParserRuleContext {
		public SufijoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sufijo; }
	 
		public SufijoContext() { }
		public void copyFrom(SufijoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SufijoIndiceContext extends SufijoContext {
		public TerminalNode CORCHETE_IZQUIERDO() { return getToken(pigLatinParser.CORCHETE_IZQUIERDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DERECHO() { return getToken(pigLatinParser.CORCHETE_DERECHO, 0); }
		public SufijoIndiceContext(SufijoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSufijoIndice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSufijoIndice(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SufijoAtributoContext extends SufijoContext {
		public TerminalNode PUNTO() { return getToken(pigLatinParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(pigLatinParser.IDENTIFICADOR, 0); }
		public SufijoAtributoContext(SufijoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSufijoAtributo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSufijoAtributo(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SufijoLlamadaContext extends SufijoContext {
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public SufijoLlamadaContext(SufijoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSufijoLlamada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSufijoLlamada(this);
		}
	}

	public final SufijoContext sufijo() throws RecognitionException {
		SufijoContext _localctx = new SufijoContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_sufijo);
		int _la;
		try {
			setState(323);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CORCHETE_IZQUIERDO:
				_localctx = new SufijoIndiceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(312);
				match(CORCHETE_IZQUIERDO);
				setState(313);
				expresion(0);
				setState(314);
				match(CORCHETE_DERECHO);
				}
				break;
			case PUNTO:
				_localctx = new SufijoAtributoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				match(PUNTO);
				setState(317);
				match(IDENTIFICADOR);
				}
				break;
			case PARENTESIS_IZQUIERDO:
				_localctx = new SufijoLlamadaContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(318);
				match(PARENTESIS_IZQUIERDO);
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 140737764307503488L) != 0)) {
					{
					setState(319);
					argumentos();
					}
				}

				setState(322);
				match(PARENTESIS_DERECHO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentosContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(pigLatinParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(pigLatinParser.COMA, i);
		}
		public ArgumentosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterArgumentos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitArgumentos(this);
		}
	}

	public final ArgumentosContext argumentos() throws RecognitionException {
		ArgumentosContext _localctx = new ArgumentosContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_argumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			expresion(0);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(326);
				match(COMA);
				setState(327);
				expresion(0);
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreacionObjetoContext extends ParserRuleContext {
		public TerminalNode NOVUS() { return getToken(pigLatinParser.NOVUS, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(pigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public CreacionObjetoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creacionObjeto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterCreacionObjeto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitCreacionObjeto(this);
		}
	}

	public final CreacionObjetoContext creacionObjeto() throws RecognitionException {
		CreacionObjetoContext _localctx = new CreacionObjetoContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_creacionObjeto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(NOVUS);
			setState(334);
			match(IDENTIFICADOR);
			setState(335);
			match(PARENTESIS_IZQUIERDO);
			setState(337);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 140737764307503488L) != 0)) {
				{
				setState(336);
				argumentos();
				}
			}

			setState(339);
			match(PARENTESIS_DERECHO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionContext extends ParserRuleContext {
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
	 
		public ExpresionContext() { }
		public void copyFrom(ExpresionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionNuevoObjetoContext extends ExpresionContext {
		public CreacionObjetoContext creacionObjeto() {
			return getRuleContext(CreacionObjetoContext.class,0);
		}
		public List<SufijoContext> sufijo() {
			return getRuleContexts(SufijoContext.class);
		}
		public SufijoContext sufijo(int i) {
			return getRuleContext(SufijoContext.class,i);
		}
		public ExpresionNuevoObjetoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionNuevoObjeto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionNuevoObjeto(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionUnariaContext extends ExpresionContext {
		public Token operador;
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode NEGACION() { return getToken(pigLatinParser.NEGACION, 0); }
		public TerminalNode NO_LOGICO() { return getToken(pigLatinParser.NO_LOGICO, 0); }
		public TerminalNode RESTA() { return getToken(pigLatinParser.RESTA, 0); }
		public ExpresionUnariaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionUnaria(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionUnaria(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionIgualdadContext extends ExpresionContext {
		public Token operador;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode IGUAL_QUE() { return getToken(pigLatinParser.IGUAL_QUE, 0); }
		public TerminalNode DIFERENTE_DE() { return getToken(pigLatinParser.DIFERENTE_DE, 0); }
		public ExpresionIgualdadContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionIgualdad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionIgualdad(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionMultiplicativaContext extends ExpresionContext {
		public Token operador;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MULTIPLICACION() { return getToken(pigLatinParser.MULTIPLICACION, 0); }
		public TerminalNode DIVISION() { return getToken(pigLatinParser.DIVISION, 0); }
		public ExpresionMultiplicativaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionMultiplicativa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionMultiplicativa(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionYContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode Y_LOGICO() { return getToken(pigLatinParser.Y_LOGICO, 0); }
		public ExpresionYContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionY(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionRelacionalContext extends ExpresionContext {
		public Token operador;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MENOR_QUE() { return getToken(pigLatinParser.MENOR_QUE, 0); }
		public TerminalNode MENOR_O_IGUAL_QUE() { return getToken(pigLatinParser.MENOR_O_IGUAL_QUE, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(pigLatinParser.MAYOR_QUE, 0); }
		public TerminalNode MAYOR_O_IGUAL_QUE() { return getToken(pigLatinParser.MAYOR_O_IGUAL_QUE, 0); }
		public ExpresionRelacionalContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionRelacional(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionLiteralContext extends ExpresionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExpresionLiteralContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionLiteral(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionParentesisContext extends ExpresionContext {
		public TerminalNode PARENTESIS_IZQUIERDO() { return getToken(pigLatinParser.PARENTESIS_IZQUIERDO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PARENTESIS_DERECHO() { return getToken(pigLatinParser.PARENTESIS_DERECHO, 0); }
		public ExpresionParentesisContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionParentesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionParentesis(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionAccesoContext extends ExpresionContext {
		public AccesoContext acceso() {
			return getRuleContext(AccesoContext.class,0);
		}
		public ExpresionAccesoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionAcceso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionAcceso(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionOContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode O_LOGICO() { return getToken(pigLatinParser.O_LOGICO, 0); }
		public ExpresionOContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionO(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionO(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionAditivaContext extends ExpresionContext {
		public Token operador;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode SUMA() { return getToken(pigLatinParser.SUMA, 0); }
		public TerminalNode RESTA() { return getToken(pigLatinParser.RESTA, 0); }
		public ExpresionAditivaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExpresionAditiva(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExpresionAditiva(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		return expresion(0);
	}

	private ExpresionContext expresion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpresionContext _localctx = new ExpresionContext(_ctx, _parentState);
		ExpresionContext _prevctx = _localctx;
		int _startState = 68;
		enterRecursionRule(_localctx, 68, RULE_expresion, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARENTESIS_IZQUIERDO:
				{
				_localctx = new ExpresionParentesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(342);
				match(PARENTESIS_IZQUIERDO);
				setState(343);
				expresion(0);
				setState(344);
				match(PARENTESIS_DERECHO);
				}
				break;
			case VERUM:
			case FALSUS:
			case DECIMAL:
			case ENTERO:
			case TEXTO:
			case CARACTER:
				{
				_localctx = new ExpresionLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(346);
				literal();
				}
				break;
			case NOVUS:
				{
				_localctx = new ExpresionNuevoObjetoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(347);
				creacionObjeto();
				setState(351);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(348);
						sufijo();
						}
						} 
					}
					setState(353);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				}
				break;
			case IDENTIFICADOR:
				{
				_localctx = new ExpresionAccesoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(354);
				acceso();
				}
				break;
			case NEGACION:
			case NO_LOGICO:
			case RESTA:
				{
				_localctx = new ExpresionUnariaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(355);
				((ExpresionUnariaContext)_localctx).operador = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 275952173056L) != 0)) ) {
					((ExpresionUnariaContext)_localctx).operador = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(356);
				expresion(7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(379);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(377);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
					case 1:
						{
						_localctx = new ExpresionMultiplicativaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(359);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(360);
						((ExpresionMultiplicativaContext)_localctx).operador = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULTIPLICACION || _la==DIVISION) ) {
							((ExpresionMultiplicativaContext)_localctx).operador = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(361);
						expresion(7);
						}
						break;
					case 2:
						{
						_localctx = new ExpresionAditivaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(362);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(363);
						((ExpresionAditivaContext)_localctx).operador = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SUMA || _la==RESTA) ) {
							((ExpresionAditivaContext)_localctx).operador = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(364);
						expresion(6);
						}
						break;
					case 3:
						{
						_localctx = new ExpresionRelacionalContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(365);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(366);
						((ExpresionRelacionalContext)_localctx).operador = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 103280541696L) != 0)) ) {
							((ExpresionRelacionalContext)_localctx).operador = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(367);
						expresion(5);
						}
						break;
					case 4:
						{
						_localctx = new ExpresionIgualdadContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(368);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(369);
						((ExpresionIgualdadContext)_localctx).operador = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==IGUAL_QUE || _la==DIFERENTE_DE) ) {
							((ExpresionIgualdadContext)_localctx).operador = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(370);
						expresion(4);
						}
						break;
					case 5:
						{
						_localctx = new ExpresionYContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(371);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(372);
						match(Y_LOGICO);
						setState(373);
						expresion(3);
						}
						break;
					case 6:
						{
						_localctx = new ExpresionOContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(374);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(375);
						match(O_LOGICO);
						setState(376);
						expresion(2);
						}
						break;
					}
					} 
				}
				setState(381);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode ENTERO() { return getToken(pigLatinParser.ENTERO, 0); }
		public TerminalNode DECIMAL() { return getToken(pigLatinParser.DECIMAL, 0); }
		public TerminalNode TEXTO() { return getToken(pigLatinParser.TEXTO, 0); }
		public TerminalNode CARACTER() { return getToken(pigLatinParser.CARACTER, 0); }
		public TerminalNode VERUM() { return getToken(pigLatinParser.VERUM, 0); }
		public TerminalNode FALSUS() { return getToken(pigLatinParser.FALSUS, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 67553994410557824L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 34:
			return expresion_sempred((ExpresionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expresion_sempred(ExpresionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001?\u0181\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0001\u0000\u0005\u0000J\b\u0000\n\u0000\f\u0000M\t\u0000\u0001"+
		"\u0000\u0003\u0000P\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001X\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0004\u0002]\b\u0002\u000b\u0002\f\u0002^\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003d\b\u0003\n\u0003\f\u0003g\t\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004l\b\u0004\n\u0004\f\u0004"+
		"o\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0003\u0005v\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u0080\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u0084\b\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0004\b\u0089\b\b\u000b\b\f\b\u008a\u0001\b\u0001\b\u0001\b\u0003\b"+
		"\u0090\b\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0003\n\u009a\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000b\u00a0\b\u000b\n\u000b\f\u000b\u00a3\t\u000b\u0003\u000b\u00a5\b"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0005\r"+
		"\u00ad\b\r\n\r\f\r\u00b0\t\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00c0\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u00da\b\u0014\n\u0014\f\u0014\u00dd\t\u0014\u0001\u0014\u0003\u0014"+
		"\u00e0\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0109\b\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0115\b\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u011e\b\u001b\u0001\u001c\u0003\u001c\u0121\b"+
		"\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0125\b\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u012b\b\u001d\n\u001d\f\u001d"+
		"\u012e\t\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0005\u001e"+
		"\u0134\b\u001e\n\u001e\f\u001e\u0137\t\u001e\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u0141\b\u001f\u0001\u001f\u0003\u001f\u0144\b\u001f\u0001 \u0001"+
		" \u0001 \u0005 \u0149\b \n \f \u014c\t \u0001!\u0001!\u0001!\u0001!\u0003"+
		"!\u0152\b!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0005\"\u015e\b\"\n\"\f\"\u0161\t\"\u0001\"\u0001\""+
		"\u0001\"\u0003\"\u0166\b\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0005\"\u017a\b\"\n\"\f\"\u017d\t\"\u0001#"+
		"\u0001#\u0001#\u0000\u0001D$\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF\u0000"+
		"\t\u0001\u0000\u0007\b\u0003\u0000\u0003\u0006\t\t88\u0001\u0000\u001f"+
		" \u0003\u0000\u0013\u0013\u001e\u001e&&\u0001\u0000\'(\u0001\u0000%&\u0002"+
		"\u0000\u001a\u001b#$\u0001\u0000\u0018\u0019\u0002\u0000\u0007\b47\u0190"+
		"\u0000K\u0001\u0000\u0000\u0000\u0002T\u0001\u0000\u0000\u0000\u0004Y"+
		"\u0001\u0000\u0000\u0000\u0006`\u0001\u0000\u0000\u0000\bh\u0001\u0000"+
		"\u0000\u0000\nu\u0001\u0000\u0000\u0000\fw\u0001\u0000\u0000\u0000\u000e"+
		"\u0083\u0001\u0000\u0000\u0000\u0010\u0085\u0001\u0000\u0000\u0000\u0012"+
		"\u0093\u0001\u0000\u0000\u0000\u0014\u0099\u0001\u0000\u0000\u0000\u0016"+
		"\u009b\u0001\u0000\u0000\u0000\u0018\u00a8\u0001\u0000\u0000\u0000\u001a"+
		"\u00aa\u0001\u0000\u0000\u0000\u001c\u00bf\u0001\u0000\u0000\u0000\u001e"+
		"\u00c1\u0001\u0000\u0000\u0000 \u00c6\u0001\u0000\u0000\u0000\"\u00ca"+
		"\u0001\u0000\u0000\u0000$\u00cd\u0001\u0000\u0000\u0000&\u00d0\u0001\u0000"+
		"\u0000\u0000(\u00d3\u0001\u0000\u0000\u0000*\u00e4\u0001\u0000\u0000\u0000"+
		",\u00ea\u0001\u0000\u0000\u0000.\u00ed\u0001\u0000\u0000\u00000\u00f5"+
		"\u0001\u0000\u0000\u00002\u00fd\u0001\u0000\u0000\u00004\u0114\u0001\u0000"+
		"\u0000\u00006\u011d\u0001\u0000\u0000\u00008\u0120\u0001\u0000\u0000\u0000"+
		":\u0126\u0001\u0000\u0000\u0000<\u0131\u0001\u0000\u0000\u0000>\u0143"+
		"\u0001\u0000\u0000\u0000@\u0145\u0001\u0000\u0000\u0000B\u014d\u0001\u0000"+
		"\u0000\u0000D\u0165\u0001\u0000\u0000\u0000F\u017e\u0001\u0000\u0000\u0000"+
		"HJ\u0003\u0002\u0001\u0000IH\u0001\u0000\u0000\u0000JM\u0001\u0000\u0000"+
		"\u0000KI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LO\u0001\u0000"+
		"\u0000\u0000MK\u0001\u0000\u0000\u0000NP\u0003\u0006\u0003\u0000ON\u0001"+
		"\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000"+
		"QR\u0003\b\u0004\u0000RS\u0005\u0000\u0000\u0001S\u0001\u0001\u0000\u0000"+
		"\u0000TU\u0005\u0001\u0000\u0000UW\u0003\u0004\u0002\u0000VX\u0005+\u0000"+
		"\u0000WV\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000X\u0003\u0001"+
		"\u0000\u0000\u0000Y\\\u00058\u0000\u0000Z[\u0005-\u0000\u0000[]\u0005"+
		"8\u0000\u0000\\Z\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\\"+
		"\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_\u0005\u0001\u0000"+
		"\u0000\u0000`a\u0005\u0015\u0000\u0000ae\u0005$\u0000\u0000bd\u0003\n"+
		"\u0005\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000f\u0007\u0001\u0000\u0000"+
		"\u0000ge\u0001\u0000\u0000\u0000hi\u0005\u0016\u0000\u0000im\u0005$\u0000"+
		"\u0000jl\u0003\u001c\u000e\u0000kj\u0001\u0000\u0000\u0000lo\u0001\u0000"+
		"\u0000\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000np\u0001"+
		"\u0000\u0000\u0000om\u0001\u0000\u0000\u0000pq\u0005\u0017\u0000\u0000"+
		"qr\u0005+\u0000\u0000r\t\u0001\u0000\u0000\u0000sv\u0003\f\u0006\u0000"+
		"tv\u0003\u0010\b\u0000us\u0001\u0000\u0000\u0000ut\u0001\u0000\u0000\u0000"+
		"v\u000b\u0001\u0000\u0000\u0000wx\u0005\u0002\u0000\u0000xy\u00058\u0000"+
		"\u0000yz\u0005*\u0000\u0000z{\u0003\u000e\u0007\u0000{|\u0005+\u0000\u0000"+
		"|\r\u0001\u0000\u0000\u0000}\u007f\u0003\u0018\f\u0000~\u0080\u0003\u0014"+
		"\n\u0000\u007f~\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000"+
		"\u0000\u0080\u0084\u0001\u0000\u0000\u0000\u0081\u0084\u0007\u0000\u0000"+
		"\u0000\u0082\u0084\u0003B!\u0000\u0083}\u0001\u0000\u0000\u0000\u0083"+
		"\u0081\u0001\u0000\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084"+
		"\u000f\u0001\u0000\u0000\u0000\u0085\u0086\u0005\n\u0000\u0000\u0086\u0088"+
		"\u00058\u0000\u0000\u0087\u0089\u0003\u0012\t\u0000\u0088\u0087\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008c\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0005*\u0000\u0000\u008d\u008f\u0003\u0018"+
		"\f\u0000\u008e\u0090\u0003\u0014\n\u0000\u008f\u008e\u0001\u0000\u0000"+
		"\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0005+\u0000\u0000\u0092\u0011\u0001\u0000\u0000\u0000"+
		"\u0093\u0094\u0005.\u0000\u0000\u0094\u0095\u0003D\"\u0000\u0095\u0096"+
		"\u0005/\u0000\u0000\u0096\u0013\u0001\u0000\u0000\u0000\u0097\u009a\u0003"+
		"D\"\u0000\u0098\u009a\u0003\u0016\u000b\u0000\u0099\u0097\u0001\u0000"+
		"\u0000\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u009a\u0015\u0001\u0000"+
		"\u0000\u0000\u009b\u00a4\u00050\u0000\u0000\u009c\u00a1\u0003\u0014\n"+
		"\u0000\u009d\u009e\u0005,\u0000\u0000\u009e\u00a0\u0003\u0014\n\u0000"+
		"\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a4\u009c\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u00051\u0000\u0000\u00a7"+
		"\u0017\u0001\u0000\u0000\u0000\u00a8\u00a9\u0007\u0001\u0000\u0000\u00a9"+
		"\u0019\u0001\u0000\u0000\u0000\u00aa\u00ae\u00050\u0000\u0000\u00ab\u00ad"+
		"\u0003\u001c\u000e\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad\u00b0"+
		"\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0001\u0000\u0000\u0000\u00af\u00b1\u0001\u0000\u0000\u0000\u00b0\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u00051\u0000\u0000\u00b2\u001b\u0001"+
		"\u0000\u0000\u0000\u00b3\u00c0\u0003\n\u0005\u0000\u00b4\u00c0\u0003\u001e"+
		"\u000f\u0000\u00b5\u00c0\u0003 \u0010\u0000\u00b6\u00c0\u0003\"\u0011"+
		"\u0000\u00b7\u00c0\u0003(\u0014\u0000\u00b8\u00c0\u0003.\u0017\u0000\u00b9"+
		"\u00c0\u00030\u0018\u0000\u00ba\u00c0\u00032\u0019\u0000\u00bb\u00c0\u0003"+
		"$\u0012\u0000\u00bc\u00c0\u0003&\u0013\u0000\u00bd\u00c0\u00038\u001c"+
		"\u0000\u00be\u00c0\u0003:\u001d\u0000\u00bf\u00b3\u0001\u0000\u0000\u0000"+
		"\u00bf\u00b4\u0001\u0000\u0000\u0000\u00bf\u00b5\u0001\u0000\u0000\u0000"+
		"\u00bf\u00b6\u0001\u0000\u0000\u0000\u00bf\u00b7\u0001\u0000\u0000\u0000"+
		"\u00bf\u00b8\u0001\u0000\u0000\u0000\u00bf\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bf\u00ba\u0001\u0000\u0000\u0000\u00bf\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bf\u00bc\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000"+
		"\u00bf\u00be\u0001\u0000\u0000\u0000\u00c0\u001d\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0003<\u001e\u0000\u00c2\u00c3\u0005)\u0000\u0000\u00c3\u00c4"+
		"\u0003D\"\u0000\u00c4\u00c5\u0005+\u0000\u0000\u00c5\u001f\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0003<\u001e\u0000\u00c7\u00c8\u0007\u0002\u0000"+
		"\u0000\u00c8\u00c9\u0005+\u0000\u0000\u00c9!\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0003<\u001e\u0000\u00cb\u00cc\u0005+\u0000\u0000\u00cc#\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0005\u0011\u0000\u0000\u00ce\u00cf\u0005"+
		"+\u0000\u0000\u00cf%\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\u0012\u0000"+
		"\u0000\u00d1\u00d2\u0005+\u0000\u0000\u00d2\'\u0001\u0000\u0000\u0000"+
		"\u00d3\u00d4\u0005\f\u0000\u0000\u00d4\u00d5\u00052\u0000\u0000\u00d5"+
		"\u00d6\u0003D\"\u0000\u00d6\u00d7\u00053\u0000\u0000\u00d7\u00db\u0003"+
		"\u001a\r\u0000\u00d8\u00da\u0003*\u0015\u0000\u00d9\u00d8\u0001\u0000"+
		"\u0000\u0000\u00da\u00dd\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000"+
		"\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00df\u0001\u0000"+
		"\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00e0\u0003,\u0016"+
		"\u0000\u00df\u00de\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u00e2\u0005\u0014\u0000"+
		"\u0000\u00e2\u00e3\u0005+\u0000\u0000\u00e3)\u0001\u0000\u0000\u0000\u00e4"+
		"\u00e5\u0005\r\u0000\u0000\u00e5\u00e6\u00052\u0000\u0000\u00e6\u00e7"+
		"\u0003D\"\u0000\u00e7\u00e8\u00053\u0000\u0000\u00e8\u00e9\u0003\u001a"+
		"\r\u0000\u00e9+\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005\r\u0000\u0000"+
		"\u00eb\u00ec\u0003\u001a\r\u0000\u00ec-\u0001\u0000\u0000\u0000\u00ed"+
		"\u00ee\u0005\u000e\u0000\u0000\u00ee\u00ef\u00052\u0000\u0000\u00ef\u00f0"+
		"\u0003D\"\u0000\u00f0\u00f1\u00053\u0000\u0000\u00f1\u00f2\u0003\u001a"+
		"\r\u0000\u00f2\u00f3\u0005\u0014\u0000\u0000\u00f3\u00f4\u0005+\u0000"+
		"\u0000\u00f4/\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005\u000f\u0000\u0000"+
		"\u00f6\u00f7\u0003\u001a\r\u0000\u00f7\u00f8\u0005\u000e\u0000\u0000\u00f8"+
		"\u00f9\u00052\u0000\u0000\u00f9\u00fa\u0003D\"\u0000\u00fa\u00fb\u0005"+
		"3\u0000\u0000\u00fb\u00fc\u0005+\u0000\u0000\u00fc1\u0001\u0000\u0000"+
		"\u0000\u00fd\u00fe\u0005\u0010\u0000\u0000\u00fe\u00ff\u00052\u0000\u0000"+
		"\u00ff\u0100\u00034\u001a\u0000\u0100\u0101\u0005+\u0000\u0000\u0101\u0102"+
		"\u0003D\"\u0000\u0102\u0103\u0005+\u0000\u0000\u0103\u0104\u00036\u001b"+
		"\u0000\u0104\u0105\u00053\u0000\u0000\u0105\u0108\u0003\u001a\r\u0000"+
		"\u0106\u0107\u0005\u0014\u0000\u0000\u0107\u0109\u0005+\u0000\u0000\u0108"+
		"\u0106\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000\u0109"+
		"3\u0001\u0000\u0000\u0000\u010a\u010b\u0005\u0002\u0000\u0000\u010b\u010c"+
		"\u00058\u0000\u0000\u010c\u010d\u0005*\u0000\u0000\u010d\u010e\u0003\u0018"+
		"\f\u0000\u010e\u010f\u0003D\"\u0000\u010f\u0115\u0001\u0000\u0000\u0000"+
		"\u0110\u0111\u0003<\u001e\u0000\u0111\u0112\u0005)\u0000\u0000\u0112\u0113"+
		"\u0003D\"\u0000\u0113\u0115\u0001\u0000\u0000\u0000\u0114\u010a\u0001"+
		"\u0000\u0000\u0000\u0114\u0110\u0001\u0000\u0000\u0000\u01155\u0001\u0000"+
		"\u0000\u0000\u0116\u0117\u0003<\u001e\u0000\u0117\u0118\u0007\u0002\u0000"+
		"\u0000\u0118\u011e\u0001\u0000\u0000\u0000\u0119\u011a\u0003<\u001e\u0000"+
		"\u011a\u011b\u0005)\u0000\u0000\u011b\u011c\u0003D\"\u0000\u011c\u011e"+
		"\u0001\u0000\u0000\u0000\u011d\u0116\u0001\u0000\u0000\u0000\u011d\u0119"+
		"\u0001\u0000\u0000\u0000\u011e7\u0001\u0000\u0000\u0000\u011f\u0121\u0003"+
		"<\u001e\u0000\u0120\u011f\u0001\u0000\u0000\u0000\u0120\u0121\u0001\u0000"+
		"\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0124\u0005!\u0000"+
		"\u0000\u0123\u0125\u0005+\u0000\u0000\u0124\u0123\u0001\u0000\u0000\u0000"+
		"\u0124\u0125\u0001\u0000\u0000\u0000\u01259\u0001\u0000\u0000\u0000\u0126"+
		"\u0127\u0005\"\u0000\u0000\u0127\u012c\u0003D\"\u0000\u0128\u0129\u0005"+
		"\"\u0000\u0000\u0129\u012b\u0003D\"\u0000\u012a\u0128\u0001\u0000\u0000"+
		"\u0000\u012b\u012e\u0001\u0000\u0000\u0000\u012c\u012a\u0001\u0000\u0000"+
		"\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u012f\u0001\u0000\u0000"+
		"\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012f\u0130\u0005+\u0000\u0000"+
		"\u0130;\u0001\u0000\u0000\u0000\u0131\u0135\u00058\u0000\u0000\u0132\u0134"+
		"\u0003>\u001f\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0134\u0137\u0001"+
		"\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001"+
		"\u0000\u0000\u0000\u0136=\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000"+
		"\u0000\u0000\u0138\u0139\u0005.\u0000\u0000\u0139\u013a\u0003D\"\u0000"+
		"\u013a\u013b\u0005/\u0000\u0000\u013b\u0144\u0001\u0000\u0000\u0000\u013c"+
		"\u013d\u0005-\u0000\u0000\u013d\u0144\u00058\u0000\u0000\u013e\u0140\u0005"+
		"2\u0000\u0000\u013f\u0141\u0003@ \u0000\u0140\u013f\u0001\u0000\u0000"+
		"\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000"+
		"\u0000\u0142\u0144\u00053\u0000\u0000\u0143\u0138\u0001\u0000\u0000\u0000"+
		"\u0143\u013c\u0001\u0000\u0000\u0000\u0143\u013e\u0001\u0000\u0000\u0000"+
		"\u0144?\u0001\u0000\u0000\u0000\u0145\u014a\u0003D\"\u0000\u0146\u0147"+
		"\u0005,\u0000\u0000\u0147\u0149\u0003D\"\u0000\u0148\u0146\u0001\u0000"+
		"\u0000\u0000\u0149\u014c\u0001\u0000\u0000\u0000\u014a\u0148\u0001\u0000"+
		"\u0000\u0000\u014a\u014b\u0001\u0000\u0000\u0000\u014bA\u0001\u0000\u0000"+
		"\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014d\u014e\u0005\u000b\u0000"+
		"\u0000\u014e\u014f\u00058\u0000\u0000\u014f\u0151\u00052\u0000\u0000\u0150"+
		"\u0152\u0003@ \u0000\u0151\u0150\u0001\u0000\u0000\u0000\u0151\u0152\u0001"+
		"\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0154\u0005"+
		"3\u0000\u0000\u0154C\u0001\u0000\u0000\u0000\u0155\u0156\u0006\"\uffff"+
		"\uffff\u0000\u0156\u0157\u00052\u0000\u0000\u0157\u0158\u0003D\"\u0000"+
		"\u0158\u0159\u00053\u0000\u0000\u0159\u0166\u0001\u0000\u0000\u0000\u015a"+
		"\u0166\u0003F#\u0000\u015b\u015f\u0003B!\u0000\u015c\u015e\u0003>\u001f"+
		"\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015e\u0161\u0001\u0000\u0000"+
		"\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000"+
		"\u0000\u0160\u0166\u0001\u0000\u0000\u0000\u0161\u015f\u0001\u0000\u0000"+
		"\u0000\u0162\u0166\u0003<\u001e\u0000\u0163\u0164\u0007\u0003\u0000\u0000"+
		"\u0164\u0166\u0003D\"\u0007\u0165\u0155\u0001\u0000\u0000\u0000\u0165"+
		"\u015a\u0001\u0000\u0000\u0000\u0165\u015b\u0001\u0000\u0000\u0000\u0165"+
		"\u0162\u0001\u0000\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0166"+
		"\u017b\u0001\u0000\u0000\u0000\u0167\u0168\n\u0006\u0000\u0000\u0168\u0169"+
		"\u0007\u0004\u0000\u0000\u0169\u017a\u0003D\"\u0007\u016a\u016b\n\u0005"+
		"\u0000\u0000\u016b\u016c\u0007\u0005\u0000\u0000\u016c\u017a\u0003D\""+
		"\u0006\u016d\u016e\n\u0004\u0000\u0000\u016e\u016f\u0007\u0006\u0000\u0000"+
		"\u016f\u017a\u0003D\"\u0005\u0170\u0171\n\u0003\u0000\u0000\u0171\u0172"+
		"\u0007\u0007\u0000\u0000\u0172\u017a\u0003D\"\u0004\u0173\u0174\n\u0002"+
		"\u0000\u0000\u0174\u0175\u0005\u001c\u0000\u0000\u0175\u017a\u0003D\""+
		"\u0003\u0176\u0177\n\u0001\u0000\u0000\u0177\u0178\u0005\u001d\u0000\u0000"+
		"\u0178\u017a\u0003D\"\u0002\u0179\u0167\u0001\u0000\u0000\u0000\u0179"+
		"\u016a\u0001\u0000\u0000\u0000\u0179\u016d\u0001\u0000\u0000\u0000\u0179"+
		"\u0170\u0001\u0000\u0000\u0000\u0179\u0173\u0001\u0000\u0000\u0000\u0179"+
		"\u0176\u0001\u0000\u0000\u0000\u017a\u017d\u0001\u0000\u0000\u0000\u017b"+
		"\u0179\u0001\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c"+
		"E\u0001\u0000\u0000\u0000\u017d\u017b\u0001\u0000\u0000\u0000\u017e\u017f"+
		"\u0007\b\u0000\u0000\u017fG\u0001\u0000\u0000\u0000!KOW^emu\u007f\u0083"+
		"\u008a\u008f\u0099\u00a1\u00a4\u00ae\u00bf\u00db\u00df\u0108\u0114\u011d"+
		"\u0120\u0124\u012c\u0135\u0140\u0143\u014a\u0151\u015f\u0165\u0179\u017b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}