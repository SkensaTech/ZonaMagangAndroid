package com.zonamagang.zonamagang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Denandra on 16/08/2016.
 */
public class baruFragment extends Fragment{

    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.baru, container, false);

        RelativeLayout relativeLayout = (RelativeLayout)rootView.findViewById(R.id.detailSiswa);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailSiswaOlehIndustri.class);
                startActivity(intent);
            }
        });

        button = (Button)rootView.findViewById(R.id.tglInterview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialogInterviewFragment newFragment = new dialogInterviewFragment();
                newFragment.show(ft,"dialog");
            }
        });

        return rootView;
    }

    public static baruFragment newInstance(int text, String title){
        baruFragment f = new baruFragment();
        Bundle b = new Bundle();
        b.putString("Baru",title);

        f.setArguments(b);

        return f;
    }
}
