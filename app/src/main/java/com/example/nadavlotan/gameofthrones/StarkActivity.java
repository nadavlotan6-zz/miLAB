package com.example.nadavlotan.gameofthrones;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StarkActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark);

        // initialize the mediaPlayer with the stark theme song
        mediaPlayer = MediaPlayer.create(this, R.raw.stark);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // initialize all stark house
        List<FamilyMember> familyMembers = new ArrayList<>();
        familyMembers.add(new FamilyMember("Eddard (Ned) Stark ", R.drawable.ned));
        familyMembers.add(new FamilyMember("Catelyn Stark", R.drawable.catelyn));
        familyMembers.add(new FamilyMember("Robb Stark", R.drawable.ronn));
        familyMembers.add(new FamilyMember("Sansa Stark", R.drawable.sansa));
        familyMembers.add(new FamilyMember("Arya Stark", R.drawable.arya));
        familyMembers.add(new FamilyMember("Brandon (Bran) Stark", R.drawable.bran));
        familyMembers.add(new FamilyMember("Rickon Stark", R.drawable.rickon));
        familyMembers.add(new FamilyMember("Jon Snow", R.drawable.jon));
        familyMembers.add(new FamilyMember("Benjen Stark", R.drawable.benjen));

        // set the recyclerView with the layout and the adapter
        RecyclerView starkRecyclerView = (RecyclerView) findViewById(R.id.starkRecycleView);
        starkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter listAdapter = new ListAdapter(familyMembers);
        starkRecyclerView.setAdapter(listAdapter);
    }

    // stop the mediaPlayer from playing once returning to the mainActivity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

    // make sure that the music stops when pressing home
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    // set the theme song to start once activity is restarted
    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer = MediaPlayer.create(this, R.raw.stark);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
}
