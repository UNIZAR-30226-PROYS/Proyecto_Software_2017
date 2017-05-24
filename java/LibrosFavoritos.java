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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LibrosFavoritos extends AppCompatActivity {

    private ListView mList;
    ArrayList<String> parametros = new ArrayList<>();
    private String user;
    private String pass;
    List<Row> rows = new ArrayList<Row>();

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Libros favoritos");
        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView)findViewById(R.id.list);

        //new LibrosFavoritos.SearchFavsBooksTask().execute();
    }


    private void fillData(){

            for (int i = 0; i < parametros.size(); i = i + 6) {
                rows.add(new Row(parametros.get(i + 1), parametros.get(i + 2), parametros.get(i + 3),
                        /*Long.parseLong(parametros.get(i+4))*/ (long) 4, Integer.parseInt(parametros.get(i))));
            }


            if (!rows.isEmpty()) {
                TextView empty = (TextView) findViewById(R.id.empty);
                empty.setWidth(0);
            }

            CustomArrayAdapter c = new CustomArrayAdapter(this, rows) {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;

                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);
                    //this.getView((int) v.getTag(), v);



                    int idBook = (int) v.getTag(R.id.key_2);
                    String libro = (String) v.getTag(R.id.key_1);
                    Toast.makeText(this.getContext(), libro + " eliminado de favoritos", Toast.LENGTH_SHORT).show();

                    button.setActivated(true);

                    // Eliminar de favoritos


                    new LibrosFavoritos.DeleteFavBookTask().execute(String.valueOf(idBook));

                }
            };

            c.setIds(R.layout.activity_libros_favoritos, R.id.ciudadLibroF, R.id.nombreLibroF, R.id.usuarioLibroF, R.id.distLibroF, R.id.botonFav);

            mList.setAdapter(c);

            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String idBook = String.valueOf(rows.get(position).getId());

                    Intent i = new Intent(LibrosFavoritos.this, PerfilLibros.class);
                    // Enviar el nombre del usuario
                    i.putExtra("idBook", idBook);
                    i.putExtra("user", user);
                    i.putExtra("pass", pass);
                    startActivity(i);
                }
            });



    }

    private class SearchFavsBooksTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {

                String url = getString(R.string.dir) + "SearchFavBookServlet?nick=" + user;

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url);
                request.setURI(website);

                HttpResponse response = httpClient.execute(request);

                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);


                parametros = XML_Parser.parseaResultadoBusqueda(result);



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

    private class DeleteFavBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            // EN principio, el parametro llevara los datos
            String text = "";
            BufferedReader reader = null;

            try {

                String idBook = data[0];
                String url = getString(R.string.dir) + "DeleteFavBookServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", user));
                postParameters.add(new BasicNameValuePair("id_book", idBook));



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
            new LibrosFavoritos.SearchFavsBooksTask().execute();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        rows =  new ArrayList<Row>();
        new LibrosFavoritos.SearchFavsBooksTask().execute();
    }
}
