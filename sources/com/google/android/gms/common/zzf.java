package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzay;
import com.google.android.gms.common.internal.zzaz;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

final class zzf {
    private static zzay zzaAd;
    private static final Object zzaAe = new Object();
    private static Context zzaAf;

    static boolean zza(String str, zzg zzg) {
        return zza(str, zzg, false);
    }

    private static boolean zza(String str, zzg zzg, boolean z) {
        boolean z2 = false;
        if (!zzoX()) {
            return z2;
        }
        zzbo.zzu(zzaAf);
        try {
            return zzaAd.zza(new zzm(str, zzg, z), zzn.zzw(zzaAf.getPackageManager()));
        } catch (RemoteException e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            return z2;
        }
    }

    static synchronized void zzav(Context context) {
        synchronized (zzf.class) {
            if (zzaAf != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzaAf = context.getApplicationContext();
            }
        }
    }

    static boolean zzb(String str, zzg zzg) {
        return zza(str, zzg, true);
    }

    private static boolean zzoX() {
        boolean z = true;
        if (zzaAd == null) {
            zzbo.zzu(zzaAf);
            synchronized (zzaAe) {
                if (zzaAd == null) {
                    try {
                        zzaAd = zzaz.zzJ(DynamiteModule.zza(zzaAf, DynamiteModule.zzaSP, "com.google.android.gms.googlecertificates").zzcV("com.google.android.gms.common.GoogleCertificatesImpl"));
                    } catch (zzc e) {
                        Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e);
                        z = false;
                    }
                }
            }
        }
        return z;
    }
}
