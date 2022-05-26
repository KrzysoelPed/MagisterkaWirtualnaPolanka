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
                //ArrayList<String> dataFromDb = new ArrayList<>();
                if(snapshot.exists()){
                    //String dataString2 = (String) snapshot.child("osoba").getValue();

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
                            Log.d("tag", "Nazwa po kliknieciu -> " + name);
                            //Log.d("tag", "ds.getKey -> " + ds.getKey());
                            //Log.d("tag", "dopasowanie -> " + dataString.matches(temp));
                            for(DataSnapshot ds:snapshot.getChildren()) {

                                if (ds.child("osoba").getValue() != null) {
                                    //String tempName = "(.*)" + name;
                                    Log.d("tag", "Name -> " + name);
                                    Log.d("tag", "ds.child(osoba) name -> " + ds.child("osoba").getValue().toString());
                                    Log.d("tag", "Equals -> " + ds.child("osoba").getValue().equals(name));


                                    if (ds.child("osoba").getValue().equals(name)) {
                                        Log.d("tag", "Wykryto nazwe -> " + ds.child("osoba").getValue().toString());
                                        String idName = ds.child("id").getValue().toString();

                                        Log.d("tag", "idName -> " + idName);

                                        Log.d("tag", "Pierwszy znak z id -> " + idName.charAt(0));
                                        if (idName.charAt(0) == '0') {
                                            Log.d("tag", "Przechodze do 0 -> " + idName.charAt(0));
                                            Intent intent = new Intent(Choose_Floor.this, Ground_floor.class);
                                            intent.putExtra("IDFromSearch", idName);
                                            idName = null;
                                            startActivity(intent);
                                            break;
                                        }
                                        if (idName.charAt(0) == '1') {
                                            Log.d("tag", "Przechodze do 1 -> " + idName.charAt(0));
                                            Intent intent = new Intent(Choose_Floor.this, First_floor.class);
                                            intent.putExtra("IDFromSearch", idName);
                                            idName = null;
                                            startActivity(intent);
                                            break;
                                        }

                                        if (idName.charAt(0) == '2') {
                                            Log.d("tag", "Przechodze do 2 -> " + idName.charAt(0));
                                            Intent intent = new Intent(Choose_Floor.this, Second_floor.class);
                                            intent.putExtra("IDFromSearch", idName);
                                            idName = null;
                                            startActivity(intent);
                                            break;
                                        }
                                    }

                                }

                                if (ds.child("id").getValue().equals(name)) {
                                    Log.d("tag", "Wykryto nazwe -> " + ds.child("osoba").getValue().toString());
                                    String idName = ds.child("id").getValue().toString();

                                    Log.d("tag", "idName -> " + idName);

                                    Log.d("tag", "Pierwszy znak z id -> " + idName.charAt(0));
                                    if (idName.charAt(0) == '0') {
                                        Log.d("tag", "Przechodze do 0 -> " + idName.charAt(0));
                                        Intent intent = new Intent(Choose_Floor.this, Ground_floor.class);
                                        intent.putExtra("IDFromSearch", idName);
                                        idName = null;
                                        startActivity(intent);
                                        break;
                                    }
                                    if (idName.charAt(0) == '1') {
                                        Log.d("tag", "Przechodze do 1 -> " + idName.charAt(0));
                                        Intent intent = new Intent(Choose_Floor.this, First_floor.class);
                                        intent.putExtra("IDFromSearch", idName);
                                        idName = null;
                                        startActivity(intent);
                                        break;
                                    }

                                    if (idName.charAt(0) == '2') {
                                        Log.d("tag", "Przechodze do 2 -> " + idName.charAt(0));
                                        Intent intent = new Intent(Choose_Floor.this, Second_floor.class);
                                        intent.putExtra("IDFromSearch", idName);
                                        idName = null;
                                        startActivity(intent);
                                        break;
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