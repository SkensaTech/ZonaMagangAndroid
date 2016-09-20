package com.zonamagang.zonamagang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessException;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
import com.zonamagang.zonamagang.Adapters.Industri.HomeSiswaIndustriAdapter;
import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriBaruAdapter;
import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriSiswaCustom;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skensa Tech on 16/08/2016.
 */
public class baruFragment extends Fragment{

    private Button button;
    ArrayList<HomeIndustriSiswaCustom> listSiswa;
    View rootView;
    int id_siswa,id_industri;
    String id_user_now;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.baru, container, false);

        new listingSiswa().execute();

        return rootView;
    }

    private class listingSiswa extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getActivity().findViewById(R.id.home_industri_progressBar).setVisibility(View.VISIBLE);

            BackendlessUser userNow = Backendless.UserService.CurrentUser();
            id_user_now = userNow.getProperty("id_user").toString();
            listSiswa = new ArrayList<HomeIndustriSiswaCustom>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                String whereClause = "id_user = " + id_user_now;
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);
                BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find(dataQuery);
                List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
                id_industri = firstPageIndustri.get(0).getId_industri();

                BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                dataQueryTbMagang.setWhereClause("id_industri. = " +id_industri+" AND status_diterima = 1");
                BackendlessCollection<tb_magang> magangInfo = Backendless.Persistence.of(tb_magang.class).find(dataQuery);
                List<tb_magang> firstPageMagang = magangInfo.getCurrentPage();
                if(firstPageMagang.size() > 0){
                    for(int i = 0; i < firstPageMagang.size(); i++){
                        firstPageMagang.get(i).getId_siswa();

                        Toast.makeText(getContext(),"loop magang",Toast.LENGTH_SHORT).show();

                        BackendlessDataQuery dataQuerySiswa= new BackendlessDataQuery();
                        dataQuerySiswa.setWhereClause("id_siswa = "+id_siswa);
                        BackendlessCollection<tb_siswa> siswaInfo = Backendless.Persistence.of(tb_siswa.class).find(dataQuery);
                        List<tb_siswa> firstPageSiswa = siswaInfo.getCurrentPage();
                        int id_siswa = firstPageSiswa.get(0).getId_siswa();
                        int id_sekolah = firstPageSiswa.get(0).getId_sekolah();

                        //cari sekolah
                        BackendlessDataQuery dataQuerySekolah= new BackendlessDataQuery();
                        dataQuerySekolah.setWhereClause("id_sekolah = "+id_sekolah);
                        BackendlessCollection<tb_sekolah> sekolahInfo = Backendless.Persistence.of(tb_sekolah.class).find(dataQuery);
                        List<tb_sekolah> firstPageSekolah = sekolahInfo.getCurrentPage();
                        String nama_sekolah = firstPageSekolah.get(0).getNama();
                        //end cari sekolah

                        int id_bidang = firstPageSiswa.get(0).getId_bidang();

                        //cari bidang
                        BackendlessDataQuery dataQueryBidang= new BackendlessDataQuery();
                        dataQueryBidang.setWhereClause("id_bidang = "+id_bidang);
                        BackendlessCollection<tb_bidang> bidangInfo = Backendless.Persistence.of(tb_bidang.class).find(dataQuery);
                        List<tb_bidang> firstPageBidang = bidangInfo.getCurrentPage();
                        String bidang = firstPageBidang.get(0).getNama();
                        //end cari bidang

                        String nama_siswa = firstPageSiswa.get(0).getNama();
                        String foto = firstPageSiswa.get(0).getFoto();

                        baruFragment.this.listSiswa.add(
                                new HomeIndustriSiswaCustom(id_siswa, nama_sekolah, nama_siswa, bidang, foto)
                        );



                    }
                }
                else{
                    Toast.makeText(getContext(),"Belum ada siswa yang mendaftar",Toast.LENGTH_SHORT).show();
                }

            }
            catch(Exception exception){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) rootView.findViewById(R.id.baru_listView);

            HomeIndustriBaruAdapter adapter = new HomeIndustriBaruAdapter(getActivity(), listSiswa);

            listView.setAdapter(adapter);
            getActivity().findViewById(R.id.home_industri_progressBar).setVisibility(View.GONE);
        }
    }

    public static baruFragment newInstance(int text, String title){
        baruFragment f = new baruFragment();
        Bundle b = new Bundle();
        b.putString("Baru",title);

        f.setArguments(b);

        return f;
    }
}
