package com.example.nadavlotan.gameofthrones;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button starkBtn = (Button) findViewById(R.id.starkBtn);
        Button lannisterBtn = (Button) findViewById(R.id.lannisterBtn);

        starkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starkIntent = new Intent(v.getContext(), StarkActivity.class);
                startActivity(starkIntent);
            }
        });

        lannisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lannisterIntent = new Intent(v.getContext(), LannisterActivity.class);
                startActivity(lannisterIntent);
            }
        });
    }


}
