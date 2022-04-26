/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.logic;

/**
 *
 * @author sommovir
 */
public interface EventListener {
    
    public void generationStarted();
    
    public void generationEnded();
    
    public void algoStart();
    
    public void solutionFound();
    
}
