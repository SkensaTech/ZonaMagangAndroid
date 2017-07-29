package com.zonamagang.zonamagang.Adapters.Siswa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
 * Created by Skensa Tech on 25/8/2016.
 */
public class HomeIndustriSudahAdapter extends ArrayAdapter<HomeIndustriSiswaCustom> {

    HomeIndustriSiswaCustom siswaNow;
    int id_siswa_now;
    int id_industri;
    ProgressDialog progressDialog;

    public HomeIndustriSudahAdapter(Activity context, ArrayList<HomeIndustriSiswaCustom> industri){super(context,0,industri);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.sudah_item,parent,false);
        }

        siswaNow = getItem(position);

        TextView namaSiswa = (TextView)listItemView.findViewById(R.id.sudahItemNama);
        namaSiswa.setText(siswaNow.getNama_siswa());

        TextView namaSekolah = (TextView)listItemView.findViewById(R.id.sudahItemSekolah);
        namaSekolah.setText(siswaNow.getNama_sekolah());

        TextView bidang = (TextView)listItemView.findViewById(R.id.sudahItemBidang);
        bidang.setText(siswaNow.getBidang());

        ImageView foto = (ImageView)listItemView.findViewById(R.id.sudahItemFoto);
        String fotoUrl = siswaNow.getFoto();
        Picasso.with(getContext()).load(fotoUrl).into(foto);

        Button btnTerima = (Button)listItemView.findViewById(R.id.sudahItemTerima);
        btnTerima.setTag(position);

        Button btnTolak = (Button)listItemView.findViewById(R.id.sudahItemTolak);
        btnTolak.setTag(position);

        btnTolak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();
                siswaNow = getItem(position);
                id_siswa_now = siswaNow.getId_siswa();

                //START KONFIRMASI DIALOG BOX

                //DIALOG BOX
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah anda yakin menolak siswa ini ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog = new ProgressDialog(getContext()); // this = YourActivity
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setMessage("Menolak siswa tersebut untuk magang di industri anda...");
                        progressDialog.setIndeterminate(true);
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();


                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                //END KONFIRMASI DIALOG BOX

            }
        });

        btnTerima.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();
                siswaNow = getItem(position);
                id_siswa_now = siswaNow.getId_siswa();

                //START KONFIRMASI DIALOG BOX

                //DIALOG BOX
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah anda yakin menerima siswa ini ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog = new ProgressDialog(getContext()); // this = YourActivity
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setMessage("Menerima siswa ke industri anda...");
                        progressDialog.setIndeterminate(true);
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();


                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                //END KONFIRMASI DIALOG BOX

            }
        });


        return listItemView;
    }
}
