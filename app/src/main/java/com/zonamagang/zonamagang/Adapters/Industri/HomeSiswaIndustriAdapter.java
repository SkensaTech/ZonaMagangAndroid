package com.zonamagang.zonamagang.Adapters.Industri;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Skensa Tech on 22/8/2016.
 */
public class HomeSiswaIndustriAdapter extends ArrayAdapter<CustomIndustri> {

    int id_industri;

    public HomeSiswaIndustriAdapter(Activity context, ArrayList<CustomIndustri> industri){super(context,0,industri);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.siswa_industri_belum_item,parent,false);
        }

        CustomIndustri industriNow = getItem(position);

        TextView namaIndustri = (TextView) listItemView.findViewById(R.id.namaIndustri);
        namaIndustri.setText(industriNow.getNama());

        TextView alamatIndustri = (TextView) listItemView.findViewById(R.id.alamatIndustri);
        alamatIndustri.setText(industriNow.getAlamat());

        TextView telpIndustri = (TextView) listItemView.findViewById(R.id.telpIndustri);
        telpIndustri.setText(industriNow.getNo_telp());

        TextView emailIndustri = (TextView) listItemView.findViewById(R.id.emailIndustri);
        emailIndustri.setText(industriNow.getEmail());

        TextView kuotaIndustri = (TextView) listItemView.findViewById(R.id.kuotaIndustri);
        kuotaIndustri.setText("Kuota : "+industriNow.getKuota()+" orang");

        ImageView logo = (ImageView) listItemView.findViewById(R.id.gambarSiswa);
        String logoUrl = industriNow.getLogo();
        Picasso.with(getContext()).load(logoUrl).into(logo);

        id_industri = industriNow.getId_industri();

        Button siswa_layout_belum_btnDaftar = (Button) listItemView.findViewById(R.id.siswa_layout_belum_btnDaftar);


        siswa_layout_belum_btnDaftar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

            }
        });

        Button siswa_layout_belum_btnDetail = (Button) listItemView.findViewById(R.id.siswa_layout_belum_btnDetail);

        siswa_layout_belum_btnDetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub.


            }
        });

        return listItemView;

    }

}
