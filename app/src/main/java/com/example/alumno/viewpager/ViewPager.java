package com.example.alumno.viewpager;

import android.app.Application;
import android.app.DownloadManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by alumno on 01/04/2016.
 */
public class ViewPager extends Application {
    Tracker mcTracker;
    private static final int TIME_OUT=10000;
    private static final int NUM_RETRY=3;
    private static final String TAG= Application.class.getName();
    private RequestQueue requestQueue;
    private static ViewPager instance;

    public static synchronized ViewPager getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
          super.onCreate();
          instance=this;
          requestQueue= Volley.newRequestQueue(getApplicationContext());

    }
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
    public <T> void add (Request<T> request) {
       request.setTag(TAG);
       request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,NUM_RETRY,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(request);
    }
    public void cancel() {
        requestQueue.cancelAll(TAG);
    }


    public synchronized Tracker getTracker() {
        if (mcTracker==null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = analytics.newTracker("UA-75848039-1");
            mcTracker=t;
        }
        return mcTracker;
    }

}
