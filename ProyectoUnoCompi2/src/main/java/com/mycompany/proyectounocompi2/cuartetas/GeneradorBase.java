package com.mycompany.proyectounocompi2.cuartetas;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import com.mycompany.proyectounocompi2.tablas.Campo;
import com.mycompany.proyectounocompi2.tablas.Simbolo;
import com.mycompany.proyectounocompi2.tablas.TablaCompatibilidad;
import com.mycompany.proyectounocompi2.tablas.TablaSimbolos;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import com.mycompany.proyectounocompi2.tablas.TipoDefinido;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.BiConsumer;

public abstract class GeneradorBase {

    protected static final Tipo ENTERO = Tipo.primitivo(TipoPrimitivo.ENTERO);
    protected static final Tipo DECIMAL = Tipo.primitivo(TipoPrimitivo.DECIMAL);
    protected static final Tipo CADENA = Tipo.primitivo(TipoPrimitivo.CADENA);
    protected static final Tipo CARACTER = Tipo.primitivo(TipoPrimitivo.CARACTER);
    protected static final Tipo BOOLEANO = Tipo.primitivo(TipoPrimitivo.BOOLEANO);
    protected static final Tipo VOID = Tipo.primitivo(TipoPrimitivo.VOID);

    protected final Tablas tablas;
    private final ListaCuartetas codigo;
    private final Lenguaje lenguaje;
    private final String archivo;

    private int marco;                  
    private Tipo retorno;               
    private String etiquetaFin;         
    private final Deque<String[]> saltos = new ArrayDeque<>();  

    protected GeneradorBase(Tablas tablas, ListaCuartetas codigo, Lenguaje lenguaje, String archivo) {
        this.tablas = tablas;
        this.codigo = codigo;
        this.lenguaje = lenguaje;
        this.archivo = archivo;
    }

    // Emision 

    protected void emitir(String operador, String argumento1, String argumento2, String resultado) {
        codigo.agregar(operador, argumento1, argumento2, resultado);
    }

    protected String temporal() {
        return codigo.nuevoTemporal();
    }

    protected String etiqueta() {
        return codigo.nuevaEtiqueta();
    }

    protected void colocar(String etiqueta) {
        emitir("label", "", "", etiqueta);
    }

    protected void saltar(String etiqueta) {
        emitir("goto", "", "", etiqueta);
    }

    // Operacion que deja su resultado en un temporal 
    private Valor operar(String operador, String argumento1, String argumento2, Tipo tipo) {
        String resultado = temporal();
        emitir(operador, argumento1, argumento2, resultado);
        return new Valor(resultado, tipo);
    }

    // t = memoria[base + desplazamiento]   
    private Valor leer(String memoria, String base, int desplazamiento, Tipo tipo) {
        return operar("leer_" + memoria, base, String.valueOf(desplazamiento), tipo);
    }

    // memoria[base + desplazamiento] = valor
    private void escribir(String memoria, String base, int desplazamiento, Valor valor) {
        emitir("escribir_" + memoria, valor.direccion(), String.valueOf(desplazamiento), base);
    }

    // H = H + celdas o P = P +/- celdas
    private void actualizar(String registro, String operador, String cantidad) {
        String nuevo = operar(operador, registro, cantidad, ENTERO).direccion();
        emitir("=", nuevo, "", registro);
    }

    // Saltos condicionales 

    protected static boolean esRelacional(OperadorBinario operador) {
        return switch (operador) {
            case IGUAL_QUE, DIFERENTE_DE, MENOR_QUE, MENOR_O_IGUAL_QUE, MAYOR_QUE, MAYOR_O_IGUAL_QUE -> true;
            default -> false;
        };
    }

    protected static boolean esLogico(OperadorBinario operador) {
        return operador == OperadorBinario.Y_LOGICO || operador == OperadorBinario.O_LOGICO;
    }

    // if a op b goto etiqueta
    private void saltarSi(Valor izquierda, String operador, Valor derecha, String etiqueta) {
        emitir("if " + operador, izquierda.direccion(), derecha.direccion(), etiqueta);
    }

