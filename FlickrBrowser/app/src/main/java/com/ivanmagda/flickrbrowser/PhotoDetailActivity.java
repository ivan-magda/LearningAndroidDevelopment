package com.ivanmagda.flickrbrowser;

import android.content.Intent;
import android.os.Bundle;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.photo_detail);
        activateToolbarWithHomeEnabled();

        Intent intent = getIntent();
        Photo photo = (Photo)intent.getSerializableExtra(PHOTO_TRANSFER);
    }

}
