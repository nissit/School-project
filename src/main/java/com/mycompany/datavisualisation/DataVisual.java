package com.mycompany.datavisualisation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataVisual extends JFrame {
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JInternalFrame jInternalFrame1;

    public DataVisual() {
        initComponents();
    }

    private void initComponents() {
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jInternalFrame1 = new JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Azo Sans", 0, 36));

        jLabel1.setFont(new java.awt.Font("Azo Sans", 0, 30));
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Data Visualisation");

        jButton1.setFont(new java.awt.Font("Azo Sans", 0, 18));
        jButton1.setText("Add Data");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Azo Sans", 0, 12));
        jLabel2.setText("Title of file");

        jButton2.setText("Preview Data");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pie-Chart");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Bar Chart");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Reset");

        jInternalFrame1.setVisible(true);

        GroupLayout jInternalFrame1Layout = new GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
                jInternalFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 232, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
                jInternalFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3)
                                        .addComponent(jButton4)
                                        .addComponent(jButton5))
                                .addGap(18, 18, 18)
                                .addComponent(jInternalFrame1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(52, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jInternalFrame1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton5)
                                                .addGap(0, 32, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        // Code to display the form when "Add Data" button is clicked
        AddDataForm addDataForm = new AddDataForm();
        addDataForm.setVisible(true);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        // Display collected data inside data.csv file in the jInternalFrame1
        displayData();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        // Display pie chart of data from data.csv
        displayPieChart();
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        // Display bar chart of data from data.csv
        displayBarChart();
    }

    private void displayData() {
        // Read data from data.csv and display it in a tabular format inside jInternalFrame1
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Value");
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            jInternalFrame1.setContentPane(scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayPieChart() {
        // Read data from data.csv and create a pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                dataset.setValue(data[0], Double.parseDouble(data[1]));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart("Data Pie Chart", dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        jInternalFrame1.setContentPane(chartPanel);
    }

    private void displayBarChart() {
        // Read data from data.csv and create a bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                dataset.addValue
                (Double.parseDouble(data[1]), "Value", data[0]);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart("Data Bar Chart", "Name", "Value", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        jInternalFrame1.setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataVisual().setVisible(true);
            }
        });
    }
}