    // if a op b goto verdadero; goto falso 
    protected void comparar(OperadorBinario operador, Valor izquierda, Valor derecha, String verdadero,
            String falso) {
        if (tipo(izquierda).equals(CADENA) && tipo(derecha).equals(CADENA)) {
            Valor iguales = operar("cadenas_iguales", izquierda.direccion(), derecha.direccion(), BOOLEANO);
            saltarSi(iguales, "==", new Valor(operador == OperadorBinario.IGUAL_QUE ? "1" : "0", ENTERO), verdadero);
        } else {
            saltarSi(izquierda, operador.getSimbolo(), derecha, verdadero);
        }
        if (falso != null) {
            saltar(falso);
        }
    }

    // Condicion que ya es un valor booleano
    protected void saltarSegun(Valor condicion, String verdadero, String falso) {
        saltarSi(condicion, "==", new Valor("1", ENTERO), verdadero);
        saltar(falso);
    }

    // Valor (1 o 0) de una condicion que se genera con saltos (&&, ||)
    protected Valor valorDeCondicion(BiConsumer<String, String> condicion) {
        String resultado = temporal();
        String verdadero = etiqueta();
        String falso = etiqueta();
        String fin = etiqueta();
        condicion.accept(verdadero, falso);
        colocar(verdadero);
        emitir("=", "1", "", resultado);
        saltar(fin);
        colocar(falso);
        emitir("=", "0", "", resultado);
        colocar(fin);
        return new Valor(resultado, BOOLEANO);
    }

    protected GeneracionException noSoportado(Posicion posicion, String que) {
        return new GeneracionException(archivo, posicion, que);
    }

    // Funciones 

    protected void inicioFuncion(String nombre, int tamanoMarco, Tipo tipoRetorno) {
        marco = tamanoMarco;
        retorno = tipoRetorno;
        etiquetaFin = etiqueta();
        emitir("func", "", "", nombre);
    }

    protected void finFuncion() {
        colocar(etiquetaFin);
        emitir("end", "", "", "");
    }

    protected void finPrograma() {
        colocar(etiquetaFin);
        emitir("halt", "", "", "");
        emitir("end", "", "", "");
    }

    protected void retornar(Valor valor) {
        if (valor != null) {
            emitir("=", convertir(valor, retorno).direccion(), "", "RET");
        }
        saltar(etiquetaFin);
    }

    // Ejecuta la accion dentro del ambito que la tabla de simbolos creo para el nodo
    protected void dentroDe(Object nodo, Runnable accion) {
        tablas.simbolos().entrar(nodo);
        accion.run();
        tablas.simbolos().salirAmbito();
    }

    // Ciclos y switch 

    protected void enCiclo(String salida, String continuar, Runnable cuerpo) {
        saltos.push(new String[]{salida, continuar});
        cuerpo.run();
        saltos.pop();
    }

    // En un switch/elegir sigue siendo el del ciclo que lo contiene
    protected void enSeleccion(String salida, Runnable cuerpo) {
        saltos.push(new String[]{salida, saltos.isEmpty() ? null : saltos.peek()[1]});
        cuerpo.run();
        saltos.pop();
    }

    protected void romper() {
        saltar(saltos.peek()[0]);
    }

    protected void continuar() {
        saltar(saltos.peek()[1]);
    }

    // Memoria 

    protected Direccion direccionVariable(String nombre, Posicion posicion) {
        Simbolo variable = tablas.simbolos().buscarVariable(nombre, posicion);
        return new Direccion("P", variable.desplazamiento(), false, variable.tipo());
    }

    protected Valor este(String clase) {
        return leer("stack", "P", 0, Tipo.clase(clase));
    }

    protected Valor cargar(Direccion direccion) {
        return leer(direccion.enHeap() ? "heap" : "stack", direccion.base(), direccion.desplazamiento(), direccion.tipo());
    }

    protected void guardar(Direccion direccion, Valor valor) {
        escribir(direccion.enHeap() ? "heap" : "stack", direccion.base(), direccion.desplazamiento(),
                convertir(valor, direccion.tipo()));
    }

    protected Direccion direccionCampo(Valor objeto, String nombre) {
        Campo campo = tablas.tipos().buscar(objeto.tipo().nombreClase()).buscarCampo(nombre);
        return new Direccion(objeto.direccion(), campo.desplazamiento(), true, campo.tipo());
    }

