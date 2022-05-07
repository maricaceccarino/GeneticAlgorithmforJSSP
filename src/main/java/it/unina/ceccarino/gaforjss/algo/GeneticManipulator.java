/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import it.unina.ceccarino.gaforjss.exceptions.GeneticPoolNotLoadedException;
import it.unina.ceccarino.gaforjss.exceptions.NotSortedPopulationException;
import it.unina.ceccarino.gaforjss.logic.EventManager;
import it.unina.ceccarino.gaforjss.model.InputManager;
import static it.unina.ceccarino.gaforjss.model.InputManager.JOB_TOTAL_QUANTITY;
import static it.unina.ceccarino.gaforjss.model.InputManager.SEQUENCE_SIZE;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.JobType;
import it.unina.ceccarino.gaforjss.model.Machine;
import it.unina.ceccarino.gaforjss.model.MachineStep;
import it.unina.ceccarino.gaforjss.model.Settings;
import it.unina.ceccarino.gaforjss.model.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author Marica
 */
public class GeneticManipulator {

    private static GeneticManipulator _instance = null;
    private SelectionStrategy selectionStrategy = SelectionStrategy.BEST;
    private Population population;
    private boolean sorted = false;
    private StopWatch watch = new StopWatch();
    private boolean interrupt = false;

    public static GeneticManipulator getInstance() {
        if (_instance == null) {
            _instance = new GeneticManipulator();
        }
        return _instance;
    }

