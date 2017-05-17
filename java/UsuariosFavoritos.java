package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosFavoritos extends AppCompatActivity {

    private ListView mList;

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

                // ListView Clicked item index
                long itemPosition     = id;

                // ListView Clicked item value
                //int  itemValue    = mList.getitem

                // Show Alert
                Toast.makeText(getApplicationContext(), "Hiciste click en el número " + itemPosition,
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(UsuariosFavoritos.this, PerfilUsuarios.class);
                startActivity(i);
            }
        });


                /* PRUEBAS */

        String prueba = "<busqueda nick=\"Barbara96\">\n" +
                "<gente>\n" +
                "<usuario>\n" +
                "\t<nick>paco91</nick>\n" +
                "\t<nombre>paco</nombre>\n" +
                "\t<apellidos>Peperoni</apellidos>\n" +
                "\t<valoracion>4.4</valoracion>\n" +
                "\t<favorito>True</favorito>\n" +
                "</usuario>\n" +
                "<usuario>\n" +
                "\t<nick>Debora</nick>\n" +
                "\t<nombre>UnTio</nombre>\n" +
                "\t<apellidos>ConApellidos</apellidos>\n" +
                "\t<valoracion>5.0</valoracion>\n" +
                "\t<favorito>False</favorito>\n" +
                "</usuario>\n" +
                "</gente>\n" +
                "</busqueda>";


                /* FIN DE PRUEBAS */

        new UsuariosFavoritos.SearchFavsUsersTask().execute(prueba);
    }


    private void fillData(ArrayList<String> parametros){

        if (parametros != null) {
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

                    Toast.makeText(getApplicationContext(), "Favorito borrado" + v.getTag(),
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(true);

                    // Eliminar de favoritos

                    // Volver a cargar favoritos
                }
            };
            c.setIds(R.layout.rows_usuarios_favoritos, R.id.ciudadF, R.id.nombreF, R.id.usuarioF, R.id.distF, R.id.botonFav);

            mList.setAdapter(c);

        }



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
                httpClient.execute(request);

                        /* Recibir respuesta */
                String result = data[0];
                        /* Supongamos que lo tenemos */
                ArrayList<String> parametros = XML_Parser.parseaResultadoFavsUsers(result);

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
