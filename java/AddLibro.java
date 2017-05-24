package barbarahliskov.cambialibros;

import android.content.Intent;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.identity.intents.Address;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AddLibro extends AppCompatActivity {

    private EditText mTitulo;
    private EditText mAutor;
    private EditText mCiudad;
    private EditText mDesc;
    private String user;
    private String pass;
    private double latitude;
    private double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_libro);

        setTitle("Añadir Libro");

        mTitulo = (EditText) findViewById(R.id.bookTituloAdd);
        mCiudad = (EditText) findViewById(R.id.bookLocationADD);
        mAutor = (EditText) findViewById(R.id.bookAutorAdd);
        mDesc = (EditText) findViewById(R.id.bookDescription);

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");

        Button btn = (Button) findViewById(R.id.uploadBook_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
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
                        latitude = addresses.get(0).getLatitude();
                        longitude = addresses.get(0).getLongitude();
                        ( (TextView) findViewById(R.id.coordsadd)).setText("Lat " + latitude + ", Lon " + longitude);
                    }
                } catch (Exception e){

                }
            }
        });

    }

    private void attemptRegister() {
        try {
            // Reset errors.
            mTitulo.setError(null);
            mCiudad.setError(null);
            mAutor.setError(null);


            // Store values at the time of the login attempt.
            String titulo = mTitulo.getText().toString();
            String autor = mAutor.getText().toString();
            String ciudad = mCiudad.getText().toString();
            String descripcion = mDesc.getText().toString();

            boolean cancel = false;
            View focusView = null;

            // Check for a valid password, if the user entered one.
            if (TextUtils.isEmpty(titulo)) {
                mTitulo.setError("Introduzca algún título.");
                focusView = mTitulo;
                cancel = true;
            }
            if (TextUtils.isEmpty(autor)) {
                mAutor.setError("Introduzca algún autor.");
                focusView = mAutor;
                cancel = true;
            }
            if (TextUtils.isEmpty(ciudad)) {
                mCiudad.setError("Introduzca alguna ciudad.");
                focusView = mCiudad;
                cancel = true;
            }
            if (TextUtils.isEmpty(descripcion)) {
                mDesc.setError("Introduzca alguna descripcion.");
                focusView = mDesc;
                cancel = true;
            }

            if(cancel){
                focusView.requestFocus();
            }
            else{
                ciudad = Double.toString(latitude) + ";" + Double.toString(longitude);
                new InsertBookTask().execute(new String[] {titulo, autor, ciudad, descripcion});
            }
        } catch (Exception e){

        }



    }

    private class InsertBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            // EN principio, el parametro llevara los datos
            String text = "";
            BufferedReader reader = null;

            // Send data
            try
            {
                String url = getString(R.string.dir) + "InsertBookServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();


                postParameters.add(new BasicNameValuePair("nick", user));
                postParameters.add(new BasicNameValuePair("tittle", data[0]));
                postParameters.add(new BasicNameValuePair("author", data[1]));
                postParameters.add(new BasicNameValuePair("description", ""));
                postParameters.add(new BasicNameValuePair("location", data[2]));
                postParameters.add(new BasicNameValuePair("password", pass));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);

                request.setEntity(formEntity);
                httpClient.execute(request);

                // Defined URL  where to send data


            } catch(Exception e) {
                // Do something about exceptions
                e.printStackTrace();
            }

            return "BIEN";

        }

        protected void onPostExecute(String page) {
            Intent i = new Intent(AddLibro.this, Libros.class);
            i.putExtra("user", user);
            i.putExtra("userB", user);
            i.putExtra("pass", pass);

            startActivity(i);
        }
    }




}


