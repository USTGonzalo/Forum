package com.example.foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foro.Db.ForumDatabase;

public class UpdateDeleteActivity extends AppCompatActivity {

    private EditText txtRename, txtNewMessage;
    private Button btnSave, btnDelete;
    private ForumDatabase forumDatabase;
    private long id;
    private int userId;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        // Inicializar vistas
        txtRename = findViewById(R.id.TxtRename);
        txtNewMessage = findViewById(R.id.TxtNewMessage);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        forumDatabase = new ForumDatabase(this);

        // Obtener datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getLongExtra("id", -1);
            userId = intent.getIntExtra("userId", -1);
            time = intent.getStringExtra("time");

            if (id == -1) {
                Toast.makeText(this, "Error: ID no válido", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            txtRename.setText(intent.getStringExtra("title"));
            txtNewMessage.setText(intent.getStringExtra("message"));
        }

        // Botón de actualizar
        btnSave.setOnClickListener(v -> {
            String newTitle = txtRename.getText().toString().trim();
            String newMessage = txtNewMessage.getText().toString().trim();

            if (newTitle.isEmpty() || newMessage.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int rowsUpdated = forumDatabase.updatePublication(id, newTitle, newMessage);
            if (rowsUpdated > 0) {
                Toast.makeText(this, "Publicación actualizada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón de eliminar
        btnDelete.setOnClickListener(v -> {
            int rowsDeleted = forumDatabase.deletePublication(id);
            if (rowsDeleted > 0) {
                Toast.makeText(this, "Publicación eliminada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
