package com.kp.wirtualnapolanka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button choose_floor_button;
    Button qr_scan_button;
    Boolean czy_zalogowano;
    Intent choose_floor_view;
    Intent login_panel;
    Intent register_panel;
    Intent add_new_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent get_login_value = getIntent ();
        czy_zalogowano = get_login_value.getBooleanExtra ("czy_zalogowany", false);

        if(czy_zalogowano.booleanValue () == true)
        {
            Toast.makeText (this, "ZALOGOWANO USERA", Toast.LENGTH_LONG).show ();
        }

        choose_floor_button = findViewById (R.id.wybor_pietra);



        choose_floor_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                choose_floor_view = new Intent (MainActivity.this, Choose_Floor.class );
                startActivity (choose_floor_view);
            }
        });

        qr_scan_button = findViewById(R.id.qrCodeScan);

        qr_scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);

                intentIntegrator.setPrompt("W celu użycia lampki, użyj przycisku głośności w górę");

                intentIntegrator.setBeepEnabled(true);

                intentIntegrator.setOrientationLocked(true);

                intentIntegrator.setCaptureActivity(Capture.class);

                intentIntegrator.initiateScan();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult.getContents() != null){
            Intent scanIntent = new Intent(MainActivity.this, Room_detail.class);
            scanIntent.putExtra("scanQr", intentResult.getContents());
            startActivity(scanIntent);
        }else {
            Toast.makeText(getApplicationContext(), "Oj, coś poszło nie tak", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId ())
        {
            case R.id.item1:
                if(czy_zalogowano.booleanValue () == true) {
                    item.setVisible (false);
                }
                    login_panel = new Intent (MainActivity.this, Login.class);
                    startActivity (login_panel);
                return true;
            case R.id.item2:
                if(czy_zalogowano.booleanValue () == false) {
                    register_panel = new Intent (MainActivity.this, Register.class);
                    startActivity (register_panel);
                }
                return true;
            case R.id.DodajRekord:
                add_new_value = new Intent (MainActivity.this, AddValue.class);
                startActivity (add_new_value);
                return true;

            default:
                return super.onOptionsItemSelected (item);
        }

    }
}