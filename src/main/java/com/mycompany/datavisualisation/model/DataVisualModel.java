package com.mycompany.datavisualisation.model;

import com.mycompany.datavisualisation.controller.DataVisualController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataVisualModel {
    private List<DataPoint> data;
    private DataVisualController controller;

    public DataVisualModel(DataVisualController controller) {
        this.controller = controller;
        data = new ArrayList<>();
    }

    public void addData(String name, double value) {
        data.add(new DataPoint(name, value));
        controller.getView().displayData(data, "Data");
    }

    public void setData(List<DataPoint> data) {
        this.data = data;
    }

    public List<DataPoint> getData() {
        return data;
    }

    public List<List<DataPoint>> readImportedData() {
        List<List<DataPoint>> importedData = new ArrayList<>();
        try {
            Path importedDataPath = Paths.get("./importedData");
            List<Path> csvFiles = Files.list(importedDataPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".csv"))
                    .collect(Collectors.toList());

            for (Path csvFile : csvFiles) {
                List<DataPoint> fileData = Files.lines(csvFile)
                        .skip(1) // Skip header row
                        .map(line -> {
                            String[] parts = line.split(",");
                            return new DataPoint(parts[0], Double.parseDouble(parts[1]));
                        })
                        .collect(Collectors.toList());
                importedData.add(fileData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return importedData;
    }

    public List<DataPoint> readManualData() {
        // Placeholder for now, i'll need to implement this method
        // to read the manual data from the AddDataForm or a similar source
        List<DataPoint> manualData = new ArrayList<>();
        manualData.add(new DataPoint("Manual Data 1", 10.0));
        manualData.add(new DataPoint("Manual Data 2", 20.0));
        return manualData;
    }
}
