package com.example.aleksej.papajohnspizzeria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReservationsRepo{

    private Database database;

    public ReservationsRepo(Database db){
        this.database = db;
    }

    public void addReservation(int tableNo, String name, Date date, Date time){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Reservation.FIELD_TABLE_No, tableNo);
        cv.put(Reservation.FIELD_NAME, name);
        cv.put(Reservation.FIELD_DATE, date.toString());
        cv.put(Reservation.FIELD_TIME, time.toString());

        db.insert(Reservation.TABLE_NAME, null, cv);
    }

    public void editReservation(int resId, int tableNo, String name, Date date, Date time){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Reservation.FIELD_TABLE_No, tableNo);
        cv.put(Reservation.FIELD_NAME, name);
        cv.put(Reservation.FIELD_DATE, date.toString());
        cv.put(Reservation.FIELD_TIME, time.toString());

        db.update(Reservation.TABLE_NAME, cv, Reservation.FIELD_RES_ID + "=?", new String[] {String.valueOf(resId)});
    }

    public int deleteReservation(int resId){
        int numDeleted = 0;

        SQLiteDatabase db = database.getWritableDatabase();
        numDeleted = db.delete(User.TABLE_NAME, Reservation.FIELD_RES_ID + "=?", new String[] {String.valueOf(resId)});
        return numDeleted;
    }

    public void getReservationById(int resId){
        SQLiteDatabase db = database.getReadableDatabase();
        // SELECT * FROM oglas WHERE oglas_id = ?
        String query = String.format("SELECT * FROM %s WHERE %s = ?", User.TABLE_NAME, Reservation.FIELD_RES_ID);
        Cursor result = db.rawQuery(query, new String[] {String.valueOf(resId)});
        if(result.moveToFirst()) { //ako ima
            int tableNo = result.getInt(result.getColumnIndex(Reservation.FIELD_TABLE_No));
            String name = result.getString(result.getColumnIndex(Reservation.FIELD_NAME));
            String date = result.getString(result.getColumnIndex(Reservation.FIELD_DATE));
            String time = result.getString(result.getColumnIndex(Reservation.FIELD_TIME));
            result.close();



        }
    }


    public List<Reservation> getAllReservations() throws ParseException {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s", User.TABLE_NAME);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        List<Reservation> list = new ArrayList<Reservation>(result.getCount());

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd , EEE ", Locale.ENGLISH);
        DateFormat timeFormat = new SimpleDateFormat("h:mm", Locale.ENGLISH);
        while(!result.isAfterLast()){ //dok nismo izasli posle poslednjeg
            int resId = result.getInt(result.getColumnIndex(Reservation.FIELD_RES_ID));
            int tableNo = result.getInt(result.getColumnIndex(Reservation.FIELD_TABLE_No));
            String name = result.getString(result.getColumnIndex(Reservation.FIELD_NAME));

            String unparsedDate = result.getString(result.getColumnIndex(Reservation.FIELD_DATE));
            Date date = dateFormat.parse(unparsedDate);

            String unparsedTime = result.getString(result.getColumnIndex(Reservation.FIELD_TIME));
            Date time = timeFormat.parse(unparsedTime);


            list.add(new Reservation(resId, tableNo, name, date, time));
            result.moveToNext();
        }
        result.close();
        return list;
    }
}

