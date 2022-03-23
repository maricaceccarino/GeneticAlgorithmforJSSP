/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import java.util.Arrays;

/**
 *
 * @author Marica
 */
public class GeneticManipulator {
    
    private static GeneticManipulator _instance = null;
    private SelectionStrategy selectionStrategy = SelectionStrategy.BEST;
    private float subGroupPercentage = 10f;
    private Population population;
    
    public static GeneticManipulator getInstance() {
        if (_instance == null) {
            _instance = new GeneticManipulator();
        }
        return _instance;
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
            return (int)(this.population.getIndividuals().length * this.subGroupPercentage)/100;
        }
    }
    
    public void loadInitialPopulation(Population population){
        this.population = population;
        Arrays.sort(this.population.getIndividuals());
    }
    
    public void mutate(int howManyTimes){
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

    public float getSubGroupPercentage() {
        return subGroupPercentage;
    }

    public void setSubGroupPercentage(float subGroupPercentage) {
        this.subGroupPercentage = subGroupPercentage;
    }

    
    
}
