/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss;

import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.Job;
import java.util.List;

/**
 *
 * @author Marica
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Genetic Algorithm");
        
        int[] gg = InputManager.getInstance().generateJobTypeRandomBase();
        for (int i : gg) {
            System.out.print(i+" ");
        }
        
        List<Job> jobs = InputManager.getInstance().generatesJobs();
        for (Job job : jobs) {
            System.out.println(job);
        }
    }
   
}
