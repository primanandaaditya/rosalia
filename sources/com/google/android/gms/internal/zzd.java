package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd extends Thread {
    private static final boolean DEBUG = zzab.DEBUG;
    private final BlockingQueue<zzp<?>> zzg;
    /* access modifiers changed from: private */
    public final BlockingQueue<zzp<?>> zzh;
    private final zzb zzi;
    private final zzw zzj;
    private volatile boolean zzk = false;

    public zzd(BlockingQueue<zzp<?>> blockingQueue, BlockingQueue<zzp<?>> blockingQueue2, zzb zzb, zzw zzw) {
        this.zzg = blockingQueue;
        this.zzh = blockingQueue2;
        this.zzi = zzb;
        this.zzj = zzw;
    }

    public final void quit() {
        this.zzk = true;
        interrupt();
    }

    public final void run() {
        if (DEBUG) {
            zzab.zza("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzi.initialize();
        while (true) {
            try {
                zzp zzp = (zzp) this.zzg.take();
                zzp.zzb("cache-queue-take");
                zzc zza = this.zzi.zza(zzp.getUrl());
                if (zza == null) {
                    zzp.zzb("cache-miss");
                    this.zzh.put(zzp);
                } else {
                    if (zza.zzd < System.currentTimeMillis()) {
                        zzp.zzb("cache-hit-expired");
                        zzp.zza(zza);
                        this.zzh.put(zzp);
                    } else {
                        zzp.zzb("cache-hit");
                        zzt zza2 = zzp.zza(new zzn(zza.data, zza.zzf));
                        zzp.zzb("cache-hit-parsed");
                        if (!(zza.zze < System.currentTimeMillis())) {
                            this.zzj.zza(zzp, zza2);
                        } else {
                            zzp.zzb("cache-hit-refresh-needed");
                            zzp.zza(zza);
                            zza2.zzag = true;
                            this.zzj.zza(zzp, zza2, new zze(this, zzp));
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}
