package barbarahliskov.cambialibros;


import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat extends AppCompatActivity {

    int huecoLibre = 0;
    double lat = 0.0;
    double lon = 0.0;
    int numMensajes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle("Chat con <LEGENDANNY>");
        if(numMensajes == 0){
            LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
            LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
            LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
            LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
        }

        ImageView butSend = (ImageView) findViewById(R.id.boton_send);
        butSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numMensajes == 0){
                    LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
                    layout1.setVisibility(View.VISIBLE);
                    TextView texto = (TextView) findViewById(R.id.textView);
                    texto.setText("Hola! Soy Daniel. Estoy interesado en tus ultimos dos libros, porque me gusta mucho Dan Brown. ¿Podríamos quedar para itnercambiarlos?");
                    numMensajes++;
                } else if(numMensajes == 1){
                    LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
                    layout2.setVisibility(View.VISIBLE);
                    TextView texto = (TextView) findViewById(R.id.textView1);
                    texto.setText("Si claro, ¿Qué día quieres quedar?");
                    numMensajes++;
                } else if(numMensajes == 2){
                    LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
                    layout3.setVisibility(View.VISIBLE);
                    TextView texto = (TextView) findViewById(R.id.textView3);
                    texto.setText("Me iría bien la semana que viene. \n No obstante, igual no puedo acudir porque tengo viajes de negocio. \n SI te parece bien, te aviso en cuanto pueda. \n De todas maneras, envíame tus coordenadas y así acudo ahí mismo.");
                    numMensajes++;
                }


            }
        });

        ImageView butSendLoc = (ImageView) findViewById(R.id.boton_send_loc);
        butSendLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Chat.this, Chat_GPS.class);
                startActivityForResult(intent,2);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
                layout4.setVisibility(View.VISIBLE);
                TextView texto = (TextView) findViewById(R.id.textView5);
                texto.setText("Estas son mis coordenadas: " + data.getDataString());


            }
        }
    }




}
