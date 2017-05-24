package barbarahliskov.cambialibros;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Historial extends AppCompatActivity {

    private ListView mList;
    ArrayList<String> parametros = new ArrayList<String>();
    List<Row> rows = new ArrayList<Row>();

    String url_final = "";
    private String user;
    private String pass;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView)findViewById(R.id.list);

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");

        String bookSearch = getString(R.string.dir) + "SearchIntercambioServlet?nick=" + user;

        url_final = bookSearch;
        new SearchHistorialTask().execute(new String[] {});


    }


    private void fillData(){




        for(int i = 0; i<parametros.size(); i=i+4){
            if(!parametros.get(i+1).equals(user)) {
                rows.add(new Row(parametros.get(i+1),parametros.get(i+2),parametros.get(i+3), (long) 0.0 , Integer.parseInt(parametros.get(i))) );
            }
        }


        if (!rows.isEmpty()) {
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }

        CustomArrayAdapter c = new CustomArrayAdapter(this, rows) {
            @Override
            public void onClick(View v) {

                String userB = (String) v.getTag(R.id.key_1);

                Intent i = new Intent(Historial.this, PerfilUsuarios.class);
                // Enviar el nombre del usuario
                i.putExtra("userB", userB);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        };
        c.setIds(R.layout.history_rows, R.id.autorH, R.id.tituloH, R.id.usuarioH, R.id.distH, R.id.botonListH);

        mList.setAdapter(c);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idBook = String.valueOf(rows.get(position).getId());

                Intent i = new Intent(Historial.this, PerfilLibros.class);
                // Enviar el nombre del usuario
                i.putExtra("idBook", idBook);
                i.putExtra("user", user);
                i.putExtra("pass", pass);
                startActivity(i);
            }
        });
    }



    private class SearchHistorialTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url_final);
                request.setURI(website);
                HttpResponse response = httpClient.execute(request);

                /* Recibir respuesta */

                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);

                parametros = XML_Parser.parseaResultadoHistorial(result);



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




}



