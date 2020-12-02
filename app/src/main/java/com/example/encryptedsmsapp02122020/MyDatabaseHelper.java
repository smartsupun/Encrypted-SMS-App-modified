package com.example.encryptedsmsapp02122020;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABASE_NAME = "chat_sms.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "sms";
    public static final String USER_ID = "user_id";
    public static final String PHONE_NO = "phone";
    public static final String MESSAGE = "msg";



    public MyDatabaseHelper(@Nullable Context context) {
        super( context, DATABASE_NAME, null, VERSION );
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY= "CREATE TABLE " + TABLE_NAME +
                "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PHONE_NO +  " TEXT, " +
                MESSAGE + " TEXT)";
        //run table create query
        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY="DROP TABLE IF EXISTS "+TABLE_NAME;
        //drop older table if executed
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);

    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
