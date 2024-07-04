package com.mycompany.datavisualisation.controller;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;
import com.mycompany.datavisualisation.view.*;

import javax.swing.*;

import java.util.List;

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
