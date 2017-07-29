package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import com.zonamagang.zonamagang.Adapters.Siswa.notifikasi_siswa_costum;

/**
 * Created by Skensa Tech on 8/23/2016.
 */
public class notifikasi_siswa_fragment extends Fragment {
    ArrayList<notifikasi_siswa_costum> listNotif;
    private static final String TAG = "notifikasi";
    View rootView;
    String whereClause = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.notifikasi_siswa_layout, container, false);
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("Notifikasi Siswa");
        listNotif = new ArrayList<>();
        Log.e(TAG,"Notifikasi siswa fragment jalan.");


        Log.e(TAG,"return rootview");
        return rootView;
    }


}
