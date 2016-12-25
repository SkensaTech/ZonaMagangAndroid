package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMasukSebagai extends AppCompatActivity {

    Button mMasukSebagaiSiswa;
    Button mMasukSebagaiIndustri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk_sebagai);
        //layout items
        mMasukSebagaiSiswa = (Button) findViewById(R.id.MasukSebagaiSiswa);
        mMasukSebagaiIndustri = (Button) findViewById(R.id.MasukSebagaiIndustri);
        //end layout items

        mMasukSebagaiSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSiswa = new Intent(ActivityMasukSebagai.this,home_siswa_1.class);
                ActivityMasukSebagai.this.startActivity(intentSiswa);
                ActivityMasukSebagai.this.finish();
            }
        });

        mMasukSebagaiIndustri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSiswa = new Intent(ActivityMasukSebagai.this,HomeIndustri.class);
                ActivityMasukSebagai.this.startActivity(intentSiswa);
                ActivityMasukSebagai.this.finish();
            }
        });

    }
}
