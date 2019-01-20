package com.example.aleksej.papajohnspizzeria;

import org.json.JSONObject;

import java.util.Date;

public class Reservation{

    private int resID;
    private int tableNo;
    private String name, date, time;

    public Reservation(int resID, int tableNo,String name, String date, String time) {
        this.resID = resID;
        this.tableNo = tableNo;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Reservation(){

    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static Reservation parseJSON(JSONObject object){
        Reservation reservation = new Reservation();

        try{
            if(object.has("resID")){
                reservation.setResID(object.getInt("resID"));
            }

            if(object.has("tableNo")){
                reservation.setTableNo(object.getInt("tableNo"));
            }

            if(object.has("name")){
                reservation.setName(object.getString("name"));
            }

            if(object.has("date")){
                reservation.setDate(object.getString("date"));
            }

            if(object.has("time")){
                reservation.setTime(object.getString("time"));
            }

        }catch(Exception e){
            System.out.println("Error from Reservation class: " + e);
        }

        return reservation;
    }
}
