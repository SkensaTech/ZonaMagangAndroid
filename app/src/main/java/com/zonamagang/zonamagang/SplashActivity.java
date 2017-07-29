package com.zonamagang.zonamagang;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.zonamagang.zonamagang.Model.Users;
import com.zonamagang.zonamagang.Model.industri;
import com.zonamagang.zonamagang.Model.siswa;

import java.util.HashMap;

public class SplashActivity extends Activity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static String TAG = "SplashActivity";

    //User :
    String Users_level,email,status_aktif,Users_level_id;

    //Industri :
    String alamatIndustri, emailIndustri, jobdescIndustri, kotaIndustri, kualifikasiIndustri, kuotaIndustri, namaIndustri, no_telpIndustri, profilIndustri, provinsiIndustri, is_verifiedIndustri,logoIndustri;
    HashMap<String,Boolean> usersIndustri,bidangIndustri;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent mainIntent = new Intent(SplashActivity.this,home_siswa_1.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();

                    FirebaseUtil.getCurrentUserRef().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Users currentUser = dataSnapshot.getValue(Users.class);
                            Users_level = currentUser.getUsers_level();
                            email = currentUser.getEmail();
                            status_aktif = currentUser.getStatus_aktif();
                            Users_level_id = currentUser.getUsers_level_id();

                            if(status_aktif.equals("1")){
                                if(Users_level.equals("2")){ //JIKA INDUSTRI
                                    FirebaseUtil.getChildrenIndustriRef(Users_level_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            industri currentIndustri = dataSnapshot.getValue(industri.class);
                                            usersIndustri = currentIndustri.getUser();
                                            alamatIndustri = currentIndustri.getAlamat();
                                            bidangIndustri = currentIndustri.getBidang();
                                            emailIndustri = currentIndustri.getEmail();
                                            is_verifiedIndustri = currentIndustri.getIs_verified().toString();
                                            jobdescIndustri = currentIndustri.getJobdesc();
                                            kotaIndustri = currentIndustri.getKota();
                                            kualifikasiIndustri = currentIndustri.getKualifikasi();
                                            kuotaIndustri = currentIndustri.getKuota();
                                            namaIndustri = currentIndustri.getNama();
                                            no_telpIndustri = currentIndustri.getNo_telp();
                                            profilIndustri = currentIndustri.getProfil();
                                            provinsiIndustri = currentIndustri.getProvinsi();
                                            logoIndustri = currentIndustri.getLogo();

                                            Intent industriIntent = new Intent(SplashActivity.this,HomeIndustri.class);
                                            industriIntent.putExtra("Users",usersIndustri); //ini HashMap
                                            industriIntent.putExtra("alamat",alamatIndustri);
                                            industriIntent.putExtra("bidang",bidangIndustri); //ini HashMap
                                            industriIntent.putExtra("email",email);
                                            industriIntent.putExtra("is_verifiedIndustri",is_verifiedIndustri);
                                            industriIntent.putExtra("jobdescIndustri",jobdescIndustri);
                                            industriIntent.putExtra("kotaIndustri",kotaIndustri);
                                            industriIntent.putExtra("kualifikasiIndustri",kualifikasiIndustri);
                                            industriIntent.putExtra("kuotaIndustri",kuotaIndustri);
                                            industriIntent.putExtra("namaIndustri",namaIndustri);
                                            industriIntent.putExtra("no_telpIndustri",no_telpIndustri);
                                            industriIntent.putExtra("profilIndustri",profilIndustri);
                                            industriIntent.putExtra("provinsiIndustri",provinsiIndustri);
                                            industriIntent.putExtra("logoIndustri",logoIndustri);
                                            SplashActivity.this.startActivity(industriIntent);
                                            SplashActivity.this.finish();


                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                                else if(Users_level.equals("3")){ //JIKA SISWA
                                    FirebaseUtil.getChildrenIndustriRef(Users_level_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            siswa currentSiswa = dataSnapshot.getValue(siswa.class);

                                            Intent siswaIntent = new Intent(SplashActivity.this,home_siswa_1.class);
                                            siswaIntent.putExtra("email",email);
                                            siswaIntent.putExtra("Users",currentSiswa.getUsers()); //ini HashMap
                                            siswaIntent.putExtra("alamat",currentSiswa.getAlamat());
                                            siswaIntent.putExtra("bidang",currentSiswa.getBidang()); //ini HashMap
                                            siswaIntent.putExtra("foto",currentSiswa.getFoto());
                                            siswaIntent.putExtra("nama",currentSiswa.getNama());
                                            siswaIntent.putExtra("nisn",currentSiswa.getNisn());
                                            siswaIntent.putExtra("sekolah",currentSiswa.getSekolah());
                                            siswaIntent.putExtra("tempat_lahir",currentSiswa.getTempat_lahir());
                                            siswaIntent.putExtra("tanggal_lahir",currentSiswa.getTgl_lahir());
                                            SplashActivity.this.startActivity(siswaIntent);
                                            SplashActivity.this.finish();

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    Intent mainIntent = new Intent(SplashActivity.this,ActivityMasukSebagai.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
