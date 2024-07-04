package com.mycompany.datavisualisation.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataVisualModel {
    private List<DataPoint> data;

    public DataVisualModel() {
        data = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("uploads/data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    try {
                        double value = Double.parseDouble(parts[1].trim()); // Ensure trimming whitespace
                        data.add(new DataPoint(name, value));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing value for: " + name);
                        // Log the error or handle it appropriately
                    }
                } else {
                    System.err.println("Invalid format for line: " + line);
                    // Log the error or handle invalid format
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

    public void appendData(String name, double value) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("uploads/data.csv", true))) {
            writer.write(name + "," + value);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DataPoint> getData() {
        return data;
    }

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("uploads/data.csv"))) {
            for (DataPoint point : data) {
                writer.write(point.getName() + "," + point.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
