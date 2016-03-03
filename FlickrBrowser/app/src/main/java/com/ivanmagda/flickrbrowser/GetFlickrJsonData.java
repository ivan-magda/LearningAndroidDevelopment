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

        final String flickrItems = "items";
        final String flickrTitle = "title";
        final String flickrMedia = "media";
        final String flickrPhotoUrl = "m";
        final String flickrAuthor = "author";
        final String flickrAuthorId = "author_id";
        final String flickrLink = "link";
        final String flickrTags = "tags";

        try {
            JSONObject jsonData = new JSONObject(getData());
            JSONArray itemsArray = jsonData.getJSONArray(flickrItems);

            // Process on JSON data.
            int length = itemsArray.length();
            for (int i = 0; i < length; i++) {
                // Get JSON item object at specific index.
                JSONObject jsonPhoto = itemsArray.getJSONObject(i);

                // Get fields.
                String title = jsonPhoto.getString(flickrTitle);
                String author = jsonPhoto.getString(flickrAuthor);
                String authorId = jsonPhoto.getString(flickrAuthorId);
                String link = jsonPhoto.getString(flickrLink);
                String tags = jsonPhoto.getString(flickrTags);

                // Get photo URL.
                JSONObject jsonMedia = jsonPhoto.getJSONObject(flickrMedia);
                String photoUrl = jsonMedia.getString(flickrPhotoUrl);

                // Create Photo object and append it to the photo array.
                Photo photo = new Photo(title, author, authorId, link, tags, photoUrl);
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
            return super.doInBackground(params);
        }

        @Override
        protected void onPostExecute(String resultData) {
            super.onPostExecute(resultData);
            processResult();
        }

    }

}
