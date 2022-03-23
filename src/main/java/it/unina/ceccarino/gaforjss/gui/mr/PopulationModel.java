/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.mr;

import it.unina.ceccarino.gaforjss.algo.Individual;
import it.unina.ceccarino.gaforjss.gui.abstracts.AbstractLCTableModel;
import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import java.beans.Beans;
import java.util.LinkedList;

/**
 *
 * @author sommovir
 */
public class PopulationModel extends AbstractLCTableModel<JobIndividual> {

    private static String[] labels;

    static {
        if (Beans.isDesignTime()) {
            labels = new String[]{"FIT"};
        } else {
            labels = new String[InputManager.getInstance().getDimension()+1];
            System.out.println("label size = " + labels.length);
            labels[0] = "FIT";
            for (int i = 1; i < labels.length; i++) {
                labels[i] = ("" + (i-1));
            }
        }

    }

    public PopulationModel() {
        super(labels, new LinkedList<JobIndividual>());
    }

    @Override
    public int getRowCount() {
        return this.datas.size();
    }

    @Override
    public void deepChangeValueAt(JobIndividual value, int row, int col) {

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (datas.isEmpty()) {
            return null;
        }
        if (columnIndex == 0) {
            return this.datas.get(rowIndex).getFitness();
        } else {
            return this.datas.get(rowIndex).getJobPermutation()[columnIndex-1];
        }

    }

}
