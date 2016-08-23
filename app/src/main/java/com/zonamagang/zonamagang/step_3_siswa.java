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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zonamagang.zonamagang.Model.last_id;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_siswa;

public class step_3_siswa extends AppCompatActivity {

    ImageView mLogo;
    String nisn,nama,alamat,tempat,tgl,jeniskelamin,provinsi,kota,sekolah,bidang,email,pass;
    Bitmap mapfoto;
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
        sekolah = getIntent().getStringExtra("sekolah");
        bidang = getIntent().getStringExtra("bidang");
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");
        //coding untuk toolbar
        String All = nisn+" "+nama+" "+alamat+" "+tempat+" "+tgl+" "+jeniskelamin+" "+nama+" "+nama+" "+nama+" "+nama+" "+nama+" "+nama;
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
                if (resultCode == RESULT_OK) {
                    Uri photoUri = data.getData();
                    if (photoUri != null) {
                        try {
                            String[] filePathColumn = {
                                    MediaStore.Images.Media.DATA
                            };
                            Cursor cursor = getContentResolver().query(photoUri, filePathColumn, null, null, null);
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String filePath = cursor.getString(columnIndex);
                            cursor.close();
                            Bitmap bMap_image = BitmapFactory.decodeFile(filePath);
                            mLogo.setImageBitmap(bMap_image);


                        } catch (Exception e) {}
                    }
                }
            }
        }
    }
    //end coding upload foto

    private void layoutItems() {
        mLogo = (ImageView) findViewById(R.id.step_3_siswa_logo);

    }
    public void addUserInfo(){
        BackendlessUser user = new BackendlessUser();
        user.setProperty( "email", email);
        user.setProperty("status_aktif",1);
        user.setProperty("user_level",2);
        user.setProperty("id_user",id_user);
        user.setPassword( pass );

        Backendless.UserService.register( user, new AsyncCallback<BackendlessUser>()
        {
            public void handleResponse( BackendlessUser registeredUser )
            {
                //registered tapi belum login
                step_3_siswa.this.addSiswaInfo();
            }

            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_3_siswa.this,"There's an Error ! Code = "+fault.getCode(),Toast.LENGTH_SHORT).show();
                // ada error. bisa di retrieved with fault.getCode()
            }
        } );
    }
    public void addSiswaInfo(){
        Backendless.Persistence.of( tb_siswa.class).findLast(new AsyncCallback<tb_siswa>(){
            @Override
            public void handleResponse( tb_siswa siswaInfo )
            {
                id_siswa = siswaInfo.getId_siswa() + 1;

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                id_siswa = 1;
            }
        });
    }

    public void saveSiswaInfo(){

        tb_siswa saveSiswa = new tb_siswa();

    }
  /*  public void onSubmit(View view){
        setContentView(R.layout.loading_screen);
        //check last user
        Backendless.Persistence.of( last_id.class).findLast(new AsyncCallback<last_id>(){
            @Override
            public void handleResponse( last_id industriInfo )
            {
                id_user = industriInfo.getId_user() + 1;
                step_3_siswa.this.addUserInfo();

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_3_siswa.this,"findLast user failed, "+fault.getMessage(),Toast.LENGTH_LONG).show();
                id_user = 1;
                step_3_siswa.this.addUserInfo();
            }
        });
    }*/
}