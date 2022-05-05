/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import it.unina.ceccarino.gaforjss.algo.Population;
import it.unina.ceccarino.gaforjss.logic.EventManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.print.attribute.HashAttributeSet;

/**
 * pattern singleton
 *
 */
public class InputManager {

    public static final int SEQUENCE_SIZE = 6; //ogni job è fatto da 6 operazioni
    public static int JOB_TOTAL_QUANTITY = 0;
    public static final int NO_SOLUTION = -1; //UNUSED
//    public static final int NUMBER_OF_ACCETTABLE_JOBS =6;
    public static final int JOB_TYPE_SIZE = 10; //numero di differenti job type esistenti

    private JobType[] jobTypes = new JobType[JOB_TYPE_SIZE];

    private Map<Integer, Integer> jobQuantityMap = new HashMap<>();

    private static InputManager _instance = null;

    public static InputManager getInstance() {
        if (_instance == null) {
            _instance = new InputManager();
        }
        return _instance;
    }

    private InputManager() {
        super();
        initJobType();
        randomizeJobQuantity();
    }

    public MachineStep[] getSequenceByJobIndex(int jobType) {
        return this.jobTypes[jobType - 1].getSequence();
    }

    public JobType[] getJobTypes() {
        return jobTypes;
    }

    /**
     * Questo metodo definisce le sequenze di operazioni dei vari job. Ogni job
     * ha un array di MachineStep, e ogni MachinStep comprende il tipo di
     * macchina su cui andrà a alvorare (M1 ad M15) e il tempo di lavorazione
     * (un intero).
     */
    private void initJobType() {
        this.jobTypes[0] = new JobType(1, new MachineStep[]{
            new MachineStep(Machine.M1, 2),
            new MachineStep(Machine.M3, 3),
            new MachineStep(Machine.M5, 5),
            new MachineStep(Machine.M7, 6),
            new MachineStep(Machine.M8, 3),
            new MachineStep(Machine.M12, 5)

        });
        this.jobTypes[1] = new JobType(2, new MachineStep[]{
            new MachineStep(Machine.M1, 4),
            new MachineStep(Machine.M3, 1),
            new MachineStep(Machine.M5, 3),
            new MachineStep(Machine.M8, 4),
            new MachineStep(Machine.M10, 9),
            new MachineStep(Machine.M14, 3)
        });
        this.jobTypes[2] = new JobType(3, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M5, 1),
            new MachineStep(Machine.M7, 5),
            new MachineStep(Machine.M9, 4),
            new MachineStep(Machine.M12, 5)
        });
        this.jobTypes[3] = new JobType(4, new MachineStep[]{
            new MachineStep(Machine.M1, 1),
            new MachineStep(Machine.M3, 2),
            new MachineStep(Machine.M5, 4),
            new MachineStep(Machine.M7, 4),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M14, 4)
        });
        this.jobTypes[4] = new JobType(5, new MachineStep[]{
            new MachineStep(Machine.M1, 6),
            new MachineStep(Machine.M3, 5),
            new MachineStep(Machine.M5, 2),
            new MachineStep(Machine.M8, 7),
            new MachineStep(Machine.M11, 6),
            new MachineStep(Machine.M12, 2)
        });
        this.jobTypes[5] = new JobType(6, new MachineStep[]{
            new MachineStep(Machine.M1, 1),
            new MachineStep(Machine.M3, 2),
            new MachineStep(Machine.M5, 4),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M10, 8),
            new MachineStep(Machine.M14, 4)
        });
        this.jobTypes[6] = new JobType(7, new MachineStep[]{
            new MachineStep(Machine.M1, 2),
            new MachineStep(Machine.M3, 3),
            new MachineStep(Machine.M5, 5),
            new MachineStep(Machine.M9, 2),
            new MachineStep(Machine.M11, 7),
            new MachineStep(Machine.M12, 5)
        });
        this.jobTypes[7] = new JobType(8, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M5, 1),
            new MachineStep(Machine.M8, 6),
            new MachineStep(Machine.M11, 8),
            new MachineStep(Machine.M14, 5)
        });
        this.jobTypes[8] = new JobType(9, new MachineStep[]{
            new MachineStep(Machine.M1, 4),
            new MachineStep(Machine.M3, 1),
            new MachineStep(Machine.M5, 3),
            new MachineStep(Machine.M7, 7),
            new MachineStep(Machine.M10, 9),
            new MachineStep(Machine.M12, 4)
        });
        this.jobTypes[9] = new JobType(10, new MachineStep[]{
            new MachineStep(Machine.M1, 6),
            new MachineStep(Machine.M3, 5),
            new MachineStep(Machine.M3, 2),
            new MachineStep(Machine.M9, 5),
            new MachineStep(Machine.M11, 5),
            new MachineStep(Machine.M14, 2)
        });

    }
