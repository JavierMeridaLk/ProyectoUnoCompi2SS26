package com.mycompany.proyectounocompi2.views.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoManager;

import com.mycompany.proyectounocompi2.analizador.Lenguaje;
import com.mycompany.proyectounocompi2.views.coloreado.Coloreador;

// Una pestaña del editor
public class PestanaEditor extends JPanel {

    private static final int ESPACIOS_POR_TAB = 4;

    private final JTextPane texto;
    private final Coloreador coloreador;
    private final UndoManager deshacer = new UndoManager();
    private CompoundEdit agrupado; 
    private final List<Runnable> alCambiarEstado = new ArrayList<>();
    private Path archivo;
    private boolean modificado;

    public PestanaEditor(Path archivo, String contenido) {
        super(new BorderLayout());
        this.archivo = archivo;

        texto = new JTextPane() {
            
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return getParent() == null || getUI().getPreferredSize(this).width <= getParent().getWidth();
            }
        };
        texto.setFont(Estilos.FUENTE_CODIGO);
        texto.setBackground(Color.WHITE);
        texto.setForeground(new Color(30, 30, 30));
        texto.setCaretColor(Color.BLACK);
        texto.setMargin(new java.awt.Insets(2, 6, 2, 6));
        configurarTabs();
        texto.setText(contenido);
        texto.setCaretPosition(0);

        JScrollPane desplazamiento = new JScrollPane(texto);
        desplazamiento.setRowHeaderView(new NumerosDeLinea(texto));
        desplazamiento.setBorder(BorderFactory.createEmptyBorder());
        desplazamiento.getVerticalScrollBar().setUnitIncrement(16);
        desplazamiento.getHorizontalScrollBar().setUnitIncrement(16);
        add(desplazamiento, BorderLayout.CENTER);

        configurarDeshacer();
        coloreador = new Coloreador(texto);
        coloreador.setLenguaje(getLenguaje().orElse(null));
        texto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                marcarModificado();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                marcarModificado();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    // Tabulador de 4 espacios 
    private void configurarTabs() {
        int ancho = texto.getFontMetrics(texto.getFont()).charWidth(' ') * ESPACIOS_POR_TAB;
        TabStop[] paradas = new TabStop[200];
        for (int i = 0; i < paradas.length; i++) {
            paradas[i] = new TabStop((i + 1) * ancho);
        }
        Style estilo = texto.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setTabSet(estilo, new TabSet(paradas));
    }


    private void configurarDeshacer() {
        texto.getDocument().addUndoableEditListener(e -> {
            if (e.getEdit() instanceof AbstractDocument.DefaultDocumentEvent evento
                    && evento.getType() == DocumentEvent.EventType.CHANGE) {
                return;
            }
            if (agrupado != null) {
                agrupado.addEdit(e.getEdit());
            } else {
                deshacer.addEdit(e.getEdit());
            }
        });
        int ctrl = InputEvent.CTRL_DOWN_MASK;
        texto.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ctrl), "deshacer");
        texto.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ctrl), "rehacer");
        texto.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ctrl | InputEvent.SHIFT_DOWN_MASK), "rehacer");
        texto.getActionMap().put("deshacer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deshacer.canUndo()) {
                    deshacer.undo();
                }
            }
        });
        texto.getActionMap().put("rehacer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deshacer.canRedo()) {
                    deshacer.redo();
                }
            }
        });
    }

    private void marcarModificado() {
        if (!modificado) {
            modificado = true;
            notificar();
        }
    }

    public void marcarGuardado() {
        modificado = false;
        notificar();
    }

    private void notificar() {
        alCambiarEstado.forEach(Runnable::run);
    }

    public void alCambiarEstado(Runnable accion) {
        alCambiarEstado.add(accion);
    }

    // ---------- Consultas ----------

    public JTextPane getTexto() {
        return texto;
    }

    public String getContenido() {
        return texto.getText();
    }

    public Path getArchivo() {
        return archivo;
    }

    public void setArchivo(Path archivo) {
        this.archivo = archivo;
        coloreador.setLenguaje(getLenguaje().orElse(null)); 
        notificar();
    }

    public boolean isModificado() {
        return modificado;
    }

    public String getNombre() {
        return archivo != null ? archivo.getFileName().toString() : "Sin título";
    }

    public Optional<Lenguaje> getLenguaje() {
        return archivo != null ? Lenguaje.desdeArchivo(getNombre()) : Optional.empty();
    }

    public void reemplazarContenido(String nuevo) {
        if (nuevo.equals(getContenido())) {
            return;
        }
        int linea = getLineaColumna()[0];
        agrupado = new CompoundEdit();
        try {
            texto.getDocument().remove(0, texto.getDocument().getLength());
            texto.getDocument().insertString(0, nuevo, null);
        } catch (BadLocationException e) {
            throw new IllegalStateException(e);
        } finally {
            agrupado.end();
            deshacer.addEdit(agrupado);
            agrupado = null;
        }
        irA(linea, 1);
    }

    // Linea y columna del cursor 
    public int[] getLineaColumna() {
        int posicion = texto.getCaretPosition();
        Element raiz = texto.getDocument().getDefaultRootElement();
        int linea = raiz.getElementIndex(posicion);
        int columna = posicion - raiz.getElement(linea).getStartOffset();
        return new int[] {linea + 1, columna + 1};
    }

    // Mueve el cursor a una linea y columna 
    public void irA(int linea, int columna) {
        Element raiz = texto.getDocument().getDefaultRootElement();
        Element elemento = raiz.getElement(Math.max(0, Math.min(linea - 1, raiz.getElementCount() - 1)));
        int fin = Math.max(elemento.getStartOffset(), elemento.getEndOffset() - 1);
        int posicion = Math.min(elemento.getStartOffset() + Math.max(columna - 1, 0), fin);
        texto.requestFocusInWindow();
        texto.setCaretPosition(posicion);
    }
}
