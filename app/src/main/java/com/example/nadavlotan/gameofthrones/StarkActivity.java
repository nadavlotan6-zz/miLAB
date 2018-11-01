package com.example.nadavlotan.gameofthrones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark);

        String[] lanisterFamily;
        lanisterFamily = new String[]{"bla","blo","bli"};
        List<FamilyMember> familyMembers = new ArrayList<>();
        familyMembers.add(new FamilyMember("bla",R.drawable.arya));
        familyMembers.add(new FamilyMember("blo",R.drawable.benjen));
        familyMembers.add(new FamilyMember("bli",R.drawable.bran));
        familyMembers.add(new FamilyMember("blo",R.drawable.benjen));
        familyMembers.add(new FamilyMember("bli",R.drawable.bran));
        familyMembers.add(new FamilyMember("blo",R.drawable.benjen));
        familyMembers.add(new FamilyMember("bli",R.drawable.bran));
        familyMembers.add(new FamilyMember("blo",R.drawable.benjen));
        familyMembers.add(new FamilyMember("bli",R.drawable.bran));


        RecyclerView starkRecyclerView = (RecyclerView)findViewById(R.id.starkRecycleView);
        starkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter listAdapter = new ListAdapter(familyMembers);
        starkRecyclerView.setAdapter(listAdapter);

    }
}
