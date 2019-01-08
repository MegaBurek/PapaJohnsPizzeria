package com.example.aleksej.papajohnspizzeria;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Papa.Johns";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL1 = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s INTEGER, %s TEXT, %s TEXT, %s TEXT);",
                Reservation.TABLE_NAME, Reservation.FIELD_RES_ID, Reservation.FIELD_TABLE_No, Reservation.FIELD_NAME, Reservation.FIELD_DATE, Reservation.FIELD_TIME);

        String SQL2 = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s INTEGER, %s TEXT, %s TEXT, %s TEXT);",
                User.TABLE_NAME, User.FIELD_NAME, User.FIELD_SURNAME, User.FIELD_USERNAME, User.FIELD_PASSWORD, User.FIELD_RESERVE);

        db.execSQL(SQL1);
        db.execSQL(SQL2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s;", Reservation.TABLE_NAME));
        db.execSQL(String.format("DROP TABLE IF EXISTS %s;", User.TABLE_NAME));

        onCreate(db);
    }

}

