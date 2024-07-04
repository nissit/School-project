package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.controller.DataVisualController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.*;

public class AddDataForm extends JDialog {
    private JTextField nameField;
    private JTextField valueField;
    private JButton addButton;
    private JButton cancelButton;
    private JButton importButton;

    private DataVisualController controller;

    public AddDataForm(DataVisualController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Data");
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel valueLabel = new JLabel("Value:");
        valueField = new JTextField();

        addButton = new JButton("Add");
        addButton.addActionListener(e -> addData());

        importButton = new JButton("Import Data");
        importButton.addActionListener(e -> importData());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(valueLabel);
        mainPanel.add(valueField);
        mainPanel.add(addButton);
        mainPanel.add(importButton);
        mainPanel.add(cancelButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addData() {
        String name = nameField.getText();
        String valueStr = valueField.getText();

        if (!name.isEmpty() && !valueStr.isEmpty()) {
            try {
                double value = Double.parseDouble(valueStr);

                // Check if uploads/data.csv exists
                Path dataCsvPath = Paths.get("./uploads/data.csv");
                if (Files.exists(dataCsvPath)) {
                    // Append data to existing file
                    controller.getModel().appendData(name, value);
                } else {
                    // Create new file and add data
                    File uploadsFolder = new File("./uploads");
                    if (!uploadsFolder.exists()) {
                        uploadsFolder.mkdir();
                    }
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataCsvPath.toFile()))) {
                        writer.write(name + "," + value);
                        writer.newLine();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Notify user and close form
                JOptionPane.showMessageDialog(this, "Data added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                controller.handlePreviewDataButtonClick();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a name and a value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void importData() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
    
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
    
            // Check if the selected file has .csv extension
            if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
                JOptionPane.showMessageDialog(this, "Only .csv files are supported.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit method if file is not a .csv
            }
    
            try {
                Path targetPath = Paths.get("./uploads/data.csv");
    
                // Check if /uploads folder exists, create if it doesn't
                File uploadsFolder = new File("./uploads");
                if (!uploadsFolder.exists()) {
                    uploadsFolder.mkdir();
                }
    
                // If data.csv exists, append imported data to it
                if (Files.exists(targetPath)) {
                    appendImportedData(selectedFile, targetPath);
                } else {
                    // Copy the selected file to the target path (/uploads/data.csv)
                    Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
    
                // Validate the imported CSV file (optional)
                if (validateCSV(targetPath)) {
                    JOptionPane.showMessageDialog(this, "File imported successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "The CSV file must have exactly two columns (name, value).", "Error", JOptionPane.ERROR_MESSAGE);
                    Files.deleteIfExists(targetPath); // Delete the file if validation fails
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error importing data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void appendImportedData(File importedFile, Path targetPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(importedFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(targetPath.toFile(), true))) {
    
            String line;
            while ((line = reader.readLine()) != null) {
                // Append each line to the existing data.csv file
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    private boolean validateCSV(Path filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    return false; // Return false if any row doesn't have exactly two columns
                }
            }
            return true; // Return true if all rows have exactly two columns
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
