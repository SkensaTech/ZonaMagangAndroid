package com.zonamagang.zonamagang.Adapters.Siswa;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
import com.zonamagang.zonamagang.LoadingActivity;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_notif;
import com.zonamagang.zonamagang.Model.tb_siswa;
import com.zonamagang.zonamagang.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

                                        BackendlessUser userNow = Backendless.UserService.CurrentUser();
                                        String id_user_now = userNow.getProperty("id_user").toString();
                                        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                                        dataQuery.setWhereClause("id_user = "+id_user_now);
                                        Backendless.Persistence.of(tb_industri.class).find( dataQuery, new AsyncCallback<BackendlessCollection<tb_industri>>(){
                                            @Override
                                            public void handleResponse( BackendlessCollection<tb_industri> hasil )
                                            {
                                                List<tb_industri> firstPage = hasil.getCurrentPage();

                                                Iterator<tb_industri> iterator = firstPage.iterator();

                                                while( iterator.hasNext() )
                                                {
                                                    tb_industri industriInfo = iterator.next();
                                                    id_industri = industriInfo.getId_industri();

                                                    BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                                                    dataQueryTbMagang.setWhereClause("id_siswa = "+id_siswa_now+" AND id_industri = "+id_industri);
                                                    Backendless.Persistence.of(tb_magang.class).find( dataQueryTbMagang, new AsyncCallback<BackendlessCollection<tb_magang>>(){
                                                        @Override
                                                        public void handleResponse( BackendlessCollection<tb_magang> hasil )
                                                        {
                                                            List<tb_magang> firstPageTbMagang = hasil.getCurrentPage();
                                                                    firstPageTbMagang.get(0).setStatus_diterima(2);

                                                                    Backendless.Persistence.save( firstPageTbMagang.get(0), new AsyncCallback<tb_magang>() {
                                                                        @Override
                                                                        public void handleResponse( tb_magang response )
                                                                        {
                                                                            // Contact instance has been updated

                                                                            //Notif Development
                                                                            Backendless.Persistence.of( tb_notif.class).findLast( new AsyncCallback<tb_notif>(){
                                                                                @Override
                                                                                public void handleResponse( tb_notif lastTbNotif )
                                                                                {
                                                                                    lastIdNotif = lastTbNotif.getId_notif() + 1;

                                                                                    id_user_siswa_now = 0;

                                                                                    //CARI ID USER SISWA

                                                                                    BackendlessDataQuery dataQueryTbSsiswa = new BackendlessDataQuery();
                                                                                    dataQueryTbSsiswa.setWhereClause("id_siswa = "+id_siswa_now);

                                                                                    Backendless.Persistence.of( tb_siswa.class ).find( dataQueryTbSsiswa,
                                                                                            new AsyncCallback<BackendlessCollection<tb_siswa>>(){
                                                                                                @Override
                                                                                                public void handleResponse( BackendlessCollection<tb_siswa> foundSiswa )
                                                                                                {
                                                                                                    List<tb_siswa> firstPageTbSiswa = foundSiswa.getCurrentPage();
                                                                                                    id_user_siswa_now = firstPageTbSiswa.get(0).getId_user();

                                                                                                    //SAVE NOTIFIKASI
                                                                                                    tb_notif notif = new tb_notif();
                                                                                                    notif.setId_penerima(id_user_siswa_now);
                                                                                                    Log.e("HomeIndustriBaruAdapter","id user siswa = "+id_user_siswa_now);
                                                                                                    notif.setId_pengirim( id_industri );
                                                                                                    notif.setId_notif(lastIdNotif);
                                                                                                    notif.setIsi_notif("Selamat ! anda diterima magang pada industri tersebut. Silahkan datang ke industri ini pada tanggal "+hari+"-"+bulan+"-"+tahun);

                                                                                                    // save object asynchronously
                                                                                                    Backendless.Persistence.save( notif, new AsyncCallback<tb_notif>() {
                                                                                                        public void handleResponse( tb_notif response )
                                                                                                        {
                                                                                                            // new Contact instance has been saved
                                                                                                            progressDialog.hide();
                                                                                                            new AlertDialog.Builder(getContext())
                                                                                                                    .setTitle("Tanggal interview telah ditentukan !")
                                                                                                                    .setMessage("Notifikasi tentang tanggal interview telah dikirim ke siswa tersebut.")
                                                                                                                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                                                                                                        @Override
                                                                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                                                                            Intent loadingIntent = new Intent(getContext(), LoadingActivity.class);
                                                                                                                            String gotoWhere = "HomeIndustri";
                                                                                                                            loadingIntent.putExtra("tujuan",gotoWhere);
                                                                                                                            getContext().startActivity(loadingIntent);
                                                                                                                            dialog.dismiss();
                                                                                                                        }
                                                                                                                    })
                                                                                                                    .show();
                                                                                                        }

                                                                                                        public void handleFault( BackendlessFault fault )
                                                                                                        {
                                                                                                            // an error has occurred, the error code can be retrieved with fault.getCode()
                                                                                                        }
                                                                                                    });
                                                                                                    //END SAVE NOTIFIKASI

                                                                                                }
                                                                                                @Override
                                                                                                public void handleFault( BackendlessFault fault )
                                                                                                {
                                                                                                    Log.e("HomeIndustriBaruAdapter",fault.getMessage());
                                                                                                }
                                                                                            });

                                                                                    //END CARI ID USER SISWA

                                                                                }
                                                                                @Override
                                                                                public void handleFault( BackendlessFault fault )
                                                                                {
                                                                                    lastIdNotif = 1;
                                                                                    id_user_siswa_now = 0;

                                                                                    //CARI ID USER SISWA

                                                                                    BackendlessDataQuery dataQueryTbSsiswa = new BackendlessDataQuery();
                                                                                    dataQueryTbSsiswa.setWhereClause("id_siswa = "+id_siswa_now);

                                                                                    Backendless.Persistence.of( tb_siswa.class ).find( dataQueryTbSsiswa,
                                                                                            new AsyncCallback<BackendlessCollection<tb_siswa>>(){
                                                                                                @Override
                                                                                                public void handleResponse( BackendlessCollection<tb_siswa> foundSiswa )
                                                                                                {
                                                                                                    List<tb_siswa> firstPageTbSiswa = foundSiswa.getCurrentPage();
                                                                                                    id_user_siswa_now = firstPageTbSiswa.get(0).getId_user();

                                                                                                    //SAVE NOTIFIKASI
                                                                                                    tb_notif notif = new tb_notif();
                                                                                                    notif.setId_penerima(id_user_siswa_now);
                                                                                                    Log.e("HomeIndustriBaruAdapter","id user siswa = "+id_user_siswa_now);
                                                                                                    notif.setId_pengirim( id_industri );
                                                                                                    notif.setId_notif(lastIdNotif);
                                                                                                    notif.setIsi_notif("Selamat ! anda diterima magang pada industri tersebut. Silahkan datang ke industri ini pada tanggal "+hari+"-"+bulan+"-"+tahun);

                                                                                                    // save object asynchronously
                                                                                                    Backendless.Persistence.save( notif, new AsyncCallback<tb_notif>() {
                                                                                                        public void handleResponse( tb_notif response )
                                                                                                        {
                                                                                                            // new Contact instance has been saved
                                                                                                            progressDialog.hide();
                                                                                                            new AlertDialog.Builder(getContext())
                                                                                                                    .setTitle("Tanggal interview telah ditentukan !")
                                                                                                                    .setMessage("Notifikasi tentang tanggal interview telah dikirim ke siswa tersebut.")
                                                                                                                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                                                                                                        @Override
                                                                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                                                                            Intent loadingIntent = new Intent(getContext(), LoadingActivity.class);
                                                                                                                            String gotoWhere = "HomeIndustri";
                                                                                                                            loadingIntent.putExtra("tujuan",gotoWhere);
                                                                                                                            getContext().startActivity(loadingIntent);
                                                                                                                            dialog.dismiss();
                                                                                                                        }
                                                                                                                    })
                                                                                                                    .show();
                                                                                                        }

                                                                                                        public void handleFault( BackendlessFault fault )
                                                                                                        {
                                                                                                            // an error has occurred, the error code can be retrieved with fault.getCode()
                                                                                                        }
                                                                                                    });
                                                                                                    //END SAVE NOTIFIKASI

                                                                                                }
                                                                                                @Override
                                                                                                public void handleFault( BackendlessFault fault )
                                                                                                {
                                                                                                    Log.e("HomeIndustriBaruAdapter",fault.getMessage());
                                                                                                }
                                                                                            });

                                                                                    //END CARI ID USER SISWA

                                                                                }
                                                                            });

                                                                            //Notif Development END

                                                                        }
                                                                        @Override
                                                                        public void handleFault( BackendlessFault fault )
                                                                        {
                                                                            // an error has occurred, the error code can be retrieved with fault.getCode()
                                                                            Toast.makeText(getContext(),"Gagal save TbMagang ! "+fault.getMessage(),Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } );
                                                        }
                                                        @Override
                                                        public void handleFault( BackendlessFault fault )
                                                        {
                                                            Toast.makeText(getContext(),"Error ! "+fault.getMessage(),Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                            }
                                            @Override
                                            public void handleFault( BackendlessFault fault )
                                            {
                                                Toast.makeText(getContext(),"Error cari tb magang ! "+fault.getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        });

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