    /**
     * main method which launch the whole experiment
     */
    public void launch() throws Exception {
        this.interrupt = false;
        if (this.population == null) {
            throw new GeneticPoolNotLoadedException();
        }
        if (!sorted) {
            throw new NotSortedPopulationException();
        }
        System.out.println("<INITIAL POPULATION SIZE> " + this.population.getIndividuals().length);

        int comfirm = JOptionPane.showConfirmDialog(null, "Vuoi veramente iniziare l'esperimento con " + Settings.getInstance().getMaxIteration() + " iterazioni ?", "Conferma Esecuzione", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (comfirm == JOptionPane.NO_OPTION) {
            return;
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                watch.reset();
                watch.start();
                int currentFitness = population.getIndividuals()[0].getFitness();
                EventManager.getInstance().startsSimulation(currentFitness);

                JobIndividual[] POPULATION = Arrays.copyOf(population.getIndividuals(), population.getIndividuals().length);

                int maxIteration = Settings.getInstance().getMaxIteration();

                for (int k = 0; k < maxIteration; k++) {
                    
                    if(interrupt){
                        watch.stop();
                        System.out.println("<<<<< INTERRUPTED >>>>>");
                        return;
                    }

                    EventManager.getInstance().nextCycle(k);

                    JobIndividual[] immunes = (JobIndividual[]) partArray(POPULATION, getImmuneSize());
                    for (JobIndividual immune : immunes) {
                        immune.setImmune(true);
                    }

                    //creo un array di dimensione doppia rispetto al numero di immuni
                    JobIndividual[] immunesClones = new JobIndividual[immunes.length * 2];
                    for (int i = 0; i < immunesClones.length; i++) {
                        immunesClones[i] = (JobIndividual) POPULATION[0].clone();

                        immunesClones[i].setExperimental(true);
                        immunesClones[i].setMutated(true);
                        if (i < immunesClones.length / 2) {
                            immunesClones[i].swap(1);
                        } else {
                            immunesClones[i].swap(10);
                        }
                    }

                    JobIndividual[] normalPeople = evict(POPULATION, immunes.length);

                    Collections.shuffle(Arrays.asList(normalPeople));

                    Pair<JobIndividual[], JobIndividual[]> crossoverPoolPair = evictAndShrink(normalPeople, getPeopleSizeForCrossoverSize());
                    JobIndividual[] crossoverPool = crossoverPoolPair.getLeft();
                    normalPeople = crossoverPoolPair.getRight();

                    Pair<JobIndividual[], JobIndividual[]> mutationPoolPair = evictAndShrink(normalPeople, getPeopleSizeForMutationSize());
                    JobIndividual[] mutationPool = mutationPoolPair.getLeft();
                    normalPeople = mutationPoolPair.getRight();

                    JobIndividual[] untouchedPool = normalPeople;

                    if (Settings.getInstance().isVerbose()) {
                        System.out.println("*** init crossover ***");
                    }

                    JobIndividual[][] crossoverSet = prepareCrossover(crossoverPool);
                    int childrenSize = crossoverSet[0].length;
                    //questo array immagazzina il risultato del crossover
                    JobIndividual[] children = new JobIndividual[childrenSize];

                    for (int i = 0; i < childrenSize; i++) {
                        crossoverSet[0][i].setParent(true);
                        crossoverSet[1][i].setParent(true);
                        children[i] = crossOver(crossoverSet[0][i], crossoverSet[1][i]);
                        children[i].setKid(true);
                    }
                    if (Settings.getInstance().isVerbose()) {
                        System.out.println("*** end crossover ***");
                        System.out.println("+++ init mutation +++");
                    }

//        for (int i = 0; i < mutationPool.length; i++) {
//            mutationPool[i].get
//        }
                    for (JobIndividual individual : mutationPool) {
                        individual.swap(5);
                    }
                    if (Settings.getInstance().isVerbose()) {
                        System.out.println("+++ end mutation +++");
                        System.out.println("--- start swap worst population with crossover result ---");
                    }

                    LinkedList<JobIndividual> resultPopulation = new LinkedList<>();
                    for (JobIndividual immune : immunes) {
                        resultPopulation.add(immune);
                    }
                    for (JobIndividual crossaint : crossoverPool) {
                        resultPopulation.add(crossaint);
                    }
                    for (JobIndividual mutant : mutationPool) {
                        resultPopulation.add(mutant);
                    }
                    for (JobIndividual unt : untouchedPool) {
                        resultPopulation.add(unt);
                    }
                    for (JobIndividual kid : children) {
                        resultPopulation.add(kid);
                    }
                    for (JobIndividual immunesClone : immunesClones) {
                        resultPopulation.add(immunesClone);
                    }
                    if (Settings.getInstance().isVerbose()) {
                        System.out.println("<END POPULATION> " + resultPopulation.size());
                    }

                    Collections.sort(resultPopulation);

                    int avg = 0;
                    for (JobIndividual jobIndividual : resultPopulation) {
                        String mutated = jobIndividual.isMutated() ? "mutated" : "";
                        String kid = jobIndividual.isKid() ? "kid" : "";
                        String parent = jobIndividual.isParent() ? "parent" : "";
                        String immune = jobIndividual.isImmune() ? "immune" : "";
                        String experimental = jobIndividual.isExperimental() ? "experimental" : "";
                        avg += jobIndividual.getFitness();
                        if (Settings.getInstance().isVerbose()) {
                            System.out.println(jobIndividual.getFitness() + " " + mutated + " " + kid + " " + parent + " " + immune + " " + experimental);
                        }
                    }
                    avg /= resultPopulation.size();
                    EventManager.getInstance().newAVG(avg);

                    if (Settings.getInstance().isVerbose()) {
                        System.out.println("--- end swap worst population with crossover result ---");
                    }
                    for (int i = 0; i < (children.length + immunesClones.length); i++) {

                        JobIndividual removedElement = resultPopulation.pollLast();
                    }

                    if (Settings.getInstance().isVerbose()) {
                        System.out.println(" NEW GENERATIONS (" + resultPopulation.size() + ")");
                    }

                    if (Settings.getInstance().isVerbose()) {
                        for (JobIndividual jobIndividual : resultPopulation) {
                            String mutated = jobIndividual.isMutated() ? "mutated" : "";
                            String kid = jobIndividual.isKid() ? "kid" : "";
                            String parent = jobIndividual.isParent() ? "parent" : "";
                            String immune = jobIndividual.isImmune() ? "immune" : "";
                            String experimental = jobIndividual.isExperimental() ? "experimental" : "";

                            System.out.println(jobIndividual.getFitness() + " " + mutated + " " + kid + " " + parent + " " + immune + " " + experimental);

                        }
                    }

                    int z = 0;
                    if (Settings.getInstance().isVerbose()) {
                        System.out.println("POP SIZE: " + POPULATION.length);
                    }
                    for (JobIndividual jobIndividual : resultPopulation) {
                        jobIndividual.resetFlags();
                        POPULATION[z] = jobIndividual;
                        z++;
                    }

                    int newFitness = POPULATION[0].getFitness();
                    if (newFitness < currentFitness) {
                        currentFitness = newFitness;
                        EventManager.getInstance().newImprovement(POPULATION[0],newFitness);
                    }

                }
                watch.stop();

                EventManager.getInstance().end(POPULATION[0]);
            }
        });
        t.start();

    }

    public long getElapsedTime() {
        return this.watch.getTime();

    }

    /**
     * Estrae elementi dall'array iniziale e li copia in un nuovo array.
     *
     * @param initialPopulation
     * @param sizeToEvict
     * @return
     */
    public JobIndividual[] evict(JobIndividual[] initialPopulation, int sizeToEvict) {

        //estrazione dei primi 'sizeToExtract' elementi dall'array iniziale
        JobIndividual[] remaining = (JobIndividual[]) partArray(initialPopulation, sizeToEvict, initialPopulation.length - sizeToEvict);

        return remaining;
    }

    /**
     * Estrae (rimuovendoli) elementi dall'array iniziale. - modifica l'array
     * iniziale -
     *
     * @param initialPopulation
     * @param sizeToEvict
     * @return
     */
    public Pair<JobIndividual[], JobIndividual[]> evictAndShrink(JobIndividual[] initialPopulation, int sizeToEvict) {

        //estrazione dei primi 'sizeToExtract' elementi dall'array iniziale
        JobIndividual[] remaining = new JobIndividual[sizeToEvict];

        List<JobIndividual> list = new LinkedList<JobIndividual>(Arrays.asList(initialPopulation));

//        System.out.println("list size: "+list.size());
//        System.out.println("size to evict : "+sizeToEvict);
        for (int i = 0; i < sizeToEvict; i++) {
            JobIndividual removed = list.remove(0);
            remaining[i] = removed;
        }

        initialPopulation = list.toArray(new JobIndividual[initialPopulation.length - sizeToEvict]);

        return new ImmutablePair<JobIndividual[], JobIndividual[]>(remaining, initialPopulation);
    }

    /**
     * restituisce il numero di individui selezionati per la mutazione
     *
     * @return
     */
    public int getPeopleSizeForMutationSize() {
        if (population == null || this.population.getIndividuals().length == 0) {
            return 0;
        } else {
            //tot : 100 = x : perc   ->> x = (tot*perc)/100
            return (int) (this.population.getIndividuals().length * Settings.getInstance().getMutationSubgroupSize()) / 100;
        }
    }

    public int getImmuneSize() {
        if (population == null || this.population.getIndividuals().length == 0) {
            return 0;
        } else {
            //tot : 100 = x : perc   ->> x = (tot*perc)/100
            return (int) (this.population.getIndividuals().length * Settings.getInstance().getElectedPercentage()) / 100;
        }
    }

    public int getPeopleSizeForCrossoverSize() {
        if (population == null || this.population.getIndividuals().length == 0) {
            return 0;
        } else {
            //tot : 100 = x : perc   ->> x = (tot*perc)/100
            return (int) (this.population.getIndividuals().length * Settings.getInstance().getCrossoverSubgroupSize()) / 100;
        }
    }

    public void loadInitialPopulation(Population population) {
        this.population = population;
        Arrays.sort(this.population.getIndividuals());
        sorted = true;
    }
