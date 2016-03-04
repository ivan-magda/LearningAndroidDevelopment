package com.ivanmagda.flickrbrowser;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IvanMagda on 03.03.2016.
 */
public class GetFlickrJsonData extends GetRawData {

    private String LOG_TAG = GetFlickrJsonData.class.getSimpleName();
    private List<Photo> photos;
    private Uri destinationUri;

    public GetFlickrJsonData(String searchCriteria, boolean matchAll) {
        super(null);

        photos = new ArrayList<>();
        createAndUpdateUrl(searchCriteria, matchAll);
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public boolean createAndUpdateUrl(String searchCriteria, boolean matchAll) {
        final String flickrApiBaseUrl = "https://api.flickr.com/services/feeds/photos_public.gne";
        final String tagsParam = "tags";
        final String tagMode = "tagmode";
        final String formatParam = "format";
        final String noJsonCallbackParam = "nojsoncallback";

        destinationUri = Uri.parse(flickrApiBaseUrl).buildUpon()
                .appendQueryParameter(tagsParam, searchCriteria)
                .appendQueryParameter(tagMode, matchAll ? "ALL" : "ANY")
                .appendQueryParameter(formatParam, "json")
                .appendQueryParameter(noJsonCallbackParam, "1")
                .build();

        return destinationUri != null;
    }

    public void execute() {
        super.setRawUrl(destinationUri.toString());

        Log.v(LOG_TAG, "Builted URL: " + destinationUri.toString());

        DownloadJsonData downloadJsonData = new DownloadJsonData();
        downloadJsonData.execute(destinationUri.toString());
    }

    public void processResult() {
        if (getDownloadStatus() != DownloadStatus.Ok) {
            Log.e(LOG_TAG, "Error downloading raw file");
            return;
        }

        try {
            JSONObject jsonData = new JSONObject(getData());
            JSONArray itemsArray = jsonData.getJSONArray(FlickrPhotoKey.Items);

            // Process on JSON data.
            int length = itemsArray.length();
            for (int i = 0; i < length; i++) {
                // Get JSON item object at specific index.
                JSONObject jsonPhoto = itemsArray.getJSONObject(i);

                // Create Photo object and append it to the photo array.
                Photo photo = new Photo(jsonPhoto);
                Log.v(LOG_TAG, photo.toString());

                photos.add(photo);
            }
        } catch (JSONException jsonError) {
            jsonError.printStackTrace();
            Log.e(LOG_TAG, "Error processing json data");
        }
    }

    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected String doInBackground(String... params) {
            String[] param = { destinationUri.toString() };

            return super.doInBackground(param);
        }

        @Override
        protected void onPostExecute(String resultData) {
            super.onPostExecute(resultData);
            processResult();
        }

    }

}
