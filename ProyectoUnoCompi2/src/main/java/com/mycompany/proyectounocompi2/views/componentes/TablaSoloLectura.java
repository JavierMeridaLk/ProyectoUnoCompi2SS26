package com.mycompany.proyectounocompi2.views.componentes;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaSoloLectura extends JTable {

    public TablaSoloLectura(String[] columnas, List<Object[]> filas) {
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        filas.forEach(modelo::addRow);
        setModel(modelo);
        setAutoCreateRowSorter(true);
        setRowHeight(22);
        getTableHeader().setFont(Estilos.FUENTE_TITULO);
    }
}
