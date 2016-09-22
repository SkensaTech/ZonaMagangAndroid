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
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
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
public class baruFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Button button;
    ArrayList<HomeIndustriSiswaCustom> listSiswa;
    View rootView;
    int id_siswa,id_industri;
    String id_user_now;
    SwipeRefreshLayout swipeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.baru, container, false);
        new listingSiswa().execute();

        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(this);

        return rootView;
    }

    @Override
    public void onRefresh() {
        new listingSiswa().execute();
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

                try{
                    String whereClause = "id_user = " + id_user_now;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(whereClause);
                    dataQuery.setPageSize(80);
                    BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find(dataQuery);
                    List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
                    id_industri = firstPageIndustri.get(0).getId_industri();
                    Log.e("baruFragment","id_industri = "+id_industri);
                }catch(Exception exception){
                    Log.e("baruFragment","id industri tidak ditemukan ! = "+exception.getMessage());
                }

                try{
                    BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                    dataQueryTbMagang.setWhereClause("id_industri = " +id_industri+" AND status_diterima = 1");
                    dataQueryTbMagang.setPageSize(80);
                    BackendlessCollection<tb_magang> magangInfo = Backendless.Persistence.of(tb_magang.class).find(dataQueryTbMagang);
                    List<tb_magang> firstPageMagang = magangInfo.getCurrentPage();
                        for(int i = 0; i < firstPageMagang.size(); i++){
                            id_siswa = firstPageMagang.get(i).getId_siswa();
                            Log.e("baruFragment","id_siswa = "+id_siswa);

                            try {
                                BackendlessDataQuery dataQuerySiswa = new BackendlessDataQuery();
                                dataQuerySiswa.setWhereClause("id_siswa = " + id_siswa);
                                dataQuerySiswa.setPageSize(80);
                                BackendlessCollection<tb_siswa> siswaInfo = Backendless.Persistence.of(tb_siswa.class).find(dataQuerySiswa);
                                List<tb_siswa> firstPageSiswa = siswaInfo.getCurrentPage();
                                int id_siswa = firstPageSiswa.get(0).getId_siswa();
                                int id_sekolah = firstPageSiswa.get(0).getId_sekolah();

                                String nama_sekolah = "";
                                try {
                                    //cari sekolah
                                    BackendlessDataQuery dataQuerySekolah = new BackendlessDataQuery();
                                    dataQuerySekolah.setWhereClause("id_sekolah = " + id_sekolah);
                                    dataQuerySekolah.setPageSize(80);
                                    BackendlessCollection<tb_sekolah> sekolahInfo = Backendless.Persistence.of(tb_sekolah.class).find(dataQuerySekolah);
                                    List<tb_sekolah> firstPageSekolah = sekolahInfo.getCurrentPage();
                                    nama_sekolah = firstPageSekolah.get(0).getNama();
                                    //end cari sekolah
                                } catch (Exception exception) {
                                    Log.e("baruFragment", "Cari sekolah gagal ! = " + exception.getMessage());
                                }

                                int id_bidang = firstPageSiswa.get(0).getId_bidang();

                                String bidang = "";
                                try {
                                    //cari bidang
                                    BackendlessDataQuery dataQueryBidang = new BackendlessDataQuery();
                                    dataQueryBidang.setWhereClause("id_bidang = " + id_bidang);
                                    dataQueryBidang.setPageSize(80);
                                    BackendlessCollection<tb_bidang> bidangInfo = Backendless.Persistence.of(tb_bidang.class).find(dataQueryBidang);
                                    List<tb_bidang> firstPageBidang = bidangInfo.getCurrentPage();
                                    bidang = firstPageBidang.get(0).getNama();
                                    //end cari bidang
                                } catch (Exception exception) {
                                    Log.e("baruFragment", "Cari bidang gagal ! = " + exception.getMessage());
                                }

                                String nama_siswa = firstPageSiswa.get(0).getNama();
                                String foto = firstPageSiswa.get(0).getFoto();

                                baruFragment.this.listSiswa.add(
                                        new HomeIndustriSiswaCustom(id_siswa, nama_sekolah, nama_siswa, bidang, foto)
                                );
                            }catch(Exception exception){
                                Log.e("baruFragment","tidak ada di id_siswa");
                            }

                        }
                }catch(Exception exception){
                    Log.e("baruFragment","tidak ada di tb_magang ! = "+exception.getMessage());
                }

            }
            catch(Exception exception){
                Log.e("baruFragment","Terjadi Error ! = "+exception.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) rootView.findViewById(R.id.baru_listView);

            HomeIndustriBaruAdapter adapter = new HomeIndustriBaruAdapter(getActivity(), listSiswa);

            listView.setAdapter(adapter);
//            getActivity().findViewById(R.id.home_industri_progressBar).setVisibility(View.GONE);
            swipeLayout.setRefreshing(false);
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
