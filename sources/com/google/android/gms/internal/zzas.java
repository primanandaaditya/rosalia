package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import java.io.File;

public final class zzas {
    public static zzs zza(Context context, zzan zzan) {
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = new StringBuilder(String.valueOf(packageName).length() + 12).append(packageName).append("/").append(context.getPackageManager().getPackageInfo(packageName, 0).versionCode).toString();
        } catch (NameNotFoundException e) {
        }
        zzs zzs = new zzs(new zzag(file), new zzad(VERSION.SDK_INT >= 9 ? new zzao() : new zzak(AndroidHttpClient.newInstance(str))));
        zzs.start();
        return zzs;
    }
}
