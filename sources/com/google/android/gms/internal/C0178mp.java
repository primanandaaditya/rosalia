package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.firebase.crash.FirebaseCrash.zza;
import java.util.concurrent.ExecutorService;

/* renamed from: com.google.android.gms.internal.mp */
public final class C0178mp {
    private final AppMeasurement zzbYo;

    private C0178mp(AppMeasurement appMeasurement) {
        this.zzbYo = appMeasurement;
    }

    @Nullable
    public static C0178mp zzbE(Context context) {
        try {
            return new C0178mp(AppMeasurement.getInstance(context));
        } catch (NoClassDefFoundError e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseCrashAnalytics", new StringBuilder(String.valueOf(valueOf).length() + 50).append("Unable to log event, missing measurement library: ").append(valueOf).toString());
            return null;
        }
    }

    public final void zza(@NonNull Context context, @NonNull ExecutorService executorService, @Nullable zza zza) {
        this.zzbYo.registerOnMeasurementEventListener(new C0177mo(context, executorService, zza));
    }

    public final void zza(boolean z, long j) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt(Param.FATAL, 1);
        } else {
            bundle.putInt(Param.FATAL, 0);
        }
        bundle.putLong(Param.TIMESTAMP, j);
        this.zzbYo.logEventInternal(AppMeasurement.CRASH_ORIGIN, Event.APP_EXCEPTION, bundle);
    }
}
