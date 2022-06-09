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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ProximityManager proximityManager;
    Toolbar toolbar;
    Button choose_floor_button;
    Button qr_scan_button;
    Boolean czy_zalogowano;
    Intent choose_floor_view;
    Intent login_panel;
    Intent register_panel;
    Intent add_new_value;
    BluetoothAdapter bluetoothAdapter;
    public ArrayList<BluetoothDevice> BTDevices = new ArrayList<> ();
    public DeviceListAdapter mDevicesListAdapter;
    ListView DevicesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);
        KontaktSDK.initialize("XHXFxewaYczrqlXxCqIgOFFZiMUUUetY");
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

        proximityManager = ProximityManagerFactory.create(this);
        proximityManager.setIBeaconListener(createIBeaconListener());

    }

    private BroadcastReceiver BroadcastReceiver3 = new BroadcastReceiver () {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction ();

            if(action.equals (BluetoothDevice.ACTION_FOUND))
            {
                BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                int rssi = intent.getShortExtra (BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                BTDevices.add (device);
                Log.d(TAG, "onReceive: "+device.getName ()+": "+device.getAddress ()+": "+rssi);
                //mDevicesListAdapter = new DeviceListAdapter (context, R.layout.devices_view_adapter, BTDevices);
                DevicesList.setAdapter (mDevicesListAdapter);
            }

        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        startScanning();

    }

    @Override
    protected void onStop() {
        proximityManager.stopScanning();
        super.onStop();
    }

    private void startScanning() {
        proximityManager.connect(new OnServiceReadyListener () {
            @Override
            public void onServiceReady() {
                proximityManager.startScanning();
            }
        });
    }
    private IBeaconListener createIBeaconListener() {
        return new SimpleIBeaconListener () {
            @Override
            public void onIBeaconDiscovered(IBeaconDevice ibeacon, IBeaconRegion region) {
                Log.i(TAG, "IBeacon discovered: " + ibeacon.toString());
            }
        };
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