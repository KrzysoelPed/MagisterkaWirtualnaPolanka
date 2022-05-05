package com.kp.wirtualnapolanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class Ground_floor extends AppCompatActivity {

    Toolbar toolbar;
    Button[] groundFloorButtons = new Button[30];
    Intent buttonGroundFloorIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.ground_floor);

       toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        groundFloorButtons[2] = findViewById(R.id.p002);
        groundFloorButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[4] = findViewById(R.id.p004);
        groundFloorButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[5] = findViewById(R.id.p005);
        groundFloorButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[11] = findViewById(R.id.p011);
        groundFloorButtons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[13] = findViewById(R.id.p013);
        groundFloorButtons[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[14] = findViewById(R.id.p014);
        groundFloorButtons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[19] = findViewById(R.id.p019);
        groundFloorButtons[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[20] = findViewById(R.id.p020);
        groundFloorButtons[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[24] = findViewById(R.id.p024);
        groundFloorButtons[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[26] = findViewById(R.id.p026);
        groundFloorButtons[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[27] = findViewById(R.id.p027);
        groundFloorButtons[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[28] = findViewById(R.id.p028);
        groundFloorButtons[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        groundFloorButtons[29] = findViewById(R.id.p029);
        groundFloorButtons[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });
    }

    public void clickOnRoomButton(View v){
        //id = setIdForRoom(v);
        String id = v.getResources().getResourceName(v.getId());
        id = id.replace("com.kp.wirtualnapolanka:id/","");
        buttonGroundFloorIntent = new Intent(Ground_floor.this, Room_detail.class);
        buttonGroundFloorIntent.putExtra("roomID", id);
        startActivity(buttonGroundFloorIntent);
    }

    public void createButton(){
        for(int i=0; i<30; i++){
            groundFloorButtons[i] = new Button(this);
        }
    }


}