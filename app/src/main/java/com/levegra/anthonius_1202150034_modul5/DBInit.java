package com.levegra.anthonius_1202150034_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBInit extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase database;

    public static final String dbName = "listtodo.db";
    public static final String tablesName = "daftartodo";
    public static final String column1 = "todo";
    public static final String column2 = "description";
    public static final String column3 = "priority";

    //Constructor
    public DBInit(Context context) {
        super(context, dbName, null, 1);
        this.context = context;
        database = this.getWritableDatabase();
        database.execSQL("create table if not exists "+ tablesName +" (todo varchar(25) primary key, description varchar(225), priority varchar(2))");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+ tablesName +" (todo varchar(25) primary key, description varchar(225), priority varchar(2))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ tablesName);
        onCreate(sqLiteDatabase);
    }

    public boolean inputdata(ModelAddData list) {
        ContentValues val = new ContentValues();
        val.put(column1, list.getTodo());
        val.put(column2, list.getDesc());
        val.put(column3, list.getPrior());
        long res = database.insert(tablesName, null, val);
        if (res==-1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean popData(String ToDo) {
        return database.delete(tablesName, column1+"=\""+ToDo+"\"", null)>0;
    }

    public void readData(ArrayList<ModelAddData> daftar){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, priority from "+ tablesName, null);
        while (cursor.moveToNext()){
            daftar.add(new ModelAddData(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}


