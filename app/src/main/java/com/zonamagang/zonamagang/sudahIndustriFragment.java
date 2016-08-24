package com.zonamagang.zonamagang;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.zonamagang.zonamagang.Adapters.Industri.HomeSiswaSudahIndustriAdapter;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Denandra on 16/08/2016.
 */
public class sudahIndustriFragment extends Fragment {
    ArrayList<CustomIndustri> listIndustri;
    ArrayList<tb_magang> list_magang;
    List<tb_industri> list_industri;
    View rootView;
    int id_siswa,id_bidang,id_sekolah;
    String nama,email,notelp;
    String id_user_now;

    /** Fixed **/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_sudah,container,false);
        new goAsyncTask().execute();
        return rootView;
    }

    private class goAsyncTask extends AsyncTask<Void, Void, Void> {

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
                BackendlessCollection<tb_siswa> siswaInfo = Backendless.Persistence.of(tb_siswa.class).find(dataQuery);
                List<tb_siswa> firstPageSiswa = siswaInfo.getCurrentPage();
                id_siswa = firstPageSiswa.get(0).getId_siswa();

                BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find();
                List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
                for (int i = 0; i < firstPageIndustri.size(); i++) {
                    int id_industri = firstPageIndustri.get(i).getId_industri();
                    String nama = firstPageIndustri.get(i).getNama();
                    String alamat = firstPageIndustri.get(i).getAlamat();
                    String no_telp = firstPageIndustri.get(i).getNo_telp();
                    String email = firstPageIndustri.get(i).getEmail();
                    int kuota = firstPageIndustri.get(i).getKuota();
                    String logo = firstPageIndustri.get(i).getLogo();

                    String whereClauseTbMagang = "id_siswa = " + id_siswa + " AND id_industri = " + id_industri;
                    BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                    dataQueryTbMagang.setWhereClause(whereClauseTbMagang);
                    try {
                        BackendlessCollection<tb_magang> tbMagangInfo = Backendless.Persistence.of(tb_magang.class).find(dataQueryTbMagang);
                        List<tb_magang> firstPageTbMagang = tbMagangInfo.getCurrentPage();
                        if(firstPageTbMagang.size() > 0) {
                            int status_diterima = firstPageTbMagang.get(0).getStatus_diterima();
                            if(status_diterima == 1){
                                sudahIndustriFragment.this.listIndustri.add(
                                        new CustomIndustri(id_industri, nama, alamat, no_telp, email, kuota, logo)
                                );
                            }
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
            ListView listView = (ListView) rootView.findViewById(R.id.siswa_layout_sudah_listView);

            HomeSiswaSudahIndustriAdapter adapter = new HomeSiswaSudahIndustriAdapter(getActivity(), listIndustri);

            listView.setAdapter(adapter);
            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);
        }
    }

    public static sudahIndustriFragment newInstance(int text, String title){
        sudahIndustriFragment f = new sudahIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Terdaftar",title);

        f.setArguments(b);

        return f;
    }


}
