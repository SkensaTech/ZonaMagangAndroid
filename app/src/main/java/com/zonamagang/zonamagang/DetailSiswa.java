package com.zonamagang.zonamagang;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.property.UserProperty;
import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailSiswa extends AppCompatActivity {

    TextView nama2;
    String sekolah2;
    String email2;
    TextView alamat2;
    String bidang2;
    TextView ttl2, tanggal_lahir2, tempat_lahir2;
    TextView notelp2;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Backendless.initApp(this, Constants.APP_ID,Constants.APP_SECRET,Constants.APP_VERSION);
        setContentView(R.layout.activity_detail_siswa);

        BackendlessUser userNow = Backendless.UserService.CurrentUser();
        String id_user_now = userNow.getProperty("id_user").toString();

        String whereClause = "id_user = " + id_user_now;
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);

        ProgressDialog dialog = new ProgressDialog(DetailSiswa.this);
        dialog.setMessage("Tunggu Sebentar..");
        dialog.show();

        Backendless.Persistence.of(tb_siswa.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_siswa>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_siswa> response) {

                if (response.getData().size() == 1){

                    tb_siswa responses = response.getData().get(0);

                    String imageUri = responses.getFoto();
                    ImageView imagePP = (ImageView)findViewById(R.id.profilePict);
                    Picasso.with(context).load(imageUri).into(imagePP);

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
                                TextView nmSekolah = (TextView)findViewById(R.id.sekolahSiswa);
                                nmSekolah.setText(namaSekolah);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

                    String IdBidang = responses.getId_bidang().toString();
                    String where2 = "id_bidang = "+IdBidang;
                    BackendlessDataQuery dataQuery2 = new BackendlessDataQuery();
                    dataQuery2.setWhereClause(where2);

                    Backendless.Persistence.of(tb_bidang.class).find(dataQuery2, new AsyncCallback<BackendlessCollection<tb_bidang>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_bidang> response) {
                            List<tb_bidang>firstPage =response.getCurrentPage();
                            Iterator<tb_bidang> iterator = firstPage.iterator();

                            while (iterator.hasNext()){
                                tb_bidang dataBidang = iterator.next();
                                String namaBidang = dataBidang.getNama();
                                TextView nmBidang = (TextView)findViewById(R.id.jurusanSiswa);
                                nmBidang.setText(namaBidang);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

                    String IdTelpon = responses.get_no_telp();
                    notelp2 = (TextView)findViewById(R.id.noTelpSiswa);
                    notelp2.setText(IdTelpon);

                    String nama = responses.getNama();
                    nama2 = (TextView)findViewById(R.id.namaSiswa);
                    nama2.setText(nama);

                    String alamat = responses.getAlamat();
                    alamat2 = (TextView)findViewById(R.id.alamatSiswa);
                    alamat2.setText(alamat);

                    String tglLahir = responses.getTgl_lahir();
                    tanggal_lahir2 = (TextView)findViewById(R.id.tanggal_lahir);
                    tanggal_lahir2.setText(tglLahir);

                    String tempatLahir = responses.getTempat_lahir();
                    tempat_lahir2 = (TextView)findViewById(R.id.tempat_lahir);
                    tempat_lahir2.setText(tempatLahir);

                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

        dialog.dismiss();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.edit_icon:
                Intent intent = new Intent(DetailSiswa.this, UpdateAkunSiswa.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
