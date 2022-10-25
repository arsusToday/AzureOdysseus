package com.ncsoft.lineage;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.onesignal.OneSignal;
import com.orhanobut.hawk.Hawk;

public class Applicationgl extends Application {
    public static final String FUNC_ID = "funcId";
    private static final String ONESIGNAL_APP_ID = "6181129b-19ae-4bec-a0c2-6c589b978aa2";

    @Override
    public void onCreate() {
        super.onCreate();


        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);


        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        gltask.execute();


    }
    @SuppressLint("StaticFieldLeak")
    AsyncTask<Void, Void, String> gltask = new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
            AdvertisingIdClient.Info idInfo = null;
            try {
                idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String advertId = null;
            try {
                advertId = idInfo.getId();
                SharedPreferences sp_wow = getSharedPreferences("UTILITY", MODE_PRIVATE);
                SharedPreferences.Editor wow = sp_wow.edit();
                wow.putString(FUNC_ID, advertId);
                wow.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return advertId;
        }

        @Override
        protected void onPostExecute(String advertId) {


        }
    };
}
