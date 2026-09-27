package com.mycompany.proyectounocompi2.analizador;

import com.mycompany.proyectounocompi2.ast.comun.OperadorBinario;
import com.mycompany.proyectounocompi2.ast.comun.OperadorUnario;
import com.mycompany.proyectounocompi2.ast.comun.Posicion;
import com.mycompany.proyectounocompi2.ast.comun.Tipo;
import com.mycompany.proyectounocompi2.ast.comun.TipoPrimitivo;
import com.mycompany.proyectounocompi2.errores.ManejadorErrores;
import com.mycompany.proyectounocompi2.errores.TipoError;
import com.mycompany.proyectounocompi2.tablas.Campo;
import com.mycompany.proyectounocompi2.tablas.Simbolo;
import com.mycompany.proyectounocompi2.tablas.TablaCompatibilidad;
import com.mycompany.proyectounocompi2.tablas.TablaSimbolos;
import com.mycompany.proyectounocompi2.tablas.Tablas;
import com.mycompany.proyectounocompi2.tablas.TipoDefinido;
import java.util.List;
import java.util.function.Function;

// cada nodo del AST tiene su metodo analizar(contexto) que valida su parte y devuelve su tipo 
// Un tipo null significa "desconocido"
public class ContextoSemantico {

    public static final Tipo ENTERO = Tipo.primitivo(TipoPrimitivo.ENTERO);
    public static final Tipo BOOLEANO = Tipo.primitivo(TipoPrimitivo.BOOLEANO);
    public static final Tipo VOID = Tipo.primitivo(TipoPrimitivo.VOID);

    private final Tablas tablas;
    private final ManejadorErrores errores;
    private int ciclos;             
    private int selecciones;        
    private Tipo retorno;           
    private TipoDefinido clase;     

    public ContextoSemantico(Tablas tablas, ManejadorErrores errores) {
        this.tablas = tablas;
        this.errores = errores;
    }

    public Tablas getTablas() {
        return tablas;
    }

    public String getArchivo() {
        return errores.getArchivo();
    }

    public TipoDefinido getClase() {
        return clase;
    }

    public void setClase(TipoDefinido clase) {
        this.clase = clase;
    }

    public void setRetorno(Tipo retorno) {
        this.retorno = retorno;
    }

    // Ejecuta la accion dentro del ambito que la tabla de simbolos creo para el nodo
    public void dentroDe(Object nodo, Runnable accion) {
        tablas.simbolos().entrar(nodo);
        accion.run();
        tablas.simbolos().salirAmbito();
    }

    public void enCiclo(Runnable accion) {
        ciclos++;
        accion.run();
        ciclos--;
    }

    public void enSeleccion(Runnable accion) {
        selecciones++;
        accion.run();
        selecciones--;
    }

    public void error(Posicion posicion, String lexema, String descripcion) {
        errores.agregar(TipoError.SEMANTICO, posicion.linea(), posicion.columna(), lexema, descripcion);
    }

    // El tipo si existe o null si es una estructura/clase no definida 
    public Tipo conocido(Tipo tipo) {
        return tablas.tipos().existe(tipo) ? tipo : null;
    }

    // Primitivo y no arreglo
    public static boolean esSimple(Tipo tipo) {
        return tipo.esPrimitivo() && tipo.dimensiones() == 0;
    }

    public static boolean esNumerico(Tipo tipo) {
        return esSimple(tipo) && TablaCompatibilidad.esAsignable(Tipo.primitivo(TipoPrimitivo.DECIMAL), tipo);
    }

    // ---------- Variables ----------

    public Tipo tipoVariable(String nombre, Posicion posicion) {
        Simbolo simbolo = tablas.simbolos().buscarVariable(nombre, posicion);
        if (simbolo == null) {
            error(posicion, nombre, "La variable '" + nombre + "' no está declarada (o se usa antes de declararla)");
            return null;
        }
        return conocido(simbolo.tipo());
    }

    // ---------- Asignaciones y condiciones ----------

    public void verificarAsignacion(Tipo destino, Tipo origen, Posicion posicion) {
        if (destino != null && origen != null && !TablaCompatibilidad.esAsignable(destino, origen)) {
            error(posicion, null, "No se puede asignar un valor de tipo '" + origen + "' a uno de tipo '" + destino + "'");
        }
    }

