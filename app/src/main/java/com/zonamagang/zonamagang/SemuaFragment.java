package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Denandra on 16/08/2016.
 */
public class semuaFragment extends Fragment{

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.semua, container, false);
    }

    public static semuaFragment newInstance(int text, String title){
        semuaFragment f = new semuaFragment();
        Bundle b = new Bundle();
        b.putString("Semua",title);

        f.setArguments(b);

        return f;
    }
}