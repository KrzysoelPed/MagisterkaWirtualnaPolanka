package com.kp.wirtualnapolanka;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Room_detail extends AppCompatActivity {

    private static final String TAG = "Room_detail";
    Toolbar toolbar;

    Button qrButton;

    FirebaseDatabase mDataBase;
    DatabaseReference mDataRef;

    String idScan;
    String idMap;
    String id;
    String idBase;
    String rDetailPerson;
    String rDetailName;
    String rDetailInfo;
    String rTimeInfo;
    String rPresenceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Widoki z pliku roomdetail.xml
        View loadingPanel = (View) findViewById(R.id.loadingPanel);

        View roomDetailLayout = (View) findViewById(R.id.roomDetailLayout);
        ImageView roomDetailImage = (ImageView) findViewById(R.id.roomDetailImage);
        TextView roomDetailName = (TextView) findViewById(R.id.roomDetailName);
        TextView roomDetailInfo = (TextView) findViewById(R.id.roomDetailInfo);

        View timeLayout = (View) findViewById(R.id.timeLayout);
        ImageView timeImage = (ImageView) findViewById(R.id.timeImage);
        TextView timeName = (TextView) findViewById(R.id.timeName);
        TextView timeInfo = (TextView) findViewById(R.id.timeInfo);

        View presenceLayout = (View) findViewById(R.id.presenceLayout);
        ImageView presenceImage = (ImageView) findViewById(R.id.presenceImage);
        TextView presenceName = (TextView) findViewById(R.id.presenceName);
        TextView presenceInfo = (TextView) findViewById(R.id.presenceInfo);

        qrButton = findViewById(R.id.qrGeneratorButton);
        // --------------------

        idScan = (String) getIntent().getStringExtra("scanQr");
        idMap = (String) getIntent().getStringExtra("roomID");
        if (idScan == null){
            id = idMap;
        } else {
            id = idScan;
        }

        id = id.replace("p", "");
        idBase = id;

        TextView roomIdTextView = findViewById(R.id.roomIdTextView);
        roomIdTextView.setText("Pokój: " + idBase);

        mDataBase = FirebaseDatabase.getInstance();
        mDataRef = mDataBase.getReference("Pomieszczenie");

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Room_detail.this, Qr_code_generator.class);
                i.putExtra("qrGenerator", id);
                startActivity(i);
            }
        });
/*
        Button openDoor = findViewById(R.id.openDoor);
        openDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(openDoor.getText().equals("OTWÓRZ")){
                    openDoor.setText("ZAMKNIJ");
                    HashMap hashMap = new HashMap();
                    hashMap.put("obecnosc", "OBECNY");
                    mDataRef.child("220").updateChildren(hashMap);
                }
                else
                {
                    openDoor.setText("OTWÓRZ");
                    HashMap hashMap = new HashMap();
                    hashMap.put("obecnosc", "NIEOBECNY");
                    mDataRef.child("220").updateChildren(hashMap);
                }
            }
        });
*/
        mDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    rDetailPerson = (String) snapshot.child(idBase).child("osoba").getValue();
                    rDetailName = (String) snapshot.child(idBase).child("pomieszczenie_typ").getValue();
                    rDetailInfo = (String) snapshot.child(idBase).child("informacje").getValue();
                    rTimeInfo = (String) snapshot.child(idBase).child("konsultacje").getValue();
                    rPresenceInfo = (String) snapshot.child(idBase).child("obecnosc").getValue();



                    if(rDetailName.equals("Pokój prywatny")){
                        roomDetailName.setText("PROWADZĄCY");
                        roomDetailImage.setBackgroundResource(R.drawable.osoba);
                        rDetailPerson = rDetailPerson.replace("bb", " \n ");
                        roomDetailInfo.setText(rDetailPerson);

                        timeName.setText("KONSULTACJE");
                        timeImage.setBackgroundResource(R.drawable.zegar_ikona);
                        Log.d(TAG, "Jaka wartosc ma rDetailName = " + rTimeInfo);
                        rTimeInfo = rTimeInfo.replace("bb", " \n ");
                        rTimeInfo = rTimeInfo.replace("aa", " \n ");
                        Log.d(TAG, "Jaka wartosc ma rDetailName = " + rTimeInfo);
                        timeInfo.setText(rTimeInfo);

                        presenceName.setText("OBECNOŚĆ");
                        presenceImage.setBackgroundResource(R.drawable.obecnosc);
                        if(rPresenceInfo.equals("NIEOBECNY")){
                            presenceInfo.setTextColor(getResources().getColor(R.color.red));
                            presenceInfo.setText(rPresenceInfo);
                        }else{
                            presenceInfo.setTextColor(getResources().getColor(R.color.green));
                            presenceInfo.setText(rPresenceInfo);
                        }

                    }else{
                        roomDetailName.setText("POMIESZCZENIE");
                        roomDetailImage.setBackgroundResource(0);
                        roomDetailImage.setBackgroundResource(R.drawable.drzwi_ikona1);
                        roomDetailInfo.setText(rDetailName);

                        timeName.setText("INFORMACJE");
                        timeImage.setBackgroundResource(0);
                        timeImage.setBackgroundResource(R.drawable.informacje_o_sali);
                        timeInfo.setText(rDetailInfo);

                        presenceLayout.setVisibility(View.GONE);
                        presenceInfo.setVisibility(View.GONE);

                    }

                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    findViewById(R.id.roomDetailPanel).setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Problem z odczytem");
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Room_detail.this, Choose_Floor.class);
        startActivity(intent);
    }

}
