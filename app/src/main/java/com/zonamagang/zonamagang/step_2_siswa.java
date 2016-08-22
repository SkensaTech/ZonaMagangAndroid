package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_bidang_sekolah;
import com.zonamagang.zonamagang.Model.tb_parent_bidang;
import com.zonamagang.zonamagang.Model.tb_sekolah;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class step_2_siswa extends AppCompatActivity {
    //Deklarasi
    String nisn,nama,alamat,jk,ttl,tl,tgl,sprovinsi,skota,sbidang,sskolah,email,pass;
    int pos,id_bidang,id_sekolah;
    Spinner sekolah,bidang;
    List<tb_sekolah> listsekolah;
    List<tb_bidang_sekolah> listbidang;
    List<tb_bidang> bidangss;
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
        ttl = tl+","+tgl;
        //coding untuk toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");
        //coding untuk jika button click
        Button y;
        y =(Button) findViewById(R.id.BtnSiswa_step_2_submit);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(getApplicationContext(),step_3_siswa.class);
                startActivity(z);
            }
        });
        this.isiSpinnerParent();
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
        sekolah = (Spinner) findViewById(R.id.spinnerSekolah);
        bidang = (Spinner) findViewById(R.id.spinnerBidang);
        Button next = (Button) findViewById(R.id.BtnSiswa_step_2_submit);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(),step_3_siswa.class);
                nextpage.putExtra("nisn",nisn);
                nextpage.putExtra("nama",nama);
                nextpage.putExtra("jeniskelamin",jk);
                nextpage.putExtra("alamat",alamat);
                nextpage.putExtra("ttl",ttl);
                nextpage.putExtra("provinsi",sprovinsi);
                nextpage.putExtra("kota",skota);
                nextpage.putExtra("sekolah",sskolah);
                nextpage.putExtra("bidang",sbidang);
                nextpage.putExtra("email",email);
                nextpage.putExtra("pass",pass);
                startActivity(nextpage);
            }
        });
    }

    //-----------------------------------------------------------ADD SPINNER VALUE----------------------------------------------------------------
    public void eventListeners(){
        //ParentBidang ItemSelected
        sekolah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tb_sekolah sekolahSelector = (tb_sekolah) parent.getSelectedItem();
                id_sekolah = sekolahSelector.getId_sekolah();
                if(id_sekolah == 0){
                    bidang.setSelection(0);
                    bidang.setEnabled(false);
                }
                else{
                    step_2_siswa.this.loopingBidangSekolah();
                }
                bidang.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void isiSpinnerParent(){
        listsekolah = new ArrayList<>();
        listsekolah.add(new tb_sekolah(0,"Pilih bidang industri anda."));
        Backendless.Data.mapTableToClass( "tb_sekolah", tb_sekolah.class );
        Backendless.Persistence.of(tb_sekolah.class).find( new AsyncCallback<BackendlessCollection<tb_sekolah>>(){
            @Override
            public void handleResponse( BackendlessCollection<tb_sekolah> hasil )
            {
                List<tb_sekolah> firstPage = hasil.getCurrentPage();

                Iterator<tb_sekolah> iterator = firstPage.iterator();

                while( iterator.hasNext() )
                {
                    tb_sekolah sekolahList = iterator.next();
                    step_2_siswa.this.listsekolah.add(new tb_sekolah(sekolahList.getId_sekolah(),sekolahList.getNama()));
                }
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_2_siswa.this,"Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<tb_sekolah> sekolah_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listsekolah);
        sekolah.setAdapter(sekolah_adapter);
    }
    public void loopingBidangSekolah() {
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause("id_sekolah = " + id_sekolah);
        Backendless.Data.mapTableToClass("tb_bidang_sekolah", tb_bidang_sekolah.class);
        Backendless.Persistence.of(tb_bidang_sekolah.class).find(query, new AsyncCallback<BackendlessCollection<tb_bidang_sekolah>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_bidang_sekolah> bidsek) {
                List<tb_bidang_sekolah> firstPage = bidsek.getCurrentPage();
                Iterator<tb_bidang_sekolah> iterator = firstPage.iterator();

                while (iterator.hasNext()) {
                    tb_bidang_sekolah bidangid = iterator.next();
                    id_bidang = bidangid.getId_bidang();
                    bidangss = new ArrayList<>();
                    bidangss.add(new tb_bidang(0,0,"Pilih Bidang Sekolah."));
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause("id_bidang = "+id_bidang);
                    Backendless.Data.mapTableToClass( "tb_bidang", tb_bidang.class );
                    Backendless.Persistence.of(tb_bidang.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_bidang>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_bidang> hasil) {
                            List<tb_bidang> scndPage = hasil.getCurrentPage();
                            Iterator<tb_bidang> iterator = scndPage.iterator();
                            while( iterator.hasNext() )
                            {
                                tb_bidang bidangList = iterator.next();
                                step_2_siswa.this.bidangss.add(new tb_bidang(bidangList.getId_bidang(),bidangList.getId_parent_bidang(),bidangList.getNama()));
                            }
                            bidang.setEnabled(true);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

 /*  public void isiSpinnerSub(){
        bidang.setEnabled(false);
        List<tb_bidang> bidangss = new ArrayList<>();
        bidangss.add(new tb_bidang(0,0,"Pilih Bidang Sekolah."));
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause("id_ = "+id_parent_bidang);
        Backendless.Data.mapTableToClass( "tb_bidang", tb_bidang.class );
        Backendless.Persistence.of(tb_bidang.class).find( dataQuery, new AsyncCallback<BackendlessCollection<tb_bidang>>(){
            @Override
            public void handleResponse( BackendlessCollection<tb_bidang> hasil )
            {
                List<tb_bidang> firstPage = hasil.getCurrentPage();
                Iterator<tb_bidang> iterator = firstPage.iterator();

                while( iterator.hasNext() )
                {
                    tb_bidang bidangList = iterator.next();
                    step_2_industri.this.bidang.add(new tb_bidang(bidangList.getId_bidang(),bidangList.getId_parent_bidang(),bidangList.getNama()));
                }
                mBidang.setEnabled(true);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_2_industri.this,"Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<tb_bidang> parent_bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bidang);
        mBidang.setAdapter(parent_bidang_adapter);
    }
    */
    private void setSpinnerBidangListener(){
        bidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tb_bidang parentBidangSelector = (tb_bidang) parent.getSelectedItem();
                id_bidang = parentBidangSelector.getId_bidang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



}
