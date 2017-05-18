package barbarahliskov.cambialibros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilUsuarios extends AppCompatActivity {

    private TextView user;
    private TextView nombre;
    private TextView apellidos;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuarios);

        // Poner como titulo el nombre del usuario
        setTitle("Perfil Usuario");

        RatingBar valmed = (RatingBar) findViewById(R.id.val_media);
        valmed.setEnabled(false);
        usuario = "Jorge"; // tendría que recibir el usuario de la actividad anterior
        final RatingBar valoracion = (RatingBar) findViewById(R.id.nueva_val);
        Button v = (Button) findViewById(R.id.valorar_button);

        nombre = (TextView) findViewById(R.id.user_name);
        user = (TextView) findViewById(R.id.user_email);
        apellidos = (TextView) findViewById(R.id.user_apellidos);
        user.setText(usuario);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float rating = valoracion.getRating();
                Toast.makeText(getApplicationContext(), "Valoracion: " + rating,
                        Toast.LENGTH_SHORT).show();
                // Calcular valoración media y guardar en la BBDD
                // Actualizar pagina para que se muestre la nueva valoración media??
            }
        });

        Button c = (Button) findViewById(R.id.contactar_button);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilUsuarios.this, Chat.class);
                startActivity(i);
            }
        });

        Button l = (Button) findViewById(R.id.libros_button);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilUsuarios.this, Libros.class);
                startActivity(i);
            }
        });

        Button f = (Button) findViewById(R.id.fav_button);

        // Activar o desactivar el boton favorito dependiendo de si esta en su lista de favs o no

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (button.isActivated()) {
                    button.setBackgroundResource(R.drawable.ic_slide_switch_on);
                    Toast.makeText(getApplicationContext(), "Guardado como favorito",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(false);
                } else {
                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);

                    Toast.makeText(getApplicationContext(), "Favorito borrado",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(true);
                }
            }
        });

    }
}
