/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import it.unina.ceccarino.gaforjss.exceptions.GeneticPoolNotLoadedException;
import it.unina.ceccarino.gaforjss.exceptions.NotSortedPopulationException;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.Settings;
import java.util.Arrays;
import java.util.Collections;

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
        
        JobIndividual [] immunes  = (JobIndividual[])partArray(this.population.getIndividuals(), getImmuneSize());
        
        JobIndividual [] normalPeople = evict(this.population.getIndividuals(), immunes.length);
        
        Collections.shuffle(Arrays.asList(normalPeople));
        
        JobIndividual [] crossoverPool  = (JobIndividual[])partArray(normalPeople, immunes.length  + getPeopleSizeForCrossoverSize());
        JobIndividual [] mutationPool  = (JobIndividual[])partArray(normalPeople, crossoverPool.length + getPeopleSizeForMutationSize());
        
        
    }

    public JobIndividual[] evict(JobIndividual[] initialPopulation, int sizeToEvict) {

        //estrazione dei primi 'sizeToExtract' elementi dall'array iniziale
        JobIndividual[] remaining = (JobIndividual[])partArray(initialPopulation,sizeToEvict, initialPopulation.length - sizeToEvict);
        
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

    public void mutate(int howManyTimes) {
        //randomizzare-- 
        //vanno mutati coloro che non hanno subito il crossover
        for (int i = 0; i < getPeopleSizeForMutationSize(); i++) {
            this.population.getIndividuals()[i].swap(howManyTimes);
        }
    }

    private GeneticManipulator() {
        super();
    }

    public SelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public Object[] partArray(Object[] array, int size) {
        Object[] part = new Object[size];
        System.arraycopy(array, 0, part, 0, size);
        return part;
    }
    
    public Object[] partArray(Object[] array, int initialPosition, int size) {
        Object[] part = new Object[size];
        System.arraycopy(array, initialPosition, part, 0, size);
        return part;
    }


}
