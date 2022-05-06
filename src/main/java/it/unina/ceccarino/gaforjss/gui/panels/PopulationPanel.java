/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.panels;

import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import it.unina.ceccarino.gaforjss.algo.Population;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.AbstractTreeTableModel;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.DataModel;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.DataNode;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.TreeTable;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.TreeTableCellRenderer;
import it.unina.ceccarino.gaforjss.logic.EventListener;
import it.unina.ceccarino.gaforjss.logic.EventManager;
import it.unina.ceccarino.gaforjss.logic.SolutionListener;
import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author sommovir
 */
public class PopulationPanel extends javax.swing.JPanel implements SolutionListener, EventListener {

    private List<JLabel> jobLabelArary = new ArrayList<>();
    private List<Box.Filler> resettableFillers = new ArrayList<>();
    private boolean dirtyJobPanels = true;

    /**
     * Creates new form PopulationPanel
     */
    public PopulationPanel() {
        initComponents();
        EventManager.getInstance().addSolutionListener(this);
        EventManager.getInstance().addEventListener(this);
        this.jPanel_Container.setLayout(new GridLayout(0, 1));
        this.jButton_settings.setPreferredSize(new Dimension(42, 42));
        this.jButton_run.setPreferredSize(new Dimension(42, 42));
        this.jButton_Generate.setPreferredSize(new Dimension(42, 42));
        this.jButton_settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings32.png")));
        this.jButton_run.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/play32.png")));
        this.jButton_Generate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/randomize32.png")));
        this.jButton_settings.setText("");
        this.jButton_run.setText("");
        this.jButton_Generate.setText("");
        this.jButton_settings.setToolTipText("Impostazioni");
        this.jButton_run.setToolTipText("Esegui algoritmo genetico");
        this.jButton_Generate.setToolTipText("Genera un input iniziale");
        this.jToolBar1.setFloatable(false);
        this.jProgressBar1.setStringPainted(true);
        this.jProgressBar1.setString("0 %");
    }

