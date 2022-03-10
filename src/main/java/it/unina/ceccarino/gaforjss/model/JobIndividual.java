
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import static it.unina.ceccarino.gaforjss.model.InputManager.JOB_TOTAL_QUANTITY;
import static it.unina.ceccarino.gaforjss.model.InputManager.SEQUENCE_SIZE;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JobIndividual {

    //primo array Job permutation ,viene costruita una stringa di lunghezza
    // pari al numero di job generati x 6 (numero di operazioni per ogni job)
    // tale stringa è riempita con valori (da 1 a 10) che rappresentano la 
    // tipologia di job.
    private int[] jobPermutation;
    //secondo array OperationSequence,viene costruita una stringa della stessa 
    //lunghezza,
    private int[] operationSequence;
    //terzo array di MachineSelected,rappresenta la macchina sulla quale 
    //l'operazione deve essere eseguita.Quando l'operazione è vincolata ad
    //essere realizzata solo su una determinata macchina,il valore con cui si 
    // riempie la cella è deciso.
    private Machine[] machinesSelected;
    private static int N = JOB_TOTAL_QUANTITY * SEQUENCE_SIZE;
    int[] complationArray = new int[N];

    public JobIndividual(int[] jobPermutation, int[] operationSequence, Machine[] machinesSelected) {
        this.jobPermutation = jobPermutation;
        this.operationSequence = operationSequence;
        this.machinesSelected = machinesSelected;
        initComplationArray();

    }

    private final void initComplationArray() {

        // array 4 ,Completationtime 
        Map<Machine, Integer> supportMap = new HashMap<>();

        for (Machine machine : Machine.values()) {
            supportMap.put(machine, 0);
        }

        for (int i = 0; i < N; i++) {
            if(i == 10){
                break;
            }
            System.out.println("-------------------------------------------------");
            int job = this.jobPermutation[i];
            int operationSequence = this.operationSequence[i];
            int step = getStep(this.operationSequence[i]);
            System.out.println("JOB = " + job);
            System.out.println("OP SEQUENCE: " + operationSequence);
            System.out.println("STEP = " + step);
            int time = InputManager.getInstance().getJobTypes()[job - 1].getSequence()[step].getTime();

            //aggiorna il tempo in cui si libera la macchina i, sommando il tempo di completamento del dato
            //job al dato step, + il tempo che già sulla mappa. 
            //M1  M2  M3
            // 4  1   5
            //time = 3, alla M2
            //alla posizione M2 -> valore (M2) + time = 1 + 3
            Machine machineSelected = this.machinesSelected[i];
            System.out.println("MACHINE: "+machineSelected.getCode());
            
            complationArray[i] += time + supportMap.get(this.machinesSelected[i]);
            supportMap.put(this.machinesSelected[i], supportMap.get(this.machinesSelected[i]) + time);

            for (int j = i + 1; j < N; j++) {
                if (this.jobPermutation[i] == job) {
                    complationArray[j] = complationArray[i];
                }
            }

        }
    }

    /**
     * Ritorna lo step dell'operazione (quale operazione di un determinato job
     * si deve esaguire) all'indice operationIndex
     * Viene contato quante volte quel valore dell'array JobPermutation appare
     * @param operationIndex
     * @return
     */
    public int getStep(int rawStep) {
//operazione che aiuta a capire che step viene realizzato, quando i numeri sono
//da 1 a 6 ovviamente è semplice capire la corrispondenza all'operazione,negli
//altri casi si fa una divisione per 6 e l'operatore % restituisce il 
//resto della divisione,come numero intero,questo sarà lo step assegnato.
        if (rawStep <= 6) {
            return rawStep - 1;
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
