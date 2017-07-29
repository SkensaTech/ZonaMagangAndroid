package com.zonamagang.zonamagang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class search_filter extends AppCompatActivity{
    public static String prov,kab,bidang_industri;
    Spinner provinsi,kota,bidang;
    String email,nama,foto;
    int[] xx;
    public static int id_bidang;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        //coding toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = getIntent().getStringExtra("email");
        nama = getIntent().getStringExtra("nama");
        foto = getIntent().getStringExtra("foto");

        //coding spinner

        //provinsi
        provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("--Pilih Provinsi--");
        provinsis.add("Bali");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);
        provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prov = provinsi.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //kota
       kota = (Spinner) findViewById(R.id.spinnerKota);
        List<String> kotas = new ArrayList<>();
        kotas.add("--Pilih Kota--");
        kotas.add("Denpasar");
        kotas.add("Badung");
        kotas.add("Bangli");

        ArrayAdapter<String> kotas_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kotas);
        kota.setAdapter(kotas_adapter);
        kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kab = kota.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //bidang
        bidang = (Spinner) findViewById(R.id.spinnerBidang);
        final List<String> bidangs = new ArrayList<>();
        bidangs.add("--Pilih Bidang--");

        final ArrayAdapter<String> bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bidangs);
        bidang.setAdapter(bidang_adapter);
        bidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bidang_industri = bidang.getSelectedItem().toString();
                int x = bidang.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void doSearch(View view){
        Spinner spinnerBidang = (Spinner)findViewById(R.id.spinnerBidang);
        Spinner spinnerProvinsi = (Spinner)findViewById(R.id.spinnerProvinsi);
        Spinner spinnerKota = (Spinner)findViewById(R.id.spinnerKota);

        String bidang = spinnerBidang.getSelectedItem().toString();
        String provinsi = spinnerProvinsi.getSelectedItem().toString();
        String kota = spinnerKota.getSelectedItem().toString();

        Intent homeSiswa = new Intent(search_filter.this, home_siswa_1.class);
        homeSiswa.putExtra("bidang", bidang);
        homeSiswa.putExtra("provinsi", provinsi);
        homeSiswa.putExtra("kota", kota);
        homeSiswa.putExtra("email",email);
        homeSiswa.putExtra("nama",nama);
        homeSiswa.putExtra("foto",foto);
        startActivity(homeSiswa);
        finish();


    }
}
