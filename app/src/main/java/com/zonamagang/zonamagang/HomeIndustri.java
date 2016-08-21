package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class HomeIndustri extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_industri);

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

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Beranda");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Notifikasi");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Akun Saya");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Tentang Kami");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Keluar");

        AccountHeader headerProfile = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Denandra Prasetya")
                                .withEmail("denandra1628@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.asu))
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
                .withToolbar(myToolbar)
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
//                        switch (position) {
//                            case 1:
//                                Intent intent = new Intent(HomeIndustri.this, HomeIndustri.class);
//                                startActivity(intent);
//                                break;
//                            case 2:
//                                Intent intent2 = new Intent(HomeIndustri.this,TentangKami.class);
//                                startActivity(intent2);
//                                break;
//                        }
                        if (position == 1){
                            Intent intent = new Intent(HomeIndustri.this,HomeIndustri.class);
                            startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 2){
                            Intent itent = new Intent(HomeIndustri.this,Notifikasi_Industri.class);
                            startActivity(itent);
                        }
                        else if (drawerItem.getIdentifier() == 3){
                            Intent intent = new Intent(HomeIndustri.this,AkunIndustri.class);
                            startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 4){
                            Intent intent = new Intent(HomeIndustri.this,TentangKami.class);
                            startActivity(intent);
                        }
                        else if(drawerItem.getIdentifier() == 5){
                            setContentView(R.layout.loading_screen);

                            Backendless.UserService.logout(new AsyncCallback<Void>()
                            {
                                public void handleResponse( Void response )
                                {
                                    // user has been logged out.
                                    Intent MainActivityIntent = new Intent(HomeIndustri.this,MainActivity.class);
                                    startActivity(MainActivityIntent);
                                }

                                public void handleFault( BackendlessFault fault )
                                {
                                    // something went wrong and logout failed, to get the error code call fault.getCode()
                                    Toast.makeText(HomeIndustri.this,"Logout failed",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        return false;
                    }
                })
                .build();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
    }
}
