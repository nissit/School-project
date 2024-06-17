package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BarChartDisplay {
    public static void displayBarChart(JInternalFrame internalFrame, DataVisualModel model) {
        // Create the bar chart dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<DataPoint> data = model.getData();
        for (DataPoint point : data) {
            dataset.setValue(point.getValue(), "Values", point.getName());
        }

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Data Visualization",
                "Names",
                "Values",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Customize the bar chart
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setTickLabelsVisible(true);
        categoryAxis.setMaximumCategoryLabelLines(2); // Limit the number of label lines
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(0.1); // Adjust the spacing between bars

        // Add the chart to the internal frame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        internalFrame.setContentPane(chartPanel);
        internalFrame.revalidate();
        internalFrame.repaint();
    }
}
