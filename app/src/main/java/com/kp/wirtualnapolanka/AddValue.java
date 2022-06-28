package com.kp.wirtualnapolanka;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerFactory;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleIBeaconListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AddValue extends AppCompatActivity {

    private static final String TAG = "AddValue";
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 456;
    EditText numer_pomieszczenia;
    EditText poziom_budynku;
    EditText konsultacje;
    EditText opiekun_sali;
    String pomieszczenie,poziom,kons,opiekun,typ_pom, obecnosc, btDeviceAddress,deviceAddress, first,mac_user,mac_kontakt;
    Button add_value;
    Spinner type_spinner;
    BluetoothDevice device;
    FirebaseDatabase db;
    DatabaseReference pomieszczeniedBref;
    DatabaseReference pomieszczeniedBref_os;
    BluetoothAdapter bluetoothAdapter;
    BroadcastReceiver mBroadCastReceiver;
    public ArrayList<BluetoothDevice> BTDevices = new ArrayList<> ();
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ListView DevicesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.add_value);
        KontaktSDK.initialize("XHXFxewaYczrqlXxCqIgOFFZiMUUUetY");
        numer_pomieszczenia = findViewById (R.id.pomieszczenie_name);
        poziom_budynku = findViewById (R.id.poziom_budynku);
        konsultacje = findViewById (R.id.konsultacje);
        opiekun_sali = findViewById (R.id.opiekun_sali);
        add_value = findViewById (R.id.add_value);
        type_spinner = findViewById (R.id.type_spinner);
        DevicesList = findViewById (R.id.lista_elementow);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource (this, R.array.type_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource (R.layout.spinner_view);
        type_spinner.setAdapter (adapter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter ();


        if(bluetoothAdapter.isEnabled ())
        {
            bluetoothAdapter.startDiscovery ();
            deviceAddress = bluetoothAdapter.getAddress ();
            mac_user = deviceAddress;
            mBroadCastReceiver = new BroadcastReceiver () {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction ();
                    if (action.equals (BluetoothDevice.ACTION_FOUND)) {
                            device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                        if(device.getAddress ().contains ("CA:17:C9")) {
                            btDeviceAddress = device.getAddress ();
                            int rssi = intent.getShortExtra (BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                            stringArrayList.add (btDeviceAddress);
                            for (int i = 0; i < stringArrayList.size (); i++) {
                                if (stringArrayList.get (i).contains ("CA:17:C9")) {
                                    first = stringArrayList.get (i);
                                    mac_kontakt = first;
                                }
                            }

                            Log.i ("BLUETOOTH DEVICE", first);
                            Log.i ("BLUETOOTH USER", deviceAddress);
                            Log.i ("BLUETOOTH RSSI", String.valueOf (rssi));

                            if (deviceAddress.equals ("02:00:00:00:00:00")) {
                                if (btDeviceAddress.contains ("CA:17:C9")) {
                                    if (rssi > -45) {
                                        Log.i ("BLUETOOTH PAIR", "POPRAWNE SPAROWANIE");
                                        HashMap hashMap = new HashMap ();
                                        hashMap.put("obecnosc", "OBECNY");
                                        pomieszczeniedBref.child ("220").updateChildren (hashMap);
                                        bluetoothAdapter.cancelDiscovery ();
                                        //obecnosc = "OBECNY";
                                    } else if (rssi <= -45) {
                                        Log.i ("BLUETOOTH PAIR", "NIEPOPRAWNE SPAROWANIE");
                                        HashMap hashMap = new HashMap ();
                                        hashMap.put("obecnosc", "NIEOBECNY");
                                        pomieszczeniedBref.child ("220").updateChildren (hashMap);
                                        bluetoothAdapter.cancelDiscovery ();
                                        unregisterReceiver (mBroadCastReceiver);
                                        //obecnosc = "NIEOBECNY";
                                    }
                                }
                            }

                        }
                    }

                }


            };



            IntentFilter intentFilter = new IntentFilter (BluetoothDevice.ACTION_FOUND);
            registerReceiver (mBroadCastReceiver,intentFilter);
            stringArrayList.clear ();
            //Toast.makeText (this, "BLUETOOTH WLACZONY", Toast.LENGTH_SHORT).show ();
            //Toast.makeText (this, deviceAddress, Toast.LENGTH_SHORT).show ();
        }
        else
        {
            Toast.makeText (this, "BLUETOOTH WYLACZONY", Toast.LENGTH_SHORT).show ();
        }

        add_value.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                db = FirebaseDatabase.getInstance ();
                pomieszczeniedBref = db.getReference ("Pomieszczenie");
                pomieszczeniedBref_os = db.getReference ("Osoby");
                pomieszczenie = numer_pomieszczenia.getText ().toString ();
                poziom = poziom_budynku.getText ().toString ();
                kons = konsultacje.getText ().toString ();
                opiekun = opiekun_sali.getText ().toString ();
                typ_pom = type_spinner.getSelectedItem ().toString ();
                //obecnosc = "NIEOBECNY";
                //Osoba newOsoba = new Osoba (pomieszczenie, opiekun);
                //pomieszczeniedBref_os.child (opiekun).setValue (newOsoba);
                Pomieszczenie newPomieszczenie = new Pomieszczenie(pomieszczenie, poziom, typ_pom, opiekun, kons, mac_user, mac_kontakt);
                pomieszczeniedBref.child (pomieszczenie).setValue(newPomieszczenie);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, yay! Start the Bluetooth device scan.
                } else {
                    // Alert the user that this application requires the location permission to perform the scan.
                }
            }
        }
    }
}


