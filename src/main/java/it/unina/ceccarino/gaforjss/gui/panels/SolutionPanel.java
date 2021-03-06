/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package it.unina.ceccarino.gaforjss.gui.panels;

import it.cnr.istc.icv.engine.EmbeddablePanel;
import it.cnr.istc.icv.exceptions.TypeDataMismatchException;
import it.cnr.istc.icv.logic.ICVAnnotation;
import it.cnr.istc.icv.test.LinearDataSupporter;
import it.cnr.istc.icv.test.TimeValueSupporterClass;
import it.unina.ceccarino.gaforjss.algo.GeneticManipulator;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.AbstractTreeTableModel;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.DataModel;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.DataNode;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.TreeTable;
import it.unina.ceccarino.gaforjss.gui.abstracts.tree.TreeTableCellRenderer;
import it.unina.ceccarino.gaforjss.logic.EventManager;
import it.unina.ceccarino.gaforjss.logic.SolutionListener;
import it.unina.ceccarino.gaforjss.model.InputManager;
import it.unina.ceccarino.gaforjss.model.JobIndividual;
import it.unina.ceccarino.gaforjss.model.Settings;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.synth.SynthFormattedTextFieldUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;
import javax.swing.tree.TreePath;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 *
 * @author sommovir
 */
public class SolutionPanel extends javax.swing.JPanel implements SolutionListener {

    private int startFitness;
    private int currentFitness;
    final static EmbeddablePanel panel = new EmbeddablePanel();
    private int x = 0;
    private int avgEach = 1;

