package com.example.nadavlotan.gameofthrones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LannisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister);

        RecyclerView lannisterRecyclerView = (RecyclerView)findViewById(R.id.lannisterRecyclerView);
        lannisterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
