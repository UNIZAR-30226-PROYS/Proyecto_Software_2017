package barbarahliskov.cambialibros;

import android.content.Intent;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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


                String data = null;
                try {
                   /* EN PRINCIPIO; AQUI SE CREARA UN ARRAY DE STRING PARA PASARSELO DE PARAMETRO A LA FUNCION*/

                    new InsertBookTask().execute(new String[] {data});

                } catch (Exception e) {
                    e.printStackTrace();
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

            boolean cancel = false;
            View focusView = null;

            // Check for a valid password, if the user entered one.
            if (TextUtils.isEmpty(titulo)) {
                mTitulo.setError("Introduzca algún título.");
                focusView = mTitulo;
                cancel = true;
            }
            if (TextUtils.isEmpty(autor)) {
                mTitulo.setError("Introduzca algún autor.");
                focusView = mAutor;
                cancel = true;
            }
            if (TextUtils.isEmpty(ciudad)) {
                mTitulo.setError("Introduzca alguna ciudad.");
                focusView = mCiudad;
                cancel = true;
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
                String url = "http://10.0.2.2:8080/CambiaLibros/InsertBookServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", "Laura"));
                postParameters.add(new BasicNameValuePair("tittle", "Titulo"));
                postParameters.add(new BasicNameValuePair("author", "Autorrrr"));
                postParameters.add(new BasicNameValuePair("description", "Descripcion"));
                postParameters.add(new BasicNameValuePair("location", "locloc"));
                postParameters.add(new BasicNameValuePair("password", "12345"));

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

            startActivity(new Intent(AddLibro.this, Libros.class));
        }
    }




}


