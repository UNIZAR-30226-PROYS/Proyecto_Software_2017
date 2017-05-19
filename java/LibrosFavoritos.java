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

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView)findViewById(R.id.list);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                long itemPosition     = id;

                // ListView Clicked item value
                //int  itemValue    = mList.getitem

                // Show Alert
                Toast.makeText(getApplicationContext(), "Hiciste click en el número " + itemPosition,
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(LibrosFavoritos.this, Busqueda.class);
                startActivity(i);
            }
        });

        /* PRUEBAS */
        String prueba = "";
        new LibrosFavoritos.SearchFavsBooksTask().execute(prueba);
    }


    private void fillData(ArrayList<String> parametros){


        List<Row> rows = new ArrayList<Row>(30);
        Row row = null;

        if (!parametros.isEmpty()) {

            for (int i = 0; i < parametros.size(); i = i + 6) {
                rows.add(new Row(parametros.get(i + 1), parametros.get(i + 2), parametros.get(i + 3),
                        /*Long.parseLong(parametros.get(i+4))*/ (long) 4, Integer.parseInt(parametros.get(i))));
            }

        /*rows.add(new Row("El Código da Vinci", "Dan Brown", "",(long) 0));
        rows.add(new Row("Ángeles y Demonios", "Dan Brown", "", (long) 1));
        rows.add(new Row("El símbolo perdido", "Dan Brown", "", (long) 2));
        rows.add(new Row("El último deseo", "Andrzej Sapkowski", "", (long) 4));
        rows.add(new Row("Memorias de Idhún I", "Laura Gallego","", (long) 4));
        rows.add(new Row("La sombra del viento", "Carlos Ruiz Zafón", "",(long) 0));
        rows.add(new Row("El testamento maya", "Steve Alten", "", (long) 1));
        rows.add(new Row("Inferno", "Dan Brown", "", (long) 2));
        rows.add(new Row("La brújula dorada", "Philip Pullman", "", (long) 4));
        rows.add(new Row("Crpúsculo", "Stephanie Meyers", "", (long) 4));*/


            if (!rows.isEmpty()) {
                TextView empty = (TextView) findViewById(R.id.empty);
                empty.setWidth(0);
            }

            CustomArrayAdapter c = new CustomArrayAdapter(this, rows) {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;

                    /*if (button.isActivated()) {
                        button.setBackgroundResource(R.drawable.ic_slide_switch_on);
                        Toast.makeText(getApplicationContext(), "Guardado como favorito",
                                Toast.LENGTH_SHORT).show();
                        button.setActivated(false);
                    } else {
                        button.setBackgroundResource(R.drawable.ic_slide_switch_off);

                        Toast.makeText(getApplicationContext(), "Favorito borrado",
                                Toast.LENGTH_SHORT).show();
                        button.setActivated(true);
                    }*/

                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);
                    //this.getView((int) v.getTag(), v);



                    int idBook = (int) v.getTag(R.id.key_2);
                    String libro = (String) v.getTag(R.id.key_1);
                    Toast.makeText(this.getContext(), libro + " eliminado de favoritos", Toast.LENGTH_SHORT).show();

                    button.setActivated(true);

                    // Eliminar de favoritos


                    new LibrosFavoritos.DeleteFavBookTask().execute(String.valueOf(idBook));



                    // Volver a cargar favoritos
                    //new UsuariosFavoritos.SearchFavsUsersTask().execute(prueba);

                    /*Intent intent = new Intent(LibrosFavoritos.this, LibrosFavoritos.class);
                    Bundle b = new Bundle();
                    b.putSerializable("n", 1);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();*/

                   startActivity(new Intent(LibrosFavoritos.this, LibrosFavoritos.class));
                    finish();
                }
            };

            c.setIds(R.layout.activity_libros_favoritos, R.id.ciudadLibroF, R.id.nombreLibroF, R.id.usuarioLibroF, R.id.distLibroF, R.id.botonFav);

            mList.setAdapter(c);
        }

        else{
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }



    }

    private class SearchFavsBooksTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {

                String miNombre = "Laura";
                String url = "http://10.0.2.2:8080/CambiaLibros/SearchFavBookServlet?nick=" + miNombre;

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url);
                request.setURI(website);

                HttpResponse response = httpClient.execute(request);

                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);


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

    private class DeleteFavBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            // EN principio, el parametro llevara los datos
            String text = "";
            BufferedReader reader = null;

            try {

                String miNombre = "Laura";
                String idBook = data[0];
                String url = "http://10.0.2.2:8080/CambiaLibros/DeleteFavBookServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", miNombre));
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
        /*
        protected void onPostExecute(String page) {
            //textView.setText(page);
            Toast toast = Toast.makeText(getApplicationContext(), page, Toast.LENGTH_SHORT);
            toast.show();
        }
        */
    }

}
