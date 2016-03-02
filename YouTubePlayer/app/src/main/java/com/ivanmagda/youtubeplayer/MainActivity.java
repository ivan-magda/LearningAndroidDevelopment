package com.ivanmagda.youtubeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playSingleVideoButton;
    private Button standaloneMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playSingleVideoButton = (Button)findViewById(R.id.btnPlaySingleVideo);
        standaloneMenuButton = (Button)findViewById(R.id.btnPlayStandalone);

        playSingleVideoButton.setOnClickListener(this);
        standaloneMenuButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btnPlaySingleVideo:
                intent = new Intent(MainActivity.this, YouTubeActivity.class);
                break;
            case R.id.btnPlayStandalone:
                intent = new Intent(MainActivity.this, StandaloneActivity.class);
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
