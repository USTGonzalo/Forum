package com.example.foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foro.Db.ForumDatabase;
import java.util.List;

public class ForoActivity extends AppCompatActivity {

    private ListView listViewForo;
    private ImageButton btnNewPublish;
    private ForumDatabase forumDatabase;
    private List<String> publicaciones; // Guardamos la lista actual
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        // Inicializar componentes
        listViewForo = findViewById(R.id.listViewForo);
        btnNewPublish = findViewById(R.id.BtnNewPublish);
        forumDatabase = new ForumDatabase(this);

        // Cargar publicaciones
        loadPublications();

        // 🟢 Crear nueva publicación
        btnNewPublish.setOnClickListener(v -> {
            Intent intent = new Intent(ForoActivity.this, AddPublicationActivity.class);
            startActivity(intent);
        });

        // 🟡 Abrir Update/Delete al hacer clic en una publicación
        listViewForo.setOnItemClickListener((parent, view, position, id) -> {
            String item = publicaciones.get(position);

            // Formato actual: "ID: 1\nTítulo\nMensaje"
            String[] parts = item.split("\n", 3);
            if (parts.length < 3) return; // seguridad

            String idStr = parts[0].replace("ID:", "").trim();
            String title = parts[1];
            String message = parts[2];

            Intent intent = new Intent(ForoActivity.this, UpdateDeleteActivity.class);
            intent.putExtra("id", idStr);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            startActivity(intent);
        });
    }

    // 🟢 Cargar publicaciones desde la base de datos
    private void loadPublications() {
        publicaciones = forumDatabase.getAllPublications();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                publicaciones
        );

        listViewForo.setAdapter(adapter);
    }

    // 🟠 Cada vez que se vuelve a esta pantalla, recargar lista (por ejemplo, tras editar o eliminar)
    @Override
    protected void onResume() {
        super.onResume();
        loadPublications();
    }
}
