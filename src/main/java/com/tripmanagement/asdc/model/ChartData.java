package com.tripmanagement.asdc.model;

public class ChartData {
    public ChartData() {
    }

    String[] label;
    String[] backgroundColor;
    float[] data;

    public ChartData(String[] label, String[] backgroundColor, float[] data) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.data = data;
    }

    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }

    public String[] getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String[] backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }
}