    private static DataNode createDataStructure() {
        DataNode root = new DataNode("Root");
        Population pop = InputManager.getInstance().generatePopulation();
        GeneticManipulator.getInstance().loadInitialPopulation(pop);
        int lastIndexToDecorate = GeneticManipulator.getInstance().getImmuneSize();
        int i = 0;
        for (JobIndividual individual : pop.getIndividuals()) {

            //<html><font color = red><b>
            String decoration = "";
            if (i < lastIndexToDecorate) {
                decoration = TreeTableCellRenderer.HTML_DEOCORATION_1;
            }
            DataNode node = new DataNode(ArrayUtils.toObject(individual.getJobPermutation()), decoration + individual.getFitness());
            node.addChild(new DataNode(ArrayUtils.toObject(individual.getOperationSequence()), TreeTableCellRenderer.HTML_DEOCORATION_2 + "operations"));
            node.addChild(new DataNode(individual.getMachinesSelected(), TreeTableCellRenderer.HTML_DEOCORATION_2 + "machines"));
            node.addChild(new DataNode(ArrayUtils.toObject(individual.getComplationArray()), TreeTableCellRenderer.HTML_DEOCORATION_2 + "completion"));
            root.addChild(node);
            i++;
        }

        return root;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton_settings = new javax.swing.JButton();
        jButton_Generate = new javax.swing.JButton();
        jButton_run = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 30), new java.awt.Dimension(50, 30), new java.awt.Dimension(50, 30));
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel_Container = new javax.swing.JPanel();

        jToolBar1.setRollover(true);

        jButton_settings.setText("Settings");
        jButton_settings.setFocusable(false);
        jButton_settings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_settings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_settingsActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton_settings);

        jButton_Generate.setText("Generate Input");
        jButton_Generate.setFocusable(false);
        jButton_Generate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Generate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_Generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GenerateActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton_Generate);

        jButton_run.setText("  Run  ");
        jButton_run.setEnabled(false);
        jButton_run.setFocusable(false);
        jButton_run.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_run.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_runActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton_run);
        jToolBar1.add(filler1);

        jProgressBar1.setPreferredSize(new java.awt.Dimension(300, 20));
        jToolBar1.add(jProgressBar1);

        javax.swing.GroupLayout jPanel_ContainerLayout = new javax.swing.GroupLayout(jPanel_Container);
        jPanel_Container.setLayout(jPanel_ContainerLayout);
        jPanel_ContainerLayout.setHorizontalGroup(
            jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_ContainerLayout.setVerticalGroup(
            jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_runActionPerformed

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GeneticManipulator.getInstance().launch();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }//GEN-LAST:event_jButton_runActionPerformed

    private void jButton_GenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GenerateActionPerformed
        EventManager.getInstance().generationStarted();
        this.jPanel_Container.removeAll();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AbstractTreeTableModel treeTableModel = new DataModel(createDataStructure());
                jProgressBar1.setValue(15);
                jProgressBar1.setString("" + 15 + " %");
                jProgressBar1.update(jProgressBar1.getGraphics());

                final TreeTable myTreeTable = new TreeTable(treeTableModel);
                jProgressBar1.setValue(20);
                jProgressBar1.setString("" + 20 + " %");
                jProgressBar1.update(jProgressBar1.getGraphics());
                myTreeTable.getColumnModel().getColumn(0).setMinWidth(140);
//        myTreeTable.getColumnModel().getColumn(0).setMaxWidth(80);
                int dimension = InputManager.getInstance().getDimension();
                for (int i = 1; i < dimension; i++) {

                    //increment : 100 = i : dimension
                    final int increment = 21 + ((80 * i) / dimension);
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            jProgressBar1.setValue(increment);
                            jProgressBar1.setString("" + increment + " %");
//                            jProgressBar1.invalidate();
//                            jProgressBar1.repaint();
                            jProgressBar1.update(jProgressBar1.getGraphics());
                        }

                    });

                    myTreeTable.getColumnModel().getColumn(i).setPreferredWidth(40);
                    myTreeTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                            if (myTreeTable.isRowExpanded(row)) {
                                c.setBackground(Color.red);
                            } else {
                                if (isSelected) {
                                    c.setBackground(table.getSelectionBackground());
                                } else {
                                    c.setBackground(table.getBackground());
                                }
                            }
                            return c;
                        }

                    });
                }

                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        jPanel_Container.add(new JScrollPane(myTreeTable));
                        jPanel_Container.invalidate();
                        jPanel_Container.repaint();
                        PopulationPanel.this.invalidate();
                        PopulationPanel.this.jPanel_Container.repaint();
                        myTreeTable.invalidate();
                        myTreeTable.revalidate();
                        myTreeTable.repaint();
                        jPanel_Container.update(jPanel_Container.getGraphics());
                    }
                });
            }
        });
        this.jButton_run.setEnabled(true);
        EventManager.getInstance().generationEnded();

    }//GEN-LAST:event_jButton_GenerateActionPerformed

    private void jButton_settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_settingsActionPerformed
        SettingFrame settingFrame = new SettingFrame();
        settingFrame.setLocationRelativeTo(null);
        settingFrame.setVisible(true);
    }//GEN-LAST:event_jButton_settingsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton_Generate;
    private javax.swing.JButton jButton_run;
    private javax.swing.JButton jButton_settings;
    private javax.swing.JPanel jPanel_Container;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void start(int initialFitness) {
        this.jButton_Generate.setEnabled(false);
        this.jButton_settings.setEnabled(false);

    }

    @Override
    public void end(JobIndividual bestone) {
        this.jButton_Generate.setEnabled(true);
        this.jButton_settings.setEnabled(true);
    }

    @Override
    public void newImprovement(int newFitness) {
    }

    @Override
    public void nextCycle(int cycle) {
    }

    @Override
    public void generationStarted() {
    }

    @Override
    public void generationEnded() {
        if (dirtyJobPanels) {
            for (JLabel jLabel : jobLabelArary) {
                this.jToolBar1.remove(jLabel);
            }
            for (Box.Filler resettableFiller : this.resettableFillers) {
                this.jToolBar1.remove(resettableFiller);
            }
            this.jobLabelArary.clear();
            this.resettableFillers.clear();
            Map<Integer, Integer> jobQuantityMap = InputManager.getInstance().getJobQuantityMap();
            for (Map.Entry<Integer, Integer> entry : jobQuantityMap.entrySet()) {
                JLabel label = new JLabel();
                label.setMinimumSize(new Dimension(50, 40));
//            label.setOpaque(true);
                label.setText("<html><div style = \"text-align:center; padding: 4px; background-color: #B5BCAB\">Job <b>" + entry.getKey() + "</b><br><font color = blue>" + entry.getValue() + "</div>");
                this.jobLabelArary.add(label);
                Dimension rigidFillerDim = new java.awt.Dimension(10, 30);
                Box.Filler filler = new javax.swing.Box.Filler(rigidFillerDim, rigidFillerDim, rigidFillerDim);
                this.resettableFillers.add(filler);
                this.jToolBar1.add(filler);
                this.jToolBar1.add(label);
            }
            this.dirtyJobPanels = false;
        }

    }

    @Override
    public void algoStart() {
    }

    @Override
    public void solutionFound() {
    }

    @Override
    public void settingsChanged() {
        this.dirtyJobPanels = true;
    }

    @Override
    public void backToDefault() {
    }
}
