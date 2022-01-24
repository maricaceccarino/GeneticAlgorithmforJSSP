/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author marica
 */
public class JobType {
    
    private int generationProbability;
    private int type;
    private MachineSequence [] sequence = new MachineSequence[InputManager.SEQUENCE_SIZE];

    public JobType(int generationProbability, int type, MachineSequence [] sequence) {
        this.generationProbability = generationProbability;
        this.type = type;
        this.sequence = Arrays.copyOf(sequence, sequence.length);
    }

    public int getGenerationProbability() {
        return generationProbability;
    }

    public int getType() {
        return type;
    }

    public MachineSequence[] getSequence() {
        return Arrays.copyOf(sequence, sequence.length);
    }
    
    
    
    
    
    
    
    
    
    
}
