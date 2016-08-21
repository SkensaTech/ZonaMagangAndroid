package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class step_1_industri extends AppCompatActivity {

    EditText mNama;
    EditText mAlamat;
    EditText mTelp;
    Spinner mKota;
    Button mSubmit;
    //Sebelumnya :
    String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1_industri);
        this.layoutItems();

        //get value sebelumnya
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");

        Spinner kota = (Spinner) findViewById(R.id.industri_step_1_kota);
        List<String> kotas = new ArrayList<>();
        kotas.add("Kota/Kabupaten");
        kotas.add("Denpasar");
        kotas.add("Badung");
        kotas.add("Gianyar");
        kotas.add("Tabanan");
        kotas.add("Bangli");
        kotas.add("Klungkung");
        kotas.add("Singaraja");
        kotas.add("Karangasem");
        ArrayAdapter<String> kota_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kotas);
        kota.setAdapter(kota_adapter);


    }

    public void nextStep(View view){
        Intent step2Intent = new Intent(this,step_2_industri.class);
        step2Intent.putExtra("nama",mNama.getText().toString());
        step2Intent.putExtra("alamat",mAlamat.getText().toString());
        step2Intent.putExtra("telp",mTelp.getText().toString());
        step2Intent.putExtra("kota",mKota.getSelectedItem().toString());

        //before
        step2Intent.putExtra("email",email);
        step2Intent.putExtra("pass",pass);
        startActivity(step2Intent);
    }

    private void layoutItems(){
        mNama = (EditText)findViewById(R.id.industri_step_1_nama);
        mAlamat = (EditText)findViewById(R.id.industri_step_1_alamat);
        mTelp = (EditText)findViewById(R.id.industri_step_1_telp);
        mKota = (Spinner)findViewById(R.id.industri_step_1_kota);
        mSubmit = (Button)findViewById(R.id.industri_step_1_submit);
    }//
}