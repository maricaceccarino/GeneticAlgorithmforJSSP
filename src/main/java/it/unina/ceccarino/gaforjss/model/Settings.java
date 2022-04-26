/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import it.unina.ceccarino.gaforjss.exceptions.InvalidSettingsException;

/**
 *
 * @author Marica
 */
public class Settings {

    private static Settings _instance = null;
    //percentuale di individui per ciclo esenti da manipolazione genetica. 
    private int ELECTED_PERCENTAGE = 5;
    private int CROSSOVER_SUBGROUP_SIZE = 20;
    private int MUTATION_SUBGROUP_SIZE = 20;
    public static final int LIMIT = 25; // AGGIUNGI COMMENTO PLZ
    private int POPULATION_SIZE = 100;

    public static Settings getInstance() {
        if (_instance == null) {
            _instance = new Settings();
        }
        return _instance;

    }

    private Settings() {
        super();
    }

    public void backToDefault() {
        this.ELECTED_PERCENTAGE = 5;
        this.CROSSOVER_SUBGROUP_SIZE = 20;
        this.MUTATION_SUBGROUP_SIZE = 20;
        this.POPULATION_SIZE = 100;
    }

    public int getMutationSubgroupSize() {
        return MUTATION_SUBGROUP_SIZE;
    }

    public void setMutationSubgroupSize(int mutationSubgroupSize) {
        this.MUTATION_SUBGROUP_SIZE = mutationSubgroupSize;
    }
    
    public int getCrossoverSubgroupSize() {
        return CROSSOVER_SUBGROUP_SIZE;
    }

    public void setCrossoverSubgroupSize(int crossoverSubgroupSize) {
        this.CROSSOVER_SUBGROUP_SIZE = crossoverSubgroupSize;
    }
    
    
    public int getPopulationSize(){
        return this.POPULATION_SIZE;
    }
    
    public void setPopulationSize(int size){
        this.POPULATION_SIZE = size;
    }
    
    /**
     * Ritorna la percentuale di individui che non subiranno il crossover
     *
     * @return
     */
    public int getElectedPercentage() {
        return ELECTED_PERCENTAGE;
    }

    public void setElectedPercentage(int electedPercentage) throws InvalidSettingsException {
        if (electedPercentage < 0 || electedPercentage > 100) {
            throw new InvalidSettingsException("La percentuale di immuni al crossover deve essere un numero compreso tra 0 e 100");

        }
        this.ELECTED_PERCENTAGE = electedPercentage;
    }

}
