package com.zonamagang.zonamagang;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zonamagang.zonamagang.Model.siswa;

public class step_3_siswa extends AppCompatActivity {

    ImageView mLogo;
    String nisn="0",nama="0",alamat="0",tempat="0",tgl="0",jeniskelamin="0",provinsi="0",kota="0",sekolah="0",bidang="0",email="0",pass="0",foto="0";
    String telp="0";
    Bitmap bMap_image;
    public static String user_id;

    EditText notelp;
    siswa saveSiswa;
    int id_user,id_siswa,id_bidang,id_sekolah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_3_siswa);

        this.layoutItems();
        //passing from layout step 2
        nisn = getIntent().getStringExtra("nisn");
        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        tempat = getIntent().getStringExtra("tempat");
        tgl =  getIntent().getStringExtra("tanggal");
        jeniskelamin = getIntent().getStringExtra("jeniskelamin");
        provinsi = getIntent().getStringExtra("provinsi");
        kota = getIntent().getStringExtra("kota");
        id_sekolah = getIntent().getIntExtra("id_sekolah",0);
        id_bidang = getIntent().getIntExtra("id_bidang",0);
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");
        telp = notelp.getText().toString();
//        Toast.makeText(getApplicationContext(),"Sekolah : "+id_sekolah+"Id Bidang : "+id_bidang,Toast.LENGTH_LONG).show();

//        id_sekolah = Integer.parseInt(sekolah);
//        id_bidang = Integer.parseInt(bidang);
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //coding upload foto
    public void showFileChooser(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
            {
                if (resultCode == RESULT_OK)
                {
                    Uri photoUri = data.getData();
                    if (photoUri != null)
                    {
                        try {
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getContentResolver().query(photoUri, filePathColumn, null, null, null);
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String filePath = cursor.getString(columnIndex);
                            cursor.close();
                            bMap_image = BitmapFactory.decodeFile(filePath);
                            mLogo.setImageBitmap(bMap_image);


                        }catch(Exception e)
                        {}
                    }
                }
            }
        }
    }
    //end coding upload foto

    private void layoutItems() {
        mLogo = (ImageView) findViewById(R.id.step_3_siswa_logo);
        notelp = (EditText) findViewById(R.id.notelp);

    }
    public void addUserInfo(){

    }
    public void addSiswaInfo(){

    }
    public void debug(){
        Toast.makeText(getApplicationContext(),id_sekolah+"dan"+id_bidang,Toast.LENGTH_LONG).show();
    }
    public void saveSiswaInfo(){

        saveSiswa = new siswa();
//        saveSiswa.setId_siswa(id_siswa);
//        saveSiswa.setId_user(id_user);
//        saveSiswa.setId_bidang(id_bidang);
//        saveSiswa.setId_sekolah(id_sekolah);
//        saveSiswa.setNama(nama);
//        saveSiswa.setAlamat(alamat);
//        saveSiswa.setJenis_kelamin(jeniskelamin);
//        saveSiswa.setNo_telp(telp);
//        saveSiswa.setNisn(nisn);
//        saveSiswa.setTempat_lahir(tempat);
//        saveSiswa.setTgl_lahir(tgl);

    }
    public void onSubmit(View view){
        setContentView(R.layout.loading_screen);

    }
    public void registerSuccess(){

    }

    public void onBackPressed(){
        Intent intent = new Intent(step_3_siswa.this,register1.class);
        startActivity(intent);
        this.finish();
    }
}