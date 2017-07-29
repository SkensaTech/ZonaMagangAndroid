package com.zonamagang.zonamagang;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;

public class LoadingActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    String tujuan,email;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.loading_screen);
        tujuan = getIntent().getStringExtra("tujuan");

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.. */
                if(tujuan.equals("home_siswa_1")){


                }
                else if(tujuan.equals("HomeIndustri")){

                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
