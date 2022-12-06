package com.example.codingwithbelong.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.codingwithbelong.Model.ScreenTime;
import com.example.codingwithbelong.MyInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScreenTimeManager {

    public static long prevTime = 0;

    public static long startTime=0;

    private static String stId="";


    public static void saveTimeIn() {
        String uid=FirebaseAuth.getInstance().getUid();
        ScreenTime st = new ScreenTime(uid, getDateMillis(), 0);
        FirebaseFirestore.getInstance().collection("ScreenTime").add(st).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                stId=task.getResult().getId();
                Log.d("mytestdata", "stId = "+stId);
                Log.d("mytestdata", "success saveTimeIn()");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("mytestdata", "failed saveTimeIn()");
            }
        });

    }

    public static void updateSessionTime(MyInterface lambda) {
        String uid=FirebaseAuth.getInstance().getUid();
        long sessionTime=prevTime+(System.currentTimeMillis()-startTime);
        prevTime=sessionTime;
        startTime=System.currentTimeMillis();
        FirebaseFirestore.getInstance().collection("ScreenTime").
                document(stId).update("sessionTime", sessionTime).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(lambda!=null) {
                    lambda.run();
                }
                Log.d("mytestdata", "success updateSessionTime()");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("mytestdata", "failed updateSessionTime()");
            }
        });
    }

    private static long getDateMillis(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(getCurrentDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        long millis = date.getTime();
        return millis;
    }

    public static String getCurrentDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault()).format(new Date());

        return date;
    }


}
