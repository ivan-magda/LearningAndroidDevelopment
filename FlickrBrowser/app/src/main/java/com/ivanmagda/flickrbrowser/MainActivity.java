package com.ivanmagda.flickrbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private List<Photo> photosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        activateToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        flickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(MainActivity.this,
                new ArrayList<Photo>());
        recyclerView.setAdapter(flickrRecyclerViewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int idxPosition) {
                Log.d(LOG_TAG, "Gesture detected at idx: " + Integer.toString(idxPosition));
                Toast.makeText(MainActivity.this, "Normal tap", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int idxPosition) {
                Log.d(LOG_TAG, "Gesture detected at idx: " + Integer.toString(idxPosition));

                Intent intent = new Intent(MainActivity.this, PhotoDetailActivity.class);
                intent.putExtra(PHOTO_TRANSFER, flickrRecyclerViewAdapter.getPhoto(idxPosition));
                startActivity(intent);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menu_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String query = getSavedPreferenceData(FLICKR_QUERY);
        if (query.length() > 0) {
            ProcessPhotos processPhotos = new ProcessPhotos(query, true);
            processPhotos.execute();
        }
    }

    private String getSavedPreferenceData(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(key, "");
    }

    public class ProcessPhotos extends GetFlickrJsonData {

        public ProcessPhotos(String searchCriteria, boolean matchAll) {
            super(searchCriteria, matchAll);
        }

        @Override
        public void execute() {
            super.execute();

            ProcessData processData = new ProcessData();
            processData.execute();
        }

        public class ProcessData extends DownloadJsonData {

            @Override
            protected void onPostExecute(String resultData) {
                super.onPostExecute(resultData);

                photosList = getPhotos();
                flickrRecyclerViewAdapter.reloadWithNewData(photosList);
            }

        }

    }
}
