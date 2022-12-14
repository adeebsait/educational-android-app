package com.example.codingwithbelong.Fragments;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.codingwithbelong.CourseDetailsActivity;
import com.example.codingwithbelong.HomeActivity;
import com.example.codingwithbelong.LoginActivity;
import com.example.codingwithbelong.Model.Score;
import com.example.codingwithbelong.Model.User;
import com.example.codingwithbelong.R;
import com.example.codingwithbelong.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class QuizFragment extends Fragment {



    public QuizFragment() {
        // Required empty public constructor
    }

    RadioGroup oneRg,twoRg,threeRg,fourRg,fiveRg,sixRg,sevenRg,eightRg,ninRg,tenRg;
    RadioButton radioButton;
    Button submitBtn;
    View view;


    boolean isOne = false,isTwo=false,isThree = false,four = false,five = false,six = false,seven = false,eight = false,
    nine = false, ten = false;

    ProgressBar progressBar;

    String formattedDate;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_quiz, container, false);



       oneRg = view.findViewById(R.id.oneRg);
        twoRg = view.findViewById(R.id.twoRg);
        threeRg = view.findViewById(R.id.threeRg);
        fourRg = view.findViewById(R.id.fourRg);
       fiveRg = view.findViewById(R.id.fiveRg);
       sixRg = view.findViewById(R.id.sixRg);
       sevenRg = view.findViewById(R.id.sevenRg);
       eightRg = view.findViewById(R.id.eightRg);
       ninRg = view.findViewById(R.id.nineRg);
       tenRg = view.findViewById(R.id.tenRg);
       submitBtn = view.findViewById(R.id.btnSubmit);
       progressBar = view.findViewById(R.id.progress_bar);

       //get current date.........................
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        formattedDate = df.format(c);
        Log.d("kdfjdkljfkdljf",formattedDate);
       //get current date.........................



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

                FirebaseFirestore.getInstance().collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()){
                         //   uploadAllData();
                            User user = documentSnapshot.toObject(User.class);
                            updateAllData(user.getScore(),user.getTime(),user.getFundamental());
                            Log.d("dkfjdkljf", user.getFundamental()+"");
                        }
                        else {
                            updateAllData(0,0,0);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });








            }
        });



        return view;

    }

    public void retrivdata()
    {
        progressBar.setVisibility(View.VISIBLE);
        Log.d("dfkjdlkfjl","function calling");
        FirebaseFirestore.getInstance().collection(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();

                    if(myListOfDocuments.isEmpty())
                    {
                        Toast.makeText( getContext(), "Quiz Submitted", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }else
                    {
                        for(DocumentSnapshot documentSnapshot : myListOfDocuments)
                        {
                            if(documentSnapshot.getId().equals(formattedDate))
                            {
                                FirebaseFirestore.getInstance().collection(FirebaseAuth.getInstance().getCurrentUser().getUid()).document(formattedDate).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists())
                                        {
                                            Score score = documentSnapshot.toObject(Score.class);
                                            uploadScore(score.getTime());
                                            Log.d("dfkjdlkfjl",""+score.getTime());
                                        }else {
                                            uploadScore(0);
                                        }
                                    }
                                });
                            }else
                            {
                                uploadScore(0);
                            }
                        }
                    }



                }else{
                    Toast.makeText( getContext(), "Quiz Submitted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploadScore(int previousTime) {

        Map studentMap = new HashMap();

        studentMap.put("time",CourseDetailsActivity.time+previousTime);



        FirebaseFirestore.getInstance().collection(FirebaseAuth.getInstance().getCurrentUser().getUid()).document(formattedDate).set(studentMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText( getContext(), "Updated", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText( getContext(), "Failed at update score "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void updateAllData(int previousScore,int previousTime,int pFundamental) {

        Map studentMap = new HashMap();


        studentMap.put("score",calculateScore()+previousScore);
        studentMap.put("time",CourseDetailsActivity.time+previousTime);
        studentMap.put("ftotal",10);

      //  Log.d("dlkfjdlkjf","previous :"+pFundamental+" current :"+calculateScore());

        if(pFundamental < calculateScore()/10)
        {
            studentMap.put("fundamental",calculateScore()/10);
        }else
        {
            studentMap.put("fundamental",pFundamental);
        }


        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update(studentMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                retrivdata();

               // Toast.makeText(getContext(), "Result submitted successfully !", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Not updated", Toast.LENGTH_SHORT).show();
            }
        });

    }

   public  int calculateScore()
   {
        int sum=0;
       int selectedId1 = oneRg.getCheckedRadioButtonId();
       // find the radiobutton by returned id
       radioButton = (RadioButton) view.findViewById(selectedId1);
       if (oneRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Programming"))
           {
               sum += 10;

           }
       }

       int selectedId2 = twoRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId2);
       if (twoRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Eating food if you???re hungry"))
           {
               sum += 10;
           }
       }

       int selectedId3 = threeRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId3);
       if (threeRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Sequential Selection"))
           {
               sum += 10;
           }
       }

       int selectedId4 = fourRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId4);
       if (fourRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Iteration"))
           {
               sum += 10;
           }
       }

       int selectedId5 = fiveRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId5);
       if (fiveRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Sequentially"))
           {
               sum += 10;
           }
       }

       int selectedId6 = sixRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId6);
       if (sixRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Syntax"))
           {
               sum += 10;
           }
       }

       int selectedId7 = sevenRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId7);
       if (sevenRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Integrated Development Environment (IDE)"))
           {
               sum += 10;
           }
       }

       int selectedId8 = eightRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId8);
       if (eightRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Array"))
           {
               sum += 10;
           }
       }

       int selectedId9 = ninRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId9);
       if (ninRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("False"))
           {
               sum += 10;

           }
       }

       int selectedId10 = tenRg.getCheckedRadioButtonId();
       radioButton = (RadioButton) view.findViewById(selectedId10);
       if (tenRg.getCheckedRadioButtonId() != -1)
       {
           if(radioButton.getText().toString().equals("Verify"))
           {
               sum += 10;
           }

       }


       return  sum;

   }

}