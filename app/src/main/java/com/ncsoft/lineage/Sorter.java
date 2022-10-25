package com.ncsoft.lineage;


import static com.ncsoft.lineage.Hub.NM;
import static com.ncsoft.lineage.MainActivity.DL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Sorter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorter);
        new asyncFunc().execute();
    }

    public class asyncFunc extends AsyncTask<Void, Void, Void> {

        String result;

        SharedPreferences sharedPreferencesU = getSharedPreferences("UTILITY", Context.MODE_PRIVATE);
        String cAdder = sharedPreferencesU.getString(NM, "null");
        String dAdder = sharedPreferencesU.getString(DL, "null");

        String corelnk = "http://azureodysseus.xyz/move.php?to=1&";


        String oneis = "sub_id_1=";

        String namelnk = corelnk + oneis + cAdder;
        String deeplnk = corelnk + oneis + dAdder;

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc;
                if (!cAdder.equals("null")){ //сменил логический ноль на стринг
                    doc = Jsoup.connect(namelnk).get();
                } else {
                    doc = Jsoup.connect(deeplnk).get();
                }
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
            Intent i2 = new Intent(getApplicationContext(), Endpoint.class);
            if (result.equals("adm")) {
                startActivity(i1);
            } else {
                startActivity(i2);
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

    }

}