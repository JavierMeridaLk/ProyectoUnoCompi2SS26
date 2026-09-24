grammar Zetariano;

// Paquete de los archivos generados
@header { package com.mycompany; }



compilacion
    : declaracionClase EOF
    ;

// ---------- Clase ----------

declaracionClase
    : modificador* CLASS IDENTIFICADOR (EXTENDS IDENTIFICADOR)? LLAVE_IZQUIERDA miembro* LLAVE_DERECHA
    ;

modificador
    : PUBLIC
    | PRIVATE
    | PROTECTED
    | STATIC
    ;

miembro
    : declaracionAtributo
    | declaracionConstructor
    | declaracionMetodo
    ;

// String nombre;   int a = 1, b;
declaracionAtributo
    : modificador* tipo declaradorVariable (COMA declaradorVariable)* PUNTO_Y_COMA
    ;

declaracionConstructor
    : modificador* IDENTIFICADOR PARENTESIS_IZQUIERDO parametros? PARENTESIS_DERECHO bloque
    ;

declaracionMetodo
    : modificador* tipoRetorno IDENTIFICADOR PARENTESIS_IZQUIERDO parametros? PARENTESIS_DERECHO bloque
    ;

tipoRetorno
    : tipo
    | VOID
    ;

parametros
    : parametro (COMA parametro)*
    ;

parametro
    : tipo IDENTIFICADOR
    ;

// ---------- Tipos ----------

tipo
    : tipoBase (CORCHETE_IZQUIERDO CORCHETE_DERECHO)*
    ;

tipoBase
    : INT
    | DOUBLE
    | CHAR
    | BOOLEAN
    | STRING
    | IDENTIFICADOR          
    ;

// ---------- Declaracion de variables ----------

declaradorVariable
    : IDENTIFICADOR (ASIGNACION inicializadorVariable)?
    ;

inicializadorVariable
    : expresion
    | inicializadorArreglo
    ;

inicializadorArreglo
    : LLAVE_IZQUIERDA (inicializadorVariable (COMA inicializadorVariable)* COMA?)? LLAVE_DERECHA
    ;

// ---------- Sentencias ----------

bloque
    : LLAVE_IZQUIERDA sentencia* LLAVE_DERECHA
    ;

sentencia
    : bloque                                                    # sentenciaBloque
    | declaracionLocal PUNTO_Y_COMA                             # sentenciaDeclaracion
    | IF PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO sentencia (ELSE sentencia)?     # sentenciaIf
    | SWITCH PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO LLAVE_IZQUIERDA seccionSwitch* LLAVE_DERECHA  # sentenciaSwitch
    | FOR PARENTESIS_IZQUIERDO inicioFor? PUNTO_Y_COMA expresion? PUNTO_Y_COMA listaExpresiones? PARENTESIS_DERECHO sentencia  # sentenciaFor
    | WHILE PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO sentencia                    # sentenciaWhile
    | DO sentencia WHILE PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO PUNTO_Y_COMA  # sentenciaDoWhile
    | BREAK PUNTO_Y_COMA                                        # sentenciaBreak
    | CONTINUE PUNTO_Y_COMA                                     # sentenciaContinue
    | RETURN expresion? PUNTO_Y_COMA                            # sentenciaReturn
    | PRINTLN PARENTESIS_IZQUIERDO expresion? PARENTESIS_DERECHO PUNTO_Y_COMA   # sentenciaPrintln
    | PRINT PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO PUNTO_Y_COMA     # sentenciaPrint
    | expresion PUNTO_Y_COMA                                    # sentenciaExpresion   // asignaciones, x++, llamadas, readln();
    | PUNTO_Y_COMA                                              # sentenciaVacia
    ;

declaracionLocal
    : tipo declaradorVariable (COMA declaradorVariable)*
    ;

seccionSwitch
    : etiquetaSwitch+ sentencia*
    ;

etiquetaSwitch
    : CASE expresion DOS_PUNTOS
    | DEFAULT DOS_PUNTOS
    ;

inicioFor
    : declaracionLocal
    | listaExpresiones
    ;

listaExpresiones
    : expresion (COMA expresion)*
    ;

// ---------- Expresiones (de mayor a menor precedencia) ----------

expresion
    : primario                                                                                     # expresionPrimaria
    | expresion PUNTO IDENTIFICADOR PARENTESIS_IZQUIERDO argumentos? PARENTESIS_DERECHO          # expresionLlamadaMetodo
    | expresion PUNTO IDENTIFICADOR                                                                # expresionAtributo
    | expresion CORCHETE_IZQUIERDO expresion CORCHETE_DERECHO                                     # expresionIndice
    | expresion operador=(INCREMENTO | DECREMENTO)                                                 # expresionPostfija
    | operador=(INCREMENTO | DECREMENTO | SUMA | RESTA | NO_LOGICO) expresion                      # expresionUnaria
    | expresion operador=(MULTIPLICACION | DIVISION | MODULO) expresion                           # expresionMultiplicativa
    | expresion operador=(SUMA | RESTA) expresion                                                   # expresionAditiva
    | expresion operador=(MENOR_QUE | MAYOR_QUE | MENOR_O_IGUAL_QUE | MAYOR_O_IGUAL_QUE) expresion  # expresionRelacional
    | expresion operador=(IGUAL_QUE | DIFERENTE_DE) expresion                                      # expresionIgualdad
    | expresion Y_LOGICO expresion                                                                  # expresionY
    | expresion O_LOGICO expresion                                                                  # expresionO
    | <assoc=right> expresion INTERROGACION expresion DOS_PUNTOS expresion                          # expresionTernaria
    | <assoc=right> expresion operador=(ASIGNACION | MAS_IGUAL | MENOS_IGUAL | POR_IGUAL | ENTRE_IGUAL | MODULO_IGUAL) expresion  # expresionAsignacion
    ;

