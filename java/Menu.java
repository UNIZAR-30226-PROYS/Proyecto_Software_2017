package barbarahliskov.cambialibros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private String user;
    private String pass;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");

        ImageView btnSearch= (ImageView) findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Busqueda.class);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        ImageView btnLibrary= (ImageView) findViewById(R.id.btn_library);
        btnLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Libros.class);
                i.putExtra("user", user);
                i.putExtra("userB", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        ImageView btnProfile= (ImageView) findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Perfil.class);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        ImageView btnHistory= (ImageView) findViewById(R.id.btn_history);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Historial.class);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        ImageView btnFav= (ImageView) findViewById(R.id.btn_favoritos);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, UsuariosFavoritos.class);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        ImageView btnFavLib = (ImageView) findViewById(R.id.btn_lib_favs);
        btnFavLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, LibrosFavoritos.class);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        ImageView addLib = (ImageView) findViewById(R.id.addBook);
        addLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, AddLibro.class);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        ImageView delLib = (ImageView) findViewById(R.id.deleteBook);
        delLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, DeleteLibro.class);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });
    }


}
