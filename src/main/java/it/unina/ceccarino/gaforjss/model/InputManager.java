/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

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
 * @author marica
 */
public class InputManager {

    public static final int SEQUENCE_SIZE = 6;
    public static int JOB_TOTAL_QUANTITY = 0;
    public static final int NO_SOLUTION = -1;
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
     * Questo metodo definisce le sequenze dei vari job. Ogni job ha un array di
     * MachineStep, e ogni MachinStep comprende il tipo di macchina su cui andrà
     * a alvorare (M1 ad M15) e il tempo di lavorazione (un intero).
     */
    private void initJobType() {
        this.jobTypes[0] = new JobType(1, new MachineStep[]{
            new MachineStep(Machine.M1, 2),
            new MachineStep(Machine.M3, 3),
            new MachineStep(Machine.M5, 5),
            new MachineStep(Machine.M6, 6),
            new MachineStep(Machine.M8, 3),
            new MachineStep(Machine.M12, 5)

        });
        this.jobTypes[1] = new JobType(2, new MachineStep[]{
            new MachineStep(Machine.M1, 4),
            new MachineStep(Machine.M2, 1),
            new MachineStep(Machine.M3, 3),
            new MachineStep(Machine.M8, 4),
            new MachineStep(Machine.M10, 9),
            new MachineStep(Machine.M14, 3)
        });
        this.jobTypes[2] = new JobType(3, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 4),
            new MachineStep(Machine.M3, 1),
            new MachineStep(Machine.M4, 5),
            new MachineStep(Machine.M9, 4),
            new MachineStep(Machine.M12, 5)
        });
        this.jobTypes[3] = new JobType(4, new MachineStep[]{
            new MachineStep(Machine.M1, 1),
            new MachineStep(Machine.M2, 2),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M4, 4),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M14, 4)
        });
        this.jobTypes[4] = new JobType(5, new MachineStep[]{
            new MachineStep(Machine.M1, 6),
            new MachineStep(Machine.M2, 5),
            new MachineStep(Machine.M3, 2),
            new MachineStep(Machine.M8, 7),
            new MachineStep(Machine.M11, 6),
            new MachineStep(Machine.M12, 2)
        });
         this.jobTypes[5] = new JobType(6, new MachineStep[]{
            new MachineStep(Machine.M1, 1),
            new MachineStep(Machine.M2, 2),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M10, 8),
            new MachineStep(Machine.M14, 4)
        });
          this.jobTypes[6] = new JobType(7, new MachineStep[]{
            new MachineStep(Machine.M1, 2),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 5),
            new MachineStep(Machine.M9, 2),
            new MachineStep(Machine.M11, 7),
            new MachineStep(Machine.M12, 5)
        });
         this.jobTypes[7] = new JobType(8, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 4),
            new MachineStep(Machine.M3, 1),
            new MachineStep(Machine.M8, 6),
            new MachineStep(Machine.M11, 8),
            new MachineStep(Machine.M14, 5)
        });
          this.jobTypes[8] = new JobType(9, new MachineStep[]{
            new MachineStep(Machine.M1, 4),
            new MachineStep(Machine.M2, 1),
            new MachineStep(Machine.M3, 3),
            new MachineStep(Machine.M4, 7),
            new MachineStep(Machine.M10, 9),
            new MachineStep(Machine.M12, 4)
        });
           this.jobTypes[9] = new JobType(10, new MachineStep[]{
            new MachineStep(Machine.M1, 6),
            new MachineStep(Machine.M2, 5),
            new MachineStep(Machine.M3, 2),
            new MachineStep(Machine.M9, 5),
            new MachineStep(Machine.M11, 5),
            new MachineStep(Machine.M14, 2)
        });

    }

    public JobIndividual generateJobIndividual() {
        int N = JOB_TOTAL_QUANTITY * SEQUENCE_SIZE;
        System.out.println("N = " + N);
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
    private int getStep(int operationIndex, int[] operationSequenceArray) {

        int rawStep = operationSequenceArray[operationIndex];
        if (rawStep <= 6) {
            return rawStep-1;
        } else { 
            return rawStep % 6;
        }
    }

    /**
     * Randomizza una mappa con chiave l'intero rappresentativo di un JobType e
     * per valore un numero casuale da 1 a 10, rappresentante la quantità di Job
     * per quel tipo
     */
    private void randomizeJobQuantity() {

        for (JobType jobType : jobTypes) {
            int randomQuantity = Utils.randomInRange(1, 10);
            jobQuantityMap.put(jobType.getType(), randomQuantity);
            JOB_TOTAL_QUANTITY += randomQuantity;
        }
    }

    public Map<Integer, Integer> getJobQuantityMap() {
        return jobQuantityMap;
    }

    /**
     * Genera un array di interi spalmando su di esso il numero della tipologia
     * del job tante volte quante sono le probabilità in centesimi di essere
     * generato casualmente. Questa metodologia servirà poi per scegliere
     * casualmente un job tenendo conto delle rispettive probabilità di essere
     * generato. Verrà quindi estratto un indice casuale da 0 a 99, che sarà
     * utilizzato per estrarre dall'array generato da questa funzione, per
     * decidere la tipologia di job.
     *
     * @return un array di interi che rappresentano la base decisionale per la
     * tipologia di job
     * @see it.unina.ceccarino.gaforjss.model.InputManager#generateRegularJob()
     */
//    public int [] generateJobTypeRandomBase(){
//        int [] base = new int[100];
//        int i = 0;
//        for (JobType jobType : jobTypes) {
//            if(jobType == null){
//                System.out.println("ERROR: jobType is not initialized.");
//                break;
//            }
//            int generationProbability = jobType.getGenerationProbability();
//            for (int k = 0; k < generationProbability; k++) {
//                base[i] = jobType.getType();
//                i++;
//            }
//                    
//        }
//        return base;
//    }
    /**
     * Genera la lista dei job regolari, ovvero che sono settati all'istante 0,
     * 50 e 100. In particolare vengono generati tre insiemi di jobs e per ogni
     * insieme è definito un minimo di 2 job e un massimo di 10 job.
     *
     * @return La lista di job comprensiva dei 3 insiemi descritti sopra.
     */
//    public List<Job> generateRegularJob (){
//        int[] base = generateJobTypeRandomBase();
//        List<Job> regularJobs = new LinkedList<>();
//        int jobs0 = randomInRange(3, 10);
//        int jobs50 = randomInRange(3, 10);
//        int jobs100 = randomInRange(3, 10);
//        
//        for (int i = 0; i < jobs0; i++) {
//            int jobTypeIndex = base[randomInRange(0, 100)];
//            Job job = new Job(jobTypes[jobTypeIndex-1], 0, NO_SOLUTION);
//            regularJobs.add(job);
//        }
//        for (int i = 0; i < jobs50; i++) {
//            int jobTypeIndex = base[randomInRange(0, 100)];
//            Job job = new Job(jobTypes[jobTypeIndex-1], 49, NO_SOLUTION);
//            regularJobs.add(job);
//        }
//        for (int i = 0; i < jobs100; i++) {
//            int jobTypeIndex = base[randomInRange(0, 100)];
//            Job job = new Job(jobTypes[jobTypeIndex-1], 99, NO_SOLUTION);
//            regularJobs.add(job);
//        }
//        return regularJobs;
//    }
    /**
     * Genera una lista di Job regolari e irregolari.
     *
     * @return Genera una lista di Jobs
     */
//    public List<Job> generatesJobs(){
//        List<Job> jobs = new LinkedList<Job>();
//        
//        jobs.addAll(generateRegularJob());
//        jobs.addAll(generateIrregularJob());
//        
//        return jobs;
//        
//    }
}
