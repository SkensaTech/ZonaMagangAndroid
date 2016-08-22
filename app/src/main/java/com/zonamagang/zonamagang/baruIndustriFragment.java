package com.zonamagang.zonamagang;

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
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
import com.zonamagang.zonamagang.Adapters.Industri.HomeSiswaIndustriAdapter;
import com.zonamagang.zonamagang.Model.tb_bidang;
import com.zonamagang.zonamagang.Model.tb_industri;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Skensa Tech on 16/08/2016.
 */
public class baruIndustriFragment extends Fragment {
    ArrayList<CustomIndustri> listIndustri;
    View rootView;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

    }
    /** Fixed **/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_belum,container,false);

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
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(getActivity(),"Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
//        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.layoutIndustri);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),detail_industri.class);
//                startActivity(intent);
//            }
//        });
//
//        Button button = (Button)rootView.findViewById(R.id.detailSiswa2);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),detail_industri.class);
//                startActivity(intent);
//            }
//        });
        return rootView;
    }

    public static baruIndustriFragment newInstance(int text, String title){
        baruIndustriFragment f = new baruIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Sudah",title);

        f.setArguments(b);

        return f;
    }


}
