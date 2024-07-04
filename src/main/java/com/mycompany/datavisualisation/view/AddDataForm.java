package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.controller.DataVisualController;

import javax.swing.*;
import java.awt.*;
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
        String name = nameField.getText().trim();
        String valueStr = valueField.getText().trim();

        if (isValidName(name) && isValidDouble(valueStr)) {
            try {
                double value = Double.parseDouble(valueStr);

                // Check if uploads/data.csv exists
                Path dataCsvPath = Paths.get("./uploads/data.csv");
                if (Files.exists(dataCsvPath)) {
                    // Append data to existing file
                    appendDataToFile(name, value, dataCsvPath);
                } else {
                    // Create new file and add data
                    createNewDataFile(name, value, dataCsvPath);
                }

                // Notify controller to refresh charts
                controller.handlePreviewDataButtonClick();

                // Notify user and close form
                JOptionPane.showMessageDialog(this, "Data added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid name and value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidName(String name) {
        // Check if the name is not empty and contains at least one alphabetic character
        return name != null && !name.isEmpty() && name.matches(".*[a-zA-Z]+.*");
    }

    private boolean isValidDouble(String valueStr) {
        try {
            Double.parseDouble(valueStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void appendDataToFile(String name, double value, Path filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(name + "," + value);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNewDataFile(String name, double value, Path filePath) {
        try {
            // Create the uploads directory if it doesn't exist
            Files.createDirectories(filePath.getParent());

            // Create the data.csv file and add initial data
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                writer.write(name + "," + value);
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating data file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                Files.createDirectories(targetPath.getParent());

                // Validate the imported CSV file before processing
                if (!validateCSV(selectedFile.toPath())) {
                    JOptionPane.showMessageDialog(this, "The CSV file must have exactly two columns (name, value) with appropriate types.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If data.csv exists, append imported data to it
                if (Files.exists(targetPath)) {
                    appendImportedData(selectedFile, targetPath);
                } else {
                    // Copy the selected file to the target path (/uploads/data.csv)
                    Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                }

                JOptionPane.showMessageDialog(this, "File imported successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Notify controller to refresh charts
                controller.handlePreviewDataButtonClick();
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
                // Validate each line before appending
                String[] parts = line.split(",");
                if (parts.length == 2 && isValidName(parts[0].trim()) && isValidDouble(parts[1].trim())) {
                    String name = parts[0].trim();
                    double value = Double.parseDouble(parts[1].trim());
                    writer.write(name + "," + value);
                    writer.newLine();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid format in imported data: " + line, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private boolean validateCSV(Path filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2 || !isValidName(parts[0].trim()) || !isValidDouble(parts[1].trim())) {
                    return false; // Return false if any row doesn't have exactly two columns or valid types
                }
            }
            return true; // Return true if all rows have exactly two columns and valid types
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
