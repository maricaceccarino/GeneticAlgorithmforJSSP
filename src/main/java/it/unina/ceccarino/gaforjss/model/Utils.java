/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

import java.util.Random;

/**
 *
 * @author Luca
 */
public class Utils {
    /**
     * Costruzione di un metodo per la generazione randomica di ordini Regolari
     * e Irregolari
     * Ogni job ha una percentuale di probabilità di essere generato,
     * per gli ordini irregolari questa è sempre del 5%,mentre per gli 
     * ordini regolari,questa dipende dal tipo di job.
     *
     * @param percentage la soglia che se viene superata dal numero random, il
     * metodo restituirà false
     * @return Restituisce true, se viene generato un numero casuale minore di
     * percentage, false viceversa
     */
    public static boolean randomInPercentage(int percentage) {
        Random random = new Random();
        float randomInt = random.nextInt(100);
        return randomInt <= percentage;
    }

    /**
     * Restituisce un numero intero casuale tra min e max .
     *
     * @param min
     * il valore minimo entro il quale sarà generato il numero casuale
     * @param max
     * il valore massimo entro il quale sarà generato il numero casuale
     * @return un numero intero casuale tra min e max
     */
    public static int randomInRange(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

}
