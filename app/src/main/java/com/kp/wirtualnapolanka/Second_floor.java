package com.kp.wirtualnapolanka;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Second_floor extends AppCompatActivity {

    Toolbar toolbar;
    Button[] secondFloorButtons = new Button[45];
    Intent buttonSecondFloorIntent;
    int idSearch = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_floor);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        secondFloorButtons[1] = findViewById(R.id.p201);
        secondFloorButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[2] = findViewById(R.id.p202);
        secondFloorButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[3] = findViewById(R.id.p203);
        secondFloorButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[4] = findViewById(R.id.p204);
        secondFloorButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[5] = findViewById(R.id.p205);
        secondFloorButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { clickOnRoomButton(v); }
        });

        secondFloorButtons[6] = findViewById(R.id.p206);
        secondFloorButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[7] = findViewById(R.id.p207);
        secondFloorButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[8] = findViewById(R.id.p208);
        secondFloorButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[9] = findViewById(R.id.p209);
        secondFloorButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[10] = findViewById(R.id.p210);
        secondFloorButtons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[11] = findViewById(R.id.p211);
        secondFloorButtons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[16] = findViewById(R.id.p216);
        secondFloorButtons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[17] = findViewById(R.id.p217);
        secondFloorButtons[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[18] = findViewById(R.id.p218);
        secondFloorButtons[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[19] = findViewById(R.id.p219);
        secondFloorButtons[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[20] = findViewById(R.id.p220);
        secondFloorButtons[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[21] = findViewById(R.id.p221);
        secondFloorButtons[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[22] = findViewById(R.id.p222);
        secondFloorButtons[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[23] = findViewById(R.id.p223);
        secondFloorButtons[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[24] = findViewById(R.id.p224);
        secondFloorButtons[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[25] = findViewById(R.id.p225);
        secondFloorButtons[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[26] = findViewById(R.id.p226);
        secondFloorButtons[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[27] = findViewById(R.id.p227);
        secondFloorButtons[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[28] = findViewById(R.id.p228);
        secondFloorButtons[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[29] = findViewById(R.id.p229);
        secondFloorButtons[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[30] = findViewById(R.id.p230);
        secondFloorButtons[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[31] = findViewById(R.id.p231);
        secondFloorButtons[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[32] = findViewById(R.id.p232);
        secondFloorButtons[32].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[34] = findViewById(R.id.p234);
        secondFloorButtons[34].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[35] = findViewById(R.id.p235);
        secondFloorButtons[35].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[36] = findViewById(R.id.p236);
        secondFloorButtons[36].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[37] = findViewById(R.id.p237);
        secondFloorButtons[37].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[38] = findViewById(R.id.p238);
        secondFloorButtons[38].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[39] = findViewById(R.id.p239);
        secondFloorButtons[39].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[41] = findViewById(R.id.p241);
        secondFloorButtons[41].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[42] = findViewById(R.id.p242);
        secondFloorButtons[42].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        secondFloorButtons[43] = findViewById(R.id.p243);
        secondFloorButtons[43].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRoomButton(v);
            }
        });

        if(getIntent().getStringExtra("IDFromSearch") != null)
        idSearch = Integer.parseInt(getIntent().getStringExtra("IDFromSearch").substring(1));
        if(idSearch != 0){
            ObjectAnimator colorAnim = ObjectAnimator.ofInt(secondFloorButtons[idSearch], "textColor", Color.RED, Color.TRANSPARENT);
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
        Log.i("Tag", "Tu jestem");
        id = id.replace("com.kp.wirtualnapolanka:id/","");
        buttonSecondFloorIntent = new Intent(Second_floor.this, Room_detail.class);
        buttonSecondFloorIntent.putExtra("roomID", id);
        //buttonSecondFloorIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(buttonSecondFloorIntent);
        finish();
    }

    public void createButton(){
        for(int i=0; i<45; i++){
            secondFloorButtons[i] = new Button(this);
        }
    }
}
