package com.zonamagang.zonamagang;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zonamagang.zonamagang.Model.bidang;


public class MainActivity extends AppCompatActivity {

    TextView x;
    EditText mLoginEmail;
    EditText mLoginPass;
    String nama, logo, email, id_user_string;
    String foto;
    int id_siswa;
    Context context = this;
    boolean doubleBackToExit = false;
    private static final String TAG = "MainActivity";
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbBidangRef = database.child("bidang").getRef();
    DatabaseReference dbParentBidangRef = database.child("parent_bidang").getRef();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.layoutItems();
        x = (TextView) findViewById(R.id.buatakun);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent register = new Intent(getApplicationContext(), register1.class);
                startActivity(register);
            }
        }  );

        // Read from the database
        dbBidangRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot bidangSnapshot: dataSnapshot.getChildren()) {
                    bidang value = bidangSnapshot.getValue(bidang.class);
                    Log.d(TAG, "Bidang, nama value is: " + value.getNama());

//                    HashMap<String,Boolean> parent_bidang = value.getParent_bidang();
//                    Iterator it = parent_bidang.entrySet().iterator();
//                    while(it.hasNext()){
//                        Map.Entry pair = (Map.Entry)it.next();
//                        dbParentBidangRef.child(pair.getKey().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                parent_bidang valueParentBidang = dataSnapshot.getValue(parent_bidang.class);
//                                Log.d(TAG, "Parent bidang, nama = "+valueParentBidang.getNama());
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//                        it.remove();
//                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void doLogin(View view) {
//        setContentView(R.layout.loading_screen);
        mLoginEmail.setEnabled(false);
        mLoginPass.setEnabled(false);
        email = mLoginEmail.getText().toString();
        String pass = mLoginPass.getText().toString();

    }

    private void layoutItems() {
        mLoginEmail = (EditText) findViewById(R.id.login_email);
        mLoginPass = (EditText) findViewById(R.id.login_password);
    }

    @Override
    public void onBackPressed(){
        MainActivity.this.finish();
    }
}
