package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.OnEventListener;
import com.google.firebase.crash.FirebaseCrash.zza;
import java.util.concurrent.ExecutorService;

/* renamed from: com.google.android.gms.internal.mo */
final class C0177mo implements OnEventListener {
    private final Context mContext;
    private final zza zzbYh;
    private final ExecutorService zzbYn;

    public C0177mo(@NonNull Context context, @NonNull ExecutorService executorService, @Nullable zza zza) {
        this.mContext = context.getApplicationContext();
        this.zzbYn = executorService;
        this.zzbYh = zza;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        if (str != null && !str.equals(AppMeasurement.CRASH_ORIGIN) && this.zzbYh != null) {
            this.zzbYn.submit(new C0165mc(this.mContext, this.zzbYh, str2, j, bundle));
        }
    }
}
