package com.example.alumno.viewpager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {
    private ViewPager guide_pager;
    ImageView imageView;
    SharedPreferences pref;
   public static final String PREFERENCES_FILE_NAME="MyAppPreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),
                "GitHub", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),
                "GitHub2", Toast.LENGTH_SHORT).show();

        pref = getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);


        GuideAdapter adapter = new GuideAdapter(this);
        guide_pager = (ViewPager)findViewById(R.id.guide_pager);
        guide_pager.setAdapter(adapter);
        if (pref.getBoolean("visto",false)==true){
               guide_pager.setCurrentItem(6);
        }
        //NUMERO DE PAGINAS RETENIDAS AMBOS LADOS
        guide_pager.setOffscreenPageLimit(6);
        guide_pager.setOnPageChangeListener(new
                                                    ViewPager.OnPageChangeListener() {
                                                        @Override
                                                        public void onPageScrolled(int i, float v, int i2) {
                                                        }

                                                        //AL SELECCIONAR UNA NUEVA PAGINA
                                                        @Override
                                                        public void onPageSelected(int i) {
                                                            if (i == 5) {
                                                                SharedPreferences.Editor edit = pref.edit();
                                                                edit.putBoolean("visto", true);
                                                                edit.commit();
                                                                // Get tracker.
                                                                Tracker t = ((com.example.alumno.viewpager.ViewPager)
                                                                        getApplication()).getTracker();
                                                                t.setScreenName("Android - Home");
                                                                t.send(new HitBuilders.AppViewBuilder().build());
                                                                guide_pager.setOnTouchListener(new
                                                                                                       View.OnTouchListener() {
                                                                                                           public boolean onTouch(View arg0, MotionEvent
                                                                                                                   arg1) {
                                                                                                               return true;
                                                                                                           }
                                                                                                       });
                                                            }
                                                        }

                                                        @Override
                                                        public void onPageScrollStateChanged(int i) {
                                                        }
                                                    });

    }

    private void Resolucion()
    {
        Resources resources = getResources();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        switch(metrics.densityDpi)
        {
            case DisplayMetrics.DENSITY_XXXHIGH: //HDPI
                Toast.makeText(getApplication(), "EXTRA EXTRA EXTRA ALTA DENSIDAD", Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_XXHIGH: //HDPI
                Toast.makeText(getApplication(),"EXTRA EXTRA ALTA DENSIDAD",Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_XHIGH: //HDPI
                Toast.makeText(getApplication(),"EXTRA ALTA DENSIDAD",Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_HIGH: //HDPI
                Toast.makeText(getApplication(),"ALTA DENSIDAD",Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_MEDIUM: //MDPI
                Toast.makeText(getApplication(),"MEDIANA DENSIDAD",Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_LOW:  //LDPI
                Toast.makeText(getApplication(),"BAJA DENSIDAD",Toast.LENGTH_LONG).show();
                break;
        }
    }

}


