package com.example.foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foro.Db.ForumDatabase;

public class UpdateDeleteActivity extends AppCompatActivity {

    EditText txtRename, txtNewMessage;
    Button btnSave, btnDelete;
    ForumDatabase forumDatabase;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        txtRename = findViewById(R.id.TxtRename);
        txtNewMessage = findViewById(R.id.TxtNewMessage);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        forumDatabase = new ForumDatabase(this);

        // Obtener los datos enviados desde el intent
        Intent intent = getIntent();
        if (intent != null) {
            id = Long.parseLong(intent.getStringExtra("id"));
            txtRename.setText(intent.getStringExtra("title"));
            txtNewMessage.setText(intent.getStringExtra("message"));
        }

        // Botón de actualizar
        btnSave.setOnClickListener(v -> {
            String newTitle = txtRename.getText().toString();
            String newMessage = txtNewMessage.getText().toString();

            if (forumDatabase.updatePublication(id, newTitle, newMessage) > 0) {
                Toast.makeText(this, "Publicación actualizada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón de eliminar
        btnDelete.setOnClickListener(v -> {
            if (forumDatabase.deletePublication(id) > 0) {
                Toast.makeText(this, "Publicación eliminada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
