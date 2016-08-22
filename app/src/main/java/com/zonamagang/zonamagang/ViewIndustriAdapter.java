package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Denandra on 16/08/2016.
 */
public class ViewIndustriAdapter extends FragmentStatePagerAdapter{
    public ViewIndustriAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return baruIndustriFragment.newInstance(0, "Terbaru");
            case 1:
                return sudahIndustriFragment.newInstance(1, "Terdaftar");
            default:
                return baruIndustriFragment.newInstance(0,"Terbaru");
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position==0){
            return "Terbaru";
        }
        if(position==1){
            return "Terdaftar";
        }
        else{
            return "";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
