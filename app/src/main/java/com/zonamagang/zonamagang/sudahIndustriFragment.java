package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
import com.zonamagang.zonamagang.Model.industri;
import com.zonamagang.zonamagang.Model.magang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skensa Tech on 16/08/2016.
 */
public class sudahIndustriFragment extends Fragment {
    ArrayList<CustomIndustri> listIndustri;
    ArrayList<magang> list_magang;
    List<industri> list_industri;
    View rootView;
    int id_siswa,id_bidang,id_sekolah;
    String nama,email,notelp;
    String id_user_now,searchvalue;
    home_siswa_1 activity;
    /** Fixed **/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_sudah,container,false);
         activity = (home_siswa_1) getActivity();


        return rootView;
    }

    public static sudahIndustriFragment newInstance(int text, String title){
        sudahIndustriFragment f = new sudahIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Terdaftar",title);

        f.setArguments(b);

        return f;
    }


}
