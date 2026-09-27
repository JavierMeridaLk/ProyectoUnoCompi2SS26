package com.mycompany.proyectounocompi2.ast.zetariano;

// Recorrido del AST de Zetariano: un metodo por cada tipo de nodo.
// Cada recorrido (arbol grafico, semantico, generacion de codigo...) implementa esta interfaz.
public interface Visitor<T> {

    // Clase 
    T visitarClase(Clase nodo);
    T visitarAtributo(Atributo nodo);
    T visitarDeclarador(Declarador nodo);
    T visitarConstructor(Constructor nodo);
    T visitarMetodo(Metodo nodo);
    T visitarParametro(Parametro nodo);
    // Sentencias 
    T visitarBloque(Bloque nodo);
    T visitarDeclaracionLocal(DeclaracionLocal nodo);
    T visitarIf(If nodo);
    T visitarSwitch(Switch nodo);
    T visitarSeccionSwitch(SeccionSwitch nodo);
    T visitarFor(For nodo);
    T visitarWhile(While nodo);
    T visitarDoWhile(DoWhile nodo);
    T visitarBreak(Break nodo);
    T visitarContinue(Continue nodo);
    T visitarReturn(Return nodo);
    T visitarPrintln(Println nodo);
    T visitarPrint(Print nodo);
    T visitarSentenciaExpresion(SentenciaExpresion nodo);
    // Expresiones 
    T visitarLiteral(Literal nodo);
    T visitarListaInicializacion(ListaInicializacion nodo);
    T visitarThis(This nodo);
    T visitarIdentificador(Identificador nodo);
    T visitarLlamadaMetodo(LlamadaMetodo nodo);
    T visitarAccesoAtributo(AccesoAtributo nodo);
    T visitarAccesoIndice(AccesoIndice nodo);
    T visitarOperacionPostfija(OperacionPostfija nodo);
    T visitarOperacionUnaria(OperacionUnaria nodo);
    T visitarOperacionBinaria(OperacionBinaria nodo);
    T visitarTernaria(Ternaria nodo);
    T visitarAsignacionExpresion(AsignacionExpresion nodo);
    T visitarNuevoObjeto(NuevoObjeto nodo);
    T visitarNuevoArreglo(NuevoArreglo nodo);
    T visitarReadln(Readln nodo);
}
