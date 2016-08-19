package com.zonamagang.zonamagang;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;

public class step_2_industri extends AppCompatActivity {

    ImageView mLogo;
    TextView mKuota;
    TextView mProfil;
    TextView mJobdesc;
    TextView mKualifikasi;
    Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_2_industri);
        this.layoutItems();

        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void showFileChooser(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
            {
                if (resultCode == RESULT_OK)
                {
                    Uri photoUri = data.getData();
                    if (photoUri != null)
                    {
                        try {
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getContentResolver().query(photoUri, filePathColumn, null, null, null);
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String filePath = cursor.getString(columnIndex);
                            cursor.close();
                            Bitmap bMap_image = BitmapFactory.decodeFile(filePath);
                            mLogo.setImageBitmap(bMap_image);


                        }catch(Exception e)
                        {}
                    }
                }
            }
        }
    }

    private void layoutItems(){
        mKuota = (TextView)findViewById(R.id.industri_step_2_kuota);
        mProfil = (TextView)findViewById(R.id.industri_step_2_profil);
        mJobdesc = (TextView)findViewById(R.id.industri_step_2_jobdesc);
        mKualifikasi = (TextView)findViewById(R.id.industri_step_2_kualifikasi);
        mLogo = (ImageView) findViewById(R.id.step_2_industri_logo);
        mSubmit = (Button)findViewById(R.id.industri_step_2_submit);
    }

}
