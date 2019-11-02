package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.firebase.crash.FirebaseCrash.zza;

/* renamed from: com.google.android.gms.internal.me */
public final class C0167me extends C0164mb {
    private final String zzbYi;

    public C0167me(@NonNull Context context, @NonNull zza zza, @NonNull String str) {
        super(context, zza);
        this.zzbYi = str;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public final String getErrorMessage() {
        return "Failed to log message";
    }

    public final /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    /* access modifiers changed from: protected */
    public final void zzd(@NonNull C0172mj mjVar) throws RemoteException {
        mjVar.log(this.zzbYi);
    }
}
