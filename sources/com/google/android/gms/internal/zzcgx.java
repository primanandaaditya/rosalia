package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcgx implements Callable<List<zzcjk>> {
    private /* synthetic */ String zzbjh;
    private /* synthetic */ zzcgq zzbtf;
    private /* synthetic */ String zzbth;
    private /* synthetic */ String zzbti;

    zzcgx(zzcgq zzcgq, String str, String str2, String str3) {
        this.zzbtf = zzcgq;
        this.zzbjh = str;
        this.zzbth = str2;
        this.zzbti = str3;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzbtf.zzboe.zzze();
        return this.zzbtf.zzboe.zzwz().zzh(this.zzbjh, this.zzbth, this.zzbti);
    }
}
