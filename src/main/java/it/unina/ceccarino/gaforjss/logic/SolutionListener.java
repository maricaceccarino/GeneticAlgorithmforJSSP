/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.logic;

/**
 *
 * @author sommovir
 */
public interface SolutionListener {
    
    public void start(int initialFitness);
    
    public void end();
    
    public void newImprovement(int newFitness);
    
    public void nextCycle(int cycle);
    
}
