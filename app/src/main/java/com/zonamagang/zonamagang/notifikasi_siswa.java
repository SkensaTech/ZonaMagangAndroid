package com.zonamagang.zonamagang;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

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

public class notifikasi_siswa extends AppCompatActivity {

    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi_siswa);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Cari Industri");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Beranda");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withBadgeStyle(new BadgeStyle().withTextColor(Color.RED)).withIdentifier(3).withName("Notifikasi");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Akun Saya");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Tentang Kami");
        final PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Keluar");

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
                            Intent intent = new Intent(Notifikasi_Industri.this,HomeIndustri.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 2){
                            Intent itent = new Intent(Notifikasi_Industri.this,Notifikasi_Industri.class);
                            startActivity(itent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 3){
                            Intent intent = new Intent(Notifikasi_Industri.this,AkunIndustri.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 4){
                            Intent intent = new Intent(Notifikasi_Industri.this,TentangKami.class);
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
