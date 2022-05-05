package com.kp.wirtualnapolanka;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Room_detail extends AppCompatActivity {

    Toolbar toolbar;

    TextView roomIdTextView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = getIntent().getStringExtra("roomID");
        name = name.replace("p","");
        name = "Pokój: P" + name;

        roomIdTextView = (TextView) findViewById(R.id.roomIdTextView);
        roomIdTextView.setText(name);


    }
}
