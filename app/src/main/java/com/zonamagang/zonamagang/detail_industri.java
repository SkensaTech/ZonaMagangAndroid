package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detail_industri extends AppCompatActivity {
    String nama,alamat,email,no_telp,profil,jobdesc,kualifikasi;
    String kuota;
    String logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_industri);

        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        alamat = intent.getStringExtra("alamat");
        email = intent.getStringExtra("email");
        no_telp = intent.getStringExtra("no_telp");
        profil = intent.getStringExtra("profil");
        jobdesc = intent.getStringExtra("jobdesc");
        kualifikasi = intent.getStringExtra("kualifikasi");
        kuota = intent.getStringExtra("kuota");
        logo = intent.getStringExtra("logo");


        this.isiLayoutItem();
    }

    public void isiLayoutItem(){
        TextView layoutNama = (TextView)findViewById(R.id.detail_industri_nama);
        TextView layoutAlamat = (TextView)findViewById(R.id.detail_industri_alamat);
        TextView layoutEmail = (TextView)findViewById(R.id.detail_industri_email);
        TextView layoutNoTelp = (TextView)findViewById(R.id.detail_industri_telp);
        TextView layoutProfil = (TextView)findViewById(R.id.detail_industri_profil);
        TextView layoutJobdesc = (TextView)findViewById(R.id.detail_industri_jobdesc);
        TextView layoutKualifikasi = (TextView)findViewById(R.id.detail_industri_kualifikasi);
        ImageView layoutLogo = (ImageView)findViewById(R.id.gambarSiswa2);
        TextView layoutKuota = (TextView)findViewById(R.id.detail_industri_kuota);

        layoutNama.setText(nama);
        layoutAlamat.setText(alamat);
        layoutEmail.setText(email);
        layoutNoTelp.setText(no_telp);
        layoutProfil.setText(profil);
        layoutJobdesc.setText(jobdesc);
        layoutKualifikasi.setText(kualifikasi);
        layoutKuota.setText("Maksimal "+kuota+" siswa.");

        Picasso.with(getApplicationContext()).load(logo).into(layoutLogo);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
