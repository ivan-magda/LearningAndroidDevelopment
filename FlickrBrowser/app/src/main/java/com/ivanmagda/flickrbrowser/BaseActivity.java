package com.ivanmagda.flickrbrowser;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by ivanmagda on 08.03.16.
 */
public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected Toolbar activateToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar)findViewById(R.id.app_bar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }

        return toolbar;
    }
}
