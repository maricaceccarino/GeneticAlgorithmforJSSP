/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.mr;

import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 */
public class IndividualRenderer extends DefaultTableCellRenderer {

//    private Icon star = new ImageIcon(getClass().getResource("/it/cnr/istc/sponsor/tt/gui/icons/star16.png"));
//    private Icon notestar = new ImageIcon(getClass().getResource("/it/cnr/istc/sponsor/tt/gui/icons/notestar.png"));
//    private Icon note = new ImageIcon(getClass().getResource("/it/cnr/istc/sponsor/tt/gui/icons/note16.png"));
    private boolean pm = false;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableModel model = table.getModel();
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        int rowLimit = GeneticManipulator.getInstance().getImmuneSize();
        System.out.println("rowLimit: " + rowLimit);
        if (isSelected) {
            c.setForeground(Color.WHITE);
            c.setBackground(new Color(53, 85, 235));
        } else {

            if (row <= rowLimit) {
                c.setForeground(Color.BLACK);
                if (row % 2 == 0) {
                    c.setBackground(new Color(230, 205, 149));
                } else {
                    c.setBackground(new Color(230, 221, 165));
                }
                if(column == 0){
                    c.setFont(new Font("TimesRoman", Font.BOLD, 12));
                    c.setForeground(Color.RED);
                }
            } else {
                c.setForeground(Color.WHITE);
                if (row % 2 == 0) {
                    c.setBackground(new Color(55, 55, 55));
                } else {
                    c.setBackground(new Color(44, 44, 44));
                }
            }

        }

//        if (value != null) {
//            ParsedAccount v = ((PeopleTableModel) model).getAccountAtRow(row);
//            SinglePersonVariableLabel label = new SinglePersonVariableLabel("" + value);
//            label.setOpaque(true);
//            label.setHorizontalAlignment(SwingConstants.CENTER);
//            boolean admin = false;
//            if (column == 0) {
//                if (value instanceof Boolean) {
//                    if ((Boolean) value) {
//                        
//                        if(v.getAccount().getNote() == null || (v.getAccount().getNote() != null && v.getAccount().getNote().isEmpty())){
//                            label.setIcon(star);
//                        }else{
//                            label.setIcon(notestar);
//                        }
//                        admin = true;
//                    }else{
//                        
//                        if(v.getAccount().getNote() != null && !v.getAccount().getNote().isEmpty()){
//                            label.setIcon(note);
//                        }
//                    }
//                }
//                label.setValue("");
//                label.setHorizontalAlignment(SwingConstants.CENTER);
//            }
//            if (column == 1) {
//                label.setHorizontalAlignment(SwingConstants.LEFT);
//            }
//            boolean red = false;
//            if (column == 10) {
//                if (value instanceof Boolean) {
////                    JComponent check = (JComponent) table.getDefaultRenderer(Boolean.class);
//                    JComponent check = new JCheckBox();
//
//                    if (check instanceof JCheckBox) {
//                        ((JCheckBox) check).setText("");
//                        ((JCheckBox) check).setHorizontalAlignment(SwingConstants.CENTER);
//                        ((JCheckBox) check).setSelected((Boolean) value);
//                    }
//                    if (v instanceof ParsedAccount) {
//                        if (((ParsedAccount) v).getAccount().getIntervals() == null || ((ParsedAccount) v).getAccount().getIntervals().isEmpty()) {
////                            System.out.println("RED");
//                            check.setOpaque(true);
//                            check.setBackground(Color.RED);
//                        } else {
//                            if (row % 2 == 0) {
//                                check.setOpaque(false);
//                            } else {
////                                System.out.println("ROW = "+row);
////                                System.out.println("GNE RED amnd OPAQUE TRU");
//                                check.setOpaque(true);
//                            }
//                        }
//
//                    }
//
//                    if(isSelected){
//                        check.setBackground(table.getSelectionBackground());
//                        check.setOpaque(true);
//                    }
//                    return check;
//
//                }
//            }
//
//            if (row % 2 == 0 && column != 10) {
//                label.setOpaque(false);
//            }
//            if (PeopleStatManager.getInstance().isCircle(row, column)) {
//                label.setCircled(true);
//            }
//            if (PeopleStatManager.getInstance().isSquare(row, column)) {
//                label.setSquared(true);
//            }
//            if (model instanceof PeopleTableModel && column != 10) {
//
//                if (v instanceof ParsedAccount) {
//                    if (((ParsedAccount) v).getAccount().getIntervals() == null || ((ParsedAccount) v).getAccount().getIntervals().isEmpty()) {
////                        System.out.println("RED");
//                        label.setOpaque(true);
//                        label.setBackground(Color.RED);
//                        label.setForeground(Color.WHITE);
////                        ((JComponent) jTable1.getDefaultRenderer(Boolean.class)).setOpaque(true);
////                        TableCellRenderer cellRenderer = table.getCellRenderer(row, 10);
////                        cellRenderer.
//                    }
//                }
//            }
//
//            if (isSelected && column != 10) {
//                label.setOpaque(true);
//                label.setBackground(table.getSelectionBackground());
//                label.setForeground(Color.WHITE);
////                label.setText(admin ? "<html><b><font color=white>" + label.getText() : label.getText());
//            }
////            if (column == 1) {
//////                System.out.println("HOR LEF = " + SwingConstants.LEFT);
////                System.out.println("HH>> " + label.getHorizontalAlignment());
////            }
//            return label;
//        }
//        return c;
        return c;
    }
}
