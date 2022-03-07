/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

/**
 *
 * @author marica
 */
public class JobIndividual {

    //primo array
    private int[] jobPermutation;
    //secondo array
    private int[] operationSequence;
    //terzo array
    private Machine[] machinesSelected;

    public JobIndividual(int [] jobPermutation, int[] operationSequence, Machine[] machinesSelected) {
        this.jobPermutation = jobPermutation;
        this.operationSequence = operationSequence;
        this.machinesSelected = machinesSelected;
    }
    
    /**
     * Ritorna lo step dell'operazione all'indice operationIndex
     * @param operationIndex
     * @return 
     */
    public int getStep(int operationIndex){
        
        int rawStep = operationSequence[operationIndex];
        if(rawStep <=6){
            return rawStep-1;
        }
        else{
            return rawStep%6;
        }
    }
    
    public int getJobStep(int operationIndex){
        return this.jobPermutation[operationIndex];
    }

    public int [] getJobPermutation() {
        return jobPermutation;
    }

    public int[] getOperationSequence() {
        return operationSequence;
    }

    public Machine[] getMachinesSelected() {
        return machinesSelected;
    }
    

}
