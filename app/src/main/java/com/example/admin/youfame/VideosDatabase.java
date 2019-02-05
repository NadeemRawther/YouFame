package com.example.admin.youfame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class VideosDatabase extends SQLiteOpenHelper


{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Feeder.db";

    public VideosDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE VID(" + "_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME);");


    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Nothing ");

    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertDatabase(SQLiteDatabase db,int position, String Ways) {
        ContentValues values = new ContentValues();
        values.put("NAME", Ways);
        if(db!=null){
           db.update("VID", values, "_id = ?", new String[]{Integer.toString(position)});
        }
        else  {

            db.insert("VID",null,values);
       }

    }
}



