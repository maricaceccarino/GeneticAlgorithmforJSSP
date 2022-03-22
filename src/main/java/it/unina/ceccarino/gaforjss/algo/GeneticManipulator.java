/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import java.util.Arrays;

/**
 *
 * @author Luca
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
    
    
    public void loadInitialPopulation(Population population){
        this.population = population;
        Arrays.sort(this.population.getIndividuals());
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
