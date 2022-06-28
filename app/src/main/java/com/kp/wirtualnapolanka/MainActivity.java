package com.kp.wirtualnapolanka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

//import com.kontakt.sdk.android.common.KontaktSDK;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerFactory;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleIBeaconListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ProximityManager proximityManager;
    Toolbar toolbar;
    Button kontakt, webpage;
    Button choose_floor_button;
    Button qr_scan_button;
    Button door;
    Boolean czy_zalogowano;
    Boolean czy_klik_door = false;
    String zamek, obecnosc;
    Intent choose_floor_view;
    Intent login_panel;
    Intent register_panel;
    Intent add_new_value, refresh;
    Intent kontakt_intent;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference pomieszczeniedBref;
    MenuItem mLogout, mLogin, mSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance ();
        KontaktSDK.initialize("XHXFxewaYczrqlXxCqIgOFFZiMUUUetY");
        db = FirebaseDatabase.getInstance ();
        pomieszczeniedBref = db.getReference ("Pomieszczenie");
        Intent get_login_value = getIntent ();
        czy_zalogowano = get_login_value.getBooleanExtra ("czy_zalogowany", false);
        door = findViewById (R.id.door_button);
        if(czy_zalogowano.booleanValue () == true)
        {
            Toast.makeText (this, "ZALOGOWANO USERA", Toast.LENGTH_LONG).show ();
            door.setVisibility (View.VISIBLE);

            pomieszczeniedBref.addValueEventListener (new ValueEventListener () {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   zamek = (String) snapshot.child("205").child("zamek").getValue();
                   obecnosc = (String) snapshot.child("205").child("obecnosc").getValue();
                   if(zamek.equals ("ZAMKNIETE"))
                   {
                       door.setText ("OTWÓRZ");
                   }
                   else if (zamek.equals ("OTWARTE"))
                   {
                       door.setText ("ZAMKNIJ");
                   }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        kontakt = findViewById (R.id.kontakt);
        webpage = findViewById (R.id.strona_wydzialowa);
        webpage.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Uri uri;

                    uri = Uri.parse("http://cat.put.poznan.pl");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(browserIntent);

        }
        });
        kontakt.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                kontakt_intent = new Intent (MainActivity.this, Kontakt.class);
                startActivity (kontakt_intent);
            }
        });
        choose_floor_button = findViewById (R.id.wybor_pietra);

        door.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                if(zamek.equals ("ZAMKNIETE") && obecnosc.equals ("OBECNY") )
                {
                    czy_klik_door = true;
                    HashMap hashMap = new HashMap ();
                    hashMap.put ("zamek", "OTWARTE");
                    pomieszczeniedBref.child ("205").updateChildren (hashMap);
                    door.setText ("ZAMKNIJ");
                }
                else if(obecnosc.equals ("NIEOBECNY") && zamek.equals ("ZAMKNIETE"))
                {
                    Toast.makeText(MainActivity.this, "JESTES ZA DALEKO OD DRZWI", Toast.LENGTH_LONG).show ();

                }

                if(zamek.equals ("OTWARTE"))
                {
                    Log.i("WARTOSC ZAMKA W IF", zamek);
                    czy_klik_door = true;
                    HashMap hashMap = new HashMap ();
                    hashMap.put ("zamek", "ZAMKNIETE");
                    pomieszczeniedBref.child ("205").updateChildren (hashMap);
                    door.setText("OTWORZ");
                }
            }
        });




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
            case R.id.PanelLogowania:
                    login_panel = new Intent (MainActivity.this, Login.class);
                    startActivity (login_panel);
                return true;
            case R.id.PanelRejestracji:
                if(czy_zalogowano.booleanValue () == false) {
                    register_panel = new Intent (MainActivity.this, Register.class);
                    startActivity (register_panel);
                }
                return true;
            case R.id.DodajRekord:
                add_new_value = new Intent (MainActivity.this, AddValue.class);
                startActivity (add_new_value);
                return true;
            case R.id.Wyloguj:
                mAuth.signOut ();
                refresh = new Intent (MainActivity.this, MainActivity.class);
                startActivity (refresh);
            default:
                return super.onOptionsItemSelected (item);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mLogin = menu.findItem (R.id.PanelLogowania);
        mSign = menu.findItem (R.id.PanelRejestracji);
        mLogout = menu.findItem (R.id.Wyloguj);
        if(czy_zalogowano = true)
        {
            mLogin.setVisible (true);
            mSign.setVisible (true);
        }
        return true;
    }
}