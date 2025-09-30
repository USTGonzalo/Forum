package com.example.foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
            Intent intent = new Intent(MainActivity.this, ForoActivity.class);
            startActivity(intent);
        });
    }
}