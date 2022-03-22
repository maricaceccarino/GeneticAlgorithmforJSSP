/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import it.unina.ceccarino.gaforjss.model.InputManager;
import static it.unina.ceccarino.gaforjss.model.InputManager.POPULATION_SIZE;
import it.unina.ceccarino.gaforjss.model.JobIndividual;

/**
 *
 * @author Luca
 */
public class Population {
    
    private int size;
    
    private JobIndividual [] individuals;

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
    
    
    
    
    
    
}
