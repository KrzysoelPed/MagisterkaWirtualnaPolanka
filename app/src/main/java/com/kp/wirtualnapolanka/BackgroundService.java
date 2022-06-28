package com.kp.wirtualnapolanka;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class BackgroundService extends Service {
    BluetoothAdapter bluetoothAdapter;
    BroadcastReceiver mBroadCastReceiver;
    BluetoothDevice device;
    FirebaseDatabase db;
    String btDeviceAddress,deviceAddress, first,mac_user,mac_kontakt;
    DatabaseReference pomieszczeniedBref;
    ArrayList<String> stringArrayList = new ArrayList<String>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate ();

        db = FirebaseDatabase.getInstance ();
        pomieszczeniedBref = db.getReference ("Pomieszczenie");
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter ();

        CheckBeacon ();

}
public void CheckBeacon()
{
    if (bluetoothAdapter.isEnabled ()) {
        bluetoothAdapter.startDiscovery ();
        deviceAddress = bluetoothAdapter.getAddress ();
        mac_user = deviceAddress;
        mBroadCastReceiver = new BroadcastReceiver () {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction ();
                if (action.equals (BluetoothDevice.ACTION_FOUND)) {
                    device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                    if (device.getAddress ().contains ("CE:FB:EA")) {
                        btDeviceAddress = device.getAddress ();
                        int rssi = intent.getShortExtra (BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                        stringArrayList.add (btDeviceAddress);
                        for (int i = 0; i < stringArrayList.size (); i++) {
                            if (stringArrayList.get (i).contains ("CE:FB:EA")) {
                                first = stringArrayList.get (i);
                                mac_kontakt = first;
                            }
                        }
                        Log.i ("TEST DEVICE", first);
                        Log.i ("TEST RSSI", String.valueOf (rssi));

                        if (deviceAddress.equals ("02:00:00:00:00:00")) {
                            if (btDeviceAddress.contains ("CE:FB:EA")) {
                                if (rssi > -68) {
                                    HashMap hashMap = new HashMap ();
                                    hashMap.put ("obecnosc", "OBECNY");
                                    pomieszczeniedBref.child ("205").updateChildren (hashMap);
                                    bluetoothAdapter.cancelDiscovery ();
                                } else if (rssi <= -68) {
                                    HashMap hashMap = new HashMap ();
                                    hashMap.put ("obecnosc", "NIEOBECNY");
                                    pomieszczeniedBref.child ("205").updateChildren (hashMap);
                                    bluetoothAdapter.cancelDiscovery ();
                                }
                            }
                        }


                    }
                }

            }


        };

        IntentFilter intentFilter = new IntentFilter (BluetoothDevice.ACTION_FOUND);
        registerReceiver (mBroadCastReceiver, intentFilter);

        stringArrayList.clear ();
    }

    refresh(3000);
}

    @Override
    public void onDestroy() {
        super.onDestroy ();
        unregisterReceiver (mBroadCastReceiver);
    }

    private void refresh(int miliseconds)
{
    final Handler handler = new Handler ();
    final Runnable runnable = new Runnable () {
        @Override
        public void run() {
            onDestroy ();
            CheckBeacon ();
        }
    };
    handler.postDelayed (runnable, miliseconds);
}
}
