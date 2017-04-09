package barbarahliskov.cambialibros;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Busqueda extends AppCompatActivity {





    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        setTitle("Búsqueda");



        /* Invisibiliza los campos aún no decididos */
        EditText ciudad = (EditText) findViewById(R.id.ciudad);
        ciudad.setVisibility(View.INVISIBLE);
        EditText lat = (EditText) findViewById(R.id.latitud);
        lat.setVisibility(View.INVISIBLE);
        EditText lon = (EditText) findViewById(R.id.longitud);
        lon.setVisibility(View.INVISIBLE);




        /* Prepara el botón de Ciudad para visibilizar los campos
           a rellenar, en caso de que se seleccione.
         */
        final RadioButton ciudadBtn = (RadioButton) findViewById(R.id.radio_ciudad);
        final RadioButton coordBtn = (RadioButton) findViewById(R.id.radio_coord);
        ciudadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ciudad = (EditText) findViewById(R.id.ciudad);
                ciudad.setVisibility(View.VISIBLE);
                EditText lat = (EditText) findViewById(R.id.latitud);
                lat.setVisibility(View.INVISIBLE);
                EditText lon = (EditText) findViewById(R.id.longitud);
                lon.setVisibility(View.INVISIBLE);
                coordBtn.setChecked(false);
            }
        });

        /* Prepara el botón de Coordenadas para visibilizar los campos
           a rellenar, en caso de que se seleccione.
         */

        coordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ciudad = (EditText) findViewById(R.id.ciudad);
                ciudad.setVisibility(View.INVISIBLE);
                EditText lat = (EditText) findViewById(R.id.latitud);
                lat.setVisibility(View.VISIBLE);
                EditText lon = (EditText) findViewById(R.id.longitud);
                lon.setVisibility(View.VISIBLE);
                ciudadBtn.setChecked(false);
            }
        });

        Button buttonAtras = (Button) findViewById(R.id.button_patras);
        buttonAtras.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //startActivity(new Intent(Busqueda.this, Menu.class));
                  finish();
              }
        });

        Button buttonBusqueda = (Button) findViewById(R.id.button_busqueda);
        buttonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

                boolean fallo = false;

                

                

                if(!fallo){
                    startActivity(new Intent(Busqueda.this, Resultados.class));
                }
            }
        });

        
 


    }


}