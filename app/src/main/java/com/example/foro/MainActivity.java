package com.example.foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foro.Db.UsersDatabase;

public class MainActivity extends AppCompatActivity {

    UsersDatabase db = new UsersDatabase(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView NewAccount = findViewById(R.id.TxtNewAccount);
        NewAccount.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
            startActivity(intent);
        });

        Button Login = findViewById(R.id.BtnLogin);
        Login.setOnClickListener(v -> {
            EditText usernameField = findViewById(R.id.TxtUser);
            EditText passwordField = findViewById(R.id.TxtPass);

            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            boolean isValid = db.checkUserCredentials(username, password);

            if (isValid) {
                Intent intent = new Intent(MainActivity.this, ForoActivity.class);
                startActivity(intent);
            } else {
                // Mostrar error
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}