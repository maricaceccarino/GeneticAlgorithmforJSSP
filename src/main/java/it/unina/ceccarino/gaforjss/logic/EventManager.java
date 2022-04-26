/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.logic;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sommovir
 */
public class EventManager {

    private static EventManager _instance = null;
    private List<EventListener> listeners = new LinkedList<>();

    public static EventManager getInstance() {
        if (_instance == null) {
            _instance = new EventManager();
        }
        return _instance;
    }

    private EventManager() {
        super();
    }

    public void addEventListener(EventListener listener) {
        this.listeners.add(listener);
    }

    public void generationStarted() {
        for (EventListener listener : listeners) {
            listener.generationStarted();
        }
    }

    public void generationEnded() {
        for (EventListener listener : listeners) {
            listener.generationEnded();
        }
    }

}
