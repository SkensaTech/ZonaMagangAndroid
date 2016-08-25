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
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
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
    ProgressDialog progressDialog;
    tb_bidang parentBidangSelector;
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
        final ProgressDialog dialog = ProgressDialog.show(step_2_siswa.this, "",
                "Mohon tunggu sebentar", true);
        dialog.show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.. */
                step_2_siswa.this.isiSpinnerParent();
                dialog.hide();
            }
        }, 3000);
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
                nextpage.putExtra("id_sekolah",id_sekolah);
                nextpage.putExtra("id_bidang",id_bidang);
                nextpage.putExtra("email",email);
                nextpage.putExtra("pass",pass);
               // Toast.makeText(getApplicationContext(),scl+" "+bdg,Toast.LENGTH_SHORT).show();
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
            if(id_sekolah == 0) {
                bidang.setSelection(0);

                bidang.setEnabled(false);
            }
                else{

                final ProgressDialog dialog = ProgressDialog.show(step_2_siswa.this, "",
                        "Mohon tunggu sebentar", true);
                dialog.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                /* Create an Intent that will start the Menu-Activity.. */
                        step_2_siswa.this.loopingBidangSekolah();
                        dialog.hide();
                    }
                }, 3000);
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
        listsekolah.add(new tb_sekolah(0,"Pilih Sekolah Anda"));
        BackendlessDataQuery query = new BackendlessDataQuery();
        QueryOptions test = new QueryOptions();
        test.setPageSize(100);
        query.setQueryOptions(test);
        Backendless.Data.mapTableToClass( "tb_sekolah", tb_sekolah.class );
        Backendless.Persistence.of(tb_sekolah.class).find(query, new AsyncCallback<BackendlessCollection<tb_sekolah>>(){
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
//                Intent reload = new Intent(getApplicationContext(),step_2_siswa.class);
//                startActivity(reload);
//                step_2_siswa.this.finish();
                    Toast.makeText(step_2_siswa.this,"Error ! "+fault.getCode()+" ID Sekolah "+id_sekolah,Toast.LENGTH_SHORT).show();
            }
        });
      ArrayAdapter<tb_sekolah> sekolah_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listsekolah);
      sekolah.setAdapter(sekolah_adapter);
    }
  public void loopingBidangSekolah() {
    //
      //  Toast.makeText(getApplicationContext(),"Id Sekolah = "+id_sekolah,Toast.LENGTH_SHORT).show();
      BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause("id_sekolah = " + id_sekolah);
         bidangss = new ArrayList<>();
        bidangss.add(new tb_bidang(0,0,"Pilih Bidang Sekolah."));
        Backendless.Data.mapTableToClass("tb_bidang_sekolah", tb_bidang_sekolah.class);
        Backendless.Persistence.of(tb_bidang_sekolah.class).find(query, new AsyncCallback<BackendlessCollection<tb_bidang_sekolah>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_bidang_sekolah> bidsek) {
                List<tb_bidang_sekolah> firstPage = bidsek.getCurrentPage();
                Iterator<tb_bidang_sekolah> iterator = firstPage.iterator();

                while (iterator.hasNext()) {
                 tb_bidang_sekolah bidangid = iterator.next();
                    id_bidang = bidangid.getId_bidang();
                  //  Toast.makeText(getApplicationContext(),"test"+id_bidang,Toast.LENGTH_SHORT).show();

                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause("id_bidang = "+id_bidang);
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
                                step_2_siswa.this.bidangss.add(new tb_bidang(bidangList.getId_bidang(),bidangList.getId_parent_bidang(),bidangList.getNama()));

                            }
                            bidang.setEnabled(true);
                        }
                        @Override
                        public void handleFault( BackendlessFault fault )
                        {
                            Toast.makeText(step_2_siswa.this,"Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
      ArrayAdapter<tb_bidang> sekolah_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bidangss);
      bidang.setAdapter(sekolah_adapter);
    }

    private void setSpinnerBidangListener(){
        bidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parentBidangSelector = (tb_bidang) parent.getSelectedItem();

                final ProgressDialog dialog = ProgressDialog.show(step_2_siswa.this, "",
                        "Mohon tunggu sebentar", true);
                dialog.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                /* Create an Intent that will start the Menu-Activity.. */
                        id_bidang = parentBidangSelector.getId_bidang();
                        dialog.hide();
                    }
                }, 3000);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
