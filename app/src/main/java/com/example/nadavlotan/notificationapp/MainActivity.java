package com.example.nadavlotan.notificationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Main Activity has been launched!");

        Button startBtn = (Button) findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "Notification started", Toast.LENGTH_LONG);
                toast.show();
                startAlarm();
            }
        });
    }


    /**
     * Returns a quote from the quotes resource file
     *
     * @param context
     * @return quote - a random quote from the quotes array
     */
    public static String returnQuote(Context context) {
        String[] array = context.getResources().getStringArray(R.array.basketball_quotes);
        String quote = array[new Random().nextInt(array.length)];

        return quote;
    }

    /**
     * start the alarm
     */
    private void startAlarm() {
        Intent i = new Intent(this, NotificationIntentService.class);
        this.startService(i);
        NotificationIntentService.startActionCreate(this);
    }
}