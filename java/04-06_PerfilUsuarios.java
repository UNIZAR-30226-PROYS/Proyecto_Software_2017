package barbarahliskov.cambialibros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class PerfilUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuarios);

        // Poner como titulo el nombre del usuario
        setTitle("Perfil Usuario");

        RatingBar valmed = (RatingBar) findViewById(R.id.val_media);
        valmed.setEnabled(false);

        final RatingBar valoracion = (RatingBar) findViewById(R.id.nueva_val);
        Button v = (Button) findViewById(R.id.valorar_button);

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

        

        

    }
}
