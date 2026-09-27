package com.mycompany.proyectounocompi2.ast.y;

// Recorrido del AST de Y un metodo por cada tipo de nodo.
public interface Visitor<T> {

    // Programa 
    T visitarPrograma(Programa nodo);
    T visitarDefinicionEstructura(DefinicionEstructura nodo);
    T visitarAtributoEstructura(AtributoEstructura nodo);
    T visitarDefinicionFuncion(DefinicionFuncion nodo);
    T visitarParametro(Parametro nodo);

    // Sentencias 
    T visitarDeclaracion(Declaracion nodo);
    T visitarAsignacion(Asignacion nodo);
    T visitarIncrementoDecremento(IncrementoDecremento nodo);
    T visitarSentenciaExpresion(SentenciaExpresion nodo);
    T visitarImprimir(Imprimir nodo);
    T visitarRomper(Romper nodo);
    T visitarContinuar(Continuar nodo);
    T visitarRetornar(Retornar nodo);
    T visitarCondicional(Condicional nodo);
    T visitarRamaCondicional(RamaCondicional nodo);
    T visitarElegir(Elegir nodo);
    T visitarCaso(Caso nodo);
    T visitarCicloPara(CicloPara nodo);
    T visitarCicloMientras(CicloMientras nodo);
    T visitarCicloHacer(CicloHacer nodo);

    // Expresiones 
    T visitarLiteral(Literal nodo);
    T visitarListaInicializacion(ListaInicializacion nodo);
    T visitarAcceso(Acceso nodo);
    T visitarLlamadaFuncion(LlamadaFuncion nodo);
    T visitarLeer(Leer nodo);
    T visitarOperacionUnaria(OperacionUnaria nodo);
    T visitarOperacionBinaria(OperacionBinaria nodo);

    // Sufijos de un acceso 
    T visitarSufijoIndice(SufijoIndice nodo);
    T visitarSufijoAtributo(SufijoAtributo nodo);
}
