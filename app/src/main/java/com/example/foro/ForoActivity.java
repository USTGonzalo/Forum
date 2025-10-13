package com.example.foro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foro.Db.ForumDatabase;
import com.example.foro.Db.Publication;
import java.util.List;

public class ForoActivity extends AppCompatActivity {

    private ListView listViewForo;
    private ImageButton btnNewPublish;
    private ForumDatabase forumDatabase;
    private List<Publication> publicaciones;
    private PublicationAdapter adapter;

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

        // Ir al activity para crear nueva publicación
        btnNewPublish.setOnClickListener(v -> {
            Intent intent = new Intent(ForoActivity.this, AddPublicationActivity.class);
            startActivity(intent);
        });

        // Abrir Update/Delete al hacer clic en una publicación
        listViewForo.setOnItemClickListener((parent, view, position, id) -> {
            Publication pub = publicaciones.get(position);

            Intent intent = new Intent(ForoActivity.this, UpdateDeleteActivity.class);
            intent.putExtra("id", pub.getId());
            intent.putExtra("title", pub.getTitle());
            intent.putExtra("message", pub.getMessage());
            intent.putExtra("userId", pub.getUserId());
            intent.putExtra("time", pub.getTime());
            startActivity(intent);
        });
    }

    // Cargar publicaciones y actualizar adaptador
    private void loadPublications() {
        publicaciones = forumDatabase.getAllPublications(); // devuelve List<Publication>

        adapter = new PublicationAdapter(this, publicaciones);
        listViewForo.setAdapter(adapter);
    }

    // Cada vez que se vuelve a esta pantalla, recargar lista (por ejemplo, tras editar o eliminar)
    @Override
    protected void onResume() {
        super.onResume();
        loadPublications();
    }
}
