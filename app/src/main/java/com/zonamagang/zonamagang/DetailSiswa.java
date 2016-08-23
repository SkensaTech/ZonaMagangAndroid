package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.backendless.Backendless;

public class DetailSiswa extends AppCompatActivity {

    String nama,sekolah,email,alamat,bidang,ttl,notelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Backendless.initApp("F937B588-2B7D-C2BC-FF4C-A5FF37CC1400","80EB04E2-04AD-5BEF-FF0E-0D1728162B00","v1");
        setContentView(R.layout.activity_detail_siswa);

        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");

        TextView alamatSiswa = (TextView)findViewById(R.id.alamatSiswa);
        alamatSiswa.setText(alamat);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
