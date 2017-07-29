package com.zonamagang.zonamagang;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class home_siswa_1 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewIndustriAdapter adapter;
    private Drawer result = null;
    String email, foto, nama, searchquery,bidang,kota,provinsi;
    boolean doubleBackToExit = false;
    MenuItem cari;
    private static final String TAG = "home_siswa_1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_siswa_1);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null){
            if(extras.containsKey("nama")){
                HashMap<String,Boolean> Users = (HashMap<String,Boolean>) intent.getSerializableExtra("Users");
                String alamat = intent.getStringExtra("alamat");
                HashMap<String,Boolean> bidang = (HashMap<String,Boolean>) intent.getSerializableExtra("bidang");
                foto = intent.getStringExtra("foto");
                nama = intent.getStringExtra("nama");
                email = intent.getStringExtra("email");
                String nisn = intent.getStringExtra("nisn");
                String sekolah = intent.getStringExtra("sekolah");
                String tempat_lahir = intent.getStringExtra("tempat_lahir");
                String tgl_lahir = intent.getStringExtra("tgl_lahir");
            }
            else{
                email = "user email";
                nama = "User";
                foto = "https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male2-512.png";
            }
        }else{
            email = " ";
            nama = "Pengunjung";
            foto = "https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male2-512.png";
        }

        if(getIntent().getStringExtra("bidang") != null){
            bidang = getIntent().getStringExtra("bidang");
            kota = getIntent().getStringExtra("kota");
            provinsi = getIntent().getStringExtra("provinsi");
        }
        if(getIntent().getStringExtra("searchquery") != null){
            if(!getIntent().getStringExtra("searchquery").equals("")){
                searchquery = getIntent().getStringExtra("searchquery");
            }
        }

        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        adapter = new ViewIndustriAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(adapter);


        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimary));

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.textColor));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }
        });


        PrimaryDrawerItem item0 = new PrimaryDrawerItem().withIdentifier(0).withIcon(getResources().getDrawable(R.drawable.ic_login)).withName("Login");
        PrimaryDrawerItem item1 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(1).withIcon(getResources().getDrawable(R.drawable.ic_search_black_24dp)).withName("Cari Industri");
        SecondaryDrawerItem item2 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(2).withIcon(getResources().getDrawable(R.drawable.ic_dashboard_black_24dp)).withName("Beranda");
        SecondaryDrawerItem item3 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(3).withIcon(getResources().getDrawable(R.drawable.ic_notifications_black_24dp)).withName("Notifikasi");
        SecondaryDrawerItem item4 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(4).withIcon(getResources().getDrawable(R.drawable.ic_supervisor_account_black_24dp)).withName("Tentang Kami");
        SecondaryDrawerItem item5 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(5).withIcon(getResources().getDrawable(R.drawable.ic_account_box_black_24dp)).withName("Akun Saya");
        SecondaryDrawerItem item6 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(6).withName("Keluar");

        AccountHeader headerprofil = new AccountHeaderBuilder()
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

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        if(extras == null || !extras.containsKey("nama")){
            Log.d(TAG,"WITH LOGIN ITEM");
            result = new DrawerBuilder()
                    .withAccountHeader(headerprofil)
                    .withActivity(this)
                    .withToolbar(x)
                    .addDrawerItems(
                            item0,
                            new DividerDrawerItem(),
                            item1,
                            new DividerDrawerItem(),
                            item2,
                            new DividerDrawerItem(),
                            item3,
                            new DividerDrawerItem(),
                            item4,
                            new DividerDrawerItem(),
                            item5,
                            new DividerDrawerItem(),
                            item6,
                            new DividerDrawerItem()
                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (drawerItem.getIdentifier() == 0){
                                Intent intent = new Intent(home_siswa_1.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else if (drawerItem.getIdentifier() == 1){
                                Intent intent = new Intent(home_siswa_1.this, search_filter.class);
                                intent.putExtra("email",email);
                                intent.putExtra("nama",nama);
                                intent.putExtra("foto",foto);
                                startActivity(intent);
                            }
                            else if (drawerItem.getIdentifier() == 3){
                                Intent itent = new Intent(home_siswa_1.this, notifikasi_siswa.class);
                                startActivity(itent);
//                            finish();
                            }
                            else if(drawerItem.getIdentifier() == 4){
                                Intent itent = new Intent(home_siswa_1.this,TentangKami.class);
                                startActivity(itent);
                            }
                            else if (drawerItem.getIdentifier() == 5){
                                Intent intent = new Intent(home_siswa_1.this,DetailSiswa.class);
                                startActivity(intent);
                            }
                            else if(drawerItem.getIdentifier() == 6){
                                setContentView(R.layout.loading_screen);
                            }

                            return false;
                        }
                    })
                    .build();
        }
        else{
            result = new DrawerBuilder()
                    .withAccountHeader(headerprofil)
                    .withActivity(this)
                    .withToolbar(x)
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
                            new DividerDrawerItem(),
                            item6,
                            new DividerDrawerItem()
                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (drawerItem.getIdentifier() == 1){
//                            Toast.makeText(getApplicationContext(),"Fitur Pencarian masih dalam tahap pengembangan",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(home_siswa_1.this, search_filter.class);
                                intent.putExtra("email",email);
                                intent.putExtra("nama",nama);
                                intent.putExtra("foto",foto);
                                startActivity(intent);
                            }
                            else if (drawerItem.getIdentifier() == 3){
                                Intent itent = new Intent(home_siswa_1.this, notifikasi_siswa.class);
                                startActivity(itent);
//                            finish();
                            }
                            else if(drawerItem.getIdentifier() == 4){
                                Intent itent = new Intent(home_siswa_1.this,TentangKami.class);
                                startActivity(itent);
                            }
                            else if (drawerItem.getIdentifier() == 5){
                                Intent intent = new Intent(home_siswa_1.this,DetailSiswa.class);
                                startActivity(intent);
                            }
                            else if(drawerItem.getIdentifier() == 6){
                                setContentView(R.layout.loading_screen);
                            }

                            return false;
                        }
                    })
                    .build();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }

    public MenuItem getCariMenu(){
        return cari;
    }


    @Override
    public void onBackPressed(){
        if (doubleBackToExit){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExit = true;
        Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExit = false;
            }
        }, 3000);
    }

    public String getQuery() {
        return searchquery;
    }

    public String getProvinsiSearch(){
        return provinsi;
    }

    public String getKotaSearch(){
        return kota;
    }

    public String getBidangSearch(){
        return bidang;
    }

}
