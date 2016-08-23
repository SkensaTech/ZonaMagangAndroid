package com.zonamagang.zonamagang;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Adapters.Siswa.notifikasi_siswa_adapter;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_notif;

import java.util.ArrayList;
import java.util.Iterator;

import com.zonamagang.zonamagang.Adapters.Siswa.notifikasi_siswa_costum;

/**
 * Created by Rizal on 8/23/2016.
 */
public class notifikasi_siswa_fragment extends Fragment {
    ArrayList<notifikasi_siswa_costum> listNotif;
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.notifikasi_siswa_layout, container, false);
        listNotif = new ArrayList<>();
        final String whereClause = "id_penerima = "+Backendless.UserService.CurrentUser().getUserId();
        BackendlessDataQuery query = new BackendlessDataQuery(whereClause);
        Backendless.Persistence.of(tb_notif.class).find(query, new AsyncCallback<BackendlessCollection<tb_notif>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_notif> response) {
                Iterator<tb_notif> iterator = response.getCurrentPage().iterator();
                while (iterator.hasNext()) {
                    tb_notif list_notif = iterator.next();
                    String isi_notif = list_notif.getIsi_notif();
                    String whereClause = "id_industri = "+list_notif.getId_pengirim();
                    BackendlessDataQuery query = new BackendlessDataQuery(whereClause);
                    Backendless.Persistence.of(tb_industri.class).find(query, new AsyncCallback<BackendlessCollection<tb_industri>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_industri> response) {
                            if (response.getData().size() == 1) {
                                tb_industri notif = response.getData().get(0);
                                int id_industri = notif.getId_industri();
                                String nama = notif.getNama();
                                String alamat = notif.getAlamat();
                                String no_telp = notif.getNo_telp();
                                String email = notif.getEmail();
                                int kuota = notif.getKuota();
                                String logo = notif.getLogo();

                                notifikasi_siswa_fragment.this.listNotif.add(
                                        new notifikasi_siswa_costum(id_industri, nama, alamat, no_telp, email, kuota,logo)
                                );
                            }

                            ListView listView = (ListView) rootView.findViewById(R.id.notifikasi_siswa_layout_listView);
                            notifikasi_siswa_adapter adapter = new notifikasi_siswa_adapter(getActivity(), listNotif);
                            listView.setAdapter(adapter);

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(getActivity(),"Notif Siswa Error ! "+fault.getCode(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        return rootView;
    }
}
