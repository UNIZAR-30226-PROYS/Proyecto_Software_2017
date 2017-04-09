package barbarahliskov.cambialibros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


        fillData();
    }


    private void fillData(){


        List<Row> rows = new ArrayList<Row>(30);
        Row row = null;

        rows.add(new Row("El Código da Vinci", "Dan Brown", "",(long) 0));
        rows.add(new Row("Ángeles y Demonios", "Dan Brown", "", (long) 1));
        rows.add(new Row("El símbolo perdido", "Dan Brown", "", (long) 2));
        rows.add(new Row("El último deseo", "Andrzej Sapkowski", "", (long) 4));
        rows.add(new Row("Memorias de Idhún I", "Laura Gallego","", (long) 4));
        rows.add(new Row("La sombra del viento", "Carlos Ruiz Zafón", "",(long) 0));
        rows.add(new Row("El testamento maya", "Steve Alten", "", (long) 1));
        rows.add(new Row("Inferno", "Dan Brown", "", (long) 2));
        rows.add(new Row("La brújula dorada", "Philip Pullman", "", (long) 4));
        rows.add(new Row("Crpúsculo", "Stephanie Meyers", "", (long) 4));


        if (!rows.isEmpty()){
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setWidth(0);
        }

        CustomArrayAdapter c = new CustomArrayAdapter(this, rows){
            @Override
            public void onClick(View v) {
                Button button = (Button) v;

                if (button.isActivated()){
                    button.setBackgroundResource(R.drawable.ic_slide_switch_on);
                    Toast.makeText(getApplicationContext(), "Guardado como favorito",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(false);
                }
                else{
                    button.setBackgroundResource(R.drawable.ic_slide_switch_off);

                    Toast.makeText(getApplicationContext(), "Favorito borrado",
                            Toast.LENGTH_SHORT).show();
                    button.setActivated(true);
                }
            }
        };

        c.setIds(R.layout.activity_libros_favoritos, R.id.ciudadLibroF, R.id.nombreLibroF, R.id.usuarioLibroF, R.id.distLibroF, R.id.botonFav);

        mList.setAdapter(c);



    }

}
