package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzbha;

public class zze {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zze zzaAc = new zze();

    zze() {
    }

    @Nullable
    public static Intent zza(Context context, int i, @Nullable String str) {
        switch (i) {
            case 1:
            case 2:
                return (context == null || !zzj.zzaH(context)) ? zzai.zzw("com.google.android.gms", zzx(context, str)) : zzai.zzrD();
            case 3:
                return zzai.zzcD("com.google.android.gms");
            default:
                return null;
        }
    }

    public static void zzas(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzo.zzah(context);
    }

    public static void zzat(Context context) {
        zzo.zzat(context);
    }

    public static int zzau(Context context) {
        return zzo.zzau(context);
    }

    public static boolean zze(Context context, int i) {
        return zzo.zze(context, i);
    }

    public static zze zzoW() {
        return zzaAc;
    }

    private static String zzx(@Nullable Context context, @Nullable String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append("-");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        if (context != null) {
            try {
                sb.append(zzbha.zzaP(context).getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return sb.toString();
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, null);
    }

    public String getErrorString(int i) {
        return zzo.getErrorString(i);
    }

    @Nullable
    @Deprecated
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return zzo.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int isGooglePlayServicesAvailable = zzo.isGooglePlayServicesAvailable(context);
        if (zzo.zze(context, isGooglePlayServicesAvailable)) {
            return 18;
        }
        return isGooglePlayServicesAvailable;
    }

    public boolean isUserResolvableError(int i) {
        return zzo.isUserRecoverableError(i);
    }

    @Nullable
    public final PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        Intent zza = zza(context, i, str);
        if (zza == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i2, zza, 268435456);
    }

    @Nullable
    @Deprecated
    public final Intent zzak(int i) {
        return zza(null, i, null);
    }
}
