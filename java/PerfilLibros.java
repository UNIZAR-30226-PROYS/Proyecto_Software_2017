package barbarahliskov.cambialibros;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PerfilLibros extends AppCompatActivity {

    private TextView titulo;
    private TextView autor;
    private TextView usuario;
    private TextView desc;
    private TextView loc;
    private Button c;
    private Button f;
    private String user; //usuario que esta logueado en la aplicación
    private String pass;
    private String user1;
    private String idBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_libros);

        // Poner como titulo el nombre del usuario
        setTitle("Descripcion Libro");

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");
        idBook = getIntent().getExtras().getString("idBook");


        titulo = (TextView) findViewById(R.id.book_title);
        autor = (TextView) findViewById(R.id.book_author);
        usuario = (TextView) findViewById(R.id.book_user);
        desc = (TextView) findViewById(R.id.book_desc);
        loc = (TextView) findViewById(R.id.book_loc);


        c = (Button) findViewById(R.id.contactar_button);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilLibros.this, Chat.class);
                i.putExtra("user", user);
                i.putExtra("user1", user1);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });

        f = (Button) findViewById(R.id.fav_button);

        // Activar o desactivar el boton favorito dependiendo de si esta en su lista de favs o no

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (button.isActivated()) {
                    new PerfilLibros.AddFavBookTask().execute(new String[]{user, idBook});
                    button.setBackgroundResource(R.drawable.ic_slide_switch_on);
                    Toast.makeText(getApplicationContext(), "Guardado como favorito",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(false);
                } else {
                    new PerfilLibros.DeleteFavBookTask().execute(new String[]{user, idBook});
                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);

                    Toast.makeText(getApplicationContext(), "Favorito borrado",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(true);
                }
            }
        });
        new PerfilLibros.SearchBookTask().execute(new String[]{user, idBook});

    }

    private void fillData(ArrayList<String> parametros) {
        titulo.setText(parametros.get(1));
        autor.setText(parametros.get(2));
        user1 = parametros.get(3);
        usuario.setText(parametros.get(3));
        if (user.equals(parametros.get(3))){
            c.setVisibility(View.INVISIBLE);
            f.setVisibility(View.INVISIBLE);
        }
        desc.setText(parametros.get(5));


        String ciudad = "";
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        String coordS = parametros.get(4);
        String latS = coordS.substring(0,coordS.indexOf(";"));
        String lonS = coordS.substring(coordS.indexOf(";")+1,coordS.length());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(Double.parseDouble(latS), Double.parseDouble(lonS), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!addresses.isEmpty())
        {
            ciudad = addresses.get(0).getLocality();
        }
        else
        {
            // do your staff
        }


        loc.setText(ciudad);
        Button f = (Button) findViewById(R.id.fav_button);
        if (!parametros.get(6).equals("0")) {
            f.setBackgroundResource(R.drawable.ic_slide_switch_on);
            f.setActivated(false);
        } else {
            f.setActivated(true);
            f.setBackgroundResource(R.drawable.ic_slide_switch_off);
        }
    }

    private class SearchBookTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... data) {
            try {
                String url = getString(R.string.dir) + "GetBookServlet?nick=" + data[0]
                        + "&id_book=" + data[1];
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url);
                request.setURI(website);

                HttpResponse response = httpClient.execute(request);

                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);
                Log.d("result-------", result);


                publishProgress(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "BIEN";
        }

        @Override
        protected void onProgressUpdate(String... result) {
            ArrayList<String> a = XML_Parser.parseaResultadoBook(result[0]);
            fillData(a);
        }

    }

    private class DeleteFavBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            try {
                String url = getString(R.string.dir) + "DeleteFavBookServlet";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", data[0]));
                postParameters.add(new BasicNameValuePair("id_book", data[1]));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);
                request.setEntity(formEntity);
                httpClient.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "BIEN";
        }
    }

    private class AddFavBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            try {
                String url = getString(R.string.dir) + "AddFavBookServlet";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", data[0]));
                postParameters.add(new BasicNameValuePair("id_book", data[1]));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);
                request.setEntity(formEntity);
                httpClient.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "BIEN";
        }
    }

}
