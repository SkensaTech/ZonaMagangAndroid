package com.zonamagang.zonamagang;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.zonamagang.zonamagang.Model.last_id;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_bidang_industri;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_parent_bidang;

import com.zonamagang.zonamagang.Model.tb_bidang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class search_filter extends AppCompatActivity {
    public static String prov,kab,bidang_industri;
    Spinner provinsi,kota,bidang;
    int[] xx;
    public static int id_bidang;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        dialog = ProgressDialog.show(search_filter.this, "",
                "Mohon tunggu sebentar", true);
        //coding toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //coding spinner

        //provinsi
        provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        List<String> provinsis = new ArrayList<>();
        provinsis.add("--Pilih Provinsi--");
        provinsis.add("Bali");
        ArrayAdapter<String> provinsi_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinsis);
        provinsi.setAdapter(provinsi_adapter);
        provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prov = provinsi.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //kota
       kota = (Spinner) findViewById(R.id.spinnerKota);
        List<String> kotas = new ArrayList<>();
        kotas.add("--Pilih Kota--");
        kotas.add("Denpasar");

        ArrayAdapter<String> kotas_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kotas);
        kota.setAdapter(kotas_adapter);
        kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kab = kota.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //bidang
        bidang = (Spinner) findViewById(R.id.spinnerBidang);
        final List<String> bidangs = new ArrayList<>();
        bidangs.add("--Pilih Bidang--");
        QueryOptions tes = new QueryOptions();
        tes.setPageSize(50);
        BackendlessDataQuery xxx = new BackendlessDataQuery();
        xxx.setQueryOptions(tes);
        Backendless.Persistence.of(tb_bidang.class).find(xxx,new AsyncCallback<BackendlessCollection<tb_bidang>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_bidang> response) {
                List<tb_bidang> curpage = response.getCurrentPage();
                int size = curpage.size();
                for (int i = 0;i < size;i++){
                    String bidang_item = curpage.get(i).getNama();
                    int x = curpage.get(i).getId_bidang();
                    bidangs.add(bidang_item);
                    dialog.hide();


                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        final ArrayAdapter<String> bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bidangs);
        bidang.setAdapter(bidang_adapter);
        bidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bidang_industri = bidang.getSelectedItem().toString();
                int x = bidang.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn = (Button) findViewById(R.id.cari);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String where = "id_bidang = "+id_bidang;
                BackendlessDataQuery queryy = new BackendlessDataQuery();
                queryy.setWhereClause(where);
                Backendless.Persistence.of(tb_bidang_industri.class).find(queryy, new AsyncCallback<BackendlessCollection<tb_bidang_industri>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<tb_bidang_industri> bidang_industri) {
                        List<tb_bidang_industri> curpage = bidang_industri.getCurrentPage();
                        int size = curpage.size();
                        for(int i = 0;i < size;i++){
                            int id_industri = curpage.get(i).getId_industri();
                            Toast.makeText(search_filter.this, ""+id_industri, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        Toast.makeText(search_filter.this, "Bidang Industri "+fault.getCode(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void doSearch(){

    }
}
