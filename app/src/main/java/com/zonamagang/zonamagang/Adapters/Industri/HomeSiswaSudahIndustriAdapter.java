package com.zonamagang.zonamagang.Adapters.Industri;

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
 * Created by Skensa Tech on 22/8/2016.
 */
public class HomeSiswaSudahIndustriAdapter extends ArrayAdapter<CustomIndustri> {

    int id_industri,id_siswa,mYear,mMonth,mDay;
    int tahun_awal,bulan_awal,hari_awal;
    int tahun_akhir,bulan_akhir,hari_akhir;
    CustomIndustri industriNow;
    ProgressDialog progressDialog;
    int lastIdMagang,id_industri_now;

    public HomeSiswaSudahIndustriAdapter(Activity context, ArrayList<CustomIndustri> industri){super(context,0,industri);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.siswa_layout_sudah_item,parent,false);
        }

        industriNow = getItem(position);

        TextView namaIndustri = (TextView) listItemView.findViewById(R.id.namaIndustriSudah);
        namaIndustri.setText(industriNow.getNama());

        TextView alamatIndustri = (TextView) listItemView.findViewById(R.id.alamatIndustriSudah);
        alamatIndustri.setText(industriNow.getAlamat());

        TextView telpIndustri = (TextView) listItemView.findViewById(R.id.telpIndustriSudah);
        telpIndustri.setText(industriNow.getNo_telp());

        TextView emailIndustri = (TextView) listItemView.findViewById(R.id.emailIndustriSudah);
        emailIndustri.setText(industriNow.getEmail());

        TextView kuotaIndustri = (TextView) listItemView.findViewById(R.id.kuotaIndustriSudah);
        kuotaIndustri.setText("Kuota : "+industriNow.getKuota()+" orang");

        ImageView logo = (ImageView) listItemView.findViewById(R.id.gambarSiswaSudah);
        String logoUrl = industriNow.getLogo();
        Picasso.with(getContext()).load(logoUrl).into(logo);

        Button siswa_layout_belum_btnDaftar = (Button) listItemView.findViewById(R.id.siswa_layout_sudah_btnBatal);
        siswa_layout_belum_btnDaftar.setTag(position);

        siswa_layout_belum_btnDaftar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                int position = (Integer) arg0.getTag();
                industriNow = getItem(position);
                id_industri_now = industriNow.getId_industri();

                //DIALOG BOX
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah anda yakin batal mendaftar di industri ini ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog = new ProgressDialog(getContext()); // this = YourActivity
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setMessage("Membatalkan pendaftaran..");
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
                //END DIALOG BOX
            }
        });

        return listItemView;

    }

    public void hapusDiTbMagang(){


    }

}
