package com.example.alumno.viewpager;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by alumno on 01/04/2016.
 */
public class ViewPager extends Application {
    Tracker mcTracker;


    public synchronized Tracker getTracker() {
        if (mcTracker==null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = analytics.newTracker("UA-75848039-1");
            mcTracker=t;
        }
        return mcTracker;
    }

}
