package com.ncsoft.lineage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Array;
import java.util.ArrayList;

public class GameWorld1Loaded extends AppCompatActivity {
    ImageView tree, tree2, tree3, tree4, tree5, tree6, tree7, tree8, tree9, tree10, tree11, tree12, elm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_world1_loaded);
        tree = findViewById(R.id.tree1);
        tree2 = findViewById(R.id.tree2);
        tree3 = findViewById(R.id.tree3);
        tree4 = findViewById(R.id.tree4);
        tree5 = findViewById(R.id.tree5);
        tree6 = findViewById(R.id.tree6);
        tree7 = findViewById(R.id.tree7);
        tree8 = findViewById(R.id.tree8);
        tree9 = findViewById(R.id.tree9);
        tree10 = findViewById(R.id.tree10);
        tree11 = findViewById(R.id.tree11);
        tree12 = findViewById(R.id.tree12);
        elm = findViewById(R.id.element);
        ArrayList<View> viewsList =new ArrayList<>();
// adding views to array list
        viewsList.add(tree);
        viewsList.add(tree2);
        viewsList.add(tree3);
        viewsList.add(tree4);
        viewsList.add(tree5);
        viewsList.add(tree6);
        viewsList.add(tree7);
        viewsList.add(tree8);
        viewsList.add(tree9);
        viewsList.add(tree10);
        viewsList.add(tree11);
        for (View view :
             viewsList) {
            view.setOnClickListener(view1 -> {
                Toast.makeText(GameWorld1Loaded.this, "Nope, no ring here!", Toast.LENGTH_SHORT).show();
                view1.setVisibility(View.INVISIBLE);
            });
        }
    }



    public void finder(View view) {
        tree12.setVisibility(View.INVISIBLE);
        elm.setVisibility(View.VISIBLE);
        tree.setVisibility(View.INVISIBLE);
        tree2.setVisibility(View.INVISIBLE);
        tree3.setVisibility(View.INVISIBLE);
        tree4.setVisibility(View.INVISIBLE);
        tree5.setVisibility(View.INVISIBLE);
        tree6.setVisibility(View.INVISIBLE);
        tree7.setVisibility(View.INVISIBLE);
        tree8.setVisibility(View.INVISIBLE);
        tree9.setVisibility(View.INVISIBLE);
        tree10.setVisibility(View.INVISIBLE);
        tree11.setVisibility(View.INVISIBLE);
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), GameWorld2Loaded.class));
            finish();
        }, 3500);
        Snackbar.make(view, "You found the ring!", BaseTransientBottomBar.LENGTH_LONG).show();
    }
}