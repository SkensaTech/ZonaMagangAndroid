package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriSiswaCustom;

import java.util.ArrayList;

/**
 * Created by Skensa Tech on 16/08/2016.
 */
public class baruFragment extends Fragment {

    private Button button;
    ArrayList<HomeIndustriSiswaCustom> listSiswa;
    View rootView;
    int id_siswa,id_industri;
    String id_user_now;
    SwipeRefreshLayout swipeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.baru, container, false);

        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);

        return rootView;
    }


    public static baruFragment newInstance(int text, String title){
        baruFragment f = new baruFragment();
        Bundle b = new Bundle();
        b.putString("Baru",title);

        f.setArguments(b);

        return f;
    }
}
