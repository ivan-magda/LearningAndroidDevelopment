package com.ivanmagda.flickrbrowser;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by ivanmagda on 08.03.16.
 */
public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public static final String FLICKR_QUERY = "FLICKR_QUERY";
    public static final String PHOTO_TRANSFER = "PHOTO_TRANSFER";

    protected Toolbar activateToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar)findViewById(R.id.app_bar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }

        return toolbar;
    }

    protected Toolbar activateToolbarWithHomeEnabled() {
        activateToolbar();

        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        return toolbar;
    }

}
