package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.firebase.crash.FirebaseCrash.zza;

/* renamed from: com.google.android.gms.internal.mg */
public final class C0169mg extends C0164mb {
    private final boolean zzbYk;

    public C0169mg(@NonNull Context context, @NonNull zza zza, boolean z) {
        super(context, zza);
        this.zzbYk = z;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public final String getErrorMessage() {
        return "Failed to set crash enabled to " + this.zzbYk;
    }

    public final void run() {
        try {
            C0172mj zzFg = this.zzbYh.zzFg();
            if (zzFg == null) {
                Log.e("FirebaseCrash", "Crash api not available");
            } else {
                zzd(zzFg);
            }
        } catch (RemoteException | RuntimeException e) {
            zzg.zza(this.mContext, e);
            Log.e("FirebaseCrash", getErrorMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzd(@NonNull C0172mj mjVar) throws RemoteException {
        mjVar.zzaz(this.zzbYk);
    }
}
