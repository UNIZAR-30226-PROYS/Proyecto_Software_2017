package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class UsuariosFavoritos extends AppCompatActivity {

    private ListView mList;
    ArrayList<String> parametros = new ArrayList<>();

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Usuarios favoritos");
        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView)findViewById(R.id.list);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(UsuariosFavoritos.this, PerfilUsuarios.class);
                // Enviar el nombre del usuario
                i.putExtra("PerfilUser", "user1");
                startActivity(i);
            }
        });

        new UsuariosFavoritos.SearchFavsUsersTask().execute();

        //new UsuariosFavoritos.SearchFavsUsersTask().execute(prueba);
    }


    private void fillData(){

            List<Row> rows = new ArrayList<Row>();
            Row row = null;

            for (int i = 0; i < parametros.size(); i = i + 5) {
                rows.add(new Row(parametros.get(i), "", "", (long) 0));
            }

        /*
        rows.add(new Row("Juan123", "Zaragoza", "",(long) 0));
        rows.add(new Row("Vic93", "Zaragoza", "", (long) 1));
        rows.add(new Row("Aniita94", "Huesca", "", (long) 2));
        rows.add(new Row("Teresa", "Barcelona", "", (long) 4));
        rows.add(new Row("usuario6", "Teruel","", (long) 4));
        rows.add(new Row("Pedro.Garcia", "Zaragoza", "", (long) 4));
        */

            if (!rows.isEmpty()) {
                TextView empty = (TextView) findViewById(R.id.empty);
                empty.setWidth(0);
            }

            CustomArrayAdapter c = new CustomArrayAdapter(this, rows) {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;

                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);


                    String user = (String) v.getTag(R.id.key_1);
                    Toast.makeText(this.getContext(), user + " eliminado de favoritos", Toast.LENGTH_SHORT).show();

                    button.setActivated(true);

                    // Eliminar de favoritos
                    new UsuariosFavoritos.DeleteFavUserTask().execute(user);
                }
            };
            c.setIds(R.layout.rows_usuarios_favoritos, R.id.ciudadF, R.id.nombreF, R.id.usuarioF, R.id.distF, R.id.botonFav);

            mList.setAdapter(c);




    }

    private class SearchFavsUsersTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {

                String miNombre = "Laura";
                String url = "http://10.0.2.2:8080/CambiaLibros/SearchFavUserServlet?nick=" + miNombre;

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url);
                request.setURI(website);

                HttpResponse response = httpClient.execute(request);

                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);

                //String result = data[0];

                parametros = XML_Parser.parseaResultadoFavsUsers(result);


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
            fillData();
        }

    }

    private class DeleteFavUserTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            // EN principio, el parametro llevara los datos
            String text = "";
            BufferedReader reader = null;

            try {

                String miNombre = "Laura";
                String user = data[0];
                String url = "http://10.0.2.2:8080/CambiaLibros/DeleteFavUserServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", miNombre));
                postParameters.add(new BasicNameValuePair("fav_nick", user));



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
            new UsuariosFavoritos.SearchFavsUsersTask().execute();
        }

    }

}
