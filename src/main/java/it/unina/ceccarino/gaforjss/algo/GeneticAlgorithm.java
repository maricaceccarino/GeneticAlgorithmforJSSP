/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.algo;

/**
 
public class GeneticAlgorithm {
    //Nella classe Genetic Algorithm faremo le operazioni principali dell'alg genetico.

//Creiamo la classe GeneticAlgorithm 
    private int populationSize; // dimensione della popolazione 
    private double mutationRate; // tasso di mutazione 
    private double crossoverRate; // tasso di crossover
    private int elitismCount; // elitismo

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }

////Inizializziamo la popolazione 
//    public Population JobPermutation
//
//        Population population = new Population(this.populationSize, vettoreLength);
//        return population;
//    }
//
////Calcoliamo la fitness
//        double fitness = 0;
//        if (tcagv1 > tcagv2) {
//            fitness = tcagv1;
//        } else {
//            fitness = tcagv2;
//        }
//
////Invertiamo la fitness (in modo da averla decrescente)
//        fitness = 1 / fitness;
//
////Memorizziamo la fitness
//        individual.setFitness(fitness);
//
//        return fitness;
//    }

    /*Valutiamo la popolazione attraverso un loop che calcola la fitness per ogni individuo
* e dopo calcola la fitness dell'intera popolazione
//     */
//    public void evalPopulation(Population population, double[] tp, double[] w,
//            int alpha, int MTBF, double tg, double tr) {
//
//        double populationFitness = 0;
//
//        for (Individual individual : population.getIndividuals()) {
//            populationFitness += calcFitness(individual, tp, w, alpha, MTBF, tg, tr);
//        }
//        population.setPopulationFitness(populationFitness);
//    }
//
////Verifichiamo le condizioni di terminazione
//    public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
//        return (generationsCount > maxGenerations);
//
//    }
//
//    /*Facciamo la selezione per il crossover in modo randomico e dopo applichiamo il crossover. 
//* Ogni individuo viene considerato per il crossover e confrontando il tasso di crossover con 
//* un numero casuale decidiamo se all'individuo va applicato il crossover. 
//* Dopo aver selezionato un primo individuo bisogna trovarne un secondo, per la selezione utilizziamo
//* la ruota della roulette.
//     */
//    public Individual selectParent(Population population) {
//
////Otteniamo gli individui 
//        Individual individuals[] = population.getIndividuals();
//
////Giriamo la ruota della roulette
//        double populationFitness = population.getPopulationFitness();
//        double rouletteWheelPosition = Math.random() * populationFitness;
//
////Troviamo il genitore 1 	
//        double spinWheel = 0;
//        for (Individual individual : individuals) {
//            spinWheel += individual.getFitness();
//            if (spinWheel >= rouletteWheelPosition) {
//                return individual;
//            }
//        }
//        return individuals[population.size() - 1];
//    }
//
//    public Population crossoverPopulation(Population population) {
//
////Creiamo una nuova popolazione 
//        Population newPopulation = new Population(population.size());
//
////Facciamo un loop per capire se applicare il crossover all'individuo 1
//        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
//            Individual parent1 = population.getFittest(populationIndex);
//
//            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
//
//                Individual offspring = new Individual(parent1.getVettoreLength());
//
////Cerchiamo il secondo genitore
//                Individual parent2 = selectParent(population);
//
////Facciamo un loop sul genoma
//                for (int iIndex = 0; iIndex < parent1.getVettoreLength(); iIndex++) {
//
////Usiamo metà dei geni del genitore 1 e metà di quelli del genitore 2 
//                    if (0.5 > Math.random()) {
//                        offspring.setI(iIndex, parent1.getI(iIndex));
//                    } else {
//                        offspring.setI(iIndex, parent2.getI(iIndex));
//                    }
//                }
//
////Aggiungiamo la prole alla nuova popolazione
//                newPopulation.setIndividual(populationIndex, offspring);
//            } else {
//
//                newPopulation.setIndividual(populationIndex, parent1);
//            }
//        }
//
//        return newPopulation;
//    }
//
//    /* Applichiamo la mutazione, tenendo conto del tasso di mutazione. 
//* Per la mutazione invertiamo la posizione di due geni usando quella che viene chiamata mutazione
//* di scambio (swap mutation).
//* 
//     */
//    public Population mutatePopulation(Population population) {
//
////Creiamo una nuova popolazione che contenga gli individui mutati
//        Population newPopulation = new Population(this.populationSize);
//
////Facciamo un loop a partire dalle persone più in forma
//        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
//            Individual individual = population.getFittest(populationIndex);
//
////Saltiamo la mutazione se incontriamo individui di elite
//            if (populationIndex >= this.elitismCount) {
//
////Facciamo un loop sui geni di ogni individuo per vedere quali geni devono essere mutati
//                for (int iIndex = 0; iIndex < individual.getVettoreLength(); iIndex++) {
//
//                    if (this.mutationRate > Math.random()) {
////Se un gene deve essere mutato ne viene scelto un altro casuale per lo swap 
//
//                        int newIPos = (int) (Math.random() * individual.getVettoreLength());
//
//                        int i1 = individual.getI(newIPos);
//                        int i2 = individual.getI(iIndex);
//
//                        individual.setI(iIndex, i1);
//                        individual.setI(newIPos, i2);
//                    }
//                }
//            }
//
////Aggiungiamo l'individuo mutato alla popolazione
//            newPopulation.setIndividual(populationIndex, individual);
//        }
//
//        return newPopulation;
//    }
//
//    public double tempodicompletamento(Population population, double[] tp, double tr) {
//
//        int[] Fittest = population.getFittest(0).getvettore();
//
//        int[] primoagv = new int[Fittest.length];
//        for (int i = 0; i < Fittest.length; i++) {
//            if (Fittest[i] != 2) {
//                primoagv[i] = Fittest[i];
//            }
//        }
//
//        int[] secondoagv = new int[Fittest.length];
//        for (int i = 0; i < Fittest.length; i++) {
//            if (Fittest[i] != 1) {
//                secondoagv[i] = Fittest[i];
//            }
//        }
//
//       
//     
//        }
//
//        double tcagv1 = massimo1; //tempo di completamento agv1 
//
//        //AGV2
//        double[] t2 = new double[Fittest.length]; //tempo di complet2 AGV2
//        double cumulata2 = 0;
//        for (int i = 0; i < Fittest.length; i++) {
//            if (secondoagv[i] == 2) {
//                t2[i] = tp[i] + cumulata2;
//                cumulata2 = t2[i];
//            }
//        }
//
//        double massimo2 = t2[0]; //massimo dei tempi di complet2 AGV2
//        for (int i = 0; i < Fittest.length; i++) {
//            if (t2[i] > massimo2) {
//                massimo2 = t2[i];
//            }
//        }
//
//        double tcagv2 = massimo2; //tempo di completamento agv2
//
//        double fitness1 = 0;
//        if (tcagv1 > tcagv2) {
//            fitness1 = tcagv1;
//        } else {
//            fitness1 = tcagv2;
//        }
//
//        return fitness1;
//    }
//
//}
