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
    
    private int type;
    private MachineStep [] sequence = new MachineStep[InputManager.SEQUENCE_SIZE];

    public JobType(int type, MachineStep [] sequence) {
        this.type = type;
        this.sequence = Arrays.copyOf(sequence, sequence.length);
    }

    public int getType() {
        return type;
    }

    public MachineStep[] getSequence() {
        return Arrays.copyOf(sequence, sequence.length);
    }
    
    
    
    
    
    
    
    
    
    
}
