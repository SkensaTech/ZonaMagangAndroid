package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Denandra on 16/08/2016.
 */
public class baruFragment extends Fragment{

    private TextView nama;
    private TextView sekolah;
    private TextView bidang;
    private ImageView gambar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.baru, container, false);

        nama = (TextView) rootView.findViewById(R.id.namaSiswa);
        nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailSiswaOlehIndustri.class);
                startActivity(intent);
            }
        });
        gambar = (ImageView) rootView.findViewById(R.id.gambarSiswa);
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailSiswaOlehIndustri.class);
                startActivity(intent);
            }
        });
        sekolah = (TextView) rootView.findViewById(R.id.sekolahSiswa);
        sekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailSiswaOlehIndustri.class);
                startActivity(intent);
            }
        });
        bidang = (TextView) rootView.findViewById(R.id.bidangSiswa);
        bidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailSiswaOlehIndustri.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.detailSiswa:
//                Intent intent = new Intent(getActivity(), DetailSiswaOlehIndustri.class);
//                startActivity(intent);
//        }
//    }

    public static baruFragment newInstance(int text, String title){
        baruFragment f = new baruFragment();
        Bundle b = new Bundle();
        b.putString("Baru",title);

        f.setArguments(b);

        return f;
    }
}
