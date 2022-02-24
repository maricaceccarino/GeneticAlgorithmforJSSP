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

/**
 * pattern singleton
 *
 * @author marica
 */
public class InputManager {

    public static final int SEQUENCE_SIZE = 6;
    public static final int JOB_TYPE_SIZE = 10;
    public static final int NO_SOLUTION = -1;
    public static final int NUMBER_OF_ACCETTABLE_JOBS =6;

    private JobType[] jobTypes = new JobType[JOB_TYPE_SIZE];

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
    }

    /**
     * Questo metodo definisce le sequenze dei vari job. Ogni job ha un array
     * di MachineStep, e ogni MachinStep comprende il tipo di macchina su cui
     * andrà a alvorare (A..I) e il tempo di lavorazione (un intero). 
     */
    private void initJobType() {
        this.jobTypes[0] = new JobType(1, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M3, 3),
            new MachineStep(Machine.M5, 4),
            new MachineStep(Machine.M6, 7),
            new MachineStep(Machine.M8, 4),
            new MachineStep(Machine.M12, 5)

        });
        this.jobTypes[1] = new JobType(2, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M8, 4),
            new MachineStep(Machine.M10, 8),
            new MachineStep(Machine.M14, 3)
        });
        this.jobTypes[2] = new JobType(3, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M4, 7),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M12, 5)
        });
        this.jobTypes[3] = new JobType(4, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M4, 7),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M14, 3)
        });
        this.jobTypes[4] = new JobType(5, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M8 4),
            new MachineStep(Machine.M11, 6),
            new MachineStep(Machine.M12, 5)
        });
         this.jobTypes[5] = new JobType(6, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M10, 8),
            new MachineStep(Machine.M14, 3)
        });
          this.jobTypes[6] = new JobType(7, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M11, 6),
            new MachineStep(Machine.M12, 5)
        });
         this.jobTypes[7] = new JobType(8, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M8, 4),
            new MachineStep(Machine.M11, 6),
            new MachineStep(Machine.M14, 3)
        });
          this.jobTypes[8] = new JobType(9, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M4, 7),
            new MachineStep(Machine.M10, 8),
            new MachineStep(Machine.M12, 5)
        });
           this.jobTypes[9] = new JobType(10, new MachineStep[]{
            new MachineStep(Machine.M1, 3),
            new MachineStep(Machine.M2, 3),
            new MachineStep(Machine.M3, 4),
            new MachineStep(Machine.M9, 3),
            new MachineStep(Machine.M11, 6),
            new MachineStep(Machine.M14, 3)
        });
      

    }

    /**
     * Costruzione di un metodo per la generazione randomica di ordini Regolari
     * e Irregolari
     * Ogni job ha una percentuale di probabilità di essere generato,
     * per gli ordini irregolari questa è sempre del 5%,mentre per gli 
     * ordini regolari,questa dipende dal tipo di job.
     *
     * @param percentage la soglia che se viene superata dal numero random, il
     * metodo restituirà false
     * @return Restituisce true, se viene generato un numero casuale minore di
     * percentage, false viceversa
     */
    public boolean randomInPercentage(int percentage) {
        Random random = new Random();
        float randomInt = random.nextInt(100);
        return randomInt <= percentage;
    }

    /**
     * Restituisce un numero intero casuale tra min e max .
     *
     * @param min
     * il valore minimo entro il quale sarà generato il numero casuale
     * @param max
     * il valore massimo entro il quale sarà generato il numero casuale
     * @return un numero intero casuale tra min e max
     */
    public int randomInRange(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    
    
    
    
    public JobIndividual generateJobIndividual(){
        int N = JOB_TYPE_SIZE * SEQUENCE_SIZE;
        int [] jobPermutationArray = new int[N]; //60
        int [] operationSequenceArray = new int[N]; //60
        List<Integer> freePositions = new LinkedList<>();
        //inizializzazione free position list:
        for (int i = 0; i < jobPermutationArray.length; i++) {
            freePositions.add(i);
        }
        
        for (int jobTypeIndex = 1; jobTypeIndex <= JOB_TYPE_SIZE; jobTypeIndex++) {
//            System.out.println("JOB TYPE: "+jobTypeIndex);
            for (int j = 0; j < SEQUENCE_SIZE; j++) {
                int randomIndex = randomInRange(0,freePositions.size());
//                System.out.println("randomIndex = "+randomIndex);
                jobPermutationArray[freePositions.get(randomIndex)]  = jobTypeIndex;
                freePositions.remove(randomIndex);
//                System.out.println("freePosition Lenght: "+freePositions.size());
            }
        }
        
        Map<Integer,Integer> positionMap = new HashMap<>();
   
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
        
        // 4 ,  1
        // 5 ,  2
        // 1 ,  1
        
        
        
        
        JobIndividual result = new JobIndividual(jobPermutationArray, operationSequenceArray, null);
        return result;
    }
    
    
    /**
     * Genera un array di interi spalmando su di esso il numero della tipologia
     * del job tante volte quante sono le probabilità in centesimi di essere
     * generato casualmente. Questa metodologia servirà poi per scegliere 
     * casualmente un job tenendo conto delle rispettive probabilità di essere 
     * generato. Verrà quindi estratto un indice casuale da 0 a 99, che sarà
     * utilizzato per estrarre dall'array generato da questa funzione, per
     * decidere la tipologia di job.
     * @return 
     * un array di interi che rappresentano la base decisionale per la tipologia
     * di job
     * @see
     * it.unina.ceccarino.gaforjss.model.InputManager#generateRegularJob() 
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
     * Genera la lista dei job regolari, ovvero che sono settati all'istante
     * 0, 50 e 100. In particolare vengono generati tre insiemi di jobs e per
     * ogni insieme è definito un minimo di 2 job e un massimo di 10 job. 
     * @return 
     * La lista di job comprensiva dei 3 insiemi descritti sopra. 
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
     * @return 
     * Genera una lista di Jobs
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
