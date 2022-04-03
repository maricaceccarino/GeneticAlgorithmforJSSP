/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import it.unina.ceccarino.gaforjss.exceptions.InvalidSettingsException;

/**
 *
 * @author Marica
 */
public class Settings {

    private static Settings _instance = null;
    private int ELECTED_PERCENTAGE;

    public static Settings getInstance() {
        if (_instance == null) {
            _instance = new Settings();
        }
        return _instance;

    }

    private Settings() {
        super();
    }
    
    public void backToDefault(){
        this.ELECTED_PERCENTAGE = 5;
    }

    /**
     * Ritorna la percentuale di individui che non subiranno il crossover
     * @return 
     */
    public int getElectedPercentage() {
        return ELECTED_PERCENTAGE;
    }

    public void setElectedPercentage(int electedPercentage) throws InvalidSettingsException {
        if(electedPercentage < 0 || electedPercentage > 100){
            throw new InvalidSettingsException("La percentuale di immuni al crossover deve essere un numero compreso tra 0 e 100");
                    
        }
        this.ELECTED_PERCENTAGE = electedPercentage;
    }
    
    
    
    

}
