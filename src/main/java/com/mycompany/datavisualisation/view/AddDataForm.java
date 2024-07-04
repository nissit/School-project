package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.controller.DataVisualController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AddDataForm extends JDialog {
    private JTextField nameField;
    private JTextField valueField;
    private JButton addButton;
    private JButton cancelButton;
    private JButton importButton; // New button for importing CSV data

    private DataVisualController controller;

    public AddDataForm(DataVisualController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Data");
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200); // Adjusted size to fit the new button
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // Increased rows for the new button
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel valueLabel = new JLabel("Value:");
        valueField = new JTextField();

        addButton = new JButton("Add");
        addButton.addActionListener(e -> addData());

        importButton = new JButton("Import Data"); // New button
        importButton.addActionListener(e -> importData()); // ActionListener for import button

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(valueLabel);
        mainPanel.add(valueField);
        mainPanel.add(addButton);
        mainPanel.add(importButton); // Add import button to panel
        mainPanel.add(cancelButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addData() {
        String name = nameField.getText();
        String value = valueField.getText();

        if (!name.isEmpty() && !value.isEmpty()) {
            try {
                // Add data logic here
                controller.handlePreviewDataButtonClick();
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error adding data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a name and a value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void importData() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                Path targetPath = Paths.get("./uploads/data.csv"); // Path to save the imported data

                // Check if /uploads folder exists, create if it doesn't
                File uploadsFolder = new File("./uploads");
                if (!uploadsFolder.exists()) {
                    uploadsFolder.mkdir();
                }

                // Copy the selected file to the target path (/uploads/data.csv)
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                // Validate the imported CSV file
                if (validateCSV(targetPath)) {
                    JOptionPane.showMessageDialog(this, "File imported successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "The CSV file must have exactly two rows.", "Error", JOptionPane.ERROR_MESSAGE);
                    Files.deleteIfExists(targetPath); // Delete the file if validation fails
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error importing data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateCSV(Path filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            int rowCount = 0;
            while ((line = reader.readLine()) != null) {
                rowCount++;
            }
            return rowCount == 2; // Validate if the file has exactly two rows
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