//la dimensione della stringa è data dal prodotto della quantità totale di job 
//generati moltiplicati per 6,ovvero il numero di operazioni di ogni job

    public int getDimension() {
        return Settings.getInstance().getMaxJobOveralQuantity() * SEQUENCE_SIZE;
    }

    /**
     * Restituisce la popolazione di JobIndividual di dimensione POPULATION_SIZE
     *
     * @return
     */
    public Population generatePopulation() {
        return new Population(Settings.getInstance().getPopulationSize());
    }

    //la lunghezza degli array è sempre la stessa e pari ad N
    public JobIndividual generateJobIndividual() {
        int N = Settings.getInstance().getMaxJobOveralQuantity() * SEQUENCE_SIZE;
        if (Settings.getInstance().isVerbose()) {
            System.out.println("N = " + N);
        }
        int[] jobPermutationArray = new int[N];
        int[] operationSequenceArray = new int[N];
        Machine[] machinesSelectedArray = new Machine[N];
        List<Integer> freePositions = new LinkedList<>();
        //inizializzazione free position list:
        for (int i = 0; i < jobPermutationArray.length; i++) {
            freePositions.add(i);
        }

        for (int jobTypeIndex = 1; jobTypeIndex <= JOB_TYPE_SIZE; jobTypeIndex++) {
//            System.out.println("JOB TYPE: "+jobTypeIndex);
            for (int j = 0; j < this.jobQuantityMap.get(jobTypeIndex) * SEQUENCE_SIZE; j++) {
                int randomIndex = Utils.randomInRange(0, freePositions.size());
//                System.out.println("randomIndex = "+randomIndex);
                jobPermutationArray[freePositions.get(randomIndex)] = jobTypeIndex;
                freePositions.remove(randomIndex);
//                System.out.println("freePosition Lenght: "+freePositions.size());
            }
        }

        Map<Integer, Integer> positionMap = new HashMap<>();
        for (Integer jobType : this.jobQuantityMap.keySet()) {
            positionMap.put(jobType, 1);
        }
        for (int i = 0; i < jobPermutationArray.length; i++) {
            int step = positionMap.get(jobPermutationArray[i]);
            operationSequenceArray[i] = step;
            positionMap.put(jobPermutationArray[i], ++step);
        }

        for (int i = 0; i < operationSequenceArray.length; i++) {
            int step = getStep(i, operationSequenceArray);
            int jobType = jobPermutationArray[i];
            JobType jobt = this.jobTypes[jobType - 1];
            MachineStep machineSTEP = jobt.getSequence()[step];

            machinesSelectedArray[i] = machineSTEP.getRandomMachine();
        }

        //type qnt   |   type  step
        // 1    2    |    1     1
        // 2    1    |    2     1
        // 3    2    |    3     1
        //2 1 3 3 2 1 2 1 1 1 1 2 2 3 3 3 1 2 2 3 2 2 2 2 
        //1 1 1 2 2 2 3
//        Map<Integer,Integer> positionMap = new HashMap<>();
//        JobType jj;
//        for (int i=0;i<N;i++){
//            jobPermutationArray[i].set_cnt(1);
//            operationSequenceArray[i] = jobPermutationArray[i].get_cnt();
//        }
        //           jobPermutationArray[i].set_cnt(1);
        /*
        for (int i = 0; i < N; i++) {
            int jobType = jobPermutationArray[i];
            if(positionMap.containsKey(jobType)){
                positionMap.put(jobType, (positionMap.get(jobType)+1));
                operationSequenceArray[i] = positionMap.get(jobType);
            }else{
                operationSequenceArray[i] = 1;
                positionMap.put(jobType, 1);
            }
            if(jobType == 1){
                System.out.println("ON MAP:   "+positionMap.get(jobType));
                System.out.println("ON ARRAY: "+operationSequenceArray[i]);
            }
              
        }
         */
        // 4 ,  1
        // 5 ,  2
        // 1 ,  
        JobIndividual result = new JobIndividual(jobPermutationArray, operationSequenceArray, machinesSelectedArray);
        return result;
    }

    /**
     * Ritorna lo step dell'operazione all'indice operationIndex
     *
     * @param operationIndex
     * @return
     */
    public int getStep(int operationIndex, int[] operationSequenceArray) {

        int rawStep = operationSequenceArray[operationIndex];
        if (rawStep <= 6) {
            return rawStep - 1;
        } else {
            return (rawStep - 1) % 6;
        }
    }

    /**
     * Questo metodo randomizza una mappa con chiave l'intero rappresentativo di
     * un JobType e per valore un numero casuale da , rappresentante la quantità
     * di Job per quel tipo.Ogni tipologia di Job quindi, può essere generata in
     * una quantità da 1 a 10.
     *
     */
    public void randomizeJobQuantity() {
        //1 2 3 4 5 6 7 8 9 10
        //2 1 1 1 1 1 1 1 1 1   = 10

        JOB_TOTAL_QUANTITY = 0;
        for (JobType jobType : jobTypes) {
            jobQuantityMap.put(jobType.getType(), 1);
            JOB_TOTAL_QUANTITY++;
        }
        System.out.println("JOB TOTAL QUANTITY = " + JOB_TOTAL_QUANTITY);
        int n = Settings.getInstance().getMaxJobOveralQuantity() - JOB_TOTAL_QUANTITY;
        for (int i = 0; i < n; i++) {
            int randomIndex = Utils.randomInRange(1, 11);
            if (Settings.getInstance().isVerbose()) {
                System.out.println("RANDOM INDEX: " + randomIndex);
            }
            jobQuantityMap.put(randomIndex, jobQuantityMap.get(randomIndex) + 1);
            JOB_TOTAL_QUANTITY++;
        }
        EventManager.getInstance().settingsChanged();

    }

    public Map<Integer, Integer> getJobQuantityMap() {
        return jobQuantityMap;
    }

}
