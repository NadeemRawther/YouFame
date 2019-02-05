package com.example.admin.youfame;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        RecyclerView videoRecycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_video, container, false);
        VideosAdapter adapter = new VideosAdapter();
        videoRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        videoRecycler.setLayoutManager(layoutManager);


        adapter.setListener(new VideosAdapter.Listener() {
            @Override
            public void onClick(int position, String Ways) {
    try {

         VideosDatabase youpost = new VideosDatabase(getContext());
         SQLiteDatabase db = youpost.getWritableDatabase();
         youpost.insertDatabase(db, position, Ways);

          //Toast toast = Toast.makeText(getContext(), "Nadeem Database available", Toast.LENGTH_SHORT);
         //toast.show();

         } catch (SQLiteException e) {
         // Toast toast = Toast.makeText(getContext(), "Nadeem Database Unavailable", Toast.LENGTH_SHORT);
          //toast.show();
         }
            }
        });
        return videoRecycler;
    }
}
    