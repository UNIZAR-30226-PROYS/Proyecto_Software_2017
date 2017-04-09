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

        
    }

    
}