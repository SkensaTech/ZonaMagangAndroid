package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class register1 extends AppCompatActivity {

    Button siswa, industri;
    String jenis_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        siswa = (Button) findViewById(R.id.siswa);
        industri = (Button) findViewById(R.id.industri);
        siswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                Intent daftarsiswa = new Intent(getApplicationContext(), register2.class);
                jenis_daftar = "siswa";
                daftarsiswa.putExtra("jenis_daftar", jenis_daftar);
                startActivity(daftarsiswa);
            }
        });
        industri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
               // Intent daftarindustri = new Intent(getApplicationContext(), register2.class);
                jenis_daftar = "industri";
                //daftarindustri.putExtra("jenis_daftar", jenis_daftar);
                //startActivity(daftarindustri);
                Toast.makeText(getApplicationContext(),"Hanya Industri yang Terverifikasi Boleh Akses",Toast.LENGTH_SHORT).show();
            }
        });
        TextView masuk = (TextView) findViewById(R.id.signin);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent x = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(x);

            }
        });
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(register1.this,MainActivity.class);
        startActivity(intent);
    }
}
