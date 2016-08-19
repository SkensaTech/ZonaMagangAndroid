package com.zonamagang.zonamagang;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class step_1_siswa_daftar extends AppCompatActivity {

    int array_bulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1_siswa_daftar);

        Spinner tahun = (Spinner) findViewById(R.id.tahun);
        List<String> tahuns = new ArrayList<>();
        tahuns.add("Tahun");
        for (int i = 1999; i <= 2016; i++)
            tahuns.add(""+i);
        ArrayAdapter<String> tahun_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tahuns);
        tahun.setAdapter(tahun_adapter);

        Spinner bulan = (Spinner) findViewById(R.id.bulan);
        List<String> bulans = new ArrayList<>();
        bulans.add("Bulan");
        bulans.add("Jan");
        bulans.add("Feb");
        ArrayAdapter<String> bulan_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bulans);
        bulan.setAdapter(bulan_adapter);
        bulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                array_bulan = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                array_bulan = 1;
            }
        });

        Spinner tanggal = (Spinner) findViewById(R.id.tanggal);
        List<String> tanggals = new ArrayList<String>();
        int batas;
//        if ((array_bulan % 2) == 1)
//            batas = 31;
//        else
//            batas = 30;
//        for (int i = 1; i <= batas; i++)
//            tanggals.add(""+i);
        tanggals.add("Tanggal");
        tanggals.add(""+array_bulan);
        ArrayAdapter<String> tanggal_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tanggals);
        tanggal.setAdapter(tanggal_adapter);
    }
}
