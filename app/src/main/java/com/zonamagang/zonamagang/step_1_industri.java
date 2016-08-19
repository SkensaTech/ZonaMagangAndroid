package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class step_1_industri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1_industri);

        Spinner kota = (Spinner) findViewById(R.id.kota);
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
}