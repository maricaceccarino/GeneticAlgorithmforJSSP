/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.abstracts.tree;

import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

/**
 *
 * @author sommovir
 */
public class TreeTableCellRenderer extends JTree implements TableCellRenderer {

    public static final String HTML_DEOCORATION_1 = "<HTML_DECORATION1>";
    public static final String HTML_DEOCORATION_2 = "<HTML_DECORATION2>";
    
    private String label;

    /**
     * Die letzte Zeile, die gerende rt wurde.
     */
    protected int visibleRow;

    private TreeTable treeTable;

    public TreeTableCellRenderer(TreeTable treeTable, TreeModel model) {
        super(model);
        this.treeTable = treeTable;

        this.cellRenderer = new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                System.out.println("SISISISISISISI- >value: " + value);
                String s_value = value.toString();
                if (selected) {
                    s_value = s_value.replace(TreeTableCellRenderer.HTML_DEOCORATION_1, "<html><font color = yellow><b>");
                } else {
                    s_value = s_value.replace(TreeTableCellRenderer.HTML_DEOCORATION_1, "<html><font color = red><b>");
                }

                s_value = s_value.replace(TreeTableCellRenderer.HTML_DEOCORATION_2, "<html><b>");
                Component c = super.getTreeCellRendererComponent(tree, s_value, selected, expanded, leaf, row, hasFocus);
                return c;
            }
        };

        // Setzen der Zeilenhoehe fuer die JTable
        // Muss explizit aufgerufen werden, weil treeTable noch
        // null ist, wenn super(model) setRowHeight aufruft!
        setRowHeight(getRowHeight());
    }

    /**
     * Tree und Table muessen die gleiche Hoehe haben.
     */
    public void setRowHeight(int rowHeight) {
        if (rowHeight > 0) {
            super.setRowHeight(rowHeight);
            if (treeTable != null && treeTable.getRowHeight() != rowHeight) {
                treeTable.setRowHeight(getRowHeight());
            }
        }
    }

    /**
     * Tree muss die gleiche Hoehe haben wie Table.
     */
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, 0, w, treeTable.getHeight());
    }

    /**
     * Sorgt fuer die Einrueckung der Ordner.
     */
    public void paint(Graphics g) {
        g.translate(0, -visibleRow * getRowHeight());

        super.paint(g);
    }

    /**
     * Liefert den Renderer mit der passenden Hintergrundfarbe zurueck.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        System.out.println("MINCHIA: "+row);
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }
        visibleRow = row;
        return this;
    }  
}
