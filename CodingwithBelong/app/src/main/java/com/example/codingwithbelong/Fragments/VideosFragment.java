package com.example.codingwithbelong.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codingwithbelong.PlayerActivity;
import com.example.codingwithbelong.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class VideosFragment extends Fragment {




    CardView v1Cv,v2Cv,v3Cv,v4Cv,v5Cv;



    public VideosFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_videos, container, false);

        v1Cv=view.findViewById(R.id.v1Cv);
        v2Cv=view.findViewById(R.id.v2Cv);
        v3Cv=view.findViewById(R.id.v3Cv);
        v4Cv=view.findViewById(R.id.v4Cv);
        v5Cv=view.findViewById(R.id.v5Cv);


        v1Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            goNext("EGQh5SZctaE");
            }
        });

        v2Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               goNext("eSYeHlwDCNA");
            }
        });

        v3Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext("wxds6MAtUQ0");
            }
        });

        v4Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            goNext("aYjGXzktatA");
            }
        });

        v5Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goNext("A37-3lflh8I");
            }
        });









        return view;
    }

    private void goNext(String key) {

        Intent intent=new Intent(getContext(), PlayerActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);
    }


}