package com.ncsoft.lineage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                startActivity(new Intent(getApplicationContext(), GameHubWorld1.class));
                finish();
            }, 3500);
            Snackbar.make(view, "Try it again!", BaseTransientBottomBar.LENGTH_LONG).show();

        }
    }
}