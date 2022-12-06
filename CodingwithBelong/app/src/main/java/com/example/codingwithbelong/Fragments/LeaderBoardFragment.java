package com.example.codingwithbelong.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.codingwithbelong.Adapters.UserAdapter;
import com.example.codingwithbelong.Model.User;
import com.example.codingwithbelong.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LeaderBoardFragment extends Fragment {


   TextView firstName,firstScore,firstTime;
   TextView secondName,secondScore,secondTime;
   TextView thirdName,thirdScore,thirdTime;

   ImageView firstImage,secondImage,thirdImage;

    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    ArrayList<User> mArrayList = new ArrayList<>();
    ArrayList<User> copyArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leaderboard, container, false);

        firstName = view.findViewById(R.id.firstNameTv);
        firstScore = view.findViewById(R.id.firstScoreTv);
        firstTime = view.findViewById(R.id.firstTimeTv);

        secondName = view.findViewById(R.id.secondNameTv);
        secondScore = view.findViewById(R.id.secondScoreTv);
        secondTime = view.findViewById(R.id.secondTimeTv);
        thirdName = view.findViewById(R.id.thirdNameTv);
        thirdScore = view.findViewById(R.id.thirdScoreTv);
        thirdTime = view.findViewById(R.id.thirdTimeTv);

        firstImage = view.findViewById(R.id.firstImage);
        secondImage = view.findViewById(R.id.secondImage);
        thirdImage = view.findViewById(R.id.thirdImage);

        FirebaseFirestore.getInstance().collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    if (queryDocumentSnapshots.isEmpty()) {
                                        Log.d("dklfjdkljf", "onSuccess: LIST EMPTY");
                                        return;
                                    } else {
                                        // Convert the whole Query Snapshot to a list
                                        // of objects directly! No need to fetch each
                                        // document.
                                        List<User> types = queryDocumentSnapshots.toObjects(User.class);


                                        // Add all to your list
                                        mArrayList.addAll(types);
                                        ArrayList<User> allUsers = new ArrayList<>();
                                        for(User user : mArrayList)
                                        {
                                            int sum = user.getFundamental()*10+user.getJava()*10+user.getCpp()*10+user.getPython()*10;
                                            user.setScore(sum);
                                            allUsers.add(user);
                                        }
                                        mArrayList.clear();
                                        mArrayList.addAll(allUsers);
                                        Collections.sort(mArrayList,Collections.reverseOrder());

                                        if(mArrayList.size() >= 3)
                                        {
                                            Glide.with(getContext()).load(mArrayList.get(0).getPhoto())
                                                    .placeholder(R.drawable.peson)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .into(firstImage);
                                            firstName.setText(mArrayList.get(0).getName());
                                            firstScore.setText("Score : "+mArrayList.get(0).getScore());
                                            firstTime.setText("Time:"+calculateTime(mArrayList.get(0).getTime()));

                                            Glide.with(getContext()).load(mArrayList.get(1).getPhoto())
                                                    .placeholder(R.drawable.peson)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .into(secondImage);
                                            secondName.setText(mArrayList.get(1).getName());
                                            secondScore.setText("Score : "+mArrayList.get(1).getScore());
                                            secondTime.setText("Time:"+calculateTime(mArrayList.get(1).getTime()));

                                            Glide.with(getContext()).load(mArrayList.get(2).getPhoto())
                                                    .placeholder(R.drawable.peson)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .into(thirdImage);
                                            thirdName.setText(mArrayList.get(2).getName());
                                            thirdScore.setText("Score : "+mArrayList.get(2).getScore());
                                            thirdTime.setText("Time:"+calculateTime(mArrayList.get(2).getTime()));

                                            for(int i = 3; i < mArrayList.size(); i++)
                                            {
                                                copyArrayList.add(mArrayList.get(i));
                                            }

                                            recyclerView = view.findViewById(R.id.recyclerView);
                                            userAdapter=new UserAdapter(getContext(),copyArrayList);
                                            RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext());
                                            recyclerView.setLayoutManager(manager);
                                           // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                                            recyclerView.setAdapter(userAdapter);

                                        }else if(mArrayList.size() >= 2)
                                        {
                                            Glide.with(getContext()).load(mArrayList.get(0).getPhoto())
                                                    .placeholder(R.drawable.peson)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .into(firstImage);
                                            firstName.setText(mArrayList.get(0).getName());
                                            firstScore.setText("Score : "+mArrayList.get(0).getScore());
                                            firstTime.setText("Time : "+mArrayList.get(0).getTime());

                                            Glide.with(getContext()).load(mArrayList.get(1).getPhoto())
                                                    .placeholder(R.drawable.peson)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .into(secondImage);
                                            secondName.setText(mArrayList.get(1).getName());
                                            secondScore.setText("Score : "+mArrayList.get(1).getScore());
                                            secondTime.setText("Time : "+mArrayList.get(1).getTime());
                                        }else
                                        {
                                            Glide.with(getContext()).load(mArrayList.get(0).getPhoto())
                                                    .placeholder(R.drawable.peson)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .into(firstImage);
                                            firstName.setText(mArrayList.get(0).getName());
                                            firstScore.setText("Score : "+mArrayList.get(0).getScore());
                                            firstTime.setText("Time : "+mArrayList.get(0).getTime());
                                        }

                                      /*  //
                                        Collections.sort(mArrayList, new Comparator() {
                                            @Override
                                            public int compare(Object o1, Object o2) {
                                                User p1 = (User) o1;
                                                User p2 = (User) o2;
                                                return Integer.compare(p1.getScore(),p2.getScore());
                                            }
                                        });*/

                                       /* System.out.println(mArrayList.toString());*/



                                        for(User user : mArrayList)
                                        {
                                            Log.d("dklfjdkljf", "onSuccess:"+user.toString());
                                        }

                                    }
                                }
                            });




        return view;

    }

    public String calculateTime(int time)
    {
        String tTime = "";
        if(time < 60)
        {
            tTime = "0"+":"+"0"+":"+time;
        }else if(time < 3600)
        {
            int minutes = time / 60 ;
            int second = time % 60;
            tTime = "0"+":"+minutes+":"+second;
        }else{
            int hour = time / 3600;
            int s = time % 3600;
            int minutes = s / 60 ;
            int second = s % 60;
            tTime = hour+":"+minutes+":"+second;
        }

        return  tTime;
    }

}