package com.example.aleksej.papajohnspizzeria;

import java.util.Date;

public class Reservation{

    public static final String TABLE_NAME="Users";
    public static final String FIELD_RES_ID="res_id";
    public static final String FIELD_TABLE_No="tableNo";
    public static final String FIELD_NAME="name";
    public static final String FIELD_DATE="date";
    public static final String FIELD_TIME="time";


    private int resID;
    private int tableNo;
    private String name;
    private Date date;
    private Date time;

    public Reservation(int resID, int tableNo,String name, Date date, Date time) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
