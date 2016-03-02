package com.ivanmagda.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {

    private String GOOGLE_API_KEY = "AIzaSyALuiLQA72hBXCpyGfmcjG6VxUmLS4NvH0";
    private String YOUTUBE_VIDEO_ID = "pkh5_w9u0pI";
    private String YOUTUBE_PLAYLIST = "PLaE0ZJ1WrZk86D3foAg0FYtVpVDJGrzss";

    private Button playVideoButton;
    private Button playPlaylistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standalone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playVideoButton = (Button)findViewById(R.id.btnPlayVideo);
        playPlaylistButton = (Button)findViewById(R.id.btnPlayList);

        playVideoButton.setOnClickListener(this);
        playPlaylistButton.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btnPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, GOOGLE_API_KEY, YOUTUBE_VIDEO_ID);
                break;
            case R.id.btnPlayList:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, GOOGLE_API_KEY, YOUTUBE_PLAYLIST);
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

}
