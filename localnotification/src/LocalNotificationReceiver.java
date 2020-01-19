package org.godotengine.godot;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.net.Uri;
import android.media.RingtoneManager;


public class LocalNotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "Notification";
    private static final String DEFAULT_CHANNEL_ID = "10001";
    private static final String DEFAULT_CHANNEL_NAME = "Notify";

	@Override
	public void onReceive(Context context, Intent intent) {
		int notificationId = intent.getIntExtra("notification_id", 0);
		String message = intent.getStringExtra("message");
        String title = intent.getStringExtra("title");
        Log.i(TAG, "Receive notification: "+message);

		Intent intent2 = new Intent(context, com.godot.game.GodotApp.class);
		intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2,
				0);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context,DEFAULT_CHANNEL_ID);
        builder.setContentTitle(title);
		builder.setContentText(message);
		builder.setSmallIcon(R.drawable.icon);
		builder.setTicker(message);
		builder.setAutoCancel(true);
		builder.setDefaults(Notification.DEFAULT_ALL);
		builder.setContentIntent(pendingIntent);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));        

//        builder.setColor(0xff0000ff);
        Notification notification = builder.build();

		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		
        // notification channel
		NotificationChannel nc = null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			nc = new NotificationChannel(DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
			nc.enableLights(true);
			nc.enableVibration(true);
			nc.setLightColor(Color.GRAY);
			nc.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
		}
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			manager.createNotificationChannel(nc);
	}
		manager.notify(notificationId, notification);
	}
}
