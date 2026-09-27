package com.mycompany.proyectounocompi2.analizador;

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
import com.mycompany.proyectounocompi2.ast.comun.AstRaiz;
import com.mycompany.proyectounocompi2.ast.zetariano.Clase;
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

    // Lexer de los lenguaje sete tambien lo usa el coloreado
    public static Lexer crearLexer(Lenguaje lenguaje, CharStream entrada) {
        Lexer lexer = switch (lenguaje) {
            case PIG_LATIN -> new pigLatinLexer(entrada);
            case Y -> new yLexer(entrada);
            case ZETARIANO -> new ZetarianoLexer(entrada);
        };
        lexer.removeErrorListeners();
        return lexer;
    }

    public static ResultadoAnalisis analizar(String codigo, Lenguaje lenguaje, String nombreArchivo) {
        ManejadorErrores manejador = new ManejadorErrores(nombreArchivo);
        CharStream entrada = CharStreams.fromString(codigo, nombreArchivo);

        // ---------- Lexico ----------
        Lexer lexer = crearLexer(lenguaje, entrada);
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
        AstRaiz ast = null;
        try {
            arbol = switch (lenguaje) {
                case PIG_LATIN -> ((pigLatinParser) parser).programa();
                case Y -> ((yParser) parser).programa();
                case ZETARIANO -> ((ZetarianoParser) parser).compilacion();
            };
            // ---------- AST ----------
            if (!manejador.hayErrores(TipoError.SINTACTICO)) {
                ast = construirAst(lenguaje, arbol);
                if (ast instanceof Clase clase) {
                    validarNombreClase(clase, nombreArchivo, manejador);
                }
            }
        } catch (StackOverflowError e) {

            manejador.agregar(TipoError.SINTACTICO, 1, 1, null,
                    "El programa tiene demasiados niveles de anidamiento para poder analizarse");
            return new ResultadoAnalisis(lenguaje, new ParserRuleContext(), parser, tokens, null, manejador);
        }
        return new ResultadoAnalisis(lenguaje, arbol, parser, tokens, ast, manejador);
    }

    // valdacion paara el zetario el archivo tiene que llamarse como la clase definida 
    private static void validarNombreClase(Clase clase, String nombreArchivo, ManejadorErrores manejador) {
        String archivo = Path.of(nombreArchivo).getFileName().toString();
        String esperado = clase.nombre() + ".z";
        if (!archivo.equals(esperado)) {
            manejador.agregar(TipoError.SEMANTICO, clase.posicion().linea(), clase.posicion().columna(),
                    clase.nombre(), "La clase '" + clase.nombre() + "' debe estar en un archivo llamado '"
                    + esperado + "' (el archivo se llama '" + archivo + "')");
        }
    }

    // Cada lenguaje tiene su AstBuilder 
    private static AstRaiz construirAst(Lenguaje lenguaje, ParseTree arbol) {
        return (AstRaiz) switch (lenguaje) {
            case PIG_LATIN -> new com.mycompany.proyectounocompi2.visitors.piglatin.AstBuilder().visit(arbol);
            case Y -> new com.mycompany.proyectounocompi2.visitors.y.AstBuilder().visit(arbol);
            case ZETARIANO -> new com.mycompany.proyectounocompi2.visitors.zetariano.AstBuilder().visit(arbol);
        };
    }

    // se ocultan para que el parse continue
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
