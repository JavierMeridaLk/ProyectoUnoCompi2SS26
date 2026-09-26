/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyectounocompi2.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.mycompany.proyectounocompi2.errores.ErrorCompilacion;
import com.mycompany.proyectounocompi2.errores.TipoError;
import com.mycompany.proyectounocompi2.views.componentes.Estilos;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Tabla de errores lexicos, sintacticos y semanticos.
 *
 * @author xavi
 */
public class ErroresPanel extends javax.swing.JPanel {

    private static final String[] COLUMNAS = {"Tipo", "Archivo", "Línea", "Columna", "Lexema", "Descripción"};
    private static final int[] ANCHOS = {90, 120, 55, 65, 120, 600};

    private final DefaultTableModel modelo = new DefaultTableModel(COLUMNAS, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columna) {
            return columna == 2 || columna == 3 ? Integer.class : String.class;
        }
    };
    private final JTable tabla = new JTable(modelo);
    private final JLabel resumen = new JLabel(" ");


    public ErroresPanel() {
        initComponents();
        construir();
    }

    private void construir() {
        removeAll();
        setLayout(new BorderLayout());
        add(Estilos.encabezado("Errores léxicos, sintácticos y semánticos", Estilos.ENCABEZADO_ERRORES, resumen),
                BorderLayout.NORTH);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoCreateRowSorter(true);
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(22);
        tabla.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i < ANCHOS.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(ANCHOS[i]);
        }
        tabla.getColumnModel().getColumn(0).setCellRenderer(new RenderizadorTipo());
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        limpiar();
    }

    public void setErrores(List<ErrorCompilacion> nuevos) {
        modelo.setRowCount(0);
        for (ErrorCompilacion error : nuevos) {
            modelo.addRow(new Object[] {error.tipo().getNombre(), error.archivo(), error.linea(), error.columna(),
                error.lexema() == null ? "" : error.lexema(), error.descripcion()});
        }
        if (nuevos.isEmpty()) {
            resumen.setText("✔ Sin errores");
            resumen.setForeground(new Color(30, 130, 60));
        } else {
            resumen.setText(nuevos.size() + (nuevos.size() == 1 ? " error" : " errores"));
            resumen.setForeground(new Color(180, 30, 30));
        }
    }

    //Vacia la tabla 
    public void limpiar() {
        modelo.setRowCount(0);
        resumen.setText(" ");
    }

    //Colorea la columna Tipo segun sea lexico, sintactico o semantico.
    private static class RenderizadorTipo extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccionado,
                boolean foco, int fila, int columna) {
            super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
            setFont(getFont().deriveFont(Font.BOLD));
            if (!seleccionado) {
                String tipo = String.valueOf(valor);
                if (tipo.equals(TipoError.LEXICO.getNombre())) {
                    setForeground(new Color(200, 100, 0));
                } else if (tipo.equals(TipoError.SINTACTICO.getNombre())) {
                    setForeground(new Color(190, 30, 30));
                } else {
                    setForeground(new Color(120, 40, 160)); // semantico
                }
            }
            return this;
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