    // arreglo[i1][i2] base + k + ((i1 * d2 + i2) * d3 + i3)
    protected Direccion direccionElemento(Valor arreglo, List<Valor> indices, Posicion posicion) {
        int dimensiones = arreglo.tipo().dimensiones();
        if (indices.size() != dimensiones) {
            throw noSoportado(posicion, "se debe indicar un índice por cada dimensión del arreglo ("
                    + dimensiones + ")");
        }
        String desplazamiento = convertir(indices.get(0), ENTERO).direccion();
        for (int j = 1; j < dimensiones; j++) {
            Valor tamano = leer("heap", arreglo.direccion(), j, ENTERO);
            Valor fila = operar("*", desplazamiento, tamano.direccion(), ENTERO);
            desplazamiento = operar("+", fila.direccion(), convertir(indices.get(j), ENTERO).direccion(), ENTERO)
                    .direccion();
        }
        Valor base = operar("+", arreglo.direccion(), desplazamiento, ENTERO);
        return new Direccion(base.direccion(), dimensiones, true, arreglo.tipo().conDimensiones(0));
    }

    // Reserva celdas en el heap y devuelve la primera
    protected Valor reservar(String celdas, Tipo tipo) {
        Valor inicio = operar("=", "H", "", tipo);
        actualizar("H", "+", celdas);
        return inicio;
    }

    protected Valor nuevoArreglo(Tipo tipo, List<Valor> tamanos) {
        Valor inicio = operar("=", "H", "", tipo);
        String elementos = tamanos.get(0).direccion();
        for (int j = 0; j < tamanos.size(); j++) {
            escribir("heap", inicio.direccion(), j, tamanos.get(j));
            if (j > 0) {
                elementos = operar("*", elementos, tamanos.get(j).direccion(), ENTERO).direccion();
            }
        }
        String celdas = operar("+", elementos, String.valueOf(tamanos.size()), ENTERO).direccion();
        actualizar("H", "+", celdas);
        return inicio;
    }

    // Valor de una variable declarada sin valor una estructura/arreglo nuevo si se conoce su tamaño
    protected Valor valorPorDefecto(Tipo tipo, List<Valor> tamanos) {
        if (tipo.dimensiones() > 0) {
            return tamanos.isEmpty() ? new Valor("0", tipo) : nuevoArreglo(tipo, tamanos);
        }
        TipoDefinido definido = tipo.esPrimitivo() ? null : tablas.tipos().buscar(tipo.nombreClase());
        if (definido != null && definido.getCategoria() == TipoDefinido.Categoria.ESTRUCTURA) {
            return estructuraPorDefecto(definido, new HashSet<>());
        }
        return new Valor("0", tipo);
    }

    // Estructura nueva con sus arreglos de tamano fijo y sus estructuras anidadas 
    private Valor estructuraPorDefecto(TipoDefinido estructura, Set<String> enCurso) {
        enCurso.add(estructura.getNombre());
        Valor objeto = reservar(String.valueOf(estructura.getTamano()), Tipo.clase(estructura.getNombre()));
        for (Campo campo : estructura.getCampos()) {
            Tipo tipo = campo.tipo();
            TipoDefinido anidada = tipo.esPrimitivo() || tipo.dimensiones() > 0 ? null
                    : tablas.tipos().buscar(tipo.nombreClase());
            Valor valor = null;
            if (tipo.dimensiones() > 0 && !campo.tamanos().isEmpty()) {
                valor = nuevoArreglo(tipo, constantes(campo.tamanos()));
            } else if (anidada != null && anidada.getCategoria() == TipoDefinido.Categoria.ESTRUCTURA
                    && !enCurso.contains(anidada.getNombre())) {
                valor = estructuraPorDefecto(anidada, enCurso);
            }
            if (valor != null) {
                guardar(direccionCampo(objeto, campo.nombre()), valor);
            }
        }
        enCurso.remove(estructura.getNombre());
        return objeto;
    }

    private static List<Valor> constantes(List<Integer> numeros) {
        List<Valor> valores = new ArrayList<>();
        numeros.forEach(numero -> valores.add(new Valor(String.valueOf(numero), ENTERO)));
        return valores;
    }

