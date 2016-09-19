package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.Iterator;
import java.util.List;

public class hasil_pencarian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pencarian);
        Backendless.initApp(this, Constants.APP_ID,Constants.APP_SECRET,Constants.APP_VERSION);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String hasil = getIntent().getStringExtra("hasil");
        TextView ambil = (TextView)findViewById(R.id.hasil);
        ambil.setText(hasil);

        String nama="asd";

        BackendlessUser userNow = Backendless.UserService.CurrentUser();
        String id_user_now = userNow.getProperty("id_user").toString();

        String nama_industri = hasil;
//        "nama = 'Aditya Herlambang'"
//        String whereClause = "nama = '"+ nama_industri+"'";
        String whereClause = "nama LIKE '%nama_industri%'";
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);

        Backendless.Persistence.of(tb_industri.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_industri>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_industri> response) {
                List<tb_industri> firstPage = response.getCurrentPage();

                Iterator<tb_industri>iterator = firstPage.iterator();

                while (iterator.hasNext()){
                    tb_industri dataIndustri = iterator.next();
                    String namaSekolah = dataIndustri.getNama();
                    TextView nmSekolah = (TextView)findViewById(R.id.hasil2);
                    nmSekolah.setText(namaSekolah);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                TextView asd = (TextView)findViewById(R.id.hasil2);
                asd.setText("error "+fault);
            }
        });
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
