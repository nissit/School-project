package com.mycompany.datavisualisation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AddDataForm extends JFrame {
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel valueLabel;
    private JTextField valueField;
    private JButton submitButton;
    private JButton resetButton;

    public AddDataForm() {
        initComponents();
    }

    private void initComponents() {
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        valueLabel = new JLabel("Value:");
        valueField = new JTextField(20);
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        setLayout(new java.awt.GridLayout(3, 2));
        add(nameLabel);
        add(nameField);
        add(valueLabel);
        add(valueField);
        add(submitButton);
        add(resetButton);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void submitButtonActionPerformed(ActionEvent evt) {
        String name = nameField.getText();
        String value = valueField.getText();
        if (name.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try (FileWriter writer = new FileWriter("data.csv", true)) {
                writer.append(name).append(",").append(value).append("\n");
                JOptionPane.showMessageDialog(this, "Data added successfully");
                nameField.setText("");
                valueField.setText("");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error writing to file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetButtonActionPerformed(ActionEvent evt) {
        nameField.setText("");
        valueField.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDataForm().setVisible(true);
            }
        });
    }
}
