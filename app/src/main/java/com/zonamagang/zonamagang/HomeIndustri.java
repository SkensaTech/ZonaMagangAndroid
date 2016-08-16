package com.zonamagang.zonamagang;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class HomeIndustri extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

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

        final TabLayout.Tab Semua = tabLayout.newTab();
        final TabLayout.Tab Belum = tabLayout.newTab();
        final TabLayout.Tab Sudah = tabLayout.newTab();

        Semua.setText("Semua Data");
        Belum.setText("Belum");
        Sudah.setText("Sudah");

        tabLayout.addTab(Semua, 0);
        tabLayout.addTab(Belum, 1);
        tabLayout.addTab(Sudah, 2);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.colorPrimary));

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.textColor));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