    // Valida el valor inicial de una declaracion o asignacion.
    // @param elementos devuelve los elementos si el valor es una lista { }, o null si no lo es
    // @param evaluar   devuelve el tipo de una expresion
    public <E> void verificarInicializacion(Tipo destino, E valor, Posicion posicion,
            Function<E, List<E>> elementos, Function<E, Tipo> evaluar) {
        List<E> lista = elementos.apply(valor);
        if (lista == null) {
            verificarAsignacion(destino, evaluar.apply(valor), posicion);
            return;
        }
        if (destino == null) {
            lista.forEach(elemento -> verificarInicializacion(null, elemento, posicion, elementos, evaluar));
            return;
        }
        if (destino.dimensiones() > 0) {
            Tipo tipoElemento = destino.conDimensiones(destino.dimensiones() - 1);
            lista.forEach(elemento -> verificarInicializacion(tipoElemento, elemento, posicion, elementos, evaluar));
            return;
        }
        TipoDefinido estructura = destino.esPrimitivo() ? null : tablas.tipos().buscar(destino.nombreClase());
        if (estructura == null || estructura.getCategoria() != TipoDefinido.Categoria.ESTRUCTURA) {
            error(posicion, null, "Un valor de tipo '" + destino + "' no se puede inicializar con una lista { }");
            return;
        }
        List<Campo> campos = estructura.getCampos();
        if (lista.size() != campos.size()) {
            error(posicion, null, "La estructura '" + destino + "' tiene " + campos.size()
                    + " atributo(s) y se dieron " + lista.size() + " valor(es)");
        }
        for (int i = 0; i < Math.min(lista.size(), campos.size()); i++) {
            verificarInicializacion(conocido(campos.get(i).tipo()), lista.get(i), posicion, elementos, evaluar);
        }
    }

    public void verificarCondicion(Tipo tipo, Posicion posicion) {
        if (tipo != null && !tipo.equals(BOOLEANO)) {
            error(posicion, null, "La condición debe ser de tipo booleano (se encontró '" + tipo + "')");
        }
    }

    public void verificarEntero(Tipo tipo, Posicion posicion, String que) {
        if (tipo != null && !TablaCompatibilidad.esAsignable(ENTERO, tipo)) {
            error(posicion, null, que + " debe ser de tipo entero (se encontró '" + tipo + "')");
        }
    }

    public void verificarNumerico(Tipo tipo, Posicion posicion, String lexema) {
        if (tipo != null && !esNumerico(tipo)) {
            error(posicion, lexema, "Solo se puede incrementar o decrementar un valor numérico (se encontró '"
                    + tipo + "')");
        }
    }

    // Imprimir un valor cualquier tipo menos void
    public void verificarImprimible(Tipo tipo, Posicion posicion) {
        if (VOID.equals(tipo)) {
            error(posicion, null, "No se puede imprimir una llamada que no retorna valor");
        }
    }

    // continue, perge, continuar, interrumpe
    public void verificarCiclo(Posicion posicion, String sentencia) {
        if (ciclos == 0) {
            error(posicion, sentencia, "'" + sentencia + "' solo se puede usar dentro de un ciclo");
        }
    }

    // break, romper
    public void verificarRomper(Posicion posicion, String sentencia) {
        if (ciclos == 0 && selecciones == 0) {
            error(posicion, sentencia, "'" + sentencia + "' solo se puede usar dentro de un ciclo o de un "
                    + (sentencia.equals("break") ? "switch" : "elegir"));
        }
    }

    // Tipo del retorno
    public void verificarRetorno(Tipo valor, boolean tieneValor, Posicion posicion) {
        Tipo esperado = retorno;
        if (esperado == null) {
            return;
        }
        if (esperado.equals(VOID) && tieneValor) {
            error(posicion, null, "No se puede retornar un valor en una función o método sin tipo de retorno");
        } else if (!esperado.equals(VOID) && !tieneValor) {
            error(posicion, null, "Se debe retornar un valor de tipo '" + esperado + "'");
        } else if (tieneValor && valor != null && !TablaCompatibilidad.esAsignable(esperado, valor)) {
            error(posicion, null, "Se debe retornar un valor de tipo '" + esperado + "' (se encontró '" + valor + "')");
        }
    }

    // ---------- Operaciones ----------

    public Tipo operacionBinaria(OperadorBinario operador, Tipo izquierda, Tipo derecha, Posicion posicion) {
        if (izquierda == null || derecha == null) {
            return null;
        }
        if (esSimple(izquierda) && esSimple(derecha)) {
            TipoPrimitivo resultado = TablaCompatibilidad.resultado(operador, izquierda.primitivo(), derecha.primitivo());
            if (resultado != null) {
                return Tipo.primitivo(resultado);
            }
        } else if ((operador == OperadorBinario.IGUAL_QUE || operador == OperadorBinario.DIFERENTE_DE)
                && (TablaCompatibilidad.esAsignable(izquierda, derecha)
                || TablaCompatibilidad.esAsignable(derecha, izquierda))) {
            
            return BOOLEANO;
        }
        error(posicion, operador.getSimbolo(), "El operador '" + operador.getSimbolo() + "' no se puede aplicar a '"
                + izquierda + "' y '" + derecha + "'");
        return null;
    }

    public Tipo operacionUnaria(OperadorUnario operador, Tipo operando, Posicion posicion) {
        if (operando == null) {
            return null;
        }
        if (esSimple(operando)) {
            TipoPrimitivo resultado = TablaCompatibilidad.resultado(operador, operando.primitivo());
            if (resultado != null) {
                return Tipo.primitivo(resultado);
            }
        }
        error(posicion, operador.getSimbolo(), "El operador '" + operador.getSimbolo() + "' no se puede aplicar a '"
                + operando + "'");
        return null;
    }

