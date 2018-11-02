package com.example.nadavlotan.gameofthrones;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // initialize two button objects with reference to the stark and lannister buttons
        Button starkBtn = (Button) findViewById(R.id.starkBtn);
        Button lannisterBtn = (Button) findViewById(R.id.lannisterBtn);

        // initialize the mediaPlayer with the theme song
        mediaPlayer = MediaPlayer.create(this, R.raw.game_of_thrones);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // OnClick event for starkBtn
        starkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starkIntent = new Intent(v.getContext(), StarkActivity.class);
                mediaPlayer.stop();
                startActivity(starkIntent);
            }
        });

        // OnClick event for lannisterBtn
        lannisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lannisterIntent = new Intent(v.getContext(), LannisterActivity.class);
                mediaPlayer.stop();
                startActivity(lannisterIntent);
            }
        });
    }

    // set the theme song to start once activity is restarted
    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer = MediaPlayer.create(this, R.raw.game_of_thrones);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    // make sure that the music stops when pressing back
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
}