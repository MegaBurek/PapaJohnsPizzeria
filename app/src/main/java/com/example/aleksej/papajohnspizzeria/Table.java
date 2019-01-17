package com.example.aleksej.papajohnspizzeria;

import org.json.JSONObject;

public class Table {

    private int tableNo,chairs;
    private String type,smokers;
    private boolean reserved;

    public Table(){

    }

    public Table(int tableNo, String type, String smokers, int chairs, boolean reserved){
        this.tableNo = tableNo;
        this.type = type;
        this.smokers = smokers;
        this.chairs = chairs;
        this.reserved = reserved;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
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

    public String getSmokers() {
        return smokers;
    }

    public void setSmokers(String shape) {
        this.smokers = shape;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public static Table parseJSON(JSONObject object){
        Table table = new Table();

        try{
            if(object.has("tableNo")){
                table.setTableNo(object.getInt("tableNo"));
            }

            if(object.has("chairs")){
                table.setChairs(object.getInt("chairs"));
            }

            if(object.has("type")){
                table.setType(object.getString("type"));
            }

            if(object.has("smokers")){
                table.setSmokers(object.getString("smokers"));
            }

            if(object.has("reserved")){
                table.setSmokers(object.getString("reserved"));
            }

        }catch(Exception e){

        }

        return table;
    }

}
