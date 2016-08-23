package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_industri;

import java.util.Iterator;
import java.util.List;

public class detail_industri extends AppCompatActivity {
    String nama,alamat,email,no_telp,profil,jobdesc,kualifikasi;
    int id_industri;
    int kuota;
    String logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_industri);

        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id_industri = Integer.parseInt(getIntent().getStringExtra("id_industri"));

        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause("id_industri = "+id_industri);
        Backendless.Persistence.of(tb_industri.class).find( dataQuery, new AsyncCallback<BackendlessCollection<tb_industri>>(){
            @Override
            public void handleResponse( BackendlessCollection<tb_industri> hasil )
            {
                List<tb_industri> firstPage = hasil.getCurrentPage();

                Iterator<tb_industri> iterator = firstPage.iterator();

                while( iterator.hasNext() )
                {
                    tb_industri industriInfo = iterator.next();
                    nama = industriInfo.getNama();
                    alamat = industriInfo.getAlamat();
                    email = industriInfo.getEmail();
                    no_telp = industriInfo.getNo_telp();
                    jobdesc = industriInfo.getJobdesc();
                    kualifikasi = industriInfo.getKualifikasi();
                    kuota = industriInfo.getKuota();
                    logo = industriInfo.getLogo();
                    profil = industriInfo.getProfil();
                    detail_industri.this.isiLayoutItem();
                }
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(detail_industri.this,"Error ! "+fault.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

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