    // Valor inicial de una declaracion o asignacion. 
    protected <E> Valor inicializar(Tipo destino, E valor, List<Valor> tamanos, Function<E, List<E>> elementos,
            Function<E, Valor> evaluar) {
        List<E> lista = elementos.apply(valor);
        if (lista == null) {
            return convertir(evaluar.apply(valor), destino);
        }
        if (destino.dimensiones() > 0) {
            int dimensiones = destino.dimensiones();
            Valor arreglo = nuevoArreglo(destino, tamanos.isEmpty() ? forma(lista, dimensiones, elementos) : tamanos);
            List<E> hojas = aplanar(lista, dimensiones, elementos);
            for (int i = 0; i < hojas.size(); i++) {
                Valor elemento = inicializar(destino.conDimensiones(0), hojas.get(i), List.of(), elementos, evaluar);
                escribir("heap", arreglo.direccion(), dimensiones + i, elemento);
            }
            return arreglo;
        }
        TipoDefinido estructura = tablas.tipos().buscar(destino.nombreClase());
        Valor objeto = reservar(String.valueOf(estructura.getTamano()), destino);
        for (int i = 0; i < estructura.getCampos().size(); i++) {
            Campo campo = estructura.getCampos().get(i);
            Valor dato = inicializar(campo.tipo(), lista.get(i), constantes(campo.tamanos()), elementos, evaluar);
            guardar(direccionCampo(objeto, campo.nombre()), dato);
        }
        return objeto;
    }

    // Tamanos de un arreglo que se deducen de la lista
    private static <E> List<Valor> forma(List<E> lista, int dimensiones, Function<E, List<E>> elementos) {
        List<Valor> tamanos = new ArrayList<>();
        List<E> nivel = lista;
        for (int j = 0; j < dimensiones && nivel != null; j++) {
            tamanos.add(new Valor(String.valueOf(nivel.size()), ENTERO));
            nivel = nivel.isEmpty() ? null : elementos.apply(nivel.get(0));
        }
        return tamanos;
    }

    // Elementos de una lista anidada en orden por filas
    private static <E> List<E> aplanar(List<E> lista, int dimensiones, Function<E, List<E>> elementos) {
        if (dimensiones == 1) {
            return lista;
        }
        List<E> hojas = new ArrayList<>();
        for (E elemento : lista) {
            hojas.addAll(aplanar(elementos.apply(elemento), dimensiones - 1, elementos));
        }
        return hojas;
    }

    // Un texto leido se convierte al tipo de donde se guarda
    protected Valor convertir(Valor valor, Tipo destino) {
        if (valor.tipo() != null) {
            return valor;
        }
        if (destino == null || !destino.esPrimitivo() || destino.dimensiones() > 0
                || destino.primitivo() == TipoPrimitivo.CADENA) {
            return new Valor(valor.direccion(), CADENA);
        }
        return operar("cadena_a", valor.direccion(), nombre(destino.primitivo()), destino);
    }

    private static Tipo tipo(Valor valor) {
        return valor.tipo() != null ? valor.tipo() : CADENA;
    }

    private static String nombre(TipoPrimitivo tipo) {
        return tipo.name().toLowerCase();
    }

    private static boolean esSimple(Tipo tipo) {
        return tipo.esPrimitivo() && tipo.dimensiones() == 0;
    }

    // Literales y cadenas 

    protected Valor literal(TipoPrimitivo tipo, Object valor) {
        return switch (tipo) {
            case ENTERO -> new Valor(valor != null ? valor.toString() : "0", ENTERO);
            case DECIMAL -> new Valor(valor.toString(), DECIMAL);
            case CARACTER -> new Valor(String.valueOf((int) (Character) valor), CARACTER);
            case BOOLEANO -> new Valor((Boolean) valor ? "1" : "0", BOOLEANO);
            case CADENA -> cadena((String) valor);
            default -> new Valor("0", Tipo.primitivo(TipoPrimitivo.NULO));
        };
    }

