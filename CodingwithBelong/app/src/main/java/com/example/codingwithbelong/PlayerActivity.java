package com.example.codingwithbelong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.codingwithbelong.util.ScreenTimeManager;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity {

    YouTubePlayerView v1Player;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle bundle=getIntent().getExtras();
        String id=bundle.getString("key");



        v1Player=findViewById(R.id.v1Player);

        onInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(id);

            }



            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                Toast.makeText(PlayerActivity.this, ""+youTubeInitializationResult, Toast.LENGTH_SHORT).show();
            }


        };

        v1Player.initialize("",onInitializedListener);
    }

    @Override
    protected void onPause() {
        Log.d("mytestdata", "PlayerActivity: onPause()");
        ScreenTimeManager.updateSessionTime(null);
        super.onPause();
    }

}