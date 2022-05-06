
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import it.unina.ceccarino.gaforjss.algo.SelectionStrategy;
import static it.unina.ceccarino.gaforjss.model.InputManager.JOB_TOTAL_QUANTITY;
import static it.unina.ceccarino.gaforjss.model.InputManager.JOB_TYPE_SIZE;
import static it.unina.ceccarino.gaforjss.model.InputManager.SEQUENCE_SIZE;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class JobIndividual implements Comparable<JobIndividual>, Cloneable {

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

    private boolean mutated = false;
    private boolean kid = false;
    private boolean parent = false;
    private boolean immune = false;
    private boolean experimental = false;

    public JobIndividual(int[] jobPermutation, int[] operationSequence, Machine[] machinesSelected) {
        this.jobPermutation = jobPermutation;
        this.operationSequence = operationSequence;
        this.machinesSelected = machinesSelected;
        initCompletionArray();

    }
    
    private  JobIndividual(int[] jobPermutation, int[] operationSequence, Machine[] machinesSelected, int[] completionArray) {
        this.jobPermutation = jobPermutation;
        this.operationSequence = operationSequence;
        this.machinesSelected = machinesSelected;
        this.completionArray = completionArray;

    }
    
    public void resetFlags(){
        this.mutated = false;
        this.kid = false;
        this.parent = false;
        this.immune = false;
        this.experimental = false;
    }

    public void setExperimental(boolean experimental) {
        this.experimental = experimental;
    }

    public boolean isExperimental() {
        return experimental;
    }

    
    public void setMutated(boolean mutated) {
        this.mutated = mutated;
    }

    public void setKid(boolean kid) {
        this.kid = kid;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    //constructor for newborn baby
    public JobIndividual() {
        this.jobPermutation = new int[N];
    }

    //aggiorna tutti gli altri array partendo dal jobPermutation
    public void update() {
        this.operationSequence = new int[N];
        this.machinesSelected = new Machine[N];
        Map<Integer, Integer> positionMap = new HashMap<>();
        for (Integer jobType : InputManager.getInstance().getJobQuantityMap().keySet()) {
            positionMap.put(jobType, 1);
        }
        for (int i = 0; i < jobPermutation.length; i++) {
            int step = positionMap.get(jobPermutation[i]);
            operationSequence[i] = step;
            positionMap.put(jobPermutation[i], ++step);
        }

        for (int i = 0; i < operationSequence.length; i++) {
            int step = InputManager.getInstance().getStep(i, operationSequence);
            int jobType = jobPermutation[i];
            JobType jobt = InputManager.getInstance().getJobTypes()[jobType - 1];
            MachineStep machineSTEP = jobt.getSequence()[step];

            machinesSelected[i] = machineSTEP.getRandomMachine();
        }
        initCompletionArray();
    }

    public boolean isKid() {
        return kid;
    }

    public boolean isParent() {
        return parent;
    }

    public boolean isImmune() {
        return immune;
    }

    public int getFitness() {
        if (this.completionArray != null) {
            int fitness = 0;
            for (int time : completionArray) {
                if (time > fitness) {
                    fitness = time;
                }
            }
            return fitness;
        } else {
            return -1;
        }
    }

    public void swap(int swapTime) {
        for (int i = 0; i < swapTime; i++) {
            int r1 = Utils.randomInRange(0, this.jobPermutation.length);
            int r2 = Utils.randomInRange(0, this.jobPermutation.length);
            int t = this.jobPermutation[r1];
            this.jobPermutation[r1] = this.jobPermutation[r2];
            this.jobPermutation[r2] = this.jobPermutation[t];
        }
        this.mutated = true;
        //QUESTION: devo richiamare la #initCompletionArray ? 
    }

    public boolean isMutated() {
        return mutated;
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
//            System.out.println("-------------------------------------------------");
            int job = this.jobPermutation[i];
            int previousOperationEndTime = previousJobStepEndTimeMap.get(job);
            int step = InputManager.getInstance().getStep(i, this.operationSequence);
//            System.out.println("STEP = " + step);
            Machine currentMachine = this.machinesSelected[i];
            int freeMachineTime = machineEndTimeMap.get(currentMachine);
            int time = InputManager.getInstance().getJobTypes()[job - 1].getSequence()[step].getTime();

//            System.out.println("JOB = " + job);
//            System.out.println("OP SEQUENCE: " + operationSequence);
//            System.out.println("STEP = " + step);

            //  a [i]  = max(tempo_completamento_operazione_precedente, tempo_in_cui_si_libera_la_macchina) + tempo_processamento_operazione_corrente
            int value = Utils.max(previousOperationEndTime, freeMachineTime) + time;
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
        if (GeneticManipulator.getInstance().getSelectionStrategy() == SelectionStrategy.BEST) {
            return Integer.compare(this.getFitness(), o.getFitness());
        } else {
            return Integer.compare(o.getFitness(), this.getFitness());
        }
    }

    public void flush() {
        System.out.println("FLUSH MUST BE IMPLEMENTED");
    }

    @Override
    public String toString() {
        String print = "";
        print+="[JOB PERMUTATION ARRAY] size: "+this.jobPermutation.length+"\n";
        for (int i : jobPermutation) {
            print+=""+i+", ";
        }
        print+="\n[JOB OPERATION ARRAY] size: "+this.operationSequence.length+"\n";
        for (int i : operationSequence) {
            print+=""+i+", ";
        }
        print+="\n[JOB MACHINE ARRAY] size: "+this.machinesSelected.length+"\n";
        for (Machine i : machinesSelected) {
            print+=""+i.getCode()+", ";
        }
        print+="\n[JOB COMPLETION ARRAY] size: "+this.completionArray.length+"\n";
        for (int i : completionArray) {
            print+=""+i+", ";
        }
        
        return print;
    }

    @Override
    public Object clone() {
        int [] job2 = new int[jobPermutation.length];
        System.arraycopy(jobPermutation, 0, job2, 0, jobPermutation.length);
        int [] operation2 = new int[operationSequence.length];
        System.arraycopy(operationSequence, 0, operation2, 0, operationSequence.length);
        Machine [] machine2 = new Machine[machinesSelected.length];
        System.arraycopy(machinesSelected, 0, machine2, 0, machinesSelected.length);
        int [] completion2 = new int[completionArray.length];
        System.arraycopy(completionArray, 0, completion2, 0, completionArray.length);
        JobIndividual clone = new JobIndividual(job2, operation2, machine2, completion2);
        return clone;
    }
    
    

    
    
}