    // Una cadena literal es un operando
    protected Valor cadena(String texto) {
        StringBuilder literal = new StringBuilder("\"");
        for (char c : texto.toCharArray()) {
            switch (c) {
                case '\n' -> literal.append("\\n");
                case '\t' -> literal.append("\\t");
                case '\r' -> literal.append("\\r");
                case '"' -> literal.append("\\\"");
                case '\\' -> literal.append("\\\\");
                default -> literal.append(c);
            }
        }
        return new Valor(literal.append('"').toString(), CADENA);
    }

    protected Valor aCadena(Valor valor) {
        Tipo tipo = tipo(valor);
        if (tipo.equals(CADENA)) {
            return new Valor(valor.direccion(), CADENA);
        }
        if (tipo.equals(BOOLEANO)) {
            // verum/falsus, verdadero/falso o true/false 
            String resultado = temporal();
            String verdadero = etiqueta();
            String falso = etiqueta();
            String fin = etiqueta();
            saltarSegun(valor, verdadero, falso);
            colocar(verdadero);
            emitir("=", cadena(lenguaje.booleano(true)).direccion(), "", resultado);
            saltar(fin);
            colocar(falso);
            emitir("=", cadena(lenguaje.booleano(false)).direccion(), "", resultado);
            colocar(fin);
            return new Valor(resultado, CADENA);
        }
        String conversion = tipo.equals(DECIMAL) ? "decimal_a_cadena"
                : tipo.equals(CARACTER) ? "caracter_a_cadena" : "entero_a_cadena";
        return operar(conversion, valor.direccion(), "", CADENA);
    }

    // Entrada y salida 

    protected void imprimir(Valor valor) {
        Tipo tipo = tipo(valor);
        if (tipo.equals(BOOLEANO)) {
            imprimir(aCadena(valor));
            return;
        }
        String como = tipo.equals(CADENA) ? "cadena" : tipo.equals(DECIMAL) ? "decimal"
                : tipo.equals(CARACTER) ? "caracter" : "entero";
        emitir("print", valor.direccion(), como, "");
    }

    protected void saltoDeLinea() {
        emitir("print", "10", "caracter", "");
    }

    // Lee una linea como texto
    protected Valor leer() {
        return operar("leer", "", "", null);
    }

    // Operaciones 

    protected Valor operacionBinaria(OperadorBinario operador, Valor izquierda, Valor derecha) {
        Tipo tipoIzquierda = tipo(izquierda);
        Tipo tipoDerecha = tipo(derecha);
        if (operador == OperadorBinario.SUMA && (tipoIzquierda.equals(CADENA) || tipoDerecha.equals(CADENA))) {
            return operar("concat", aCadena(izquierda).direccion(), aCadena(derecha).direccion(), CADENA);
        }
        boolean igualdad = operador == OperadorBinario.IGUAL_QUE || operador == OperadorBinario.DIFERENTE_DE;
        if (igualdad && tipoIzquierda.equals(CADENA) && tipoDerecha.equals(CADENA)) {
            Valor iguales = operar("cadenas_iguales", izquierda.direccion(), derecha.direccion(), BOOLEANO);
            return operador == OperadorBinario.IGUAL_QUE ? iguales : operar("!", iguales.direccion(), "", BOOLEANO);
        }
        TipoPrimitivo primitivo = esSimple(tipoIzquierda) && esSimple(tipoDerecha)
                ? TablaCompatibilidad.resultado(operador, tipoIzquierda.primitivo(), tipoDerecha.primitivo()) : null;
        Tipo resultado = primitivo != null ? Tipo.primitivo(primitivo) : BOOLEANO;
        Valor valor = operar(operador.getSimbolo(), izquierda.direccion(), derecha.direccion(), resultado);
        if (operador == OperadorBinario.DIVISION && resultado.equals(ENTERO)) {
            return operar("(int)", valor.direccion(), "", ENTERO);
        }
        return valor;
    }

    protected Valor operacionUnaria(OperadorUnario operador, Valor operando) {
        Tipo numerico = tipo(operando).equals(DECIMAL) ? DECIMAL : ENTERO;
        return switch (operador) {
            case NEGATIVO -> operando.direccion().matches("\\d+(\\.\\d+)?")
                    ? new Valor("-" + operando.direccion(), numerico)
                    : operar("neg", operando.direccion(), "", numerico);
            case NEGACION -> operar("!", operando.direccion(), "", BOOLEANO);
            default -> operando;
        };
    }

