package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriSiswaCustom;

import java.util.ArrayList;

/**
 * Created by Denandra on 16/08/2016.
 */
public class sudahFragment extends Fragment {
    private Button button;
    ArrayList<HomeIndustriSiswaCustom> listSiswa;
    View rootView;
    int id_siswa,id_industri;
    String id_user_now;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sudah, container, false);

        return rootView;
    }

    public static sudahFragment newInstance(int text, String title){
        sudahFragment f = new sudahFragment();
        Bundle b = new Bundle();
        b.putString("Sudah",title);

        f.setArguments(b);

        return f;
    }


}
