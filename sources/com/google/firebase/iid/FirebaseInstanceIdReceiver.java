package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;

public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        String str = null;
        int i = -1;
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        String stringExtra2 = intent.getStringExtra("from");
        if ("google.com/iid".equals(stringExtra2) || "gcm.googleapis.com/refresh".equals(stringExtra2)) {
            str = "com.google.firebase.INSTANCE_ID_EVENT";
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            str = "com.google.firebase.MESSAGING_EVENT";
        } else {
            Log.d("FirebaseInstanceId", "Unexpected intent");
        }
        if (str != null) {
            if (FirebaseInstanceIdInternalReceiver.zzbH(context)) {
                if (isOrderedBroadcast()) {
                    setResultCode(-1);
                }
                FirebaseInstanceIdInternalReceiver.zzH(context, str).zza(intent, goAsync());
            } else {
                i = zzq.zzJX().zza(context, str, intent);
            }
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }
}
