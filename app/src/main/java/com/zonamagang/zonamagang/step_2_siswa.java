package com.zonamagang.zonamagang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class step_2_siswa extends AppCompatActivity {
    //Deklarasi
    String nisn,nama,alamat,jk,ttl,tl,tgl,sprovinsi,skota,sbidang,sskolah,email,pass;
    int pos,id_bidang,id_sekolah;
    int xy = 0;
    Spinner sekolah,bidang;
    ProgressDialog dialog;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_2_siswa);

        nisn = getIntent().getStringExtra("nisn");
        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        jk = getIntent().getStringExtra("jk");
        tl = getIntent().getStringExtra("tl");
        tgl = getIntent().getStringExtra("tgllahir");
        pass = getIntent().getStringExtra("pass");

        email = getIntent().getStringExtra("email");
        ttl = tl+","+tgl;
       // String All = nisn+" "+nama+" "+alamat+" "+tl+" "+tgl+" "+jk+" "+email+" "+pass;
       // Toast.makeText(getApplicationContext(),All,Toast.LENGTH_SHORT).show();
        //coding untuk toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sekolah = (Spinner) findViewById(R.id.spinnerSekolah);
        bidang = (Spinner) findViewById(R.id.spinnerBidang);

        //coding untuk jika button click
        dialog = ProgressDialog.show(step_2_siswa.this, "",
                "Mohon tunggu sebentar", true);
        dialog.show();
                /* Create an Intent that will start the Menu-Activity.. */
                step_2_siswa.this.isiSpinnerParent();

        this.eventListeners();
        this.setSpinnerBidangListener();
    Spinner provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("Bali");
        provinsis.add("Jawa");
        provinsis.add("Kalimantan");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);
        provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner provspin = (Spinner) findViewById(R.id.spinnerProvinsi);
                pos = provspin.getSelectedItemPosition();
                sprovinsi = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner kota = (Spinner) findViewById(R.id.spinnerKota);
        List<String> kotas = new ArrayList<>();
        kotas.add("Denpasar");
        kotas.add("Singaraja");
        kotas.add("Gianyar");
        ArrayAdapter<String> kotas_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kotas);
        kota.setAdapter(kotas_adapter);
        kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner kotaspin = (Spinner) findViewById(R.id.spinnerKota);
                pos = kotaspin.getSelectedItemPosition();
                skota = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button next = (Button) findViewById(R.id.BtnSiswa_step_2_submit);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextpage = new Intent(getApplicationContext(),step_3_siswa.class);
              String scl,bdg;

                nextpage.putExtra("nisn",nisn);
                nextpage.putExtra("nama",nama);
                nextpage.putExtra("jeniskelamin",jk);
                nextpage.putExtra("alamat",alamat);
                nextpage.putExtra("tempat",tl);
                nextpage.putExtra("tanggal",tgl);
                nextpage.putExtra("provinsi",sprovinsi);
                nextpage.putExtra("kota",skota);
                nextpage.putExtra("id_sekolah",xy);
                nextpage.putExtra("id_bidang",id_bidang);
                nextpage.putExtra("email",email);
                nextpage.putExtra("pass",pass);
               // Toast.makeText(getApplicationContext(),scl+" "+bdg,Toast.LENGTH_SHORT).show();
              startActivity(nextpage);
                finish();

            }
        });
    }

    //-----------------------------------------------------------ADD SPINNER VALUE----------------------------------------------------------------
    public void eventListeners(){
        //ParentBidang ItemSelected
        sekolah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            if(id_sekolah == 0) {
                bidang.setSelection(0);

                bidang.setEnabled(false);
            }else{

                /* Create an Intent that will start the Menu-Activity.. */
                        step_2_siswa.this.loopingBidangSekolah();
                        dialog.show();
           }
                bidang.setSelection(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void isiSpinnerParent(){

    }
  public void loopingBidangSekolah() {

    }

    private void setSpinnerBidangListener(){

    }

    public void onBackPressed(){
        Intent intent = new Intent(step_2_siswa.this,register1.class);
        startActivity(intent);
        this.finish();
    }

    public boolean loading() {
        final ProgressDialog dialog = ProgressDialog.show(step_2_siswa.this, "",
                "Mohon tunggu sebentar", true);
        dialog.show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.. */
                dialog.hide();
            }
        }, 3000);
        return true;
    }

}
