grammar y;

// Paquete de los archivos generados 
@header { package com.mycompany; }

tokens { INDENT, DEDENT }

@lexer::members {
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
}


programa
    : NEWLINE* seccionEstructuras? seccionFunciones EOF
    ;

// ---------- Secciones ----------

seccionEstructuras
    : SECCION_ESTRUCTURAS NEWLINE definicionEstructura*
    ;

seccionFunciones
    : SECCION_FUNCIONES NEWLINE definicionFuncion*
    ;

// ---------- Estructuras ----------

// estructura Persona:
//     entero edad
//     entero notas[3]
//     Direccion domicilio
definicionEstructura
    : ESTRUCTURA IDENTIFICADOR DOS_PUNTOS NEWLINE INDENT atributoEstructura+ DEDENT
    ;

// Las dimensiones deben ser constantes (se valida en el semantico)
atributoEstructura
    : tipo IDENTIFICADOR dimension* NEWLINE
    ;

// ---------- Funciones ----------

// definir nombre(params) -> tipo :
definicionFuncion
    : DEFINIR IDENTIFICADOR PARENTESIS_IZQUIERDO parametros? PARENTESIS_DERECHO (FLECHA tipoRetorno)? DOS_PUNTOS NEWLINE bloque
    ;

tipoRetorno
    : tipo (CORCHETE_IZQUIERDO CORCHETE_DERECHO)*
    ;

parametros
    : parametro (COMA parametro)*
    ;

parametro
    : tipo IDENTIFICADOR                                                              # parametroValor        
    | (CORCHETE_IZQUIERDO CORCHETE_DERECHO)+ tipo IDENTIFICADOR                       # parametroArreglo     
    | LLAVE_IZQUIERDA LLAVE_DERECHA IDENTIFICADOR IDENTIFICADOR                       # parametroEstructura   
    ;

tipo
    : ENTERO_T
    | FLOTANTE_T
    | CADENA_T
    | CARACTER_T
    | BOOL_T
    | IDENTIFICADOR        
    ;

// ---------- Bloques e instrucciones ----------

bloque
    : INDENT instruccion+ DEDENT
    ;

instruccion
    : instruccionSimple PUNTO_Y_COMA? NEWLINE
    | instruccionCompuesta
    ;

instruccionSimple
    : declaracion
    | asignacion
    | incremento
    | llamadaFuncion
    | imprimir
    | leer
    | ROMPER
    | CONTINUAR
    | retornar
    ;

instruccionCompuesta
    : definicionEstructura      
    | condicional
    | elegir
    | cicloPara
    | cicloMientras
    | cicloHacer
    ;


declaracion
    : tipo IDENTIFICADOR dimension* (ASIGNACION inicializador)?
    ;

dimension
    : CORCHETE_IZQUIERDO expresion CORCHETE_DERECHO
    ;

inicializador
    : expresion
    | listaInicializacion
    ;

listaInicializacion
    : LLAVE_IZQUIERDA (inicializador (COMA inicializador)*)? LLAVE_DERECHA
    ;

asignacion
    : acceso ASIGNACION inicializador
    ;

incremento
    : acceso (INCREMENTO | DECREMENTO)
    ;

retornar
    : RETORNAR expresion?
    ;

imprimir
    : IMPRIMIR PARENTESIS_IZQUIERDO argumentos? PARENTESIS_DERECHO
    ;

leer
    : LEER PARENTESIS_IZQUIERDO PARENTESIS_DERECHO
    ;

llamadaFuncion
    : IDENTIFICADOR PARENTESIS_IZQUIERDO argumentos? PARENTESIS_DERECHO
    ;

argumentos
    : expresion (COMA expresion)*
    ;

// ---------- Condicionales ----------

condicional
    : SI PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO ENTONCES NEWLINE bloque
      sinoSi*
      contrario?
    ;

sinoSi
    : SINO PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO ENTONCES NEWLINE bloque
    ;

contrario
    : CONTRARIO NEWLINE bloque
    ;


elegir
    : ELEGIR PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO DOS_PUNTOS NEWLINE
      INDENT caso* casoSiempre? DEDENT
    ;

caso
    : CASO expresion DOS_PUNTOS NEWLINE bloque
    ;

casoSiempre
    : SIEMPRE DOS_PUNTOS NEWLINE bloque
    ;

// ---------- Ciclos ----------

cicloPara
    : PARA PARENTESIS_IZQUIERDO inicioPara? PUNTO_Y_COMA expresion? PUNTO_Y_COMA actualizacionPara? PARENTESIS_DERECHO DOS_PUNTOS NEWLINE bloque
    ;

inicioPara
    : declaracion
    | asignacion
    ;

actualizacionPara
    : incremento
    | asignacion
    ;

cicloMientras
    : MIENTRAS PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO HACER NEWLINE bloque
    ;


cicloHacer
    : HACER DOS_PUNTOS NEWLINE bloque MIENTRAS PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO PUNTO_Y_COMA? NEWLINE
    ;

// ---------- Accesos ----------

acceso
    : IDENTIFICADOR sufijoAcceso*
    ;

