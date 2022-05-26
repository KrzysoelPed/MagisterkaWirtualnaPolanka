package com.kp.wirtualnapolanka;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Choose_Floor extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton button_ground;
    ImageButton button_first_floor;
    ImageButton button_second_floor;
    Intent button_ground_intent;
    Intent button_first_floor_intent;
    Intent button_second_floor_intent;

    DatabaseReference mDataRef;
    private ListView listData;
    private AutoCompleteTextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.choose_floor);

        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        mDataRef = FirebaseDatabase.getInstance().getReference("Osoby");
        listData = (ListView)findViewById(R.id.listData);
        search = (AutoCompleteTextView)findViewById(R.id.search);

        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> dataFromDb = new ArrayList<>();
                if(snapshot.exists()){
                    String dataString2 = (String) snapshot.child("osoba").getValue();

                    for(DataSnapshot ds:snapshot.getChildren()){

                        if(ds.child("osoba").getValue() != null){
                            String dataString = (String) ds.child("osoba").getValue();

                            String temp = "(.*)" + dataString + "(.*)";
                            //Log.d("tag", "Data string -> " + dataString);
                            //Log.d("tag", "ds.getKey -> " + ds.getKey());
                            //Log.d("tag", "dopasowanie -> " + dataString.matches(temp));


                            //if(dataString.matches(temp)){
                                //Log.d("tag", "Wykryto key id -> " + ds.child("id").getValue());
                            //}

                            dataFromDb.add(dataString);
                        }
                    }





                    ArrayAdapter adapter = new ArrayAdapter<String>(Choose_Floor.this, android.R.layout.simple_list_item_1, dataFromDb);
                    search.setAdapter(adapter);
                    search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String name = search.getText().toString();
                            Log.d("tag", "Data string -> " + name);
                            //Log.d("tag", "ds.getKey -> " + ds.getKey());
                            //Log.d("tag", "dopasowanie -> " + dataString.matches(temp));
                            for(DataSnapshot ds:snapshot.getChildren()){

                                if(ds.child("osoba").getValue() != null){
                                    String temp = "(.*)" + name + "(.*)";

                                    if(name.matches(temp)){
                                        Log.d("tag", "Wykryto id -> " + ds.child("id").getValue());
                                        String idName = ds.child("id").getValue().toString();

                                        Intent intent = new Intent(Choose_Floor.this, Second_floor.class);
                                        intent.putExtra("IDFromSearch", idName);
                                        startActivity(intent);

                                        //int id = getResources().getIdentifier("p222","id",getPackageName());
                                        //int id = R.id.p222;
                                        //Log.d("tag", "Wykryto id otrzymane -> " + id);

                                        //String stringTemp = "R.id.p222";

                                        //Button button = (Button)findViewById((Integer)1000472);
                                        //button.setText("OK");




                                    }
                                }
                            }

                        }
                    });

                }else {
                    Log.d("tag", "Nie znaleziono danych");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mDataRef.addListenerForSingleValueEvent(event);

        button_ground = findViewById(R.id.ground);
        button_first_floor = findViewById(R.id.floor1);
        button_second_floor = findViewById(R.id.floor2);

        button_ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_ground_intent = new Intent(Choose_Floor.this, Ground_floor.class);
                startActivity(button_ground_intent);
            }
        });

        button_first_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_first_floor_intent = new Intent(Choose_Floor.this, First_floor.class);
                startActivity(button_first_floor_intent);
            }
        });

        button_second_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_second_floor_intent = new Intent(Choose_Floor.this, Second_floor.class);
                startActivity(button_second_floor_intent);
            }
        });







    }
}