package com.mycompany.datavisualisation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BarChartDisplay {
    public static void displayBarChart(JInternalFrame jInternalFrame1) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                dataset.addValue(Double.parseDouble(data[1]), data[0], "");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Data Bar Chart",
                "Category",
                "Value",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        jInternalFrame1.setContentPane(chartPanel);
        jInternalFrame1.revalidate();
        jInternalFrame1.repaint();
    }
}