    // ---------- Arreglos y atributos ----------

    public Tipo tipoIndice(Tipo arreglo, Tipo indice, Posicion posicion) {
        verificarEntero(indice, posicion, "El índice");
        if (arreglo == null) {
            return null;
        }
        if (arreglo.dimensiones() == 0) {
            error(posicion, null, "Un valor de tipo '" + arreglo + "' no es un arreglo");
            return null;
        }
        return arreglo.conDimensiones(arreglo.dimensiones() - 1);
    }

    public Tipo tipoAtributo(Tipo objeto, String nombre, Posicion posicion) {
        if (objeto == null) {
            return null;
        }
        TipoDefinido definido = objeto.esPrimitivo() || objeto.dimensiones() > 0 ? null
                : tablas.tipos().buscar(objeto.nombreClase());
        if (definido == null) {
            error(posicion, nombre, "Un valor de tipo '" + objeto + "' no tiene atributos");
            return null;
        }
        Campo campo = definido.buscarCampo(nombre);
        if (campo == null) {
            error(posicion, nombre, "El tipo '" + objeto + "' no tiene un atributo '" + nombre + "'");
            return null;
        }
        return conocido(campo.tipo());
    }

    // ---------- Llamadas ----------

    public Tipo llamarFuncion(String nombre, List<Tipo> argumentos, Posicion posicion) {
        Simbolo funcion = tablas.simbolos().buscarFuncion(nombre);
        if (funcion == null) {
            error(posicion, nombre, "La función '" + nombre + "' no está definida");
            return null;
        }
        if (funcion.parametros().size() != argumentos.size()) {
            error(posicion, nombre, "La función '" + nombre + "' espera " + funcion.parametros().size()
                    + " argumento(s) y se dieron " + argumentos.size());
        } else {
            for (int i = 0; i < argumentos.size(); i++) {
                Tipo parametro = conocido(funcion.parametros().get(i));
                Tipo argumento = argumentos.get(i);
                if (parametro != null && argumento != null && !TablaCompatibilidad.esAsignable(parametro, argumento)) {
                    error(posicion, nombre, "El argumento " + (i + 1) + " de '" + nombre + "' debe ser de tipo '"
                            + parametro + "' (se encontró '" + argumento + "')");
                }
            }
        }
        return conocido(funcion.tipo());
    }

    public Tipo llamarMetodo(Tipo objeto, String nombre, List<Tipo> argumentos, Posicion posicion) {
        if (objeto == null) {
            return null;
        }
        TipoDefinido clase = objeto.esPrimitivo() || objeto.dimensiones() > 0 ? null
                : tablas.tipos().buscar(objeto.nombreClase());
        if (clase == null || clase.getCategoria() != TipoDefinido.Categoria.CLASE) {
            error(posicion, nombre, "Un valor de tipo '" + objeto + "' no tiene métodos");
            return null;
        }
        List<Simbolo> metodos = tablas.simbolos().buscarMetodos(clase.getNombre(), nombre);
        if (metodos.isEmpty()) {
            error(posicion, nombre, "La clase '" + clase.getNombre() + "' no tiene un método '" + nombre + "'");
            return null;
        }
        Simbolo metodo = TablaSimbolos.elegirSobrecarga(metodos, argumentos);
        if (metodo == null) {
            error(posicion, nombre, "Ningún método '" + nombre + "' de la clase '" + clase.getNombre()
                    + "' acepta los argumentos " + describir(argumentos));
            return null;
        }
        return conocido(metodo.tipo());
    }

    public Tipo crearObjeto(String nombre, List<Tipo> argumentos, Posicion posicion) {
        TipoDefinido clase = tablas.tipos().buscar(nombre);
        if (clase == null) {
            error(posicion, nombre, "La clase '" + nombre + "' no está definida");
            return null;
        }
        if (clase.getCategoria() != TipoDefinido.Categoria.CLASE) {
            error(posicion, nombre, "'" + nombre + "' no es una clase, no se puede crear con new/novus");
            return null;
        }
        List<Simbolo> constructores = tablas.simbolos().buscarConstructores(nombre);
        boolean valido = constructores.isEmpty() ? argumentos.isEmpty()
                : TablaSimbolos.elegirSobrecarga(constructores, argumentos) != null;
        if (!valido) {
            error(posicion, nombre, "Ningún constructor de la clase '" + nombre + "' acepta los argumentos "
                    + describir(argumentos));
        }
        return Tipo.clase(nombre);
    }

    // (entero, cadena); '?' si el tipo es desconocido
    private static String describir(List<Tipo> argumentos) {
        StringBuilder texto = new StringBuilder("(");
        for (int i = 0; i < argumentos.size(); i++) {
            texto.append(i > 0 ? ", " : "").append(argumentos.get(i) == null ? "?" : argumentos.get(i));
        }
        return texto.append(")").toString();
    }
}
