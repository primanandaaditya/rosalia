package com.google.firebase.crash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.internal.C0172mj;
import com.google.android.gms.internal.C0174ml;
import com.google.android.gms.internal.C0176mn;
import com.google.android.gms.internal.C0179mq;
import com.google.android.gms.internal.zzcaf;
import com.google.firebase.FirebaseApp;

public final class zzc {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final FirebaseApp zzbVZ;
    /* access modifiers changed from: private */
    public String zzbYc = null;

    zzc(@NonNull FirebaseApp firebaseApp, @Nullable String str) {
        this.mContext = firebaseApp.getApplicationContext();
        this.zzbVZ = firebaseApp;
    }

    @VisibleForTesting
    public final C0172mj zzFi() {
        C0172mj mjVar;
        C0179mq.initialize(this.mContext);
        if (((Boolean) zzcaf.zzuc().zzb(C0179mq.zzbYp)).booleanValue()) {
            try {
                C0174ml.zzFj().zzav(this.mContext);
                mjVar = C0174ml.zzFj().zzFk();
                String str = "FirebaseCrash";
                try {
                    String valueOf = String.valueOf(C0174ml.zzFj());
                    Log.i(str, new StringBuilder(String.valueOf(valueOf).length() + 33).append("FirebaseCrash reporting loaded - ").append(valueOf).toString());
                    return mjVar;
                } catch (C0176mn e) {
                    e = e;
                }
            } catch (C0176mn e2) {
                e = e2;
                mjVar = null;
                Log.e("FirebaseCrash", "Failed to load crash reporting", e);
                zzg.zza(this.mContext, e);
                return mjVar;
            }
        } else {
            Log.w("FirebaseCrash", "Crash reporting is disabled");
            return null;
        }
    }
}
