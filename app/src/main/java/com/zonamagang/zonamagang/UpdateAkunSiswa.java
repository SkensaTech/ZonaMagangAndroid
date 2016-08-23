package com.zonamagang.zonamagang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
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
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UpdateAkunSiswa extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun_siswa);
        Backendless.initApp(this, Constants.APP_ID, Constants.APP_SECRET, Constants.APP_VERSION);

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
                                EditText nmSekolah = (EditText) findViewById(R.id.sekolahSiswa);
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
                                EditText jrsSiswa = (EditText) findViewById(R.id.jurusanSiswa);
                                jrsSiswa.setText(jurusanSiswa);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

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
        BackendlessUser userNow = Backendless.UserService.CurrentUser();
        String id_user_now = userNow.getProperty("id_user").toString();

        String whereClause = "id_user = " + id_user_now;
        final BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);
        final AsyncCallback<tb_siswa> updateResponder = new AsyncCallback<tb_siswa>() {
            @Override
            public void handleResponse(tb_siswa updatedtb_siswa) {
                System.out.println("tb_siswa's nama after update " + updatedtb_siswa.nama);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                System.out.println("Server reported an error - " + backendlessFault.getMessage());
            }
        };
        Backendless.Data.of(tb_siswa.class).findLast(new AsyncCallback<tb_siswa>() {
            @Override
            public void handleResponse(tb_siswa tb_siswa) {
                System.out.println("Loaded object. nama - " + tb_siswa.nama);
                EditText nama = (EditText) findViewById(R.id.namaSiswa);
                tb_siswa.nama = nama.getText().toString();

                EditText alamat = (EditText) findViewById(R.id.alamatSiswa);
                tb_siswa.alamat = alamat.getText().toString();

                EditText no_telp = (EditText) findViewById(R.id.noTelpSiswa);
                tb_siswa.no_telp = no_telp.getText().toString();

                EditText tanggal_lahir = (EditText) findViewById(R.id.tanggal_lahir);
                tb_siswa.tgl_lahir = tanggal_lahir.getText().toString();

                EditText tempat_lahir = (EditText) findViewById(R.id.tempat_lahir);
                tb_siswa.tempat_lahir = tempat_lahir.getText().toString();

                Backendless.Data.of(tb_siswa.class).save(tb_siswa, updateResponder);
                Toast.makeText(UpdateAkunSiswa.this,"Data anda berhasil diupdate ",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(UpdateAkunSiswa.this,UpdateAkunSiswa.class);
                startActivity(intent);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                System.out.println("Server reported an error - " + backendlessFault.getMessage());
            }
        });
    }
}
