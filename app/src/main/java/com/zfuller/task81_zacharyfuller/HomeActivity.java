package com.zfuller.task81_zacharyfuller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zfuller.task81_zacharyfuller.data.VideoDatabaseHelper;
import com.zfuller.task81_zacharyfuller.model.Video;

public class HomeActivity extends AppCompatActivity {
    EditText editTextURL;
    Button btnPlay, btnAdd, btnPlaylist;
    VideoDatabaseHelper videoDatabaseHelper;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        videoDatabaseHelper = new VideoDatabaseHelper(this);
        editTextURL = findViewById(R.id.editTextURL);
        btnPlay = findViewById(R.id.btnPlay);
        btnAdd = findViewById(R.id.btnAdd);
        btnPlaylist = findViewById(R.id.btnPlaylist);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);

        String url = intent.getStringExtra("url");
        editTextURL.setText(url);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextURL.getText().toString();

                if (!url.isEmpty()) {
                    String newUrl = url.substring(32, 43);
                    Intent i = new Intent(HomeActivity.this, PlayerActivity.class);
                    i.putExtra("url", newUrl);
                    startActivity(i);
                } else {
                    Toast.makeText(HomeActivity.this, "Please enter a url first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextURL.getText().toString();

                if (url == null || url.trim().length() == 0) {
                    Toast.makeText(HomeActivity.this, "Please enter a url first!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Video video = new Video(userId, url);
                videoDatabaseHelper.InsertVideo(video);
                Toast.makeText(HomeActivity.this, "Video added to playlist!", Toast.LENGTH_SHORT).show();
            }
        });

        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlistIntent = new Intent(HomeActivity.this, PlaylistActivity.class);
                playlistIntent.putExtra("userId", userId);
                startActivity(playlistIntent);
            }
        });
    }
}
