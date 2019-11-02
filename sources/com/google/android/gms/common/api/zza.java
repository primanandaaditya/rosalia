package com.google.android.gms.common.api;

import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbat;
import java.util.ArrayList;

public final class zza extends Exception {
    private final ArrayMap<zzbat<?>, ConnectionResult> zzaAB;

    public zza(ArrayMap<zzbat<?>, ConnectionResult> arrayMap) {
        this.zzaAB = arrayMap;
    }

    public final String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzbat zzbat : this.zzaAB.keySet()) {
            ConnectionResult connectionResult = (ConnectionResult) this.zzaAB.get(zzbat);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String valueOf = String.valueOf(zzbat.zzpr());
            String valueOf2 = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(valueOf2).length()).append(valueOf).append(": ").append(valueOf2).toString());
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    public final ConnectionResult zza(GoogleApi<? extends ApiOptions> googleApi) {
        zzbat zzph = googleApi.zzph();
        zzbo.zzb(this.zzaAB.get(zzph) != null, (Object) "The given API was not part of the availability request.");
        return (ConnectionResult) this.zzaAB.get(zzph);
    }

    public final ArrayMap<zzbat<?>, ConnectionResult> zzpf() {
        return this.zzaAB;
    }
}
