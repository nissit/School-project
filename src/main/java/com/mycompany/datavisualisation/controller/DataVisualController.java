package com.mycompany.datavisualisation.controller;

import com.mycompany.datavisualisation.model.DataVisualModel;
import com.mycompany.datavisualisation.view.*;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class DataVisualController {
    private DataVisualView view;
    private DataVisualModel model;

    public DataVisualController() {
        model = new DataVisualModel();
        view = new DataVisualView(this, model);
        view.setVisible(true);
    }

    public DataVisualView getView() {
        return view;
    }

    public DataVisualModel getModel() {
        return model;
    }

    public void handleAddDataButtonClick() {
        AddDataForm addDataForm = new AddDataForm(this);
        addDataForm.setVisible(true);
    }

    public void handlePreviewDataButtonClick() {
        if (Files.exists(Paths.get("./uploads/data.csv"))) {
            DataDisplay.displayData(view.jInternalFrame1);
        } else {
            JOptionPane.showMessageDialog(view, "No data available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void handlePieChartButtonClick() {
        if (Files.exists(Paths.get("./uploads/data.csv"))) {
            PieChartDisplay.displayPieChart(view.jInternalFrame1, model);
        } else {
            JOptionPane.showMessageDialog(view, "No data available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void handleBarChartButtonClick() {
        if (Files.exists(Paths.get("./uploads/data.csv"))) {
            BarChartDisplay.displayBarChart(view.jInternalFrame1, model);
        } else {
            JOptionPane.showMessageDialog(view, "No data available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void addData(String name, double value) {
        if (model.addData(name, value)) {
            refreshApplication();
        }
    }

    public void handleResetButtonClick() {
        Path path = Paths.get("./uploads/data.csv");
        if (Files.exists(path)) {
            try {
                Files.deleteIfExists(path);
                model.clearData();
                refreshApplication(); // Call the refreshApplication() method
                JOptionPane.showMessageDialog(view, "Data has been reset.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view, "Error resetting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "No data to be reset.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void refreshApplication() {
        // Clear the internal frame
        view.jInternalFrame1.getContentPane().removeAll();
        view.jInternalFrame1.revalidate();
        view.jInternalFrame1.repaint();

        // Refresh the data display
        DataDisplay.displayData(view.jInternalFrame1);
        // Refresh the pie chart
        PieChartDisplay.displayPieChart(view.jInternalFrame1, model);
        // Refresh the bar chart
        BarChartDisplay.displayBarChart(view.jInternalFrame1, model);
    }
}
