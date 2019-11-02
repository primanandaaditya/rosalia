package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolder.zza;
import com.google.android.gms.common.data.zzd;
import com.google.firebase.iid.zzi;
import java.util.HashMap;
import java.util.Map.Entry;

final class zzbhm extends zzbhp {
    private /* synthetic */ zzbhf zzaKu;

    zzbhm(zzbhl zzbhl, GoogleApiClient googleApiClient, zzbhf zzbhf) {
        this.zzaKu = zzbhf;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzbie zzbie) throws RemoteException {
        String str;
        String str2;
        zza zzqQ = zzd.zzqQ();
        for (Entry entry : this.zzaKu.zzsn().entrySet()) {
            zzd.zza(zzqQ, new zzbhw((String) entry.getKey(), (String) entry.getValue()));
        }
        DataHolder zzav = zzqQ.zzav(0);
        String str3 = zzbdm.zzaz(context) == Status.zzaBm ? zzbdm.zzqA() : null;
        try {
            str = zzi.zzJP().getId();
            try {
                str2 = zzi.zzJP().getToken();
            } catch (IllegalStateException e) {
                e = e;
            }
        } catch (IllegalStateException e2) {
            e = e2;
            str = null;
        }
        try {
            zzbie.zza(this.zzaKv, new zzbhy(context.getPackageName(), this.zzaKu.zzsm(), zzav, str3, str, str2, null, this.zzaKu.zzso(), zzbhk.zzaR(context), this.zzaKu.zzsp(), this.zzaKu.zzsq()));
        } finally {
            zzav.close();
        }
        if (Log.isLoggable("ConfigApiImpl", 3)) {
            Log.d("ConfigApiImpl", "Cannot retrieve instanceId or instanceIdToken.", e);
        }
        str2 = null;
        zzbie.zza(this.zzaKv, new zzbhy(context.getPackageName(), this.zzaKu.zzsm(), zzav, str3, str, str2, null, this.zzaKu.zzso(), zzbhk.zzaR(context), this.zzaKu.zzsp(), this.zzaKu.zzsq()));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result zzb(Status status) {
        return new zzbhr(status, new HashMap());
    }
}
