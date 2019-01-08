package com.example.aleksej.papajohnspizzeria;

public class Table {

    private int chairs;
    private String type,shape;

    public Table(String type, String shape, int chairs){
        this.type = type;
        this.shape = shape;
        this.chairs = chairs;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
