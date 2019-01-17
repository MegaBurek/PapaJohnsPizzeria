package com.example.aleksej.papajohnspizzeria;

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
}
