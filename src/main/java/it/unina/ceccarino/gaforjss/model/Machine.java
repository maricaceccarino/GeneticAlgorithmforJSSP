/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.model;

/**
 *Sono definite le 15 macchine totali,alcune di queste sono della stessa 
 * tipologia, infatti le macchine A,B,C ,J e I sono 2,anzichè una sola.
 * @author sommovir
 */
public enum Machine {
    A(false),
    B(false),
    C(false),
    D(true),
    E(true),
    F(true),
    G(true),
    H(true),
    I(false),
    J(false);

    private Machine(boolean single) {
        this.single = single;
    }
    
    private boolean single = false;

    public boolean isSingle() {
        return single;
    }
    
    
}
