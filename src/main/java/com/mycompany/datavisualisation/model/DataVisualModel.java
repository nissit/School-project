package com.mycompany.datavisualisation.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataVisualModel {
    private List<DataPoint> data;

    public DataVisualModel() {
        data = new ArrayList<>();
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
