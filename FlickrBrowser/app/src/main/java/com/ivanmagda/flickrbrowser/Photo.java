package com.ivanmagda.flickrbrowser;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

interface FlickrPhotoKey {

    public static final String Items = "items";
    public static final String Title = "title";
    public static final String Media = "media";
    public static final String PhotoUrl = "m";
    public static final String Author = "author";
    public static final String AuthorId = "author_id";
    public static final String Link = "link";
    public static final String Tags = "tags";

}

/**
 * Created by IvanMagda on 03.03.2016.
 */
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String LOG_TAG = Photo.class.getSimpleName();

    private String title;
    private String author;
    private String authorId;
    private String link;
    private String tags;
    private String image;

    public Photo(String title, String author, String authorId, String link, String tags, String image) {
        this.title = title;
        this.author = author;
        this.authorId = authorId;
        this.link = link;
        this.tags = tags;
        this.image = image;
    }

    public Photo(JSONObject jsonPhoto) {
        try {
            // Get fields.
            title = jsonPhoto.getString(FlickrPhotoKey.Title);
            author = jsonPhoto.getString(FlickrPhotoKey.Author);
            authorId = jsonPhoto.getString(FlickrPhotoKey.AuthorId);
            link = jsonPhoto.getString(FlickrPhotoKey.Link);
            tags = jsonPhoto.getString(FlickrPhotoKey.Tags);

            // Get photo URL.
            JSONObject jsonMedia = jsonPhoto.getJSONObject(FlickrPhotoKey.Media);
            String photoUrl = jsonMedia.getString(FlickrPhotoKey.PhotoUrl);

            image = photoUrl;

        } catch (JSONException jsonError) {
            jsonError.printStackTrace();
            Log.e(LOG_TAG, "Error processing json data");
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getLink() {
        return link;
    }

    public String getTags() {
        return tags;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", authorId='" + authorId + '\'' +
                ", link='" + link + '\'' +
                ", tags='" + tags + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
