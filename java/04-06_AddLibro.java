package barbarahliskov.cambialibros;

import android.content.Intent;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.identity.intents.Address;

import java.util.List;

public class AddLibro extends AppCompatActivity {

    private EditText mTitulo;
    private EditText mAutor;
    private EditText mCiudad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);

        setTitle("Añadir Libro");

        mTitulo = (EditText) findViewById(R.id.bookTituloAdd);
        mCiudad = (EditText) findViewById(R.id.bookLocationADD);
        mAutor = (EditText) findViewById(R.id.bookAutorAdd);


        Button btn = (Button) findViewById(R.id.uploadBook_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
                startActivity(new Intent(AddLibro.this, Libros.class));
            }
        });

        mCiudad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    Geocoder geocoder = new Geocoder(AddLibro.this);
                    List<android.location.Address> addresses;

                        addresses = geocoder.getFromLocationName(mCiudad.getText().toString(), 1);

                    if (addresses.size() > 0) {
                        double latitude = addresses.get(0).getLatitude();
                        double longitude = addresses.get(0).getLongitude();
                        ( (TextView) findViewById(R.id.coordsadd)).setText("Lat " + latitude + ", Lon " + longitude);
                    }
                } catch (Exception e){

                }
            }
        });

    }

    private void attemptRegister() {
        



    }


}
