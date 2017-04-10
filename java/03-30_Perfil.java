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

    void comprobar(){
        // Reset errors.
        mUsuario.setError(null);
        mCiudad.setError(null);
        mOldPass.setError(null);
        mNewPass.setError(null);
        mReNewPass.setError(null);

        // Store values at the time of the login attempt.
        String email = mUsuario.getText().toString();
        String oldPass = mOldPass.getText().toString();
        String newPass = mNewPass.getText().toString();
        String rePass = mReNewPass.getText().toString();
        String ciudad = mCiudad.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Comprobar contraseña en el caso en el que el usuario la haya cambiado
        if( !TextUtils.isEmpty(newPass) || !TextUtils.isEmpty(rePass)) {
            // Contraseña del usuario incorrecta
            if (TextUtils.isEmpty(oldPass)) {
                /***********************************************************
                 * Si el campo de la contraseña esta vacío o no coincide con la contreseña del usuario
                 */
                mOldPass.setError(getString(R.string.error_incorrect_password));
                focusView = mOldPass;
                cancel = true;
            }
            // Nueva contraseña incorrecta
            else if(TextUtils.isEmpty(newPass) || newPass.length()<=4){
                mNewPass.setError(getString(R.string.error_invalid_password));
                focusView = mNewPass;
                cancel = true;
            }
            // Contraseña repetida no coincide
            else if(TextUtils.isEmpty(rePass) || !newPass.equals(rePass)){
                mReNewPass.setError(getString(R.string.error_incorrect_password));
                focusView = mReNewPass;
                cancel = true;
            }
        }


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUsuario.setError(getString(R.string.error_field_required));
            focusView = mUsuario;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mUsuario.setError(getString(R.string.error_invalid_email));
            focusView = mUsuario;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(ciudad)) {
            mCiudad.setError(getString(R.string.error_field_required));
            focusView = mCiudad;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            startActivity(new Intent(Perfil.this, Menu.class));
        }
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
