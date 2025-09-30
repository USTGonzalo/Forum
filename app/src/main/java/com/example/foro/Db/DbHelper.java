package com.example.foro.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "forum.db";

    private static final String SQL_CREATE_ENTRIES_USERS =
            "CREATE TABLE " + UsersContract.UsersEntry.TABLE_NAME + " (" +
                    UsersContract.UsersEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UsersContract.UsersEntry.COLUMN_NAME + " VARCHAR(64) NOT NULL," +
                    UsersContract.UsersEntry.COLUMN_LAST + " VARCHAR(64) NOT NULL," +
                    UsersContract.UsersEntry.COLUMN_USER + " VARCHAR(64) NOT NULL," +
                    UsersContract.UsersEntry.COLUMN_PASS + " TEXT NOT NULL," +
                    UsersContract.UsersEntry.COLUMN_DATE + " DATE NOT NULL" +
                    ");";

    private static final String SQL_CREATE_ENTRIES_FORUM =
            "CREATE TABLE " + ForumContract.ForumEntry.TABLE_NAME + " (" +
                    ForumContract.ForumEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ForumContract.ForumEntry.COLUMN_TITLE + " VARCHAR(64) NOT NULL," +
                    ForumContract.ForumEntry.COLUMN_MESSAGE + " TEXT NOT NULL" +
                    ");";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USERS);
        db.execSQL(SQL_CREATE_ENTRIES_FORUM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UsersContract.UsersEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ForumContract.ForumEntry.TABLE_NAME);
        onCreate(db);
    }
}
