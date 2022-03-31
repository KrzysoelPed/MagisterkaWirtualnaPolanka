package com.kp.wirtualnapolanka;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class Ground_floor extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.ground_floor);

       toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);





    }


}
