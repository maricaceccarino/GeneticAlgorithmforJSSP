/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import it.unina.ceccarino.gaforjss.exceptions.GeneticPoolNotLoadedException;
import it.unina.ceccarino.gaforjss.exceptions.NotSortedPopulationException;
import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.JobType;
import it.unina.ceccarino.gaforjss.model.Settings;
import it.unina.ceccarino.gaforjss.model.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author Marica
 */
public class GeneticManipulator {

    private static GeneticManipulator _instance = null;
    private SelectionStrategy selectionStrategy = SelectionStrategy.BEST;
    private Population population;
    private boolean sorted = false;

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
        if (this.population == null) {
            throw new GeneticPoolNotLoadedException();
        }
        if (!sorted) {
            throw new NotSortedPopulationException();
        }

        JobIndividual[] immunes = (JobIndividual[]) partArray(this.population.getIndividuals(), getImmuneSize());

        JobIndividual[] normalPeople = evict(this.population.getIndividuals(), immunes.length);

        Collections.shuffle(Arrays.asList(normalPeople));

        JobIndividual[] crossoverPool = (JobIndividual[]) partArray(normalPeople, immunes.length + getPeopleSizeForCrossoverSize());
        JobIndividual[] mutationPool = (JobIndividual[]) partArray(normalPeople, crossoverPool.length + getPeopleSizeForMutationSize());
        JobIndividual[] untouchedPool = (JobIndividual[]) partArray(normalPeople, mutationPool.length + getPeopleSizeForMutationSize());
        
        System.out.println("*** init crossover ***");
        
        JobIndividual[][] crossoverSet = this.prepareCrossover(crossoverPool);
        int childrenSize = crossoverSet[0].length;
        //questo array immagazzina il risultato del crossover
        JobIndividual [] children = new JobIndividual[childrenSize]; 
        
        for (int i = 0; i < childrenSize; i++) {
            children[i] = this.crossOver(crossoverSet[0][i], crossoverSet[1][i]);
        }
        System.out.println("*** end crossover ***");
        
        System.out.println("+++ init mutation +++");
        
//        for (int i = 0; i < mutationPool.length; i++) {
//            mutationPool[i].get
//        }
        for (JobIndividual individual : mutationPool) {
            individual.swap(1);
        }
        System.out.println("+++ end mutation +++");
        
        System.out.println("--- start swap worst population with crossover result ---");
        
        TreeSet<JobIndividual> tree = new TreeSet<>();
        for (JobIndividual immune : immunes) {
            tree.add(immune);
        }
        for (JobIndividual crossaint : crossoverPool) {
            tree.add(crossaint);
        }
        for (JobIndividual mutant : mutationPool) {
            tree.add(mutant);
        }
        for (JobIndividual unt : untouchedPool) {
            tree.add(unt);
        }
        for (JobIndividual kid : children) {
            tree.add(kid);
        }
        
        for (JobIndividual jobIndividual : tree) {
            System.out.println(jobIndividual.getFitness());
        }
        
        
        
        
        System.out.println("--- end swap worst population with crossover result ---");
        
    }

    public JobIndividual[] evict(JobIndividual[] initialPopulation, int sizeToEvict) {

        //estrazione dei primi 'sizeToExtract' elementi dall'array iniziale
        JobIndividual[] remaining = (JobIndividual[]) partArray(initialPopulation, sizeToEvict, initialPopulation.length - sizeToEvict);

        return remaining;
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
        
        JobIndividual[][] readyForCrossover = new JobIndividual[2][(crossoverPool.length - ghostElected )/2];

        readyForCrossover[0] = Arrays.copyOfRange(crossoverPool, 0, (crossoverPool.length - ghostElected) / 2);
        readyForCrossover[1] = Arrays.copyOfRange(crossoverPool, ((crossoverPool.length - ghostElected) / 2), crossoverPool.length - ghostElected);
        
        return readyForCrossover;

    }

    public JobIndividual crossOver(JobIndividual mister, JobIndividual miss) {

        JobIndividual baby = new JobIndividual();
        List<Integer> posizioniVuote = new LinkedList<>();
        Map<Integer, Integer> posizioniOccupateMap = new HashMap<>();
        //  id job ,  quantity
        for (JobType jobType : InputManager.getInstance().getJobTypes()) {
            posizioniOccupateMap.put(jobType.getType(), 0);
        }

        for (int i = 0; i < mister.getJobPermutation().length; i++) {
            if (mister.getJobPermutation()[i] <= 5) {
                baby.getJobPermutation()[i] = mister.getJobPermutation()[i];
                posizioniOccupateMap.put(mister.getJobPermutation()[i], posizioniOccupateMap.get(mister.getJobPermutation()[i]) + 1);
            } else if (miss.getJobPermutation()[i] > 5) {
                baby.getJobPermutation()[i] = miss.getJobPermutation()[i];
                posizioniOccupateMap.put(miss.getJobPermutation()[i], posizioniOccupateMap.get(miss.getJobPermutation()[i]) + 1);
            } else {
                //posizioni vuote
                posizioniVuote.add(i);
            }
        }

        for (Map.Entry<Integer, Integer> entry : posizioniOccupateMap.entrySet()) {
            int jobLimit = InputManager.getInstance().getJobQuantityMap().get(entry.getKey());
            int jobMancanti = jobLimit - entry.getValue();
            for (int i = 0; i < jobMancanti; i++) {

                int randomInRange = Utils.randomInRange(0, posizioniVuote.size());
                posizioniVuote.remove(randomInRange);
                baby.getJobPermutation()[randomInRange] = entry.getKey();
            }
        }

        return baby;
    }

}
