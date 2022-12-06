package com.example.codingwithbelong.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.codingwithbelong.LoginActivity;
import com.example.codingwithbelong.MyInterface;
import com.example.codingwithbelong.R;
import com.example.codingwithbelong.util.ScreenTimeManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    CircleImageView profileIv;
    TextView nameTv,bioTv;

    LinearLayout activtyLayout,leaderBoardLayout,settingsLayout,logoutLayout;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        profileIv=view.findViewById(R.id.profile_Iv);
        nameTv=view.findViewById(R.id.nameTv);
        bioTv=view.findViewById(R.id.bioTv);

        activtyLayout = view.findViewById(R.id.viewActivityLayout);
        leaderBoardLayout = view.findViewById(R.id.leaderboardLayout);
        settingsLayout = view.findViewById(R.id.settingsLayout);
        logoutLayout = view.findViewById(R.id.layoutLogout);
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        settingsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).addToBackStack("tag").commit();

            }
        });

        leaderBoardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,new LeaderBoardFragment()).addToBackStack("tag").commit();
            }
        });
        activtyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyInterface lambda = () -> {
                    getFragmentManager().beginTransaction().replace(R.id.container,new DashboardFragment()).addToBackStack("tag").commit();
                };
                ScreenTimeManager.updateSessionTime(lambda);
            }
        });



        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                nameTv.setText(documentSnapshot.getString("name"));
                bioTv.setText(documentSnapshot.getString("bio"));

                if (getActivity() != null) {
                    Glide.with(getContext()).load(documentSnapshot.getString("photo"))
                            .placeholder(R.drawable.peson)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(profileIv);
                }


            }
        });




        return view;
    }

}