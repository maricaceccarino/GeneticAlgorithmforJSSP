/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss;

import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.JobType;
import it.unina.ceccarino.gaforjss.model.Machine;
import java.util.Map;

/**
 *
 * @author Marica
 */
public class Main {

    public static int maxGenerations = 100; // massimo num. di iterazioni per la terminazione

    public static void main(String[] args) {
        System.out.println("Welcome to Genetic Algorithm");
        
        
        Map<Integer, Integer> map = InputManager.getInstance().getJobQuantityMap();
        for (Integer jobType : map.keySet()) {
            System.out.println("JOB["+jobType+"] quantity: "+map.get(jobType));
        }
        System.out.println("In totale abbiamo: "+InputManager.JOB_TOTAL_QUANTITY);
        
        System.out.println("-------------------------------------------------");
        
        JobIndividual individuo = InputManager.getInstance().generateJobIndividual();
        System.out.println(" ** JOB PERMUTATION ARRAY **");
        for (int type : individuo.getJobPermutation()) {
            System.out.print(type == 10 ? "X " : type+" ");
        }
        System.out.println("-------------------------------------------------");
        System.out.println(" ** JOB OPERATION SEQUENCE **");
        for (int type : individuo.getOperationSequence()) {
            System.out.print(type+" ");
        }
        
        System.out.println("-------------------------------------------------");
        System.out.println(" ** MACHINE SEQUENCE **");
        for (Machine machine : individuo.getMachinesSelected()) {
            System.out.print(machine.getCode()+" ");
        }
        
        
        
//        JobIndividual individual = InputManager.getInstance().generateJobIndividual();
//        JobType[] jobIndividual = individual.getJobPermutation();
//        System.out.println("JOB PERMUTATION");
//        for (JobType i : jobIndividual) {
//            System.out.print(i+" ");
//        }
//        System.out.println("\n\n------------------------------");
//        
//        int[] jobOperationSequence = individual.getOperationSequence();
//        System.out.println("JOB OPERATION SEQUENCE");
//        for (int i : jobOperationSequence) {
//            System.out.print(i+" ");
//        }
//        System.out.println("------------------------------");
//        
//        
//        
        if(true){
            return;
        }
        
        
        //        
        //        int[] gg = InputManager.getInstance().generateJobTypeRandomBase();
        //        for (int i : gg) {
        //            System.out.print(i+" ");
        //        }
        //        
        //        List<Job> jobs = InputManager.getInstance().generatesJobs();
        //        for (Job job : jobs) {
        //            System.out.println(job);
        //        }

        int alpha = 100000; //alpha per la prob di guasto
        int MTBF = 6; //tempo medio tra due guasti
        double tg = 5; //tempo medio di recupero a guasto
        double tr = 2; //tempo di ricarica

        /* Calcoliamo il vettore dei tempi di processamento in modo casuale
		 
		int [ ] tp = new int [10]; //vettore tempi di processamento
		for (int i=0; i<10; i++) {
			tp[i] = ((int) ((Math.random()*100)%2)+1); } //genero un vettore di lunghezza 10 con valori tra 1 e 2
		
         */
        double[] tp = {0.5, 0.7, 1, 1.25, 1.3, 1.5};
        double[] w = {1, 2, 2, 2, 1.5, 2};

        /*Definiamo un vettore per peso di ogni job
		double [] w = new double [10];
		int w1=0;
		for (int i=0; i<10; i++) {
			w1=(int) ((Math.random()*100%3));
			switch (w1) {
			case 0: w[i]=1;
					break;
			case 1: w[i]=1.5;
					break;
			case 2: w[i]=2;
					break;
			}
		}*/
// Creiamo l'oggetto ga
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.95, 0);

// Inizializziamo la popolazione specificando la lunghezza dei cromosomi
        Population population = ga.initPopulation(6);

// Valutiamo la popolazione
        ga.evalPopulation(population, tp, w, alpha, MTBF, tg, tr);

        int generation = 1;

        while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {

// Stampiamo l'individuo più in forma 
            Individual fittest = population.getFittest(0);
            System.out.println(
                    "G" + generation + " Best solution (" + fittest.getFitness() + ")");

// Applichiamo il crossover
            population = ga.crossoverPopulation(population);

// Applichiamo la mutazione
            population = ga.mutatePopulation(population);

// Valutiamo la popolazione
            ga.evalPopulation(population, tp, w, alpha, MTBF, tg, tr);

            generation++;
        }

        System.out.println("Stopped after " + maxGenerations + " generations.");
        {
            Individual fittest = population.getFittest(0);
            System.out.println("La fitness è " + fittest.getFitness());
        }

        System.out.println("La schedulazione è: " + population.getFittest(0));

        double tempodicompletamento = ga.tempodicompletamento(population, tp, tr);
        System.out.println("Cmax è: " + tempodicompletamento);

        System.out.print("tp è: ");
        for (int i = 0; i < 6; i++) {
            System.out.print(tp[i] + " ");
        }

    }

}
