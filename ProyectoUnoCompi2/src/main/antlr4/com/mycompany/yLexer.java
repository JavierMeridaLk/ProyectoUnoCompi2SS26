// Generated from y.g4 by ANTLR 4.13.2
 package com.mycompany; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class yLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SECCION_ESTRUCTURAS=1, SECCION_FUNCIONES=2, ESTRUCTURA=3, DEFINIR=4, RETORNAR=5, 
		ENTERO_T=6, FLOTANTE_T=7, CADENA_T=8, CARACTER_T=9, BOOL_T=10, VERDADERO=11, 
		FALSO=12, SI=13, SINO=14, CONTRARIO=15, ENTONCES=16, ELEGIR=17, CASO=18, 
		SIEMPRE=19, PARA=20, MIENTRAS=21, HACER=22, ROMPER=23, CONTINUAR=24, IMPRIMIR=25, 
		LEER=26, FLECHA=27, IGUAL_QUE=28, DIFERENTE_DE=29, MENOR_O_IGUAL_QUE=30, 
		MAYOR_O_IGUAL_QUE=31, Y_LOGICO=32, O_LOGICO=33, INCREMENTO=34, DECREMENTO=35, 
		NO_LOGICO=36, MENOR_QUE=37, MAYOR_QUE=38, SUMA=39, RESTA=40, MULTIPLICACION=41, 
		DIVISION=42, MODULO=43, ASIGNACION=44, PARENTESIS_IZQUIERDO=45, PARENTESIS_DERECHO=46, 
		CORCHETE_IZQUIERDO=47, CORCHETE_DERECHO=48, LLAVE_IZQUIERDA=49, LLAVE_DERECHA=50, 
		DOS_PUNTOS=51, PUNTO_Y_COMA=52, COMA=53, PUNTO=54, DECIMAL=55, ENTERO=56, 
		TEXTO=57, CARACTER=58, IDENTIFICADOR=59, NEWLINE=60, COMENTARIO_DE_BLOQUE=61, 
		COMENTARIO_DE_LINEA=62, ESPACIOS=63, COMENTARIO_SIN_CERRAR=64, TEXTO_SIN_CERRAR=65, 
		CARACTER_INVALIDO=66, ERROR_LEXICO=67;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SECCION_ESTRUCTURAS", "SECCION_FUNCIONES", "ESTRUCTURA", "DEFINIR", 
			"RETORNAR", "ENTERO_T", "FLOTANTE_T", "CADENA_T", "CARACTER_T", "BOOL_T", 
			"VERDADERO", "FALSO", "SI", "SINO", "CONTRARIO", "ENTONCES", "ELEGIR", 
			"CASO", "SIEMPRE", "PARA", "MIENTRAS", "HACER", "ROMPER", "CONTINUAR", 
			"IMPRIMIR", "LEER", "FLECHA", "IGUAL_QUE", "DIFERENTE_DE", "MENOR_O_IGUAL_QUE", 
			"MAYOR_O_IGUAL_QUE", "Y_LOGICO", "O_LOGICO", "INCREMENTO", "DECREMENTO", 
			"NO_LOGICO", "MENOR_QUE", "MAYOR_QUE", "SUMA", "RESTA", "MULTIPLICACION", 
			"DIVISION", "MODULO", "ASIGNACION", "PARENTESIS_IZQUIERDO", "PARENTESIS_DERECHO", 
			"CORCHETE_IZQUIERDO", "CORCHETE_DERECHO", "LLAVE_IZQUIERDA", "LLAVE_DERECHA", 
			"DOS_PUNTOS", "PUNTO_Y_COMA", "COMA", "PUNTO", "DECIMAL", "ENTERO", "TEXTO", 
			"CARACTER", "IDENTIFICADOR", "NEWLINE", "COMENTARIO_DE_BLOQUE", "COMENTARIO_DE_LINEA", 
			"ESPACIOS", "COMENTARIO_SIN_CERRAR", "TEXTO_SIN_CERRAR", "CARACTER_INVALIDO", 
			"ERROR_LEXICO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'%estructuras'", "'%funciones'", "'estructura'", "'definir'", 
			"'retornar'", "'entero'", "'flotante'", "'cadena'", "'caracter'", "'bool'", 
			"'verdadero'", "'falso'", "'si'", "'sino'", "'contrario'", "'entonces'", 
			"'elegir'", "'caso'", "'siempre'", "'para'", "'mientras'", "'hacer'", 
			"'romper'", "'continuar'", "'imprimir'", "'leer'", "'->'", "'=='", "'!='", 
			"'<='", "'>='", "'&&'", "'||'", "'++'", "'--'", "'!'", "'<'", "'>'", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "':'", "';'", "','", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SECCION_ESTRUCTURAS", "SECCION_FUNCIONES", "ESTRUCTURA", "DEFINIR", 
			"RETORNAR", "ENTERO_T", "FLOTANTE_T", "CADENA_T", "CARACTER_T", "BOOL_T", 
			"VERDADERO", "FALSO", "SI", "SINO", "CONTRARIO", "ENTONCES", "ELEGIR", 
			"CASO", "SIEMPRE", "PARA", "MIENTRAS", "HACER", "ROMPER", "CONTINUAR", 
			"IMPRIMIR", "LEER", "FLECHA", "IGUAL_QUE", "DIFERENTE_DE", "MENOR_O_IGUAL_QUE", 
			"MAYOR_O_IGUAL_QUE", "Y_LOGICO", "O_LOGICO", "INCREMENTO", "DECREMENTO", 
			"NO_LOGICO", "MENOR_QUE", "MAYOR_QUE", "SUMA", "RESTA", "MULTIPLICACION", 
			"DIVISION", "MODULO", "ASIGNACION", "PARENTESIS_IZQUIERDO", "PARENTESIS_DERECHO", 
			"CORCHETE_IZQUIERDO", "CORCHETE_DERECHO", "LLAVE_IZQUIERDA", "LLAVE_DERECHA", 
			"DOS_PUNTOS", "PUNTO_Y_COMA", "COMA", "PUNTO", "DECIMAL", "ENTERO", "TEXTO", 
			"CARACTER", "IDENTIFICADOR", "NEWLINE", "COMENTARIO_DE_BLOQUE", "COMENTARIO_DE_LINEA", 
			"ESPACIOS", "COMENTARIO_SIN_CERRAR", "TEXTO_SIN_CERRAR", "CARACTER_INVALIDO", 
			"ERROR_LEXICO"
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


	    private static final int TAMANO_TAB = 4;

	    // Tokens generados (NEWLINE, INDENT, DEDENT) pendientes de entregar al parser
	    private final java.util.LinkedList<Token> pendientes = new java.util.LinkedList<>();
	    // Pila de niveles de indentacion abiertos (el nivel 0 es implicito)
	    private final java.util.Deque<Integer> indentaciones = new java.util.ArrayDeque<>();
	    // Cantidad de [ { abiertos: dentro de ellos no se procesan saltos de linea
	    private int agrupaciones = 0;
	    private int ultimoTipo = -1;
	    private Token ultimoToken = null;   // ultimo token visible, para ubicar el NEWLINE final
	    private boolean finProcesado = false;

	    @Override
	    public Token nextToken() {
	        if (pendientes.isEmpty()) {
	            // super.nextToken() puede encolar NEWLINE/INDENT/DEDENT (ver procesarSaltoLinea)
	            Token t = super.nextToken();
	            if (t.getType() == EOF && !finProcesado) {
	                finProcesado = true;
	                cerrarBloques();
	            }
	            encolar(t);
	        }
	        return pendientes.poll();
	    }

	    private void encolar(Token t) {
	        if (t.getChannel() == Token.DEFAULT_CHANNEL) {
	            ultimoTipo = t.getType();
	            if (t.getType() != NEWLINE && t.getType() != yParser.INDENT && t.getType() != yParser.DEDENT) {
	                ultimoToken = t;
	            }
	        }
	        pendientes.add(t);
	    }

	    private Token crearToken(int tipo, String texto, int linea, int columna) {
	        CommonToken t = new CommonToken(_tokenFactorySourcePair, tipo, DEFAULT_TOKEN_CHANNEL,
	                _tokenStartCharIndex, _tokenStartCharIndex - 1);
	        t.setText(texto);
	        t.setLine(linea);
	        t.setCharPositionInLine(columna);
	        return t;
	    }

	    private int calcularIndentacion(String espacios) {
	        int columnas = 0;
	        for (char c : espacios.toCharArray()) {
	            if (c == '\t') {
	                columnas += TAMANO_TAB - (columnas % TAMANO_TAB);
	            } else {
	                columnas++;
	            }
	        }
	        return columnas;
	    }

	    // Una linea se ignora si esta en blanco o solo tiene comentarios.
	    // "/* nota */ x = 1" NO se ignora: despues del comentario hay codigo.
	    private boolean esLineaIgnorable() {
	        int i = 1;
	        while (true) {
	            int c = _input.LA(i);
	            if (c == ' ' || c == '\t') {
	                i++;
	            } else if (c == '\n' || c == '\r' || c == EOF) {
	                return true;
	            } else if (c == '/' && _input.LA(i + 1) == '/') {
	                return true;
	            } else if (c == '/' && _input.LA(i + 1) == '*') {
	                i += 2;
	                while (!(_input.LA(i) == '*' && _input.LA(i + 1) == '/')) {
	                    if (_input.LA(i) == EOF) {
	                        return true; // comentario sin cerrar (error lexico aparte)
	                    }
	                    i++;
	                }
	                i += 2;
	            } else {
	                return false;
	            }
	        }
	    }

	    // Accion del token NEWLINE
	    private void procesarSaltoLinea() {
	        if (agrupaciones > 0 || esLineaIgnorable()) {
	            skip();
	            return;
	        }
	        String espacios = getText().replaceAll("[\r\n]+", "");
	        int linea = getLine();
	        int indentacion = calcularIndentacion(espacios);
	        int anterior = indentaciones.isEmpty() ? 0 : indentaciones.peek();

	        encolar(crearToken(NEWLINE, "\\n", _tokenStartLine, _tokenStartCharPositionInLine));

	        if (indentacion > anterior) {
	            indentaciones.push(indentacion);
	            encolar(crearToken(yParser.INDENT, espacios, linea, 0));
	        } else {
	            while (!indentaciones.isEmpty() && indentaciones.peek() > indentacion) {
	                indentaciones.pop();
	                encolar(crearToken(yParser.DEDENT, "", linea, indentacion));
	            }
	            int actual = indentaciones.isEmpty() ? 0 : indentaciones.peek();
	            if (actual != indentacion) {
	                getErrorListenerDispatch().syntaxError(this, null, linea, indentacion,
	                        "Indentacion inconsistente: no coincide con ningun bloque abierto", null);
	            }
	        }
	        skip();
	    }

	    // Al llegar al EOF: cierra la ultima linea y todos los bloques abiertos
	    private void cerrarBloques() {
	        // Se ubican al final del ultimo token real, asi un error en la ultima
	        // linea se reporta en esa linea y no en la siguiente
	        int linea = ultimoToken != null ? ultimoToken.getLine() : getLine();
	        int columna = ultimoToken != null
	                ? ultimoToken.getCharPositionInLine() + ultimoToken.getText().length()
	                : getCharPositionInLine();
	        if (ultimoTipo != -1 && ultimoTipo != NEWLINE) {
	            encolar(crearToken(NEWLINE, "\\n", linea, columna));
	        }
	        while (!indentaciones.isEmpty()) {
	            indentaciones.pop();
	            encolar(crearToken(yParser.DEDENT, "", linea, columna));
	        }
	    }


	public yLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "y.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 46:
			CORCHETE_IZQUIERDO_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			CORCHETE_DERECHO_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			LLAVE_IZQUIERDA_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			LLAVE_DERECHA_action((RuleContext)_localctx, actionIndex);
			break;
		case 59:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void CORCHETE_IZQUIERDO_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 agrupaciones++; 
			break;
		}
	}
	private void CORCHETE_DERECHO_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 if (agrupaciones > 0) agrupaciones--; 
			break;
		}
	}
	private void LLAVE_IZQUIERDA_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 agrupaciones++; 
			break;
		}
	}
	private void LLAVE_DERECHA_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 if (agrupaciones > 0) agrupaciones--; 
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 procesarSaltoLinea(); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000C\u0223\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0002@\u0007@\u0002A\u0007A\u0002B\u0007B\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 "+
		"\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001"+
		"$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0001+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001"+
		".\u0001.\u0001/\u0001/\u0001/\u00010\u00010\u00010\u00011\u00011\u0001"+
		"1\u00012\u00012\u00013\u00013\u00014\u00014\u00015\u00015\u00016\u0004"+
		"6\u0199\b6\u000b6\f6\u019a\u00016\u00016\u00046\u019f\b6\u000b6\f6\u01a0"+
		"\u00017\u00047\u01a4\b7\u000b7\f7\u01a5\u00018\u00018\u00018\u00018\u0005"+
		"8\u01ac\b8\n8\f8\u01af\t8\u00018\u00018\u00019\u00019\u00019\u00019\u0003"+
		"9\u01b7\b9\u00019\u00019\u0001:\u0001:\u0005:\u01bd\b:\n:\f:\u01c0\t:"+
		"\u0001;\u0003;\u01c3\b;\u0001;\u0001;\u0003;\u01c7\b;\u0001;\u0005;\u01ca"+
		"\b;\n;\f;\u01cd\t;\u0001;\u0001;\u0001<\u0001<\u0001<\u0001<\u0005<\u01d5"+
		"\b<\n<\f<\u01d8\t<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001=\u0001=\u0001"+
		"=\u0001=\u0005=\u01e3\b=\n=\f=\u01e6\t=\u0001=\u0001=\u0001>\u0004>\u01eb"+
		"\b>\u000b>\f>\u01ec\u0001>\u0001>\u0001?\u0001?\u0001?\u0001?\u0001?\u0004"+
		"?\u01f6\b?\u000b?\f?\u01f7\u0001?\u0005?\u01fb\b?\n?\f?\u01fe\t?\u0001"+
		"?\u0005?\u0201\b?\n?\f?\u0204\t?\u0001?\u0001?\u0001?\u0001?\u0001@\u0001"+
		"@\u0001@\u0001@\u0005@\u020e\b@\n@\f@\u0211\t@\u0001@\u0001@\u0001A\u0001"+
		"A\u0005A\u0217\bA\nA\fA\u021a\tA\u0001A\u0001A\u0001A\u0001A\u0001B\u0001"+
		"B\u0001B\u0001B\u0001\u01d6\u0000C\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c"+
		"9\u001d;\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6"+
		"m7o8q9s:u;w<y={>}?\u007f@\u0081A\u0083B\u0085C\u0001\u0000\n\u0001\u0000"+
		"09\u0004\u0000\n\n\r\r\"\"\\\\\u0004\u0000\n\n\r\r\'\'\\\\\u0003\u0000"+
		"AZ__az\u0004\u000009AZ__az\u0002\u0000\t\t  \u0002\u0000\n\n\r\r\u0001"+
		"\u0000**\u0002\u0000**//\u0003\u0000\n\n\r\r\'\'\u0236\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005"+
		"\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000"+
		"\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000"+
		"\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C"+
		"\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000"+
		"\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000"+
		"\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q"+
		"\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000"+
		"\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000"+
		"\u0000[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_"+
		"\u0001\u0000\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000"+
		"\u0000\u0000\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000"+
		"\u0000i\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m"+
		"\u0001\u0000\u0000\u0000\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001\u0000"+
		"\u0000\u0000\u0000s\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000"+
		"\u0000w\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000{"+
		"\u0001\u0000\u0000\u0000\u0000}\u0001\u0000\u0000\u0000\u0000\u007f\u0001"+
		"\u0000\u0000\u0000\u0000\u0081\u0001\u0000\u0000\u0000\u0000\u0083\u0001"+
		"\u0000\u0000\u0000\u0000\u0085\u0001\u0000\u0000\u0000\u0001\u0087\u0001"+
		"\u0000\u0000\u0000\u0003\u0094\u0001\u0000\u0000\u0000\u0005\u009f\u0001"+
		"\u0000\u0000\u0000\u0007\u00aa\u0001\u0000\u0000\u0000\t\u00b2\u0001\u0000"+
		"\u0000\u0000\u000b\u00bb\u0001\u0000\u0000\u0000\r\u00c2\u0001\u0000\u0000"+
		"\u0000\u000f\u00cb\u0001\u0000\u0000\u0000\u0011\u00d2\u0001\u0000\u0000"+
		"\u0000\u0013\u00db\u0001\u0000\u0000\u0000\u0015\u00e0\u0001\u0000\u0000"+
		"\u0000\u0017\u00ea\u0001\u0000\u0000\u0000\u0019\u00f0\u0001\u0000\u0000"+
		"\u0000\u001b\u00f3\u0001\u0000\u0000\u0000\u001d\u00f8\u0001\u0000\u0000"+
		"\u0000\u001f\u0102\u0001\u0000\u0000\u0000!\u010b\u0001\u0000\u0000\u0000"+
		"#\u0112\u0001\u0000\u0000\u0000%\u0117\u0001\u0000\u0000\u0000\'\u011f"+
		"\u0001\u0000\u0000\u0000)\u0124\u0001\u0000\u0000\u0000+\u012d\u0001\u0000"+
		"\u0000\u0000-\u0133\u0001\u0000\u0000\u0000/\u013a\u0001\u0000\u0000\u0000"+
		"1\u0144\u0001\u0000\u0000\u00003\u014d\u0001\u0000\u0000\u00005\u0152"+
		"\u0001\u0000\u0000\u00007\u0155\u0001\u0000\u0000\u00009\u0158\u0001\u0000"+
		"\u0000\u0000;\u015b\u0001\u0000\u0000\u0000=\u015e\u0001\u0000\u0000\u0000"+
		"?\u0161\u0001\u0000\u0000\u0000A\u0164\u0001\u0000\u0000\u0000C\u0167"+
		"\u0001\u0000\u0000\u0000E\u016a\u0001\u0000\u0000\u0000G\u016d\u0001\u0000"+
		"\u0000\u0000I\u016f\u0001\u0000\u0000\u0000K\u0171\u0001\u0000\u0000\u0000"+
		"M\u0173\u0001\u0000\u0000\u0000O\u0175\u0001\u0000\u0000\u0000Q\u0177"+
		"\u0001\u0000\u0000\u0000S\u0179\u0001\u0000\u0000\u0000U\u017b\u0001\u0000"+
		"\u0000\u0000W\u017d\u0001\u0000\u0000\u0000Y\u017f\u0001\u0000\u0000\u0000"+
		"[\u0181\u0001\u0000\u0000\u0000]\u0183\u0001\u0000\u0000\u0000_\u0186"+
		"\u0001\u0000\u0000\u0000a\u0189\u0001\u0000\u0000\u0000c\u018c\u0001\u0000"+
		"\u0000\u0000e\u018f\u0001\u0000\u0000\u0000g\u0191\u0001\u0000\u0000\u0000"+
		"i\u0193\u0001\u0000\u0000\u0000k\u0195\u0001\u0000\u0000\u0000m\u0198"+
		"\u0001\u0000\u0000\u0000o\u01a3\u0001\u0000\u0000\u0000q\u01a7\u0001\u0000"+
		"\u0000\u0000s\u01b2\u0001\u0000\u0000\u0000u\u01ba\u0001\u0000\u0000\u0000"+
		"w\u01c6\u0001\u0000\u0000\u0000y\u01d0\u0001\u0000\u0000\u0000{\u01de"+
		"\u0001\u0000\u0000\u0000}\u01ea\u0001\u0000\u0000\u0000\u007f\u01f0\u0001"+
		"\u0000\u0000\u0000\u0081\u0209\u0001\u0000\u0000\u0000\u0083\u0214\u0001"+
		"\u0000\u0000\u0000\u0085\u021f\u0001\u0000\u0000\u0000\u0087\u0088\u0005"+
		"%\u0000\u0000\u0088\u0089\u0005e\u0000\u0000\u0089\u008a\u0005s\u0000"+
		"\u0000\u008a\u008b\u0005t\u0000\u0000\u008b\u008c\u0005r\u0000\u0000\u008c"+
		"\u008d\u0005u\u0000\u0000\u008d\u008e\u0005c\u0000\u0000\u008e\u008f\u0005"+
		"t\u0000\u0000\u008f\u0090\u0005u\u0000\u0000\u0090\u0091\u0005r\u0000"+
		"\u0000\u0091\u0092\u0005a\u0000\u0000\u0092\u0093\u0005s\u0000\u0000\u0093"+
		"\u0002\u0001\u0000\u0000\u0000\u0094\u0095\u0005%\u0000\u0000\u0095\u0096"+
		"\u0005f\u0000\u0000\u0096\u0097\u0005u\u0000\u0000\u0097\u0098\u0005n"+
		"\u0000\u0000\u0098\u0099\u0005c\u0000\u0000\u0099\u009a\u0005i\u0000\u0000"+
		"\u009a\u009b\u0005o\u0000\u0000\u009b\u009c\u0005n\u0000\u0000\u009c\u009d"+
		"\u0005e\u0000\u0000\u009d\u009e\u0005s\u0000\u0000\u009e\u0004\u0001\u0000"+
		"\u0000\u0000\u009f\u00a0\u0005e\u0000\u0000\u00a0\u00a1\u0005s\u0000\u0000"+
		"\u00a1\u00a2\u0005t\u0000\u0000\u00a2\u00a3\u0005r\u0000\u0000\u00a3\u00a4"+
		"\u0005u\u0000\u0000\u00a4\u00a5\u0005c\u0000\u0000\u00a5\u00a6\u0005t"+
		"\u0000\u0000\u00a6\u00a7\u0005u\u0000\u0000\u00a7\u00a8\u0005r\u0000\u0000"+
		"\u00a8\u00a9\u0005a\u0000\u0000\u00a9\u0006\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u0005d\u0000\u0000\u00ab\u00ac\u0005e\u0000\u0000\u00ac\u00ad\u0005"+
		"f\u0000\u0000\u00ad\u00ae\u0005i\u0000\u0000\u00ae\u00af\u0005n\u0000"+
		"\u0000\u00af\u00b0\u0005i\u0000\u0000\u00b0\u00b1\u0005r\u0000\u0000\u00b1"+
		"\b\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005r\u0000\u0000\u00b3\u00b4"+
		"\u0005e\u0000\u0000\u00b4\u00b5\u0005t\u0000\u0000\u00b5\u00b6\u0005o"+
		"\u0000\u0000\u00b6\u00b7\u0005r\u0000\u0000\u00b7\u00b8\u0005n\u0000\u0000"+
		"\u00b8\u00b9\u0005a\u0000\u0000\u00b9\u00ba\u0005r\u0000\u0000\u00ba\n"+
		"\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005e\u0000\u0000\u00bc\u00bd\u0005"+
		"n\u0000\u0000\u00bd\u00be\u0005t\u0000\u0000\u00be\u00bf\u0005e\u0000"+
		"\u0000\u00bf\u00c0\u0005r\u0000\u0000\u00c0\u00c1\u0005o\u0000\u0000\u00c1"+
		"\f\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005f\u0000\u0000\u00c3\u00c4"+
		"\u0005l\u0000\u0000\u00c4\u00c5\u0005o\u0000\u0000\u00c5\u00c6\u0005t"+
		"\u0000\u0000\u00c6\u00c7\u0005a\u0000\u0000\u00c7\u00c8\u0005n\u0000\u0000"+
		"\u00c8\u00c9\u0005t\u0000\u0000\u00c9\u00ca\u0005e\u0000\u0000\u00ca\u000e"+
		"\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005c\u0000\u0000\u00cc\u00cd\u0005"+
		"a\u0000\u0000\u00cd\u00ce\u0005d\u0000\u0000\u00ce\u00cf\u0005e\u0000"+
		"\u0000\u00cf\u00d0\u0005n\u0000\u0000\u00d0\u00d1\u0005a\u0000\u0000\u00d1"+
		"\u0010\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005c\u0000\u0000\u00d3\u00d4"+
		"\u0005a\u0000\u0000\u00d4\u00d5\u0005r\u0000\u0000\u00d5\u00d6\u0005a"+
		"\u0000\u0000\u00d6\u00d7\u0005c\u0000\u0000\u00d7\u00d8\u0005t\u0000\u0000"+
		"\u00d8\u00d9\u0005e\u0000\u0000\u00d9\u00da\u0005r\u0000\u0000\u00da\u0012"+
		"\u0001\u0000\u0000\u0000\u00db\u00dc\u0005b\u0000\u0000\u00dc\u00dd\u0005"+
		"o\u0000\u0000\u00dd\u00de\u0005o\u0000\u0000\u00de\u00df\u0005l\u0000"+
		"\u0000\u00df\u0014\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005v\u0000\u0000"+
		"\u00e1\u00e2\u0005e\u0000\u0000\u00e2\u00e3\u0005r\u0000\u0000\u00e3\u00e4"+
		"\u0005d\u0000\u0000\u00e4\u00e5\u0005a\u0000\u0000\u00e5\u00e6\u0005d"+
		"\u0000\u0000\u00e6\u00e7\u0005e\u0000\u0000\u00e7\u00e8\u0005r\u0000\u0000"+
		"\u00e8\u00e9\u0005o\u0000\u0000\u00e9\u0016\u0001\u0000\u0000\u0000\u00ea"+
		"\u00eb\u0005f\u0000\u0000\u00eb\u00ec\u0005a\u0000\u0000\u00ec\u00ed\u0005"+
		"l\u0000\u0000\u00ed\u00ee\u0005s\u0000\u0000\u00ee\u00ef\u0005o\u0000"+
		"\u0000\u00ef\u0018\u0001\u0000\u0000\u0000\u00f0\u00f1\u0005s\u0000\u0000"+
		"\u00f1\u00f2\u0005i\u0000\u0000\u00f2\u001a\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f4\u0005s\u0000\u0000\u00f4\u00f5\u0005i\u0000\u0000\u00f5\u00f6\u0005"+
		"n\u0000\u0000\u00f6\u00f7\u0005o\u0000\u0000\u00f7\u001c\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f9\u0005c\u0000\u0000\u00f9\u00fa\u0005o\u0000\u0000\u00fa"+
		"\u00fb\u0005n\u0000\u0000\u00fb\u00fc\u0005t\u0000\u0000\u00fc\u00fd\u0005"+
		"r\u0000\u0000\u00fd\u00fe\u0005a\u0000\u0000\u00fe\u00ff\u0005r\u0000"+
		"\u0000\u00ff\u0100\u0005i\u0000\u0000\u0100\u0101\u0005o\u0000\u0000\u0101"+
		"\u001e\u0001\u0000\u0000\u0000\u0102\u0103\u0005e\u0000\u0000\u0103\u0104"+
		"\u0005n\u0000\u0000\u0104\u0105\u0005t\u0000\u0000\u0105\u0106\u0005o"+
		"\u0000\u0000\u0106\u0107\u0005n\u0000\u0000\u0107\u0108\u0005c\u0000\u0000"+
		"\u0108\u0109\u0005e\u0000\u0000\u0109\u010a\u0005s\u0000\u0000\u010a "+
		"\u0001\u0000\u0000\u0000\u010b\u010c\u0005e\u0000\u0000\u010c\u010d\u0005"+
		"l\u0000\u0000\u010d\u010e\u0005e\u0000\u0000\u010e\u010f\u0005g\u0000"+
		"\u0000\u010f\u0110\u0005i\u0000\u0000\u0110\u0111\u0005r\u0000\u0000\u0111"+
		"\"\u0001\u0000\u0000\u0000\u0112\u0113\u0005c\u0000\u0000\u0113\u0114"+
		"\u0005a\u0000\u0000\u0114\u0115\u0005s\u0000\u0000\u0115\u0116\u0005o"+
		"\u0000\u0000\u0116$\u0001\u0000\u0000\u0000\u0117\u0118\u0005s\u0000\u0000"+
		"\u0118\u0119\u0005i\u0000\u0000\u0119\u011a\u0005e\u0000\u0000\u011a\u011b"+
		"\u0005m\u0000\u0000\u011b\u011c\u0005p\u0000\u0000\u011c\u011d\u0005r"+
		"\u0000\u0000\u011d\u011e\u0005e\u0000\u0000\u011e&\u0001\u0000\u0000\u0000"+
		"\u011f\u0120\u0005p\u0000\u0000\u0120\u0121\u0005a\u0000\u0000\u0121\u0122"+
		"\u0005r\u0000\u0000\u0122\u0123\u0005a\u0000\u0000\u0123(\u0001\u0000"+
		"\u0000\u0000\u0124\u0125\u0005m\u0000\u0000\u0125\u0126\u0005i\u0000\u0000"+
		"\u0126\u0127\u0005e\u0000\u0000\u0127\u0128\u0005n\u0000\u0000\u0128\u0129"+
		"\u0005t\u0000\u0000\u0129\u012a\u0005r\u0000\u0000\u012a\u012b\u0005a"+
		"\u0000\u0000\u012b\u012c\u0005s\u0000\u0000\u012c*\u0001\u0000\u0000\u0000"+
		"\u012d\u012e\u0005h\u0000\u0000\u012e\u012f\u0005a\u0000\u0000\u012f\u0130"+
		"\u0005c\u0000\u0000\u0130\u0131\u0005e\u0000\u0000\u0131\u0132\u0005r"+
		"\u0000\u0000\u0132,\u0001\u0000\u0000\u0000\u0133\u0134\u0005r\u0000\u0000"+
		"\u0134\u0135\u0005o\u0000\u0000\u0135\u0136\u0005m\u0000\u0000\u0136\u0137"+
		"\u0005p\u0000\u0000\u0137\u0138\u0005e\u0000\u0000\u0138\u0139\u0005r"+
		"\u0000\u0000\u0139.\u0001\u0000\u0000\u0000\u013a\u013b\u0005c\u0000\u0000"+
		"\u013b\u013c\u0005o\u0000\u0000\u013c\u013d\u0005n\u0000\u0000\u013d\u013e"+
		"\u0005t\u0000\u0000\u013e\u013f\u0005i\u0000\u0000\u013f\u0140\u0005n"+
		"\u0000\u0000\u0140\u0141\u0005u\u0000\u0000\u0141\u0142\u0005a\u0000\u0000"+
		"\u0142\u0143\u0005r\u0000\u0000\u01430\u0001\u0000\u0000\u0000\u0144\u0145"+
		"\u0005i\u0000\u0000\u0145\u0146\u0005m\u0000\u0000\u0146\u0147\u0005p"+
		"\u0000\u0000\u0147\u0148\u0005r\u0000\u0000\u0148\u0149\u0005i\u0000\u0000"+
		"\u0149\u014a\u0005m\u0000\u0000\u014a\u014b\u0005i\u0000\u0000\u014b\u014c"+
		"\u0005r\u0000\u0000\u014c2\u0001\u0000\u0000\u0000\u014d\u014e\u0005l"+
		"\u0000\u0000\u014e\u014f\u0005e\u0000\u0000\u014f\u0150\u0005e\u0000\u0000"+
		"\u0150\u0151\u0005r\u0000\u0000\u01514\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0005-\u0000\u0000\u0153\u0154\u0005>\u0000\u0000\u01546\u0001\u0000"+
		"\u0000\u0000\u0155\u0156\u0005=\u0000\u0000\u0156\u0157\u0005=\u0000\u0000"+
		"\u01578\u0001\u0000\u0000\u0000\u0158\u0159\u0005!\u0000\u0000\u0159\u015a"+
		"\u0005=\u0000\u0000\u015a:\u0001\u0000\u0000\u0000\u015b\u015c\u0005<"+
		"\u0000\u0000\u015c\u015d\u0005=\u0000\u0000\u015d<\u0001\u0000\u0000\u0000"+
		"\u015e\u015f\u0005>\u0000\u0000\u015f\u0160\u0005=\u0000\u0000\u0160>"+
		"\u0001\u0000\u0000\u0000\u0161\u0162\u0005&\u0000\u0000\u0162\u0163\u0005"+
		"&\u0000\u0000\u0163@\u0001\u0000\u0000\u0000\u0164\u0165\u0005|\u0000"+
		"\u0000\u0165\u0166\u0005|\u0000\u0000\u0166B\u0001\u0000\u0000\u0000\u0167"+
		"\u0168\u0005+\u0000\u0000\u0168\u0169\u0005+\u0000\u0000\u0169D\u0001"+
		"\u0000\u0000\u0000\u016a\u016b\u0005-\u0000\u0000\u016b\u016c\u0005-\u0000"+
		"\u0000\u016cF\u0001\u0000\u0000\u0000\u016d\u016e\u0005!\u0000\u0000\u016e"+
		"H\u0001\u0000\u0000\u0000\u016f\u0170\u0005<\u0000\u0000\u0170J\u0001"+
		"\u0000\u0000\u0000\u0171\u0172\u0005>\u0000\u0000\u0172L\u0001\u0000\u0000"+
		"\u0000\u0173\u0174\u0005+\u0000\u0000\u0174N\u0001\u0000\u0000\u0000\u0175"+
		"\u0176\u0005-\u0000\u0000\u0176P\u0001\u0000\u0000\u0000\u0177\u0178\u0005"+
		"*\u0000\u0000\u0178R\u0001\u0000\u0000\u0000\u0179\u017a\u0005/\u0000"+
		"\u0000\u017aT\u0001\u0000\u0000\u0000\u017b\u017c\u0005%\u0000\u0000\u017c"+
		"V\u0001\u0000\u0000\u0000\u017d\u017e\u0005=\u0000\u0000\u017eX\u0001"+
		"\u0000\u0000\u0000\u017f\u0180\u0005(\u0000\u0000\u0180Z\u0001\u0000\u0000"+
		"\u0000\u0181\u0182\u0005)\u0000\u0000\u0182\\\u0001\u0000\u0000\u0000"+
		"\u0183\u0184\u0005[\u0000\u0000\u0184\u0185\u0006.\u0000\u0000\u0185^"+
		"\u0001\u0000\u0000\u0000\u0186\u0187\u0005]\u0000\u0000\u0187\u0188\u0006"+
		"/\u0001\u0000\u0188`\u0001\u0000\u0000\u0000\u0189\u018a\u0005{\u0000"+
		"\u0000\u018a\u018b\u00060\u0002\u0000\u018bb\u0001\u0000\u0000\u0000\u018c"+
		"\u018d\u0005}\u0000\u0000\u018d\u018e\u00061\u0003\u0000\u018ed\u0001"+
		"\u0000\u0000\u0000\u018f\u0190\u0005:\u0000\u0000\u0190f\u0001\u0000\u0000"+
		"\u0000\u0191\u0192\u0005;\u0000\u0000\u0192h\u0001\u0000\u0000\u0000\u0193"+
		"\u0194\u0005,\u0000\u0000\u0194j\u0001\u0000\u0000\u0000\u0195\u0196\u0005"+
		".\u0000\u0000\u0196l\u0001\u0000\u0000\u0000\u0197\u0199\u0007\u0000\u0000"+
		"\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000"+
		"\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000"+
		"\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u019e\u0005.\u0000\u0000"+
		"\u019d\u019f\u0007\u0000\u0000\u0000\u019e\u019d\u0001\u0000\u0000\u0000"+
		"\u019f\u01a0\u0001\u0000\u0000\u0000\u01a0\u019e\u0001\u0000\u0000\u0000"+
		"\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1n\u0001\u0000\u0000\u0000\u01a2"+
		"\u01a4\u0007\u0000\u0000\u0000\u01a3\u01a2\u0001\u0000\u0000\u0000\u01a4"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a5\u01a3\u0001\u0000\u0000\u0000\u01a5"+
		"\u01a6\u0001\u0000\u0000\u0000\u01a6p\u0001\u0000\u0000\u0000\u01a7\u01ad"+
		"\u0005\"\u0000\u0000\u01a8\u01a9\u0005\\\u0000\u0000\u01a9\u01ac\t\u0000"+
		"\u0000\u0000\u01aa\u01ac\b\u0001\u0000\u0000\u01ab\u01a8\u0001\u0000\u0000"+
		"\u0000\u01ab\u01aa\u0001\u0000\u0000\u0000\u01ac\u01af\u0001\u0000\u0000"+
		"\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000"+
		"\u0000\u01ae\u01b0\u0001\u0000\u0000\u0000\u01af\u01ad\u0001\u0000\u0000"+
		"\u0000\u01b0\u01b1\u0005\"\u0000\u0000\u01b1r\u0001\u0000\u0000\u0000"+
		"\u01b2\u01b6\u0005\'\u0000\u0000\u01b3\u01b4\u0005\\\u0000\u0000\u01b4"+
		"\u01b7\t\u0000\u0000\u0000\u01b5\u01b7\b\u0002\u0000\u0000\u01b6\u01b3"+
		"\u0001\u0000\u0000\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000\u01b7\u01b8"+
		"\u0001\u0000\u0000\u0000\u01b8\u01b9\u0005\'\u0000\u0000\u01b9t\u0001"+
		"\u0000\u0000\u0000\u01ba\u01be\u0007\u0003\u0000\u0000\u01bb\u01bd\u0007"+
		"\u0004\u0000\u0000\u01bc\u01bb\u0001\u0000\u0000\u0000\u01bd\u01c0\u0001"+
		"\u0000\u0000\u0000\u01be\u01bc\u0001\u0000\u0000\u0000\u01be\u01bf\u0001"+
		"\u0000\u0000\u0000\u01bfv\u0001\u0000\u0000\u0000\u01c0\u01be\u0001\u0000"+
		"\u0000\u0000\u01c1\u01c3\u0005\r\u0000\u0000\u01c2\u01c1\u0001\u0000\u0000"+
		"\u0000\u01c2\u01c3\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000"+
		"\u0000\u01c4\u01c7\u0005\n\u0000\u0000\u01c5\u01c7\u0005\r\u0000\u0000"+
		"\u01c6\u01c2\u0001\u0000\u0000\u0000\u01c6\u01c5\u0001\u0000\u0000\u0000"+
		"\u01c7\u01cb\u0001\u0000\u0000\u0000\u01c8\u01ca\u0007\u0005\u0000\u0000"+
		"\u01c9\u01c8\u0001\u0000\u0000\u0000\u01ca\u01cd\u0001\u0000\u0000\u0000"+
		"\u01cb\u01c9\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000"+
		"\u01cc\u01ce\u0001\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000\u0000\u0000"+
		"\u01ce\u01cf\u0006;\u0004\u0000\u01cfx\u0001\u0000\u0000\u0000\u01d0\u01d1"+
		"\u0005/\u0000\u0000\u01d1\u01d2\u0005*\u0000\u0000\u01d2\u01d6\u0001\u0000"+
		"\u0000\u0000\u01d3\u01d5\t\u0000\u0000\u0000\u01d4\u01d3\u0001\u0000\u0000"+
		"\u0000\u01d5\u01d8\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000\u0000"+
		"\u0000\u01d6\u01d4\u0001\u0000\u0000\u0000\u01d7\u01d9\u0001\u0000\u0000"+
		"\u0000\u01d8\u01d6\u0001\u0000\u0000\u0000\u01d9\u01da\u0005*\u0000\u0000"+
		"\u01da\u01db\u0005/\u0000\u0000\u01db\u01dc\u0001\u0000\u0000\u0000\u01dc"+
		"\u01dd\u0006<\u0005\u0000\u01ddz\u0001\u0000\u0000\u0000\u01de\u01df\u0005"+
		"/\u0000\u0000\u01df\u01e0\u0005/\u0000\u0000\u01e0\u01e4\u0001\u0000\u0000"+
		"\u0000\u01e1\u01e3\b\u0006\u0000\u0000\u01e2\u01e1\u0001\u0000\u0000\u0000"+
		"\u01e3\u01e6\u0001\u0000\u0000\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000"+
		"\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u01e7\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e4\u0001\u0000\u0000\u0000\u01e7\u01e8\u0006=\u0005\u0000\u01e8"+
		"|\u0001\u0000\u0000\u0000\u01e9\u01eb\u0007\u0005\u0000\u0000\u01ea\u01e9"+
		"\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001\u0000\u0000\u0000\u01ec\u01ea"+
		"\u0001\u0000\u0000\u0000\u01ec\u01ed\u0001\u0000\u0000\u0000\u01ed\u01ee"+
		"\u0001\u0000\u0000\u0000\u01ee\u01ef\u0006>\u0006\u0000\u01ef~\u0001\u0000"+
		"\u0000\u0000\u01f0\u01f1\u0005/\u0000\u0000\u01f1\u01f2\u0005*\u0000\u0000"+
		"\u01f2\u01fc\u0001\u0000\u0000\u0000\u01f3\u01fb\b\u0007\u0000\u0000\u01f4"+
		"\u01f6\u0005*\u0000\u0000\u01f5\u01f4\u0001\u0000\u0000\u0000\u01f6\u01f7"+
		"\u0001\u0000\u0000\u0000\u01f7\u01f5\u0001\u0000\u0000\u0000\u01f7\u01f8"+
		"\u0001\u0000\u0000\u0000\u01f8\u01f9\u0001\u0000\u0000\u0000\u01f9\u01fb"+
		"\b\b\u0000\u0000\u01fa\u01f3\u0001\u0000\u0000\u0000\u01fa\u01f5\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fe\u0001\u0000\u0000\u0000\u01fc\u01fa\u0001"+
		"\u0000\u0000\u0000\u01fc\u01fd\u0001\u0000\u0000\u0000\u01fd\u0202\u0001"+
		"\u0000\u0000\u0000\u01fe\u01fc\u0001\u0000\u0000\u0000\u01ff\u0201\u0005"+
		"*\u0000\u0000\u0200\u01ff\u0001\u0000\u0000\u0000\u0201\u0204\u0001\u0000"+
		"\u0000\u0000\u0202\u0200\u0001\u0000\u0000\u0000\u0202\u0203\u0001\u0000"+
		"\u0000\u0000\u0203\u0205\u0001\u0000\u0000\u0000\u0204\u0202\u0001\u0000"+
		"\u0000\u0000\u0205\u0206\u0005\u0000\u0000\u0001\u0206\u0207\u0001\u0000"+
		"\u0000\u0000\u0207\u0208\u0006?\u0005\u0000\u0208\u0080\u0001\u0000\u0000"+
		"\u0000\u0209\u020f\u0005\"\u0000\u0000\u020a\u020b\u0005\\\u0000\u0000"+
		"\u020b\u020e\t\u0000\u0000\u0000\u020c\u020e\b\u0001\u0000\u0000\u020d"+
		"\u020a\u0001\u0000\u0000\u0000\u020d\u020c\u0001\u0000\u0000\u0000\u020e"+
		"\u0211\u0001\u0000\u0000\u0000\u020f\u020d\u0001\u0000\u0000\u0000\u020f"+
		"\u0210\u0001\u0000\u0000\u0000\u0210\u0212\u0001\u0000\u0000\u0000\u0211"+
		"\u020f\u0001\u0000\u0000\u0000\u0212\u0213\u0006@\u0005\u0000\u0213\u0082"+
		"\u0001\u0000\u0000\u0000\u0214\u0218\u0005\'\u0000\u0000\u0215\u0217\b"+
		"\t\u0000\u0000\u0216\u0215\u0001\u0000\u0000\u0000\u0217\u021a\u0001\u0000"+
		"\u0000\u0000\u0218\u0216\u0001\u0000\u0000\u0000\u0218\u0219\u0001\u0000"+
		"\u0000\u0000\u0219\u021b\u0001\u0000\u0000\u0000\u021a\u0218\u0001\u0000"+
		"\u0000\u0000\u021b\u021c\u0005\'\u0000\u0000\u021c\u021d\u0001\u0000\u0000"+
		"\u0000\u021d\u021e\u0006A\u0005\u0000\u021e\u0084\u0001\u0000\u0000\u0000"+
		"\u021f\u0220\t\u0000\u0000\u0000\u0220\u0221\u0001\u0000\u0000\u0000\u0221"+
		"\u0222\u0006B\u0005\u0000\u0222\u0086\u0001\u0000\u0000\u0000\u0015\u0000"+
		"\u019a\u01a0\u01a5\u01ab\u01ad\u01b6\u01be\u01c2\u01c6\u01cb\u01d6\u01e4"+
		"\u01ec\u01f7\u01fa\u01fc\u0202\u020d\u020f\u0218\u0007\u0001.\u0000\u0001"+
		"/\u0001\u00010\u0002\u00011\u0003\u0001;\u0004\u0000\u0001\u0000\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}