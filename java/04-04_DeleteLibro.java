package barbarahliskov.cambialibros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteLibro extends AppCompatActivity {

    private ListView mList;
    List<Row> rows = new ArrayList<Row>(30);
    Row row = null;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        setTitle("Libros favoritos");
        Button closeButton = (Button) findViewById(R.id.botonCerrar);

        closeButton.setVisibility(View.INVISIBLE);
        mList = (ListView) findViewById(R.id.list);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                long itemPosition = id;




                // Show Alert
                Toast.makeText(getApplicationContext(), "Se borrara el elemento " + rows.get( (int) itemPosition).getTitulo(),
                        Toast.LENGTH_LONG).show();
                // Intent i = new Intent(LibrosFavoritos.this, Busqueda.class);
                // startActivity(i);
            }
        });




        fillData();




    }


    private void fillData() {






    }



}
