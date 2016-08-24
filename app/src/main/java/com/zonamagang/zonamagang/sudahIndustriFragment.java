package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
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

    /** Fixed **/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_sudah,container,false);

        getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility
                (View.VISIBLE);

        BackendlessUser userNow = Backendless.UserService.CurrentUser();
        String id_user_now = userNow.getProperty("id_user").toString();
        String email_user_now = userNow.getProperty("email").toString();


        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause("id_user = "+id_user_now);
        Backendless.Persistence.of(tb_siswa.class).find( dataQuery, new
                AsyncCallback<BackendlessCollection<tb_siswa>>(){
                    @Override
                    public void handleResponse( BackendlessCollection<tb_siswa> hasil )
                    {
                        List<tb_siswa> firstPage = hasil.getCurrentPage();

                        Iterator<tb_siswa> iterator = firstPage.iterator();

                        while( iterator.hasNext() )
                        {
                            tb_siswa siswaInfo = iterator.next();
                            id_siswa = siswaInfo.getId_siswa();
                            nama = siswaInfo.getNama();
                            id_bidang = siswaInfo.getId_bidang();
                            id_sekolah = siswaInfo.getId_sekolah();


                        }
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(getContext(),"Error get siswa "+fault.getMessage
                                (),Toast.LENGTH_SHORT).show();
                    }
                });
        BackendlessDataQuery queryIndustri = new BackendlessDataQuery();
        queryIndustri.setWhereClause("id_siswa = "+id_siswa);
        Backendless.Persistence.of(tb_magang.class).find(queryIndustri, new
                AsyncCallback<BackendlessCollection<tb_magang>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<tb_magang> hasil) {
                        List<tb_magang> first = hasil.getCurrentPage();
                        Iterator<tb_magang> iterator = first.iterator();

                        while (iterator.hasNext()){
                            list_industri = new ArrayList<tb_industri>();
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });
        //START
        listIndustri = new ArrayList<CustomIndustri>();
        list_magang = new ArrayList<tb_magang>();


        /*Backendless.Persistence.of(tb_industri.class).find(new
AsyncCallback<BackendlessCollection<tb_industri>>(){
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
                            new CustomIndustri(id_industri, nama, alamat, no_telp,
email, kuota,logo)
                    );
                }

                ListView listView = (ListView) rootView.findViewById
(R.id.siswa_layout_belum_listView);

                HomeSiswaIndustriAdapter adapter = new HomeSiswaIndustriAdapter
(getActivity(), listIndustri);

                listView.setAdapter(adapter);
                getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility
(View.GONE);

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(getActivity(),"Error ! "+fault.getCode
(),Toast.LENGTH_SHORT).show();
            }
        });*/

        //END
        return rootView;
    }

    public static sudahIndustriFragment newInstance(int text, String title){
        sudahIndustriFragment f = new sudahIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Terdaftar",title);

        f.setArguments(b);

        return f;
    }


}
