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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Model.last_id;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_bidang_industri;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_parent_bidang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class step_2_industri extends AppCompatActivity {

    ImageView mLogo;
    TextView mKuota;
    TextView mProfil;
    TextView mJobdesc;
    TextView mKualifikasi;
    Button mSubmit;
    Spinner mBidang;
    Spinner mParentBidang;
    //Sebelumnya :
    String email,pass,nama,alamat,telp,kota;
    //step2:
    String profil,jobdesc,kualifikasi,provinsi="Bali",logo;
    int id_industri,id_bidang,id_user,kuota,id_bidang_industri;
    Bitmap bMap_image;
    tb_industri saveIndustri;

    //etc :
    int id_parent_bidang;
    String text_parent_bidang;
    List<tb_parent_bidang> parent_bidang;
    List<tb_bidang> bidang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_2_industri);
        Backendless.initApp( this, Constants.APP_ID, Constants.APP_SECRET, Constants.APP_VERSION );
        this.layoutItems();

        //isi spinner parent
        this.isiSpinnerParent();

        //Listeners
        this.eventListeners();
        this.setSpinnerBidangListener();

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

        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void onSubmit(View view){
        setContentView(R.layout.loading_screen);
        //check last user
        Backendless.Persistence.of( last_id.class).findLast(new AsyncCallback<last_id>(){
            @Override
            public void handleResponse( last_id industriInfo )
            {
                id_user = industriInfo.getId_user() + 1;
                step_2_industri.this.addUserInfo();

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_2_industri.this,"findLast user failed, "+fault.getMessage(),Toast.LENGTH_LONG).show();
                id_user = 1;
                step_2_industri.this.addUserInfo();
            }
        });
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
                step_2_industri.this.addIndustriInfo();
            }

            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_2_industri.this,"There's an Error ! Code = "+fault.getCode(),Toast.LENGTH_SHORT).show();
                // ada error. bisa di retrieved with fault.getCode()
            }
        } );
    }

    public void addIndustriInfo(){
        Backendless.Persistence.of( tb_industri.class).findLast( new AsyncCallback<tb_industri>(){
            @Override
            public void handleResponse( tb_industri industriInfo )
            {
                id_industri = industriInfo.getId_industri() + 1;

                step_2_industri.this.getLastTbBidangIndustri();

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Toast.makeText(step_2_industri.this,"findLast industri error, "+fault.getMessage(),Toast.LENGTH_LONG).show();
                id_industri = 1;
                step_2_industri.this.getLastTbBidangIndustri();
            }
        });
    }

    public void getLastTbBidangIndustri(){
        Backendless.Persistence.of( tb_bidang_industri.class).findLast( new AsyncCallback<tb_bidang_industri>(){
            @Override
            public void handleResponse( tb_bidang_industri item )
            {
                id_bidang_industri = item.getId_bidang_industri() + 1;
                step_2_industri.this.addTbBidangIndustriInfo();
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_2_industri.this,"findLast industri failed, "+fault.getMessage(),Toast.LENGTH_LONG).show();
                id_bidang_industri = 1;
                step_2_industri.this.addTbBidangIndustriInfo();
            }
        });
    }

    public void addTbBidangIndustriInfo(){
        tb_bidang_industri bidangIndustriInfo = new tb_bidang_industri();
        bidangIndustriInfo.setId_bidang(id_bidang);
        bidangIndustriInfo.setId_bidang_industri(id_bidang_industri);
        bidangIndustriInfo.setId_industri(id_industri);

        // save object asynchronously
        Backendless.Persistence.save( bidangIndustriInfo, new AsyncCallback<tb_bidang_industri>() {
            public void handleResponse( tb_bidang_industri response )
            {
                // new Contact instance has been saved
                step_2_industri.this.saveIndustriInfo();
            }

            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Toast.makeText(step_2_industri.this,"Error bidang Industri save= "+fault.getMessage()+", id_bidang_industri = "+id_bidang_industri,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveIndustriInfo(){


        saveIndustri = new tb_industri();
        saveIndustri.setId_industri(id_industri);
        saveIndustri.setId_user(id_user);
        kuota = Integer.parseInt(mKuota.getText().toString());
        profil = mProfil.getText().toString();
        jobdesc = mJobdesc.getText().toString();
        kualifikasi = mKualifikasi.getText().toString();
        saveIndustri.setNama(nama);
        saveIndustri.setAlamat(alamat);
        saveIndustri.setProfil(profil);
        saveIndustri.setProvinsi(provinsi);
        saveIndustri.setNo_telp(telp);
        saveIndustri.setKota(kota);
        saveIndustri.setKualifikasi(kualifikasi);
        saveIndustri.setKuota(kuota);
        saveIndustri.setJobdesc(jobdesc);
        saveIndustri.setEmail(email);
        Backendless.Files.Android.upload( bMap_image,
                Bitmap.CompressFormat.PNG,
                100,
                id_user+"_industri_logo.png",
                "mypics",
                new AsyncCallback<BackendlessFile>()
                {
                    @Override
                    public void handleResponse( final BackendlessFile backendlessFile )
                    {
                        logo = backendlessFile.getFileURL();
                        step_2_industri.this.saveIndustri.setLogo(logo);
                        // save object asynchronously
                        Backendless.Persistence.save( saveIndustri, new AsyncCallback<tb_industri>() {
                            public void handleResponse( tb_industri response )
                            {
                                step_2_industri.this.registerSuccess();
                            }

                            public void handleFault( BackendlessFault fault )
                            {
                                Toast.makeText(step_2_industri.this,"KUOTA = "+kuota+"Error saveIndustriInfo = "+fault.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void handleFault( BackendlessFault backendlessFault )
                    {
                        Toast.makeText( step_2_industri.this, backendlessFault.toString(), Toast.LENGTH_SHORT ).show();
                    }
                });
    }

    public void registerSuccess(){

        last_id last_id_user = new last_id();
        last_id_user.setId_user(id_user);
        // save object asynchronously
        Backendless.Persistence.save( last_id_user, new AsyncCallback<last_id>() {
            public void handleResponse( last_id response )
            {
                // new Contact instance has been saved
                Intent loginIntent = new Intent(step_2_industri.this,MainActivity.class);
                startActivity(loginIntent);
            }

            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });

    }

    public void eventListeners(){
        //ParentBidang ItemSelected
        mParentBidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tb_parent_bidang parentBidangSelector = (tb_parent_bidang) parent.getSelectedItem();
                id_parent_bidang = parentBidangSelector.getId_parent_bidang();
                if(id_parent_bidang == 0){
                    mBidang.setSelection(0);
                    mBidang.setEnabled(false);
                }
                else{
                    step_2_industri.this.isiSpinnerSub();
                }
                mBidang.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void isiSpinnerParent(){
        parent_bidang = new ArrayList<>();
        parent_bidang.add(new tb_parent_bidang(0,"Pilih bidang industri anda."));
        Backendless.Data.mapTableToClass( "tb_parent_bidang", tb_parent_bidang.class );
        Backendless.Persistence.of(tb_parent_bidang.class).find( new AsyncCallback<BackendlessCollection<tb_parent_bidang>>(){
            @Override
            public void handleResponse( BackendlessCollection<tb_parent_bidang> hasil )
            {
                List<tb_parent_bidang> firstPage = hasil.getCurrentPage();

                Iterator<tb_parent_bidang> iterator = firstPage.iterator();

                while( iterator.hasNext() )
                {
                    tb_parent_bidang bidangList = iterator.next();
                    step_2_industri.this.parent_bidang.add(new tb_parent_bidang(bidangList.getId_parent_bidang(),bidangList.getNama()));
                }
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_2_industri.this,"Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<tb_parent_bidang> parent_bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, parent_bidang);
        mParentBidang.setAdapter(parent_bidang_adapter);
    }

    public void isiSpinnerSub(){
        mBidang.setEnabled(false);
        bidang = new ArrayList<>();
        bidang.add(new tb_bidang(0,0,"Pilih sub bidang industri anda."));
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause("id_parent_bidang = "+id_parent_bidang);
        Backendless.Data.mapTableToClass( "tb_bidang", tb_bidang.class );
        Backendless.Persistence.of(tb_bidang.class).find( dataQuery, new AsyncCallback<BackendlessCollection<tb_bidang>>(){
            @Override
            public void handleResponse( BackendlessCollection<tb_bidang> hasil )
            {
                List<tb_bidang> firstPage = hasil.getCurrentPage();

                Iterator<tb_bidang> iterator = firstPage.iterator();

                while( iterator.hasNext() )
                {
                    tb_bidang bidangList = iterator.next();
                    step_2_industri.this.bidang.add(new tb_bidang(bidangList.getId_bidang(),bidangList.getId_parent_bidang(),bidangList.getNama()));
                }
                mBidang.setEnabled(true);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(step_2_industri.this,"Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<tb_bidang> parent_bidang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bidang);
        mBidang.setAdapter(parent_bidang_adapter);
    }

    private void setSpinnerBidangListener(){
        mBidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tb_bidang parentBidangSelector = (tb_bidang) parent.getSelectedItem();
                id_bidang = parentBidangSelector.getId_parent_bidang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

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

    private void layoutItems(){
        mKuota = (TextView)findViewById(R.id.industri_step_2_kuota);
        mProfil = (TextView)findViewById(R.id.industri_step_2_profil);
        mJobdesc = (TextView)findViewById(R.id.industri_step_2_jobdesc);
        mKualifikasi = (TextView)findViewById(R.id.industri_step_2_kualifikasi);
        mLogo = (ImageView) findViewById(R.id.step_2_industri_logo);
        mSubmit = (Button)findViewById(R.id.industri_step_2_submit);
        mParentBidang = (Spinner)findViewById(R.id.industri_step_2_parent_bidang);
        mBidang = (Spinner)findViewById(R.id.industri_step_2_bidang);
    }

}
