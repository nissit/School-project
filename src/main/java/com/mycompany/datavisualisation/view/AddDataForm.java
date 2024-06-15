package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.controller.DataVisualController;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddDataForm extends JDialog {
    private JTextField nameField;
    private JTextField valueField;
    private JButton addButton;
    private JButton cancelButton;

    private DataVisualController controller;

    public AddDataForm(DataVisualController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Data");
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel valueLabel = new JLabel("Value:");
        valueField = new JTextField();

        addButton = new JButton("Add");
        addButton.addActionListener(e -> addData());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(valueLabel);
        mainPanel.add(valueField);
        mainPanel.add(addButton);
        mainPanel.add(cancelButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addData() {
        String name = nameField.getText();
        String value = valueField.getText();

        if (!name.isEmpty() && !value.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv", true))) {
                writer.write(name + "," + value);
                writer.newLine();
                writer.flush();
                controller.handlePreviewDataButtonClick();
                dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a name and a value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
