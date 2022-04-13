/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.abstracts.tree;

import java.awt.Dimension;
import javax.swing.JTable;

/**
 *
 * @author sommovir
 */
public class TreeTable extends JTable {

    private TreeTableCellRenderer tree;

    public TreeTable(AbstractTreeTableModel treeTableModel) {
        super();

        // JTree erstellen.
        tree = new TreeTableCellRenderer(this, treeTableModel);

        // Modell setzen.
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));
        tree.setRootVisible(false);

        // Gleichzeitiges Selektieren fuer Tree und Table.
        TreeTableSelectionModel selectionModel = new TreeTableSelectionModel();
        tree.setSelectionModel(selectionModel); //For the tree
        setSelectionModel(selectionModel.getListSelectionModel()); //For the table

        // Renderer fuer den Tree.
        setDefaultRenderer(TreeTableModel.class, tree);
        // Editor fuer die TreeTable
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor(tree, this));

        // Kein Grid anzeigen.
        setShowGrid(false);

        // Keine Abstaende.
        setIntercellSpacing(new Dimension(0, 0));
//        int N = InputManager.getInstance().getDimension();
//        System.out.println("N = " + N);
//        for (int i = 0; i < N; i++) {
//            this.getColumnModel().getColumn(i).setMinWidth(30);
//            this.getColumnModel().getColumn(i).setMaxWidth(36);
//        }
//        JTableHeader header = this.getTableHeader();
//        header.setDefaultRenderer(new IndividualHeaderRenderer(this));
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    }
    
    public boolean isRowExpanded(int row){
        return this.tree.isExpanded(row);
    }
}
