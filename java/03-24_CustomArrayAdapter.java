package barbarahliskov.cambialibros;

import java.util.List;

import barbarahliskov.cambialibros.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CustomArrayAdapter extends ArrayAdapter<Row> implements
        View.OnClickListener {

        private LayoutInflater layoutInflater;
        private int layout;
        private int idAutor;
        private int idTitulo;
        private int idUsuario;
        private int idDist;
        private int idBoton;

        public CustomArrayAdapter(Context context, List < Row > objects) {
            super(context, 0, objects);
            layoutInflater = LayoutInflater.from(context);
        }

        public void setIds (int layout, int a, int t, int u, int d, int b){
            this.layout = layout;
            this.idAutor = a;
            this.idTitulo = t;
            this.idUsuario = u;
            this.idDist = d;
            this.idBoton = b;
        }
    
    

    
}