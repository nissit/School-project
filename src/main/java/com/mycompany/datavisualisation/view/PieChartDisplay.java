package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PieChartDisplay {
    public static void displayPieChart(JInternalFrame internalFrame, DataVisualModel model) {
        // Create the pie chart dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        List<DataPoint> data = model.getData();
        for (DataPoint point : data) {
            dataset.setValue(point.getName(), point.getValue());
        }

        // Create the pie chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Data Visualization",
                dataset,
                true,
                true,
                false
        );

        // Customize the pie chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(null); // Remove the labels
        plot.setNoDataMessage("No data available"); // Set a message for no data

        // Add the chart to the internal frame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        internalFrame.setContentPane(chartPanel);
        internalFrame.revalidate();
        internalFrame.repaint();
    }
}
