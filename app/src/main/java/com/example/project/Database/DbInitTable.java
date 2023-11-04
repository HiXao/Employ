package com.example.project.Database;

public class DbInitTable {
    public static final String DATABASE_NANE = "ManageVolunteer.db";
    public static final String TABLE_USER = "user";
    public static final String COL_ID_USER = "id_user";
    public static final String COL_NAME_USER = "username";
    public static final String COL_PASSWORD = "password";

    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " ("
            + COL_ID_USER + " INTEGER PRIMARY KEY, "
            + COL_NAME_USER + " TEXT, "
            + COL_PASSWORD + " TEXT)";

    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER;
}