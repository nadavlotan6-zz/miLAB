package com.example.nadavlotan.notificationapp;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class NotificationIntentService extends IntentService {
    private static final String ACTION_CREATE = "com.example.nadavlotan.notificationapp.action.ACTION_CREATE";
    private static final String ACTION_NOTIFY = "com.example.nadavlotan.notificationapp.action.ACTION_NOTIFY";
    private static final String CHANNEL_ID = "NotiChannel";
    protected int m_ID = 1000;

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionCreate(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_CREATE);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionNotify(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_NOTIFY);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CREATE.equals(action)) {
                handleActionCreate();
            } else if (ACTION_NOTIFY.equals(action)) {
                handleActionNotify();
            }
        }
    }

    /**
     * Handle action create
     */
    private void handleActionCreate() {
        Intent intent = new Intent(this, NotificationReciever.class);
        intent.setAction(ACTION_NOTIFY);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_FIFTEEN_MINUTES / (15), pendingIntent);

        createNotiChannel();
    }

    /**
     * Handle action notify
     */
    private void handleActionNotify() {
        String quote = MainActivity.returnQuote(this);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.nba_l)
                .setContentTitle("Quote of the minute:")
                .setContentText(quote)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(quote))
                .setVibrate(new long[]{500, 500, 500, 500, 500})
                .setSound(alarmSound)
                .setLights(Color.GREEN, 1000, 4000);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(m_ID++, notificationBuilder.build());
    }

    private void createNotiChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}