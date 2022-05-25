package com.zfuller.task81_zacharyfuller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.zfuller.task81_zacharyfuller.data.VideoDatabaseHelper;
import com.zfuller.task81_zacharyfuller.model.Video;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {
    ListView videoListView;
    ArrayList<Video> videoArrayList;
    VideoAdapter videoArrayAdapter;
    VideoDatabaseHelper videoDatabaseHelper;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);

        videoDatabaseHelper = new VideoDatabaseHelper(this);

        videoListView = findViewById(R.id.videoListView);

        videoArrayList = videoDatabaseHelper.GetVideos(userId);
        videoArrayAdapter = new VideoAdapter(this, videoArrayList);
        videoListView.setAdapter(videoArrayAdapter);

        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Video video = videoArrayAdapter.getItem(i);

                Intent homePageIntent = new Intent(PlaylistActivity.this, HomeActivity.class);
                homePageIntent.putExtra("userId", userId);
                homePageIntent.putExtra("url", video.getURL());
                startActivity(homePageIntent);
                finish();
            }
        });
    }
}
