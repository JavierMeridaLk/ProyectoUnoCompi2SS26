package com.mycompany.proyectounocompi2.errores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.misc.IntervalSet;

/**
 * Misma recuperacion de errores que ANTLR pero con mensajes en espanol.
 */
public class EstrategiaErroresSintacticos extends DefaultErrorStrategy {

    private static final int MAX_ESPERADOS = 8;

    private static final Map<String, String> NOMBRES = Map.ofEntries(
            Map.entry("IDENTIFICADOR", "identificador"),
            Map.entry("ENTERO", "número entero"),
            Map.entry("DECIMAL", "número decimal"),
            Map.entry("TEXTO", "cadena"),
            Map.entry("CARACTER", "carácter"),
            Map.entry("NEWLINE", "salto de línea"),
            Map.entry("INDENT", "bloque indentado"),
            Map.entry("DEDENT", "fin de bloque"));

    @Override
    protected void reportNoViableAlternative(Parser parser, NoViableAltException e) {
        Token token = e.getOffendingToken();
        String mensaje = token.getType() == Token.EOF
                ? "El archivo terminó antes de completar la instrucción"
                : "Instrucción o expresión no válida: no se esperaba " + describirToken(token, parser.getVocabulary());
        parser.notifyErrorListeners(token, mensaje, e);
    }

    @Override
    protected void reportInputMismatch(Parser parser, InputMismatchException e) {
        Token token = e.getOffendingToken();
        String mensaje = "Se encontró " + describirToken(token, parser.getVocabulary())
                + " pero se esperaba " + describirEsperados(e.getExpectedTokens(), parser.getVocabulary());
        parser.notifyErrorListeners(token, mensaje, e);
    }

    @Override
    protected void reportFailedPredicate(Parser parser, FailedPredicateException e) {
        parser.notifyErrorListeners(e.getOffendingToken(), "Condición no cumplida: " + e.getMessage(), e);
    }

    @Override
    protected void reportUnwantedToken(Parser parser) {
        if (inErrorRecoveryMode(parser)) {
            return;
        }
        beginErrorCondition(parser);
        Token token = parser.getCurrentToken();
        String mensaje = "Sobra " + describirToken(token, parser.getVocabulary())
                + "; se esperaba " + describirEsperados(getExpectedTokens(parser), parser.getVocabulary());
        parser.notifyErrorListeners(token, mensaje, null);
    }

    @Override
    protected void reportMissingToken(Parser parser) {
        if (inErrorRecoveryMode(parser)) {
            return;
        }
        beginErrorCondition(parser);
        Token token = parser.getCurrentToken();
        String mensaje = "Falta " + describirEsperados(getExpectedTokens(parser), parser.getVocabulary())
                + " antes de " + describirToken(token, parser.getVocabulary());
        parser.notifyErrorListeners(token, mensaje, null);
    }

    /** Nombre legible de un token concreto: 'x', fin de archivo, salto de linea... */
    public static String describirToken(Token token, Vocabulary vocabulario) {
        if (token.getType() == Token.EOF) {
            return "fin de archivo";
        }
        String simbolico = vocabulario.getSymbolicName(token.getType());
        if (simbolico != null && NOMBRES.containsKey(simbolico)
                && (simbolico.equals("NEWLINE") || simbolico.equals("INDENT") || simbolico.equals("DEDENT"))) {
            return NOMBRES.get(simbolico);
        }
        return "'" + token.getText() + "'";
    }

    /** Lista legible de tipos de token esperados. */
    public static String describirEsperados(IntervalSet esperados, Vocabulary vocabulario) {
        if (esperados == null || esperados.isNil()) {
            return "otro símbolo";
        }
        List<String> nombres = new ArrayList<>();
        for (int tipo : esperados.toList()) {
            nombres.add(describirTipo(tipo, vocabulario));
        }
        if (nombres.size() > MAX_ESPERADOS) {
            return String.join(", ", nombres.subList(0, MAX_ESPERADOS)) + ", ...";
        }
        if (nombres.size() == 1) {
            return nombres.get(0);
        }
        return "uno de: " + String.join(", ", nombres);
    }

    private static String describirTipo(int tipo, Vocabulary vocabulario) {
        if (tipo == Token.EOF) {
            return "fin de archivo";
        }
        String literal = vocabulario.getLiteralName(tipo);
        if (literal != null) {
            return literal;
        }
        String simbolico = vocabulario.getSymbolicName(tipo);
        return NOMBRES.getOrDefault(simbolico, vocabulario.getDisplayName(tipo));
    }
}
