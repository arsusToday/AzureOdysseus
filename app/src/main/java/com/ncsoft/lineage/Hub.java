package com.ncsoft.lineage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.applinks.AppLinkData;

import java.util.Map;
import java.util.Objects;

public class Hub extends AppCompatActivity {

    public static final String NM = "nm";

    private static final String AF_DEV_KEY = "NEqquebBApzVHm3tnRLk3W";
    public MutableLiveData<String> mCurrentIndex = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        SharedPreferences sharedPreferences = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("activity_exec", false)) {
            Intent intent = new Intent(this, Sorter.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor exec = sharedPreferences.edit();
            exec.putBoolean("activity_exec", true);
            exec.apply();
        }

        appsflyer();


        mCurrentIndex.observe(this, newStringValue -> {
            // Update the UI, in this case, a TextView.
            startActivity(new Intent(getApplicationContext(), Sorter.class));
            handler.removeCallbacks(runnableCode);
            finish();
        });

      handler.post(runnableCode);


    }

    final Handler handler = new Handler();
    Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 2000);
            SharedPreferences prefs = getSharedPreferences("UTILITY", Context.MODE_PRIVATE);
            String stCheck = prefs.getString(NM, null);
            if (stCheck != null) {
                mCurrentIndex.setValue(stCheck);
                Log.d("dev_logs", stCheck);
            }
        }
    };




    public void appsflyer()  {
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {

            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {

                String stMain;

                Log.d("TESTING_ZONE", "af stat is " + conversionData.get("af_status"));

                if(Objects.equals(conversionData.get("af_status"), "Organic")){
                    stMain = "null";
                } else
                {
                stMain = (String) conversionData.get("campaign");}


                SharedPreferences prefs = getSharedPreferences("UTILITY", Context.MODE_PRIVATE);
                SharedPreferences.Editor executor = prefs.edit();
                executor.putString(NM, stMain);
                executor.apply();

                Log.d("dev_logs", stMain);

                Log.d("NAMING TEST", "campaign attributed: " + stMain);
            }

            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                for (String attrName : attributionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
                }

            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
            }

        };


        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);


    }



}