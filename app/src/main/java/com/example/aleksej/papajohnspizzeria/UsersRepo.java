package com.example.aleksej.papajohnspizzeria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UsersRepo{

    private static Database database;

    public UsersRepo(Database db){
        this.database = db;
    }


    public void addUser(String name, String surname, String username, String password, int reserves){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(User.FIELD_NAME, name);
        cv.put(User.FIELD_SURNAME, surname);
        cv.put(User.FIELD_USERNAME, username);
        cv.put(User.FIELD_PASSWORD, password);
        cv.put(User.FIELD_RESERVE, reserves);
        System.out.println(cv);

        db.insert(User.TABLE_NAME, null, cv);
    }

    public static boolean checkLogIn(String username, String password){
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT username,password FROM %s WHERE username = %s AND password = %s", User.TABLE_NAME, username, password);
        Cursor result = db.rawQuery(query, new String[]{});
        System.out.println(result.moveToFirst());
        if(result.moveToFirst()) {
            return true;
        } else{
            return false;
        }
    }

//    public void getUserById(int resId){
//        SQLiteDatabase db = this.getReadableDatabase();
//        // SELECT * FROM oglas WHERE oglas_id = ?
//        String query = String.format("SELECT * FROM %s WHERE %s = ?", User.TABLE_NAME, User.FIELD_RES_ID);
//        Cursor result = db.rawQuery(query, new String[] {String.valueOf(resId)});
//        if(result.moveToFirst()) { //ako ima
//            int tableNo = result.getInt(result.getColumnIndex(User.FIELD_TABLE_No));
//            String name = result.getString(result.getColumnIndex(User.FIELD_NAME));
//            String date = result.getString(result.getColumnIndex(User.FIELD_DATE));
//            String time = result.getString(result.getColumnIndex(User.FIELD_TIME));
//            result.close();
//
//
//
//        }
//    }


    public static List<User> getAllUsers() throws ParseException {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = String.format("SELECT * FROM %s", User.TABLE_NAME);
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        List<User> list = new ArrayList<User>(result.getCount());
        while(!result.isAfterLast()){ //dok nismo izasli posle poslednjeg
            int userID = result.getInt(result.getColumnIndex(User.FIELD_USER_ID));
            String name = result.getString(result.getColumnIndex(User.FIELD_NAME));
            String surname = result.getString(result.getColumnIndex(User.FIELD_SURNAME));
            String username = result.getString(result.getColumnIndex(User.FIELD_USERNAME));
            String password = result.getString(result.getColumnIndex(User.FIELD_PASSWORD));
            int reserves = result.getInt(result.getColumnIndex(User.FIELD_RESERVE));

            list.add(new User(userID,name,surname,username,password,reserves));

            result.moveToNext();
        }
        result.close();
        return list;
    }
}

