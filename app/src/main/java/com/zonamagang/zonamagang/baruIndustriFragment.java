package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Denandra on 16/08/2016.
 */
public class baruIndustriFragment extends Fragment {
    /** Fixed **/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.siswa_layout_belum,container,false);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.layoutIndustri);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),detail_industri.class);
                startActivity(intent);
            }
        });

        Button button = (Button)rootView.findViewById(R.id.detailSiswa2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),detail_industri.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public static baruIndustriFragment newInstance(int text, String title){
        baruIndustriFragment f = new baruIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Sudah",title);

        f.setArguments(b);

        return f;
    }


}
