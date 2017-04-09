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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        
        

        ImageView butSend = (ImageView) findViewById(R.id.boton_send);
        butSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              
                    


            }
        });

        ImageView butSendLoc = (ImageView) findViewById(R.id.boton_send_loc);
        butSendLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              
            }
        });




    }

   




}
