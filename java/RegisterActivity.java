package barbarahliskov.cambialibros;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity{

    private EditText mUsuario;
    private EditText mCiudad;
    private EditText mPassword1;
    private EditText mPassword2;
    private EditText mNombre;
    private EditText mApellido;

    private String userS = "", nomS = "", apeS = "", pasS = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Registrarse");

        mUsuario = (EditText) findViewById(R.id.emailRg);
        mPassword1 = (EditText) findViewById(R.id.passwordRg);
        mPassword2 = (EditText) findViewById(R.id.passwordRepeatRg);
        mNombre = (EditText) findViewById(R.id.nameReg);
        mApellido = (EditText) findViewById(R.id.apeReg);

        Button btn = (Button) findViewById(R.id.resgister_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    attemptRegister();
            }
        });

    }

    private void attemptRegister() {

        // Reset errors.
        mUsuario.setError(null);
        mPassword1.setError(null);
        mPassword2.setError(null);

        // Store values at the time of the login attempt.
        String login = mUsuario.getText().toString();
        String password = mPassword1.getText().toString();
        String password2 = mPassword2.getText().toString();
        String apellideision = mApellido.getText().toString();
        String name = mNombre.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !(password.length()>4)) {
            mPassword1.setError(getString(R.string.error_invalid_password));
            focusView = mPassword1;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password2) || !(password2.length()>4) || !(password2.equals(password))) {
            mPassword2.setError(getString(R.string.error_incorrect_password));
            focusView = mPassword2;
            cancel = true;
        }

        // Check for a valid login
        if (TextUtils.isEmpty(login)) {
            mUsuario.setError(getString(R.string.error_field_required));
            focusView = mUsuario;
            cancel = true;
        }

        // Check for a valid login
        if (TextUtils.isEmpty(name)) {
            mUsuario.setError(getString(R.string.error_field_required));
            focusView = mUsuario;
            cancel = true;
        }

        // Check for a valid login
        if (TextUtils.isEmpty(apellideision)) {
            mUsuario.setError(getString(R.string.error_field_required));
            focusView = mUsuario;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            userS = mUsuario.getText().toString();
            pasS = mPassword1.getText().toString();
            nomS = mNombre.getText().toString();
            apeS = mApellido.getText().toString();
            new InsertUserTask().execute();


        }
    }

    private class InsertUserTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... data) {
            // EN principio, el parametro llevara los datos
            String text = "";
            BufferedReader reader = null;

            // Send data
            try
            {
                String url = "http://10.0.2.2:8080/CambiaLibros/InsertUserServlet";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("nick", userS));
                postParameters.add(new BasicNameValuePair("password", pasS));
                postParameters.add(new BasicNameValuePair("nombre", nomS));
                postParameters.add(new BasicNameValuePair("apellidos", apeS));

                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                        postParameters);

                request.setEntity(formEntity);
                httpClient.execute(request);

                // Defined URL  where to send data


            } catch(Exception e) {
                // Do something about exceptions
                e.printStackTrace();
            }

            return "BIEN";

        }

        protected void onPostExecute(String page) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
    }
}