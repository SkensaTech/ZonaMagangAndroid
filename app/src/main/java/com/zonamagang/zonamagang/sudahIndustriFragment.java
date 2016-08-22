package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Denandra on 16/08/2016.
 */
public class sudahIndustriFragment extends Fragment {
    /** Fixed **/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.siswa_layout_sudah,container,false);
    }

    public static sudahIndustriFragment newInstance(int text, String title){
        sudahIndustriFragment f = new sudahIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Sudah",title);

        f.setArguments(b);

        return f;
    }


}
