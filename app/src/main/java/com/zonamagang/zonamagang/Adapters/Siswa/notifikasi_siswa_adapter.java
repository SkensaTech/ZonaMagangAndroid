package com.zonamagang.zonamagang.Adapters.Siswa;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.Adapters.Siswa.notifikasi_siswa_costum;
import com.zonamagang.zonamagang.R;

/**
 * Created by Rizal on 8/23/2016.
 */
public class notifikasi_siswa_adapter extends ArrayAdapter<notifikasi_siswa_costum> {
    notifikasi_siswa_costum notifikasi_siswa_costum;

    public notifikasi_siswa_adapter(Activity context, ArrayList<notifikasi_siswa_costum> listView) {super(context, 0, listView);}

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.notifikasi_siswa_item,parent,false);
        }
        notifikasi_siswa_costum = getItem(position);

        TextView nama = (TextView) view.findViewById(R.id.nama_industri);
        nama.setText(notifikasi_siswa_costum.getNama());

        TextView isi_notif = (TextView) view.findViewById(R.id.isi_notif);
        isi_notif.setText(notifikasi_siswa_costum.getNotif());

        ImageView logo = (ImageView) view.findViewById(R.id.logo_industri);
        String logoUrl = notifikasi_siswa_costum.getLogo();
        Picasso.with(getContext()).load(logoUrl).into(logo);

        return view;
    }
}
