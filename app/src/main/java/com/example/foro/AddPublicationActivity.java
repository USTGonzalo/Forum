package com.example.foro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foro.Db.ForumDatabase;

public class AddPublicationActivity extends AppCompatActivity {

    ForumDatabase forumDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_publication);

        forumDatabase = new ForumDatabase(this);

        EditText title = findViewById(R.id.TxtTitle);
        EditText message = findViewById(R.id.TxtMessage);
        Button btnBack = findViewById(R.id.BtnCancel);
        Button btnCreate = findViewById(R.id.BtnSend);

        btnBack.setOnClickListener(v -> finish());

        btnCreate.setOnClickListener(v -> {
            String titleText = title.getText().toString().trim();
            String messageText = message.getText().toString().trim();

            // Validación básica
            if (titleText.isEmpty() || messageText.isEmpty()) {
                Toast.makeText(AddPublicationActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtener el ID del usuario actual (ejemplo usando SharedPreferences)
            SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
            int currentUserId = prefs.getInt("userId", -1);
            if (currentUserId == -1) {
                Toast.makeText(this, "Usuario no identificado", Toast.LENGTH_SHORT).show();
                return;
            }

            long newRowId = forumDatabase.insertPublication(titleText, messageText, currentUserId);

            if (newRowId != -1) {
                Toast.makeText(AddPublicationActivity.this,
                        "Publicación creada correctamente. ID: " + newRowId, Toast.LENGTH_SHORT).show();
                title.setText("");
                message.setText("");
                finish();
            } else {
                Toast.makeText(AddPublicationActivity.this,
                        "Error al crear la publicación",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
