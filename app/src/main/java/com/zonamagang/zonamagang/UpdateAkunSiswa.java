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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UpdateAkunSiswa extends AppCompatActivity {

    Context context = this;
    ProgressDialog dialog;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun_siswa);
        Backendless.initApp(this, Constants.APP_ID, Constants.APP_SECRET, Constants.APP_VERSION);

        loading = (ProgressBar) findViewById(R.id.update_akun_siswa_progress_bar);

        loading.setVisibility(View.VISIBLE);

        BackendlessUser userNow = Backendless.UserService.CurrentUser();
        String id_user_now = userNow.getProperty("id_user").toString();

        String whereClause = "id_user = " + id_user_now;
        final BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);

        Backendless.Persistence.of(tb_siswa.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_siswa>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_siswa> response) {
                if (response.getData().size() == 1) {

                    tb_siswa responses = response.getData().get(0);

//                    String imageUri = responses.getFoto();
//                    ImageView imagePP = (ImageView)findViewById(R.id.profilePict);
//                    Picasso.with(context).load(imageUri).into(imagePP);

                    String NamaSiswa = responses.getNama();
                    TextView nmSiswa = (TextView) findViewById(R.id.namaSiswa);
                    nmSiswa.setText(NamaSiswa);

                    String Idsekolah = responses.getId_sekolah().toString();
                    String where = "id_sekolah = " + Idsekolah;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(where);

                    Backendless.Persistence.of(tb_sekolah.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_sekolah>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_sekolah> response) {

                            List<tb_sekolah> firstPage = response.getCurrentPage();

                            Iterator<tb_sekolah> iterator = firstPage.iterator();

                            while (iterator.hasNext()) {
                                tb_sekolah dataSekolah = iterator.next();
                                String namaSekolah = dataSekolah.getNama();
                                TextView nmSekolah = (TextView) findViewById(R.id.sekolahSiswa);
                                nmSekolah.setText(namaSekolah);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

                    String jurusanSiswa = responses.getId_bidang().toString();

                    String where3 = "id_bidang = " + jurusanSiswa;
                    BackendlessDataQuery dataQuery2 = new BackendlessDataQuery();
                    dataQuery2.setWhereClause(where3);

                    Backendless.Persistence.of(tb_bidang.class).find(dataQuery2, new AsyncCallback<BackendlessCollection<tb_bidang>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_bidang> response) {
                            List<tb_bidang> firstPage = response.getCurrentPage();
                            Iterator<tb_bidang> iterator2 = firstPage.iterator();

                            while (iterator2.hasNext()) {
                                tb_bidang dataJurusan = iterator2.next();

                                String jurusanSiswa = dataJurusan.getNama();
                                TextView jrsSiswa = (TextView) findViewById(R.id.jurusanSiswa);
                                jrsSiswa.setText(jurusanSiswa);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

                    String nisnSiswa = responses.getNisn();
                    EditText nisnS = (EditText)findViewById(R.id.nisnSiswa);
                    nisnS.setText(nisnSiswa);

                    String alamatSiswa = responses.getAlamat();
                    EditText amtSiswa = (EditText) findViewById(R.id.alamatSiswa);
                    amtSiswa.setText(alamatSiswa);

                    String noTelpSiswa = responses.get_no_telp();
                    EditText ntSiswa = (EditText) findViewById(R.id.noTelpSiswa);
                    ntSiswa.setText(noTelpSiswa);

                    String tglLahir = responses.getTgl_lahir();
                    EditText tanggal_lahir2 = (EditText)findViewById(R.id.tanggal_lahir);
                    tanggal_lahir2.setText(tglLahir);

                    String tempatLahir = responses.getTempat_lahir();
                    EditText tempat_lahir2 = (EditText)findViewById(R.id.tempat_lahir);
                    tempat_lahir2.setText(tempatLahir);

                    String imageUri = responses.getFoto();
                    ImageView imagePP = (ImageView)findViewById(R.id.profilePict);
                    Picasso.with(context).load(imageUri).into(imagePP);
                    UpdateAkunSiswa.this.loading_complete();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save_icon:
                this.editdata();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void editdata(){
        dialog = ProgressDialog.show(UpdateAkunSiswa.this, "",
                "Mohon tunggu sebentar...", true);

        BackendlessUser userNow = Backendless.UserService.CurrentUser();
        String id_user_now = userNow.getProperty("id_user").toString();

        String whereClause = "id_user = " + id_user_now;
        final BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);
        Backendless.Data.of(tb_siswa.class).find(dataQuery, new AsyncCallback<BackendlessCollection<tb_siswa>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_siswa> response) {

                if (response.getData().size() == 1) {
                    tb_siswa siswa = response.getData().get(0);

                    EditText nama = (EditText) findViewById(R.id.namaSiswa);
                    siswa.setNama(nama.getText().toString());

                    EditText alamat = (EditText) findViewById(R.id.alamatSiswa);
                    siswa.setAlamat(alamat.getText().toString());

                    EditText no_telp = (EditText) findViewById(R.id.noTelpSiswa);
                    siswa.setNo_telp(no_telp.getText().toString());

                    EditText tanggal_lahir = (EditText) findViewById(R.id.tanggal_lahir);
                    siswa.setTgl_lahir(tanggal_lahir.getText().toString());

                    EditText tempat_lahir = (EditText) findViewById(R.id.tempat_lahir);
                    siswa.setTempat_lahir(tempat_lahir.getText().toString());

                    Backendless.Data.save(siswa, new AsyncCallback<tb_siswa>() {
                        @Override
                        public void handleResponse(tb_siswa response) {
                            Toast.makeText(UpdateAkunSiswa.this,"Data anda berhasil diupdate",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            UpdateAkunSiswa.this.tamat();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(UpdateAkunSiswa.this,"Kok ndak ke save ya?"+fault.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
    protected void tamat() {
        finish();
        startActivity(getIntent());
    }
    protected void loading_complete() {loading.setVisibility(View.GONE);}
}
