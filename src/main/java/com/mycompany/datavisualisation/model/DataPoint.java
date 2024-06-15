package com.mycompany.datavisualisation.model;

public class DataPoint {
    private String name;
    private double value;

    public DataPoint(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
