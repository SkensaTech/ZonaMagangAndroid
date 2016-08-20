package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class step_2_siswa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_2_siswa);

        //coding untuk toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //coding untuk jika button click
        Button y;
        y =(Button) findViewById(R.id.BtnSiswa_step_2_submit);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(getApplicationContext(),step_3_siswa.class);
                startActivity(z);
            }
        });

     //coding untuk data spinner
    //provinsi
        Spinner provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("--Pilih Provinsi--");
        provinsis.add("Bali");
        provinsis.add("Jawa");
        provinsis.add("Kalimantan");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);

    //kota
        Spinner kota = (Spinner) findViewById(R.id.spinnerKota);
        List<String> kotas = new ArrayList<>();
        kotas.add("--Pilih Kabupaten/Kota--");
        kotas.add("Denpasar");
        kotas.add("Singaraja");
        kotas.add("Gianyar");
        ArrayAdapter<String> kotas_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kotas);
        kota.setAdapter(kotas_adapter);

    //sekolah
        Spinner sekolah = (Spinner) findViewById(R.id.spinnerSekolah);
        List<String> sekolahs = new ArrayList<>();
        sekolahs.add("--Pilih Sekolah--");
        sekolahs.add("SMKN 1 Denpasar");
        sekolahs.add("SMAN 1 Denpasar");
        sekolahs.add("SLTB");

        ArrayAdapter<String> sekolah_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sekolahs);
        sekolah.setAdapter(sekolah_adapter);

    //bidang
        Spinner bidang = (Spinner) findViewById(R.id.spinnerBidang);
        List<String> bidangs = new ArrayList<>();
        bidangs.add("--Pilih Bidang/Jurusan");
        bidangs.add("Rekayasa Perangkat Lunak");
        bidangs.add("Teknik Komputer Jaringan");
        bidangs.add("Akomodasi Perhotelan");

        ArrayAdapter<String> bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bidangs);
        bidang.setAdapter(bidang_adapter);
    }
}
