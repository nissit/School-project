package com.mycompany.datavisualisation.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.datavisualisation.controller.DataVisualController;
import com.mycompany.datavisualisation.view.*;

public class DataVisualModel {
    private List<DataPoint> data;
    private DataVisualController controller;


    public DataVisualModel(DataVisualController controller) {
    data = new ArrayList<>();
    this.controller = controller;
    loadData();
}

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    double value = Double.parseDouble(parts[1]);
                    data.add(new DataPoint(name, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addData(String name, double value) {
        data.add(new DataPoint(name, value));
        saveData();
    
        // Update the data display and charts
        DataDisplay.displayData(controller.getView().jInternalFrame1);
        PieChartDisplay.displayPieChart(controller.getView().jInternalFrame1, this);
        BarChartDisplay.displayBarChart(controller.getView().jInternalFrame1, this);
    }
    

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv"))) {
            for (DataPoint point : data) {
                writer.write(point.getName() + "," + point.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DataPoint> getData() {
        return data;
    }
}