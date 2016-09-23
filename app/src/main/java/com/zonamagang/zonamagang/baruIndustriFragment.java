package com.zonamagang.zonamagang;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
import com.zonamagang.zonamagang.Adapters.Industri.HomeSiswaIndustriAdapter;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_bidang_industri;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Skensa Tech on 16/08/2016.
 */
public class baruIndustriFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    ArrayList<CustomIndustri> listIndustri;
    View rootView;
    int id_siswa,id_industri;
    String bidangSearch, kotaSearch, provinsiSearch,searchvalue;
    String id_user_now;
    SwipeRefreshLayout swipeLayout;
    /** Fixed **/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_belum,container,false);
        home_siswa_1 activity = (home_siswa_1) getActivity();

        searchvalue = activity.getQuery();
        Log.e("baruIndustriFragment","searchvalue = "+searchvalue);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(this);

        if(((home_siswa_1) getActivity()).getBidangSearch() != null){
            bidangSearch = ((home_siswa_1) getActivity()).getBidangSearch();
            kotaSearch =  ((home_siswa_1) getActivity()).getKotaSearch();
            provinsiSearch =  ((home_siswa_1) getActivity()).getProvinsiSearch();
            Log.e("baruIndustriFragment","kondisi 1 executed");
            new filteredIndustri().execute();
        }else if(searchvalue != null && !searchvalue.equals("")){
            new searchedIndustri().execute();
            Log.e("baruIndustriFragment","kondisi 2 executed");
        }
        else{
            new industriAll().execute();
            Log.e("baruIndustriFragment","kondisi 3 executed");
        }

        return rootView;
    }
    @Override
    public void onRefresh() {
        new industriAll().execute();
    }
    private class filteredIndustri extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.VISIBLE);

            BackendlessUser userNow = Backendless.UserService.CurrentUser();
            id_user_now = userNow.getProperty("id_user").toString();
            listIndustri = new ArrayList<CustomIndustri>();
            Log.e("baruIndustriFragment","kota = "+kotaSearch+" || bidang = "+bidangSearch+" || provinsi = "+provinsiSearch);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String whereClause = "id_user = " + id_user_now;
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);
                dataQuery.setPageSize(80);
                BackendlessCollection<tb_siswa> siswaInfo = Backendless.Persistence.of(tb_siswa.class).find(dataQuery);
                List<tb_siswa> firstPageSiswa = siswaInfo.getCurrentPage();
                id_siswa = firstPageSiswa.get(0).getId_siswa();

                String whereClauseBidang = "nama = '" + bidangSearch+"'";
                BackendlessDataQuery dataQueryBidang = new BackendlessDataQuery();
                dataQueryBidang.setWhereClause(whereClauseBidang);
                BackendlessCollection<tb_bidang> bidangInfo = Backendless.Persistence.of(tb_bidang.class).find(dataQueryBidang);
                List<tb_bidang> firstPageBidang = bidangInfo.getCurrentPage();
                int id_bidang = firstPageBidang.get(0).getId_bidang();

                String whereClauseBidangIndustri = "id_bidang = " + id_bidang;
                BackendlessDataQuery dataQueryBidangIndustri = new BackendlessDataQuery();
                dataQueryBidangIndustri.setWhereClause(whereClauseBidangIndustri);
                BackendlessCollection<tb_bidang_industri> bidangIndustriInfo = Backendless.Persistence.of(tb_bidang_industri.class).find(dataQueryBidangIndustri);
                List<tb_bidang_industri> firstPageBidangIndustri = bidangIndustriInfo.getCurrentPage();
                for (int a = 0; a < firstPageBidangIndustri.size(); a++) {
                    BackendlessDataQuery dataQuery2 = new BackendlessDataQuery();
                    dataQuery2.setPageSize(80);
                    String searchQuery2 = "kota = '"+kotaSearch+"' AND provinsi = '"+provinsiSearch+"' AND id_industri = "+firstPageBidangIndustri.get(a).getId_industri();
                    dataQuery2.setWhereClause(searchQuery2);
                    Log.e("baruIndustriFragment","Search query 2 = "+searchQuery2);
                    BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find(dataQuery2);
                    List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
                    for (int i = 0; i < firstPageIndustri.size(); i++) {
                        int id_industri = firstPageIndustri.get(i).getId_industri();
                        String nama = firstPageIndustri.get(i).getNama();
                        String alamat = firstPageIndustri.get(i).getAlamat();
                        String no_telp = firstPageIndustri.get(i).getNo_telp();
                        String email = firstPageIndustri.get(i).getEmail();
                        String pimpinan = firstPageIndustri.get(i).getPimpinan();
                        int kuota = firstPageIndustri.get(i).getKuota();
                        String logo = firstPageIndustri.get(i).getLogo();

                        String whereClauseTbMagang = "id_siswa = " + id_siswa + " AND id_industri = " + id_industri;
                        BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                        dataQueryTbMagang.setWhereClause(whereClauseTbMagang);
                        dataQueryTbMagang.setPageSize(80);
                        try {
                            BackendlessCollection<tb_magang> tbMagangInfo = Backendless.Persistence.of(tb_magang.class).find(dataQueryTbMagang);
                            List<tb_magang> firstPageTbMagang = tbMagangInfo.getCurrentPage();
                            if(firstPageTbMagang.size() < 1){
                                baruIndustriFragment.this.listIndustri.add(
                                        new CustomIndustri(id_industri, nama, alamat, no_telp, email, kuota, logo, pimpinan)
                                );
                            }

                        } catch (BackendlessException be) {
                            Log.e("baruIndustriFragment","salah 1 = "+be.getMessage());
                        }

                    }
                }
            }
            catch(Exception exception){
                Log.e("baruIndustriFragment","ada error ! = "+exception.getMessage()+exception.getCause());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) rootView.findViewById(R.id.siswa_layout_belum_listView);

            HomeSiswaIndustriAdapter adapter = new HomeSiswaIndustriAdapter(getActivity(), listIndustri);

            listView.setAdapter(adapter);
            ((home_siswa_1) getActivity()).getCariMenu().setEnabled(true);
