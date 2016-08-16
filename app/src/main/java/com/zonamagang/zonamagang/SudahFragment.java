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
public class sudahFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sudah,container,false);
    }

    public static sudahFragment newInstance(int text, String title){
        sudahFragment f = new sudahFragment();
        Bundle b = new Bundle();
        b.putString("Sudah",title);

        f.setArguments(b);

        return f;
    }
}
