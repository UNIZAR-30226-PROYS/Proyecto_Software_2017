package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 24/03/2017.
 */

public class Perfil extends AppCompatActivity {

    private EditText mUsuario;
    private EditText mNombre;
    private EditText mApellidos;
    private EditText mOldPass;
    private EditText mNewPass;
    private EditText mReNewPass;
    private String user;
    private RatingBar valmed;
    private TextView valnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mi Perfil");

        setContentView(R.layout.activity_perfil);
        //user = getIntent().getExtras().getString("Usuario");
        user = "Laura";
        mUsuario = (EditText) findViewById(R.id.user_email);
        mNombre = (EditText) findViewById(R.id.user_name);
        mApellidos = (EditText) findViewById(R.id.user_apellidos);
        mOldPass = (EditText) findViewById(R.id.oldPass);
        mNewPass = (EditText) findViewById(R.id.newPass);
        mReNewPass = (EditText) findViewById(R.id.reNewPass);
        valnum = (TextView) findViewById(R.id.valoracion_num);
        mUsuario.setText(user);

        valmed = (RatingBar) findViewById(R.id.valoracion);

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
                i.putExtra("Usuario", user);
                startActivity(i);
            }
        });

        new Perfil.SearchUserTask().execute(new String[]{user});


    }

    void comprobar() {
        // Reset errors.
        mUsuario.setEnabled(false);
        mNombre.setError(null);
        mApellidos.setError(null);
        mOldPass.setError(null);
        mNewPass.setError(null);
        mReNewPass.setError(null);
        // Store values at the time of the login attempt.
        String email = mUsuario.getText().toString();
        String oldPass = mOldPass.getText().toString();
        String newPass = mNewPass.getText().toString();
        String rePass = mReNewPass.getText().toString();
        String nombre = mNombre.getText().toString();
        String apellidos = mApellidos.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Comprobar contraseña en el caso en el que el usuario la haya cambiado
        if (!TextUtils.isEmpty(newPass) || !TextUtils.isEmpty(rePass)) {

            // Contraseña del usuario incorrecta
            if (TextUtils.isEmpty(oldPass)) {
                /***********************************************************
                 * Si el campo de la contraseña esta vacío o no coincide con la contreseña del usuario
                 */
                /** Comparar con la contraseña pasada por bundle ***/
                mOldPass.setError(getString(R.string.error_incorrect_password));
                focusView = mOldPass;
                cancel = true;
            }
            // Nueva contraseña incorrecta
            else if (TextUtils.isEmpty(newPass) || newPass.length() <= 4) {
                mNewPass.setError(getString(R.string.error_invalid_password));
                focusView = mNewPass;
                cancel = true;
            }
            // Contraseña repetida no coincide
            else if (TextUtils.isEmpty(rePass) || !newPass.equals(rePass)) {
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
        }


        if (TextUtils.isEmpty(nombre)) {
            mNombre.setError(getString(R.string.error_field_required));
            focusView = mNombre;
            cancel = true;
        }
        if (TextUtils.isEmpty(apellidos)) {
            mApellidos.setError(getString(R.string.error_field_required));
            focusView = mApellidos;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            new UpdateUserTask().execute(new String[]{user, nombre, apellidos, oldPass, newPass});
        }
    }

    void eliminarUsuario() {

        String oldPass = mOldPass.getText().toString();
        if (TextUtils.isEmpty(oldPass)) { //Si la contraseña es incorrecta
            mOldPass.setError(getString(R.string.error_incorrect_password));
            View focusView = mOldPass;
            focusView.requestFocus();
        }
        /* Comprobar que sea la misma que la pasada por bundle
        else if(){

        }*/
        else {
            new DeleteUserTask().execute(new String[]{user});


        }

    }

    private void fillData(ArrayList<String> parametros) {
        mNombre.setText(parametros.get(1));
        mApellidos.setText(parametros.get(2));
        valmed.setRating(Float.parseFloat(parametros.get(3)));
        valnum.setText(parametros.get(3) + "/5");
    }


    private class SearchUserTask extends AsyncTask<String, String, String> {
        protected String doInBackground(String... data) {
            try {
                String user = data[0];
                String url = "http://10.0.2.2:8080/CambiaLibros/GetUserServlet?nick=" + user
                        + "&nick_s=" + user;
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                URI website = new URI(url);
                request.setURI(website);

                HttpResponse response = httpClient.execute(request);

                HttpEntity entity = response.getEntity();

                // Read the contents of an entity and return it as a String.
                String result = EntityUtils.toString(entity);
                /* para rellenar los campos no se puede hacer desde aquí, llama al metodo onProgressUpdate */
                publishProgress(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "BIEN";
        }

        @Override
        protected void onProgressUpdate(String... result) {
            ArrayList<String> a = XML_Parser.parseaResultadoUser(result[0]);
            fillData(a);
        }

    }

    private class UpdateUserTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            try {
                String url = "http://10.0.2.2:8080/CambiaLibros/ModifyUserServlet";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", data[0]));
                postParameters.add(new BasicNameValuePair("nombre", data[1]));
                postParameters.add(new BasicNameValuePair("apellidos", data[2]));
                /********************Hay que pasar la contraseña por bundle**********************/
                postParameters.add(new BasicNameValuePair("password", "12345"));

                if(!TextUtils.isEmpty(data[3])){
                    postParameters.add(new BasicNameValuePair("new_password", data[4]));
                }


                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);
                request.setEntity(formEntity);
                httpClient.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "BIEN";
        }

        protected void onPostExecute(String page) {
            Intent i = new Intent(Perfil.this, Menu.class);
            i.putExtra("Usuario", user);
            startActivity(i);
        }
    }


    private class DeleteUserTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            try {
                String user = data[0];
                String url = "http://10.0.2.2:8080/CambiaLibros/DeleteUserServlet";
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", user));

                /********************Hay que pasar la contraseña por bundle**********************/
                postParameters.add(new BasicNameValuePair("password", "12345"));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);
                request.setEntity(formEntity);
                httpClient.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "BIEN";
        }

        protected void onPostExecute(String page) {
            Intent i = new Intent(Perfil.this, LoginActivity.class);
            startActivity(i);
        }
    }

}