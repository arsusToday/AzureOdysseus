package com.ncsoft.lineage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameWorld2Loaded extends AppCompatActivity {

    int powerScore = 0;
    TextView textView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_world2_loaded);
        textView = findViewById(R.id.textMainRing);

    }

    public void forgeRingPower(View view) {
        powerScore++;
        if (powerScore > 5){
            textView.setText("Great Job, Friend!");

        }
    }
}