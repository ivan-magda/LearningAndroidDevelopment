package com.ivanmagda.top25freeapps;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by ivanmagda on 01.03.16.
 */
public class ParseApplications {

    private String xmlData;
    private ArrayList<Application> applications;

    public ParseApplications(String xmlData) {
        this.xmlData = xmlData;
        applications = new ArrayList<>();
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }

    public boolean process() {
        boolean status = true;

        Application currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tag = xmlPullParser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d("ParseApplications", "Starting tag for " + tag);

                        if (tag.equalsIgnoreCase("entry")) {
                            inEntry = true;
                            currentRecord = new Application();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xmlPullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
//                        Log.d("ParseApplications", "Ending tag for " + tag);
                        if (inEntry) {
                            if (tag.equalsIgnoreCase("entry")) {
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if (tag.equalsIgnoreCase("name")) {
                                currentRecord.setName(textValue);
                            } else if (tag.equalsIgnoreCase("artist")) {
                                currentRecord.setArtist(textValue);
                            } else if (tag.equalsIgnoreCase("releaseDate")) {
                                currentRecord.setReleaseDate(textValue);
                            }
                        }
                        break;
                    default:
                        break;
                }

                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        for (Application application: applications) {
            Log.d("ParseApplications", "----------------------------------------");
            Log.d("ParseApplications", "Name: " + application.getName());
            Log.d("ParseApplications", "Artist: " + application.getArtist());
            Log.d("ParseApplications", "Release date: " + application.getReleaseDate());
        }

        return status;
    }

}
