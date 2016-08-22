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
                return baruFragment.newInstance(0, "Belum");
            case 1:
                return sudahFragment.newInstance(1, "Sudah");
            default:
                return baruFragment.newInstance(0,"Sudah");
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position==0){
            return "Belum";
        }
        if(position==1){
            return "Sudah";
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
