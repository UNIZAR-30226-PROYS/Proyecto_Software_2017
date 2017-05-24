package barbarahliskov.cambialibros;

import android.content.Intent;
import android.database.MatrixCursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Resultados extends AppCompatActivity {

    private ListView mList;
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

        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        user = getIntent().getExtras().getString("user");
        pass = getIntent().getExtras().getString("pass");

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView)findViewById(R.id.list);

        fillData();
    }


    private void fillData(){

        Bundle b = getIntent().getExtras();

        if(b != null) {
            Row row = null;

            ArrayList<String> parametros = b.getStringArrayList("parametros");

            for(int i = 0; i<parametros.size(); i=i+6){
                String ciudad = "";
                Geocoder gcd = new Geocoder(this, Locale.getDefault());
                String coordS = parametros.get(i+4);
                String latS = coordS.substring(0,coordS.indexOf(";"));
                String lonS = coordS.substring(coordS.indexOf(";")+1,coordS.length());
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(Double.parseDouble(latS), Double.parseDouble(lonS), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!addresses.isEmpty())
                {
                    ciudad = addresses.get(0).getLocality();
                }
                else
                {
                    // do your staff
                }

                rows.add(new Row(parametros.get(i+1),parametros.get(i+2),parametros.get(i+3), ciudad, Integer.parseInt(parametros.get(i))));
            }


            if (!rows.isEmpty()) {
                TextView empty = (TextView) findViewById(R.id.empty);
                empty.setWidth(0);
            }

            CustomArrayAdapter c = new CustomArrayAdapter(this, rows) {
                @Override
                public void onClick(View v) {

                    String userB = (String) v.getTag(R.id.key_3);
                    Log.d("usuadras-----", userB);

                    Intent i = new Intent(Resultados.this, PerfilUsuarios.class);
                    // Enviar el nombre del usuario
                    i.putExtra("userB", userB);
                    i.putExtra("user", user);
                    i.putExtra("pass", pass);
                    startActivity(i);
                }
            };
            c.setIds(R.layout.resultados_rows, R.id.autorR, R.id.tituloR, R.id.usuarioR, R.id.distR, R.id.botonListR);

            mList.setAdapter(c);

            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String idBook = String.valueOf(rows.get(position).getId());

                    Intent i = new Intent(Resultados.this, PerfilLibros.class);
                    // Enviar el nombre del usuario
                    i.putExtra("idBook", idBook);
                    i.putExtra("user", user);
                    i.putExtra("pass", pass);
                    startActivity(i);
                }
            });
        }

    }





}
