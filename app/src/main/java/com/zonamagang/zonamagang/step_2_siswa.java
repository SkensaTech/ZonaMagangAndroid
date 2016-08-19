package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class step_2_siswa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_2_siswa);

        Spinner provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("Kota/Kabupaten");
        provinsis.add("Denpasar");
        provinsis.add("Badung");
        provinsis.add("Gianyar");
        provinsis.add("Tabanan");
        provinsis.add("Bangli");
        provinsis.add("Klungkung");
        provinsis.add("Singaraja");
        provinsis.add("Karangasem");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);

    }
}
