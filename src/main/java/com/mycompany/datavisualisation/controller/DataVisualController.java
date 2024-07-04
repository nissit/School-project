package com.mycompany.datavisualisation.controller;

import com.mycompany.datavisualisation.model.DataVisualModel;
import com.mycompany.datavisualisation.view.*;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
}
