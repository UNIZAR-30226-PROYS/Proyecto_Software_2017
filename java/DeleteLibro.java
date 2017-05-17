package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    List<Row> rows = new ArrayList<Row>(30);
    Row row = null;
    String url_final = "http://10.0.2.2:8080/CambiaLibros/SearchBookServlet?nick=LegenDanny&nick_b=LegenDanny";

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        /* PRUEBAS */


        final String prueba = "<busqueda nick=\"Barbara96\">\n" +
                "<libreria>\n" +
                "<libro>\n" +
                "\t<id_libro>4134134</id_libro>\n" +
                "\t<titulo> 50 sombras de gray </titulo>\n" +
                "\t<autor> el autor gay </autor>\n" +
                "\t<usuario> el user </usuario>\n" +
                "\t<localizacion>lo del gps</localizacion>\n" +
                "\t<favorito>True</favorito>\n" +
                "</libro>\n" +
                "<libro>\n" +
                "\t<id_libro>111</id_libro>\n" +
                "\t<titulo> 51 sombras de gray </titulo>\n" +
                "\t<autor> el autor gay </autor>\n" +
                "\t<usuario> el user </usuario>\n" +
                "\t<localizacion> la localicacion o lo del gps </localizacion>\n" +
                "\t<fav>False</fav>\n" +
                "</libro>\n" +
                "<libro>\n" +
                "\t<id_libro>541</id_libro>\n" +
                "\t<titulo> 52 sombras de gray </titulo>\n" +
                "\t<autor> el autor gay </autor>\n" +
                "\t<usuario> el user </usuario>\n" +
                "\t<localizacion> la localicacion o lo del gps </localizacion>\n" +
                "\t<favorito>False</favorito>\n" +
                "</libro>\n" +
                "</libreria>\n" +
                "</busqueda>";


                /* FIN DE PRUEBAS */


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        setTitle("Mis libros");
        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView) findViewById(R.id.list);


        new DeleteLibro.SearchBookTask().execute(new String[]{"", prueba});



        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                long itemPosition = id;

                try {
                    //new DeleteBookTask().execute(new String[] {"" , prueba});
                    //new SearchBookTask().execute(new String[]{"", prueba});
                } catch(Exception e){

                }


                // Show Alert
                Toast.makeText(getApplicationContext(), "Se borrara el elemento " + rows.get( (int) itemPosition).getTitulo(),
                        Toast.LENGTH_LONG).show();
                // Intent i = new Intent(LibrosFavoritos.this, Busqueda.class);
                // startActivity(i);
            }
        });




    }


    private void fillData(ArrayList<String> parametros) {




        for(int i = 0; i<parametros.size(); i=i+6){
            rows.add(new Row(parametros.get(i+1),parametros.get(i+2),parametros.get(i+3), /*Long.parseLong(parametros.get(i+4))*/ (long) 4));
        }


        if (!rows.isEmpty()) {
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }

        CustomArrayAdapter c = new CustomArrayAdapter(this, rows) {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;

                if (button.isActivated()) {
                    button.setBackgroundResource(R.drawable.ic_slide_switch_on);
                    Toast.makeText(getApplicationContext(), "Guardado como favorito",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(false);
                } else {
                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);

                    Toast.makeText(getApplicationContext(), "Favorito borrado",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(true);
                }
            }
        };

        c.setIds(R.layout.activity_libros, R.id.ciudadMiLi, R.id.nombreMiLi, R.id.usuarioMiLi, R.id.distMiLi, R.id.FavMiLi);

        mList.setAdapter(c);


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

                postParameters.add(new BasicNameValuePair("nick", "LegenDanny"));
                postParameters.add(new BasicNameValuePair("password", "Titulo"));
                postParameters.add(new BasicNameValuePair("id_book", "1234"));

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
        /*
        protected void onPostExecute(String page) {
            //textView.setText(page);
            Toast toast = Toast.makeText(getApplicationContext(), page, Toast.LENGTH_SHORT);
            toast.show();
        }
        */
    }


    private class SearchBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url_final);
                request.setURI(website);
                httpClient.execute(request);

                HttpResponse response = httpClient.execute(request);
                response.getParams().toString();


                // Defined URL  where to send data

                response.getEntity().consumeContent();

                        /* Recibir respuesta */
                String result = data[1];
                        /* Supongamos que lo tenemos */
                ArrayList<String> parametros = XML_Parser.parseaResultadoBusqueda(result);

                fillData(parametros);


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
