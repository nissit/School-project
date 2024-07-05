package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.controller.DataVisualController;
import com.mycompany.datavisualisation.model.DataVisualModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataVisualView extends JFrame {
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5; // Reset button
    public JInternalFrame jInternalFrame1;
    private DataVisualController controller;
    private DataVisualModel model;

    public DataVisualView(DataVisualController controller, DataVisualModel model) {
        this.controller = controller;
        this.model = model;
        initComponents();
    }

    private void initComponents() {
        setTitle("Data Visualisation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top panel with buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jButton1 = new JButton("Add Data");
        jButton1.addActionListener(e -> controller.handleAddDataButtonClick());
        jButton2 = new JButton("Preview Data");
        jButton2.addActionListener(e -> controller.handlePreviewDataButtonClick());
        jButton3 = new JButton("Pie Chart");
        jButton3.addActionListener(e -> controller.handlePieChartButtonClick());
        jButton4 = new JButton("Bar Chart");
        jButton4.addActionListener(e -> controller.handleBarChartButtonClick());
        jButton5 = new JButton("Reset"); // Add the Reset button
        jButton5.addActionListener(e -> controller.handleResetButtonClick());
        topPanel.add(jButton1);
        topPanel.add(jButton2);
        topPanel.add(jButton3);
        topPanel.add(jButton4);
        topPanel.add(jButton5); // Add the Reset button to the top panel

        // Internal frame to display data/charts
        jInternalFrame1 = new JInternalFrame("Data/Charts", true, true, true, true);
        jInternalFrame1.setSize(600, 400);
        jInternalFrame1.setLocation(100, 100);
        jInternalFrame1.setVisible(true);

        // Add components to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(jInternalFrame1, BorderLayout.CENTER);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
}
