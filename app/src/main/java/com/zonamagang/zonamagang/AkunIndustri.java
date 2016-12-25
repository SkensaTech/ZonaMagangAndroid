package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.w3c.dom.Text;

public class AkunIndustri extends AppCompatActivity {

    private Drawer result = null;
    private TextView nama_industri;
    private TextView jalan_industri;
    private TextView email_industri;
    private TextView noTelp_industri;
    private TextView profil_industri;
    private TextView deskripsi_industri;
    private TextView kemampuan_industri;

    String namaIndustri,jalanIndustri,emailIndustri,
            noTelpIndustri,profilIndustri,
            deskripsiIndustri,kemampuanIndustri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_industri);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                            Intent intent = new Intent(AkunIndustri.this,HomeIndustri.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 2){
                            Intent itent = new Intent(AkunIndustri.this,Notifikasi_Industri.class);
                            startActivity(itent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 3){
                            Intent intent = new Intent(AkunIndustri.this,AkunIndustri.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (drawerItem.getIdentifier() == 4){
                            Intent intent = new Intent(AkunIndustri.this,TentangKami.class);
                            startActivity(intent);
                            finish();
                        }
                        return false;
                    }
                })
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.edit_icon:
                Intent intent = new Intent(AkunIndustri.this, UpdateAkun.class);
                startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }
}
