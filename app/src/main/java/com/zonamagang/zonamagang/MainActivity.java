package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;


public class MainActivity extends AppCompatActivity {
    TextView x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Backendless.initApp(this,Constants.APP_ID,Constants.APP_SECRET,Constants.APP_VERSION);
        setContentView(R.layout.activity_main);
        x = (TextView) findViewById(R.id.buatakun);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent register = new Intent(getApplicationContext(),register1.class);
                startActivity(register);

            }
        });


    }
}
