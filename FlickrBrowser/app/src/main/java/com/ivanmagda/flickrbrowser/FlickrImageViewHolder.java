package com.ivanmagda.flickrbrowser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ivanmagda on 04.03.16.
 */
public class FlickrImageViewHolder extends RecyclerView.ViewHolder {

    protected ImageView thumbnail;
    protected TextView title;

    public FlickrImageViewHolder(View itemView) {
        super(itemView);

        thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
        title = (TextView)itemView.findViewById(R.id.photoTitle);
    }

}
