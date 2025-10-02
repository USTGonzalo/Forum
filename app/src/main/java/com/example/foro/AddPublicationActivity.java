package com.example.foro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

        EditText title   = findViewById(R.id.TxtTitle);
        EditText message = findViewById(R.id.TxtMessage);
        Button btnBack   = findViewById(R.id.BtnCancel);
        Button btnCreate = findViewById(R.id.BtnSend);

        btnBack.setOnClickListener(v -> finish());

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText   = title.getText().toString();
                String messageText = message.getText().toString();

                long newRowId = forumDatabase.insertPublication(titleText, messageText);

                if (newRowId != -1) {
                    Toast.makeText(AddPublicationActivity.this, "Publicación creada correctamente", Toast.LENGTH_SHORT).show();
                    title.setText("");
                    message.setText("");
                } else {
                    Toast.makeText(AddPublicationActivity.this, "Error al crear la publicación", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}