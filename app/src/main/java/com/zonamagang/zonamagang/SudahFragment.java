package com.zonamagang.zonamagang;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriBaruAdapter;
import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriSiswaCustom;
import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriSudahAdapter;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denandra on 16/08/2016.
 */
public class sudahFragment extends Fragment {
    private Button button;
    ArrayList<HomeIndustriSiswaCustom> listSiswa;
    View rootView;
    int id_siswa,id_industri;
    String id_user_now;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sudah, container, false);
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

                try{
                    String whereClause = "id_user = " + id_user_now;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(whereClause);
                    BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find(dataQuery);
                    List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
                    id_industri = firstPageIndustri.get(0).getId_industri();
                    Log.e("baruFragment","(sudah) id_industri sudah = "+id_industri);
                }catch(Exception exception){
                    Log.e("baruFragment","(sudah) id industri tidak ditemukan ! = "+exception.getMessage());
                }

                try{
                    BackendlessDataQuery dataQueryTbMagang = new BackendlessDataQuery();
                    dataQueryTbMagang.setWhereClause("id_industri = " +id_industri+" AND status_diterima = 2");
                    BackendlessCollection<tb_magang> magangInfo = Backendless.Persistence.of(tb_magang.class).find(dataQueryTbMagang);
                    List<tb_magang> firstPageMagang = magangInfo.getCurrentPage();
                    for(int i = 0; i < firstPageMagang.size(); i++){
                        id_siswa = firstPageMagang.get(i).getId_siswa();
                        Log.e("baruFragment","(sudah) id_siswa sudah = "+id_siswa);

                        try {
                            BackendlessDataQuery dataQuerySiswa = new BackendlessDataQuery();
                            dataQuerySiswa.setWhereClause("id_siswa = " + id_siswa);
                            BackendlessCollection<tb_siswa> siswaInfo = Backendless.Persistence.of(tb_siswa.class).find(dataQuerySiswa);
                            List<tb_siswa> firstPageSiswa = siswaInfo.getCurrentPage();
                            int id_siswa = firstPageSiswa.get(0).getId_siswa();
                            int id_sekolah = firstPageSiswa.get(0).getId_sekolah();

                            String nama_sekolah = "";
                            try {
                                //cari sekolah
                                BackendlessDataQuery dataQuerySekolah = new BackendlessDataQuery();
                                dataQuerySekolah.setWhereClause("id_sekolah = " + id_sekolah);
                                BackendlessCollection<tb_sekolah> sekolahInfo = Backendless.Persistence.of(tb_sekolah.class).find(dataQuerySekolah);
                                List<tb_sekolah> firstPageSekolah = sekolahInfo.getCurrentPage();
                                nama_sekolah = firstPageSekolah.get(0).getNama();
                                //end cari sekolah
                            } catch (Exception exception) {
                                Log.e("baruFragment", "(sudah) Cari sekolah gagal ! = " + exception.getMessage());
                            }

                            int id_bidang = firstPageSiswa.get(0).getId_bidang();

                            String bidang = "";
                            try {
                                //cari bidang
                                BackendlessDataQuery dataQueryBidang = new BackendlessDataQuery();
                                dataQueryBidang.setWhereClause("id_bidang = " + id_bidang);
                                BackendlessCollection<tb_bidang> bidangInfo = Backendless.Persistence.of(tb_bidang.class).find(dataQueryBidang);
                                List<tb_bidang> firstPageBidang = bidangInfo.getCurrentPage();
                                bidang = firstPageBidang.get(0).getNama();
                                //end cari bidang
                            } catch (Exception exception) {
                                Log.e("baruFragment", "(sudah) Cari bidang gagal ! = " + exception.getMessage());
                            }

                            String nama_siswa = firstPageSiswa.get(0).getNama();
                            String foto = firstPageSiswa.get(0).getFoto();

                            sudahFragment.this.listSiswa.add(
                                    new HomeIndustriSiswaCustom(id_siswa, nama_sekolah, nama_siswa, bidang, foto)
                            );
                        }catch(Exception exception){
                            Log.e("baruFragment","(sudah) tidak ada di id_siswa");
                        }

                    }
                }catch(Exception exception){
                    Log.e("baruFragment","(sudah) tidak ada di tb_magang ! = "+exception.getMessage());
                }

            }
            catch(Exception exception){
                Log.e("baruFragment","(sudah) Terjadi Error ! = "+exception.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) rootView.findViewById(R.id.sudah_listView);

            HomeIndustriSudahAdapter adapter = new HomeIndustriSudahAdapter(getActivity(), listSiswa);

            listView.setAdapter(adapter);
            getActivity().findViewById(R.id.home_industri_progressBar).setVisibility(View.GONE);
        }
    }

    public static sudahFragment newInstance(int text, String title){
        sudahFragment f = new sudahFragment();
        Bundle b = new Bundle();
        b.putString("Sudah",title);

        f.setArguments(b);

        return f;
    }


}
