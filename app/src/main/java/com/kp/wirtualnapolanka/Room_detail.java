package com.kp.wirtualnapolanka;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Room_detail extends AppCompatActivity {

    private static final String TAG = "Room_detail";
    Toolbar toolbar;

    DatabaseReference mDataBase;
    String mPomieszczenie;

    TextView roomIdTextView;
    TextView roomDetailTextView;
    TextView roomTimeTextView;
    String id;
    String detail;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBase = FirebaseDatabase.getInstance().getReference().child("Pomieszczenie");


        //mDataBase.add


        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mPomieszczenie = String.valueOf(snapshot.child("-N181Q-4YVci3y8-7nmM").child("opiekun").getValue());
                    Log.d(TAG,"test " + mPomieszczenie);

                    roomDetailTextView.setText(mPomieszczenie);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d(TAG,"test2 " + detail);

        id = getIntent().getStringExtra("roomID");
        id = id.replace("p","");
/*
        if(id.equals("106")) {
            detail = "Laboratorium Układów Programowalnych i Systemów Wbudowanych";
        }else
        {
            detail = "Prof. Dr hab. inż. Adrian Kliks";
        }

 */

        id = "Pokój: P" + id;





        roomIdTextView = (TextView) findViewById(R.id.roomIdTextView);
        roomIdTextView.setText(id);
        roomDetailTextView = (TextView) findViewById(R.id.pokoj);
        roomDetailTextView.setText(detail);


    }
}
