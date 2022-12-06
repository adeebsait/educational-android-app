package com.example.codingwithbelong.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.codingwithbelong.ChangePasswordActivity;
import com.example.codingwithbelong.EditProfilePictureActivity;
import com.example.codingwithbelong.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;


public class SettingsFragment extends Fragment {


    PDFView pdfView;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_settings, container, false);

        CardView profilePictureCard = view.findViewById(R.id.cv_picture_settings);
        CardView passwordCard = view.findViewById(R.id.cv_password_settings);

        profilePictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfilePictureActivity.class);
                startActivity(intent);
            }
        });

        passwordCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}