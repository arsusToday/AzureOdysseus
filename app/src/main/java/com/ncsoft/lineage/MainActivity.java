package com.ncsoft.lineage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new asyncFunc().execute();
    }

    public class asyncFunc extends AsyncTask<Void, Void, Void> {

        String result;
        String corelnk = "http://azureodysseus.xyz/checker.txt";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc;
                doc = Jsoup.connect(corelnk).get();
                result = doc.text();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent i1 = new Intent(getApplicationContext(), GameHubWorld1.class);

            Intent i2 = new Intent(getApplicationContext(), Hub.class);
           switch(result){
               case "1":    startActivity(i2);
                   break;
               case "2":    startActivity(i1);
                   break;
           }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

    }
}