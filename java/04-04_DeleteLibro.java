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




        rows.add(new Row("50 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("51 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("52 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("53 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("54 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("55 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("56 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("57 sombras de Grey", "Anonimo", "", (long) 0));
        rows.add(new Row("58 sombras de Grey", "Anonimo", "", (long) 0));




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



}