//
//    public void mutate(int howManyTimes) {
//        //randomizzare-- 
//        //vanno mutati coloro che non hanno subito il crossover
//        for (int i = 0; i < getPeopleSizeForMutationSize(); i++) {
//            this.population.getIndividuals()[i].swap(howManyTimes);
//        }
//    }

    private GeneticManipulator() {
        super();
    }

    public SelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public JobIndividual[] partArray(JobIndividual[] array, int size) {
        JobIndividual[] part = new JobIndividual[size];
        System.arraycopy(array, 0, part, 0, size);
        return part;
    }

    public JobIndividual[] partArray(JobIndividual[] array, int initialPosition, int size) {
        JobIndividual[] part = new JobIndividual[size];
        System.arraycopy(array, initialPosition, part, 0, size);
        return part;
    }

    /**
     * crossover
     */
    public JobIndividual[][] prepareCrossover(JobIndividual[] crossoverPool) {
        //estraggo la corrente percentuale di eletti che non subiranno il crossover
        int electedPercentage = Settings.getInstance().getElectedPercentage();

        //verifico se i rimanenti sono pari o dispari, se dispari aumento di uno
        //gli eletti di modo che poi posso dividere i rimanenti in due insiemi
        //esatti.
        //La seguente variabile sarà quindi zero nel caso i rimanenti sono di 
        //numero pari, 1 viceversa. 
        int ghostElected = crossoverPool.length % 2 == 0 ? 0 : 1;

        JobIndividual[][] readyForCrossover = new JobIndividual[2][(crossoverPool.length - ghostElected) / 2];

        readyForCrossover[0] = Arrays.copyOfRange(crossoverPool, 0, (crossoverPool.length - ghostElected) / 2);
        readyForCrossover[1] = Arrays.copyOfRange(crossoverPool, ((crossoverPool.length - ghostElected) / 2), crossoverPool.length - ghostElected);

        return readyForCrossover;

    }

    public JobIndividual crossOver(JobIndividual mister, JobIndividual miss) {

        int N = JOB_TOTAL_QUANTITY * SEQUENCE_SIZE;
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

        List<IntWrapper> posizioniVuote = new LinkedList<>();
        Map<Integer, Integer> posizioniOccupateMap = new HashMap<>();
        //  id job ,  quantity
        for (JobType jobType : InputManager.getInstance().getJobTypes()) {
            posizioniOccupateMap.put(jobType.getType(), 0);
        }

        for (int i = 0; i < mister.getJobPermutation().length; i++) {
            if (mister.getJobPermutation()[i] <= 5) {
                jobPermutationArray[i] = mister.getJobPermutation()[i];
                posizioniOccupateMap.put(mister.getJobPermutation()[i], posizioniOccupateMap.get(mister.getJobPermutation()[i]) + 1);
            } else if (miss.getJobPermutation()[i] > 5) {
                jobPermutationArray[i] = miss.getJobPermutation()[i];
                posizioniOccupateMap.put(miss.getJobPermutation()[i], posizioniOccupateMap.get(miss.getJobPermutation()[i]) + 1);
            } else {
                //posizioni vuote
                posizioniVuote.add(new IntWrapper(i));
            }
        }

        if (Settings.getInstance().isVerbose()) {
            System.out.println("\nMISTER: ");
            for (int job : mister.getJobPermutation()) {
                System.out.print(job + " ");
            }
            System.out.println("\nMISS: ");
            for (int job : miss.getJobPermutation()) {
                System.out.print(job + " ");
            }
            System.out.println("\nCROSSOVER CON BUCHI ");
            for (int jjj : jobPermutationArray) {
                System.out.print(jjj + " ");
            }
        }

        if (Settings.getInstance().isVerbose()) {
            System.out.println("\nposizione vuote size: " + posizioniVuote.size());
        }
        int checkStart = posizioniVuote.size();
        int checkSum = 0;
        for (Map.Entry<Integer, Integer> entry : posizioniOccupateMap.entrySet()) {
            int jobLimit = InputManager.getInstance().getJobQuantityMap().get(entry.getKey());
            int jobMancanti = (jobLimit * 6) - entry.getValue();
            for (int i = 0; i < jobMancanti; i++) {
                if (posizioniVuote.isEmpty()) {
                    break;
                }
                int randomInRange = Utils.randomInRange(0, posizioniVuote.size());
                int position = posizioniVuote.get(randomInRange).getValue();
                posizioniVuote.remove(randomInRange);
                jobPermutationArray[position] = entry.getKey();
                checkSum++;
            }
        }

        if (Settings.getInstance().isVerbose()) {
            System.out.println("checkSum = " + checkSum);
        }

        if (checkStart != checkSum) {
            System.out.println(">>>>>>>>>>  CHECK SUM INVALID  <<<<<<<<<<<<<<<");
        }

        if (Settings.getInstance().isVerbose()) {
            System.out.println("\nCROSSOVER SENZA BUCHI ");
        }
        boolean errore = false;

        for (int jjj : jobPermutationArray) {
            if (jjj == 0) {
                errore = true;
            }
            if (Settings.getInstance().isVerbose()) {
                System.out.print(jjj + " ");
            }
        }

        if (errore) {
            System.out.println("\n****** INVALID ARRAY *******");
            System.out.println("****** INVALID ARRAY *******");
            System.out.println("****** INVALID ARRAY *******");

        }

        Map<Integer, Integer> positionMap = new HashMap<>();
        for (Integer jobType : InputManager.getInstance().getJobQuantityMap().keySet()) {
            positionMap.put(jobType, 1);
        }
        for (int i = 0; i < jobPermutationArray.length; i++) {
            int step = positionMap.get(jobPermutationArray[i]);
            operationSequenceArray[i] = step;
            positionMap.put(jobPermutationArray[i], ++step);
        }

        for (int i = 0; i < operationSequenceArray.length; i++) {
            int step = InputManager.getInstance().getStep(i, operationSequenceArray);
            int jobType = jobPermutationArray[i];
            JobType jobt = InputManager.getInstance().getJobTypes()[jobType - 1];
            MachineStep machineSTEP = jobt.getSequence()[step];

            machinesSelectedArray[i] = machineSTEP.getRandomMachine();
        }
        JobIndividual baby = new JobIndividual(jobPermutationArray, operationSequenceArray, machinesSelectedArray);
        return baby;
    }

    public boolean validateSolution(JobIndividual solution) {

        Map<Integer, Integer> jobQuantityMap = InputManager.getInstance().getJobQuantityMap();
        int[] jobPermutation = solution.getJobPermutation();

        for (Map.Entry<Integer, Integer> entry : jobQuantityMap.entrySet()) {
            int occ = 0;
            for (int i : jobPermutation) {
                if (i == entry.getKey()) {
                    occ++;
                }
            }
            if (occ != entry.getValue()*6) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> prova = new PriorityQueue<>();

        prova.add(3);
        prova.add(4);
        prova.add(1);
        prova.add(2);

        for (Integer integer : prova) {
            System.out.println("p: " + integer);
        }

    }

    public void interrupt() {
       this.interrupt = true;
    }

}
