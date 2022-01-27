/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author marica
 */
public class Population {

    private Individual population[];
    private double populationFitness = -1;

    public Population(int populationSize) {
        // Initial population
        this.population = new Individual[populationSize];
    }

//Inizializziamo una popolazione di individui
    public Population(int populationSize, int chromosomeLength) {

        this.population = new Individual[populationSize];

//Creiamo ogni individuo inizializzando il suo cromosoma alla lunghezza data 
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {

            Individual individual = new Individual(chromosomeLength);

//Aggiungiamo ogni individuo alla popolazione 
            this.population[individualCount] = individual;
        }
    }

//Otteniamo gli individui dalla popolazione 	/**
    public Individual[] getIndividuals() {
        return this.population;
    }

    /*Cerchiamo un individuo nella popolazione attraverso la sua fitness, ordinando la popolazione 
*  attraverso la fitness e facciamoci restituire l'individuo più adatto (fittest)
     */
    public Individual getFittest(int offset) {

        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });

        return this.population[offset];

    }

//Impostiamo e otteniamo la fitness della popolazione 
    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    public double getPopulationFitness() {
        return this.populationFitness;
    }

//Otteniamo la dimensione della popolazione 
    public int size() {
        return this.population.length;
    }

//Impostiamo e otteniamo un individuo all'offset
    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

    public Individual getIndividual(int offset) {
        return population[offset];
    }

//Mescoliamo la popolazione 
    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individual a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }

}
