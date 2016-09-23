package com.zonamagang.zonamagang.Adapters.Industri;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.R;

import java.util.ArrayList;

/**
 * Created by Denandra on 20/09/2016.
 */
public class hasil_pencarian_adapter extends ArrayAdapter<CustomIndustri> {
    int id_industri,id_siswa,mYear,mMonth,mDay;
    int tahun_awal,bulan_awal,hari_awal;
    int tahun_akhir,bulan_akhir,hari_akhir;
    CustomIndustri industriNow;
    ProgressDialog progressDialog;
    int lastIdMagang,id_industri_now;

    public hasil_pencarian_adapter(Activity context, ArrayList<CustomIndustri> industri){super(context,0,industri);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.siswa_industri_belum_item,parent,false);
        }

        industriNow = getItem(position);

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

        ImageView logo = (ImageView) listItemView.findViewById(R.id.baruItemFoto);
        String logoUrl = industriNow.getLogo();
        Picasso.with(getContext()).load(logoUrl).into(logo);

        Button siswa_layout_belum_btnDaftar = (Button) listItemView.findViewById(R.id.siswa_layout_belum_btnDaftar);
        siswa_layout_belum_btnDaftar.setTag(position);

        return listItemView;
    }
}
