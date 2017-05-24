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
        private int id;

        static public final int ID_1 = 1;

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
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // holder pattern
        Holder holder = null;
        if (convertView == null)
        {
            holder = new Holder();

            convertView = layoutInflater.inflate(layout, null);
            holder.setTextViewAutor((TextView) convertView.findViewById(idAutor));
            holder.setTextViewTitulo((TextView) convertView.findViewById(idTitulo));
            holder.setTextViewUsuario((TextView) convertView.findViewById(idUsuario));
            holder.setTextViewDist((TextView) convertView.findViewById(idDist));
            holder.setButton((Button) convertView.findViewById(idBoton));
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }

        Row row = getItem(position);
        holder.getTextViewTitulo().setText(row.getTitulo());
        holder.getTextViewAutor().setText(row.getAutor());
        holder.getTextViewUsuario().setText(row.getUsuario());
        holder.getTextViewDist().setText((row.getCity()));
       // holder.getButton().setTag(position);

        //Para borrar un usuario favorito se necesita el nick del usuario
        holder.getButton().setTag(R.id.key_1, row.getTitulo());
        //Para borrar un libro favorito se necesita el id del libro
        holder.getButton().setTag(R.id.key_2, row.getId());
        //Para devolver o intercambiar se necesita el nick del usuario y el titulo del libro
        // para abrir el chat y generar un mensaje automatico
        holder.getButton().setTag(R.id.key_3, row.getUsuario());
        holder.getButton().setTag(R.id.key_4, row.getAutor());
        //holder.getCheckBox().setChecked(row.isChecked());
        holder.getButton().setOnClickListener(this);

        return convertView;
    }
    @Override
    public void onClick(View v) {

        Button button = (Button) v;
        //int position = (Integer) v.getTag();
        /*getItem(position).setChecked(checkBox.isChecked());*/

        String msg = (String) v.getTag(R.id.key_1);
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    static class Holder
    {
        TextView textViewTitulo;
        TextView textViewAutor;
        TextView textViewUsuario;
        TextView textViewDist;
        Button button;

        public TextView getTextViewTitulo()
        {
            return textViewTitulo;
        }

        public void setTextViewTitulo(TextView textViewTitle)
        {
            this.textViewTitulo = textViewTitle;
        }

        public TextView getTextViewAutor()
        {
            return textViewAutor;
        }

        public void setTextViewAutor(TextView textViewAutor)
        {
            this.textViewAutor = textViewAutor;
        }

        public TextView getTextViewUsuario()
        {
            return textViewUsuario;
        }

        public void setTextViewUsuario(TextView textViewUsuario)
        {
            this.textViewUsuario = textViewUsuario;
        }

        public TextView getTextViewDist()
        {
            return textViewDist;
        }

        public void setTextViewDist(TextView textViewDist)
        {
            this.textViewDist = textViewDist;
        }
        public Button getButton()
        {
            return button;
        }
        public void setButton(Button b)
        {
            this.button = b;
        }

    }
}