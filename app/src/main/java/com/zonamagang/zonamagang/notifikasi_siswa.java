package com.zonamagang.zonamagang;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import com.zonamagang.zonamagang.Model.tb_notif;
import com.zonamagang.zonamagang.Model.tb_industri;

import java.util.Iterator;

/*
* Created by SkensaTech on 23/8/16
* */
public class notifikasi_siswa extends AppCompatActivity {

    private Drawer result = null;

    String nama, email, foto;
    String nama_industri, logo_industri, isi_notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Backendless.initApp(this,Constants.APP_ID,Constants.APP_SECRET,Constants.APP_VERSION);
        setContentView(R.layout.activity_notifikasi_siswa);

        email = getIntent().getStringExtra("email");
        nama = getIntent().getStringExtra("nama");
        foto = getIntent().getStringExtra("foto");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Cari Industri");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Beranda");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withBadgeStyle(new BadgeStyle().withTextColor(Color.RED)).withIdentifier(3).withName("Notifikasi");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Akun Saya");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Tentang Kami");
        final PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Keluar");

        Backendless.Persistence.of(tb_notif.class).find(new AsyncCallback<BackendlessCollection<tb_notif>>() {
            @Override
            public void handleResponse(BackendlessCollection<tb_notif> response) {
                Iterator<tb_notif> iterator = response.getCurrentPage().iterator();

                while (iterator.hasNext()) {
                    tb_notif responses = iterator.next();
                    isi_notif = responses.getIsi_notif();
                    // Cari industri berdasarkan id industri dan id siswa di tabel notif
                    String whereClause = "id_industri = "+responses.getId_pengirim()+" AND id_siswa = "+responses.getId_penerima();
                    BackendlessDataQuery query = new BackendlessDataQuery(whereClause);
                    Backendless.Persistence.of(tb_industri.class).find(query, new AsyncCallback<BackendlessCollection<tb_industri>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<tb_industri> response) {
                            if (response.getData().size() == 1) {
                                tb_industri responses = response.getData().get(0);
                                nama_industri = responses.getNama();
                                logo_industri = responses.getLogo();
                            } else {
                                System.out.println("Response salah broo");
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

        AccountHeader headerProfile = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(nama)
                                .withEmail(email)
                                .withIcon(foto)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();

        result = new DrawerBuilder()
                .withAccountHeader(headerProfile)
                .withActivity(this)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem(),
                        item3,
                        new DividerDrawerItem(),
                        item4,
                        new DividerDrawerItem(),
                        item5,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (position == 1){
                            Intent intent = new Intent(notifikasi_siswa.this,HomeIndustri.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 2){
                            Intent itent = new Intent(notifikasi_siswa.this,notifikasi_siswa.class);
                            startActivity(itent);
//                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 3){
                            Intent intent = new Intent(notifikasi_siswa.this,akun_user.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 4){
                            Intent intent = new Intent(notifikasi_siswa.this,TentangKami.class);
                            startActivity(intent);
                            finish();
                        }
                        return false;
                    }
                })
                .build();
    }

    public boolean onOptionsItemSelected(MenuItem Item){
        switch (Item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(Item);
    }
}
