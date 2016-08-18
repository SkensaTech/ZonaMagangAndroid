package com.zonamagang.zonamagang;


import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class home_siswa_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_siswa_1);

        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);


    }
}
