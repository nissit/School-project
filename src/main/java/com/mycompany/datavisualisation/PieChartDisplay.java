package com.mycompany.datavisualisation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PieChartDisplay {
    public static void displayPieChart(JInternalFrame jInternalFrame1) {
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
        jInternalFrame1.revalidate();
        jInternalFrame1.repaint();
    }
}
