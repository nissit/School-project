package com.mycompany.datavisualisation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataDisplay {
    public static void displayData(JInternalFrame jInternalFrame1) {
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Value");
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            jInternalFrame1.setContentPane(scrollPane);
            jInternalFrame1.revalidate();
            jInternalFrame1.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}