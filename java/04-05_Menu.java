package barbarahliskov.cambialibros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView btnSearch= (ImageView) findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Menu.this, Busqueda.class));
            }
        });

        ImageView btnLibrary= (ImageView) findViewById(R.id.btn_library);
        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Libros.class));
            }
        });

        ImageView btnProfile= (ImageView) findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Perfil.class));
             }
        });

        ImageView btnHistory= (ImageView) findViewById(R.id.btn_history);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Historial.class));
            }
        });

        ImageView btnFav= (ImageView) findViewById(R.id.btn_favoritos);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, UsuariosFavoritos.class));
            }
        });

        ImageView btnFavLib = (ImageView) findViewById(R.id.btn_lib_favs);
        btnFavLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, LibrosFavoritos.class));
            }
        });

        ImageView addLib = (ImageView) findViewById(R.id.addBook);
        addLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, AddLibro.class));
            }
        });

        ImageView delLib = (ImageView) findViewById(R.id.deleteBook);
        delLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, DeleteLibro.class));
            }
        });
    }


}