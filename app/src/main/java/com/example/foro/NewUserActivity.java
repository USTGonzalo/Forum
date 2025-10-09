package com.example.foro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foro.Db.UsersDatabase;

public class NewUserActivity extends AppCompatActivity {

    UsersDatabase usersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_user);

        usersDatabase = new UsersDatabase(this);

        EditText firstName = findViewById(R.id.TxtFirstName);
        EditText lastName  = findViewById(R.id.TxtLastName);
        EditText nick      = findViewById(R.id.TxtNameUser);
        EditText password  = findViewById(R.id.TxtPassword);
        EditText birth     = findViewById(R.id.TxtDate);

        Button enviarDatos = findViewById(R.id.BtnCreate);
        //Botón para ingresar datos nuevos (usuarios)
        enviarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name   = firstName.getText().toString();
                String second = lastName.getText().toString();
                String user   = nick.getText().toString();
                String pass   = password.getText().toString();
                String date   = birth.getText().toString();

                long newRowId = usersDatabase.insertUser(name, second, user, pass, date);

                if (newRowId != -1) {

                    //Si la condición se cumple, avisar y eliminar todo el texto.
                    Toast.makeText(NewUserActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    firstName.setText("");
                    lastName.setText("");
                    nick.setText("");
                    password.setText("");
                    birth.setText("");
                } else {
                    Toast.makeText(NewUserActivity.this, "No se ha logrado registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
