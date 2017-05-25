package barbarahliskov.cambialibros;


import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chat extends AppCompatActivity {

    int huecoLibre = 0;
    double lat = 0.0;
    double lon = 0.0;
    int numMensajes = 0;
    private String user;
    private String pass;
    private String user1;

    int escritor[] = new int[4];

    ArrayList<String> parametros = new ArrayList<String>();

    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        user = getIntent().getExtras().getString("user");
        user1 = getIntent().getExtras().getString("user1");
        pass = getIntent().getExtras().getString("pass");


        Button inter = (Button) findViewById(R.id.button_intercambiar);
        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Chat.this, Libros.class);
                i.putExtra("user", user);
                i.putExtra("userB", user);
                i.putExtra("user1", user1);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });
        setTitle("Chat con " + user1);
        if(numMensajes == 0){
            LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
            LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
            LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
            LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
        }

        ImageView butSend = (ImageView) findViewById(R.id.boton_send);
        butSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mMessage = (EditText) findViewById(R.id.Typemsgarea);
                message = mMessage.getText().toString();
                new InsertMessageTask().execute();
            }
        });

        ImageView butSendLoc = (ImageView) findViewById(R.id.boton_send_loc);
        butSendLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Chat.this, Chat_GPS.class);
                startActivityForResult(intent,2);
            }
        });

        new SearchMessagesReceivedTask().execute();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                /*
                LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
                layout4.setVisibility(View.VISIBLE);
                TextView texto = (TextView) findViewById(R.id.textView5);
                texto.setText("Estas son mis coordenadas: " + data.getDataString());
                */
            }
        }
    }



    private class SearchMessagesReceivedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {

                String url_final = "http://10.0.2.2:8080/ChatCambiaLibros/SearchMessageServlet?";
                url_final += "emisor=" + user1 + "&receptor=" + user;

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url_final);
                request.setURI(website);
                HttpResponse response = httpClient.execute(request);



                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);
                Log.d("url", url_final);

                parametros = XML_Parser.parseaResultadoBusquedaMensajes(result);



            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return "BIEN";

        }

        protected void onPostExecute(String page) {
            /* Aqui tenemos los parametros */
            int max = 4;
            int numMensj = (parametros.size()/4);
            if(numMensj<max) {
                max = numMensj;
            }

                for(int i=0; i<max; i++){
                    String id = parametros.get(4*i);
                    String emisor = parametros.get(4*i+1);
                    String receptor = parametros.get(4*i+2);
                    String mensaje = parametros.get(4*i+3);

                    if(i == 0){
                        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
                        layout1.setVisibility(View.VISIBLE);
                        TextView texto = (TextView) findViewById(R.id.textView1);
                        texto.setText(mensaje);
                        ImageView foto = (ImageView) findViewById(R.id.imageView12);
                        foto.setBackgroundResource(R.drawable.user_pratikshya);
                        escritor[numMensajes] = 0;        // 0 es el otro, 1 tu
                        numMensajes++;
                    } else if(i == 1){
                        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
                        layout2.setVisibility(View.VISIBLE);
                        TextView texto = (TextView) findViewById(R.id.textView2);
                        texto.setText(mensaje);
                        ImageView foto = (ImageView) findViewById(R.id.imageView22);
                        foto.setBackgroundResource(R.drawable.user_pratikshya);
                        escritor[numMensajes] = 0;        // 0 es el otro, 1 tu
                        numMensajes++;
                    } else if(i == 2) {
                        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
                        layout3.setVisibility(View.VISIBLE);
                        TextView texto = (TextView) findViewById(R.id.textView3);
                        texto.setText(mensaje);
                        ImageView foto = (ImageView) findViewById(R.id.imageView32);
                        foto.setBackgroundResource(R.drawable.user_pratikshya);
                        escritor[numMensajes] = 0;        // 0 es el otro, 1 tu
                        numMensajes++;
                    } else {
                        LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
                        layout4.setVisibility(View.VISIBLE);
                        TextView texto = (TextView) findViewById(R.id.textView4);
                        texto.setText(mensaje);
                        ImageView foto = (ImageView) findViewById(R.id.imageView42);
                        foto.setBackgroundResource(R.drawable.user_pratikshya);
                        escritor[numMensajes] = 0;        // 0 es el otro, 1 tu
                        numMensajes++;
                    }
                }

        }

    }

    private class InsertMessageTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            // EN principio, el parametro llevara los datos
            String text = "";
            BufferedReader reader = null;

            // Send data
            try
            {
                String url = "http://10.0.2.2:8080/ChatCambiaLibros/" + "InsertMessageServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();


                postParameters.add(new BasicNameValuePair("emisor", user));
                postParameters.add(new BasicNameValuePair("receptor", user1));
                postParameters.add(new BasicNameValuePair("mensaje", message));


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

            if(numMensajes == 0){
                LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
                layout1.setVisibility(View.VISIBLE);
                TextView texto = (TextView) findViewById(R.id.textView1);
                texto.setText(message);
                ImageView foto = (ImageView) findViewById(R.id.imageView12);
                foto.setBackgroundResource(R.drawable.user_pacific);
                escritor[numMensajes] = 1;        // 0 es el otro, 1 tu
                numMensajes++;
            } else if(numMensajes == 1){
                LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
                layout2.setVisibility(View.VISIBLE);
                TextView texto = (TextView) findViewById(R.id.textView2);
                texto.setText(message);
                ImageView foto = (ImageView) findViewById(R.id.imageView22);
                foto.setBackgroundResource(R.drawable.user_pacific);
                escritor[numMensajes] = 1;        // 0 es el otro, 1 tu
                numMensajes++;
            } else if(numMensajes == 2){
                LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
                layout3.setVisibility(View.VISIBLE);
                TextView texto = (TextView) findViewById(R.id.textView3);
                texto.setText(message);
                ImageView foto = (ImageView) findViewById(R.id.imageView32);
                foto.setBackgroundResource(R.drawable.user_pacific);
                escritor[numMensajes] = 1;        // 0 es el otro, 1 tu
                numMensajes++;
            } else if(numMensajes == 3) {
                LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
                layout4.setVisibility(View.VISIBLE);
                TextView texto = (TextView) findViewById(R.id.textView4);
                texto.setText(message);
                ImageView foto = (ImageView) findViewById(R.id.imageView42);
                foto.setBackgroundResource(R.drawable.user_pacific);
                escritor[numMensajes] = 1;        // 0 es el otro, 1 tu
                numMensajes++;
            } else {
                TextView texto1 = (TextView) findViewById(R.id.textView1);
                TextView texto2 = (TextView) findViewById(R.id.textView2);
                TextView texto3 = (TextView) findViewById(R.id.textView3);
                TextView texto4 = (TextView) findViewById(R.id.textView4);
                texto1.setText(texto2.getText());
                texto2.setText(texto3.getText());
                texto3.setText(texto4.getText());
                texto4.setText(message);

                if(escritor[1] == 1){
                    escritor[0] = escritor[1];
                    ImageView foto1 = (ImageView) findViewById(R.id.imageView12);
                    foto1.setBackgroundResource(R.drawable.user_pacific);
                } else {
                    escritor[0] = escritor[1];
                    ImageView foto1 = (ImageView) findViewById(R.id.imageView12);
                    foto1.setBackgroundResource(R.drawable.user_pratikshya);
                }

                if(escritor[2] == 1){
                    escritor[1] = escritor[2];
                    ImageView foto2 = (ImageView) findViewById(R.id.imageView22);
                    foto2.setBackgroundResource(R.drawable.user_pacific);
                } else {
                    escritor[1] = escritor[2];
                    ImageView foto2 = (ImageView) findViewById(R.id.imageView22);
                    foto2.setBackgroundResource(R.drawable.user_pratikshya);
                }

                if(escritor[3] == 1){
                    escritor[2] = escritor[3];
                    ImageView foto3 = (ImageView) findViewById(R.id.imageView32);
                    foto3.setBackgroundResource(R.drawable.user_pacific);
                } else {
                    escritor[2] = escritor[3];
                    ImageView foto3 = (ImageView) findViewById(R.id.imageView32);
                    foto3.setBackgroundResource(R.drawable.user_pratikshya);
                }

                numMensajes++;
            }

        }
    }




}
