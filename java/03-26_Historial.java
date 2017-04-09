package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Historial extends AppCompatActivity {

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
        // Get all of the notes from the database and create the item list
        /*Cursor c = mDbHelper.fetchAllNotes();
        startManagingCursor(c);
        String[] from = new String[] {  NotesDbAdapter.KEY_TITLE, NotesDbAdapter.KEY_TITLE, NotesDbAdapter.KEY_TITLE, NotesDbAdapter.KEY_TITLE };
        int[] to = new int[] { R.id.titulo, R.id.usuario,  R.id.autor, R.id.dist  };
        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
                new SimpleCursorAdapter(this, R.layout.novedades_rows, c, from, to);
        if (!notes.isEmpty()){
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }
        mList.setAdapter(notes);*/

        //Simulamos que extraemos los datos de la base de datos a un cursor
        /*String[] columnasBD = new String[] {"_id", "titulo", "autor", "distancia", "usuario"};
        MatrixCursor c = new MatrixCursor(columnasBD);


        c.addRow(new Object[] {"0","En busca del tiempo perdido", "Marcel Proust", "1 km", "Juan123"});
        c.addRow(new Object[] {"1","Harry Potter y el legado maldito", "J. K. Rowling", "1 km", "Vic93"});
        c.addRow(new Object[] {"2","Poeta en Nueva York", "Federico García Lorca", "2 km", "Aniita94"});
        c.addRow(new Object[] {"3","El Corredor Del Laberinto 1", "Dashner James", "4 km", "Teresa"});
        c.addRow(new Object[] {"4","Los Juegos Del Hambre", "Suzanne Collins", "4 km", "usuario6"});
        c.addRow(new Object[] {"5","Peter Pan", "James Matthew Barrie", "4 km", "Pedro.Garcia"});

        //Añadimos los datos al Adapter y le indicamos donde dibujar cada dato en la fila del Layout
        String[] from = new String[] { "titulo", "autor", "distancia", "usuario" };
        int[] to = new int[] { R.id.tituloH,  R.id.autorH, R.id.distH, R.id.usuarioH  };
        SimpleCursorAdapter rows =
                new SimpleCursorAdapter(this, R.layout.history_rows, c, from, to);

        if (!rows.isEmpty()){
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }

        mList.setAdapter(rows);*/

        



    }

    
}
