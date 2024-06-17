package com.mycompany.datavisualisation.view;

import com.mycompany.datavisualisation.model.DataPoint;
import com.mycompany.datavisualisation.model.DataVisualModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class DataDisplay extends JPanel {
    private DataVisualModel model;
    private JTable table;
    private DefaultTableModel tableModel;

    public DataDisplay(DataVisualModel model) {
        this.model = model;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Name", "Value"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTable(List<DataPoint> data, String title) {
        tableModel.setRowCount(0);
        for (DataPoint dataPoint : data) {
            tableModel.addRow(new Object[]{dataPoint.getName(), dataPoint.getValue()});
        }
        table.repaint();
    }
}
