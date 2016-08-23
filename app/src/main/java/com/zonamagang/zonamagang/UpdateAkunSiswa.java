package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UpdateAkunSiswa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun_siswa);
        Backendless.initApp(this, Constants.APP_ID, Constants.APP_SECRET, Constants.APP_VERSION);

        String whereClause = "id_user = 3";
        final BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);

        Backendless.Persistence.of(tb_siswa.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_siswa>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_siswa> response) {
                if (response.getData().size() == 1){

                    tb_siswa responses = response.getData().get(0);

//                    String imageUri = responses.getFoto();
//                    ImageView imagePP = (ImageView)findViewById(R.id.profilePict);
//                    Picasso.with(context).load(imageUri).into(imagePP);

                    String Idsekolah = responses.getId_sekolah().toString();
                    String where = "id_sekolah = "+Idsekolah;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(where);

                    Backendless.Persistence.of(tb_sekolah.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_sekolah>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_sekolah> response) {

                            List<tb_sekolah> firstPage = response.getCurrentPage();

                            Iterator<tb_sekolah>iterator = firstPage.iterator();

                            while (iterator.hasNext()){
                                tb_sekolah dataSekolah = iterator.next();
                                String namaSekolah = dataSekolah.getNama();
                                EditText nmSekolah = (EditText)findViewById(R.id.sekolahSiswa);
                                nmSekolah.setText(namaSekolah);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

                    String jurusanSiswa = responses.getId_bidang().toString();

                    String where3 = "id_bidang ="+jurusanSiswa;
                    BackendlessDataQuery dataQuery2 = new BackendlessDataQuery();
                    dataQuery2.setWhereClause(where3);

                    Backendless.Persistence.of(tb_bidang.class).find(dataQuery2, new AsyncCallback<BackendlessCollection<tb_bidang>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_bidang> response) {
                            List <tb_bidang> firstPage = response.getCurrentPage();
                            Iterator <tb_bidang> iterator2 = firstPage.iterator();

                            while (iterator2.hasNext()){
                                tb_bidang dataJurusan = iterator2.next();

                                String jurusanSiswa = dataJurusan.getNama();
                                EditText jrsSiswa = (EditText)findViewById(R.id.jurusanSiswa);
                                jrsSiswa.setText(jurusanSiswa);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

                    String alamatSiswa = responses.getAlamat();
                    EditText amtSiswa = (EditText)findViewById(R.id.alamatSiswa);
                    amtSiswa.setText(alamatSiswa);

                    String noTelpSiswa = responses.get_no_telp();
                    EditText ntSiswa = (EditText)findViewById(R.id.noTelpSiswa);
                    ntSiswa.setText(noTelpSiswa);

                    String tmptSiswa = responses.getTempat_lahir();
                    String tglSiswa = responses.getTgl_lahir();
                    EditText ttlSiswa = (EditText)findViewById(R.id.ttlSiswa);
                    ttlSiswa.setText(tmptSiswa +", "+tglSiswa);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.save_icon:
                String KONTOL = "KONTOL LOE";
                HashMap akunSiswa = new HashMap();
                akunSiswa.put("nama",KONTOL);
                Backendless.Persistence.save(akunSiswa, new AsyncCallback<Map>() {
                    public void handleResponse( Map savedAkunSiswa )
                    {
                        // New contact object has been saved, now it can be updated
                        savedAkunSiswa.put( "nama", "BACOD NGENTODDD" );

                        Backendless.Persistence.save( savedAkunSiswa, new AsyncCallback<Map>() {
                            @Override
                            public void handleResponse( Map response )
                            {
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                            }
                        } );
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                    }
                });
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