    /**
     * Creates new form SolutionPanel
     */
    public SolutionPanel() {
        initComponents();
        final JComponent editor = this.jSpinner_avg.getEditor();
        int c = editor.getComponentCount();
        for (int i = 0; i < c; i++) {
            final Component comp = editor.getComponent(i);
            if (comp instanceof JTextComponent) {
                ((JTextComponent) comp).setUI(new SynthFormattedTextFieldUI() {
                    @Override
                    protected void paint(javax.swing.plaf.synth.SynthContext context, java.awt.Graphics g) {
                        g.setColor(Color.GRAY);
                        g.fillRect(0, 0, getComponent().getWidth(), getComponent().getHeight());
                        super.paint(context, g);
                    }
                });
            }
        }
        final JComponent editor2 = this.jSpinner_eachNoImprovement.getEditor();
        int c2 = editor2.getComponentCount();
        for (int i = 0; i < c2; i++) {
            final Component comp = editor2.getComponent(i);
            if (comp instanceof JTextComponent) {
                ((JTextComponent) comp).setUI(new SynthFormattedTextFieldUI() {
                    @Override
                    protected void paint(javax.swing.plaf.synth.SynthContext context, java.awt.Graphics g) {
                        g.setColor(Color.GRAY);
                        g.fillRect(0, 0, getComponent().getWidth(), getComponent().getHeight());
                        super.paint(context, g);
                    }
                });
            }
        }

        final JComponent editor3 = this.jSpinner_adv_rate.getEditor();
        int c3 = editor3.getComponentCount();
        for (int i = 0; i < c3; i++) {
            final Component comp = editor3.getComponent(i);
            if (comp instanceof JTextComponent) {
                ((JTextComponent) comp).setUI(new SynthFormattedTextFieldUI() {
                    @Override
                    protected void paint(javax.swing.plaf.synth.SynthContext context, java.awt.Graphics g) {
                        g.setColor(Color.GRAY);
                        g.fillRect(0, 0, getComponent().getWidth(), getComponent().getHeight());
                        super.paint(context, g);
                    }
                });
            }
        }

        EventManager.getInstance().addSolutionListener(this);
        panel.getMixedPanel().LEFT_MARGIN = 40;
        panel.getMixedPanel().setStartRange(0);
        panel.getMixedPanel().setEndRange(Settings.getInstance().getMaxIteration());
        panel.getMixedPanel().setShowDate(false);
        panel.getMixedPanel().setBackground(Color.WHITE);
        panel.getMixedPanel().setZoomEnable(true);
        panel.setXTooltipLabel("Cicli");
        panel.setYTooltipLabel("Fitness");

        LinearDataSupporter s = new LinearDataSupporter("Soluzione");
        s.setColorToSubChart("Best Fitness", Color.RED);
        s.setColorToSubChart("AVG Fitness", Color.BLUE);
        s.setDotVisible(true);
        s.setSubChartWithDots("Best Fitness", true);
        s.setSubChartWithDots("AVG Fitness", false);
        s.setOrder(1);
        s.setDiscret(false);
//        s.setMaxValueToShow(10);
//        s.setMinValueToShow(0);

//        s.
//        LinearDataSupporter s2 = new LinearDataSupporter("AVG fitness");
//        s2.setOrder(2);
//        s2.setDiscret(false);
        panel.getMixedPanel().addDataBar(s);
//        panel.getMixedPanel().addDataBar(s2);
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

        jPanel_Solution = new javax.swing.JPanel();
        jScrollPane_solutionContainer = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_Iterazioni = new javax.swing.JLabel();
        jLabel_startFitness = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_currentFitness = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel_elapsed = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jCheckBox_AVG = new javax.swing.JCheckBox();
        jSpinner_avg = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox_validation = new javax.swing.JCheckBox();
        jCheckBox_kalergi = new javax.swing.JCheckBox();
        jSpinner_eachNoImprovement = new javax.swing.JSpinner();
        jSpinner_adv_rate = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel_nothing = new javax.swing.JPanel();
        jLabel_runningMessage = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel_SolutionLayout = new javax.swing.GroupLayout(jPanel_Solution);
        jPanel_Solution.setLayout(jPanel_SolutionLayout);
        jPanel_SolutionLayout.setHorizontalGroup(
            jPanel_SolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_solutionContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        jPanel_SolutionLayout.setVerticalGroup(
            jPanel_SolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_solutionContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setText("Iterations");

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

        jCheckBox1.setText("Verbose");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("EsapsedTime: ");

        jLabel_elapsed.setBackground(new java.awt.Color(51, 51, 51));
        jLabel_elapsed.setForeground(new java.awt.Color(51, 255, 255));
        jLabel_elapsed.setText("-");
        jLabel_elapsed.setOpaque(true);

        jCheckBox_AVG.setText("AVG");

        jSpinner_avg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner_avg.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner_avg.setBorder(null);
        jSpinner_avg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_avgStateChanged(evt);
            }
        });

        jLabel4.setText("avg each");

