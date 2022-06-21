package com.kp.wirtualnapolanka;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;



import androidx.appcompat.app.AppCompatActivity;

public class First_floor extends AppCompatActivity {

    Toolbar toolbar;
    Button[] firstFloorButtons = new Button[50];
    Intent buttonFirstFloorIntent;
    int idSearch = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_floor);
        createButton();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstFloorButtons[1] = findViewById(R.id.p101);
        firstFloorButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[2] = findViewById(R.id.p102);
        firstFloorButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[3] = findViewById(R.id.p103);
        firstFloorButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[4] = findViewById(R.id.p104);
        firstFloorButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[5] = findViewById(R.id.p105);
        firstFloorButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[6] = findViewById(R.id.p106);
        firstFloorButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[7] = findViewById(R.id.p107);
        firstFloorButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[8] = findViewById(R.id.p108);
        firstFloorButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[9] = findViewById(R.id.p109);
        firstFloorButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[14] = findViewById(R.id.p114);
        firstFloorButtons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[15] = findViewById(R.id.p115);
        firstFloorButtons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[16] = findViewById(R.id.p116);
        firstFloorButtons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[17] = findViewById(R.id.p117);
        firstFloorButtons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[18] = findViewById(R.id.p118);
        firstFloorButtons[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[19] = findViewById(R.id.p119);
        firstFloorButtons[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[20] = findViewById(R.id.p120);
        firstFloorButtons[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[21] = findViewById(R.id.p121);
        firstFloorButtons[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[22] = findViewById(R.id.p122);
        firstFloorButtons[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[23] = findViewById(R.id.p123);
        firstFloorButtons[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[24] = findViewById(R.id.p124);
        firstFloorButtons[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[25] = findViewById(R.id.p125);
        firstFloorButtons[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[26] = findViewById(R.id.p126);
        firstFloorButtons[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[28] = findViewById(R.id.p128);
        firstFloorButtons[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[29] = findViewById(R.id.p129);
        firstFloorButtons[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[30] = findViewById(R.id.p130);
        firstFloorButtons[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[31] = findViewById(R.id.p131);
        firstFloorButtons[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[32] = findViewById(R.id.p132);
        firstFloorButtons[32].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[33] = findViewById(R.id.p133);
        firstFloorButtons[33].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[34] = findViewById(R.id.p134);
        firstFloorButtons[34].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[35] = findViewById(R.id.p135);
        firstFloorButtons[35].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[36] = findViewById(R.id.p136);
        firstFloorButtons[36].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[37] = findViewById(R.id.p137);
        firstFloorButtons[37].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[38] = findViewById(R.id.p138);
        firstFloorButtons[38].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        firstFloorButtons[39] = findViewById(R.id.p139);
        firstFloorButtons[39].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        if(getIntent().getStringExtra("IDFromSearch") != null)
            idSearch = Integer.parseInt(getIntent().getStringExtra("IDFromSearch").substring(1));
        if(idSearch != 0){
            ObjectAnimator colorAnim = ObjectAnimator.ofInt(firstFloorButtons[idSearch], "textColor", Color.RED, Color.TRANSPARENT);
            colorAnim.setDuration(1000);
            colorAnim.setEvaluator(new ArgbEvaluator());
            colorAnim.setRepeatCount(ValueAnimator.INFINITE);
            colorAnim.setRepeatMode(ValueAnimator.REVERSE);
            colorAnim.start();
        }

    }

    public void clickOnRoomButton(View v){
        //id = setIdForRoom(v);
        String id = v.getResources().getResourceName(v.getId());
        id = id.replace("com.kp.wirtualnapolanka:id/","");
        buttonFirstFloorIntent = new Intent(First_floor.this, Room_detail.class);
        buttonFirstFloorIntent.putExtra("roomID", id);
        startActivity(buttonFirstFloorIntent);
        finish();
    }

    public void createButton(){
        for(int i=0; i<50; i++){
            firstFloorButtons[i] = new Button(this);
        }
    }
}
