grammar pigLatin;

// Paquete de los archivos generados 
@header { package com.mycompany; }

programa
    : importacion* seccionVariables? seccionPrincipal EOF
    ;

// ---------- Importaciones ----------

importacion
    : IMPORT rutaImportacion PUNTO_Y_COMA?
    ;

rutaImportacion
    : IDENTIFICADOR (PUNTO IDENTIFICADOR)+
    ;

// ---------- Secciones ----------

seccionVariables
    : VARIABILES MAYOR_QUE declaracion*
    ;

seccionPrincipal
    : MAIOR MAYOR_QUE instruccion* FIN_PROGRAMA PUNTO_Y_COMA
    ;

// ---------- Declaraciones ----------

declaracion
    : declaracionVariable
    | declaracionArreglo
    ;

declaracionVariable
    : ESTO IDENTIFICADOR DOS_PUNTOS cuerpoDeclaracion PUNTO_Y_COMA
    ;

cuerpoDeclaracion
    : tipo inicializador?          # declaracionConTipo       
    | (VERUM | FALSUS)             # declaracionBooleana      
    | creacionObjeto               # declaracionObjeto        
    ;

declaracionArreglo
    : SERIES IDENTIFICADOR dimension+ DOS_PUNTOS tipo inicializador? PUNTO_Y_COMA
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

tipo
    : NUMERUS
    | TEXTUM
    | DECIMALIS
    | LITTERA
    | BOOL
    | IDENTIFICADOR          // estructura (.y) u objeto (.z)
    ;

// ---------- Instrucciones ----------

bloque
    : LLAVE_IZQUIERDA instruccion* LLAVE_DERECHA
    ;

instruccion
    : declaracion
    | asignacion
    | incremento
    | llamada
    | condicional
    | cicloDum
    | cicloFacere
    | cicloPer
    | continuar
    | interrumpir
    | lectura
    | escritura
    ;

asignacion
    : acceso ASIGNACION expresion PUNTO_Y_COMA
    ;

incremento
    : acceso (INCREMENTO | DECREMENTO) PUNTO_Y_COMA
    ;

llamada
    : acceso PUNTO_Y_COMA
    ;

continuar
    : PERGE PUNTO_Y_COMA
    ;

interrumpir
    : INTERRUMPE PUNTO_Y_COMA
    ;

condicional
    : SI PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO bloque
      sinoSi*
      sino?
      FINIS PUNTO_Y_COMA
    ;

sinoSi
    : ALITER PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO bloque
    ;

sino
    : ALITER bloque
    ;

cicloDum
    : DUM PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO bloque FINIS PUNTO_Y_COMA
    ;

cicloFacere
    : FACERE bloque DUM PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO PUNTO_Y_COMA
    ;

cicloPer
    : PER PARENTESIS_IZQUIERDO inicioPer PUNTO_Y_COMA expresion PUNTO_Y_COMA actualizacionPer PARENTESIS_DERECHO bloque (FINIS PUNTO_Y_COMA)?
    ;

inicioPer
    : ESTO IDENTIFICADOR DOS_PUNTOS tipo expresion       # inicioPerDeclaracion
    | acceso ASIGNACION expresion                          # inicioPerAsignacion
    ;

actualizacionPer
    : acceso (INCREMENTO | DECREMENTO)
    | acceso ASIGNACION expresion
    ;

lectura
    : acceso? LEER PUNTO_Y_COMA?
    ;

escritura
    : IMPRIMIR expresion (IMPRIMIR expresion)* PUNTO_Y_COMA
    ;

// ---------- Accesos ----------

acceso
    : IDENTIFICADOR sufijo*
    ;

sufijo
    : CORCHETE_IZQUIERDO expresion CORCHETE_DERECHO                  # sufijoIndice
    | PUNTO IDENTIFICADOR                                              # sufijoAtributo
    | PARENTESIS_IZQUIERDO argumentos? PARENTESIS_DERECHO             # sufijoLlamada
    ;

argumentos
    : expresion (COMA expresion)*
    ;

creacionObjeto
    : NOVUS IDENTIFICADOR PARENTESIS_IZQUIERDO argumentos? PARENTESIS_DERECHO
    ;

