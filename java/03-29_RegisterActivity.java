package barbarahliskov.cambialibros;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity{

    private EditText mUsuario;
    private EditText mCiudad;
    private EditText mPassword1;
    private EditText mPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Registrarse");

        mUsuario = (EditText) findViewById(R.id.emailRg);
        mCiudad = (EditText) findViewById(R.id.ciudadRg);
        mPassword1 = (EditText) findViewById(R.id.passwordRg);
        mPassword2 = (EditText) findViewById(R.id.passwordRepeatRg);

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
        mCiudad.setError(null);
        mPassword1.setError(null);
        mPassword2.setError(null);

        // Store values at the time of the login attempt.
        String email = mUsuario.getText().toString();
        String password = mPassword1.getText().toString();
        String password2 = mPassword2.getText().toString();
        String ciudad = mCiudad.getText().toString();

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
            startActivity(new Intent(RegisterActivity.this, Menu.class));
        }
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }
}