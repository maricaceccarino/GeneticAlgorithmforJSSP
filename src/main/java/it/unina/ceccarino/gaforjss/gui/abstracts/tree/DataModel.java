/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.abstracts.tree;

import it.unina.ceccarino.gaforjss.model.InputManager;
import java.util.Date;

/**
 *
 * @author sommovir
 */
public class DataModel extends AbstractTreeTableModel {

    // Spalten Name.
//    static protected String[] columnNames = {"Knotentext", "String", "Datum", "Integer"};
//
//    // Spalten Typen.
//    static protected Class<?>[] columnTypes = {TreeTableModel.class, String.class, Date.class, Integer.class};
    public DataModel(DataNode rootNode) {
        super(rootNode);
        root = rootNode;
    }

    public Object getChild(Object parent, int index) {
        return ((DataNode) parent).getChildren().get(index);
    }

    public int getChildCount(Object parent) {
        return ((DataNode) parent).getChildren().size();
    }

    public int getColumnCount() {
        return InputManager.getInstance().getDimension()+1;
    }

    public String getColumnName(int column) {
        if (column == 0) {
            return "Fitness";
        }
        return "" + column;
    }

    public Class<?> getColumnClass(int column) {
        if (column != 0) {
            return String.class;
        }else{
            return TreeTableModel.class;
        }
    }

    public Object getValueAt(Object node, int column) {
        if (column == 0) {
            return ((DataNode) node).getLabel();
        }
        if (((DataNode) node).getData() != null) {
            return ((DataNode) node).getData()[column-1];
        }
        return "-";
    }

    public boolean isCellEditable(Object node, int column) {
        return column == 0 ? true : false; // Important to activate TreeExpandListener
    }

    public void setValueAt(Object aValue, Object node, int column) {
    }

}
