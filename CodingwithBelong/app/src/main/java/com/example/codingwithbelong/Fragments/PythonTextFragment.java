package com.example.codingwithbelong.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.codingwithbelong.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;


public class PythonTextFragment extends Fragment {


    PDFView pdfView;

    public PythonTextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_text, container, false);

        pdfView=view.findViewById(R.id.pdfView);
        pdfView.useBestQuality(true);



        pdfView.fromAsset("pn.pdf")

                // .enableSwipe(true) // allows to block changing pages using swipe
                .nightMode(false)
                .pageFitPolicy(FitPolicy.BOTH)
                .fitEachPage(true)
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .spacing(20)
                .autoSpacing(false)
                .load();



        return view;
    }
}