/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.mr.foreigntree;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;

/**
 *
 * @author sommovir
 */
public class MyTreeTableSelectionModel extends DefaultTreeSelectionModel {
 
    public MyTreeTableSelectionModel() {
        super();
 
        getListSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
 
            }
        });
    }
 
    ListSelectionModel getListSelectionModel() {
        return listSelectionModel;
    }
}
