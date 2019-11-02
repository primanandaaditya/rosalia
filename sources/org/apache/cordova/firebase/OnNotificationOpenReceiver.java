package org.apache.cordova.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class OnNotificationOpenReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntent.addFlags(335544320);
        Bundle data = intent.getExtras();
        data.putBoolean("tap", true);
        FirebasePlugin.sendNotification(data);
        launchIntent.putExtras(data);
        context.startActivity(launchIntent);
    }
}
