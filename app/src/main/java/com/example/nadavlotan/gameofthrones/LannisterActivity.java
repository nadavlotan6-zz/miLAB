package com.example.nadavlotan.gameofthrones;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LannisterActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister);

        // initialize the mediaPLayer with the lannister theme song
        mediaPlayer = MediaPlayer.create(this, R.raw.rains_lannister);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // create the recyclerView object with a reference to the lannisterRecyclerView
        RecyclerView lannisterRecyclerView = (RecyclerView) findViewById(R.id.lannisterRecycleView);
        lannisterRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set all lannister family members
        List<FamilyMember> familyMembers = new ArrayList<>();
        familyMembers.add(new FamilyMember("Tywin Lannister ", R.drawable.tywin));
        familyMembers.add(new FamilyMember("Cersei Lannister", R.drawable.cersei));
        familyMembers.add(new FamilyMember("Jaime Lannister", R.drawable.jaime));
        familyMembers.add(new FamilyMember("Tyrion Lannister", R.drawable.tyrion));
        familyMembers.add(new FamilyMember("Joffrey Baratheon", R.drawable.joffreyb));
        familyMembers.add(new FamilyMember("Myrcella Baratheon", R.drawable.myrcella));
        familyMembers.add(new FamilyMember("Tommen Baratheon", R.drawable.tomm));
        familyMembers.add(new FamilyMember("Lancel Lannister", R.drawable.lancell));


        // set the recyclerView with the layout and the adapter as needed
        lannisterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter listAdapter = new ListAdapter(familyMembers);
        lannisterRecyclerView.setAdapter(listAdapter);
    }

    // make sure that the music stops when returning to MainActivity
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
        mediaPlayer = MediaPlayer.create(this, R.raw.rains_lannister);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
}
