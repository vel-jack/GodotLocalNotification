package org.godotengine.godot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.net.Uri;
import android.media.RingtoneManager;


public class LocalNotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "Notification";

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

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
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

		manager.notify(notificationId, notification);
	}
}
