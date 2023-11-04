package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    DBCreateTable dbCT;
    SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "SQLQuanLyLuong", null, 1);
        dbCT = new DBCreateTable(context);
        sqLiteDatabase = dbCT.getWritableDatabase();

    }
    public void insertUser(String username, String password){
        ContentValues values = new ContentValues();

        values.put(DbInitTable.COL_NAME_USER, username);
        values.put(DbInitTable.COL_PASSWORD, password);
        sqLiteDatabase.insert(DbInitTable.TABLE_USER, null, values);
        sqLiteDatabase.close();
    }
    public Boolean checkAccount(String username, String password){
        sqLiteDatabase = dbCT.getReadableDatabase();
        String sql = "SELECT * FROM " + DbInitTable.TABLE_USER + " WHERE " + DbInitTable.COL_NAME_USER + " =?" + " AND " + DbInitTable.COL_PASSWORD + " =?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{username, password});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlPhongBan = "Create table PhongBan (mapb text PRIMARY KEY NOT NULL , tenpb text) ";
        sqLiteDatabase.execSQL(sqlPhongBan);

        String sqlNhanVien = "Create table NhanVien (manv text PRIMARY KEY NOT NULL, tennv text, ngaysinh text, gioitinh text,mapb text, hesoluong text, imagenv Blob)";
        sqLiteDatabase.execSQL(sqlNhanVien);

        String sqlChamCong = "Create table ChamCong (manv text, ngaychamcong text, songaycong text)";
        sqLiteDatabase.execSQL(sqlChamCong);

        String sqlTamUng = "Create table TamUng (sophieu text PRIMARY KEY NOT NULL, ngaytamung text, sotienung text, manv text)";
        sqLiteDatabase.execSQL(sqlTamUng);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
