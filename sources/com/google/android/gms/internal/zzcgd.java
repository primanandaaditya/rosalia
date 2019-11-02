package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

final class zzcgd implements Runnable {
    private /* synthetic */ zzcgl zzbrM;
    private /* synthetic */ long zzbrN;
    private /* synthetic */ Bundle zzbrO;
    private /* synthetic */ zzcfl zzbrP;
    private /* synthetic */ Context zztF;

    zzcgd(zzcgc zzcgc, zzcgl zzcgl, long j, Bundle bundle, Context context, zzcfl zzcfl) {
        this.zzbrM = zzcgl;
        this.zzbrN = j;
        this.zzbrO = bundle;
        this.zztF = context;
        this.zzbrP = zzcfl;
    }

    public final void run() {
        zzcjk zzG = this.zzbrM.zzwz().zzG(this.zzbrM.zzwu().zzhl(), "_fot");
        long j = (zzG == null || !(zzG.mValue instanceof Long)) ? 0 : ((Long) zzG.mValue).longValue();
        long j2 = this.zzbrN;
        long j3 = (j <= 0 || (j2 < j && j2 > 0)) ? j2 : j - 1;
        if (j3 > 0) {
            this.zzbrO.putLong("click_timestamp", j3);
        }
        AppMeasurement.getInstance(this.zztF).logEventInternal("auto", "_cmp", this.zzbrO);
        this.zzbrP.zzyD().log("Install campaign recorded");
    }
}
