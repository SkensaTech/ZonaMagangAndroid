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
        setContentView(R.layout.activity_detail_siswa);
        Backendless.initApp(Constants.APP_ID,Constants.APP_SECRET,Constants.APP_VERSION);

        nama = getIntent().getStringExtra("nama");
        sekolah = getIntent().getStringExtra("");
        email = getIntent().getStringExtra("");
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
