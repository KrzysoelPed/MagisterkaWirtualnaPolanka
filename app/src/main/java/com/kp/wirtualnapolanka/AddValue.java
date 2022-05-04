package com.kp.wirtualnapolanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddValue extends AppCompatActivity {

    EditText numer_pomieszczenia;
    EditText poziom_budynku;
    EditText typ_pomieszczenia;
    EditText opiekun_sali;
    Button add_value;
    Spinner type_spinner;

    DatabaseReference pomieszczeniedBref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.add_value);

        numer_pomieszczenia = findViewById (R.id.pomieszczenie_name);
        poziom_budynku = findViewById (R.id.poziom_budynku);
        //typ_pomieszczenia = findViewById (R.id.typ_pomieszczenia);
        opiekun_sali = findViewById (R.id.opiekun_sali);
        add_value = findViewById (R.id.add_value);
        type_spinner = findViewById (R.id.type_spinner);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource (this, R.array.type_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource (R.layout.spinner_view);
        type_spinner.setAdapter (adapter);

        pomieszczeniedBref = FirebaseDatabase.getInstance ().getReference ().child ("Pomieszczenie");

        add_value.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                insertValueToDatabase();
            }
        });
    }
    private  void  insertValueToDatabase()
    {
        String pomieszczenie = numer_pomieszczenia.getText().toString();
        String poziom = poziom_budynku.getText().toString();
        String pomieszczenie_typ = type_spinner.getSelectedItem ().toString ();
        String opiekun = opiekun_sali.getText ().toString ();

        Pomieszczenie newPomieszczenie = new Pomieszczenie (pomieszczenie,poziom,pomieszczenie_typ,opiekun);
        pomieszczeniedBref.push ().setValue (newPomieszczenie);

        pomieszczeniedBref.child ("Pomieszczenie").get().addOnSuccessListener (new OnSuccessListener<DataSnapshot> () {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Intent new_intent = new Intent (AddValue.this, MainActivity.class);
                startActivity (new_intent);
                Toast.makeText (AddValue.this, "Dodano nowe pomieszczenie!", Toast.LENGTH_LONG).show ();
            }
        });
    }
}
