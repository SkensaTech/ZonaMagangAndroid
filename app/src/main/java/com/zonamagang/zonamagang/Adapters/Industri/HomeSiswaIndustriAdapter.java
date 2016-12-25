package com.zonamagang.zonamagang.Adapters.Industri;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.zonamagang.zonamagang.Model.magang;
import com.zonamagang.zonamagang.R;
import com.zonamagang.zonamagang.detail_industri;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Skensa Tech on 22/8/2016.
 */
public class HomeSiswaIndustriAdapter extends ArrayAdapter<CustomIndustri> {

    int id_industri,id_siswa,mYear,mMonth,mDay;
    int tahun_awal,bulan_awal,hari_awal;
    int tahun_akhir,bulan_akhir,hari_akhir;
    CustomIndustri industriNow;
    ProgressDialog progressDialog;
    int lastIdMagang,id_industri_now,lastIdNotif;

    public HomeSiswaIndustriAdapter(Activity context, ArrayList<CustomIndustri> industri){super(context,0,industri);}

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
                builder.setMessage("Apakah anda yakin mendaftar di industri ini ?");

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

                                        tahun_awal = year;bulan_awal=monthOfYear;hari_awal=dayOfMonth;

                                        final Calendar d = Calendar.getInstance();
                                        mYear = d.get(Calendar.YEAR);
                                        mMonth = d.get(Calendar.MONTH);
                                        mDay = d.get(Calendar.DAY_OF_MONTH);

                                        DatePickerDialog dpdAkhir = new DatePickerDialog(getContext(),
                                                new DatePickerDialog.OnDateSetListener() {

                                                    @Override
                                                    public void onDateSet(DatePicker view, int year,
                                                                          int monthOfYear, int dayOfMonth) {

                                                        tahun_akhir = year;bulan_akhir=monthOfYear;hari_akhir=dayOfMonth;

                                                        progressDialog = new ProgressDialog(getContext()); // this = YourActivity
                                                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                                        progressDialog.setMessage("Mendaftarkan anda ke industri...");
                                                        progressDialog.setIndeterminate(true);
                                                        progressDialog.setCanceledOnTouchOutside(false);
                                                        progressDialog.show();


                                                    }
                                                }, mYear, mMonth, mDay);
                                        dpdAkhir.setTitle("Tentukan tanggal AKHIR magang.");
                                        dpdAkhir.show();

                                    }
                                }, mYear, mMonth, mDay);
                        dpd.setTitle("Tentukan tanggal AWAL magang.");
                        dpd.show();

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Pendaftaran dibatalkan.",Toast.LENGTH_SHORT).show();
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                //END DIALOG BOX
            }
        });

        Button siswa_layout_belum_btnDetail = (Button) listItemView.findViewById(R.id.siswa_layout_belum_btnDetail);
        siswa_layout_belum_btnDetail.setTag(position);

        siswa_layout_belum_btnDetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub.

                int position = (Integer) arg0.getTag();

                industriNow = getItem(position);
                id_industri_now = industriNow.getId_industri();
                String id_industri_string = Integer.toString(id_industri_now);

                Intent detailIndustriIntent = new Intent(getContext(), detail_industri.class);
                detailIndustriIntent.putExtra("id_industri",id_industri_string);
                getContext().startActivity(detailIndustriIntent);

            }
        });

        return listItemView;

    }

    public void saveTbMagang(){
        // last contact instance has been found
        //INSERT TO TBMAGANG
        magang tbmagang = new magang();
//        tbmagang.setStatus_diterima(1);
//        tbmagang.setId_industri(id_industri_now);
//        tbmagang.setId_siswa(id_siswa);
//        tbmagang.setId_magang(lastIdMagang);
//        tbmagang.setAwal_magang(hari_awal+"-"+bulan_awal+"-"+tahun_awal);
//        tbmagang.setAkhir_magang(hari_akhir+"-"+bulan_akhir+"-"+tahun_akhir);

        // save object asynchronously
    }

}
