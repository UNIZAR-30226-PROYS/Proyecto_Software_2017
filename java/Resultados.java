package barbarahliskov.cambialibros;

import android.content.Intent;
import android.database.MatrixCursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
                //closeButton.setText(Long.toString(itemPosition));
            }
        });

        fillData();
    }


    private void fillData(){

        Bundle b = getIntent().getExtras();

        if(b != null) {
            List<Row> rows = new ArrayList<Row>(30);
            Row row = null;

            ArrayList<String> parametros = b.getStringArrayList("parametros");

            for(int i = 0; i<parametros.size(); i=i+6){
                String ciudad = "";
                Geocoder gcd = new Geocoder(this, Locale.getDefault());
                String coordS = parametros.get(i+4);
                String latS = coordS.substring(0,coordS.indexOf(";"));
                String lonS = coordS.substring(coordS.indexOf(";"),coordS.length());
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(Double.parseDouble(latS), Double.parseDouble(lonS), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses.size() > 0)
                {
                    ciudad = addresses.get(0).getLocality();
                }
                else
                {
                    // do your staff
                }
                rows.add(new Row(parametros.get(i+1),parametros.get(i+2),parametros.get(i+3), ciudad));
            }

            /*  rows.add(new Row("El Corredor Del Laberinto 1", "Dashner James", "Teresa", (long) 4));
                rows.add(new Row("El Corredor Del Laberinto 2", "Dashner James", "Teresa", (long) 4));
                rows.add(new Row("El Corredor Del Laberinto 3", "Dashner James", "Teresa", (long) 4)); */

            if (!rows.isEmpty()) {
                TextView empty = (TextView) findViewById(R.id.empty);
                empty.setWidth(0);
            }

            CustomArrayAdapter c = new CustomArrayAdapter(this, rows) {
                @Override
                public void onClick(View v) {

                    String user = (String) v.getTag(R.id.key_3);
                    Toast.makeText(this.getContext(), user + " eliminado de favoritos", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Resultados.this, PerfilUsuarios.class);
                    // Enviar el nombre del usuario
                    i.putExtra("PerfilUser", user);
                    startActivity(i);
                }
            };
            c.setIds(R.layout.resultados_rows, R.id.autorR, R.id.tituloR, R.id.usuarioR, R.id.distR, R.id.botonListR);

            mList.setAdapter(c);
        }

    }





}
