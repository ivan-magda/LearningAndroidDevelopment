package com.ivanmagda.flickrbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ivanmagda on 04.03.16.
 */
public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrImageViewHolder> {

    private static final String LOG_TAG = FlickrRecyclerViewAdapter.class.getSimpleName();

    private List<Photo> photosList;
    private Context context;

    public FlickrRecyclerViewAdapter(Context context, List<Photo> photosList) {
        this.photosList = photosList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position) {
        Photo item = photosList.get(position);

        Log.d(LOG_TAG, "Processing photo item: " + item.getTitle() + "--> " + Integer.toString(position));

        Picasso.with(context).load(item.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return (photosList != null ? photosList.size() : 0);
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, null);
        FlickrImageViewHolder flickrImageViewHolder = new FlickrImageViewHolder(view);

        return flickrImageViewHolder;
    }
}
