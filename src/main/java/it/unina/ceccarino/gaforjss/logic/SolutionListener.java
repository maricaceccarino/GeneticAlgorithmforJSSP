/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.logic;

import it.unina.ceccarino.gaforjss.model.JobIndividual;

/**
 *
 * @author sommovir
 */
public interface SolutionListener {
    
    public void start(int initialFitness);
    
    public void end(JobIndividual bestone);
    
    public void newImprovement(int newFitness);
    
    public void nextCycle(int cycle);
    
    public void newAVG(int avg);
    
}
