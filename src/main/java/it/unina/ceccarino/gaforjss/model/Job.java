/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

/**
 *
 * @author Marica
 */
public class Job {
    
    private JobType jobType;
    private int arrivalTime; //da 0 - 100
    private int solutionTime;

    public Job(JobType jobType, int arrivalTime, int solutionTime) {
        this.jobType = jobType;
        this.arrivalTime = arrivalTime;
        this.solutionTime = solutionTime;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getSolutionTime() {
        return solutionTime;
    }

    public void setSolutionTime(int solutionTime) {
        this.solutionTime = solutionTime;
    }

    @Override
    public String toString() {
        return "[J"+this.jobType.getType() + ", "+this.arrivalTime+"]";
    }
    
    
    
}
