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

    String url_final = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView)findViewById(R.id.list);




        String miNombre="Danny";
        String bookSearch = "http://10.0.2.2:8080/CambiaLibros/SearchIntercambioServlet?nick=" + miNombre;
        url_final = bookSearch;
        new SearchHistorialTask().execute(new String[] {});

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }


    private void fillData(){


        List<Row> rows = new ArrayList<Row>(30);
        Row row = null;


        for(int i = 0; i<parametros.size(); i=i+4){
            String aux = parametros.get(i+1);
            String aux2 = parametros.get(i);
            //
            // int a = Integer.parseInt(parametros.get(i));
            rows.add(new Row(parametros.get(i+1),parametros.get(i+2),parametros.get(i+3), (long) 0.0 , Integer.parseInt(parametros.get(i))) );
        }


        if (!rows.isEmpty()) {
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }

        CustomArrayAdapter c = new CustomArrayAdapter(this, rows);
        c.setIds(R.layout.history_rows, R.id.autorH, R.id.tituloH, R.id.usuarioH, R.id.distH, R.id.botonListH);

        mList.setAdapter(c);
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
                Log.d(" Devuelto-> ", result);

                parametros = XML_Parser.parseaResultadoHistorial(result);

                fillData();


            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return "BIEN";

        }
        /*
        protected void onPostExecute(String page) {
            //textView.setText(page);
            Toast toast = Toast.makeText(getApplicationContext(), page, Toast.LENGTH_SHORT);
            toast.show();
        }
        */
    }




}



