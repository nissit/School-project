package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.controller.DataVisualController;
import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataVisualView extends JFrame {
    private DataVisualController controller;
    private DataVisualModel model;
    private JTabbedPane tabbedPane;
    private BarChartDisplay barChartDisplay;
    private PieChartDisplay pieChartDisplay;
    private DataDisplay dataDisplay;
    private JPanel manualDataPanel;
    private JTextField nameField;
    private JTextField valueField;
    private JButton addDataButton;
    private JButton importDataButton;

    public DataVisualView(DataVisualController controller, DataVisualModel model) {
        this.controller = controller;
        this.model = model;
        initUI();
    }

    private void initUI() {
        setTitle("Data Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        barChartDisplay = new BarChartDisplay(model);
        pieChartDisplay = new PieChartDisplay(model);
        dataDisplay = new DataDisplay(model);

        tabbedPane.addTab("Bar Chart", barChartDisplay);
        tabbedPane.addTab("Pie Chart", pieChartDisplay);
        tabbedPane.addTab("Data", dataDisplay);

        manualDataPanel = createManualDataPanel();
        tabbedPane.addTab("Manual Data", manualDataPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createManualDataPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        JLabel valueLabel = new JLabel("Value:");
        valueField = new JTextField(10);
        addDataButton = new JButton("Add Data");
        addDataButton.setPreferredSize(new Dimension(100, 30));
        addDataButton.addActionListener(e -> {
            String name = nameField.getText();
            double value = Double.parseDouble(valueField.getText());
            List<DataPoint> manualData = new ArrayList<>();
            manualData.add(new DataPoint(name, value));
            controller.handleManualDataEntry(manualData);
            nameField.setText("");
            valueField.setText("");
        });
        importDataButton = new JButton("Import Data");
        importDataButton.setPreferredSize(new Dimension(100, 30));
        importDataButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select CSV file");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                controller.handleImportedData(selectedFile);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(valueLabel);
        panel.add(valueField);
        panel.add(addDataButton);
        panel.add(importDataButton);

        return panel;
    }

    public void displayData(List<DataPoint> data, String title) {
        barChartDisplay.updateChart(data, title);
        pieChartDisplay.updateChart(data, title);
        dataDisplay.updateTable(data, title);
    }
}
