package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zonamagang.zonamagang.Model.tb_industri;

import java.util.ArrayList;
import java.util.List;

public class home_admin extends AppCompatActivity {

    tb_industri tbindustri;
    List<tb_industri> listIndustri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listview = (ListView) findViewById(R.id.listIndustriAdmin);
        listIndustri = new ArrayList<tb_industri>();
        Backendless.Persistence.of(tb_industri.class).find(new AsyncCallback<BackendlessCollection<tb_industri>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_industri> industri) {
                List<tb_industri> curpage = industri.getCurrentPage();
                int size = curpage.size();
                for (int i = 0; i < size;i++){
                    String nama = curpage.get(i).getNama();
                    String alamat = curpage.get(i).getAlamat();

                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
