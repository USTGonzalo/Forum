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
import com.example.foro.Db.ForumContract;

public class AddPublicationActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_publication);

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        EditText title   = findViewById(R.id.TxtTitle);
        EditText message = findViewById(R.id.TxtMessage);
        Button btnBack = findViewById(R.id.BtnCancel);
        btnBack.setOnClickListener(v->{finish();});

        Button btnCreate = findViewById(R.id.BtnSend);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText   = title.getText().toString();
                String messageText = message.getText().toString();

                ContentValues values = new ContentValues();
                values.put(ForumContract.ForumEntry.COLUMN_TITLE, titleText);
                values.put(ForumContract.ForumEntry.COLUMN_MESSAGE, messageText);

                long newRowId = db.insert(ForumContract.ForumEntry.TABLE_NAME, null, values);

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
