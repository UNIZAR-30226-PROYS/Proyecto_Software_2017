package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
        mNewPass = (EditText) findViewById(R.id.oldPass);
        mReNewPass = (EditText) findViewById(R.id.oldPass);
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
        String prueba = "<?xml version=\"1.0\"?>\n" +
                "<busqueda nick=\"Barbara96\">\n" +
                "<usuario>\n" +
                "\t<nick>Debora</nick>\n" +
                "\t<nombre>UnTio</nombre>\n" +
                "\t<apellidos>ConApellidos</apellidos>\n" +
                "\t<valoracion>4.01</valoracion>\n" +
                "\t<favorito>13431</favorito>\n" +
                "usuario\n" +
                "</busqueda>";
        new Perfil.SearchUserTask().execute(new String[]{user});


    }

    void comprobar() {
        // Reset errors.
        mUsuario.setError(null);
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
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            String data = null; // datos del usuario a cambiar

            /* EN PRINCIPIO; AQUI SE CREARA UN ARRAY DE STRING PARA PASARSELO DE PARAMETRO A LA FUNCION*/
            new UpdateUserTask().execute(new String[]{data});
            Intent i = new Intent(Perfil.this, Menu.class);
            i.putExtra("Usuario", user);
            startActivity(i);
        }
    }

    void eliminarUsuario() {

        String oldPass = mOldPass.getText().toString();
        if (TextUtils.isEmpty(oldPass)) { //Si la contraseña es incorrecta
            mOldPass.setError(getString(R.string.error_incorrect_password));
            View focusView = mOldPass;
            focusView.requestFocus();
        } else {
            new DeleteUserTask().execute(new String[]{user});

            Intent i = new Intent(Perfil.this, LoginActivity.class);
            startActivity(i);
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

                postParameters.add(new BasicNameValuePair("nick", "LegenDanny"));
                postParameters.add(new BasicNameValuePair("nombre", "Nombre"));
                postParameters.add(new BasicNameValuePair("apellidos", "Apellidos"));
                postParameters.add(new BasicNameValuePair("new_password", "12345"));
                postParameters.add(new BasicNameValuePair("password", "12334"));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);
                request.setEntity(formEntity);
                httpClient.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "BIEN";
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
                postParameters.add(new BasicNameValuePair("password", "12334"));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);
                request.setEntity(formEntity);
                httpClient.execute(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "BIEN";
        }
    }

}