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
    //percentuale che si arrivi un job irregolare per ogni tick di tempo
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

    private void initJobType() {
        this.jobTypes[0] = new JobType(5f, 1, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.D, 7),
            new MachineSequence(Machine.E, 4),
            new MachineSequence(Machine.I, 5)

        });
        this.jobTypes[1] = new JobType(5f, 2, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.E, 4),
            new MachineSequence(Machine.G, 8),
            new MachineSequence(Machine.J, 3)
        });
        this.jobTypes[2] = new JobType(5f, 3, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.D, 7),
            new MachineSequence(Machine.F, 3),
            new MachineSequence(Machine.I, 5)
        });
        this.jobTypes[3] = new JobType(5f, 4, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.D, 7),
            new MachineSequence(Machine.F, 3),
            new MachineSequence(Machine.J, 3)
        });
        this.jobTypes[4] = new JobType(5f, 5, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.E, 4),
            new MachineSequence(Machine.H, 6),
            new MachineSequence(Machine.I, 5)
        });
         this.jobTypes[5] = new JobType(5f, 6, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.F, 3),
            new MachineSequence(Machine.G, 8),
            new MachineSequence(Machine.J, 3)
        });
          this.jobTypes[6] = new JobType(5f, 7, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.F, 3),
            new MachineSequence(Machine.H, 6),
            new MachineSequence(Machine.I, 5)
        });
         this.jobTypes[7] = new JobType(5f, 8, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.E, 4),
            new MachineSequence(Machine.H, 6),
            new MachineSequence(Machine.J, 3)
        });
          this.jobTypes[8] = new JobType(5f, 9, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.D, 7),
            new MachineSequence(Machine.G, 8),
            new MachineSequence(Machine.I, 5)
        });
           this.jobTypes[9] = new JobType(5f, 10, new MachineSequence[]{
            new MachineSequence(Machine.A, 3),
            new MachineSequence(Machine.B, 3),
            new MachineSequence(Machine.C, 4),
            new MachineSequence(Machine.F, 3),
            new MachineSequence(Machine.H, 6),
            new MachineSequence(Machine.J, 3)
        });
      

    }

    /**
     * Restituisce true, se viene generato un numero casuale minore di
     * percentage
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
     * @param max
     * @return un numero intero casuale tra min e max
     */
    public int randomInRange(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

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
    public List<Job> generateRegularJob (){
        
        List<Job> regularJobs = new LinkedList<>();
        int jobs0 = randomInRange(0, 10);
        int jobs50 = randomInRange(0, 10);
        int jobs100 = randomInRange(0, 10);
        
        for (int i = 0; i < jobs0; i++) {
            
        }
        for (int i = 0; i < jobs50; i++) {
            
        }
        for (int i = 0; i < jobs100; i++) {
            
        }
        
    }
}
