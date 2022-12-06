package com.example.codingwithbelong;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {

    EditText nameEt, bioEt;
    CircleImageView profile_Iv;
    Uri resultUri = null;
    ProgressBar progress_bar;
    String profileImageLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEt = findViewById(R.id.nameEt);
        bioEt = findViewById(R.id.bioEt);
        profile_Iv = findViewById(R.id.profile_Iv);
        progress_bar = findViewById(R.id.progress_bar);
    }

    public void uploadData(View view) {
        if (resultUri == null) {
            resultUri =  Uri.parse("android.resource://com.example.codingwithbelong/" + R.drawable.peson);
        }
        if (nameEt.getText().toString().length() < 1) {
            nameEt.setError("Enter name");
            return;
        }

        if (bioEt.getText().toString().length() < 1) {
            bioEt.setError("Enter bio");
            return;
        }

        progress_bar.setVisibility(View.VISIBLE);
        uploadPI();

    }

    public void checkPermission(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            askPermission();
        } else {


            Intent gallery = new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery, "Select Image"), 1);
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent gallery = new Intent();
                    gallery.setType("image/*");
                    gallery.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(gallery, "Select Image"), 1);


                }
                break;

            }


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            resultUri = data.getData();
            profile_Iv.setImageURI(resultUri);

        }
    }


    private void uploadPI() {

        FirebaseStorage.getInstance().getReference().child("ProfileImages").child(FirebaseAuth.getInstance().getCurrentUser().getUid()+".jpg").putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                FirebaseStorage.getInstance().getReference().child("ProfileImages").child(FirebaseAuth.getInstance().getCurrentUser().getUid()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        profileImageLink=uri.toString();
                        uploadAllData();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progress_bar.setVisibility(View.GONE);
                        Toast.makeText(RegistrationActivity.this, "Failed : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progress_bar.setVisibility(View.GONE);
                Toast.makeText(RegistrationActivity.this, "Failed : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void uploadAllData() {

        Map studentMap = new HashMap();

        studentMap.put("name", nameEt.getText().toString());
        studentMap.put("bio", bioEt.getText().toString());
        studentMap.put("photo", profileImageLink);
        studentMap.put("time", 0);
        studentMap.put("score",0);
        studentMap.put("fundamental",0);
        studentMap.put("ftotal",0);
        studentMap.put("python",0);
        studentMap.put("ptotal", 0);
        studentMap.put("java",0);
        studentMap.put("jtotal",0);
        studentMap.put("cpp",0);
        studentMap.put("cpptotal",0);


        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(studentMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

               progress_bar.setVisibility(View.GONE);
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                Toast.makeText(RegistrationActivity.this, "Account created successfully !", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progress_bar.setVisibility(View.GONE);
                Toast.makeText( RegistrationActivity.this, "Failed : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}