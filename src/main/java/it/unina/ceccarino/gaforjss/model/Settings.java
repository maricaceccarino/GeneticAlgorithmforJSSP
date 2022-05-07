/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import it.unina.ceccarino.gaforjss.exceptions.InvalidSettingsException;
import it.unina.ceccarino.gaforjss.logic.EventManager;

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
    private int MAX_JOB_OVERAL_QUANTITY = 25; // AGGIUNGI COMMENTO PLZ
    private int POPULATION_SIZE = 100;
    private int MAX_ITERATION = 300;
    private boolean verbose = false;
    private boolean kalergi = false;
    private int kalergi_each = 50; //numero di turni senza alcun miglioramento
    private int kalergi_injection_rate = 10; //percentuale di individui esterni rigenerati. 
    private boolean needResetPopulation = true;

    public static Settings getInstance() {
        if (_instance == null) {
            _instance = new Settings();
        }
        return _instance;

    }

    private Settings() {
        super();
    }

    public boolean isNeedResetPopulation() {
        return needResetPopulation;
    }

    public void setNeedResetPopulation(boolean needResetPopulation) {
        this.needResetPopulation = needResetPopulation;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public boolean isKalergi() {
        return kalergi;
    }

    public void setKalergi(boolean kalergi) {
        this.kalergi = kalergi;
    }

    public int getKalergiEach() {
        return kalergi_each;
    }

    public void setKalergiEach(int kalergi_each) {
        this.kalergi_each = kalergi_each;
    }

    public int getKalergiInjectionRate() {
        return kalergi_injection_rate;
    }

    public void setKalergiInjectionRate(int kalergi_injection_rate) {
        this.kalergi_injection_rate = kalergi_injection_rate;
    }
    
    
    
    

    public void backToDefault() {
        this.ELECTED_PERCENTAGE = 5;
        this.CROSSOVER_SUBGROUP_SIZE = 20;
        this.MUTATION_SUBGROUP_SIZE = 20;
        this.MAX_JOB_OVERAL_QUANTITY = 25;
        this.POPULATION_SIZE = 100;
        this.MAX_ITERATION = 300;
        EventManager.getInstance().backToDefault();
    }
    
    public int getMaxIteration(){
        return MAX_ITERATION;
    }
    
    public void setMaxIteration(int max){
        this.MAX_ITERATION = max;
    }
    
    public int getMaxJobOveralQuantity(){
        return MAX_JOB_OVERAL_QUANTITY;
    }
    
    public void setMaxJobOveralQuantity(int maxJobOveralQuantity){
        if(maxJobOveralQuantity < 10){
            throw new IllegalArgumentException("L'input non può essere minore di 10");
        }
        this.MAX_JOB_OVERAL_QUANTITY = maxJobOveralQuantity;
        InputManager.getInstance().randomizeJobQuantity();
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
