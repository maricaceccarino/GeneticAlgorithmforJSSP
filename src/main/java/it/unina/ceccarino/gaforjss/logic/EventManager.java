/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.logic;

import it.unina.ceccarino.gaforjss.model.JobIndividual;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sommovir
 */
public class EventManager {

    private static EventManager _instance = null;
    private List<EventListener> listeners = new LinkedList<>();
    private List<SolutionListener> solutionListeners = new LinkedList<SolutionListener>();

    public static EventManager getInstance() {
        if (_instance == null) {
            _instance = new EventManager();
        }
        return _instance;
    }

    private EventManager() {
        super();
    }

    public void addSolutionListener(SolutionListener listener) {
        this.solutionListeners.add(listener);
    }

    public void addEventListener(EventListener listener) {
        this.listeners.add(listener);
    }

    public void startsSimulation(int initialFitness) {
        for (SolutionListener listener : solutionListeners) {
            listener.start(initialFitness);
        }
    }

    public void end(JobIndividual bestone) {
        for (SolutionListener listener : solutionListeners) {
            listener.end(bestone);
        }
    }

    public void newImprovement(int newFitness) {
        for (SolutionListener listener : solutionListeners) {
            listener.newImprovement(newFitness);
        }
    }

    public void nextCycle(int cycle) {
        for (SolutionListener listener : solutionListeners) {
            listener.nextCycle(cycle);
        }
    }

    public void settingsChanged() {
        for (EventListener listener : listeners) {
            listener.settingsChanged();
        }
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

    public void backToDefault() {
        for (EventListener listener : listeners) {
            listener.backToDefault();
        }
    }

}
