package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.zzq;

public final class FirebaseInstanceIdInternalReceiver extends WakefulBroadcastReceiver {
    private static boolean zzbfB = false;
    private static zzh zzckA;
    private static zzh zzckz;

    static synchronized zzh zzH(Context context, String str) {
        zzh zzh;
        synchronized (FirebaseInstanceIdInternalReceiver.class) {
            if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
                if (zzckA == null) {
                    zzckA = new zzh(context, str);
                }
                zzh = zzckA;
            } else {
                if (zzckz == null) {
                    zzckz = new zzh(context, str);
                }
                zzh = zzckz;
            }
        }
        return zzh;
    }

    static boolean zzbH(Context context) {
        return zzq.isAtLeastO() && context.getApplicationInfo().targetSdkVersion > 25;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (!(parcelableExtra instanceof Intent)) {
                Log.e("FirebaseInstanceId", "Missing or invalid wrapped intent");
                return;
            }
            Intent intent2 = (Intent) parcelableExtra;
            if (zzbH(context)) {
                zzH(context, intent.getAction()).zza(intent2, goAsync());
            } else {
                zzq.zzJX().zza(context, intent.getAction(), intent2);
            }
        }
    }
}
