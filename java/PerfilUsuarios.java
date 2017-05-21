package barbarahliskov.cambialibros;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;

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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PerfilUsuarios extends AppCompatActivity {

    private TextView nombre;
    private TextView apellidos;
    private String usuario; //usuario que esta logueado en la aplicación
    private String usuarioBuscado; //Usuario del cual se va a mostrar la informacion
    private RatingBar valmed;
    private TextView valnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuarios);

        // Poner como titulo el nombre del usuario
        setTitle("Perfil Usuario");

        valmed = (RatingBar) findViewById(R.id.val_media);
        valnum = (TextView) findViewById(R.id.valoracion_num);
        usuario = "Jorge"; //tendría que recibirlo de la actividad anterior
        usuarioBuscado = "Dani"; // tendría que recibir el usuario de la actividad anterior
        final RatingBar valoracion = (RatingBar) findViewById(R.id.nueva_val);
        Button v = (Button) findViewById(R.id.valorar_button);

        nombre = (TextView) findViewById(R.id.user_name);
        TextView user = (TextView) findViewById(R.id.user_email);
        apellidos = (TextView) findViewById(R.id.user_apellidos);
        user.setText(usuarioBuscado);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float rating = valoracion.getRating();
                Toast.makeText(getApplicationContext(), "Valoracion: " + rating,
                        Toast.LENGTH_SHORT).show();
                new PerfilUsuarios.UpdateValUserTask().execute(new String[]{usuarioBuscado, rating.toString()});
                v.setEnabled(false);
                valoracion.setEnabled(false);
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
                    new PerfilUsuarios.DeleteFavUserTask().execute(new String[]{usuario, usuarioBuscado});
                    button.setBackgroundResource(R.drawable.ic_slide_switch_on);
                    Toast.makeText(getApplicationContext(), "Guardado como favorito",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(false);
                } else {
                    new PerfilUsuarios.AddFavUserTask().execute(new String[]{usuario, usuarioBuscado});
                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);

                    Toast.makeText(getApplicationContext(), "Favorito borrado",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(true);
                }
            }
        });
        new PerfilUsuarios.SearchUserTask().execute(new String[]{usuario, usuarioBuscado});

    }

    private void fillData(ArrayList<String> parametros) {
        nombre.setText(parametros.get(1));
        apellidos.setText(parametros.get(2));
        valmed.setRating(Float.parseFloat(parametros.get(3)));
        valnum.setText(parametros.get(3) + "/5");
        Button f = (Button) findViewById(R.id.fav_button);
        if (parametros.get(4).equals("si")) {
            f.setBackgroundResource(R.drawable.ic_slide_switch_on);
            f.setActivated(true);
        } else {
            f.setActivated(false);
            f.setBackgroundResource(R.drawable.ic_slide_switch_off);
        }
    }

    private class SearchUserTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... data) {
            try {
                String url = "http://10.0.2.2:8080/CambiaLibros/GetUserServlet?nick=" + data[0]
                        + "&nick_s=" + data[1];
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url);
                request.setURI(website);

                HttpResponse response = httpClient.execute(request);

                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);


                publishProgress(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "BIEN";
        }

        @Override
        protected void onProgressUpdate(String... result) {
            ArrayList<String> a = XML_Parser.parseaResultadoUser(result[0]);
            fillData(a);
        }

    }

    private class DeleteFavUserTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            try {
                String url = "http://10.0.2.2:8080/CambiaLibros/DeleteFavUserServlet";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", data[0]));
                postParameters.add(new BasicNameValuePair("fav_nick", data[1]));

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

    private class AddFavUserTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            try {
                String url = "http://10.0.2.2:8080/CambiaLibros/AddFavUserServlet";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", data[0]));
                postParameters.add(new BasicNameValuePair("fav_nick", data[1]));

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

    private class UpdateValUserTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            try {
                String url = "http://10.0.2.2:8080/CambiaLibros/UpdateValoracionServlet";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", data[0]));
                postParameters.add(new BasicNameValuePair("valoration", data[1]));

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