        jCheckBox_validation.setSelected(true);
        jCheckBox_validation.setText("validate each solution");
        jCheckBox_validation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_validationActionPerformed(evt);
            }
        });

        jCheckBox_kalergi.setText("Kalergi each: ");
        jCheckBox_kalergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_kalergiActionPerformed(evt);
            }
        });

        jSpinner_eachNoImprovement.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner_eachNoImprovement.setModel(new javax.swing.SpinnerNumberModel(100, 10, null, 1));
        jSpinner_eachNoImprovement.setBorder(null);
        jSpinner_eachNoImprovement.setEnabled(false);
        jSpinner_eachNoImprovement.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_eachNoImprovementStateChanged(evt);
            }
        });

        jSpinner_adv_rate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jSpinner_adv_rate.setModel(new javax.swing.SpinnerNumberModel(10, 1, 50, 1));
        jSpinner_adv_rate.setBorder(null);
        jSpinner_adv_rate.setEnabled(false);
        jSpinner_adv_rate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_adv_rateStateChanged(evt);
            }
        });

        jLabel7.setText("kalergi %");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_Iterazioni, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_startFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_currentFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_elapsed, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox_AVG)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner_avg, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox_validation)
                                .addGap(31, 31, 31)
                                .addComponent(jCheckBox_kalergi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner_eachNoImprovement, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner_adv_rate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel_currentFitness)
                        .addComponent(jLabel3)
                        .addComponent(jLabel_elapsed))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel_Iterazioni)
                        .addComponent(jLabel_startFitness)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox_AVG)
                    .addComponent(jSpinner_avg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jCheckBox_validation)
                    .addComponent(jCheckBox_kalergi)
                    .addComponent(jSpinner_eachNoImprovement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner_adv_rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setBackground(new java.awt.Color(56, 51, 51));
        jSplitPane1.setRightComponent(jScrollPane1);

        jPanel_nothing.setBackground(new java.awt.Color(51, 51, 51));

        jLabel_runningMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_runningMessage.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_runningMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_runningMessage.setText("No solution yet.");

        javax.swing.GroupLayout jPanel_nothingLayout = new javax.swing.GroupLayout(jPanel_nothing);
        jPanel_nothing.setLayout(jPanel_nothingLayout);
        jPanel_nothingLayout.setHorizontalGroup(
            jPanel_nothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_runningMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
        );
        jPanel_nothingLayout.setVerticalGroup(
            jPanel_nothingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_runningMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel_nothing);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        Settings.getInstance().setVerbose(this.jCheckBox1.isSelected());
        //EventManager.getInstance().settingsChanged();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jSpinner_avgStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_avgStateChanged
        this.avgEach = (Integer) this.jSpinner_avg.getValue();
    }//GEN-LAST:event_jSpinner_avgStateChanged

    private void jCheckBox_validationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_validationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_validationActionPerformed

    private void jSpinner_eachNoImprovementStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_eachNoImprovementStateChanged
        Settings.getInstance().setKalergiEach((Integer)this.jSpinner_eachNoImprovement.getValue());
    }//GEN-LAST:event_jSpinner_eachNoImprovementStateChanged

    private void jSpinner_adv_rateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_adv_rateStateChanged
        Settings.getInstance().setKalergiInjectionRate((Integer)this.jSpinner_adv_rate.getValue());
    }//GEN-LAST:event_jSpinner_adv_rateStateChanged

    private void jCheckBox_kalergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_kalergiActionPerformed
        Settings.getInstance().setKalergi(jCheckBox_kalergi.isSelected());
        this.jSpinner_adv_rate.setEnabled(this.jCheckBox_kalergi.isSelected());
        this.jSpinner_eachNoImprovement.setEnabled(this.jCheckBox_kalergi.isSelected());
    }//GEN-LAST:event_jCheckBox_kalergiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox_AVG;
    private javax.swing.JCheckBox jCheckBox_kalergi;
    private javax.swing.JCheckBox jCheckBox_validation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_Iterazioni;
    private javax.swing.JLabel jLabel_currentFitness;
    private javax.swing.JLabel jLabel_elapsed;
    private javax.swing.JLabel jLabel_runningMessage;
    private javax.swing.JLabel jLabel_startFitness;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Solution;
    private javax.swing.JPanel jPanel_nothing;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane_solutionContainer;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner_adv_rate;
    private javax.swing.JSpinner jSpinner_avg;
    private javax.swing.JSpinner jSpinner_eachNoImprovement;
    private javax.swing.JSplitPane jSplitPane1;
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
        panel.getMixedPanel().setNowLineVisible(true);
        TimeValueSupporterClass ds1 = new TimeValueSupporterClass(initialFitness, "Best Fitness", new Date(x));
        try {
            panel.getMixedPanel().addLinearData("Soluzione", ds1, true);
        } catch (TypeDataMismatchException ex) {
            ex.printStackTrace();
        }
        this.jLabel_elapsed.setText("running..");
        this.jSplitPane1.setLeftComponent(this.jPanel_nothing);
        this.jLabel_runningMessage.setForeground(Color.YELLOW);
        this.jLabel_runningMessage.setText("Calculating new solution..");

    }

    @Override
    public void end(JobIndividual bestone) {

        System.out.println("<<SOLUTION with finess: " + bestone.getFitness() + " >>");
        System.out.println(bestone);

        long elapsedTime = GeneticManipulator.getInstance().getElapsedTime();
        String formatDuration = DurationFormatUtils.formatDuration(elapsedTime, "HH:mm:ss.S");
        this.jLabel_elapsed.setText(formatDuration);

        DataNode root = new DataNode("Root");
        DataNode node = new DataNode(ArrayUtils.toObject(bestone.getJobPermutation()), "" + bestone.getFitness());
        node.addChild(new DataNode(ArrayUtils.toObject(bestone.getOperationSequence()), TreeTableCellRenderer.HTML_DEOCORATION_2 + "operations"));
        node.addChild(new DataNode(bestone.getMachinesSelected(), TreeTableCellRenderer.HTML_DEOCORATION_2 + "machines"));
        node.addChild(new DataNode(ArrayUtils.toObject(bestone.getComplationArray()), TreeTableCellRenderer.HTML_DEOCORATION_2 + "completion"));
        root.addChild(node);

        AbstractTreeTableModel treeTableModel = new DataModel(root);
        final TreeTable solutionTreeTable = new TreeTable(treeTableModel);
        solutionTreeTable.getColumnModel().getColumn(0).setMinWidth(140);
        int dimension = InputManager.getInstance().getDimension();
        for (int i = 1; i <= dimension; i++) {
            solutionTreeTable.getColumnModel().getColumn(i).setPreferredWidth(40);
            solutionTreeTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                    if (solutionTreeTable.isRowExpanded(row)) {
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
        solutionTreeTable.expandRoot();
        this.jScrollPane_solutionContainer.setViewportView(solutionTreeTable);
        this.jSplitPane1.setLeftComponent(this.jPanel_Solution);

    }

    @Override
    public void newImprovement(JobIndividual bestone, int newFitness) {
        this.currentFitness = newFitness;
        this.jLabel_currentFitness.setText("" + newFitness + " ");
        TimeValueSupporterClass ds1 = new TimeValueSupporterClass(newFitness, "Best Fitness", new Date(x));
        try {
            panel.getMixedPanel().addLinearData("Soluzione", ds1, true);
        } catch (TypeDataMismatchException ex) {
            ex.printStackTrace();
        }
        if (!GeneticManipulator.getInstance().validateSolution(bestone)) {
            this.jLabel_runningMessage.setText("Soluzione invalida. Algoritmo interrotto.");
            System.out.println("SOLUZIONE INVALIDA: ");
            System.out.println("FITNESS: " + bestone.getFitness());
            System.out.println(bestone);
            System.out.println("--------------------------------------------------------------------");
            GeneticManipulator.getInstance().interrupt();

            JOptionPane.showMessageDialog(null, "La soluzione non ?? valida. ", "Errore", JOptionPane.ERROR_MESSAGE);

        } else {
            System.out.println("[OK] SOLUZIONE VALIDA A FITNESS: " + bestone.getFitness());
        }
        // panel.getMixedPanel().addICVAnnotation(new ICVAnnotation("Soluzione", x, "" + x, true));

    }

    @Override
    public void nextCycle(int cycle) {
        cycle++;
        this.jLabel_Iterazioni.setText(cycle + "/" + Settings.getInstance().getMaxIteration() + " ");
        x = cycle;
        panel.getMixedPanel().setFloatableNow(x);

    }

    @Override
    public void newAVG(int avg) {
        if (this.jCheckBox_AVG.isSelected()) {
            if (x % this.avgEach == 0) {
                TimeValueSupporterClass ds1 = new TimeValueSupporterClass(avg, "AVG Fitness", new Date(x));
                try {
                    panel.getMixedPanel().addLinearData("Soluzione", ds1, true);
                } catch (TypeDataMismatchException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void kalergi() {
        panel.getMixedPanel().addICVAnnotation(new ICVAnnotation(
                "Soluzione",
                x,
                "",
                new javax.swing.ImageIcon(getClass().getResource("/icons/triangle16.png")).getImage(),
                16f,
                ICVAnnotation.DescriptionAlignment.CENTER,
                true));
    }
}
