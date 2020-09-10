package com.example.delta_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mylist.db";

    public DatabaseHelper(@Nullable Context context ){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table tbl_favourites(ID integer primary key autoincrement ,name text, imageurl text)";

        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS tbl_favourites");
      onCreate(db);
    }

    public String addRecord(String p1,String p2){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",p1);
        cv.put("imageurl",p2);

        long res= db.insert("tbl_favourites",null,cv);
        if(res==-1){
            return "Failed";
        }else return "Successfully Inserted";
    }
    public Cursor readAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query ="select * from tbl_favourites order by id desc";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }

    public void deleteitem(model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tbl_favourites", "name" + " =?", new String[]{String.valueOf(model.getName())});
        db.close();
    }
}
