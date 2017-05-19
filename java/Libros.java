package barbarahliskov.cambialibros;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class Libros extends AppCompatActivity {

    private ListView mList;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        setTitle("Libros");
        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView) findViewById(R.id.list);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        new Libros.SearchBookTask().execute();

    }


    private void fillData(ArrayList<String> parametros) {
        List<Row> rows = new ArrayList<Row>(30);
        Row row = null;

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


    private class SearchBookTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {

            try {


                String url_final = "http://10.0.2.2:8080/CambiaLibros/SearchBookServlet?nick=Laura&nick_b=Laura";
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url_final);
                request.setURI(website);
                //httpClient.execute(request);

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


}