package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.internal.zzj;
import java.util.Iterator;
import java.util.Map;

final class zzbcg extends zzbcn {
    final /* synthetic */ zzbcd zzaDp;
    private final Map<zze, zzbcf> zzaDr;

    public zzbcg(zzbcd zzbcd, Map<zze, zzbcf> map) {
        this.zzaDp = zzbcd;
        super(zzbcd, null);
        this.zzaDr = map;
    }

    @WorkerThread
    public final void zzpV() {
        boolean z;
        boolean z2;
        int i = 0;
        Iterator it = this.zzaDr.keySet().iterator();
        boolean z3 = true;
        boolean z4 = false;
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            zze zze = (zze) it.next();
            if (!zze.zzpe()) {
                z2 = false;
            } else if (!((zzbcf) this.zzaDr.get(zze)).zzaCj) {
                z4 = true;
                z = true;
                break;
            } else {
                z2 = z3;
                z4 = true;
            }
            z3 = z2;
        }
        if (z4) {
            i = this.zzaDp.zzaCF.isGooglePlayServicesAvailable(this.zzaDp.mContext);
        }
        if (i == 0 || (!z && !z3)) {
            if (this.zzaDp.zzaDj) {
                this.zzaDp.zzaDh.connect();
            }
            for (zze zze2 : this.zzaDr.keySet()) {
                zzj zzj = (zzj) this.zzaDr.get(zze2);
                if (!zze2.zzpe() || i == 0) {
                    zze2.zza(zzj);
                } else {
                    this.zzaDp.zzaCZ.zza((zzbcy) new zzbci(this, this.zzaDp, zzj));
                }
            }
            return;
        }
        this.zzaDp.zzaCZ.zza((zzbcy) new zzbch(this, this.zzaDp, new ConnectionResult(i, null)));
    }
}
