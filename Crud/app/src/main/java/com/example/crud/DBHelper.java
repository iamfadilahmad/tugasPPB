package com.example.crud;

import android.annotation.SuppressLint;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLInput;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    public static final String DBNAME = "mhs";

    public DBHelper(Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB){
        DB.execSQL("CREATE TABLE mhs(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , nrp TEXT UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1){
        DB.execSQL("DROP TABLE IF EXISTS mhs");
    }

    public Boolean insertData(String name, String nrp){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); contentValues.put("name", name); contentValues.put("nrp", nrp);
        long result = DB.insert("mhs", null, contentValues);
        if(result==-1) return false;

        return true;
    }

    @SuppressLint("Range")
    public String[] checknrp(String nrp){
        SQLiteDatabase DB = this.getWritableDatabase();
        String[] result = new String[2];

        Cursor cursor = DB.rawQuery("SELECT * FROM mhs WHERE nrp = ?", new String[]{nrp});

        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                result[0] = cursor.getString(cursor.getColumnIndex("name"));
                result[1] = cursor.getString(cursor.getColumnIndex("nrp"));
            } else {
                result[0] = null;
                result[1]= null;
            }
        }

        cursor.close();
        return result;
    }

    @SuppressLint("Range")
    public String[] checkname(String name){
        SQLiteDatabase DB = this.getWritableDatabase();

        String[] result = new String[2];

        Cursor cursor = DB.rawQuery("SELECT * FROM mhs WHERE name = ?",new String[]{name});

        if(cursor.getCount()>0){
            result[0] = cursor.getString(cursor.getColumnIndex("name"));
            result[1] = cursor.getString(cursor.getColumnIndex("nrp"));
        }else{
            result[0] = null;
            result[1] = null;
        }

        cursor.close();
        return result;
    }

    public boolean updateName(String newName, String nrp){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", newName);
        String whClause = "nrp = ?";
        String[] whArgs = {nrp};
        int rowsUpdated = DB.update("mhs", contentValues, whClause, whArgs);

        return rowsUpdated > 0;
    }

    public boolean deleteData(String namedelete){
        SQLiteDatabase DB = this.getWritableDatabase();
        String whClause = "name = ?";
        String[] whArgs = {namedelete};
        int rowsDeleted = DB.delete("mhs", whClause, whArgs);

        return rowsDeleted > 0;
    }

    @SuppressLint("Range")
    public String[] showAll(){
        ArrayList<String> db = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT name,nrp FROM mhs",null);

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String showName = cursor.getString(cursor.getColumnIndex("name"));
                String showNrp = cursor.getString(cursor.getColumnIndex("nrp"));
            }
        }
        cursor.close();

        String[] dbArray = db.toArray(new String[db.size()]);

        return dbArray;
    }
}
