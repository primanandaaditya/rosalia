package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbo;

final class zzbbb {
    private final int zzaBP;
    private final ConnectionResult zzaBQ;

    zzbbb(ConnectionResult connectionResult, int i) {
        zzbo.zzu(connectionResult);
        this.zzaBQ = connectionResult;
        this.zzaBP = i;
    }

    /* access modifiers changed from: 0000 */
    public final int zzpy() {
        return this.zzaBP;
    }

    /* access modifiers changed from: 0000 */
    public final ConnectionResult zzpz() {
        return this.zzaBQ;
    }
}
