package com.zonamagang.zonamagang.Adapters.admin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.R;

import java.util.List;

/**
 * Created by ASUS on 9/22/2016.
 */
public class daftar_industri_adapter extends ArrayAdapter<tb_industri> {
    Context context;
    int layout;
    List<tb_industri> listIndustri;
    public daftar_industri_adapter(Context context,int layout,List<tb_industri> listIndustri){
        super(context,layout,listIndustri);
        this.context = context;
        this.layout = layout;
        this.listIndustri = listIndustri;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Holder holder;
        if (v==null) {
            LayoutInflater vi = ((Activity)context).getLayoutInflater();
            v = vi.inflate(layout,parent,false);
            holder = new Holder();
            holder.judul = (TextView) v.findViewById(R.id.nama_industri);
            holder.alamat = (TextView) v.findViewById(R.id.alamat_industri);
            holder.telp = (TextView) v.findViewById(R.id.telp_industri);
            holder.img = (ImageView) v.findViewById(R.id.adminIndustriFoto);
            v.setTag(holder);
        }
        else {
            holder = (Holder) v.getTag();
        }
        tb_industri industri = listIndustri.get(position);
        holder.judul.setText(industri.getNama());
        holder.alamat.setText(industri.getAlamat());
        holder.telp.setText(industri.getNo_telp());
        String fotoUrl = industri.getLogo();
        Picasso.with(getContext()).load(fotoUrl).into(holder.img);
        return  v;
    }
    static class Holder{
        TextView judul;
        TextView alamat;
        TextView telp;
        ImageView img;
    }
}
