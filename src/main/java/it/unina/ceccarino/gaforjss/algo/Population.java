/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import it.unina.ceccarino.gaforjss.exceptions.InvalidSettingsException;
import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.JobType;
import it.unina.ceccarino.gaforjss.model.Settings;
import it.unina.ceccarino.gaforjss.model.Utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marica
 */
public class Population {

    private int size;

    private JobIndividual[] individuals;
    // dopo aver ordinato la popolazione l'elected index rappresenta l'indice
    //dell'ultimo elemento eletto nella popolazione;
    private int electedIndex;
    private JobIndividual[] boys = null;
    private JobIndividual[] girls = null;

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

   

//    public static void main(String[] args) {
//        try {
//            //test output
//            int array[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
//            
//            int[] boys = Arrays.copyOfRange(array, 0, array.length / 2);
//            int[] girls = Arrays.copyOfRange(array, array.length / 2, array.length);
//            
//            for (int i = 0; i < boys.length; i++) {
//                System.out.println("BOY:  " + boys[i]);
//            }
//            System.out.println("---------------------------------------------------------");
//            for (int i = 0; i < girls.length; i++) {
//                System.out.println("GIRL: " + girls[i]);
//            }
//            
//            System.out.println("---------------------------------------------------------");
//            
//            Settings.getInstance().setElectedPercentage(0);
//            Population pop = new Population(100);
//            pop.prepareCrossover();
//            int electedIndex = pop.getElectedIndex();
//            assertEquals(-1, electedIndex, "errore nel calcolo dell'elected index con % a 0");
//        } catch (InvalidSettingsException ex) {
//            Logger.getLogger(Population.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        
//        try {
//            Settings.getInstance().setElectedPercentage(0);
//            Population pop = new Population(100);
//            pop.prepareCrossover();
//            int electedIndex = pop.getElectedIndex();
////            assertEquals(-1, electedIndex, "errore nel calcolo dell'elected index con % a 0");
//        } catch (InvalidSettingsException ex) {
////            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
//        }
//    }

}
