/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.panels;

import it.cnr.istc.icv.engine.EmbeddablePanel;
import it.cnr.istc.icv.engine.MixedDataPanel;
import it.cnr.istc.icv.engine.MyJLayer;
import it.cnr.istc.icv.engine.MyLayer;
import it.cnr.istc.icv.engine.ZoomLabeledLayer;
import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.test.LinearDataSupporter;
import it.cnr.istc.icv.test.TimeValueSupporterClass;
import it.unina.ceccarino.gaforjss.logic.EventManager;
import it.unina.ceccarino.gaforjss.logic.SolutionListener;
import it.unina.ceccarino.gaforjss.model.Settings;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author sommovir
 */
public class SolutionPanel extends javax.swing.JPanel implements SolutionListener {

    private int startFitness;
    private int currentFitness;
    final static EmbeddablePanel panel = new EmbeddablePanel();
    private int x = 0;

    /**
     * Creates new form SolutionPanel
     */
    public SolutionPanel() {
        initComponents();
        EventManager.getInstance().addSolutionListener(this);

        panel.getMixedPanel().setStartRange(0);
        panel.getMixedPanel().setEndRange(Settings.getInstance().getMaxIteration());
        panel.getMixedPanel().setShowDate(false);
        panel.getMixedPanel().setBackground(Color.WHITE);
        panel.getMixedPanel().setZoomEnable(true);
        panel.setXTooltipLabel("Cicli");
        panel.setYTooltipLabel("Fitness");

        LinearDataSupporter s = new LinearDataSupporter("Soluzione");
//        s.setOrder(1);
        s.setDiscret(false);
//        s.setMaxValueToShow(10);
//        s.setMinValueToShow(0);

        panel.getMixedPanel().addDataBar(s);
//        MyLayer<JPanel> layerUI = new ZoomLabeledLayer(panel);
//        JPanel containerP = new JPanel();
//        containerP.setLayout(new GridLayout(0, 1));
//        containerP.add(panel);
//
//        MyJLayer<JPanel> jlayer = new MyJLayer<JPanel>(panel, layerUI);
//        containerP.add(jlayer);
        this.jScrollPane1.setViewportView(panel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_Iterazioni = new javax.swing.JLabel();
        jLabel_startFitness = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_currentFitness = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setText("Iterazioni:");

        jLabel2.setText("Start Fitness");

        jLabel_Iterazioni.setBackground(new java.awt.Color(51, 51, 51));
        jLabel_Iterazioni.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel_Iterazioni.setForeground(new java.awt.Color(255, 255, 0));
        jLabel_Iterazioni.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Iterazioni.setText("1/300");
        jLabel_Iterazioni.setOpaque(true);

        jLabel_startFitness.setBackground(new java.awt.Color(51, 51, 51));
        jLabel_startFitness.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel_startFitness.setForeground(new java.awt.Color(255, 255, 0));
        jLabel_startFitness.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_startFitness.setText("143");
        jLabel_startFitness.setOpaque(true);

        jLabel5.setText("Current Fitness");

        jLabel_currentFitness.setBackground(new java.awt.Color(51, 51, 51));
        jLabel_currentFitness.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel_currentFitness.setForeground(new java.awt.Color(51, 204, 0));
        jLabel_currentFitness.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_currentFitness.setText("143");
        jLabel_currentFitness.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Iterazioni, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_startFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_currentFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel_currentFitness))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel_Iterazioni)
                        .addComponent(jLabel_startFitness)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(56, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_Iterazioni;
    private javax.swing.JLabel jLabel_currentFitness;
    private javax.swing.JLabel jLabel_startFitness;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void start(int initialFitness) {
        this.startFitness = initialFitness;
        this.x = 0;
        panel.getMixedPanel().setEndRange(Settings.getInstance().getMaxIteration());
        this.jLabel_Iterazioni.setText("0/" + Settings.getInstance().getMaxIteration() + " ");
        this.jLabel_startFitness.setText("" + initialFitness + " ");
        this.jLabel_currentFitness.setText("<html><font color = red>" + initialFitness + "</font>");
        panel.getMixedPanel().clearLinearDataBar("Soluzione");
        TimeValueSupporterClass ds1 = new TimeValueSupporterClass(initialFitness, "Soluzione", new Date(x));
        try {
            panel.getMixedPanel().addLinearData("Soluzione", ds1, true);
        } catch (TypeDataMismatchException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void end() {
    }

    @Override
    public void newImprovement(int newFitness) {
        this.currentFitness = newFitness;
        this.jLabel_currentFitness.setText("" + newFitness + " ");
        TimeValueSupporterClass ds1 = new TimeValueSupporterClass(newFitness, "Soluzione", new Date(x));
        try {
            panel.getMixedPanel().addLinearData("Soluzione", ds1, true);
        } catch (TypeDataMismatchException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void nextCycle(int cycle) {
        this.jLabel_Iterazioni.setText(cycle + "/" + Settings.getInstance().getMaxIteration() + " ");
        x = cycle;
        
    }
}
