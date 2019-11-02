package com.google.firebase.crash;

import android.util.Log;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.C0170mh;
import com.google.android.gms.internal.C0172mj;
import com.google.android.gms.internal.C0174ml;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zze implements Runnable {
    private /* synthetic */ zzc zzbYd;
    private /* synthetic */ Future zzbYe;
    private /* synthetic */ long zzbYf = 10000;
    private /* synthetic */ zzf zzbYg;

    zze(zzc zzc, Future future, long j, zzf zzf) {
        this.zzbYd = zzc;
        this.zzbYe = future;
        this.zzbYg = zzf;
    }

    public final void run() {
        C0172mj mjVar;
        try {
            mjVar = (C0172mj) this.zzbYe.get(this.zzbYf, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e("FirebaseCrash", "Failed to initialize crash reporting", e);
            this.zzbYe.cancel(true);
            mjVar = null;
        }
        if (mjVar == null) {
            this.zzbYg.zzFh();
            return;
        }
        try {
            FirebaseOptions options = this.zzbYd.zzbVZ.getOptions();
            mjVar.zza(zzn.zzw(this.zzbYd.mContext), new C0170mh(options.getApplicationId(), options.getApiKey()));
            if (this.zzbYd.zzbYc == null) {
                this.zzbYd.zzbYc = FirebaseInstanceId.getInstance().getId();
            }
            mjVar.zzgz(this.zzbYd.zzbYc);
            String valueOf = String.valueOf(C0174ml.zzFj());
            Log.i("FirebaseCrash", new StringBuilder(String.valueOf(valueOf).length() + 36).append("FirebaseCrash reporting initialized ").append(valueOf).toString());
            this.zzbYg.zzc(mjVar);
        } catch (Exception e2) {
            Exception exc = e2;
            String str = "FirebaseCrash";
            String str2 = "Failed to initialize crash reporting: ";
            String valueOf2 = String.valueOf(exc.getMessage());
            Log.e(str, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
            zzg.zza(this.zzbYd.mContext, exc);
            this.zzbYg.zzFh();
        }
    }
}
