package com.zonamagang.zonamagang;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
import com.zonamagang.zonamagang.Adapters.Industri.HomeSiswaIndustriAdapter;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_magang;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Skensa Tech on 16/08/2016.
 */
public class baruIndustriFragment extends Fragment {
    ArrayList<CustomIndustri> listIndustri;
    View rootView;
    int id_siswa;

    /** Fixed **/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_belum,container,false);

        getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.VISIBLE);

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

                    //START
                    listIndustri = new ArrayList<CustomIndustri>();

                    Backendless.Persistence.of(tb_industri.class).find(new AsyncCallback<BackendlessCollection<tb_industri>>(){
                        @Override
                        public void handleResponse( BackendlessCollection<tb_industri> hasil )
                        {
                            List<tb_industri> firstPage = hasil.getCurrentPage();

                            Iterator<tb_industri> iterator = firstPage.iterator();

                            while( iterator.hasNext() )
                            {
                                tb_industri bidangList = iterator.next();

                                int id_industri = bidangList.getId_industri();
                                String nama = bidangList.getNama();
                                String alamat = bidangList.getAlamat();
                                String no_telp = bidangList.getNo_telp();
                                String email = bidangList.getEmail();
                                int kuota = bidangList.getKuota();
                                String logo = bidangList.getLogo();

                                baruIndustriFragment.this.listIndustri.add(
                                        new CustomIndustri(id_industri, nama, alamat, no_telp, email, kuota,logo)
                                );
                            }

                            ListView listView = (ListView) rootView.findViewById(R.id.siswa_layout_belum_listView);

                            HomeSiswaIndustriAdapter adapter = new HomeSiswaIndustriAdapter(getActivity(), listIndustri);

                            listView.setAdapter(adapter);
                            getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);

                        }
                        @Override
                        public void handleFault( BackendlessFault fault )
                        {
                            Toast.makeText(getActivity(),"Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
                        }
                    });

                    //END
                }
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(getContext(),"Error get siswa "+fault.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public static baruIndustriFragment newInstance(int text, String title){
        baruIndustriFragment f = new baruIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Terbaru",title);

        f.setArguments(b);

        return f;
    }


}
