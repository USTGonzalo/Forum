package com.example.foro.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ForumDatabase {

    private DbHelper dbHelper;

    public ForumDatabase(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Crear nueva publicación con usuario
    public long insertPublication(String title, String message, int userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ForumContract.ForumEntry.COLUMN_TITLE, title);
        values.put(ForumContract.ForumEntry.COLUMN_MESSAGE, message);
        values.put(ForumContract.ForumEntry.COLUMN_USER, userId);
        // La columna time se asigna automáticamente con datetime('now','localtime')
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

    // Obtener todas las publicaciones como lista de objetos Publication
    public List<Publication> getAllPublications() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Publication> publications = new ArrayList<>();

        Cursor cursor = db.query(
                ForumContract.ForumEntry.TABLE_NAME,
                new String[]{
                        ForumContract.ForumEntry.COLUMN_ID,
                        ForumContract.ForumEntry.COLUMN_TITLE,
                        ForumContract.ForumEntry.COLUMN_MESSAGE,
                        ForumContract.ForumEntry.COLUMN_USER,
                        ForumContract.ForumEntry.COLUMN_TIME
                },
                null, null, null, null,
                ForumContract.ForumEntry.COLUMN_ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(ForumContract.ForumEntry.COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(ForumContract.ForumEntry.COLUMN_TITLE));
                String message = cursor.getString(cursor.getColumnIndexOrThrow(ForumContract.ForumEntry.COLUMN_MESSAGE));
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow(ForumContract.ForumEntry.COLUMN_USER));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(ForumContract.ForumEntry.COLUMN_TIME));

                publications.add(new Publication(id, title, message, userId, time));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return publications;
    }

    // Actualizar publicación
    public int updatePublication(long id, String newTitle, String newMessage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ForumContract.ForumEntry.COLUMN_TITLE, newTitle);
        values.put(ForumContract.ForumEntry.COLUMN_MESSAGE, newMessage);
        // No actualizamos usuario ni hora aquí
        return db.update(
                ForumContract.ForumEntry.TABLE_NAME,
                values,
                ForumContract.ForumEntry.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }
}