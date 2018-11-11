package com.learn.eduscope.eduscope;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pw;
    private Spinner dropdown;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email   = (EditText)findViewById(R.id.input_email);
        pw = (EditText)findViewById(R.id.input_password);
        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

//create a list of items for the spinner.
        String[] items = new String[]{"Lecturer", "Student"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);



        mFirebaseDatabase =  FirebaseDatabase.getInstance();
        mDbRef = mFirebaseDatabase.getReference("USERS");


        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.toString();

                Log.d("FIREBASE_DATA", "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("FIREBASE_DATA", "An error occured");
            }
        });


    }

    public void goToDashboard(View view) {
        if(email.toString().matches("") || pw.toString().matches(""))
        {
            System.out.println("invalid");
        }
        else
        {
            System.out.println("valid");

            String spinnerSelectedItem = (String) dropdown.getSelectedItem();

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();

            if(spinnerSelectedItem.equals("Lecturer"))
            {
                editor.putString("role", "Lecturer");
                editor.commit();

                /*String role = sharedpreferences.getString("role", "");

                System.out.println("role");
                System.out.println(role);*/


                Intent intent = new Intent(this, StudentDashboard.class);
                startActivity(intent);
            }
            else
            {
                editor.putString("role", "Student");
                editor.commit();

                /*String role = sharedpreferences.getString("role", "");

                System.out.println("role");
                System.out.println(role);*/



//                Intent intent = new Intent(this, StudentDashboard.class);
//                startActivity(intent);
            }
        }

    }


}
