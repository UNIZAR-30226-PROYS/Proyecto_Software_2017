package barbarahliskov.cambialibros;

import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    }


    private void fillData(){
        List<Row> rows = new ArrayList<Row>(30);
        Row row = null;


        rows.add(new Row("El Corredor Del Laberinto 1", "Dashner James", "Teresa", (long) 4));
        rows.add(new Row("El Corredor Del Laberinto 2", "Dashner James", "Teresa", (long) 4));
        rows.add(new Row("El Corredor Del Laberinto 3", "Dashner James", "Teresa", (long) 4));

        if (!rows.isEmpty()){
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }

        CustomArrayAdapter c = new CustomArrayAdapter(this, rows);
        c.setIds(R.layout.resultados_rows, R.id.autorR,R.id.tituloR, R.id.usuarioR, R.id.distR, R.id.botonListR);

        mList.setAdapter(c);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        //outState.putSerializable(CategoryDbAdapter.KEY_ROWID, mRowId);
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }
    @Override
    protected void onResume() {
        super.onResume();
        fillData();
    }

    private void saveState() {
        Intent i = new Intent(this,Menu.class);
        startActivity(i);
    }

    public void onCambiar(){

    }



}
