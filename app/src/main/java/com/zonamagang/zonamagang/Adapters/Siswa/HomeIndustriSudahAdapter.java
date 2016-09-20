package com.zonamagang.zonamagang.Adapters.Siswa;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.zonamagang.zonamagang.home_siswa_1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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
                                            Backendless.Persistence.of( tb_magang.class ).remove( firstPageTbMagang.get(0),
                                                    new AsyncCallback<Long>()
                                                    {
                                                        public void handleResponse( Long response )
                                                        {
                                                            progressDialog.hide();
                                                            new AlertDialog.Builder(getContext())
                                                                    .setTitle("Siswa Ditolak.")
                                                                    .setMessage("Anda telah menolak siswa tersebut untuk magang di industri anda.")
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
                                                            Intent detailIndustriIntent = new Intent(getContext(), home_siswa_1.class);
                                                            getContext().startActivity(detailIndustriIntent);
                                                        }
                                                        public void handleFault( BackendlessFault fault )
                                                        {
                                                            Toast.makeText(getContext(),"Error delete tbmagang, "+fault.getMessage(),Toast.LENGTH_LONG).show();
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
                                            firstPageTbMagang.get(0).setStatus_diterima(3);

                                            Backendless.Persistence.save( firstPageTbMagang.get(0), new AsyncCallback<tb_magang>() {
                                                @Override
                                                public void handleResponse( tb_magang response )
                                                {
                                                    // Contact instance has been updated
                                                    progressDialog.hide();
                                                    new AlertDialog.Builder(getContext())
                                                            .setTitle("Siswa diterima !")
                                                            .setMessage("Siswa tersebut telah diterima magang di industri anda.")
                                                            .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    Intent loadingIntent = new Intent(getContext(), LoadingActivity.class);
                                                                    String gotoWhere = "HomeIndustri";
                                                                    loadingIntent.putExtra("tujuan",gotoWhere);
                                                                    getContext().startActivity(loadingIntent);
                                                                    progressDialog.dismiss();
                                                                }
                                                            })
                                                            .show();
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
