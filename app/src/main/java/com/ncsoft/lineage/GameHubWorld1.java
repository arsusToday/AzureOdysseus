package com.ncsoft.lineage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameHubWorld1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hub_world1);
    }

    public void moveToQuest(View view) {
        startActivity(new Intent(getApplicationContext(), GameWorld1Loaded.class));
        finish();
    }
}