package org.apache.cordova.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import java.util.Random;

public class FirebasePluginMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebasePlugin";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title;
        String text;
        String id;
        if (remoteMessage.getNotification() != null) {
            title = remoteMessage.getNotification().getTitle();
            text = remoteMessage.getNotification().getBody();
            id = remoteMessage.getMessageId();
        } else {
            title = (String) remoteMessage.getData().get("title");
            text = (String) remoteMessage.getData().get("text");
            id = (String) remoteMessage.getData().get("id");
        }
        if (TextUtils.isEmpty(id)) {
            id = Integer.toString(new Random().nextInt(50) + 1);
        }
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message id: " + id);
        Log.d(TAG, "Notification Message Title: " + title);
        Log.d(TAG, "Notification Message Body/Text: " + text);
        if (!TextUtils.isEmpty(text) || !TextUtils.isEmpty(title) || !remoteMessage.getData().isEmpty()) {
            sendNotification(id, title, text, remoteMessage.getData(), (FirebasePlugin.inBackground() || !FirebasePlugin.hasNotificationsCallback()) && (!TextUtils.isEmpty(text) || !TextUtils.isEmpty(title)));
        }
    }

    private void sendNotification(String id, String title, String messageBody, Map<String, String> data, boolean showNotification) {
        Bundle bundle = new Bundle();
        for (String key : data.keySet()) {
            bundle.putString(key, (String) data.get(key));
        }
        if (showNotification) {
            Intent intent = new Intent(this, OnNotificationOpenReceiver.class);
            intent.putExtras(bundle);
            Builder notificationBuilder = new Builder(this).setContentTitle(title).setContentText(messageBody).setVisibility(1).setStyle(new BigTextStyle().bigText(messageBody)).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getBroadcast(this, id.hashCode(), intent, 134217728));
            int resID = getResources().getIdentifier("notification_icon", "drawable", getPackageName());
            if (resID != 0) {
                notificationBuilder.setSmallIcon(resID);
            } else {
                notificationBuilder.setSmallIcon(getApplicationInfo().icon);
            }
            if (VERSION.SDK_INT >= 23) {
                notificationBuilder.setColor(getResources().getColor(getResources().getIdentifier("accent", "color", getPackageName()), null));
            }
            Notification notification = notificationBuilder.build();
            if (VERSION.SDK_INT >= 21) {
                int notiID = getResources().getIdentifier("notification_big", "drawable", getPackageName());
                if (notification.contentView != null) {
                    notification.contentView.setImageViewResource(16908294, notiID);
                }
            }
            ((NotificationManager) getSystemService("notification")).notify(id.hashCode(), notification);
            return;
        }
        bundle.putBoolean("tap", false);
        bundle.putString("title", title);
        bundle.putString("body", messageBody);
        FirebasePlugin.sendNotification(bundle);
    }
}
