package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Model.last_id;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_bidang_industri;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_parent_bidang;

import com.zonamagang.zonamagang.Model.tb_bidang;

import java.util.ArrayList;
import java.util.List;

public class search_filter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        //coding toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //coding spinner

        //provinsi
        Spinner provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("--Pilih Provinsi--");
        provinsis.add("Bali");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);
        provinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
        });


        //kota
        Spinner kota = (Spinner) findViewById(R.id.spinnerKota);
        List<String> kotas = new ArrayList<>();
        kotas.add("--Pilih Kota--");
        kotas.add("Denpasar");

        ArrayAdapter<String> kotas_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kotas);
        kota.setAdapter(kotas_adapter);


        //bidang
        Spinner bidang = (Spinner) findViewById(R.id.spinnerBidang);
        final List<String> bidangs = new ArrayList<>();
        bidangs.add("--Pilih Bidang--");
        Backendless.Persistence.of(tb_bidang.class).find(new AsyncCallback<BackendlessCollection<tb_bidang>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_bidang> response) {
                List<tb_bidang> curpage = response.getCurrentPage();
                int size = curpage.size();
                for (int i = 0;i < size;i++){
                    String bidang_item = curpage.get(i).getNama();
                    bidangs.add(bidang_item);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        ArrayAdapter<String> bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bidangs);
        bidang.setAdapter(bidang_adapter);


    }
}
