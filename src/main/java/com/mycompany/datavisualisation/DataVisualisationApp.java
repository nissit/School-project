package com.mycompany.datavisualisation;

import com.mycompany.datavisualisation.controller.DataVisualController;

public class DataVisualisationApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            DataVisualController controller = new DataVisualController();
            controller.getView().setVisible(true);
        });
    }
}
