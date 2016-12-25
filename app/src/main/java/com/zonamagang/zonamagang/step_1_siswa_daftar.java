package com.zonamagang.zonamagang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.View;
import android.widget.*;
import android.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class step_1_siswa_daftar extends AppCompatActivity {

    //deklarasi
    Button y;
    int array_bulan;
    String sbulan,stahun,stanggal,stgllahir,pass,email;
    EditText nama ;
    EditText nisn ;
    EditText alamat;
    EditText tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1_siswa_daftar);

        //coding untuk toolbar
        android.support.v7.widget.Toolbar x = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nama = (EditText) findViewById(R.id.nama);
        nisn = (EditText) findViewById(R.id.nisn);
        alamat = (EditText) findViewById(R.id.alamat);
        tl = (EditText) findViewById(R.id.tempat_lahir);
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");

        //coding untuk spinner
        Spinner tahun = (Spinner) findViewById(R.id.tahun);
        List<String> tahuns = new ArrayList<>();
        tahuns.add("Tahun");
        for (int i = 1990; i <= 2016; i++)
            tahuns.add(""+i);
        ArrayAdapter<String> tahun_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tahuns);
        tahun.setAdapter(tahun_adapter);
        tahun.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner tahunspin = (Spinner) findViewById(R.id.tahun);
                int val = tahunspin.getSelectedItemPosition();
                stahun = parent.getItemAtPosition(val).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner bulan = (Spinner) findViewById(R.id.bulan);
        List<String> bulans = new ArrayList<>();
        bulans.add("Bulan");
        bulans.add("Jan");
        bulans.add("Feb");
        bulans.add("Mar");
        bulans.add("Apr");
        bulans.add("May");
        bulans.add("Jun");
        bulans.add("Jul");
        bulans.add("Aug");
        bulans.add("Sep");
        bulans.add("Oct");
        bulans.add("Nov");
        bulans.add("Des");
        ArrayAdapter<String> bulan_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bulans);
        bulan.setAdapter(bulan_adapter);
        bulan.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int arg2, long arg3)
            {
               Spinner bulanspin = (Spinner) findViewById(R.id.bulan);
                int position = bulanspin.getSelectedItemPosition();
                sbulan = parent.getItemAtPosition(position).toString();

            }
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }
        });

        Spinner tanggal = (Spinner) findViewById(R.id.tanggal);
        List<String> tanggals = new ArrayList<String>();
        int batas;
        tanggals.add("Tanggal");
        for (int i = 1; i <= 31; i++)
            tanggals.add(""+i);
        ArrayAdapter<String> tanggal_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tanggals);
        tanggal.setAdapter(tanggal_adapter);
        tanggal.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int arg2, long arg3)
            {
                Spinner tglspin = (Spinner) findViewById(R.id.tanggal);
                int position = tglspin.getSelectedItemPosition();
                stanggal = parent.getItemAtPosition(position).toString();

            }
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }
        });

        //coding untuk jika button click
        y =(Button) findViewById(R.id.BtnSiswa_step_1_submit);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = ProgressDialog.show(step_1_siswa_daftar.this, "",
                        "Mohon tunggu sebentar", true);
                dialog.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        String thenisn = nisn.getText().toString();

                    }
                }, 3000);

            }
        });

    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(step_1_siswa_daftar.this,register1.class);
        startActivity(intent);
    }
}
