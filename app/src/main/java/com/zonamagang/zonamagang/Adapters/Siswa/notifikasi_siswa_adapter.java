package com.zonamagang.zonamagang.Adapters.Siswa;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import com.zonamagang.zonamagang.Adapters.Siswa.notifikasi_siswa_costum;

/**
 * Created by Rizal on 8/23/2016.
 */
public class notifikasi_siswa_adapter extends ArrayAdapter<notifikasi_siswa_costum> {

    public notifikasi_siswa_adapter(Activity context, ArrayList<notifikasi_siswa_costum> listView) {super(context, 0, listView);}
}
