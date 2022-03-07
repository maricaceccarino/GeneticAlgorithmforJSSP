/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

/**
 *
 * @author Marica
 */
public class MachineStep {
    
    private Machine machine;
    private int time;

    public MachineStep(Machine machine, int time) {
        this.machine = machine;
        this.time = time;
    }

    public Machine getMachine() {
        return machine;
    }
    
    public Machine getRandomMachine(){
        if(this.machine.getAlternativeCode() == null){
            return this.machine;
        }else{
            Machine other = Machine.of(this.machine.getAlternativeCode());
            int randomino = Utils.randomInRange(0, 100);
            if(randomino >= 50){
                return other;
            }else{
                return this.machine;
            }
        }
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    
    
}
