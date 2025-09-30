package com.example.foro;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foro.Db.DbHelper;
import com.example.foro.Db.UsersContract;

public class NewUserActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_user);

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        EditText firstName = findViewById(R.id.TxtFirstName);
        EditText lastName = findViewById(R.id.TxtLastName);
        EditText nick = findViewById(R.id.TxtNameUser);
        EditText password = findViewById(R.id.TxtPassword);
        EditText birth = findViewById(R.id.TxtDate);

        Button enviarDatos = findViewById(R.id.BtnCreate);
        enviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name   = firstName.getText().toString();
                String second = lastName.getText().toString();
                String user   = nick.getText().toString();
                String pass   = password.getText().toString();
                String date   = birth.getText().toString();

                ContentValues values = new ContentValues();
                values.put(UsersContract.UsersEntry.COLUMN_NAME, name);
                values.put(UsersContract.UsersEntry.COLUMN_LAST, second);
                values.put(UsersContract.UsersEntry.COLUMN_USER, user);
                values.put(UsersContract.UsersEntry.COLUMN_PASS, pass);
                values.put(UsersContract.UsersEntry.COLUMN_DATE, date);

                long newRowId = db.insert(UsersContract.UsersEntry.TABLE_NAME, null, values);

                if (newRowId != -1) {
                    Toast.makeText(NewUserActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewUserActivity.this, "No se ha logrado registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
