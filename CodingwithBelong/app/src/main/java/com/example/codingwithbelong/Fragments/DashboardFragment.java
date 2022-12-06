package com.example.codingwithbelong.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codingwithbelong.Adapters.TimeAdapter;
import com.example.codingwithbelong.Model.LineGraphData;
import com.example.codingwithbelong.Model.ScreenTime;
import com.example.codingwithbelong.Model.User;
import com.example.codingwithbelong.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class DashboardFragment extends Fragment {

    TextView highestScoreTv;
    TextView pythonHighScore;
    TextView javaHighScore;
    TextView cppHighScore;

    View view;

    String uid;
    FirebaseAuth auth;

    RecyclerView rv;
    TimeAdapter adapter;

    private ArrayList<Long> dates =new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        highestScoreTv = view.findViewById(R.id.highestScoreTv);
        pythonHighScore = view.findViewById(R.id.pythonHighScore);
        javaHighScore = view.findViewById(R.id.javaHighScore);
        cppHighScore = view.findViewById(R.id.cppHighScore);
        rv=view.findViewById(R.id.time_rv);
        adapter=new TimeAdapter(getContext());

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);

        rv.setLayoutManager(layoutManager);

        auth=FirebaseAuth.getInstance();
        uid= auth.getCurrentUser().getUid();

        getScoresData();

        downloadGraphData();

        return view;
    }

    private void downloadGraphData(){
        ArrayList<ScreenTime> stList=new ArrayList<>();
        FirebaseFirestore.getInstance().collection("ScreenTime")
                .whereEqualTo("userId", uid).orderBy("date").get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                ScreenTime st=document.toObject(ScreenTime.class);
                                stList.add(st);
                                dates.add(st.getDate());
                            }
                            ArrayList<LineGraphData> graphData=prepareGraphData(stList);
                            Log.d("mytestdata", "size = "+stList.size());
                            for(LineGraphData item : graphData){
                                Log.d("mytestdata", "item = "+item.toString());
                            }
                            adapter.setData(graphData);
                            rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("mytestdata", "failed: setGraphData()");
            }
        });
    }


    private ArrayList<LineGraphData> prepareGraphData(ArrayList<ScreenTime> stList){
        ArrayList<LineGraphData> graphData=new ArrayList<>();

        Set<Long> set = new HashSet<>(dates);
        dates.clear();
        dates.addAll(set);



        for(Long date: dates){
            long timeSum=0;
//            Log.d("mytestdata", "item = "+date.toString());
            for(int j=0; j<stList.size(); j++){
                if(stList.get(j).getDate()==date){
                    timeSum=timeSum+stList.get(j).getSessionTime();
                }
            }
            LineGraphData graphDataItem=new LineGraphData(date, timeSum);
            graphData.add(graphDataItem);
//            Log.d("mytestdata", "timeSum = "+timeSum);
        }

        return graphData;
    }



    private void getScoresData(){
        FirebaseFirestore.getInstance().collection("Users").document(uid).
                get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()){
                    //   uploadAllData();
                    User user = documentSnapshot.toObject(User.class);
                    highestScoreTv.setText(""+user.getFundamental()+"/"+user.getFtotal());
                    pythonHighScore.setText(""+user.getPython()+"/"+user.getPtotal());
                    javaHighScore.setText(""+user.getJava()+"/"+user.getJtotal());
                    cppHighScore.setText(""+user.getCpp()+"/"+user.getCpptotal());
                }
                else {
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}