// ---------- Expresiones ----------

expresion
    : PARENTESIS_IZQUIERDO expresion PARENTESIS_DERECHO                                       # expresionParentesis
    | literal                                                                                    # expresionLiteral
    | creacionObjeto sufijo*                                                                     # expresionNuevoObjeto   // novus P().getNombre()
    | acceso                                                                                     # expresionAcceso
    | operador=(NEGACION | NO_LOGICO | RESTA) expresion                                          # expresionUnaria
    | expresion operador=(MULTIPLICACION | DIVISION) expresion                                  # expresionMultiplicativa
    | expresion operador=(SUMA | RESTA) expresion                                                # expresionAditiva
    | expresion operador=(MENOR_QUE | MENOR_O_IGUAL_QUE | MAYOR_QUE | MAYOR_O_IGUAL_QUE) expresion # expresionRelacional
    | expresion operador=(IGUAL_QUE | DIFERENTE_DE) expresion                                   # expresionIgualdad
    | expresion Y_LOGICO expresion                                                               # expresionY
    | expresion O_LOGICO expresion                                                               # expresionO
    ;

literal
    : ENTERO
    | DECIMAL
    | TEXTO
    | CARACTER
    | VERUM
    | FALSUS
    ;


// ---------- Palabras reservadas ----------

IMPORT      : 'import';
ESTO        : 'esto';
NUMERUS     : 'numerus';
TEXTUM      : 'textum';
DECIMALIS   : 'decimalis';
LITTERA     : 'littera';
VERUM       : 'verum';
FALSUS      : 'falsus';
BOOL        : 'bool';
SERIES      : 'series';
NOVUS       : 'novus';
SI          : 'si';
ALITER      : 'aliter';
DUM         : 'dum';
FACERE      : 'facere';
PER         : 'per';
PERGE       : 'perge';
INTERRUMPE  : 'interrumpe';
NEGACION    : 'non';
FINIS       : 'finis';
VARIABILES     : 'VARIABILES';
MAIOR          : 'MAIOR';
FIN_PROGRAMA   : 'FINIS';

// ---------- Operadores ----------

IGUAL_QUE             : '==';
DIFERENTE_DE          : '!=';
MENOR_O_IGUAL_QUE     : '<=';
MAYOR_O_IGUAL_QUE     : '>=';
Y_LOGICO              : '&&';
O_LOGICO              : '||';
NO_LOGICO             : '!';
INCREMENTO            : '++';
DECREMENTO            : '--';
LEER                  : '<<';
IMPRIMIR              : '>>';
MENOR_QUE             : '<';
MAYOR_QUE             : '>';
SUMA                  : '+';
RESTA                 : '-';
MULTIPLICACION        : '*';
DIVISION              : '/';
ASIGNACION            : '=';

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

DECIMAL  : [0-9]+ '.' [0-9]+;
ENTERO   : [0-9]+;
TEXTO    : '"' ( '\\' . | ~["\\\r\n] )* '"';
CARACTER : '\'' ( '\\' . | ~['\\\r\n] ) '\'';

IDENTIFICADOR : [a-zA-Z_][a-zA-Z0-9_]*;

// ---------- Comentarios y espacios ----------

COMENTARIO_DE_BLOQUE : '##' .*? '##' -> channel(HIDDEN);
COMENTARIO_DE_LINEA  : '//' ~[\r\n]* -> channel(HIDDEN);
ESPACIOS_EN_BLANCO   : [ \t\r\n]+ -> skip;

// ---------- Errores lexicos ----------
// Van al canal oculto para que el parser no los vea; el Analizador los
// recoge del flujo de tokens y los reporta como errores lexicos.

COMENTARIO_SIN_CERRAR : '##' ( ~'#' | '#' ~'#' )* '#'? EOF -> channel(HIDDEN);
TEXTO_SIN_CERRAR      : '"' ( '\\' . | ~["\\\r\n] )* -> channel(HIDDEN);
CARACTER_INVALIDO     : '\'' ~['\r\n]* '\'' -> channel(HIDDEN);
ERROR_LEXICO          : . -> channel(HIDDEN);