//            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);
        }
    }

    private class searchedIndustri extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.VISIBLE);

            BackendlessUser userNow = Backendless.UserService.CurrentUser();
            id_user_now = userNow.getProperty("id_user").toString();
            listIndustri = new ArrayList<CustomIndustri>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String whereClause = "id_user = " + id_user_now;
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);
                dataQuery.setPageSize(80);
                BackendlessCollection<tb_siswa> siswaInfo = Backendless.Persistence.of(tb_siswa.class).find(dataQuery);
                List<tb_siswa> firstPageSiswa = siswaInfo.getCurrentPage();
                id_siswa = firstPageSiswa.get(0).getId_siswa();

                BackendlessDataQuery dataQuery2 = new BackendlessDataQuery();
                dataQuery2.setPageSize(80);
                dataQuery2.setWhereClause("nama LIKE '%"+searchvalue+"%'");
                BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find(dataQuery2);
                List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
                for (int i = 0; i < firstPageIndustri.size(); i++) {
                    int id_industri = firstPageIndustri.get(i).getId_industri();
                    String nama = firstPageIndustri.get(i).getNama();
                    String alamat = firstPageIndustri.get(i).getAlamat();
                    String no_telp = firstPageIndustri.get(i).getNo_telp();
                    String email = firstPageIndustri.get(i).getEmail();
                    String pimpinan = firstPageIndustri.get(i).getPimpinan();
                    int kuota = firstPageIndustri.get(i).getKuota();
                    String logo = firstPageIndustri.get(i).getLogo();

                    String whereClauseTbMagang = "id_siswa = " + id_siswa + " AND id_industri = " + id_industri+"";
                    BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                    dataQueryTbMagang.setWhereClause(whereClauseTbMagang);
                    dataQueryTbMagang.setPageSize(80);
                    try {
                        BackendlessCollection<tb_magang> tbMagangInfo = Backendless.Persistence.of(tb_magang.class).find(dataQueryTbMagang);
                        List<tb_magang> firstPageTbMagang = tbMagangInfo.getCurrentPage();
                        if(firstPageTbMagang.size() < 1){
                            baruIndustriFragment.this.listIndustri.add(
                                    new CustomIndustri(id_industri, nama, alamat, no_telp, email, kuota, logo, pimpinan)
                            );
                        }

                    } catch (BackendlessException be) {

                    }

                }
            }
            catch(Exception exception){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) rootView.findViewById(R.id.siswa_layout_belum_listView);

            HomeSiswaIndustriAdapter adapter = new HomeSiswaIndustriAdapter(getActivity(), listIndustri);

            listView.setAdapter(adapter);
            swipeLayout.setRefreshing(false);
//            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);
        }
    }

    private class industriAll extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.VISIBLE);

            BackendlessUser userNow = Backendless.UserService.CurrentUser();
            id_user_now = userNow.getProperty("id_user").toString();
            listIndustri = new ArrayList<CustomIndustri>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String whereClause = "id_user = " + id_user_now;
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);
                dataQuery.setPageSize(80);
                BackendlessCollection<tb_siswa> siswaInfo = Backendless.Persistence.of(tb_siswa.class).find(dataQuery);
                List<tb_siswa> firstPageSiswa = siswaInfo.getCurrentPage();
                id_siswa = firstPageSiswa.get(0).getId_siswa();

                BackendlessDataQuery dataQuery2 = new BackendlessDataQuery();
                dataQuery2.setPageSize(80);
                BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find(dataQuery2);
                List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
                for (int i = 0; i < firstPageIndustri.size(); i++) {
                    int id_industri = firstPageIndustri.get(i).getId_industri();
                    String nama = firstPageIndustri.get(i).getNama();
                    String alamat = firstPageIndustri.get(i).getAlamat();
                    String no_telp = firstPageIndustri.get(i).getNo_telp();
                    String email = firstPageIndustri.get(i).getEmail();
                    String pimpinan = firstPageIndustri.get(i).getPimpinan();
                    int kuota = firstPageIndustri.get(i).getKuota();
                    String logo = firstPageIndustri.get(i).getLogo();

                    String whereClauseTbMagang = "id_siswa = " + id_siswa + " AND id_industri = " + id_industri;
                    BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                    dataQueryTbMagang.setWhereClause(whereClauseTbMagang);
                    dataQueryTbMagang.setPageSize(80);
                    try {
                        BackendlessCollection<tb_magang> tbMagangInfo = Backendless.Persistence.of(tb_magang.class).find(dataQueryTbMagang);
                        List<tb_magang> firstPageTbMagang = tbMagangInfo.getCurrentPage();
                        if(firstPageTbMagang.size() < 1){
                            baruIndustriFragment.this.listIndustri.add(
                                    new CustomIndustri(id_industri, nama, alamat, no_telp, email, kuota, logo, pimpinan)
                            );
                        }

                    } catch (BackendlessException be) {

                    }

                }
            }
            catch(Exception exception){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) rootView.findViewById(R.id.siswa_layout_belum_listView);

            HomeSiswaIndustriAdapter adapter = new HomeSiswaIndustriAdapter(getActivity(), listIndustri);

            listView.setAdapter(adapter);
            swipeLayout.setRefreshing(false);
//            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);
        }
    }

    public static baruIndustriFragment newInstance(int text, String title){
        baruIndustriFragment f = new baruIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Terbaru",title);

        f.setArguments(b);

        return f;
    }


}