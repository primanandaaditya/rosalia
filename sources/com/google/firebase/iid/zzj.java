package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;
import org.apache.cordova.globalization.Globalization;

public final class zzj {
    private static Map<String, zzj> zzbgQ = new ArrayMap();
    static String zzbgW;
    private static zzr zzckF;
    private static zzl zzckG;
    private Context mContext;
    private KeyPair zzbgT;
    private String zzbgU = "";

    private zzj(Context context, String str, Bundle bundle) {
        this.mContext = context.getApplicationContext();
        this.zzbgU = str;
    }

    public static zzr zzJT() {
        return zzckF;
    }

    public static zzl zzJU() {
        return zzckG;
    }

    public static synchronized zzj zzb(Context context, Bundle bundle) {
        zzj zzj;
        synchronized (zzj.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            String str = string == null ? "" : string;
            Context applicationContext = context.getApplicationContext();
            if (zzckF == null) {
                zzckF = new zzr(applicationContext);
                zzckG = new zzl(applicationContext);
            }
            zzbgW = Integer.toString(FirebaseInstanceId.zzbF(applicationContext));
            zzj = (zzj) zzbgQ.get(str);
            if (zzj == null) {
                zzj = new zzj(applicationContext, str, bundle);
                zzbgQ.put(str, zzj);
            }
        }
        return zzj;
    }

    public final long getCreationTime() {
        return zzckF.zzhk(this.zzbgU);
    }

    public final String getToken(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        boolean z = true;
        if (bundle.getString("ttl") != null || "jwt".equals(bundle.getString(Globalization.TYPE))) {
            z = false;
        } else {
            zzs zzp = zzckF.zzp(this.zzbgU, str, str2);
            if (zzp != null && !zzp.zzhp(zzbgW)) {
                return zzp.zzbPJ;
            }
        }
        String zzc = zzc(str, str2, bundle);
        if (zzc == null || !z) {
            return zzc;
        }
        zzckF.zza(this.zzbgU, str, str2, zzc, zzbgW);
        return zzc;
    }

    public final void zzb(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zzckF.zzg(this.zzbgU, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("delete", "1");
        zzc(str, str2, bundle);
    }

    public final String zzc(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        if (!"".equals(this.zzbgU)) {
            str = this.zzbgU;
        }
        bundle.putString("subtype", str);
        bundle.putString("X-subtype", str);
        Intent zza = zzckG.zza(bundle, zzvK());
        if (zza == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String stringExtra = zza.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = zza.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            return stringExtra;
        }
        String stringExtra2 = zza.getStringExtra("error");
        if (stringExtra2 != null) {
            throw new IOException(stringExtra2);
        }
        String valueOf = String.valueOf(zza.getExtras());
        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 29).append("Unexpected response from GCM ").append(valueOf).toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    /* access modifiers changed from: 0000 */
    public final KeyPair zzvK() {
        if (this.zzbgT == null) {
            this.zzbgT = zzckF.zzhn(this.zzbgU);
        }
        if (this.zzbgT == null) {
            this.zzbgT = zzckF.zzhl(this.zzbgU);
        }
        return this.zzbgT;
    }

    public final void zzvL() {
        zzckF.zzhm(this.zzbgU);
        this.zzbgT = null;
    }
}
