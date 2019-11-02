package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

/* renamed from: com.google.android.gms.internal.ml */
public final class C0174ml {
    private static C0174ml zzbYm;
    private Context mContext;

    private C0174ml() {
    }

    public static synchronized C0174ml zzFj() {
        C0174ml mlVar;
        synchronized (C0174ml.class) {
            if (zzbYm == null) {
                zzbYm = new C0174ml();
            }
            mlVar = zzbYm;
        }
        return mlVar;
    }

    public final C0172mj zzFk() throws C0176mn {
        try {
            DynamiteModule zza = DynamiteModule.zza(this.mContext, DynamiteModule.zzaSN, "com.google.android.gms.crash");
            zzbo.zzu(zza);
            IBinder zzcV = zza.zzcV("com.google.firebase.crash.internal.api.FirebaseCrashApiImpl");
            if (zzcV == null) {
                return null;
            }
            IInterface queryLocalInterface = zzcV.queryLocalInterface("com.google.firebase.crash.internal.IFirebaseCrashApi");
            return queryLocalInterface instanceof C0172mj ? (C0172mj) queryLocalInterface : new C0173mk(zzcV);
        } catch (zzc e) {
            zzg.zza(this.mContext, e);
            throw new C0176mn(e);
        }
    }

    public final void zzav(Context context) {
        this.mContext = context;
    }
}
