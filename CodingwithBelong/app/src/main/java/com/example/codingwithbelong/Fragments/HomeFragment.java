package com.example.codingwithbelong.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.codingwithbelong.CourseDetailsActivity;
import com.example.codingwithbelong.R;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {


    ImageView fundaBtn,cppBtn,javaBtn,pythonBtn;
    RelativeLayout continueRl;
    TextView continueTv;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        continueTv=view.findViewById(R.id.continueTv);
        fundaBtn=view.findViewById(R.id.fundaBtn);
        cppBtn=view.findViewById(R.id.cppBtn);
        javaBtn=view.findViewById(R.id.javaBtn);
        pythonBtn=view.findViewById(R.id.pythonBtn);
        continueRl=view.findViewById(R.id.continueRl);

        fundaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             saveState("funda");

            }
        });

        cppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveState("cpp");


            }
        });

        javaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveState("java");


            }
        });
        pythonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveState("python");

            }
        });



        continueRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getActivity().getSharedPreferences("LastState", MODE_PRIVATE);
                String last = prefs.getString("last", "nothing");
                if (last.equals("nothing")){
                    saveState("funda");
                }else if (last.equals("funda")){
                    saveState("funda");
                }else if (last.equals("cpp")){
                    saveState("cpp");
                }else if (last.equals("java")){
                    saveState("java");
                }else if (last.equals("python")){
                    saveState("python");
                }

            }
        });


        return view;
    }

    public void saveState(String last){
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("LastState", MODE_PRIVATE).edit();
        editor.putString("last", last);
        editor.apply();

        Intent intent=new Intent(getContext(), CourseDetailsActivity.class);
        intent.putExtra("course",last);
        startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs = getActivity().getSharedPreferences("LastState", MODE_PRIVATE);
        String last = prefs.getString("last", "nothing");

        if (last.equals("nothing")){
            continueTv.setText("Start with Fundamentals Course");
        }else if (last.equals("funda")){
            continueTv.setText("Continue to Fundamentals Course");
        }else if (last.equals("cpp")){
            continueTv.setText("Continue to C++ Course");
        }else if (last.equals("java")){
            continueTv.setText("Continue to Java Course");
        }else if (last.equals("python")){
            continueTv.setText("Continue to Python Course");
        }
    }
}