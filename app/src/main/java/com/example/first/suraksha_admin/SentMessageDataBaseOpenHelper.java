package com.example.first.suraksha_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 9/10/17.
 */

public class SentMessageDataBaseOpenHelper extends SQLiteOpenHelper {

    public SentMessageDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table SentMessageTable(problem text,time text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addProblem(String problem,String time){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("problem",problem);
        contentValues.put("time",time);
        long c=db.insert("SentMessageTable",null,contentValues);
        db.close();
        if(c>=0)
            return true;
        else
            return false;
    }
}
