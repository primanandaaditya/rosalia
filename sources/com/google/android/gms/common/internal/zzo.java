package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

public final class zzo extends zze {
    private /* synthetic */ zzd zzaHe;

    @BinderThread
    public zzo(zzd zzd, @Nullable int i, Bundle bundle) {
        this.zzaHe = zzd;
        super(zzd, i, null);
    }

    /* access modifiers changed from: protected */
    public final void zzj(ConnectionResult connectionResult) {
        this.zzaHe.zzaGQ.zzf(connectionResult);
        this.zzaHe.onConnectionFailed(connectionResult);
    }

    /* access modifiers changed from: protected */
    public final boolean zzrj() {
        this.zzaHe.zzaGQ.zzf(ConnectionResult.zzazX);
        return true;
    }
}