primario
    : PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO                                   # primarioParentesis
    | literal                                                                              # primarioLiteral
    | THIS                                                                                 # primarioThis
    | IDENTIFICADOR PARENTESIS_IZQUIERDO argumentos? PARENTESIS_DERECHO                   # primarioLlamada      // metodo de la misma clase
    | IDENTIFICADOR                                                                        # primarioIdentificador
    | NEW IDENTIFICADOR PARENTESIS_IZQUIERDO argumentos? PARENTESIS_DERECHO               # primarioNuevoObjeto  // new Persona("Ana", 30)
    | NEW tipoBase (CORCHETE_IZQUIERDO expresion CORCHETE_DERECHO)+ (CORCHETE_IZQUIERDO CORCHETE_DERECHO)*  # primarioNuevoArreglo  // new int[3][3]
    | NEW tipoBase (CORCHETE_IZQUIERDO CORCHETE_DERECHO)+ inicializadorArreglo           # primarioNuevoArregloInicializado // new int[]{1,2}
    | READLN PARENTESIS_IZQUIERDO PARENTESIS_DERECHO                                      # primarioReadln
    ;

argumentos
    : expresion (COMA expresion)*
    ;

literal
    : ENTERO
    | DECIMAL
    | TEXTO
    | CARACTER
    | TRUE
    | FALSE
    | NULL
    ;

// =====================================================================
//                              LEXER
// =====================================================================

// ---------- Palabras reservadas ----------

PUBLIC      : 'public';
PRIVATE     : 'private';
PROTECTED   : 'protected';
STATIC      : 'static';
CLASS       : 'class';
EXTENDS     : 'extends';
VOID        : 'void';
INT         : 'int';
DOUBLE      : 'double';
CHAR        : 'char';
BOOLEAN     : 'boolean';
STRING      : 'String';
NEW         : 'new';
THIS        : 'this';
NULL        : 'null';
TRUE        : 'true';
FALSE       : 'false';
IF          : 'if';
ELSE        : 'else';
SWITCH      : 'switch';
CASE        : 'case';
DEFAULT     : 'default';
FOR         : 'for';
WHILE       : 'while';
DO          : 'do';
BREAK       : 'break';
CONTINUE    : 'continue';
RETURN      : 'return';
PRINTLN     : 'println';
PRINT       : 'print';
READLN      : 'readln';

// ---------- Operadores ----------

MAS_IGUAL           : '+=';
MENOS_IGUAL         : '-=';
POR_IGUAL           : '*=';
ENTRE_IGUAL         : '/=';
MODULO_IGUAL        : '%=';
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
INTERROGACION       : '?';

// ---------- Signos de puntuacion ----------

DOS_PUNTOS            : ':';
PUNTO_Y_COMA          : ';';
COMA                  : ',';
PUNTO                 : '.';
CORCHETE_IZQUIERDO    : '[';
CORCHETE_DERECHO      : ']';
LLAVE_IZQUIERDA       : '{';
LLAVE_DERECHA         : '}';
PARENTESIS_IZQUIERDO  : '(';
PARENTESIS_DERECHO    : ')';

// ---------- Literales ----------

DECIMAL   : [0-9]+ '.' [0-9]+;
ENTERO    : [0-9]+;
TEXTO     : '"' ( '\\' . | ~["\\\r\n] )* '"';
CARACTER  : '\'' ( '\\' . | ~['\\\r\n] ) '\'';

IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]*;

// ---------- Comentarios y espacios ----------

COMENTARIO_DE_BLOQUE : '/*' .*? '*/' -> channel(HIDDEN);
COMENTARIO_DE_LINEA  : '//' ~[\r\n]* -> channel(HIDDEN);
ESPACIOS             : [ \t\r\n]+ -> skip;

// ---------- Errores lexicos ----------

COMENTARIO_SIN_CERRAR : '/*' ( ~'*' | '*'+ ~[*/] )* '*'* EOF -> channel(HIDDEN);
TEXTO_SIN_CERRAR      : '"' ( '\\' . | ~["\\\r\n] )* -> channel(HIDDEN);
CARACTER_INVALIDO     : '\'' ~['\r\n]* '\'' -> channel(HIDDEN);
ERROR_LEXICO          : . -> channel(HIDDEN);
