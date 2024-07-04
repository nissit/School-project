package com.mycompany.datavisualisation.controller;

import com.mycompany.datavisualisation.model.DataVisualModel;
import com.mycompany.datavisualisation.view.AddDataForm;
import com.mycompany.datavisualisation.view.BarChartDisplay;
import com.mycompany.datavisualisation.view.DataDisplay;
import com.mycompany.datavisualisation.view.DataVisualView;
import com.mycompany.datavisualisation.view.PieChartDisplay;

import javax.swing.*;

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
        DataDisplay.displayData(view.jInternalFrame1);
    }

    public void handlePieChartButtonClick() {
        PieChartDisplay.displayPieChart(view.jInternalFrame1, model);
    }

    public void handleBarChartButtonClick() {
        BarChartDisplay.displayBarChart(view.jInternalFrame1, model);
    }
}
