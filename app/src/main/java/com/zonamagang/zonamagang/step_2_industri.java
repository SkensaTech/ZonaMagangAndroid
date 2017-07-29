package com.zonamagang.zonamagang;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.zonamagang.zonamagang.Model.industri;
import com.zonamagang.zonamagang.Model.parent_bidang;

import java.util.List;

public class step_2_industri extends AppCompatActivity {

    ImageView mLogo;
    EditText mKuota;
    EditText mProfil;
    EditText mJobdesc;
    EditText mKualifikasi;
    Button mSubmit;
    Spinner mBidang;
    Spinner mParentBidang;
    private EditText input;
    private Button check;

    //Sebelumnya :
    String email,pass,nama,alamat,telp,kota;
    //step2:
    String profil,jobdesc,kualifikasi,provinsi="Bali",logo="kosong";
    int id_industri,id_bidang,id_user,kuota,id_bidang_industri;
    Bitmap bMap_image;
    industri saveIndustri;

    //etc :
    int id_parent_bidang;
    String text_parent_bidang;
    List<parent_bidang> parent_bidang;
    List<com.zonamagang.zonamagang.Model.bidang> bidang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_2_industri);
        //untuk toolbar
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.layoutItems();

        this.layoutItems();

        //isi spinner parent
        this.isiSpinnerParent();

        //Listeners
        this.eventListeners();
        this.setSpinnerBidangListener();

        input = (EditText) findViewById(R.id.industri_step_2_kuota);
        check = (Button) findViewById(R.id.industri_step_2_submit);


//        check.setOnClickListener(new View.OnClickListener() {
//                                     @Override
//                                     public void onClick(View arg0) {
//
//
//                                     }
//                                 });

        //All Info Industri
        //Sebelumnya:
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");
        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        telp = getIntent().getStringExtra("telp");
        kota = getIntent().getStringExtra("kota");
        //step2
        id_industri = 0;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void onSubmit(View view) {

        String kosong = null;
        if (mKuota.getText().toString().equals("") || mProfil.getText().toString().equals("") || mJobdesc.getText().toString().equals("")
                || mKualifikasi.getText().toString().equals("")) {
            Toast.makeText(this, "Ada data yang kosong !", Toast.LENGTH_SHORT).show();
        } else {


            setContentView(R.layout.loading_screen);
        }
    }

    public void addUserInfo() {

    }

    public void addIndustriInfo() {

    }

    public void getLastTbBidangIndustri() {

    }

    public void addTbBidangIndustriInfo() {

    }

    public void saveIndustriInfo() {


//        saveIndustri = new industri();
//        saveIndustri.setId_industri(id_industri);
//        saveIndustri.setId_user(id_user);
//        kuota = Integer.parseInt(mKuota.getText().toString());
//        profil = mProfil.getText().toString();
//        jobdesc = mJobdesc.getText().toString();
//        kualifikasi = mKualifikasi.getText().toString();
//        saveIndustri.setNama(nama);
//        saveIndustri.setAlamat(alamat);
//        saveIndustri.setProfil(profil);
//        saveIndustri.setProvinsi(provinsi);
//        saveIndustri.setNo_telp(telp);
//        saveIndustri.setKota(kota);
//        saveIndustri.setKualifikasi(kualifikasi);
//        saveIndustri.setKuota(kuota);
//        saveIndustri.setJobdesc(jobdesc);
//        saveIndustri.setEmail(email);
    }

    public void registerSuccess() {


    }

    public void eventListeners() {

    }

    public void isiSpinnerParent() {
    }

    public void isiSpinnerSub() {
    }

    private void setSpinnerBidangListener() {

    }

    public void showFileChooser(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK) {
                    Uri photoUri = data.getData();
                    if (photoUri != null) {
                        try {
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getContentResolver().query(photoUri, filePathColumn, null, null, null);
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String filePath = cursor.getString(columnIndex);
                            cursor.close();
                            bMap_image = BitmapFactory.decodeFile(filePath);
                            mLogo.setImageBitmap(bMap_image);


                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }


    private void layoutItems(){
        mKuota = (EditText) findViewById(R.id.industri_step_2_kuota);
        mProfil = (EditText) findViewById(R.id.industri_step_2_profil);
        mJobdesc = (EditText) findViewById(R.id.industri_step_2_jobdesc);
        mKualifikasi = (EditText) findViewById(R.id.industri_step_2_kualifikasi);
        mLogo = (ImageView) findViewById(R.id.step_2_industri_logo);
        mSubmit = (Button)findViewById(R.id.industri_step_2_submit);
        mParentBidang = (Spinner)findViewById(R.id.industri_step_2_parent_bidang);
        mBidang = (Spinner)findViewById(R.id.industri_step_2_bidang);
    }

}
