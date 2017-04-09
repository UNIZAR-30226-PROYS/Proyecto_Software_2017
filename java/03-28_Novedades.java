package barbarahliskov.cambialibros;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class Novedades extends AppCompatActivity {

    private ListView mList;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Novedades");

        

        mList = (ListView)findViewById(R.id.list);
        fillData();

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent i = new Intent(Novedades.this, Chat.class);
                startActivity(i);
            }
        });

    }


    private void fillData(){
        //Simulamos que extraemos los datos de la base de datos a un cursor
        String[] columnasBD = new String[] {"_id", "titulo", "autor", "distancia", "usuario"};
        MatrixCursor c = new MatrixCursor(columnasBD);


        c.addRow(new Object[] {"0","Nuevo mensaje", "Hola, me gustaría intercambiar el libro Corredor del Laberinto 1", "31/03/17", "Juan123"});
        c.addRow(new Object[] {"0","Nueva actividad favoritos", "Nuevo libro: Los Juegos del Hambre", "31/03/17", "usuario6"});
        c.addRow(new Object[] {"0","Nuevo mensaje", "Hola, me gustaría devolver el libro Peter Pan", "31/03/17", "Pedro.Garcia"});

        //Añadimos los datos al Adapter y le indicamos donde dibujar cada dato en la fila del Layout
        String[] from = new String[] { "titulo", "autor", "distancia", "usuario" };
        int[] to = new int[] { R.id.tituloN, R.id.autorN, R.id.fechaN, R.id.usuarioN  };
        SimpleCursorAdapter rows =
                new SimpleCursorAdapter(this, R.layout.novedades_rows, c, from, to);

        if (!rows.isEmpty()){
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }

        mList.setAdapter(rows);

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        fillData();
    }


}
