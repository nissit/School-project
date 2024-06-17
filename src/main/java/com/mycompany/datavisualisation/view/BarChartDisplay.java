package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class BarChartDisplay extends JPanel {
    private DataVisualModel model;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public BarChartDisplay(DataVisualModel model) {
        this.model = model;
        initUI();
    }

    private void initUI() {
        chart = createChart();
        chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }

    private JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Bar Chart",
                "Name",
                "Value",
                new DefaultCategoryDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);
        return barChart;
    }

    public void updateChart(List<DataPoint> data, String title) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DataPoint dataPoint : data) {
            dataset.addValue(dataPoint.getValue(), "Data", dataPoint.getName());
        }
        chart.getCategoryPlot().setDataset(dataset);
        chart.setTitle(title);
        chartPanel.repaint();
    }
}
