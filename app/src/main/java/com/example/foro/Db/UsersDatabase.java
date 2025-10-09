package com.example.foro.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersDatabase {

    private DbHelper dbHelper;

    public UsersDatabase(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Insertar nuevo usuario
    public long insertUser(String name, String lastName, String username, String password, String birthDate) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersContract.UsersEntry.COLUMN_NAME, name);
        values.put(UsersContract.UsersEntry.COLUMN_LAST, lastName);
        values.put(UsersContract.UsersEntry.COLUMN_USER, username);
        values.put(UsersContract.UsersEntry.COLUMN_PASS, password);
        values.put(UsersContract.UsersEntry.COLUMN_DATE, birthDate);

        return db.insert(UsersContract.UsersEntry.TABLE_NAME, null, values);
    }

    // Verificar usuario y contraseña
    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = { UsersContract.UsersEntry.COLUMN_ID };
        String selection = UsersContract.UsersEntry.COLUMN_USER + "=? AND " + UsersContract.UsersEntry.COLUMN_PASS + "=?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.query(
                UsersContract.UsersEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
