package com.google.android.gms.internal;

import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzbbc implements Runnable {
    private final zzbbb zzaBR;
    final /* synthetic */ zzbba zzaBS;

    zzbbc(zzbba zzbba, zzbbb zzbbb) {
        this.zzaBS = zzbba;
        this.zzaBR = zzbbb;
    }

    @MainThread
    public final void run() {
        if (this.zzaBS.mStarted) {
            ConnectionResult zzpz = this.zzaBR.zzpz();
            if (zzpz.hasResolution()) {
                this.zzaBS.zzaEG.startActivityForResult(GoogleApiActivity.zza(this.zzaBS.getActivity(), zzpz.getResolution(), this.zzaBR.zzpy(), false), 1);
            } else if (this.zzaBS.zzaBd.isUserResolvableError(zzpz.getErrorCode())) {
                this.zzaBS.zzaBd.zza(this.zzaBS.getActivity(), this.zzaBS.zzaEG, zzpz.getErrorCode(), 2, this.zzaBS);
            } else if (zzpz.getErrorCode() == 18) {
                GoogleApiAvailability.zza(this.zzaBS.getActivity().getApplicationContext(), (zzbdl) new zzbbd(this, GoogleApiAvailability.zza(this.zzaBS.getActivity(), (OnCancelListener) this.zzaBS)));
            } else {
                this.zzaBS.zza(zzpz, this.zzaBR.zzpy());
            }
        }
    }
}
