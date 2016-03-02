package com.ivanmagda.flickrbrowser;

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

public class GetRawData {

    private String logTag = GetRawData.class.getSimpleName();
    private String rawUrl;
    private String data;
    private DownloadStatus downloadStatus;

    public GetRawData(String rawUrl) {
        this.rawUrl = rawUrl;
    }
}
