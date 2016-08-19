package com.zonamagang.zonamagang;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class register2 extends AppCompatActivity {
    Button daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         daftar = (Button) findViewById(R.id.tomboldaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = getIntent().getStringExtra("jenis_daftar");
                Intent step1;
                if (ss.equals("siswa")){
                     step1 = new Intent(getApplicationContext(),step_1_siswa_daftar.class);
                    startActivity(step1);

                } else if(ss.equals("industri"))  {
                     step1 = new Intent(register2.this,step_1_industri.class);
                    startActivity(step1);

                }
            }
        });

    }
    //
}