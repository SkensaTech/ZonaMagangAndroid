package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriBaruAdapter;
import com.zonamagang.zonamagang.Adapters.Siswa.HomeIndustriSiswaCustom;
import com.zonamagang.zonamagang.Adapters.Siswa.notifikasi_siswa_adapter;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_notif;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zonamagang.zonamagang.Adapters.Siswa.notifikasi_siswa_costum;
import com.zonamagang.zonamagang.Model.tb_sekolah;
import com.zonamagang.zonamagang.Model.tb_siswa;

/**
 * Created by Skensa Tech on 8/23/2016.
 */
public class notifikasi_siswa_fragment extends Fragment {
    ArrayList<notifikasi_siswa_costum> listNotif;
    private static final String TAG = "notifikasi";
    View rootView;
    String whereClause = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.notifikasi_siswa_layout, container, false);
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("Notifikasi Siswa");
        listNotif = new ArrayList<>();
        Log.e(TAG,"Notifikasi siswa fragment jalan.");

        new listingNotif().execute();

        Log.e(TAG,"return rootview");
        return rootView;
    }
    //AsyncTask
    private class listingNotif extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            getActivity().findViewById(R.id.notif_siswa_progressbar).setVisibility(View.VISIBLE);
            Log.e(TAG,"First Loading.");
            BackendlessUser userNow = Backendless.UserService.CurrentUser();
            String id_user_now = userNow.getProperty("id_user").toString();
            whereClause = "id_penerima = "+id_user_now;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                String isiNotif;
                Log.e(TAG,"Start do in background.");
                BackendlessDataQuery query = new BackendlessDataQuery();
                query.setWhereClause(whereClause);
                Log.e(TAG,"Where clause = "+whereClause);
                BackendlessCollection<tb_notif> notifInfo = Backendless.Persistence.of(tb_notif.class).find(query);
                List<tb_notif> firstPageNotif = notifInfo.getCurrentPage();
                Log.e(TAG,"Hasil notif = "+firstPageNotif.size());
                for(int i = 0; i < firstPageNotif.size(); i++){
                    Log.e(TAG,"Loop tabel notif.");
                    isiNotif = firstPageNotif.get(i).getIsi_notif();
                    String whereClause2 = "id_industri = "+firstPageNotif.get(i).getId_pengirim();
                    BackendlessDataQuery query2 = new BackendlessDataQuery(whereClause2);
                    BackendlessCollection<tb_industri> industriInfo = Backendless.Persistence.of(tb_industri.class).find(query2);
                    List<tb_industri> firstPageIndustri = industriInfo.getCurrentPage();
//                    if(firstPageIndustri.size() > 0){
                        Log.e(TAG,"Start add industri.");
                        int id_industri = firstPageIndustri.get(0).getId_industri();
                        String nama = firstPageIndustri.get(0).getNama();
                        String alamat = firstPageIndustri.get(0).getAlamat();
                        String no_telp = firstPageIndustri.get(0).getNo_telp();
                        String email = firstPageIndustri.get(0).getEmail();
                        int kuota = firstPageIndustri.get(0).getKuota();
                        String logo = firstPageIndustri.get(0).getLogo();

                        Log.e(TAG,"something added");

                        notifikasi_siswa_fragment.this.listNotif.add(
                                new notifikasi_siswa_costum(id_industri, nama, alamat, no_telp, email, kuota, logo, isiNotif)
                        );
//                    }else{
//                        Log.e(TAG,"industri tidak ditemukan.");
//                    }
                }
            }
            catch(Exception exception){
                Log.e("baruFragment","Terjadi Error ! = "+exception.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ListView listView = (ListView) rootView.findViewById(R.id.notifikasi_siswa_layout_listView);
            notifikasi_siswa_adapter adapter = new notifikasi_siswa_adapter(getActivity(), listNotif);
            listView.setAdapter(adapter);

            ProgressBar progressBar = (ProgressBar)getActivity().findViewById(R.id.notif_siswa_progressbar);
            progressBar.setVisibility(View.GONE);

            Log.e(TAG,"End Loading.");
        }
    }

}
