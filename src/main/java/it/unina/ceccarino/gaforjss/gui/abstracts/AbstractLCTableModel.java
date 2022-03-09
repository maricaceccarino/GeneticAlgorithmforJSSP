/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.abstracts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sommovir
 */
public abstract class AbstractLCTableModel<T> extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    protected List<T> datas;
    protected String[] labels;

    public AbstractLCTableModel(String[] labels, List<T> datas) {
        if (datas != null) {
            this.datas = datas;
        }
        this.labels = labels;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void addRowElement(T element) {
        datas.add(element);
        fireTableRowsInserted(datas.size() - 1, datas.size() - 1);
    }

    @Override
    public int getRowCount() {
        return datas.size();
    }

    @Override
    public int getColumnCount() {
        return labels.length;
    }

    @Override
    public String getColumnName(int column) {
        return labels[column];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public final void changeValueAt(T value, int row, int col) {
        deepChangeValueAt(value, row, col);
        fireTableCellUpdated(row, col);

    }

    public abstract void deepChangeValueAt(T value, int row, int col);

    @Override
    @Deprecated
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public int indexOf(T t) {
        return datas.indexOf(t);
    }

    public void reorder(int fromIndex, int toIndex) {
        T element = datas.get(fromIndex);
        datas.set(fromIndex, datas.get(toIndex));
        datas.set(toIndex, element);

        fireTableRowsUpdated(toIndex, toIndex);
        fireTableRowsUpdated(fromIndex, fromIndex);
    }

    public void removeRowElement(int i) {
        datas.remove(i);
        fireTableRowsDeleted(i, i);
    }


    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);
}
