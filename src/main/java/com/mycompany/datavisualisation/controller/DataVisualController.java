package com.mycompany.datavisualisation.controller;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;
import com.mycompany.datavisualisation.view.AddDataForm;
import com.mycompany.datavisualisation.view.DataVisualView;

import java.io.File;
import java.util.List;

public class DataVisualController {
    private DataVisualModel model;
    private DataVisualView view;

    public DataVisualController() {
        model = new DataVisualModel(this);
        view = new DataVisualView(this, model);
        AddDataForm addDataForm = new AddDataForm(model);

        // Read manual data and display it
        List<DataPoint> manualData = model.readManualData();
        handleManualDataEntry(manualData);
    }

    public DataVisualModel getModel() {
        return model;
    }

    public DataVisualView getView() {
        return view;
    }

    public void handleManualDataEntry(List<DataPoint> data) {
        model.setData(data);
        view.displayData(data, "Manual Data");
    }

    public void handleImportedData(File selectedFile) {
        List<List<DataPoint>> importedData = model.readImportedData();
        for (List<DataPoint> data : importedData) {
            view.displayData(data, "Imported Data");
        }
    }
}
