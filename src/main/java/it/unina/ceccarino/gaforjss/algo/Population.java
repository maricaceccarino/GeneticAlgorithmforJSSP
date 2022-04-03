/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.Settings;
import java.util.List;

/**
 *
 * @author Marica
 */
public class Population {
    
    private int size;
    
    private JobIndividual [] individuals;
    // dopo aver ordinato la popolazione l'elected index rappresenta l'indice
    //dell'ultimo elemento eletto nella popolazione;
    private int electedIndex;

    public Population(int size) {
        this.size = size;
         individuals = new JobIndividual[size];
        for (int i = 0; i < size; i++) {
            individuals[i] = InputManager.getInstance().generateJobIndividual();
        }
    }

    public JobIndividual[] getIndividuals() {
        return individuals;
    }

    public int getElectedIndex() {
        return electedIndex;
    }
    
    
    public void mutate(){
        //estraggo la corrente percentuale di eletti che non subiranno il crossover
        int electedPercentage = Settings.getInstance().getElectedPercentage();
        
        //calcolo l'elected Index
        
        //x : size = elected : 100   -> size*elected / 100
        
        this.electedIndex = (this.size*electedPercentage / 100) -1;
        
        
        
    }
    
    
    
    
    
    
}
