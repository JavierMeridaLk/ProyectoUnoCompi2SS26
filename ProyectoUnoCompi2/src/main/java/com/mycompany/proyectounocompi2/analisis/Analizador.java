package com.mycompany.proyectounocompi2.analisis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import com.mycompany.ZetarianoLexer;
import com.mycompany.ZetarianoParser;
import com.mycompany.pigLatinLexer;
import com.mycompany.pigLatinParser;
import com.mycompany.proyectounocompi2.errores.EscuchaErroresLexicos;
import com.mycompany.proyectounocompi2.errores.EscuchaErroresSintacticos;
import com.mycompany.proyectounocompi2.errores.EstrategiaErroresSintacticos;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.errores.TipoError;
import com.mycompany.yLexer;
import com.mycompany.yParser;


public final class Analizador {

    private Analizador() {
    }

    public static ResultadoAnalisis analizarArchivo(Path archivo) throws IOException {
        String nombre = archivo.getFileName().toString();
        Lenguaje lenguaje = Lenguaje.desdeArchivo(nombre)
                .orElseThrow(() -> new IllegalArgumentException("Extensión no soportada: " + nombre));
        return analizar(Files.readString(archivo), lenguaje, nombre);
    }

    public static ResultadoAnalisis analizar(String codigo, Lenguaje lenguaje, String nombreArchivo) {
        ManejadorErrores manejador = new ManejadorErrores(nombreArchivo);
        CharStream entrada = CharStreams.fromString(codigo, nombreArchivo);

        // ---------- Lexico ----------
        Lexer lexer = switch (lenguaje) {
            case PIG_LATIN -> new pigLatinLexer(entrada);
            case Y -> new yLexer(entrada);
            case ZETARIANO -> new ZetarianoLexer(entrada);
        };
        lexer.removeErrorListeners();
        lexer.addErrorListener(new EscuchaErroresLexicos(manejador));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        reportarTokensInvalidos(tokens, lexer, manejador);

        // ---------- Sintactico ----------
        Parser parser = switch (lenguaje) {
            case PIG_LATIN -> new pigLatinParser(tokens);
            case Y -> new yParser(tokens);
            case ZETARIANO -> new ZetarianoParser(tokens);
        };
        parser.removeErrorListeners();
        parser.addErrorListener(new EscuchaErroresSintacticos(manejador));
        parser.setErrorHandler(new EstrategiaErroresSintacticos());
        ParseTree arbol;
        try {
            arbol = switch (lenguaje) {
                case PIG_LATIN -> ((pigLatinParser) parser).programa();
                case Y -> ((yParser) parser).programa();
                case ZETARIANO -> ((ZetarianoParser) parser).compilacion();
            };
        } catch (StackOverflowError e) {
            // Miles de niveles de anidamiento: se reporta como error en vez de tumbar la aplicacion
            manejador.agregar(TipoError.SINTACTICO, 1, 1, null,
                    "El programa tiene demasiados niveles de anidamiento para poder analizarse");
            return new ResultadoAnalisis(lenguaje, new ParserRuleContext(), parser, tokens, manejador);
        }
        return new ResultadoAnalisis(lenguaje, arbol, parser, tokens, manejador);
    }

    /**
     * Los tokens de error viajan por el canal oculto para que el parser no los vea
     */
    private static void reportarTokensInvalidos(CommonTokenStream tokens, Lexer lexer, ManejadorErrores manejador) {
        for (Token token : tokens.getTokens()) {
            String nombre = lexer.getVocabulary().getSymbolicName(token.getType());
            if (nombre == null) {
                continue;
            }
            switch (nombre) {
                case "ERROR_LEXICO" -> manejador.lexico(token,
                        "Símbolo no reconocido por el lenguaje: '" + token.getText() + "'");
                case "TEXTO_SIN_CERRAR" -> manejador.lexico(token,
                        "Cadena sin cerrar: falta la comilla doble (\") de cierre en la misma línea");
                case "CARACTER_INVALIDO" -> manejador.lexico(token,
                        "Literal de carácter inválido: debe contener exactamente un carácter entre comillas simples");
                case "COMENTARIO_SIN_CERRAR" -> manejador.lexico(token,
                        "Comentario de bloque sin cerrar");
                default -> {
                }
            }
        }
    }
}
