package com.zonamagang.zonamagang;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem cari = menu.findItem(R.id.searchbox);

        SearchView searchView = new SearchView(((home_siswa_1) c).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(cari, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(cari, searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals("")){
                    baruIndustriFragment.this.firebaseQuery(null);
                }
                else{
                    Query queryForRecyclerView = mIndustriReference.orderByChild("nama").startAt(query).endAt(query+"\uf8ff");
                    baruIndustriFragment.this.firebaseQuery(queryForRecyclerView);
//                baruIndustriFragment.this.returnNewView();
                    Log.e(TAG,"QUERY SUBMITTED");
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                if(query.equals("")){
                    baruIndustriFragment.this.firebaseQuery(null);
                }
                else{
                    Query queryForRecyclerView = mIndustriReference.orderByChild("nama").startAt(query).endAt(query+"\uf8ff");
                    baruIndustriFragment.this.firebaseQuery(queryForRecyclerView);
//                baruIndustriFragment.this.returnNewView();
                    Log.e(TAG,"QUERY SUBMITTED");
                }
                return true;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      }
        );
    }

    /** Fixed **/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.siswa_layout_belum,container,false);
        setHasOptionsMenu(true);
        home_siswa_1 activity = (home_siswa_1) getActivity();
        c = getContext();
        getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.VISIBLE);

        mLinearLayoutManager = new LinearLayoutManager(c);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.siswa_layout_belum_RecyclerView);

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);


        this.firebaseQuery(null);

        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        return rootView;
    }

//    public View returnNewView(){
//        return rootView;
//    }

    public void firebaseQuery(Query queryForRecyclerView){
        if(queryForRecyclerView == null){
            mFirebaseAdapter= new FirebaseRecyclerAdapter<industri, IndustriViewHolder>(industri.class, R.layout.siswa_industri_belum_item, IndustriViewHolder.class, mIndustriReference) {
                @Override
                protected void populateViewHolder(IndustriViewHolder viewHolder, industri model, int position) {

                    viewHolder.bindIndustri(model);
                    getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);
                }
            };
            mFirebaseAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mFirebaseAdapter);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        }
        else{
            mFirebaseAdapter= new FirebaseRecyclerAdapter<industri, IndustriViewHolder>(industri.class, R.layout.siswa_industri_belum_item, IndustriViewHolder.class, queryForRecyclerView) {
                @Override
                protected void populateViewHolder(IndustriViewHolder viewHolder, industri model, int position) {

                    viewHolder.bindIndustri(model);
                    getActivity().findViewById(R.id.home_siswa_1_progressBar).setVisibility(View.GONE);
                }
            };
            mFirebaseAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mFirebaseAdapter);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        }


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
    }

    public static baruIndustriFragment newInstance(int text, String title){
        baruIndustriFragment f = new baruIndustriFragment();
        Bundle b = new Bundle();
        b.putString("Terbaru",title);

        f.setArguments(b);

        return f;
    }


}