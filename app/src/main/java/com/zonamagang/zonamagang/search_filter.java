package com.zonamagang.zonamagang;

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

import com.zonamagang.zonamagang.Model.tb_bidang;

import java.util.ArrayList;
import java.util.List;

public class search_filter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        //coding toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //coding spinner

        //provinsi
        Spinner provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("--Pilih Provinsi--");
        provinsis.add("Bali");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);


        //kota
        Spinner kota = (Spinner) findViewById(R.id.spinnerKota);
        List<String> kotas = new ArrayList<>();
        kotas.add("--Pilih Kota--");
        kotas.add("Denpasar");
        kotas.add("Singaraja");
        kotas.add("Gianyar");

        ArrayAdapter<String> kotas_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kotas);
        kota.setAdapter(kotas_adapter);


        //bidang
        Spinner bidang = (Spinner) findViewById(R.id.spinnerBidang);
        List<String> bidangs = new ArrayList<>();
        bidangs.add("--Pilih Bidang--");
        bidangs.add("Rekayasa Perangkat Lunak");
        bidangs.add("Teknik Komputer Jaringan");
        bidangs.add("Akomodasi Perhotelan");

        ArrayAdapter<String> bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bidangs);
        bidang.setAdapter(bidang_adapter);


    }
}
