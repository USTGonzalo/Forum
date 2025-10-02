package com.example.foro.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ForumDatabase {

    private DbHelper dbHelper;

    public ForumDatabase(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Crear nueva publicación
    public long insertPublication(String title, String message) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ForumContract.ForumEntry.COLUMN_TITLE, title);
        values.put(ForumContract.ForumEntry.COLUMN_MESSAGE, message);

        return db.insert(ForumContract.ForumEntry.TABLE_NAME, null, values);
    }

    // Eliminar publicación por ID
    public int deletePublication(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                ForumContract.ForumEntry.TABLE_NAME,
                ForumContract.ForumEntry.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }
}
