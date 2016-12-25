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
import android.widget.TextView;

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
        setContentView(R.layout.activity_detail_siswa);

        ProgressDialog dialog = new ProgressDialog(DetailSiswa.this);
        dialog.setMessage("Tunggu Sebentar..");
        dialog.show();

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