    // x++ / x-- / ++x / --x: guarda el nuevo valor y devuelve el anterior o el nuevo
    protected Valor incrementar(Direccion direccion, boolean incremento, boolean devolverAnterior) {
        Valor anterior = cargar(direccion);
        Valor nuevo = operar(incremento ? "+" : "-", anterior.direccion(), "1", direccion.tipo());
        guardar(direccion, nuevo);
        return devolverAnterior ? anterior : nuevo;
    }

    // Llamadas 

    // Nombre en C de una funcion 
    protected static String nombreC(Simbolo funcion) {
        StringBuilder sufijo = new StringBuilder();
        funcion.parametros().forEach(tipo -> sufijo.append('_').append(tipo.toString().replace("[]", "_arr")));
        return switch (funcion.categoria()) {
            case FUNCION -> "f_" + funcion.nombre();
            case CONSTRUCTOR -> funcion.ambito() + "_constructor" + sufijo;
            default -> funcion.ambito() + "_" + funcion.nombre() + sufijo;
        };
    }

    // Funcion que ejecuta los valores iniciales de los atributos de una clase
    protected static String nombreInicializador(String clase) {
        return clase + "_init";
    }

    // Copia los argumentos al marco siguiente, llama y devuelve RET.
    protected Valor llamar(String nombre, Tipo tipoRetorno, List<Valor> argumentos) {
        for (int i = 0; i < argumentos.size(); i++) {
            escribir("stack", "P", marco + i, argumentos.get(i));
        }
        actualizar("P", "+", String.valueOf(marco));
        emitir("call", "", "", nombre);
        actualizar("P", "-", String.valueOf(marco));
        if (tipoRetorno == null || tipoRetorno.equals(VOID)) {
            return new Valor("0", VOID);
        }
        return operar("=", "RET", "", tipoRetorno);
    }

    private List<Valor> convertirArgumentos(Simbolo funcion, List<Valor> argumentos) {
        List<Valor> convertidos = new ArrayList<>();
        for (int i = 0; i < argumentos.size(); i++) {
            convertidos.add(convertir(argumentos.get(i), funcion.parametros().get(i)));
        }
        return convertidos;
    }

    private static List<Tipo> tipos(List<Valor> valores) {
        List<Tipo> tipos = new ArrayList<>();
        valores.forEach(valor -> tipos.add(valor.tipo()));
        return tipos;
    }

    protected Valor llamarFuncion(String nombre, List<Valor> argumentos) {
        Simbolo funcion = tablas.simbolos().buscarFuncion(nombre);
        return llamar(nombreC(funcion), funcion.tipo(), convertirArgumentos(funcion, argumentos));
    }

    protected Valor llamarMetodo(Valor objeto, String nombre, List<Valor> argumentos) {
        Simbolo metodo = TablaSimbolos.elegirSobrecarga(
                tablas.simbolos().buscarMetodos(objeto.tipo().nombreClase(), nombre), tipos(argumentos));
        List<Valor> conThis = new ArrayList<>(List.of(objeto));
        conThis.addAll(convertirArgumentos(metodo, argumentos));
        return llamar(nombreC(metodo), metodo.tipo(), conThis);
    }

    // new/novus: reserva el objeto, ejecuta los valores iniciales de sus atributos y su constructor
    protected Valor crearObjeto(String clase, List<Valor> argumentos) {
        TipoDefinido definido = tablas.tipos().buscar(clase);
        List<Simbolo> constructores = tablas.simbolos().buscarConstructores(clase);
        Valor objeto = reservar(String.valueOf(definido.getTamano()), Tipo.clase(clase));
        llamar(nombreInicializador(clase), VOID, List.of(objeto));
        if (!constructores.isEmpty()) {
            Simbolo constructor = TablaSimbolos.elegirSobrecarga(constructores, tipos(argumentos));
            List<Valor> conThis = new ArrayList<>(List.of(objeto));
            conThis.addAll(convertirArgumentos(constructor, argumentos));
            llamar(nombreC(constructor), VOID, conThis);
        }
        return objeto;
    }
}
