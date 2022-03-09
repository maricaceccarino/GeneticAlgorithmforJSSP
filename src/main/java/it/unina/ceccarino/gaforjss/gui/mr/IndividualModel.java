/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.mr;

import it.unina.ceccarino.gaforjss.algo.Individual;
import it.unina.ceccarino.gaforjss.gui.abstracts.AbstractLCTableModel;
import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import java.util.LinkedList;

/**
 *
 * @author sommovir
 */
public class IndividualModel extends AbstractLCTableModel<JobIndividual> {
    
    private static String [] labels;
    
    static{
        labels = new String[InputManager.getInstance().getDimension()];
        System.out.println("label size = "+labels.length);
        for (int i = 0; i < labels.length; i++) {
            labels[i]=(""+i);
        }
        
    }

    public IndividualModel() {
        super(labels, new LinkedList<JobIndividual>());
    }

    @Override
    public int getRowCount() {
        return 4;
    }
    
    
    
    

    @Override
    public void deepChangeValueAt(JobIndividual value, int row, int col) {
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(rowIndex){
            case 0: {
                return this.datas.get(0).getJobPermutation()[columnIndex];
            }
            case 1: {
                return this.datas.get(0).getOperationSequence()[columnIndex];
            }
            case 2: {
                return this.datas.get(0).getMachinesSelected()[columnIndex];
            }
            case 3: {
                return this.datas.get(0).getComplationArray()[columnIndex];
            }
            default:{
                return null;
            }
            
        }
    }
    
}
