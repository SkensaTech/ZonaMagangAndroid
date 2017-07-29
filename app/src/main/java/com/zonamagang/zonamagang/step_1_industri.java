package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class step_1_industri extends AppCompatActivity {

    EditText mNama;
    EditText mAlamat;
    EditText mTelp;
    Spinner mKota;
    Button mSubmit;
    private EditText input;
    private Button check;
    //Sebelumnya :
    String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1_industri);
        this.layoutItems();


        //coding toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        input = (EditText) findViewById(R.id.industri_step_1_nama);
        check = (Button) findViewById(R.id.industri_step_1_submit);


//        check.setOnClickListener(new View.OnClickListener() {
//                                     @Override
//                                     public void onClick(View arg0) {
//
//
//                                     }
//                                 });

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
        ArrayAdapter<String> kota_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kotas);
        kota.setAdapter(kota_adapter);


    }

    public void nextStep(View view){
        String kosong = null;
        if(mNama.getText().toString().equals("") || mAlamat.getText().toString().equals("")|| mTelp.getText().toString().equals("") || mKota.getSelectedItem().toString().equals("Kota/Kabupatens")){
            Toast.makeText(this,"Ada data yang kosong !",Toast.LENGTH_SHORT).show();
        } else {


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
        }






    private void layoutItems(){
        mNama = (EditText)findViewById(R.id.industri_step_1_nama);
        mAlamat = (EditText)findViewById(R.id.industri_step_1_alamat);
        mTelp = (EditText)findViewById(R.id.industri_step_1_telp);
        mKota = (Spinner)findViewById(R.id.industri_step_1_kota);


        mSubmit = (Button)findViewById(R.id.industri_step_1_submit);
    }//
}