sufijoAcceso
    : CORCHETE_IZQUIERDO expresion CORCHETE_DERECHO     # sufijoIndice
    | PUNTO IDENTIFICADOR                                 # sufijoAtributo
    ;

// ---------- Expresiones ----------

expresion
    : PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO                                         # expresionParentesis
    | literal                                                                                      # expresionLiteral
    | leer                                                                                         # expresionLeer
    | llamadaFuncion                                                                               # expresionLlamada
    | acceso                                                                                       # expresionAcceso
    | operador=(NO_LOGICO | RESTA) expresion                                                       # expresionUnaria
    | expresion operador=(MULTIPLICACION | DIVISION | MODULO) expresion                          # expresionMultiplicativa
    | expresion operador=(SUMA | RESTA) expresion                                                  # expresionAditiva
    | expresion operador=(MENOR_QUE | MAYOR_QUE | MENOR_O_IGUAL_QUE | MAYOR_O_IGUAL_QUE) expresion # expresionRelacional
    | expresion operador=(IGUAL_QUE | DIFERENTE_DE) expresion                                     # expresionIgualdad
    | expresion Y_LOGICO expresion                                                                 # expresionY
    | expresion O_LOGICO expresion                                                                 # expresionO
    ;

literal
    : ENTERO
    | DECIMAL
    | TEXTO
    | CARACTER
    | VERDADERO
    | FALSO
    ;

// =====================================================================
//                              LEXER
// =====================================================================

// ---------- Secciones ----------

SECCION_ESTRUCTURAS : '%estructuras';
SECCION_FUNCIONES   : '%funciones';

// ---------- Palabras reservadas ----------

ESTRUCTURA  : 'estructura';
DEFINIR     : 'definir';
RETORNAR    : 'retornar';
ENTERO_T    : 'entero';
FLOTANTE_T  : 'flotante';
CADENA_T    : 'cadena';
CARACTER_T  : 'caracter';
BOOL_T      : 'bool';
VERDADERO   : 'verdadero';
FALSO       : 'falso';
SI          : 'si';
SINO        : 'sino';
CONTRARIO   : 'contrario';
ENTONCES    : 'entonces';
ELEGIR      : 'elegir';
CASO        : 'caso';
SIEMPRE     : 'siempre';
PARA        : 'para';
MIENTRAS    : 'mientras';
HACER       : 'hacer';
ROMPER      : 'romper';
CONTINUAR   : 'continuar';
IMPRIMIR    : 'imprimir';
LEER        : 'leer';

// ---------- Operadores ----------

FLECHA              : '->';
IGUAL_QUE           : '==';
DIFERENTE_DE        : '!=';
MENOR_O_IGUAL_QUE   : '<=';
MAYOR_O_IGUAL_QUE   : '>=';
Y_LOGICO            : '&&';
O_LOGICO            : '||';
INCREMENTO          : '++';
DECREMENTO          : '--';
NO_LOGICO           : '!';
MENOR_QUE           : '<';
MAYOR_QUE           : '>';
SUMA                : '+';
RESTA               : '-';
MULTIPLICACION      : '*';
DIVISION            : '/';
MODULO              : '%';
ASIGNACION          : '=';

// ---------- Signos de agrupacion ----------


PARENTESIS_IZQUIERDO : '(';
PARENTESIS_DERECHO   : ')';
CORCHETE_IZQUIERDO   : '[' { agrupaciones++; };
CORCHETE_DERECHO     : ']' { if (agrupaciones > 0) agrupaciones--; };
LLAVE_IZQUIERDA      : '{' { agrupaciones++; };
LLAVE_DERECHA        : '}' { if (agrupaciones > 0) agrupaciones--; };

// ---------- Signos de puntuacion ----------

DOS_PUNTOS    : ':';
PUNTO_Y_COMA  : ';';
COMA          : ',';
PUNTO         : '.';

// ---------- Literales ----------

DECIMAL   : [0-9]+ '.' [0-9]+;
ENTERO    : [0-9]+;
TEXTO     : '"' ( '\\' . | ~["\\\r\n] )* '"';
CARACTER  : '\'' ( '\\' . | ~['\\\r\n] ) '\'';

IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]*;

// ---------- Saltos de linea / indentacion ----------

NEWLINE
    : ( '\r'? '\n' | '\r' ) [ \t]*  { procesarSaltoLinea(); }
    ;

// ---------- Comentarios y espacios ----------

COMENTARIO_DE_BLOQUE : '/*' .*? '*/' -> channel(HIDDEN);
COMENTARIO_DE_LINEA  : '//' ~[\r\n]* -> channel(HIDDEN);
ESPACIOS             : [ \t]+ -> skip;

// ---------- Errores lexicos ----------


COMENTARIO_SIN_CERRAR : '/*' ( ~'*' | '*'+ ~[*/] )* '*'* EOF -> channel(HIDDEN);
TEXTO_SIN_CERRAR      : '"' ( '\\' . | ~["\\\r\n] )* -> channel(HIDDEN);
CARACTER_INVALIDO     : '\'' ~['\r\n]* '\'' -> channel(HIDDEN);
ERROR_LEXICO          : . -> channel(HIDDEN);
