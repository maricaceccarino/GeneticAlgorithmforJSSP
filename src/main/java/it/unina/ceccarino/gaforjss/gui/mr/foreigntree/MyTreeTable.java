/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.mr.foreigntree;

import it.unina.ceccarino.gaforjss.gui.mr.foreigntree.MyTreeTableSelectionModel;
import it.unina.ceccarino.gaforjss.gui.mr.foreigntree.MyTreeTableModelAdapter;
import it.unina.ceccarino.gaforjss.gui.mr.foreigntree.MyTreeTableCellEditor;
import it.unina.ceccarino.gaforjss.gui.mr.foreigntree.MyAbstractTreeTableModel;
import it.unina.ceccarino.gaforjss.gui.mr.foreigntree.MyTreeTableCellRenderer;
import java.awt.Dimension;
import javax.swing.JTable;

/**
 *
 * @author sommovir
 */
public class MyTreeTable extends JTable {
 
    private MyTreeTableCellRenderer tree;
 
 
    public MyTreeTable(MyAbstractTreeTableModel treeTableModel) {
        super();
 
        // JTree erstellen.
        tree = new MyTreeTableCellRenderer(this, treeTableModel);
 
        // Modell setzen.
        super.setModel(new MyTreeTableModelAdapter(treeTableModel, tree));
 
        // Gleichzeitiges Selektieren fuer Tree und Table.
        MyTreeTableSelectionModel selectionModel = new MyTreeTableSelectionModel();
        tree.setSelectionModel(selectionModel); //For the tree
        setSelectionModel(selectionModel.getListSelectionModel()); //For the table
 
 
        // Renderer fuer den Tree.
        setDefaultRenderer(MyTreeTableModel.class, tree);
        // Editor fuer die TreeTable
        setDefaultEditor(MyTreeTableModel.class, new MyTreeTableCellEditor(tree, this));
 
        // Kein Grid anzeigen.
        setShowGrid(false);
 
        // Keine Abstaende.
        setIntercellSpacing(new Dimension(0, 0));
 
    }
}