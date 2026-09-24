package com.mycompany.proyectounocompi2.errores;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

/**
 * Registra los errores del parser
 * {@link EstrategiaErroresSintacticos}.
 */
public class EscuchaErroresSintacticos extends BaseErrorListener {

    private final ManejadorErrores manejador;

    public EscuchaErroresSintacticos(ManejadorErrores manejador) {
        this.manejador = manejador;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object simbolo, int linea, int columna,
            String mensaje, RecognitionException e) {
        String lexema = null;
        if (simbolo instanceof Token token) {
            lexema = EstrategiaErroresSintacticos.describirToken(token, recognizer.getVocabulary());
        }
        manejador.agregar(TipoError.SINTACTICO, linea, columna + 1, lexema, mensaje);
    }
}
