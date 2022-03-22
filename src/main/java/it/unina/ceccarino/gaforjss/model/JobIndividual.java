
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import static it.unina.ceccarino.gaforjss.model.InputManager.JOB_TOTAL_QUANTITY;
import static it.unina.ceccarino.gaforjss.model.InputManager.JOB_TYPE_SIZE;
import static it.unina.ceccarino.gaforjss.model.InputManager.SEQUENCE_SIZE;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JobIndividual implements Comparable<JobIndividual> {

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
    private int[] completionArray = new int[N];

    public JobIndividual(int[] jobPermutation, int[] operationSequence, Machine[] machinesSelected) {
        this.jobPermutation = jobPermutation;
        this.operationSequence = operationSequence;
        this.machinesSelected = machinesSelected;
        initCompletionArray();

    }
    
    public int getFitness(){
        if(this.completionArray != null){
            int fitness = 0;
            for (int time : completionArray) {
                if(time > fitness){
                    fitness = time;
                }
            }
            return fitness;
        }else{
            return -1;
        }
    }

    private final void initCompletionArray() {

        // array 4 ,Completationtime 
        Map<Machine, Integer> machineEndTimeMap = new HashMap<>();
        //per ogni job(chiave) si memorizza il tempo di completamento della fase precedente
        Map<Integer, Integer> previousJobStepEndTimeMap = new HashMap<>();

        //inizializza per ogni macchina un tempo iniziale 0
        for (Machine machine : Machine.values()) {
            machineEndTimeMap.put(machine, 0);
        }

        //inizilizzo per ogni jobtype un valore 0 come tempo di attesa della fase precedente
        for (int i = 1; i <= JOB_TYPE_SIZE; i++) {
            previousJobStepEndTimeMap.put(i, 0);
        }

        for (int i = 0; i < N; i++) {
//            if(i == 10){
//                break;
//            }
            System.out.println("-------------------------------------------------");
            int job = this.jobPermutation[i];
            int previousOperationEndTime = previousJobStepEndTimeMap.get(job);
            int step = InputManager.getInstance().getStep(i,this.operationSequence);
            System.out.println("STEP = " + step);
            Machine currentMachine = this.machinesSelected[i];
            int freeMachineTime = machineEndTimeMap.get(currentMachine);
            int time = InputManager.getInstance().getJobTypes()[job - 1].getSequence()[step].getTime();

            System.out.println("JOB = " + job);
            System.out.println("OP SEQUENCE: " + operationSequence);
            System.out.println("STEP = " + step);

            //  a [i]  = max(tempo_completamento_operazione_precedente, tempo_in_cui_si_libera_la_macchina) + tempo_processamento_operazione_corrente
            
            int value = Utils.max(previousOperationEndTime, freeMachineTime) +time;
            completionArray[i] = value;
            
            previousJobStepEndTimeMap.put(job, value);
            machineEndTimeMap.put(currentMachine, value);
            
            

            //aggiorna il tempo in cui si libera la macchina i, sommando il tempo di completamento del dato
            //job al dato step, + il tempo che già sulla mappa. 
            //M1  M2  M3
            // 4  1   5
            //time = 3, alla M2
            //alla posizione M2 -> valore (M2) + time = 1 + 3
//            System.out.println("MACHINE: " + machineSelected.getCode());

            
//            machineEndTimeMap.put(this.machinesSelected[i], machineEndTimeMap.get(this.machinesSelected[i]) + time);

//            for (int j = i + 1; j < N; j++) {
//                if (this.jobPermutation[i] == job) {
//                    completionArray[j] = completionArray[i];
//                }
//            }

        }
    }

    /**
     * Ritorna lo step dell'operazione (quale operazione di un determinato job
     * si deve esaguire) all'indice operationIndex Viene contato quante volte
     * quel valore dell'array JobPermutation appare
     *
     * @param operationIndex
     * @return
     */
//    public int getStep(int rawStep) {
////operazione che aiuta a capire che step viene realizzato, quando i numeri sono
////da 1 a 6 ovviamente è semplice capire la corrispondenza all'operazione,negli
////altri casi si fa una divisione per 6 e l'operatore % restituisce il 
////resto della divisione,come numero intero,questo sarà lo step assegnato.
//        if (rawStep <= 6) {
//            return rawStep - 1;
//        } else {
//            return rawStep % 6;
//        }
//    }

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
        return completionArray;
    }

    @Override
    public int compareTo(JobIndividual o) {
        return Integer.compare(this.getFitness(), o.getFitness());
    }

}
