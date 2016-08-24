package com.zonamagang.zonamagang;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    String tujuan;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        tujuan = getIntent().getStringExtra("tujuan");

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.. */
                if(tujuan == "home_siswa_1"){
                    Intent mainIntent = new Intent(LoadingActivity.this,home_siswa_1.class);
                    LoadingActivity.this.startActivity(mainIntent);
                    LoadingActivity.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
