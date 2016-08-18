package com.zonamagang.zonamagang;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Denandra on 16/08/2016.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter{
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return semuaFragment.newInstance(0, "Semua");
            case 1:
                return baruFragment.newInstance(1, "Baru");
            case 2:
                return sudahFragment.newInstance(2, "Sudah");
            default:
                return semuaFragment.newInstance(0,"Semua");
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position==0){
            return "Semua";
        }
        if(position==1){
            return "Belum";
        }
        if(position==2){
            return "Sudah";
        }
        else{
            return "";
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
