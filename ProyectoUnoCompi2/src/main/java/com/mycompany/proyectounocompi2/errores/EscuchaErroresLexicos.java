package com.mycompany.proyectounocompi2.errores;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Recibe los errores que reporta el propio lexer
 */
public class EscuchaErroresLexicos extends BaseErrorListener {

    private final ManejadorErrores manejador;

    public EscuchaErroresLexicos(ManejadorErrores manejador) {
        this.manejador = manejador;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object simbolo, int linea, int columna,
            String mensaje, RecognitionException e) {
        String descripcion = mensaje.startsWith("token recognition error at: ")
                ? "Símbolo no reconocido por el lenguaje: " + mensaje.substring("token recognition error at: ".length())
                : mensaje;
        manejador.agregar(TipoError.LEXICO, linea, columna + 1, null, descripcion);
    }
}
