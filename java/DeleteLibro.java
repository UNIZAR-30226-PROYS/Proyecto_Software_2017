package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class DeleteLibro extends AppCompatActivity {

    private ListView mList;
    ArrayList<String> parametros = new ArrayList<>();
    List<Row> rows = new ArrayList<Row>();
    Row row = null;
    String url_final = "http://10.0.2.2:8080/CambiaLibros/SearchBookServlet?nick=Laura&nick_b=Laura";

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        /* PRUEBAS */


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        setTitle("Mis libros");
        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView) findViewById(R.id.list);


        new SearchBookTask().execute(new String[]{});



        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                long itemPosition = id;



                try {
                    new DeleteBookTask().execute(String.valueOf(rows.get( (int) itemPosition).getId()));

                } catch(Exception e){

                }


                // Show Alert
                Toast.makeText(getApplicationContext(), "Se borrara el elemento " + rows.get( (int) itemPosition).getTitulo(),
                        Toast.LENGTH_LONG).show();
            }
        });




    }


    private void fillData() {

        if(!parametros.isEmpty()) {


            for (int i = 0; i < parametros.size(); i = i + 6) {
                rows.add(new Row(parametros.get(i + 1), parametros.get(i + 2), parametros.get(i + 3),
                    /*Long.parseLong(parametros.get(i+4))*/ (long) 4, Integer.parseInt(parametros.get(i))));
            }


            if (!rows.isEmpty()) {
                TextView empty = (TextView) findViewById(R.id.empty);
                empty.setWidth(0);
            }

            CustomArrayAdapter c = new CustomArrayAdapter(this, rows);

            c.setIds(R.layout.activity_libros, R.id.ciudadMiLi, R.id.nombreMiLi, R.id.usuarioMiLi, R.id.distMiLi, R.id.FavMiLi);

            mList.setAdapter(c);
        }
        else{
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }


    }


    private class DeleteBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            // EN principio, el parametro llevara los datos
            String text = "";
            BufferedReader reader = null;

            // Send data
            try
            {
                String url = "http://10.0.2.2:8080/CambiaLibros/DeleteBookServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", "Laura"));
                postParameters.add(new BasicNameValuePair("password", "12345"));
                postParameters.add(new BasicNameValuePair("id_book", data[0]));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);

                request.setEntity(formEntity);
                HttpResponse response = httpClient.execute(request);

                // Defined URL  where to send data

                String repuestado = response.getEntity().toString();

                response.getEntity().consumeContent();



            } catch(Exception e) {
                // Do something about exceptions
                e.printStackTrace();
            }

            return "BIEN";

        }

        protected void onPostExecute(String page) {
            new SearchBookTask().execute(new String[]{});
        }

    }


    private class SearchBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url_final);
                request.setURI(website);
                //httpClient.execute(request);

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


}
