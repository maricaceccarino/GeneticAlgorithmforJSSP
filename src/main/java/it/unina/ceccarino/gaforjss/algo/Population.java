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

    /**
     * crossover
     */
    public void prepareCrossover() {
        //estraggo la corrente percentuale di eletti che non subiranno il crossover
        int electedPercentage = Settings.getInstance().getElectedPercentage();

        //calcolo l'elected Index
        //x : size = elected : 100   -> size*elected / 100
        this.electedIndex = (this.size * electedPercentage / 100) - 1;

        Arrays.sort(this.individuals);

        //verifico se i rimanenti sono pari o dispari, se dispari aumento di uno
        //gli eletti di modo che poi posso dividere i rimanenti in due insiemi
        //esatti.
        //La seguente variabile sarà quindi zero nel caso i rimanenti sono di 
        //numero pari, 1 viceversa. 
        int ghostElected = (this.size - this.electedIndex + 1) % 2 == 0 ? 0 : 1;

        //Copio i superstiti in questo array "crossoverCandidates"
        JobIndividual[] crossoverCandidates = Arrays.copyOfRange(this.individuals, this.electedIndex + ghostElected +1, size);

        //Mescolo l'array casualmente
        Collections.shuffle(Arrays.asList(crossoverCandidates));

        boys = Arrays.copyOfRange(crossoverCandidates, 0, crossoverCandidates.length / 2);
        girls = Arrays.copyOfRange(crossoverCandidates, crossoverCandidates.length / 2, crossoverCandidates.length);

    }

    public JobIndividual[] getBoys() {
        return boys;
    }

    public JobIndividual[] getGirls() {
        return girls;
    }

    public JobIndividual crossOver(JobIndividual mister, JobIndividual miss) {

        JobIndividual baby = new JobIndividual();
        List<Integer> posizioniVuote = new LinkedList<>();
        Map<Integer, Integer> posizioniOccupateMap = new HashMap<>();
        //  id job ,  quantity
        for (JobType jobType : InputManager.getInstance().getJobTypes()) {
            posizioniOccupateMap.put(jobType.getType(), 0);
        }

        for (int i = 0; i < mister.getJobPermutation().length; i++) {
            if (mister.getJobPermutation()[i] <= 5) {
                baby.getJobPermutation()[i] = mister.getJobPermutation()[i];
                posizioniOccupateMap.put(mister.getJobPermutation()[i], posizioniOccupateMap.get(mister.getJobPermutation()[i]) + 1 );
            } else if(miss.getJobPermutation()[i] > 5){
                baby.getJobPermutation()[i] = miss.getJobPermutation()[i];
                posizioniOccupateMap.put(miss.getJobPermutation()[i], posizioniOccupateMap.get(miss.getJobPermutation()[i]) + 1 );
            }else{
                //posizioni vuote
                posizioniVuote.add(i);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : posizioniOccupateMap.entrySet()) {
            int jobLimit = InputManager.getInstance().getJobQuantityMap().get(entry.getKey());
            int jobMancanti = jobLimit - entry.getValue();
            for (int i = 0; i < jobMancanti; i++) {
                
                int randomInRange = Utils.randomInRange(0, posizioniVuote.size());
                posizioniVuote.remove(randomInRange);
                baby.getJobPermutation()[randomInRange] = entry.getKey();
            }
        }
        
        return baby;
    }

    public static void main(String[] args) {
        //test output
        int array[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        int[] boys = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] girls = Arrays.copyOfRange(array, array.length / 2, array.length);

        for (int i = 0; i < boys.length; i++) {
            System.out.println("BOY:  " + boys[i]);
        }
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < girls.length; i++) {
            System.out.println("GIRL: " + girls[i]);
        }

        
        try {
            Settings.getInstance().setElectedPercentage(0);
            Population pop = new Population(100);
            pop.prepareCrossover();
            int electedIndex = pop.getElectedIndex();
//            assertEquals(-1, electedIndex, "errore nel calcolo dell'elected index con % a 0");
        } catch (InvalidSettingsException ex) {
//            assertTrue(false, "Non dovrebbe lanciare eccezione con parametri tra 0 e 100");
        }
    }

}
