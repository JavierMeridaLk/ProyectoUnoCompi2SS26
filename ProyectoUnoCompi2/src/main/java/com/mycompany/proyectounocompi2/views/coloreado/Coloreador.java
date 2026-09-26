package com.mycompany.proyectounocompi2.views.coloreado;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import com.mycompany.proyectounocompi2.analisis.Analizador;
import com.mycompany.proyectounocompi2.analisis.Lenguaje;

/**
 * Colorea el editor en tiempo real con el lexer de ANTLR del lenguaje del archivo. 
 */
public class Coloreador {

    private static final int ESPERA_MS = 150;

    private static final Map<String, EstiloToken> ESTILOS = new HashMap<>();

    static {
        asignar(EstiloToken.PALABRA_RESERVADA,
                // Pig Latin
                "IMPORT", "ESTO", "SERIES", "NOVUS", "VERUM", "FALSUS", "FINIS", "SI", "ALITER", "DUM", "FACERE",
                "PER", "PERGE", "INTERRUMPE",
                // Y?
                "DEFINIR", "RETORNAR", "VERDADERO", "FALSO", "ESTRUCTURA", "SINO", "CONTRARIO", "ENTONCES",
                "ELEGIR", "CASO", "SIEMPRE", "PARA", "MIENTRAS", "HACER", "ROMPER", "CONTINUAR",
                // Zetariano
                "PUBLIC", "PRIVATE", "PROTECTED", "STATIC", "CLASS", "EXTENDS", "NEW", "THIS", "NULL", "TRUE",
                "FALSE", "RETURN", "IF", "ELSE", "SWITCH", "CASE", "DEFAULT", "FOR", "WHILE", "DO", "BREAK",
                "CONTINUE");
        asignar(EstiloToken.TIPO,
                "NUMERUS", "TEXTUM", "DECIMALIS", "LITTERA", "BOOL",
                "ENTERO_T", "FLOTANTE_T", "CADENA_T", "CARACTER_T", "BOOL_T",
                "INT", "DOUBLE", "CHAR", "BOOLEAN", "STRING", "VOID");
        asignar(EstiloToken.OPERADOR,
                "SUMA", "RESTA", "MULTIPLICACION", "DIVISION", "MODULO", "ASIGNACION", "IGUAL_QUE", "DIFERENTE_DE",
                "MENOR_QUE", "MAYOR_QUE", "MENOR_O_IGUAL_QUE", "MAYOR_O_IGUAL_QUE", "Y_LOGICO", "O_LOGICO",
                "NO_LOGICO", "NEGACION", "INCREMENTO", "DECREMENTO", "FLECHA", "INTERROGACION",
                "MAS_IGUAL", "MENOS_IGUAL", "POR_IGUAL", "ENTRE_IGUAL", "MODULO_IGUAL");
        asignar(EstiloToken.AGRUPACION,
                "PARENTESIS_IZQUIERDO", "PARENTESIS_DERECHO", "CORCHETE_IZQUIERDO", "CORCHETE_DERECHO",
                "LLAVE_IZQUIERDA", "LLAVE_DERECHA");
        asignar(EstiloToken.SEPARADOR,
                // separadores de seccion
                "VARIABILES", "MAIOR", "FIN_PROGRAMA", "SECCION_ESTRUCTURAS", "SECCION_FUNCIONES",
                // operadores y funciones especiales: >> << imprimir leer println print readln
                "IMPRIMIR", "LEER", "PRINTLN", "PRINT", "READLN");
        asignar(EstiloToken.IDENTIFICADOR, "IDENTIFICADOR");
        asignar(EstiloToken.NUMERO, "ENTERO", "DECIMAL");
        asignar(EstiloToken.TEXTO, "TEXTO", "CARACTER");
        asignar(EstiloToken.COMENTARIO, "COMENTARIO_DE_LINEA", "COMENTARIO_DE_BLOQUE");
    }

    private static void asignar(EstiloToken estilo, String... nombres) {
        for (String nombre : nombres) {
            ESTILOS.put(nombre, estilo);
        }
    }

    private final JTextPane texto;
    private final Timer temporizador;
    private Lenguaje lenguaje;

    public Coloreador(JTextPane texto) {
        this.texto = texto;
        this.temporizador = new Timer(ESPERA_MS, e -> colorear());
        this.temporizador.setRepeats(false);
        texto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                temporizador.restart();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                temporizador.restart();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // son los cambios de color que hace esta misma clase
            }
        });
    }

    // Lenguaje del archivo 
    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
        colorear();
    }

    public void colorear() {
        StyledDocument documento = texto.getStyledDocument();
        int largo = documento.getLength();
        documento.setCharacterAttributes(0, largo, EstiloToken.NORMAL.getAtributos(), true);
        if (lenguaje == null || largo == 0) {
            return;
        }
        String contenido;
        try {
            contenido = documento.getText(0, largo);
        } catch (BadLocationException e) {
            return;
        }
        int[] posiciones = posicionesEnTexto(contenido);
        Lexer lexer = Analizador.crearLexer(lenguaje, CharStreams.fromString(contenido));
        Vocabulary vocabulario = lexer.getVocabulary();

        String anterior = null;
        for (Token token = lexer.nextToken(); token.getType() != Token.EOF; token = lexer.nextToken()) {
            String nombre = vocabulario.getSymbolicName(token.getType());
            EstiloToken estilo = ESTILOS.getOrDefault(nombre, EstiloToken.NORMAL);
            // Pig Latin: el '>' de "MAIOR>" y "VARIABILES>" va con el color de la seccion
            if ("MAYOR_QUE".equals(nombre) && ("MAIOR".equals(anterior) || "VARIABILES".equals(anterior))) {
                estilo = EstiloToken.SEPARADOR;
            }
            if (token.getChannel() == Token.DEFAULT_CHANNEL) {
                anterior = nombre;
            }
            pintar(documento, posiciones, token, estilo);
        }
    }

    private static void pintar(StyledDocument documento, int[] posiciones, Token token, EstiloToken estilo) {
        if (estilo == EstiloToken.NORMAL || token.getStartIndex() > token.getStopIndex()) {
            return;
        }
        int inicio = posiciones[token.getStartIndex()];
        int fin = posiciones[token.getStopIndex() + 1];
        documento.setCharacterAttributes(inicio, fin - inicio, estilo.getAtributos(), true);
    }

    private static int[] posicionesEnTexto(String contenido) {
        int puntos = contenido.codePointCount(0, contenido.length());
        int[] posiciones = new int[puntos + 1];
        int indice = 0;
        for (int i = 0; i < puntos; i++) {
            posiciones[i] = indice;
            indice += Character.charCount(contenido.codePointAt(indice));
        }
        posiciones[puntos] = indice;
        return posiciones;
    }
}
