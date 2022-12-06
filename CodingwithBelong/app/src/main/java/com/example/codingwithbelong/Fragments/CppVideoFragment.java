package com.example.codingwithbelong.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.codingwithbelong.PlayerActivity;
import com.example.codingwithbelong.R;
import com.github.barteksc.pdfviewer.PDFView;


public class CppVideoFragment extends Fragment {


    CardView v1Cv,v2Cv,v3Cv,v4Cv,v5Cv;

    public CppVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cpp_videos, container, false);

        v1Cv=view.findViewById(R.id.v1Cv);
        v2Cv=view.findViewById(R.id.v2Cv);
        v3Cv=view.findViewById(R.id.v3Cv);
        v4Cv=view.findViewById(R.id.v4Cv);
        v5Cv=view.findViewById(R.id.v5Cv);


        v1Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext("MNeX4EGtR5Y");
            }
        });

        v4Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext("4voqwothJTA");
            }
        });

        v5Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext("qhnARpYSqAA");
            }
        });

        v2Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext("a5kUr-u2UNo");
            }
        });

        v3Cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goNext("M3o7Y0juEP0");
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