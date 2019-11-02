package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.firebase.crash.FirebaseCrash.zza;

/* renamed from: com.google.android.gms.internal.mb */
abstract class C0164mb implements Runnable {
    protected final Context mContext;
    protected final zza zzbYh;

    C0164mb(@NonNull Context context, @NonNull zza zza) {
        this.zzbYh = zza;
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public abstract String getErrorMessage();

    public void run() {
        try {
            C0172mj zzFg = this.zzbYh.zzFg();
            if (zzFg != null && zzFg.zzFf()) {
                zzd(zzFg);
            } else if (zzFg != null) {
                Log.e("FirebaseCrash", "Firebase Crash Reporting not enabled");
            } else {
                Log.e("FirebaseCrash", "Crash api not available");
            }
        } catch (RemoteException | RuntimeException e) {
            zzg.zza(this.mContext, e);
            Log.e("FirebaseCrash", getErrorMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzd(@NonNull C0172mj mjVar) throws RemoteException;
}
