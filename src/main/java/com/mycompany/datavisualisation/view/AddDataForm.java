package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataVisualModel;

import javax.swing.*;
import java.awt.*;

public class AddDataForm extends JDialog {
    private DataVisualModel model;

    public AddDataForm(DataVisualModel model) {
        this.model = model;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Data");
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel valueLabel = new JLabel("Value:");
        JTextField valueField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            double value = Double.parseDouble(valueField.getText());
            model.addData(name, value);
            dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(valueLabel);
        mainPanel.add(valueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
