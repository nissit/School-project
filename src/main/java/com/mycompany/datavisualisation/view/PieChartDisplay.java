package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.List;

public class PieChartDisplay extends JPanel {
    private DataVisualModel model;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public PieChartDisplay(DataVisualModel model) {
        this.model = model;
        initUI();
    }

    private void initUI() {
        chart = createChart();
        chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }

    private JFreeChart createChart() {
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Pie Chart",
                new DefaultPieDataset(),
                true, true, false);
        return pieChart;
    }

    public void updateChart(List<DataPoint> data, String title) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (DataPoint dataPoint : data) {
            dataset.setValue(dataPoint.getName(), dataPoint.getValue());
        }
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setDataset(dataset);
        chart.setTitle(title);
        chartPanel.repaint();
    }
}
