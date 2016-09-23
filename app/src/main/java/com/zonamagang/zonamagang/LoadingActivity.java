package com.zonamagang.zonamagang;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.Iterator;
import java.util.List;

public class LoadingActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    String tujuan,email;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.loading_screen);
        tujuan = getIntent().getStringExtra("tujuan");

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.. */
                if(tujuan.equals("home_siswa_1")){

                    BackendlessUser userNow = Backendless.UserService.CurrentUser();
                    String id_user_string = userNow.getProperty("id_user").toString();
                    email = userNow.getProperty("email").toString();

                    String whereClause = "id_user = " + id_user_string;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(whereClause);
                    Backendless.Persistence.of(tb_siswa.class).find(dataQuery,
                            new AsyncCallback<BackendlessCollection<tb_siswa>>() {
                                @Override
                                public void handleResponse(BackendlessCollection<tb_siswa> foundContacts) {
                                    List<tb_siswa> firstPage = foundContacts.getCurrentPage();

                                    Iterator<tb_siswa> iterator = firstPage.iterator();

                                    while (iterator.hasNext()) {
                                        tb_siswa siswaInfo = iterator.next();
                                        String nama = siswaInfo.getNama();
                                        String foto = siswaInfo.getFoto();
                                        int id_siswa = siswaInfo.getId_siswa();
                                        String id_siswa_string = Integer.toString(id_siswa);

                                        Intent homeSiswa = new Intent(LoadingActivity.this, home_siswa_1.class);
                                        homeSiswa.putExtra("email", email);
                                        homeSiswa.putExtra("nama", nama);
                                        homeSiswa.putExtra("foto", foto);
                                        homeSiswa.putExtra("id_siswa",id_siswa_string);
                                        LoadingActivity.this.startActivity(homeSiswa);
                                        LoadingActivity.this.finish();
                                    }
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    // an error has occurred, the error code can be retrieved with fault.getCode()
//                                    Toast.makeText(MainActivity.this, "Failed to get industri info, " + fault.getMessage(), Toast.LENGTH_LONG).show();
                                    new AlertDialog.Builder(getApplicationContext())
                                            .setTitle("Alert")
                                            .setMessage("Failed to get industri info, "+fault.getMessage())
                                            .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            })
                                            .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .show();
                                }
                            });
                }
                else if(tujuan.equals("HomeIndustri")){
                    BackendlessUser userNow = Backendless.UserService.CurrentUser();
                    String id_user_string = userNow.getProperty("id_user").toString();
                    email = userNow.getProperty("email").toString();

                    String whereClause = "id_user = " + id_user_string;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(whereClause);
                    Backendless.Persistence.of(tb_industri.class).find(dataQuery,
                            new AsyncCallback<BackendlessCollection<tb_industri>>() {
                                @Override
                                public void handleResponse(BackendlessCollection<tb_industri> foundContacts) {
                                    List<tb_industri> firstPage = foundContacts.getCurrentPage();

                                    Iterator<tb_industri> iterator = firstPage.iterator();

                                    while (iterator.hasNext()) {
                                        tb_industri industriInfo = iterator.next();
                                        String nama = industriInfo.getNama();
                                        String logo = industriInfo.getLogo();
                                        int id_industri = industriInfo.getId_industri();
                                        String id_industri_string = Integer.toString(id_industri);
                                        String pimpinan = industriInfo.getPimpinan();

                                        Intent homeIndustri = new Intent(LoadingActivity.this, HomeIndustri.class);
                                        homeIndustri.putExtra("email", email);
                                        homeIndustri.putExtra("nama", nama);
                                        homeIndustri.putExtra("logo", logo);
                                        homeIndustri.putExtra("pimpinan", pimpinan);
                                        homeIndustri.putExtra("id_industri",id_industri_string);
                                        LoadingActivity.this.startActivity(homeIndustri);
                                        LoadingActivity.this.finish();
                                    }
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    // an error has occurred, the error code can be retrieved with fault.getCode()
//                                    Toast.makeText(MainActivity.this, "Failed to get industri info, " + fault.getMessage(), Toast.LENGTH_LONG).show();
                                    new AlertDialog.Builder(getApplicationContext())
                                            .setTitle("Alert")
                                            .setMessage("Failed to get industri info., "+fault.getMessage())
                                            .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            })
                                            .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .show();
                                }
                            });
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
