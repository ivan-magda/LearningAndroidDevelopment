package com.ivanmagda.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by IvanMagda on 02.03.2016.
 */

enum DownloadStatus {
    Idle,
    Processing,
    NotInitialised,
    FailedOrEmpty,
    Ok
}

interface GetRawDataCallback {
    void didDoneOnExecution();
}

public class GetRawData {

    private String LOG_TAG = GetRawData.class.getSimpleName();

    private String rawUrl;
    private String data;
    private DownloadStatus downloadStatus;

    private GetRawDataCallback callback;

    public GetRawData(String rawUrl) {
        this.rawUrl = rawUrl;
        downloadStatus = DownloadStatus.Idle;
    }

    public String getData() {
        return data;
    }

    public DownloadStatus getDownloadStatus() {
        return downloadStatus;
    }

    public void setCallback(GetRawDataCallback callback) {
        this.callback = callback;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public void reset() {
        downloadStatus = DownloadStatus.Idle;
        rawUrl = null;
        data = null;
    }

    public void execute() {
        downloadStatus = DownloadStatus.Processing;

        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.execute(rawUrl);
    }

    public class DownloadRawData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;

            if (params == null) {
                return null;
            }

            try {
                URL url = new URL(params[0]);

                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                if (inputStream == null) {
                    return null;
                }

                StringBuffer buffer = new StringBuffer();

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                return buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error", e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }

                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String resultData) {
            data = resultData;

            Log.v(LOG_TAG, "Returned data is: " + data);

            if (data == null) {
                // Failed.
                if (rawUrl == null) {
                    downloadStatus = DownloadStatus.NotInitialised;
                } else {
                    downloadStatus = DownloadStatus.FailedOrEmpty;
                }
            } else {
                // Success.
                downloadStatus = DownloadStatus.Ok;
            }

            callback.didDoneOnExecution();
        }
    }

}
