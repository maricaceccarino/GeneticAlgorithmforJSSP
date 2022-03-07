/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import static it.unina.ceccarino.gaforjss.model.InputManager.JOB_TOTAL_QUANTITY;
import static it.unina.ceccarino.gaforjss.model.InputManager.SEQUENCE_SIZE;

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
    private static int N = JOB_TOTAL_QUANTITY * SEQUENCE_SIZE;
    int[] complationArray = new int[N];
    

    public JobIndividual(int[] jobPermutation, int[] operationSequence, Machine[] machinesSelected) {
        this.jobPermutation = jobPermutation;
        this.operationSequence = operationSequence;
        this.machinesSelected = machinesSelected;
        initCompletionArray();

    }

    private final void initCompletionArray() {
        for (int i = 0; i < N; i++) {
            int job = this.jobPermutation[i];
            int step = getStep(this.operationSequence[i]);
            System.out.println("JOB = " + job);
            System.out.println("STEP = " + step);
            int time = InputManager.getInstance().getJobTypes()[job - 1].getSequence()[step].getTime();

            complationArray[i] = time;

        }
    }

    /**
     * Ritorna lo step dell'operazione all'indice operationIndex
     *
     * @param operationIndex
     * @return
     */
   private int getStep(int operationIndex) {

        int rawStep = this.operationSequence[operationIndex];
        if (rawStep <= 6) {
            return rawStep-1;
        } else { 
            return rawStep % 6;
        }
    }

    public int getJobStep(int operationIndex) {
        return this.jobPermutation[operationIndex];
    }

    public int[] getJobPermutation() {
        return jobPermutation;
    }

    public int[] getOperationSequence() {
        return operationSequence;
    }

    public Machine[] getMachinesSelected() {
        return machinesSelected;
    }

    public int[] getComplationArray() {
        return complationArray;
    }
    
    

}
