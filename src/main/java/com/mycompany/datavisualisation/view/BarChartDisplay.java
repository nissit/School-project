package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataVisualModel;

import javax.swing.*;
import java.awt.*;

public class BarChartDisplay {
    public static void displayBarChart(JInternalFrame internalFrame) {
        // Implement the logic to display the bar chart
        JPanel barChartPanel = new JPanel();
        barChartPanel.setBackground(Color.WHITE);
        barChartPanel.add(new JLabel("Bar Chart"));
        internalFrame.add(barChartPanel);
        internalFrame.setVisible(true);
    }
}
