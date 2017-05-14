package barbarahliskov.cambialibros;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Busqueda extends AppCompatActivity {



    double xml_latitude;
    double xml_longitude;


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
                /* Recupera los EditText del formulario */
                EditText latE = (EditText) findViewById(R.id.latitud);
                EditText lonE = (EditText) findViewById(R.id.longitud);
                final EditText ciudad = (EditText) findViewById(R.id.ciudad);
                EditText autor = (EditText) findViewById(R.id.text_writer);
                EditText usuario = (EditText) findViewById(R.id.user);
                EditText titulo = (EditText) findViewById(R.id.text_title);
                RadioButton citB = (RadioButton) findViewById(R.id.radio_ciudad);
                RadioButton cooB = (RadioButton) findViewById(R.id.radio_coord);

                boolean fallo = false;

                /* Prepara casillas de error */
                TextView errorTitulo = (TextView) findViewById(R.id.errorTitulo);
                TextView errorAutor = (TextView) findViewById(R.id.errorAutor);
                TextView errorUsuario = (TextView) findViewById(R.id.errorusuario);
                TextView errorLoc = (TextView) findViewById(R.id.errorLocalizacion);

                float lat = -200.0f;
                /* Recupera los valores que contengan */
                if(!latE.getText().toString().equals("")) {
                    lat = Float.parseFloat(latE.getText().toString());
                }
                float lon = -200.0f;
                if(!lonE.getText().toString().equals("")){
                    lon = Float.parseFloat(lonE.getText().toString());
                }

                String titS = titulo.getText().toString();
                String autS = autor.getText().toString();
                String usrS = usuario.getText().toString();
                final String citS = ciudad.getText().toString();
                String error = "";

                if ((cooB.isChecked()) && (lat < -90.0 || lat > 90.0)){
                    errorLoc.setTextColor(Color.RED);
                    error = "Mala latitud.";
                    errorLoc.setText(error);
                    fallo = true;
                } else if ((cooB.isChecked()) && (lon < -180.0 || lon > 180.0)){
                    error = "Mala longitud.";
                    errorLoc.setTextColor(Color.RED);
                    errorLoc.setText(error);
                    fallo = true;
                } else if ((citB.isChecked()) && (citS.equals(""))){
                    error = "Introduzca una ciudad.";
                    errorLoc.setText(error);
                    errorLoc.setTextColor(Color.RED);
                    fallo = true;
                } /* else if ((!citB.isChecked()) && (!cooB.isChecked())){
                    error = "Seleccione una opción.";
                    errorLoc.setText(error);
                    errorLoc.setTextColor(Color.RED);
                    fallo = true;
                } */

                /*
                if (titS.equals("")){
                    errorTitulo.setTextColor(Color.RED);
                    error = "Introduzca título";
                    errorTitulo.setText(error);
                    fallo = true;
                }

                if (autS.equals("")){
                    errorAutor.setTextColor(Color.RED);
                    error = "Introduzca autor";
                    errorAutor.setText(error);
                    fallo = true;
                }

                if (usrS.equals("")){
                    errorUsuario.setTextColor(Color.RED);
                    error = "Introduzca usuario";
                    errorUsuario.setText(error);
                    fallo = true;
                }
                */

                if(!fallo){
                    String miNombre="LegenDanny";
                    String bookSearch = "http://localhost:8080/CambiaLibros/SearchBookServlet?nick=" + miNombre;
                    if (!titS.equals("")){
                        bookSearch += "&tittle="+titS;
                    }

                    if (!autS.equals("")){
                        bookSearch += "&author="+autS;
                    }

                    if (!usrS.equals("")){
                        bookSearch += "&nick_b="+titS;
                    }

                    if (citB.isChecked()){

                        try {
                            Geocoder geocoder = new Geocoder(Busqueda.this);
                            List<Address> addresses;

                            addresses = geocoder.getFromLocationName(citS, 1);

                            if (addresses.size() > 0) {
                                xml_latitude = addresses.get(0).getLatitude();
                                xml_longitude = addresses.get(0).getLongitude();
                            }
                        } catch (Exception e){

                        }

                        bookSearch += "&location="+xml_latitude+";"+xml_longitude;
                    } else if (cooB.isChecked()) {
                        bookSearch += "&location="+lat+";"+lon;
                    }
                    try {
                        URL url = new URL(bookSearch);
                        URLConnection connection = url.openConnection();
                        connection.connect();
                        /* Recibir respuesta */

                        /* Supongamos que lo tenemos */
                        String answer_XML;

                        //startActivity(new Intent(Busqueda.this, Resultados.class));
                    } catch (MalformedURLException mue) {
                        mue.printStackTrace();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        });

        final EditText latE = (EditText) findViewById(R.id.latitud);
        final EditText lonE = (EditText) findViewById(R.id.longitud);
        final EditText ciudadE = (EditText) findViewById(R.id.ciudad);
        final EditText autorE = (EditText) findViewById(R.id.text_writer);
        final EditText usuarioE = (EditText) findViewById(R.id.user);
        final EditText tituloE = (EditText) findViewById(R.id.text_title);
        final RadioButton citB = (RadioButton) findViewById(R.id.radio_ciudad);
        final RadioButton cooB = (RadioButton) findViewById(R.id.radio_coord);

        latE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        lonE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        ciudadE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        autorE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        usuarioE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        tituloE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        cooB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        citB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                limpiaErrores();
            }
        });

        findViewById(R.id.activity_busqueda).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });


    }


    public void limpiaErrores(){
        final TextView errorTitulo = (TextView) findViewById(R.id.errorTitulo);
        final TextView errorAutor = (TextView) findViewById(R.id.errorAutor);
        final TextView errorUsuario = (TextView) findViewById(R.id.errorusuario);
        final TextView errorLoc = (TextView) findViewById(R.id.errorLocalizacion);
        errorTitulo.setText("");
        errorAutor.setText("");
        errorLoc.setText("");
        errorUsuario.setText("");
    }


}


