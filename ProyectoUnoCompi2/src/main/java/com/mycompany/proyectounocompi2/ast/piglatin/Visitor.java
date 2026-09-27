package com.mycompany.proyectounocompi2.ast.piglatin;

// Recorrido del AST de Pig Latin: un metodo por cada tipo de nodo.
// Cada recorrido (arbol grafico, semantico, generacion de codigo...) implementa esta interfaz.
public interface Visitor<T> {

    // Programa
    T visitarPrograma(Programa nodo);
    T visitarImportacion(Importacion nodo);
    // Sentencias 
    T visitarDeclaracion(Declaracion nodo);
    T visitarDeclaracionArreglo(DeclaracionArreglo nodo);
    T visitarAsignacion(Asignacion nodo);
    T visitarIncrementoDecremento(IncrementoDecremento nodo);
    T visitarSentenciaExpresion(SentenciaExpresion nodo);
    T visitarCondicional(Condicional nodo);
    T visitarRamaCondicional(RamaCondicional nodo);
    T visitarCicloDum(CicloDum nodo);
    T visitarCicloFacere(CicloFacere nodo);
    T visitarCicloPer(CicloPer nodo);
    T visitarPerge(Perge nodo);
    T visitarInterrumpe(Interrumpe nodo);
    T visitarLectura(Lectura nodo);
    T visitarEscritura(Escritura nodo);
    // Expresiones 
    T visitarLiteral(Literal nodo);
    T visitarListaInicializacion(ListaInicializacion nodo);
    T visitarAcceso(Acceso nodo);
    T visitarNuevoObjeto(NuevoObjeto nodo);
    T visitarOperacionUnaria(OperacionUnaria nodo);
    T visitarOperacionBinaria(OperacionBinaria nodo);
    // Sufijos de un acceso 
    T visitarSufijoIndice(SufijoIndice nodo);
    T visitarSufijoAtributo(SufijoAtributo nodo);
    T visitarSufijoLlamada(SufijoLlamada nodo);
}
