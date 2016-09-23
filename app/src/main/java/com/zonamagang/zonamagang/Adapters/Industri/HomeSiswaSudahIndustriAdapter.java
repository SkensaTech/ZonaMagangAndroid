package com.zonamagang.zonamagang.Adapters.Industri;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.LoadingActivity;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_siswa;
import com.zonamagang.zonamagang.R;
import com.zonamagang.zonamagang.detail_industri;
import com.zonamagang.zonamagang.dialogInterviewFragment;
import com.zonamagang.zonamagang.home_siswa_1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

        TextView pimpinan = (TextView) listItemView.findViewById(R.id.pimpinan);
        pimpinan.setText(industriNow.getPimpinan());

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

                                    HomeSiswaSudahIndustriAdapter.this.hapusDiTbMagang();

                                }
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                Toast.makeText(getContext(),"Error ! "+fault.getMessage(),Toast.LENGTH_SHORT).show();
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
                //END DIALOG BOX
            }
        });

        return listItemView;

    }

    public void hapusDiTbMagang(){



        String whereClause = "id_industri = "+id_industri_now+" AND id_siswa = "+id_siswa;
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause( whereClause );
        Backendless.Persistence.of( tb_magang.class ).find( dataQuery,
                new AsyncCallback<BackendlessCollection<tb_magang>>(){
                    @Override
                    public void handleResponse( BackendlessCollection<tb_magang> foundTbMagang )
                    {
                        List<tb_magang> firstPageTbMagang = foundTbMagang.getCurrentPage();
                        Backendless.Persistence.of( tb_magang.class ).remove( firstPageTbMagang.get(0),
                                new AsyncCallback<Long>()
                                {
                                    public void handleResponse( Long response )
                                    {
                                        progressDialog.hide();
                                        new AlertDialog.Builder(getContext())
                                                .setTitle("Pendaftaran dibatalkan,")
                                                .setMessage("Anda telah membatalkan pendaftaran ke industri tersebut.")
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
                                        Toast.makeText(getContext(),"Error delete tbmagang, "+fault.getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                } );
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(getContext(),"Error find tbmagang, "+fault.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

    }

}
