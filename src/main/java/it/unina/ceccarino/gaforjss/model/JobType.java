/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author marica
 */
public class JobType {
    
    private int type;
    private MachineStep [] sequence = new MachineStep[InputManager.SEQUENCE_SIZE];
    private int cnt = 0;
    
    public JobType(int type, MachineStep [] sequence) {
        this.type = type;
        this.sequence = Arrays.copyOf(sequence, sequence.length);
    }

    public int get_cnt(){
        return this.cnt;
    }
    
    public void set_cnt(int q){
        this.cnt = this.cnt + q;
    }
    public int getType() {
        return type;
    }
    public void setType(int cc){
        this.type = cc;
    }

    public MachineStep[] getSequence() {
        return Arrays.copyOf(sequence, sequence.length);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.type;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JobType other = (JobType) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
}
