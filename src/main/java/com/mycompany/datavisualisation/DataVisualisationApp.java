package com.mycompany.datavisualisation;

import com.mycompany.datavisualisation.controller.DataVisualController;
import com.mycompany.datavisualisation.model.DataVisualModel;
import com.mycompany.datavisualisation.view.DataVisualView;

public class DataVisualisationApp {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            DataVisualController controller = new DataVisualController();
            controller.getView().setVisible(true);
        });
    }
    

}
