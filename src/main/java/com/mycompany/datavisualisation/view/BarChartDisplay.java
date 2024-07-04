package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BarChartDisplay {
    public static void displayBarChart(JInternalFrame internalFrame, DataVisualModel model) {
        internalFrame.getContentPane().removeAll(); // Clear existing content

        // Create the bar chart dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<DataPoint> data = model.getData();
        for (DataPoint point : data) {
            dataset.setValue(point.getValue(), point.getName(), "");
        }

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart - Data Visualization",
            "Categories",
            "Values",
            dataset
        );

        // Create the chart panel and add it to the internal frame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        internalFrame.setContentPane(chartPanel);

        // Pack and set internal frame visible
        internalFrame.pack();
        internalFrame.setVisible(true);
    }
}
