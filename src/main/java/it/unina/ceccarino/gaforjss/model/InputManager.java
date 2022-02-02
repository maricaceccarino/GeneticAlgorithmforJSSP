/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * pattern singleton
 *
 * @author marica
 */
public class InputManager {

    public static final int SEQUENCE_SIZE = 6;
    public static final int JOB_TYPE_SIZE = 10;
    //percentuale che arrivi un job irregolare per ogni tick di tempo
    public static final int IRREGULAR_JOB_COMPARISON_PERCENTAGE = 5;
    public static final int NO_SOLUTION = -1;
    public static final int MAX_ACCETTABLE_JOBS =10;

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
        this.jobTypes[0] = new JobType(5, 1, new MachineStep[]{
            new MachineStep(Machine.A, 2),
            new MachineStep(Machine.B, 3),
            new MachineStep(Machine.C, 5),
            new MachineStep(Machine.D, 6),
            new MachineStep(Machine.E, 3),
            new MachineStep(Machine.I, 5)

        });
        this.jobTypes[1] = new JobType(5, 2, new MachineStep[]{
            new MachineStep(Machine.A, 4),
            new MachineStep(Machine.B, 1),
            new MachineStep(Machine.C, 3),
            new MachineStep(Machine.E, 4),
            new MachineStep(Machine.G, 9),
            new MachineStep(Machine.J, 3)
        });
        this.jobTypes[2] = new JobType(5, 3, new MachineStep[]{
            new MachineStep(Machine.A, 3),
            new MachineStep(Machine.B, 4),
            new MachineStep(Machine.C, 1),
            new MachineStep(Machine.D, 5),
            new MachineStep(Machine.F, 4),
            new MachineStep(Machine.I, 5)
        });
        this.jobTypes[3] = new JobType(5, 4, new MachineStep[]{
            new MachineStep(Machine.A, 1),
            new MachineStep(Machine.B, 2),
            new MachineStep(Machine.C, 4),
            new MachineStep(Machine.D, 4),
            new MachineStep(Machine.F, 3),
            new MachineStep(Machine.J, 4)
        });
        this.jobTypes[4] = new JobType(10, 5, new MachineStep[]{
            new MachineStep(Machine.A, 6),
            new MachineStep(Machine.B, 5),
            new MachineStep(Machine.C, 2),
            new MachineStep(Machine.E, 7),
            new MachineStep(Machine.H, 6),
            new MachineStep(Machine.I, 2)
        });
         this.jobTypes[5] = new JobType(10, 6, new MachineStep[]{
            new MachineStep(Machine.A, 1),
            new MachineStep(Machine.B, 2),
            new MachineStep(Machine.C, 4),
            new MachineStep(Machine.F, 3),
            new MachineStep(Machine.G, 8),
            new MachineStep(Machine.J, 4)
        });
          this.jobTypes[6] = new JobType(10, 7, new MachineStep[]{
            new MachineStep(Machine.A, 2),
            new MachineStep(Machine.B, 3),
            new MachineStep(Machine.C, 5),
            new MachineStep(Machine.F, 2),
            new MachineStep(Machine.H, 7),
            new MachineStep(Machine.I, 5)
        });
         this.jobTypes[7] = new JobType(10, 8, new MachineStep[]{
            new MachineStep(Machine.A, 3),
            new MachineStep(Machine.B, 4),
            new MachineStep(Machine.C, 1),
            new MachineStep(Machine.E, 6),
            new MachineStep(Machine.H, 8),
            new MachineStep(Machine.J, 5)
        });
          this.jobTypes[8] = new JobType(20, 9, new MachineStep[]{
            new MachineStep(Machine.A, 4),
            new MachineStep(Machine.B, 1),
            new MachineStep(Machine.C, 3),
            new MachineStep(Machine.D, 7),
            new MachineStep(Machine.G, 9),
            new MachineStep(Machine.I, 4)
        });
           this.jobTypes[9] = new JobType(20, 10, new MachineStep[]{
            new MachineStep(Machine.A, 6),
            new MachineStep(Machine.B, 5),
            new MachineStep(Machine.C, 2),
            new MachineStep(Machine.F, 5),
            new MachineStep(Machine.H, 5),
            new MachineStep(Machine.J, 2)
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

    /**
     * Genera una lista di job irregolari. Viene eseguito un ciclo di 100 unità
     * temporali, e per ogni unità temporale vi è una probabilità definita
     * da IRREGULAR_JOB_COMPARISON_PERCENTAGE, che un nuovo job all'istante i
     * venga generato e incluso nella lista risultante. 
     * Il tipo di job che verrà generato è anch'esso casuale con probabilità 
     * uniforme tra i 10 possibili tipi di job
     * @return 
     */
    public List<Job> generateIrregularJob() {

        List<Job> irregularJobs = new LinkedList<>();
        //il for rappresenta tutti gli istanti di tempo da 0 a 99
        for (int i = 0; i < 100; i++) {
            //entra nell'if se viene generato un numero casuale < 5 (rappresenta la possibilità di evento del 5%)
            if (randomInPercentage(IRREGULAR_JOB_COMPARISON_PERCENTAGE)) {
                int jobTypeIndex = randomInRange(0, 10);
                Job job = new Job(jobTypes[jobTypeIndex], i, NO_SOLUTION);
                irregularJobs.add(job);
            }
        }
        return irregularJobs;

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
    public int [] generateJobTypeRandomBase(){
        int [] base = new int[100];
        int i = 0;
        for (JobType jobType : jobTypes) {
            if(jobType == null){
                System.out.println("ERROR: jobType is not initialized.");
                break;
            }
            int generationProbability = jobType.getGenerationProbability();
            for (int k = 0; k < generationProbability; k++) {
                base[i] = jobType.getType();
                i++;
            }
                    
        }
        return base;
    }
    
    /**
     * Genera la lista dei job regolari, ovvero che sono settati all'istante
     * 0, 50 e 100. In particolare vengono generati tre insiemi di jobs e per
     * ogni insieme è definito un minimo di 2 job e un massimo di 10 job. 
     * @return 
     * La lista di job comprensiva dei 3 insiemi descritti sopra. 
     */
    public List<Job> generateRegularJob (){
        int[] base = generateJobTypeRandomBase();
        List<Job> regularJobs = new LinkedList<>();
        int jobs0 = randomInRange(3, 10);
        int jobs50 = randomInRange(3, 10);
        int jobs100 = randomInRange(3, 10);
        
        for (int i = 0; i < jobs0; i++) {
            int jobTypeIndex = base[randomInRange(0, 100)];
            Job job = new Job(jobTypes[jobTypeIndex-1], 0, NO_SOLUTION);
            regularJobs.add(job);
        }
        for (int i = 0; i < jobs50; i++) {
            int jobTypeIndex = base[randomInRange(0, 100)];
            Job job = new Job(jobTypes[jobTypeIndex-1], 49, NO_SOLUTION);
            regularJobs.add(job);
        }
        for (int i = 0; i < jobs100; i++) {
            int jobTypeIndex = base[randomInRange(0, 100)];
            Job job = new Job(jobTypes[jobTypeIndex-1], 99, NO_SOLUTION);
            regularJobs.add(job);
        }
        return regularJobs;
    }
    
    /**
     * Genera una lista di Job regolari e irregolari.
     * @return 
     * Genera una lista di Jobs
     */
    public List<Job> generatesJobs(){
        List<Job> jobs = new LinkedList<Job>();
        
        jobs.addAll(generateRegularJob());
        jobs.addAll(generateIrregularJob());
        
        return jobs;
        
    }
}
