package com.mycompany.datavisualisation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataDisplay {
    public static void displayData(JInternalFrame jInternalFrame1) {
        // Path to data file
        Path dataFilePath = Paths.get("./uploads/data.csv");

        // Check if data file exists
        if (Files.exists(dataFilePath)) {
            try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath.toFile()))) {
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Name");
                model.addColumn("Value");

                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 2) {
                        model.addRow(data);
                    }
                }

                JTable table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table);
                jInternalFrame1.setContentPane(scrollPane);
                jInternalFrame1.revalidate();
                jInternalFrame1.repaint();

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No data available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
