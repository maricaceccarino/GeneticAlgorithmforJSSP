/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import it.unina.ceccarino.gaforjss.exceptions.GeneticPoolNotLoadedException;
import it.unina.ceccarino.gaforjss.exceptions.NotSortedPopulationException;
import it.unina.ceccarino.gaforjss.model.Settings;
import java.util.Arrays;

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
    public void launch() throws Exception{
        if(this.population == null){
            throw new GeneticPoolNotLoadedException();
        }
        if(!sorted){
            throw new NotSortedPopulationException();
        }
    }
    
    /**
     * restituisce il numero di individui selezionati per la mutazione
     * @return 
     */
    public int getAffectedIndividuals(){
        if(population == null || this.population.getIndividuals().length == 0){
            return 0;
        }else{
            //tot : 100 = x : perc   ->> x = (tot*perc)/100
            return (int)(this.population.getIndividuals().length * Settings.getInstance().getElectedPercentage())/100;
        }
    }
    
    public void loadInitialPopulation(Population population){
        this.population = population;
        Arrays.sort(this.population.getIndividuals());
        sorted = true;
    }
    
    public void mutate(int howManyTimes){
        //randomizzare-- 
        //vanno mutati coloro che non hanno subito il crossover
        for (int i = 0; i < getAffectedIndividuals(); i++) {
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

    
    
}
