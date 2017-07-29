package com.zonamagang.zonamagang;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zonamagang.zonamagang.Model.siswa;

public class UpdateAkunSiswa extends AppCompatActivity {

    Context context = this;
    ProgressDialog dialog;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_akun_siswa);

        loading = (ProgressBar) findViewById(R.id.update_akun_siswa_progress_bar);

        loading.setVisibility(View.VISIBLE);

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

    }
    protected void tamat() {
        finish();
        startActivity(getIntent());
    }
    protected void loading_complete() {loading.setVisibility(View.GONE);}
}
