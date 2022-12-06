package com.example.codingwithbelong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    List<AuthUI.IdpConfig> provider;
    int AuthUI_Request_Code=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        provider= Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());
        gotoSignInMethods();


    }

    private void gotoSignInMethods() {

        Intent intent= AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false).
                setAvailableProviders(provider).setTheme(R.style.Theme_CodingWithBelong).build();

        startActivityForResult(intent,AuthUI_Request_Code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==AuthUI_Request_Code){
            if (resultCode==RESULT_OK){




                String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

                FirebaseFirestore.getInstance().collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()){
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            finish();
                        }
                        else {
                            startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
            else {

                IdpResponse response= IdpResponse.fromResultIntent(data);
                if (response==null){

                    Toast.makeText(this, "you have canceled the sign in process", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {

                    Toast.makeText(this, response.getError().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}