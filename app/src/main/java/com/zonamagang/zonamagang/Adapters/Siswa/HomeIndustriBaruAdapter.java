package com.zonamagang.zonamagang.Adapters.Siswa;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Skensa Tech on 25/8/2016.
 */
public class HomeIndustriBaruAdapter extends ArrayAdapter<HomeIndustriSiswaCustom> {

    HomeIndustriSiswaCustom siswaNow;
    int id_siswa_now,id_user_siswa_now;
    int mYear,mMonth,mDay;
    int tahun,bulan,hari;
    int id_industri;
    int lastIdNotif;
    ProgressDialog progressDialog;

    public HomeIndustriBaruAdapter(Activity context, ArrayList<HomeIndustriSiswaCustom> industri){super(context,0,industri);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.baru_item,parent,false);
        }

        siswaNow = getItem(position);

        TextView namaSiswa = (TextView)listItemView.findViewById(R.id.baruItemNama);
        namaSiswa.setText(siswaNow.getNama_siswa());

        TextView namaSekolah = (TextView)listItemView.findViewById(R.id.baruItemSekolah);
        namaSekolah.setText(siswaNow.getNama_sekolah());

        TextView bidang = (TextView)listItemView.findViewById(R.id.baruItemBidang);
        bidang.setText(siswaNow.getBidang());

        ImageView foto = (ImageView)listItemView.findViewById(R.id.baruItemFoto);
        String fotoUrl = siswaNow.getFoto();
        Picasso.with(getContext()).load(fotoUrl).into(foto);

        Button btnTentukan = (Button)listItemView.findViewById(R.id.baruItemBtnTentukan);
        btnTentukan.setTag(position);

        btnTentukan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();
                siswaNow = getItem(position);
                id_siswa_now = siswaNow.getId_siswa();

                //START KONFIRMASI DIALOG BOX

                //DIALOG BOX
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah anda yakin ingin menentukan tanggal interview untuk siswa ini ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dpd = new DatePickerDialog(getContext(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        tahun = year;bulan = monthOfYear;hari=dayOfMonth;

                                        progressDialog = new ProgressDialog(getContext()); // this = YourActivity
                                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                        progressDialog.setMessage("Menentukan tanggal interview....");
                                        progressDialog.setIndeterminate(true);
                                        progressDialog.setCanceledOnTouchOutside(false);
                                        progressDialog.show();


                                    }
                                }, mYear, mMonth, mDay);
                        dpd.setTitle("Tentukan tanggal interview untuk siswa tersebut.");
                        dpd.show();

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Penentuan tanggal interview dibatalkan.",Toast.LENGTH_SHORT).show();
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
