package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Jorge on 24/03/2017.
 */

public class Perfil extends AppCompatActivity{

    private EditText mUsuario;
    private EditText mCiudad;
    private EditText mOldPass;
    private EditText mNewPass;
    private EditText mReNewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mi Perfil");

        setContentView(R.layout.activity_perfil);

        mUsuario = (EditText) findViewById(R.id.user_email);
        mCiudad = (EditText) findViewById(R.id.user_city);
        mOldPass = (EditText) findViewById(R.id.oldPass);
        mNewPass = (EditText) findViewById(R.id.oldPass);
        mReNewPass = (EditText) findViewById(R.id.oldPass);

        RatingBar valmed = (RatingBar) findViewById(R.id.valoracion);
        valmed.setEnabled(false);

        Button guardar = (Button) findViewById(R.id.button_guardar);
        Button eliminar = (Button) findViewById(R.id.button_eliminar);
        Button cancelar = (Button) findViewById(R.id.button_cancelar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobar();
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Perfil.this, Menu.class);
                startActivity(i);
            }
        });



    }

    

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    void eliminarUsuario(){
        //Eliminar usuario BBDD

        Intent i = new Intent(Perfil.this, LoginActivity.class);
        startActivity(i);
    }
}
