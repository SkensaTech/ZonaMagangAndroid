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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;

public class step_2_industri extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 0;
    private static final String TAG ="step_2_industri";
    ImageView mLogo;
    String fileLocation;
    TextView kuota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_2_industri);

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
                            ImageView img = (ImageView) findViewById(R.id.step_2_industri_logo);
                            img.setImageBitmap(bMap_image);


                        }catch(Exception e)
                        {}
                    }
                }// resultCode
            }// case 1
        }// switch, request code
    }// public void onActivityResult

//    public void showFileChooser(View view) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//
//        try {
//            startActivityForResult(intent,FILE_SELECT_CODE );
//        } catch (android.content.ActivityNotFoundException ex) {
//            // Potentially direct the user to the Market with a Dialog
//            Toast.makeText(this, "Please install a File Manager.",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case FILE_SELECT_CODE:
//                if (resultCode == RESULT_OK) {
//                    // Get the Uri of the selected file
//                    try {
//                        Uri uri = data.getData();
//                        Log.d(TAG, "File Uri: " + uri.toString());
//                        // Get the path
//                        fileLocation = FileUtils.getPath(this, uri);
//                        Log.d(TAG, "File Path: " + fileLocation);
//                        // Get the file instance
//                        // File file = new File(path);
//                        // Initiate the upload
//
////                        mLogo = (ImageView)findViewById(R.id.step_2_industri_logo);
////                        mLogo.setImageURI(Uri.parse(fileLocation));
//
//                        kuota = (TextView)findViewById(R.id.industri_step_2_kuota);
//                        kuota.setText(fileLocation);
//
//                    }
//                    catch(URISyntaxException e){
//                        e.printStackTrace();
//                    }
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}
