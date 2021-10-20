package com.example.caovuongbach_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Feedback.db";
    public static final String TABLE_NAME = "TBL_Feedback";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create Table " +TABLE_NAME+ " ( "+
                TBL_Feedback.id + " INTEGER PRIMARY KEY, "+
                TBL_Feedback.name + " TEXT, "+
                TBL_Feedback.mail + " TEXT, "+
                TBL_Feedback.gender + " TEXT, "+
                TBL_Feedback.content + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "Drop Table If Exists "+ TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

    public String insertDB(FeedBack feedBack){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TBL_Feedback.name, feedBack.getName());
        cv.put(TBL_Feedback.mail, feedBack.getMail());
        cv.put(TBL_Feedback.gender, feedBack.getGender());
        cv.put(TBL_Feedback.content, feedBack.getContent());

        long isSuccess = db.insert(TABLE_NAME, null, cv);
        if(isSuccess > 0){
            return "Success";
        } else {
            return "Failed";
        }
    }

    public List<FeedBack> getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Select * From "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        List<FeedBack> list = new ArrayList<>();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                FeedBack feedBack = new FeedBack();
                feedBack.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TBL_Feedback.id)));
                feedBack.setName(cursor.getString(cursor.getColumnIndexOrThrow(TBL_Feedback.name)));
                feedBack.setMail(cursor.getString(cursor.getColumnIndexOrThrow(TBL_Feedback.mail)));
                feedBack.setGender(cursor.getString(cursor.getColumnIndexOrThrow(TBL_Feedback.gender)));
                feedBack.setContent(cursor.getString(cursor.getColumnIndexOrThrow(TBL_Feedback.content)));

                list.add(feedBack);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public String updateDB(FeedBack feedBack){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TBL_Feedback.name, feedBack.getName());
        cv.put(TBL_Feedback.mail, feedBack.getMail());
        cv.put(TBL_Feedback.gender, feedBack.getGender());
        cv.put(TBL_Feedback.content, feedBack.getContent());
        long isSuccess = db.update(TABLE_NAME,cv,"id="+feedBack.getId(),null);
        if(isSuccess > 0){
            return "Update Success";
        } else {
            return "Update Failed";
        }
    }

    public String deleteDB(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long isSuccess = db.delete(TABLE_NAME,"id="+id,null);
        if(isSuccess > 0 ){
            return "Delete Success";
        } else {
            return "Delete Failed";
        }
    }

}

