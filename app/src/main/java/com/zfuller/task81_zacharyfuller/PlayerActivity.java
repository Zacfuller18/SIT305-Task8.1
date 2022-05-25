package com.zfuller.task81_zacharyfuller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.zfuller.task81_zacharyfuller.data.VideoDatabaseHelper;

public class PlayerActivity extends YouTubeBaseActivity {
    YouTubePlayerView youtubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        youtubePlayerView = findViewById(R.id.youtube_player_view);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(url);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(PlayerActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();

            }
        };
        youtubePlayerView.initialize("AIzaSyAnD-8RbcDPZVqBtnJj8jmTw8wFxvbuWhM", onInitializedListener);
    }
}


// Used https://www.youtube.com/watch?v=qAHMCZBwYo4 for the majority of the player section of the project