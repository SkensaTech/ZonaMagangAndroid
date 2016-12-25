package com.zonamagang.zonamagang;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.zonamagang.zonamagang.Adapters.Industri.CustomIndustri;
import com.zonamagang.zonamagang.Model.industri;
import com.zonamagang.zonamagang.ViewHolders.IndustriViewHolder;

import java.util.ArrayList;

/**
 * Created by Skensa Tech on 16/08/2016.
 */
public class baruIndustriFragment extends Fragment {
    View rootView;
    Context c;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    DatabaseReference mIndustriReference = FirebaseUtil.getIndustriRef();
    FirebaseRecyclerAdapter<industri, IndustriViewHolder>  mFirebaseAdapter;
    final static String TAG = "home_siswa_1";


    /** Fixed **/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_belum,container,false);
        home_siswa_1 activity = (home_siswa_1) getActivity();
        c = getContext();
        getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.VISIBLE);

        mLinearLayoutManager = new LinearLayoutManager(c);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.siswa_layout_belum_RecyclerView);

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);


        mFirebaseAdapter= new FirebaseRecyclerAdapter<industri, IndustriViewHolder>(industri.class, R.layout.siswa_industri_belum_item, IndustriViewHolder.class, mIndustriReference) {
            @Override
            protected void populateViewHolder(IndustriViewHolder viewHolder, industri model, int position) {

                viewHolder.bindIndustri(model);
                getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int industriCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (industriCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        return rootView;
    }

    public static baruIndustriFragment newInstance(int text, String title){
        baruIndustriFragment f = new baruIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Terbaru",title);

        f.setArguments(b);

        return f;
    }


}