/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.exceptions;

/**
 *
 * @author Luca
 */
public class GeneticPoolNotLoadedException extends Exception {

    public GeneticPoolNotLoadedException() {
        super("you may have called the -launch- method without properly loading "
                + "the population.\n. You have to call 'loadInitialPopulation' "
                + "before, and passing a valid population that can be generated "
                + "by InputManager");
    }

}
