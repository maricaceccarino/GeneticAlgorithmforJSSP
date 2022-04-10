/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.abstracts.tree;

import it.unina.ceccarino.gaforjss.model.JobIndividual;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sommovir
 */
public class DataNode implements TreeData<DataNode>{
    
    private Object [] data;
    private List<DataNode> children = new LinkedList<>();
    private String label;

    public DataNode(Object [] data) {
        this.data = data;
    }

    public DataNode(String label) {
        this.label = label;
    }

    public DataNode(Object[] data, String label) {
        this.data = data;
        this.label = label;
    }
    
    

    public String getLabel() {
        return label;
    }

    public Object[] getData() {
        return data;
    }

    
    public void addChild(Object[] data){
        this.children.add(new DataNode(data));
    }
    
     public void addChild(DataNode data){
        this.children.add(data);
    }

    @Override
    public List<DataNode> getChildren() {
       return this.children;
    }

    @Override
    public String toString() {
        return this.label;
    }
    
    
    
}
