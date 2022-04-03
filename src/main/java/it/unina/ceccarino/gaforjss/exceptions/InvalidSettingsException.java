/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.exceptions;

/**
 *
 * @author marica
 */
public class InvalidSettingsException extends Exception {
    
    private String detail;

    public InvalidSettingsException(String detail) {
        super("Errore nelle impostazioni di setting");
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
    
    

}
