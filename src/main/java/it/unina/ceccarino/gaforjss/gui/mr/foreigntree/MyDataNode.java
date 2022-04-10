/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.mr.foreigntree;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sommovir
 */
public class MyDataNode {
 
    private String name;
    private String capital;
    private Date declared;
    private Integer area;
 
    private List<MyDataNode> children;
 
    public MyDataNode(String name, String capital, Date declared, Integer area, List<MyDataNode> children) {
        this.name = name;
        this.capital = capital;
        this.declared = declared;
        this.area = area;
        this.children = children;
 
        if (this.children == null) {
            this.children = Collections.emptyList();
        }
    }
 
    public String getName() {
        return name;
    }
 
    public String getCapital() {
        return capital;
    }
 
    public Date getDeclared() {
        return declared;
    }
 
    public Integer getArea() {
        return area;
    }
 
    public List<MyDataNode> getChildren() {
        return children;
    }
 
    /**
     * Knotentext vom JTree.
     */
    public String toString() {
        return name;
    }
}