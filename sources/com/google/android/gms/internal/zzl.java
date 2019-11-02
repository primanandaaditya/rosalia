package com.google.android.gms.internal;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzl extends Thread {
    private final zzb zzi;
    private final zzw zzj;
    private volatile boolean zzk = false;
    private final BlockingQueue<zzp<?>> zzw;
    private final zzk zzx;

    public zzl(BlockingQueue<zzp<?>> blockingQueue, zzk zzk2, zzb zzb, zzw zzw2) {
        this.zzw = blockingQueue;
        this.zzx = zzk2;
        this.zzi = zzb;
        this.zzj = zzw2;
    }

    public final void quit() {
        this.zzk = true;
        interrupt();
    }

    public final void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                zzp zzp = (zzp) this.zzw.take();
                try {
                    zzp.zzb("network-queue-take");
                    TrafficStats.setThreadStatsTag(zzp.zzc());
                    zzn zza = this.zzx.zza(zzp);
                    zzp.zzb("network-http-complete");
                    if (!zza.zzz || !zzp.zzl()) {
                        zzt zza2 = zzp.zza(zza);
                        zzp.zzb("network-parse-complete");
                        if (zzp.zzh() && zza2.zzae != null) {
                            this.zzi.zza(zzp.getUrl(), zza2.zzae);
                            zzp.zzb("network-cache-written");
                        }
                        zzp.zzk();
                        this.zzj.zza(zzp, zza2);
                    } else {
                        zzp.zzc("not-modified");
                    }
                } catch (zzaa e) {
                    e.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzj.zza(zzp, e);
                } catch (Exception e2) {
                    zzab.zza(e2, "Unhandled exception %s", e2.toString());
                    zzaa zzaa = new zzaa((Throwable) e2);
                    zzaa.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzj.zza(zzp, zzaa);
                }
            } catch (InterruptedException e3) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}
