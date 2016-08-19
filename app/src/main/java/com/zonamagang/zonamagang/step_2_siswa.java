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
    //provinsi
        Spinner provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("Provinsi");
        provinsis.add("Bali");
        provinsis.add("Jawa");
        provinsis.add("Kalimantan");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);

    //kota
        Spinner kota = (Spinner) findViewById(R.id.spinnerKota);
        List<String> kotas = new ArrayList<>();
        kotas.add("Kabupaten/Kota");
        kotas.add("Denpasar");
        kotas.add("Singaraja");
        kotas.add("Gianyar");
        ArrayAdapter<String> kotas_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kotas);
        kota.setAdapter(kotas_adapter);

    //sekolah
        Spinner sekolah = (Spinner) findViewById(R.id.spinnerSekolah);
        List<String> sekolahs = new ArrayList<>();
        sekolahs.add("Sekolah");
        sekolahs.add("SMKN 1 Denpasar");
        sekolahs.add("SMAN 1 Denpasar");
        sekolahs.add("SLTB");

        ArrayAdapter<String> sekolah_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sekolahs);
        sekolah.setAdapter(sekolah_adapter);

    //bidang
        Spinner bidang = (Spinner) findViewById(R.id.spinnerBidang);
        List<String> bidangs = new ArrayList<>();
        bidangs.add("Sekolah");
        bidangs.add("SMKN 1 Denpasar");
        bidangs.add("SMAN 1 Denpasar");
        bidangs.add("SLTB");

        ArrayAdapter<String> bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bidangs);
        bidang.setAdapter(bidang_adapter);
    }
}
