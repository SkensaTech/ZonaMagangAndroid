package com.zonamagang.zonamagang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class register2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        String s = getIntent().getStringExtra("jenis_daftar");
        TextView daftaremail = (TextView) findViewById(R.id.reg);
        daftaremail.setText(s);
    }
}
