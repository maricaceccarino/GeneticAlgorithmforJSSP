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
    private JobType[] jobPermutation;
    //secondo array
    private int[] operationSequence;
    //terzo array
    private int[] machinesSelected;

    public JobIndividual(JobType [] jobPermutation, int[] operationSequence, int[] machinesSelected) {
        this.jobPermutation = jobPermutation;
        this.operationSequence = operationSequence;
        this.machinesSelected = machinesSelected;
    }

    public JobType [] getJobPermutation() {
        return jobPermutation;
    }

    public int[] getOperationSequence() {
        return operationSequence;
    }

    public int[] getMachinesSelected() {
        return machinesSelected;
    }
    

}
