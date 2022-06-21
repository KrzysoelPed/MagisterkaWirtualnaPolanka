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
    Button button_ground;
    Button button_first_floor;
    Button button_second_floor;
    Intent button_ground_intent;
    Intent button_first_floor_intent;
    Intent button_second_floor_intent;

    DatabaseReference mDataRef;
    DatabaseReference mDataRef2;
    private ListView listData;
    private AutoCompleteTextView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.choose_floor);

        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        mDataRef = FirebaseDatabase.getInstance().getReference("Osoby");
        mDataRef2 = FirebaseDatabase.getInstance().getReference("Pomieszczenie");
        listData = (ListView)findViewById(R.id.listData);
        search = (AutoCompleteTextView)findViewById(R.id.search);

        ArrayList<String> dataFromDb = new ArrayList<>();

        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds:snapshot.getChildren()){
                        if(ds.child("osoba").getValue() != null){
                            String dataString = (String) ds.child("osoba").getValue();
                            dataFromDb.add(dataString);
                        }
                    }

                    mDataRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    if (ds.child("pomieszczenie").getValue() != null) {
                                        String dataString = ds.child("pomieszczenie").getValue().toString();
                                        dataFromDb.add(dataString);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                        });

                    ArrayAdapter adapter = new ArrayAdapter<String>(Choose_Floor.this, android.R.layout.simple_list_item_1, dataFromDb);
                    search.setAdapter(adapter);
                    search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String name = search.getText().toString();
                            Log.d("TAG", "Test name: " + name);
                            for(DataSnapshot ds:snapshot.getChildren()) {

                                if (ds.child("osoba").getValue() != null) {
                                    if (ds.child("osoba").getValue().equals(name)) {
                                        String idName = ds.child("id").getValue().toString();
                                        Log.d("TAG", "Test idName1: " + idName);

                                        if (idName.charAt(0) == '0') {
                                            Intent intent = new Intent(Choose_Floor.this, Ground_floor.class);
                                            intent.putExtra("IDFromSearch", idName);
                                            idName = null;
                                            dataFromDb.clear();
                                            startActivity(intent);

                                            break;
                                        }
                                        if (idName.charAt(0) == '1') {
                                            Intent intent = new Intent(Choose_Floor.this, First_floor.class);
                                            intent.putExtra("IDFromSearch", idName);
                                            idName = null;
                                            dataFromDb.clear();
                                            startActivity(intent);
                                            //finish();
                                            break;
                                        }

                                        if (idName.charAt(0) == '2') {
                                            Intent intent = new Intent(Choose_Floor.this, Second_floor.class);
                                            intent.putExtra("IDFromSearch", idName);
                                            idName = null;
                                            dataFromDb.clear();
                                            startActivity(intent);
                                            //finish();
                                            break;
                                        }
                                    }

                                }
                                Log.d("TAG", "Test 0");
                                Log.d("TAG", "Test ds child" + ds.child("id").getValue());

                                mDataRef2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot ds:snapshot.getChildren()) {
                                            if (ds.child("pomieszczenie").getValue().equals(name)) {

                                                String idName = ds.child("pomieszczenie").getValue().toString();
                                                Log.d("TAG", "Test idName2: " + idName);

                                                if (idName.charAt(0) == '0') {
                                                    Intent intent = new Intent(Choose_Floor.this, Ground_floor.class);
                                                    intent.putExtra("IDFromSearch", idName);
                                                    idName = null;
                                                    dataFromDb.clear();
                                                    startActivity(intent);
                                                    break;
                                                }
                                                if (idName.charAt(0) == '1') {
                                                    Intent intent = new Intent(Choose_Floor.this, First_floor.class);
                                                    intent.putExtra("IDFromSearch", idName);
                                                    idName = null;
                                                    dataFromDb.clear();
                                                    startActivity(intent);
                                                    break;
                                                }

                                                if (idName.charAt(0) == '2') {
                                                    Intent intent = new Intent(Choose_Floor.this, Second_floor.class);
                                                    intent.putExtra("IDFromSearch", idName);
                                                    idName = null;
                                                    dataFromDb.clear();
                                                    startActivity(intent);
                                                    break;
                                                }
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                                Log.d("TAG", "Test 02");

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
                dataFromDb.clear();
                startActivity(button_ground_intent);
            }
        });

        button_first_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_first_floor_intent = new Intent(Choose_Floor.this, First_floor.class);
                dataFromDb.clear();
                startActivity(button_first_floor_intent);
            }
        });

        button_second_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_second_floor_intent = new Intent(Choose_Floor.this, Second_floor.class);
                dataFromDb.clear();
                startActivity(button_second_floor_intent);
            }
        });







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Choose_Floor.this, MainActivity.class);
        startActivity(intent);
    }
}