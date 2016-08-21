package com.zonamagang.zonamagang;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
public class home_siswa_1 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private Drawer result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_siswa_1);

        Toolbar x = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(x);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        Toolbar myToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        adapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimary));

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.textColor));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Cari Industri");
        SecondaryDrawerItem item2 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(2).withName("Beranda");
        SecondaryDrawerItem item3 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(3).withName("Notifikasi");
        SecondaryDrawerItem item5 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(5).withName("Profil Saya");
        SecondaryDrawerItem item4 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(4).withName("Tentang Kami");
        SecondaryDrawerItem item6 = (SecondaryDrawerItem) new SecondaryDrawerItem().withIdentifier(6).withName("Keluar");

        AccountHeader headerprofil = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg)
                .addProfiles(
                        new ProfileDrawerItem().withName("Wahyu Baskara").withEmail("wahyubaskara@outlook.com").withIcon(getResources().getDrawable(R.drawable.asu))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
                        if (position == 1){
                            Intent intent = new Intent(home_siswa_1.this,cari_industri.class);
                            startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 2){
                            Intent itent = new Intent(home_siswa_1.this,home_siswa_1.class);
                            startActivity(itent);
                        }
                        else if (drawerItem.getIdentifier() == 3){
                            Intent itent = new Intent(home_siswa_1.this,notifikasi_siswa.class);
                            startActivity(itent);
                        }
                        else if (drawerItem.getIdentifier() == 4){
                            Intent itent = new Intent(home_siswa_1.this,notifikasi_siswa.class);
                            startActivity(itent);
                        }
                        else if (drawerItem.getIdentifier() == 5){
                            Intent itent = new Intent(home_siswa_1.this,TentangKami.class);
                            startActivity(itent);
                        }


                        return false;
                    }
                })
                .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuin = getMenuInflater();
        menuin.inflate(R.menu.menu_search, menu);
        MenuItem cari = menu.findItem(R.id.searchbox);
        final SearchView pencarian = (SearchView) MenuItemCompat.getActionView(cari);

        return super.onCreateOptionsMenu(menu);

    }


}
