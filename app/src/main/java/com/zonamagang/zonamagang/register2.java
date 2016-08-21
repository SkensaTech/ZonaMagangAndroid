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
    TextView mEmail;
    TextView mPass;
    TextView mRePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.layoutItems();

        daftar = (Button) findViewById(R.id.tomboldaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pass = mPass.getText().toString();

                String ss = getIntent().getStringExtra("jenis_daftar");
                Intent step1;
                if (ss.equals("siswa")){
                    step1 = new Intent(getApplicationContext(),step_1_siswa_daftar.class);
                    step1.putExtra("email",email);
                    step1.putExtra("pass",pass);
                    startActivity(step1);

                } else if(ss.equals("industri"))  {
                     step1 = new Intent(register2.this,step_1_industri.class);
                    step1.putExtra("email",email);
                    step1.putExtra("pass",pass);
                    startActivity(step1);

                }
            }
        });

    }

    private void layoutItems(){
        mEmail = (TextView)findViewById(R.id.daftaremail);
        mPass = (TextView)findViewById(R.id.daftarpwd);
        mRePass = (TextView)findViewById(R.id.daftarrepwd);
    }
}