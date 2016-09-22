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

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.LoadingActivity;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_notif;
import com.zonamagang.zonamagang.Model.tb_siswa;
import com.zonamagang.zonamagang.R;
import com.zonamagang.zonamagang.detail_industri;
import com.zonamagang.zonamagang.home_siswa_1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

                                                        BackendlessUser userNow = Backendless.UserService.CurrentUser();
                                                        String id_user_now = userNow.getProperty("id_user").toString();
                                                        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                                                        dataQuery.setWhereClause("id_user = "+id_user_now);
                                                        Backendless.Persistence.of(tb_siswa.class).find( dataQuery, new AsyncCallback<BackendlessCollection<tb_siswa>>(){
                                                            @Override
                                                            public void handleResponse( BackendlessCollection<tb_siswa> hasil )
                                                            {
                                                                List<tb_siswa> firstPage = hasil.getCurrentPage();

                                                                Iterator<tb_siswa> iterator = firstPage.iterator();

                                                                while( iterator.hasNext() )
                                                                {
                                                                    tb_siswa siswaInfo = iterator.next();
                                                                    id_siswa = siswaInfo.getId_siswa();

                                                                    Backendless.Persistence.of( tb_magang.class).findLast( new AsyncCallback<tb_magang>(){
                                                                        @Override
                                                                        public void handleResponse( tb_magang lastTbMagang )
                                                                        {
                                                                            lastIdMagang = lastTbMagang.getId_magang() + 1;
                                                                            HomeSiswaIndustriAdapter.this.saveTbMagang();
                                                                        }
                                                                        @Override
                                                                        public void handleFault( BackendlessFault fault )
                                                                        {
                                                                            lastIdMagang = 1;
                                                                            HomeSiswaIndustriAdapter.this.saveTbMagang();
                                                                        }
                                                                    });

                                                                }
                                                            }
                                                            @Override
                                                            public void handleFault( BackendlessFault fault )
                                                            {
                                                                Toast.makeText(getContext(),"Error ! "+fault.getMessage(),Toast.LENGTH_SHORT).show();
                                                            }
                                                        });

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
        tb_magang tbmagang = new tb_magang();
        tbmagang.setStatus_diterima(1);
        tbmagang.setId_industri(id_industri_now);
        tbmagang.setId_siswa(id_siswa);
        tbmagang.setId_magang(lastIdMagang);
        tbmagang.setAwal_magang(hari_awal+"-"+bulan_awal+"-"+tahun_awal);
        tbmagang.setAkhir_magang(hari_akhir+"-"+bulan_akhir+"-"+tahun_akhir);

        // save object asynchronously
        Backendless.Persistence.save( tbmagang, new AsyncCallback<tb_magang>() {
            public void handleResponse( tb_magang response )
            {
                // new Contact instance has been saved
                progressDialog.hide();
                new AlertDialog.Builder(getContext())
                        .setTitle("Anda telah mendaftar !")
                        .setMessage("Tunggu notifikasi untuk mengetahui anda diterima atau tidak pada industri tersebut.")
                        .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent loadingIntent = new Intent(getContext(), LoadingActivity.class);
                                String gotoWhere = "home_siswa_1";
                                loadingIntent.putExtra("tujuan",gotoWhere);
                                getContext().startActivity(loadingIntent);
                                dialog.dismiss();
                            }
                        })
                        .show();
                Intent detailIndustriIntent = new Intent(getContext(), home_siswa_1.class);
                getContext().startActivity(detailIndustriIntent);
            }

            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Toast.makeText(getContext(),"Gagal saveTbMagang = "+fault.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
